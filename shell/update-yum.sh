#更新yum源
ntpdate time.nist.gov

#下载repo文件
cd /etc/yum.repos.d/
wget http://mirrors.163.com/.help/CentOS6-Base-163.repo

#替换系统的repo文件
rm -rf CentOS-Base.repo
mv CentOS6-Base-163.repo CentOS-Base.repo

#执行yum源更新
yum clean all && yum makecache && yum update