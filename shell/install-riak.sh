#linux git
#运行例子：sh install-riak.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
riak_install_path=$1
if [ ! $riak_install_path ]; then
	echo 'error command!!! you must input riak install path...'
	echo 'for example: sh install-riak.sh /usr/local'
	exit
fi

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc lrzsz openssh-server postfix cronie vim-enhanced readline readline-devel ncurses-devel gdbm-devel glibc-devel tcl-devel openssl-devel curl-devel expat-devel db4-devel byacc sqlite-devel libyaml libyaml-devel libffi libffi-devel libxml2 libxml2-devel libxslt libxslt-devel libicu libicu-devel system-config-firewall-tui python-devel crontabs logwatch logrotate perl-Time-HiRes autoconf 

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#安装erlang
sh install-erlang.sh $riak_install_path

#安装riak