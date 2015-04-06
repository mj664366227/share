#linux nsq
#运行例子：sh install-nsq.sh

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path
nsq_install_path='/usr/local'

# 安装nsq 
nsq_version='nsq-0.3.2.linux-amd64.go1.4.1'
rm -rf /usr/bin/nsq*
if [ ! -f $base_path/nsq.tar.gz ]; then
	wget -O $base_path/nsq.tar.gz https://s3.amazonaws.com/bitly-downloads/nsq/$nsq_version.tar.gz
fi

tar zxvf $base_path/nsq.tar.gz -C $install_path || exit
chmod -R 777 $install_path/$nsq_version/bin/* 
yes | cp -rf $install_path/$nsq_version/bin/* /usr/bin/

nsq_path='/nsq/data'
mkdir -p $nsq_path

#获取本机ip
ip=$(ifconfig eth0 |grep "inet addr"| cut -f 2 -d ":"|cut -f 1 -d " ")

echo '' >> /etc/rc.local
echo "nsqlookupd &" >> /etc/rc.local
echo "nsqd --lookupd-tcp-address=$ip:4160 &" >> /etc/rc.local
echo "nsqadmin --lookupd-http-address=$ip:4161 -http-address=$ip:4171 &" >> /etc/rc.local
echo "nsq_to_file --output-dir=$nsq_path --lookupd-http-address=$ip:4161 &" >> /etc/rc.local

nsqlookupd &
nsqd --lookupd-tcp-address=$ip:4160 &
nsqadmin --lookupd-http-address=$ip:4161 -http-address=$ip:4171 &
nsq_to_file --output-dir=$nsq_path --lookupd-http-address=$ip:4161 &
