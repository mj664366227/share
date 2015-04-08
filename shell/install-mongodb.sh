#linux mongodb自动安装程序 
#运行例子：sh install-mongodb.sh 3.0.1 /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
mongodb_version=$1
mongodb_install_path=$2
if [ ! $mongodb_version ] || [ ! $mongodb_install_path ] ; then
	echo 'error command!!! you must input mongodb version and install path...'
	echo 'for example: sh install-mongodb.sh 3.0.1 /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

bit=$(getconf LONG_BIT)
if [ $bit = 32 ]; then
	if [ ! -f $base_path/mongodb-$mongodb_version.tgz ]; then
		wget -O $base_path/mongodb-$mongodb_version.tgz https://fastdl.mongodb.org/linux/mongodb-linux-i686-$mongodb_version.tgz || exit
	fi
	tar xzvf $base_path/mongodb-$mongodb_version.tgz -C $install_path || exit
	mv $install_path/mongodb-linux-i686-$mongodb_version $mongodb_install_path/mongodb
else
	if [ ! -f $base_path/mongodb-$mongodb_version.tgz ]; then
		wget -O $base_path/mongodb-$mongodb_version.tgz https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-$mongodb_version.tgz || exit
	fi
	tar xzvf $base_path/mongodb-$mongodb_version.tgz -C $install_path || exit
	mv $install_path/mongodb-linux-x86_64-$mongodb_version $mongodb_install_path/mongodb
fi
mkdir -p $mongodb_install_path/mongodb/data
$mongodb_install_path/mongodb/bin/mongod --port 27017 --dbpath $mongodb_install_path/mongodb/data --logpath $mongodb_install_path/mongodb/log.log --logappend &
echo '' >> /etc/rc.d/rc.local

echo 'rm -rf '$mongodb_install_path'/mongodb/data/mongod.lock' >> /etc/rc.d/rc.local
echo $mongodb_install_path'/mongodb/bin/mongod --port 27017 --dbpath '$mongodb_install_path'/mongodb/data --logpath '$mongodb_install_path'/mongodb/log.log --logappend &' >> /etc/rc.d/rc.local
source /etc/rc.d/rc.local

