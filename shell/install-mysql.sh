#linux mysql自动安装程序 
#运行例子：sh install-mysql.sh /usr/local
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate ntp.api.bz

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
cmake='cmake-3.4.0'
if [ ! -d $mysql_install_path/cmake ]; then
	echo 'installing '$cmake'...'
	if [ ! -f $base_path/$cmake.tar.gz ]; then
		echo $cmake'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$cmake.tar.gz http://www.cmake.org/files/v3.4/$cmake.tar.gz || exit
		echo 'download '$cmake' finished...'
	fi
	tar zxvf $base_path/$cmake.tar.gz -C $install_path || exit
	cd $install_path/$cmake
	./bootstrap --prefix=$mysql_install_path/cmake && make && make install || exit
	yes|cp $mysql_install_path/cmake/bin/* /usr/bin/
	echo 'export PATH='$mysql_install_path'/cmake/bin:$PATH' >> ~/.bash_profile
	source ~/.bash_profile
fi

#安装jemalloc
jemalloc='jemalloc-4.0.4'
if [ ! -d $mysql_install_path/jemalloc ]; then
	echo 'installing '$jemalloc' ...'
	if [ ! -f $base_path/$jemalloc.tar.bz2 ]; then
		echo $jemalloc'.tar.bz2 is not exists, system will going to download it...'
		wget -O $base_path/$jemalloc.tar.bz2 http://www.canonware.com/download/jemalloc/$jemalloc.tar.bz2 || exit
		echo 'download '$jemalloc' finished...'
	fi
	tar xvf $base_path/$jemalloc.tar.bz2 -C $install_path || exit
	cd $install_path/$jemalloc
	./configure && make && make install || exit
	echo '/usr/local/lib' > /etc/ld.so.conf.d/local.conf
	ldconfig
fi

#下载boost包
boost='boost_1_59_0'
if [ ! -d $install_path/$boost ]; then
	echo 'installing '$boost' ...'
	if [ ! -f $base_path/$boost.tar.gz ]; then
		echo $boost'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$boost.tar.gz http://nchc.dl.sourceforge.net/project/boost/boost/1.59.0/$boost.tar.gz || exit
		echo 'download '$boost' finished...'
	fi
	tar zxvf $base_path/$boost.tar.gz -C $install_path || exit
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
mysql='mysql-5.7.9'
if [ ! -d $install_path/$mysql ]; then
	if [ ! -f $base_path/$mysql.tar.gz ]; then
		echo $mysql'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$mysql.tar.gz http://cdn.mysql.com/get/Downloads/MySQL-5.7/$mysql.tar.gz || exit
		echo 'download '$mysql' finished...'
	fi
	tar zxvf $base_path/$mysql.tar.gz -C $install_path || exit
fi
cd $install_path/$mysql

#删除编译缓存
rm -rf $install_path/$mysql/CMakeCache.txt 

$mysql_install_path/cmake/bin/cmake . -DCMAKE_INSTALL_PREFIX=$mysql_install_path/mysql -DMYSQL_UNIX_ADDR=$mysql_data_path/mysql.sock -DSYSCONFDIR=/etc -DDEFAULT_CHARSET=utf8 -DDEFAULT_COLLATION=utf8_general_ci -DDOWNLOAD_BOOST=1 -DCMAKE_EXE_LINKER_FLAGS="-ljemalloc" -DWITH_SAFEMALLOC=OFF -DWITH_EXTRA_CHARSETS:STRING=utf8 -DWITH_MYISAM_STORAGE_ENGINE=1 -DWITH_BOOST=$install_path/$boost -DWITH_INNOBASE_STORAGE_ENGINE=1 -DWITH_MEMORY_STORAGE_ENGINE=1 -DWITH_READLINE=1 -DENABLED_LOCAL_INFILE=1 -DMYSQL_DATADIR=$mysql_data_path -DMYSQL_USER=mysql || exit

make || exit
make install || exit

#复制配置文件
rm -rf /etc/my.cnf
echo "[client]
port = 3306
socket = "$mysql_install_path"/mysql/data/mysql.sock

[mysqld]
port = 3306
socket = "$mysql_install_path"/mysql/data/mysql.sock
skip-grant-tables
skip-name-resolve
skip-external-locking
key_buffer_size = 100K
max_allowed_packet = 200K
table_open_cache = 64
sort_buffer_size = 100K
net_buffer_length = 100K
read_buffer_size = 400K
read_rnd_buffer_size = 400K
myisam_sort_buffer_size = 100K
max_connections = 100
back_log = 100
join_buffer_size = 200K
thread_cache_size = 1024
server-id  = 1
innodb_buffer_pool_size = 200K
innodb_log_file_size = 200K
innodb_log_buffer_size = 200K
innodb_flush_log_at_trx_commit = 0
innodb_lock_wait_timeout = 50
key_buffer_size = 200K
sort_buffer_size = 200K
sql-mode=\"NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION\"
default_password_lifetime = 0
long_query_time = 0
slow_query_log = 0
log_queries_not_using_indexes = 0
#log-slow-queries = "$mysql_install_path"/mysql/data/slowquery.log
#log-slow-admin-statements
#log-queries-not-using-indexes
explicit_defaults_for_timestamp = true

[mysqldump]
quick
max_allowed_packet = 200K

[mysql]
no-auto-rehash

[myisamchk]
key_buffer_size = 200K
sort_buffer_size = 400K
read_buffer = 200K
write_buffer = 200K

[mysqlhotcopy]
interactive-timeout" > /etc/my.cnf || exit

#初始化数据库
rm -rf $mysql_data_path
$mysql_install_path/mysql/bin/mysqld --initialize --user=mysql --basedir=$mysql_install_path/mysql --datadir=$mysql_data_path

#启动mysql服务
yes|cp -rf $mysql_install_path/mysql/support-files/mysql.server /etc/init.d/mysqld || exit
chmod 755 /etc/init.d/mysqld
yes|cp -rf $mysql_install_path/mysql/bin/* /usr/bin/ || exit

service mysqld start

#修改root密码
mysql -u root -e "truncate mysql.user;" || exit
mysql -u root -e "insert into mysql.user (host,user,authentication_string) values ('%','root',password('root'));" || exit

sed -i 's/skip-grant-tables/#skip-grant-tables/' /etc/my.cnf || exit

service mysqld restart

#开机自启动
echo '' >> /etc/rc.d/rc.local
echo 'service mysqld start' >> /etc/rc.local
source /etc/rc.d/rc.local

#输出版本
mysql -uroot -proot -e "select VERSION();" || exit
