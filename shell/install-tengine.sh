#linux nginx自动安装程序 
#运行例子：sh install-tengine.sh 2.1.2 /usr/local
ntpdate ntp.api.bz
 

#定义本程序的当前目录
base_path=$(pwd)

#处理外部参数
tengine_version=$1
tengine_install_path=$2
if [ ! $tengine_version ] || [ ! $tengine_install_path ]; then
	echo 'error command!!! you must input tengine version and install path...'
	echo 'for example: sh install-tengine.sh 2.1.2 /usr/local'
	exit
fi

yum -y install gcc libc6-dev gcc-c++ pcre-devel nscd perl-devel perl-ExtUtils-Embed geoip-database libgeoip-dev make gd-devel libxslt-dev rsync lrzsz libxml2 libxml2-dev libxslt-dev libgd2-xpm libgd2-xpm-dev libpcre3 libpcre3-dev libtool sed gcc gcc-c++ make net-snmp libxml2 libxml2-devel net-snmp-devel libxslt-devel nscd net-snmp-utils python-devel libc6-dev python-devel rsync perl bc lrzsz bzip2 unzip vim iptables-services

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#下载zlib
zlib='zlib-1.2.8'
if [ ! -d $install_path/$zlib ]; then
	echo 'installing '$zlib' ...'
	if [ ! -f $base_path/$zlib.tar.gz ]; then
		echo $zlib'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$zlib.tar.gz http://install.ruanzhijun.cn/$zlib.tar.gz || exit
		echo 'download '$zlib' finished...'
	fi
	tar zxvf $base_path/$zlib.tar.gz -C $install_path || exit
fi

#下载pcre
pcre='pcre-8.39'
if [ ! -d $install_path/$pcre ]; then
	echo 'installing '$pcre' ...'
	if [ ! -f $base_path/$pcre.tar.gz ]; then
		echo $pcre'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$pcre.tar.gz http://install.ruanzhijun.cn/$pcre.tar.gz || exit
		echo 'download '$pcre' finished...'
	fi
	tar zxvf $base_path/$pcre.tar.gz -C $install_path || exit
fi

#安装OpenSSL
openssl='openssl-1.1.0c' 
echo 'installing '$openssl' ...'
if [ ! -f $base_path/$openssl.tar.gz ]; then
	echo $openssl'.tar.gz is not exists, system will going to download it...'
	wget -O $base_path/$openssl.tar.gz http://install.ruanzhijun.cn/$openssl.tar.gz || exit
	echo 'download '$openssl' finished...'
fi
tar zxvf $base_path/$openssl.tar.gz -C $install_path || exit

#安装libatomic
libatomic='libatomic_ops-1.1'
if [ ! -d $install_path/$libatomic ]; then
	echo 'installing '$libatomic' ...'
	if [ ! -f $base_path/$libatomic.tar.gz ]; then
		echo $libatomic'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$libatomic.tar.gz http://install.ruanzhijun.cn/$libatomic.tar.gz || exit
		echo 'download '$libatomic' finished...'
	fi
	tar zxvf $base_path/$libatomic.tar.gz -C $install_path || exit
fi

#安装jemalloc
jemalloc='jemalloc-4.3.1'
echo 'installing '$jemalloc' ...'
if [ ! -f $base_path/$jemalloc.tar.bz2 ]; then
	echo $jemalloc'.tar.bz2 is not exists, system will going to download it...'
	wget -O $base_path/$jemalloc.tar.bz2 http://install.ruanzhijun.cn/$jemalloc.tar.bz2 || exit
	echo 'download '$jemalloc' finished...'
fi
tar xvf $base_path/$jemalloc.tar.bz2 -C $install_path || exit
mv $install_path/$jemalloc $install_path/jemalloc 

