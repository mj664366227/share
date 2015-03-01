#运行例子：sh install-riak.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
riak_install_path=$1
riak_cluster_num=$2
if [ ! $riak_install_path ]; then
	echo 'error command!!! you must input riak install path...'
	echo 'for example: sh install-riak.sh /usr/local [cluster num]'
	exit
fi

# 如果指定集群数，强制是数字
if [ $riak_cluster_num ]; then
	flag=`echo $riak_cluster_num | grep "[^0-9]"`
	if [ $flag ]; then
		echo 'please inut a number...'
		exit	
	fi
fi

# 如果不输入聚群数，默认是1
if [ ! $riak_cluster_num ]; then
	riak_cluster_num=1
fi

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc lrzsz openssh-server postfix cronie vim-enhanced readline readline-devel ncurses-devel gdbm-devel glibc-devel tcl-devel openssl-devel curl curl-devel expat-devel db4-devel byacc sqlite-devel libyaml libyaml-devel libffi libffi-devel libxml2 libxml2-devel libxslt libxslt-devel libicu libicu-devel system-config-firewall-tui python-devel crontabs logwatch logrotate perl-Time-HiRes autoconf 

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#安装erlang
sh install-erlang.sh $riak_install_path 'R14B04'

#安装riak
riak='1.4.2'
if [ ! -d $riak_install_path/riak ]; then 
	echo 'installing riak '$riak'...'
	if [ ! -f $base_path/riak-$riak.tar.gz ]; then
		echo 'riak-'$riak'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/riak-$riak.tar.gz http://s3.amazonaws.com/downloads.basho.com/riak/1.4/$riak/riak-$riak.tar.gz || exit
		echo 'download riak '$riak' finished...'
	fi
	tar zxvf $base_path/riak-$riak.tar.gz -C $install_path || exit
	cd $install_path/riak-$riak
	make rel || exit
	
	rm -rf $riak_install_path/riak
	mkdir -p $riak_install_path/riak
	
	#获取本机ip
	ip=$(ifconfig eth0 |grep "inet addr"| cut -f 2 -d ":"|cut -f 1 -d " ")
	echo 'self ip: '$ip
	
	echo 'riak cluster num is '$riak_cluster_num
	echo ''
	
	# 清理垃圾
	cd $install_path/riak-$riak/rel/riak/data && rm -rf *
	cd $install_path/riak-$riak/rel/riak/log && rm -rf *
	
	# 定义端口
	pb_port=8087
	http_port=8098
	https_port=8069
	handoff_port=8099
	
	# 脚本文件代码
	shell=''
	
	# 本来用 make devrel DEVNODES=x 就可以解决问题，但是自己写更加可控
	for i in $(seq $riak_cluster_num); do
		node='riak'$i
		echo 'creating cluster node '$node'...'
		mkdir -p $riak_install_path/riak/$node
		yes | cp -rf $install_path/riak-$riak/rel/riak/* $riak_install_path/riak/$node/
		
		# 修改配置文件
		cd $riak_install_path/riak/$node/etc
		
		# 计算各个节点的端口
		i=i-1
		this_pb_port		=`expr $pb_port + $i \* 1000`
		this_http_port		=`expr $http_port + $i \* 1000`
		this_https_port		=`expr $https_port + $i \* 1000`
		this_handoff_port	=`expr $handoff_port + $i \* 1000`
		
		sed -i 's/{pb, [ {"127.0.0.1", 8087 } ]}/{pb, [ {"'$ip'", '$this_pb_port' } ]}/' app.config || exit 
		sed -i 's/{http, [ {"127.0.0.1", 8098 } ]}/{http, [ {"'$ip'", '$this_http_port' } ]}/' app.config || exit 
		sed -i 's/%{https, [{ "127.0.0.1", 8098 }]}/{https, [{ "'$ip'", '$this_https_port' }]}/' app.config || exit 
		sed -i 's/{handoff_port, 8099 }/{handoff_port, '$this_handoff_port' }/' app.config || exit 
		sed -i 's/{enabled, false}/{enabled, true}/' app.config || exit 
		
		sed -i 's/riak@127.0.0.1/'$node'@'$ip'/' vm.args || exit
		
		# 输出到屏幕
		echo 'node name: '$node
		echo 'pb port: '$this_pb_port
		echo 'http port: '$this_http_port
		echo 'https port: '$this_https_port
		echo 'handoff port: '$this_handoff_port
		
		echo '------------------------------------------------------------------------------------------------------'
		echo ''
		
		# 生成脚本文件
$shell=$shell$riak_install_path'/riak/'$node'/bin/riak start
'
	done;
	
$shell=$shell'
'
	# 默认第一个为主
	for i in $(seq $riak_cluster_num); do
$shell=$shell$riak_install_path'/riak/'$node'/bin/riak-admin cluster join riak1@'$ip
	done;
	
	# 写入文件
	cd $riak_install_path'/riak'
	echo $shell > run.sh && chmod 777 run.sh || exit
	
	# 加入自启动
	echo $riak_install_path'/riak/run.sh' >> /etc/rc.local || exit
	
	echo 'riak '$riak' install finished...'
fi
