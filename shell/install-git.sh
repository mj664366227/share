#linux git
#运行例子：sh install-git.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
git_install_path=$1
if [ ! $git_install_path ]; then
	echo 'error command!!! you must input git version and install path...'
	echo 'for example: sh install-git.sh /usr/local'
	exit
fi

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc lrzsz zlib-devel curl curl-devel zlib-devel openssl-devel perl perl-devel cpio expat-devel gettext-devel

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

# 安装python 
python='Python-3.4.3'
if [ ! -d $git_install_path/python ]; then
	echo 'installing '$python' ...'
	if [ ! -f $base_path/$python.tgz ]; then
		echo $python'.tar.xz is not exists, system will going to download it...'
		wget -O $base_path/$python.tgz --no-check-certificate http://www.python.org/ftp/python/3.4.3/$python.tgz || exit
		echo 'download '$python' finished...'
	fi
	tar xvf $base_path/$python.tgz -C $install_path || exit
	cd $install_path/$python
	./configure --prefix=$git_install_path/python --enable-shared && make && make install || exit
	yes|cp $git_install_path/python/bin/* /usr/bin/
	echo $python' install finished...'
fi

# 安装curl
curl='curl-7.41.0'
if [ ! -d $git_install_path/curl ]; then
	echo 'installing '$curl' ...'
	if [ ! -f $base_path/$curl.tar.gz ]; then
		echo $curl'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$curl.tar.gz http://curl.haxx.se/download/$curl.tar.gz || exit
		echo 'download '$curl' finished...'
	fi
	tar zxvf $base_path/$curl.tar.gz -C $install_path || exit
	cd $install_path/$curl
	./configure --prefix=$git_install_path/curl && make && make install || exit
	yes|cp $git_install_path/curl/bin/* /usr/bin/
	echo $curl' install finished...'
fi

# 安装OpenSSL
openssl='openssl-1.0.2a'
if [ ! -d $git_install_path/openssl ]; then
	echo 'installing '$openssl' ...'
	if [ ! -f $base_path/$openssl.tar.gz ]; then
		echo $openssl'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$openssl.tar.gz http://www.openssl.org/source/$openssl.tar.gz || exit
		echo 'download '$openssl' finished...'
	fi
	tar zxvf $base_path/$openssl.tar.gz -C $install_path || exit
	cd $install_path/$openssl
	./config --prefix=$git_install_path/openssl && $install_path/$openssl/config -t && make && make test && make install || exit
	yes|cp $git_install_path/openssl/bin/* /usr/bin/
	echo $openssl' install finished...'
fi

# 下载zlib
zlib='zlib-1.2.8'
if [ ! -d $git_install_path/zlib ]; then
	echo 'installing '$zlib' ...'
	if [ ! -f $base_path/$zlib.tar.gz ]; then
		echo $zlib'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$zlib.tar.gz http://zlib.net/$zlib.tar.gz || exit
		echo 'download '$zlib' finished...'
	fi
	tar zxvf $base_path/$zlib.tar.gz -C $install_path || exit
	cd $install_path/$zlib
	./configure --prefix=$git_install_path/zlib && make && make install || exit
	echo $zlib' install finished...'
fi

if [ ! -d $php_install_path/autoconf ]; then
	autoconf='autoconf-2.69'
	if [ ! -f $base_path/$autoconf.tar.gz ]; then
		wget -O $base_path/$autoconf.tar.gz http://ftp.gnu.org/gnu/autoconf/$autoconf.tar.gz || exit
	fi
	tar zxvf $base_path/$autoconf.tar.gz -C $install_path || exit
	cd $install_path/$autoconf
	./configure --prefix=$php_install_path/autoconf && make && make install || exit
	yes|cp $php_install_path/autoconf/bin/* /usr/bin/
fi

#git
cd $install_path
echo 'installing '$git' ...'
if [ ! -d $git_install_path/git ]; then
	if [ ! -f $base_path/git.zip ]; then
		echo 'git.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/git.zip --no-check-certificate https://github.com/git/git/archive/master.zip || exit
		echo 'download '$git' finished...'
	fi
	unzip $base_path/git.zip || exit
fi
cd $install_path/git-master
autoconf
./configure --prefix=$git_install_path/git --with-curl --with-openssl --with-libpcre --with-python=$git_install_path/python --with-zlib=$git_install_path/zlib && make && make install || exit

yes|cp -rf $git_install_path/git/bin/* /usr/bin/
git --version