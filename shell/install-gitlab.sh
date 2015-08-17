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

#下载gitlab-installer.sh安装脚本
git clone https://github.com/mattias-ohlsson/gitlab-installer.git

#执行gitlab安装脚本
cd $install_path/gitlab-installer
sh gitlab-install-el6.sh || exit
