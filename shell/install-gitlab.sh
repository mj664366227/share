#linux git
#运行例子：sh install-gitlab.sh /usr/local
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path
cd $install_path

#处理外部参数
gitlab_install_path=$1
if [ ! $gitlab_install_path ]; then
	echo 'error command!!! you must input git install path...'
	echo 'for example: sh install-gitlab.sh /usr/local'
	exit
fi

yum -y install libtool gcc gcc-c++ make libc6-dev python-devel perl bc

#安装EPEL扩展源
rpm -ivh http://fr2.rpmfind.net/linux/epel/6/x86_64/epel-release-6-8.noarch.rpm

#安装ruby
if [ ! -d $gitlab_install_path/ruby ]; then
	ruby='2.2.2'
	echo 'installing ruby ...'
	if [ ! -f $base_path/ruby-$ruby.tar.gz ]; then
		echo 'ruby-'$ruby'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/ruby-$ruby.tar.gz https://cache.ruby-lang.org/pub/ruby/2.2/ruby-$ruby.tar.gz || exit
		echo 'download ruby-'$ruby' finished...'
	fi
	tar zxvf $base_path/ruby-$ruby.tar.gz -C $install_path || exit

	cd $install_path/ruby-$ruby
	./configure -prefix=$gitlab_install_path/ruby && make && make install || exit
	yes|cp $gitlab_install_path/ruby/bin/* /usr/bin/
	ruby -v 
fi

#安装gitlab-shell
cd $install_path
git clone https://github.com/gitlabhq/gitlab-shell.git
