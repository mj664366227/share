#linux twemproxy自动安装程序
#运行例子：sh install-php.sh 5.4.6 /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
twemproxy_install_path=$1
if [ ! $twemproxy_install_path ]; then
	echo 'error command!!! you must input twemproxy install path...'
	echo 'for example: sh install-twemproxy.sh /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc libxslt-dev lrzsz

#用git拉取
cd $twemproxy_install_path && git clone https://github.com/twitter/twemproxy.git

#安装twemproxy
cd $twemproxy_install_path/twemproxy/
autoreconf -fvi && ./configure --enable-debug=log && make && make install   