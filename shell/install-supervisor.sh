#linux supervisor
#运行例子：sh install-supervisor.sh

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

yum install -y python-setuptools && easy_install supervisor 

echo_supervisord_conf > /etc/supervisord.conf

echo 'rm -rf /tmp/supervisor.sock && supervisord && supervisorctl reload' >> /etc/rc.local

supervisord && supervisorctl reload

echo_supervisord_conf

echo 'supervisor install success'