#安装tengine
tengine='tengine-'$tengine_version
echo 'installing '$tengine' ...'
if [ ! -d $tengine_install_path/tengine ]; then
	if [ ! -f $base_path/$tengine.tar.gz ]; then
		echo $tengine'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$tengine.tar.gz http://install.ruanzhijun.cn/$tengine.tar.gz || exit
		echo 'download '$tengine' finished...'
	fi
	tar zxvf $base_path/$tengine.tar.gz -C $install_path || exit
	
	#支持合并js、css请求
	cd $base_path
	if [ ! -f $base_path/nginx-http-concat.zip ]; then
		echo 'nginx-http-concat.zip is not exists, system will going to download it...'
		wget -O $base_path/nginx-http-concat.zip http://install.ruanzhijun.cn/nginx-http-concat.zip || exit
		echo 'download nginx-http-concat finished...'
	fi
	unzip -o -d $install_path $base_path/nginx-http-concat.zip || exit
	mv $install_path/nginx-http-concat-master $install_path/nginx-http-concat
	cd $install_path/nginx-http-concat
	sed -i 's/x-javascript/javascript/' ngx_http_concat_module.c || exit
	
	#支持socket代理
	#cd $base_path
	#if [ ! -f $base_path/nginx_tcp_proxy_module.zip ]; then
	#	echo 'nginx_tcp_proxy_module.zip is not exists, system will going to download it...'
	#	wget -O $base_path/nginx_tcp_proxy_module.zip http://install.ruanzhijun.cn/nginx_tcp_proxy_module.zip || exit
	#	echo 'download nginx_tcp_proxy_module finished...'
	#fi
	#unzip -o -d $install_path $base_path/nginx_tcp_proxy_module.zip || exit
	#mv $install_path/nginx_tcp_proxy_module-master/ $install_path/nginx_tcp_proxy_module/
	
	cd $install_path/$tengine
	#patch -f -p1 < $install_path/nginx_tcp_proxy_module/tcp.patch
	./configure --prefix=$tengine_install_path/tengine --with-http_concat_module --with-http_stub_status_module --with-http_image_filter_module --with-http_ssl_module --with-select_module --with-poll_module --with-file-aio --with-ipv6 --with-http_gzip_static_module --with-http_sub_module --with-http_ssl_module --with-http_lua_module --with-http_v2_module --with-pcre=$install_path/$pcre --with-zlib=$install_path/$zlib --with-openssl=$install_path/$openssl --with-md5=/usr/lib --with-sha1=/usr/lib --with-md5-asm --with-sha1-asm --with-mail --with-mail_ssl_module --with-http_spdy_module --with-http_realip_module --with-http_addition_module --with-http_dyups_module --with-http_sub_module --with-http_dav_module --with-http_flv_module --with-http_reqstat_module=shared --with-http_mp4_module --with-http_gunzip_module --with-http_random_index_module --with-http_secure_link_module --with-http_degradation_module --with-http_concat_module=shared --with-http_stub_status_module --with-jemalloc=$install_path/jemalloc --with-libatomic=$install_path/$libatomic --add-module=$install_path/nginx-http-concat && make && make install || exit
fi

#添加nginx用户组
user='www'
group='www'
user_exists=$(id -nu $user)
if [ ! $user_exists ]; then
	echo 'add www user...'
	/usr/sbin/groupadd -f $group
	/usr/sbin/useradd -g $group $user
fi


#写入nginx配置文件
echo 'create nginx.conf...'
ulimit='65535' #单个进程最大打开文件数
worker_processes=$(cat /proc/cpuinfo | grep name | cut -f3 -d: | uniq -c | cut -b 7) #查询cpu逻辑个数
echo "user "$group" "$user";
worker_processes "$worker_processes";
worker_cpu_affinity auto;
worker_rlimit_nofile "$ulimit";

events {
	use epoll;
	accept_mutex off;
	reuse_port on;
	worker_connections "$ulimit";
}

