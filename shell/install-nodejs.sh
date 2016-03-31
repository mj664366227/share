#linux nodejs自动安装程序 
#http://www.cnblogs.com/zzbo/p/4963137.html
#运行例子：sh install-nodejs.sh 5.9.1 /usr/local
ntpdate ntp.api.bz
 

#定义本程序的当前目录
base_path=$(pwd)

#处理外部参数
nodejs_version=$1
nodejs_install_path=$2
if [ ! $nodejs_version ] || [ ! $nodejs_install_path ]; then
	echo 'error command!!! you must input nodejs version and install path...'
	echo 'for example: sh install-nodejs.sh 5.9.1 /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#获取c++编译器版本
gcc_version=$(gcc --version | awk '{print $3}' | head -1);
if [ $gcc_version != "5.3.0" ]; then
	#更新c++编译器
	gcc='gcc-5.3.0'
	if [ ! -f $base_path/$gcc.tar.gz ]; then
		echo $gcc'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$gcc.tar.gz https://coding.net/u/ruanzhijun/p/server-install/git/raw/master/$gcc.tar.gz || exit
		echo 'download '$gcc' finished...'
	fi
	tar zxvf $base_path/$gcc.tar.gz -C $install_path || exit
	cd $install_path/$gcc
	yum -y install gmp-devel mpfr-devel libmpc-devel
	./configure --prefix=/usr --disable-multilib && make && make install || exit
fi

#安装nodejs
rm -rf $nodejs_install_path/nodejs
echo 'installing node-v'$nodejs_version' ...'
if [ ! -f $base_path/node-v$nodejs_version.tar.gz ]; then
	echo 'node-v'$nodejs_version'.tar.gz is not exists, system will going to download it...'
	wget -O $base_path/node-v$nodejs_version.tar.gz https://coding.net/u/ruanzhijun/p/server-install/git/raw/master/node-v$nodejs_version.tar.gz || exit
	echo 'download node-v'$nodejs_version' finished...'
fi
tar zxvf $base_path/node-v$nodejs_version.tar.gz -C $install_path || exit
cd $install_path/node-v$nodejs_version
./configure --prefix=$nodejs_install_path/nodejs --enable-static && make && make install || exit
yes|cp -rf $nodejs_install_path/nodejs/bin/* /usr/bin/

