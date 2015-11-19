#linux nginx自动安装程序 
#运行例子：sh install-tengine.sh 2.1.1 /usr/local
ntpdate time.nist.gov
 

#定义本程序的当前目录
base_path=$(pwd)

#处理外部参数
tengine_version=$1
tengine_install_path=$2
if [ ! $tengine_version ] || [ ! $tengine_install_path ]; then
	echo 'error command!!! you must input tengine version and install path...'
	echo 'for example: sh install-tengine.sh 2.1.1 /usr/local'
	exit
fi

yum -y install gcc libc6-dev gcc-c++ pcre-devel libgd2-xpm libgd2-xpm-dev geoip-database libxml2 libxml2-dev libgeoip-dev make libxslt-dev rsync lrzsz

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
		wget -O $base_path/$zlib.tar.gz http://zlib.net/$zlib.tar.gz || exit
		echo 'download '$zlib' finished...'
	fi
	tar zxvf $base_path/$zlib.tar.gz -C $install_path || exit
fi

#下载pcre
pcre='pcre-8.37'
if [ ! -d $install_path/$pcre ]; then
	echo 'installing '$pcre' ...'
	if [ ! -f $base_path/$pcre.tar.gz ]; then
		echo $pcre'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$pcre.tar.gz ftp://ftp.csx.cam.ac.uk/pub/software/programming/pcre/$pcre.tar.gz || exit
		echo 'download '$pcre' finished...'
	fi
	tar zxvf $base_path/$pcre.tar.gz -C $install_path || exit
fi

#安装OpenSSL
openssl='openssl-1.0.2d'
echo 'installing '$openssl' ...'
if [ ! -f $base_path/$openssl.tar.gz ]; then
	echo $openssl'.tar.gz is not exists, system will going to download it...'
	wget -O $base_path/$openssl.tar.gz http://www.openssl.org/source/$openssl.tar.gz || exit
	echo 'download '$openssl' finished...'
fi
tar zxvf $base_path/$openssl.tar.gz -C $install_path || exit

#安装libatomic
libatomic='libatomic_ops-1.1'
if [ ! -d $install_path/$libatomic ]; then
	echo 'installing '$libatomic' ...'
	if [ ! -f $base_path/$libatomic.tar.gz ]; then
		echo $libatomic'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$libatomic.tar.gz http://www.hpl.hp.com/research/linux/atomic_ops/download/$libatomic.tar.gz || exit
		echo 'download '$libatomic' finished...'
	fi
	tar zxvf $base_path/$libatomic.tar.gz -C $install_path || exit
fi

#安装jemalloc
jemalloc='jemalloc-4.0.4'
echo 'installing '$jemalloc' ...'
if [ ! -f $base_path/$jemalloc.tar.bz2 ]; then
	echo $jemalloc'.tar.bz2 is not exists, system will going to download it...'
	wget -O $base_path/$jemalloc.tar.bz2 http://www.canonware.com/download/jemalloc/$jemalloc.tar.bz2 || exit
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
		wget -O $base_path/$tengine.tar.gz http://tengine.taobao.org/download/$tengine.tar.gz || exit
		echo 'download '$tengine' finished...'
	fi
	tar zxvf $base_path/$tengine.tar.gz -C $install_path || exit
	
	#加载外部模块
	cd $base_path
	if [ ! -d $base_path/nginx-http-concat ]; then
		git clone https://github.com/alibaba/nginx-http-concat.git
	fi
	yes | cp -rf nginx-http-concat $install_path/nginx-http-concat
	
	cd $install_path/$tengine
	./configure --prefix=$tengine_install_path/tengine --with-http_concat_module --with-http_stub_status_module  --with-http_ssl_module --with-select_module --with-poll_module --with-file-aio --with-ipv6 --with-http_gzip_static_module --with-http_sub_module --with-http_ssl_module --with-pcre=$install_path/$pcre --with-zlib=$install_path/$zlib --with-openssl=$install_path/$openssl --with-md5=/usr/lib --with-sha1=/usr/lib --with-md5-asm --with-sha1-asm --with-mail --with-mail_ssl_module --with-http_spdy_module --with-http_realip_module --with-http_addition_module --with-http_dyups_module --with-http_sub_module --with-http_dav_module --with-http_flv_module --with-http_reqstat_module=shared --with-http_mp4_module --with-http_gunzip_module --with-http_random_index_module --with-http_secure_link_module --with-http_degradation_module --with-http_concat_module=shared --with-http_stub_status_module --with-jemalloc=$install_path/jemalloc --with-libatomic=$install_path/$libatomic --add-module=$install_path/nginx-http-concat && make && make install || exit
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
worker_rlimit_nofile "$ulimit";

events {
	use epoll;
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
	gzip_comp_level 4;
	gzip_types text/plain application/x-javascript text/css application/xml;
	gzip_vary on;	

	server_tokens off;    
	   
	include "$tengine_install_path"/tengine/conf/web/*.conf;
}
" > $tengine_install_path/tengine/conf/nginx.conf || exit
rm -rf $tengine_install_path/tengine/conf/web/
mkdir $tengine_install_path/tengine/conf/web/
echo 'create nginx.conf finished...'

#创建网站文件存放目录
echo 'create www_root...'
web_root='www'
mv $tengine_install_path/tengine/html $tengine_install_path/tengine/$web_root
echo "<?php phpinfo(); ?>" > $tengine_install_path/tengine/$web_root/phpinfo.php || exit

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
	#ssl_certificate security.crt;
	#ssl_certificate_key security.key;
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


#修改环境变量
echo 'modify /etc/profile...'
echo "ulimit -SHn "$ulimit >> /etc/profile
$(source /etc/profile)

#启动nginx
yes|cp -rf $tengine_install_path/tengine/sbin/nginx /usr/bin/
nginx

#开机自启动
echo '' >> /etc/rc.d/rc.local
echo 'nginx' >> /etc/rc.d/rc.local
$(source /etc/rc.d/rc.local)