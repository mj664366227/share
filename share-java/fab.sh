if [ $1 ]; then
	if [ $1 = "h" ] || [ $1 = "help" ]; then
		echo '示例；'
		echo '回滚到某一次稳定的服务器版本：sh fab.sh release-xxxxxx'
		echo '正常发版：sh fab.sh' 
		exit
	fi
fi

root=$(pwd)
cd Gongzuoshi-Server
rm -rf .gradle
rm -rf $root/Gongzuoshi-Server/.git/index.lock
git branch --set-upstream-to=origin/master master
git checkout master
git pull

#回滚
if [ $1 ]; then
    exists='false'
    for branch  in `git branch -a`
    do
        if [ $branch = $1 ]; then
            exists='true'
        fi
    done
    if [ $exists = 'false' ]; then
        echo '分支 '$1' 不存在'
        exit
    fi
    # 开始回滚
    echo '正准备回滚到 '$1
    rm -rf .gradle
    rm -rf $root/Gongzuoshi-Server/.git/index.lock
    git branch --set-upstream-to=origin/$1 $1
    git checkout $1
    git pull
fi

#正常发版
now=$(date +%Y%m%d%H%M%S)
now=${now}
before7day=`date -d "-7 day" +%Y%m%d%H%M%S`
before7day=${before7day}
if [ ! $1 ]; then
    #备份本次的版本
    git branch --no-track release-$now
    git checkout release-$now
    git push --porcelain --progress --recurse-submodules=check origin refs/heads/release-$now:refs/heads/release-$now
    git checkout master
    git pull

    #删除太旧的版本
    for branch  in `git branch -a`
    do
        branch=$(echo $branch | grep 'release')
        if [ ! $branch ]; then
            continue
        fi
        #删除7天前的版本
        date=${branch##*-}
        date=${date}
        if [ $date -le $before7day ];then
             remotes=$(echo $branch | grep 'remotes/')
             if [ $remotes ]; then
                  git branch -d -r origin/$branch
             fi
             if [ ! $remotes ]; then
                  git branch -d $branch
             fi
             git push --porcelain --progress --recurse-submodules=check origin :refs/heads/$branch
        fi
    done
fi

gradle clean deploy

shell=$root/shell
rm -rf $shell
mkdir -p $shell

for dir in *
do
   tmp=$(echo $dir | grep '\-')
   if [ ! $tmp ]; then
      continue
   fi
   if [ ! -d $tmp/compile ];then
     continue
   fi
   rm -rf $root/$tmp/lib
   mkdir -p $root/$tmp/lib
   mkdir -p $root/$tmp/log
   rsync -av $root/Gongzuoshi-Server/$tmp/compile/ $root/$tmp/lib/
   rm -rf $root/Gongzuoshi-Server/$tmp/compile/
   rm -rf $root/Gongzuoshi-Server/$tmp/build/

   startName=${tmp#*-}
   className=${startName:0:1}
   mainClass='com.gu.'$startName'.Start'$(echo $className | tr '[a-z]' '[A-Z]')${startName:1}

   if [ $startName = "test" ]; then
      rm -rf $root/$tmp
      continue;
   fi

   echo "#!/bin/bash
processlist=\$(ps -ef|grep $root/$tmp|grep -v grep|awk '{print \$2}')
for proc in \$processlist
do
        kill -15 \$proc
done

classpath=\"$root/etc\"
for jar in \`ls $root/$tmp/lib/*.jar\`
do
        classpath=\$classpath:\$jar
done
cd $root/$tmp
echo '$startName start success'
rm -rf $root/$tmp/log/gc.log
java -Xverify:none -server "`cat $root/config/$tmp`" "`cat $root/config/jvm`" -Xloggc:$root/$tmp/log/gc.log -Dproject=$tmp -classpath \$classpath $mainClass > /dev/null &
" > $shell/run-$startName && chmod 777 $shell/run-$startName
   echo run-$startName >> $shell/run-all && chmod 777 $shell/run-all

   if [ $startName = "admin" ]; then
      continue;
   fi

   if [ $startName = "cron" ]; then
      continue;
   fi
   
   echo "#!/bin/bash
processlist=\$(ps -ef|grep $root-backup/$tmp|grep -v grep|awk '{print \$2}')
for proc in \$processlist
do
        kill -15 \$proc
done

classpath=\"$root-backup/etc\"
for jar in \`ls $root-backup/$tmp/lib/*.jar\`
do
        classpath=\$classpath:\$jar
done
cd $root-backup/$tmp
echo '$startName-bak start success'
rm -rf $root-backup/$tmp/log/gc.log
java -Xverify:none -server "`cat $root/config/$tmp`" "`cat $root/config/jvm`" -XX:+DoEscapeAnalysis -Xloggc:$root-backup/$tmp/log/gc.log -Dproject=$tmp -classpath \$classpath $mainClass > /dev/null &
" > $shell/run-$startName-bak && chmod 777 $shell/run-$startName-bak
   echo run-$startName-bak >> $shell/run-all-bak && chmod 777 $shell/run-all-bak
done
rm -rf .gradle
rsync -avzru4P --progress --ignore-errors --delete --exclude=log --exclude=rsync.pass --exclude=/config --exclude=Gongzuoshi-Server --exclude=*.sh --exclude=*.sql --exclude=*.php --exclude=etc --include=clearlog.sh --password-file=/srv/rsync.pass /srv/ gongzuoshi@gatherup.cc::gu
rm -rf $shell
rsync -avzru4P --progress --ignore-errors --delete --exclude=log --exclude=rsync.pass --exclude=/config --exclude=Gongzuoshi-Server --exclude=*.sh --exclude=*.sql --exclude=*.php --exclude=etc --password-file=/srv/rsync.pass /srv/ gongzuoshi@gatherup.cc::gu-backup

if [ ! $1 ]; then
    echo '本次发版为正常发版，当前代码已备份为：'release-$now
fi
if [ $1 ]; then
    echo '本次发版为回滚，回滚的版本号为：'$1
fi
