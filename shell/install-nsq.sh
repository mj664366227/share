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

mkdir -p $nsq_install_path/nsq
mkdir -p $nsq_install_path/nsq/data

mv $install_path/$nsq_version/ $install_path/nsq
mv $install_path/nsq/* $nsq_install_path/nsq/
chmod -R 777 $nsq_install_path/nsq/bin/*

#生成启动nsq脚本
shell='ip=$(ifconfig eth0 |grep "inet addr"| cut -f 2 -d ":"|cut -f 1 -d " ")
nsqlookupd &
nsqd --lookupd-tcp-address=$ip:4160 &
nsqadmin --lookupd-http-address=$ip:4161 -http-address=$ip:4171 &
nsq_to_file --output-dir=$nsq_install_path/nsq/data --lookupd-http-address=$ip:4161 &
'
echo $shell > $nsq_install_path/nsq/start_nsq.sh
chmod 777 $nsq_install_path/nsq/start_nsq.sh

$nsq_install_path/nsq/start_nsq.sh