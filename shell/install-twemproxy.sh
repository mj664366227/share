#linux twemproxy自动安装程序
#运行例子：sh install-twemproxy.sh /usr/local
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
twemproxy_install_path=$1
if [ ! $twemproxy_install_path ]; then
	echo 'error command!!! you must input twemproxy install path...'
	echo 'for example: sh install-twemproxy.sh /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc libxslt-dev lrzsz

#用git拉取
cd $base_path
if [ ! -d $base_path/twemproxy ]; then
	git clone https://github.com/twitter/twemproxy.git
fi
yes | cp -rf twemproxy $install_path/twemproxy

#安装twemproxy
cd $install_path/twemproxy
rm -rf $twemproxy_install_path/twemproxy
autoreconf -fvi && ./configure --prefix=$twemproxy_install_path/twemproxy && make && make install || exit
yes|cp -rf $twemproxy_install_path/twemproxy/sbin/* /usr/bin/
nutcracker -V

#生成配置文件
mkdir -p $twemproxy_install_path/twemproxy/conf
echo 'example:
listen: ip:port #twemproxy监听的端口，可以以ip:port或name:port的形式来书写。
hash: fnv1_64 #可以选择的key值的hash算法：one_at_a_time、md5、crc16、crc32(crc32 implementation compatible with libmemcached)、crc32a(correct crc32 implementation as per the spec)、fnv1_64、fnv1a_64、fnv1_32、fnv1a_32、hsieh、murmur、jenkins
hash_tag:{} # hash_tag允许根据key的一个部分来计算key的hash值，计算的结果会用于选择服务器
distribution: ketama #分布策略，存在ketama、modula、random 3种可选的配置
auto_eject_hosts: true  #是一个boolean值，用于控制twemproxy是否应该根据server的连接状态重建群集。这个连接状态是由server_failure_limit 阀值来控制。默认是false。
preconnect: true  #是一个boolean值，指示twemproxy是否应该预连接pool中的server。默认是false。
server_connections: 50 #每个server可以被打开的连接数。默认，每个服务器开一个连接。
timeout: 2000 #单位是毫秒，是连接到server的超时值。默认是永久等待。
backlog: 512 #监听TCP 的backlog（连接等待队列）的长度，默认是512。
redis: true #是一个boolean值，用来识别到服务器的通讯协议是redis还是memcached。默认是false。
server_retry_timeout: 2000 #单位是毫秒，控制服务器连接的时间间隔，在auto_eject_host被设置为true的时候产生作用。默认是30000 毫秒。
server_failure_limit: 1 #控制连接服务器的次数，在auto_eject_host被设置为true的时候产生作用。默认是2。
servers:
	- 192.168.30.170:6379:1 #部署170的redis
	- 192.168.30.171:6380:1 #部署171的redis
' > $twemproxy_install_path/twemproxy/conf/nutcracker.yml || exit

#开机自启动
echo '' >> /etc/rc.d/rc.local
echo 'nutcracker' >> /etc/rc.d/rc.local
$(source /etc/rc.d/rc.local)