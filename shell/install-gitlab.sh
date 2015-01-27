#linux git
#运行例子：sh install-gitlab.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc lrzsz openssh-server postfix cronie vim-enhanced readline readline-devel ncurses-devel gdbm-devel glibc-devel tcl-devel openssl-devel curl-devel expat-devel db4-devel byacc sqlite-devel libyaml libyaml-devel libffi libffi-devel libxml2 libxml2-devel libxslt libxslt-devel libicu libicu-devel system-config-firewall-tui python-devel crontabs logwatch logrotate perl-Time-HiRes

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#处理外部参数
gitlab_install_path=$1
if [ ! $gitlab_install_path ]; then
	echo 'error command!!! you must input git version and install path...'
	echo 'for example: sh install-gitlab.sh /usr/local'
	exit
fi

# 安装ruby
ruby='ruby-2.1.5'
if [ ! -d $gitlab_install_path/ruby ]; then
	echo 'installing '$ruby' ...'
	if [ ! -f $base_path/$ruby.tar.gz ]; then
		echo $ruby'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$ruby.tar.gz --no-check-certificate http://cache.ruby-lang.org/pub/ruby/2.1/$ruby.tar.gz || exit
		echo 'download '$ruby'.tar.gz finished...'
	fi
	tar zxvf $base_path/$ruby.tar.gz -C $install_path || exit
	cd $install_path/$ruby
	./configure --prefix=$gitlab_install_path/ruby && make && make install || exit
	yes|cp $gitlab_install_path/ruby/bin/* /usr/bin/
	echo $ruby' install finished...'
	ruby -v
fi

#安装gitlab
cd $gitlab_install_path
git clone https://github.com/gitlabhq/gitlabhq.git
