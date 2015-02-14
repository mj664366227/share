#linux mongodb自动安装程序 
#运行例子：sh install-ssdb.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
ssdb_install_path=$1
if [ ! $ssdb_install_path ]; then
	echo 'error command!!! you must input ssdb install path...'
	echo 'for example: sh install-ssdb.sh /usr/local'
	exit
fi

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc lrzsz

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

ssdb_install_path=$1'/ssdb'
rm -rf $ssdb_install_path

#安装ssdb
cd $install_path
wget -O ssdb.zip --no-check-certificate https://github.com/ideawu/ssdb/archive/master.zip
unzip ssdb.zip
cd ssdb-master && make && make install
yes|cp -rf $ssdb_install_path/ssdb-server /usr/bin/
yes|cp -rf $ssdb_install_path/ssdb-cli /usr/bin/

#添加自启动
echo 'rm -rf '$ssdb_install_path'/var/ssdb.pid && ssdb-server -d '$ssdb_install_path'/ssdb.conf' >> /etc/rc.local
$(source /etc/rc.d/rc.local)

echo 'ssdb install success'