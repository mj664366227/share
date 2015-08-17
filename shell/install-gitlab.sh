#linux git
#运行例子：sh install-gitlab.sh /usr/local
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#安装EPEL扩展源
rpm -ivh http://fr2.rpmfind.net/linux/epel/6/x86_64/epel-release-6-8.noarch.rpm

#下载gitlab-installer.sh安装脚本
git clone https://github.com/mattias-ohlsson/gitlab-installer.git

#执行gitlab安装脚本
wget https://github.com/mattias-ohlsson/gitlab-installer/blob/master/gitlab-install-el6.sh || exit
sh ./gitlab-install-el6.sh || exit
