#linux mysql自动安装程序 
#运行例子：sh install-mysql.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
mysql_install_path=$1
if [ ! $mysql_install_path ] ; then
	echo 'error command!!! you must input mysql install path...'
	echo 'for example: sh install-mysql.sh /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel net-snmp-utils libc6-dev python-devel rsync perl bc libxslt-dev lrzsz

#安装cmake
cmake='cmake-3.2.1'
if [ ! -d $mysql_install_path/cmake ]; then
	echo 'installing '$cmake'...'
	if [ ! -f $base_path/$cmake.tar.gz ]; then
		echo $cmake'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$cmake.tar.gz http://www.cmake.org/files/v3.2/$cmake.tar.gz || exit
		echo 'download '$cmake' finished...'
	fi
	tar zxvf $base_path/$cmake.tar.gz -C $install_path || exit
	cd $install_path/$cmake
	./bootstrap --prefix=$mysql_install_path/cmake && make && make install || exit
	yes|cp $mysql_install_path/cmake/bin/* /usr/bin/
	echo 'export PATH='$mysql_install_path'/cmake/bin:$PATH' >> ~/.bash_profile
	source ~/.bash_profile
fi

#安装ncurses
yum -y install ncurses-devel perl || apt-get -y install libncurses5-dev || exit

#添加mysql用户
user=$(id -nu mysql)
if [ ! $user ]; then
	user='mysql'
	group='mysql'
	/usr/sbin/groupadd -f $group
	/usr/sbin/useradd -g $group $user
fi

#建立数据目录
mysql_data_path=$mysql_install_path/mysql/data
mkdir -p $mysql_data_path

#赋予数据存放目录权限
chown mysql.mysql -R $mysql_data_path || exit

#安装mysql
echo 'installing mysql...'
mysql='mysql-5.6.22'
if [ ! -d $install_path/$mysql ]; then
	if [ ! -f $base_path/$mysql.tar.gz ]; then
		echo $mysql'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$mysql.tar.gz http://cdn.mysql.com/Downloads/MySQL-5.6/$mysql.tar.gz || exit
		echo 'download '$mysql' finished...'
	fi
	tar zxvf $base_path/$mysql.tar.gz -C $install_path || exit
fi
cd $install_path/$mysql

#删除编译缓存
rm -rf $install_path/$mysql/CMakeCache.txt 

cmake . -DCMAKE_INSTALL_PREFIX=$mysql_install_path/mysql -DMYSQL_UNIX_ADDR=$mysql_data_path/mysql.sock -DSYSCONFDIR=/etc -DDEFAULT_CHARSET=utf8 -DDEFAULT_COLLATION=utf8_general_ci -DWITH_EXTRA_CHARSETS:STRING=utf8,gbk,gb2312 -DWITH_MYISAM_STORAGE_ENGINE=1 -DWITH_INNOBASE_STORAGE_ENGINE=1 -DWITH_MEMORY_STORAGE_ENGINE=1 -DWITH_READLINE=1 -DENABLED_LOCAL_INFILE=1 -DMYSQL_DATADIR=$mysql_data_path -DMYSQL_USER=mysql || exit

make || exit
make install || exit

#复制配置文件
echo "[client]
port = 3306
socket = "$mysql_install_path"/mysql/data/mysql.sock

[mysqld]
port = 3306
socket = "$mysql_install_path"/mysql/data/mysql.sock
#skip-grant-tables
skip-external-locking
key_buffer_size = 100M
max_allowed_packet = 2M
table_open_cache = 64
sort_buffer_size = 1M
net_buffer_length = 8K
read_buffer_size = 4M
read_rnd_buffer_size = 4M
myisam_sort_buffer_size = 1M
thread_concurrency = "$(cat /proc/cpuinfo | grep name | cut -f3 -d: | uniq -c | cut -b 7)"
max_connections = 10000
join_buffer_size = 2M
thread_cache_size = 1024
server-id  = 1
innodb_buffer_pool_size = 1M
innodb_log_file_size = 0M
innodb_log_buffer_size = 0M
innodb_flush_log_at_trx_commit = 0
innodb_lock_wait_timeout = 50
key_buffer_size = 2M
sort_buffer_size = 2M
sql-mode=\"NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION\"
long_query_time = 0
slow_query_log = 0
log_queries_not_using_indexes = 0
#log-slow-queries = "$mysql_install_path"/mysql/data/slowquery.log
#log-slow-admin-statements
#log-queries-not-using-indexes

[mysqldump]
quick
max_allowed_packet = 2M

[mysql]
no-auto-rehash

[myisamchk]
key_buffer_size = 2M
sort_buffer_size = 4M
read_buffer = 2M
write_buffer = 2M

[mysqlhotcopy]
interactive-timeout" > /etc/my.cnf || exit

#启动mysql服务
service mysql start
yes|cp -rf $mysql_install_path/mysql/bin/* /usr/bin/ || exit

#修改root密码
mysqladmin -u root password root

yes|cp -rf $mysql_install_path/mysql/support-files/mysql.server /etc/init.d/mysqld || exit
chmod 755 /etc/init.d/mysqld

#初始化数据库
$mysql_install_path/mysql/scripts/mysql_install_db --user=mysql --basedir=$mysql_install_path/mysql --datadir=$mysql_data_path

#开机自启动
echo '' >> /etc/rc.d/rc.local
echo 'service mysqld start' >> /etc/rc.d/rc.local
source /etc/rc.d/rc.local