cd /share
git pull
rm -rf /share/java/.gradle

rm -rf /srv/*.sh /shell/*.sh
mkdir -p /shell
yes | cp -rf /share/java/compile.sh /srv/
yes | cp -rf /share/java/run.sh /srv/
yes | cp -rf /share/shell/*.sh /shell/
chmod 777 /srv/*.sh /shell/*.sh

for dir in /share/java/*
do 
	flag=$(echo $dir | grep 'share-')
	if [ ! -n "$flag" ]; then
		continue
	fi
	
	if [ ! -d $dir/compile ]; then
		continue
	fi
	rm -rf $dir/build/
	rm -rf $dir/compile/
	
	project=${dir##*/share/java/}
	rm -rf /srv/server/$project/lib/
	rm -rf /srv/server/$project/run.sh
	
	mkdir -p /srv/server/$project/lib/
	mkdir -p /srv/server/$project/log/
done

cd /share/java
gradle clean deploy

for dir in /share/java/*
do 
	flag=$(echo $dir | grep 'share-')
	if [ ! -n "$flag" ]; then
		continue
	fi
	
	project=${dir##*/share/java/}
	
	if [ ! -d $dir/compile ]; then
		continue
	fi
	
	if [ -d $dir/src/resources ]; then
		for res in $dir/src/resources/*
		do
			flag=$(echo $res | grep '\.')
			if [ -n "$flag" ]; then
				continue
			fi
			resourcesName=${res##*resources/}
			rm -rf /srv/server/$project/$resourcesName
			mkdir -p /srv/server/$project/$resourcesName
			yes | cp -rf $res /srv/server/$project/
		done
	fi
		
	rsync -av /share/java/$project/compile/ /srv/server/$project/lib/
	echo "cd /srv/server/$project & sh /srv/run.sh '/srv/server/$project' '$project' 'com.share.admin.start.AdminMain'" > /srv/server/$project/run.sh
	chmod 777 /srv/server/$project/run.sh
done