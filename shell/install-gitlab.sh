#linux git
#运行例子：sh install-gitlab.sh
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path
cd $install_path

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc lrzsz

#安装EPEL扩展源
rpm -ivh http://fr2.rpmfind.net/linux/epel/6/x86_64/epel-release-6-8.noarch.rpm

#安装ruby
ruby='2.2.2'
echo 'installing ruby ...'
if [ ! -f $base_path/ruby-$ruby.tar.gz ]; then
	echo 'ruby-'$ruby'.tar.gz is not exists, system will going to download it...'
	wget -O $base_path/ruby-$ruby.tar.gz https://cache.ruby-lang.org/pub/ruby/2.2/ruby-$ruby.tar.gz || exit
	echo 'download ruby-'$ruby' finished...'
fi
tar zxvf $base_path/ruby-$ruby.tar.gz -C $install_path || exit