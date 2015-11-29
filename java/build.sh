root=$(pwd)
cd Gongzuoshi-Server
rm -rf .gradle
rm -rf $root/Gongzuoshi-Server/.git/index.lock

git pull
git checkout develop
git pull
gradle clean deploy

run_all=''
rm -rf /usr/bin/run-all
rm -rf $root/config/jvm

memory='-Xms8m -Xmx512m'

jvm='-Dfile.encoding=utf8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:ParallelGCThreads=4 -XX:CompileThreshold=1 -XX:+UseParallelGC -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:+UseFastAccessorMethods -XX:+DoEscapeAnalysis'

echo $jvm > $root/config/jvm

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
   rm -rf $root/$tmp/log/gc.log
   mkdir -p $root/$tmp/lib
   mkdir -p $root/$tmp/log
   rsync -av $root/Gongzuoshi-Server/$tmp/compile/ $root/$tmp/lib/
   rm -rf $root/Gongzuoshi-Server/$tmp/compile/
   rm -rf $root/Gongzuoshi-Server/$tmp/build/

   startName=${tmp#*-}
   className=${startName:0:1}
   mainClass='com.gu.'$startName'.Start'$(echo $className | tr '[a-z]' '[A-Z]')${startName:1}

   rm -rf /usr/bin/run-$startName

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
java -Xverify:none -server $memory $jvm -Xloggc:$root/$tmp/log/gc.log -Dproject=$tmp -classpath \$classpath $mainClass > /dev/null &
" > /usr/bin/run-$startName && chmod 777 /usr/bin/run-$startName
   echo run-$startName >> /usr/bin/run-all && chmod 777 /usr/bin/run-all
done
rm -rf .gradle
