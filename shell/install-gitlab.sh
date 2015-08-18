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
rpm -ivh http://fr2.rpmfind.net/linux/epel/7/x86_64/e/epel-release-7-5.noarch.rpm

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

#安装bundler 
gem sources --remove https://rubygems.org/
gem sources -a https://ruby.taobao.org/
gem sources -l
gem install bundler --no-ri --no-rdoc

#安装gitlab-shell
if [ ! -d $gitlab_install_path/git/gitlab-shell ]; then
	cd $gitlab_install_path/git
	git clone https://github.com/gitlabhq/gitlab-shell.git
	cd $gitlab_install_path/git/gitlab-shell

	#添加gitlab用户
	user=$(id -nu gitlab)
	if [ ! $user ]; then
		user='gitlab'
		group='gitlab'
		/usr/sbin/groupadd -f $group
		/usr/sbin/useradd -g $group $user
	fi
	
	su $user
	gitlab_ssh='/home/'$user'/.ssh'
	mkdir -p $gitlab_ssh
	chmod -R 700 $gitlab_ssh
	ssh-keygen -q -N '' -t rsa -f $gitlab_ssh/id_rsa
	cat $gitlab_ssh/id_rsa.pub > $gitlab_ssh/authorized_keys
	chmod -R 600 $gitlab_ssh/authorized_keys

	#获取当前ip
	ip=$(ifconfig eth0 |grep "inet addr"| cut -f 2 -d ":"|cut -f 1 -d " ")
	
	echo '
	user: gitlab
	gitlab_url: "http://'$ip':8080/"
	self_signed_cert: false
	repos_path: "'$gitlab_install_path'/git/repositories"
	auth_file: "'$gitlab_ssh'/authorized_keys"
	' > config.yml
	
	#  http://blog.csdn.net/jiedushi/article/details/8840666
	#  另外一个gitlab  https://github.com/takezoe/gitbucket
fi
