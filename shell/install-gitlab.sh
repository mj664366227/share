#linux git
#运行例子：sh install-gitlab.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#gitlab安装方法    https://about.gitlab.com/downloads/    centos 6
yum -y install openssh-server postfix cronie
service postfix start
chkconfig postfix on
lokkit -s http -s ssh

curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.rpm.sh | bash
yum -y install gitlab-ce
gitlab-ctl reconfigure