http {
	include mime.types;
	charset utf-8;
	default_type application/octet-stream;
	access_log off;
	error_log "$tengine_install_path"/tengine/logs/error.log crit;
	sendfile on;
	tcp_nopush on;
	tcp_nodelay on;
	keepalive_timeout 60;
	client_header_buffer_size 32k;
	client_max_body_size 200m;
	
	add_header Cache-Control no-store,no-cache,must-revalidate,max-age=0;
	
	proxy_set_header  X-Real-IP \$remote_addr;
	
	fastcgi_connect_timeout 600;
	fastcgi_send_timeout 600;
	fastcgi_read_timeout 600;
	fastcgi_buffer_size 64k;
	fastcgi_buffers 4 64k;
	fastcgi_busy_buffers_size 128k;
	fastcgi_temp_file_write_size 128k;

	gzip on;
	gzip_min_length 10k;
	gzip_buffers 4 16k;
	gzip_http_version 1.0;
	gzip_comp_level 9;
	gzip_types text/plain application/x-javascript text/css application/xml;
	gzip_vary on;	

	server_tokens off;    
	   
	include "$tengine_install_path"/tengine/conf/web/*.conf;
}

#tcp {
#	access_log off; 
#	so_keepalive on;
#	tcp_nodelay on;	
#	include "$tengine_install_path"/tengine/conf/socket/*.conf; 
#}
" > $tengine_install_path/tengine/conf/nginx.conf || exit
rm -rf $tengine_install_path/tengine/conf/web/
mkdir $tengine_install_path/tengine/conf/web/
rm -rf $tengine_install_path/tengine/conf/socket/
mkdir $tengine_install_path/tengine/conf/socket/
echo 'create nginx.conf finished...'

#创建网站文件存放目录
echo 'create www_root...'
web_root='www'
mv $tengine_install_path/tengine/html $tengine_install_path/tengine/$web_root
echo "<?php phpinfo(); ?>" > $tengine_install_path/tengine/$web_root/i.php || exit

#创建一个80端口的配置文件(作为demo)
echo 'create a demo conf , 80.conf...'
echo '
#80
server {
	listen 80;
	server_name _;
	root '$web_root';
	#autoindex on; #无index时是否显示文件列表
	index index.html index.php;

	location ~ \.php$ {
		fastcgi_pass 127.0.0.1:9000;
		fastcgi_index index.php;
		fastcgi_param SCRIPT_FILENAME $document_root$fastcgi_script_name;
		include fastcgi.conf;
	}
		
	#缓存网站素材
	#location ~ .*\.(gif|jpg|jpeg|png|bmp|swf|js|css)$ {
	#	expires 30d;
	#}
		
	#如果使用了伪静态，则可以保护这些文件的正常访问
	#location ~ /(images|css|js|swf|upload)/.*$ {
	#
	#}
		
	#禁止某些文件被访问
	#location ~ .*\.(txt|ico)$ {
	#	break;
	#}
		
	#nginx 伪静态写法(一定要写在最后)
	#location ~ .*$ { 
	#	 rewrite ^/(.*)$ /index.php break;    #目录所有链接都指向index.php
	#	 fastcgi_pass  127.0.0.1:9000;
	#	 fastcgi_index index.php;
	#	 fastcgi_param SCRIPT_FILENAME $document_root$fastcgi_script_name;
	#	 include fastcgi.conf;
	#}
}
' > $tengine_install_path/tengine/conf/web/80.conf

#创建一个https的配置文件
echo 'create 443.conf...'

#首先自己生成证书，可以实现https，但是不受浏览器信任
echo '
#https
#server {
	#listen  443;
	#server_name _;
	#root  '$web_root';
	#index index.html index.php;
	
	#ssl on;
	#ssl_certificate /xxxx/xxxx.crt;
	#ssl_certificate_key /xxxx/xxxx.key;
	#ssl_ciphers "ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-AES256-GCM-SHA384:DHE-RSA-AES128-GCM-SHA256:DHE-DSS-AES128-GCM-SHA256:kEDH+AESGCM:ECDHE-RSA-AES128-SHA256:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA:ECDHE-ECDSA-AES128-SHA:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA:ECDHE-ECDSA-AES256-SHA:DHE-RSA-AES128-SHA256:DHE-RSA-AES128-SHA:DHE-DSS-AES128-SHA256:DHE-RSA-AES256-SHA256:DHE-DSS-AES256-SHA:DHE-RSA-AES256-SHA:AES128-GCM-SHA256:AES256-GCM-SHA384:AES128-SHA256:AES256-SHA256:AES128-SHA:AES256-SHA:AES:CAMELLIA:DES-CBC3-SHA:!aNULL:!eNULL:!EXPORT:!DES:!RC4:!MD5:!PSK:!aECDH:!EDH-DSS-DES-CBC3-SHA:!EDH-RSA-DES-CBC3-SHA:!KRB5-DES-CBC3-SHA";
	#ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
	#ssl_dhparam /xxxx/dhparam.pem;
	#ssl_prefer_server_ciphers on;
	#ssl_session_cache shared:SSL:10m;
#}' > $tengine_install_path/tengine/conf/web/443.conf

#安装代理服务器
echo '
#代理服务器
#server {
	#resolver 202.96.128.86; #一般是本服务器的DNS
	#listen 8000; #代理服务器的端口
	#location / {
		#proxy_pass http://$host$request_uri;
		#proxy_redirect off;
		#proxy_set_header Host $host;
		#proxy_set_header X-Real-IP $remote_addr;
		#proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		#allow all; #或者是限制某个网段的ip也可以  例如：192.168.1.0/24
		#deny all;
	#}
#}
' > $tengine_install_path/tengine/conf/web/8000.conf

#创建一个44444端口的配置文件(作为socket代理的demo)
echo '
upstream cluster{
	ip_hash;
	server 127.0.0.1:8890;   
	server 127.0.0.1:8891;    
	check interval=3000 rise=2 fall=5 timeout=1000;   
}   

server {   
	listen 44444;   
	proxy_pass cluster;   
}   
' > $tengine_install_path/tengine/conf/socket/44444.conf


#修改环境变量
echo 'modify /etc/profile...'
echo "ulimit -SHn "$ulimit >> /etc/profile
$(source /etc/profile)

#启动nginx
yes|cp -rf $tengine_install_path/tengine/sbin/nginx /usr/bin/
nginx

#关闭防火墙
systemctl stop firewalld
systemctl disable firewalld.service

#开机自启动
echo '' >> /etc/rc.d/rc.local
echo 'systemctl stop firewalld' >> /etc/rc.d/rc.local
echo 'systemctl disable firewalld.service' >> /etc/rc.d/rc.local
echo 'nginx' >> /etc/rc.d/rc.local
$(source /etc/rc.d/rc.local)