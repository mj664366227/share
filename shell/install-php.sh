#linux php自动安装程序
#运行例子：sh install-php.sh 5.6.16 /usr/local
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate ntp.api.bz

#处理外部参数
php_version=$1
php_install_path=$2 
if [ ! $php_version ] || [ ! $php_install_path ]; then
	echo 'error command!!! you must input php version and install path...'
	echo 'for example: sh install-php.sh 5.6.16 /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#因为有些系统可能安装的类库不全，先给补上
yum -y install libtool sed gcc gcc-c++ make net-snmp net-snmp-devel nscd net-snmp-utils python-devel libc6-dev python-devel rsync perl bc lrzsz

# 方便以后安装扩展，安装必备的工具
if [ ! -d $php_install_path/m4 ]; then
	m4='m4-1.4.17'
	if [ ! -f $base_path/$m4.tar.gz ]; then
		wget -O $base_path/$m4.tar.gz http://ftp.gnu.org/gnu/m4/$m4.tar.gz || exit
	fi
	tar zxvf $base_path/$m4.tar.gz -C $install_path || exit
	cd $install_path/$m4
	./configure --prefix=$php_install_path/m4 && make && make install || exit
	yes|cp $php_install_path/m4/bin/* /usr/bin/
fi

if [ ! -d $php_install_path/autoconf ]; then
	autoconf='autoconf-2.69'
	if [ ! -f $base_path/$autoconf.tar.gz ]; then
		wget -O $base_path/$autoconf.tar.gz http://ftp.gnu.org/gnu/autoconf/$autoconf.tar.gz || exit
	fi
	tar zxvf $base_path/$autoconf.tar.gz -C $install_path || exit
	cd $install_path/$autoconf
	./configure --prefix=$php_install_path/autoconf && make && make install || exit
	yes|cp $php_install_path/autoconf/bin/* /usr/bin/
fi

#安装libiconv
if [ ! -d $php_install_path/libiconv ]; then
	libiconv='libiconv-1.14'
	if [ ! -f $base_path/$libiconv.tar.gz ]; then
		wget -O $base_path/$libiconv.tar.gz http://ftp.gnu.org/pub/gnu/libiconv/$libiconv.tar.gz || exit
	fi
	tar zxvf $base_path/$libiconv.tar.gz -C $install_path || exit
	cd $install_path/$libiconv/srclib
	sed -i -e '/gets is a security/d' ./stdio.in.h
	cd $install_path/$libiconv
	./configure --prefix=$php_install_path/libiconv -enable-shared --host=arm-linux && make && make install || exit
	yes|cp $php_install_path/libiconv/bin/* /usr/bin/
fi
 
# 下载zlib
zlib='zlib-1.2.8'
if [ ! -d $php_install_path/zlib ]; then
	echo 'installing '$zlib' ...'
	if [ ! -f $base_path/$zlib.tar.gz ]; then
		echo $zlib'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$zlib.tar.gz http://zlib.net/$zlib.tar.gz || exit
		echo 'download '$zlib' finished...'
	fi
	tar zxvf $base_path/$zlib.tar.gz -C $install_path || exit
	cd $install_path/$zlib
	./configure --prefix=$php_install_path/zlib && make && make install || exit
	echo $zlib' install finished...'
fi

# 安装python 
python='Python-3.4.0'
if [ ! -d $php_install_path/python ]; then
	echo 'installing '$python' ...'
	if [ ! -f $base_path/$python.tgz ]; then
		echo $python'.tgz is not exists, system will going to download it...'
		wget -O $base_path/$python.tgz --no-check-certificate http://www.python.org/ftp/python/3.4.0/$python.tgz || exit
		echo 'download '$python' finished...'
	fi
	tar xvf $base_path/$python.tgz -C $install_path || exit
	cd $install_path/$python
	./configure --prefix=$php_install_path/python --enable-shared && make && make install || exit
	yes|cp $php_install_path/python/bin/* /usr/bin/
	echo $python' install finished...'
fi

# 安装libxml2
libxml='libxml2-2.9.3'
if [ ! -d $php_install_path/libxml2 ]; then
	echo 'installing '$libxml' ...'
	if [ ! -f $base_path/$libxml.tar.gz ]; then
		echo $libxml'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$libxml.tar.gz https://git.gnome.org/browse/libxml2/snapshot/$libxml.tar.xz || exit
		echo 'download '$libxml' finished...'
	fi
	tar zxvf $base_path/$libxml.tar.gz -C $install_path || exit
	cd $install_path/$libxml
	./configure --prefix=$php_install_path/libxml2 --disable-static --with-iconv=$php_install_path/libiconv --with-zlib=$php_install_path/zlib/ && make && make install || exit
	yes|cp $php_install_path/libxml2/bin/* /usr/bin/
	echo $libxml' install finished...'
fi

# 安装OpenSSL
openssl='openssl-1.0.2d'
if [ ! -d $php_install_path/openssl ]; then
	echo 'installing '$openssl' ...'
	if [ ! -f $base_path/$openssl.tar.gz ]; then
		echo $openssl'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$openssl.tar.gz http://www.openssl.org/source/$openssl.tar.gz || exit
		echo 'download '$openssl' finished...'
	fi
	tar zxvf $base_path/$openssl.tar.gz -C $install_path || exit
	cd $install_path/$openssl
	./config --prefix=$php_install_path/openssl && $install_path/$openssl/config -t && make && make test && make install || exit
	yes|cp $php_install_path/openssl/bin/* /usr/bin/
	echo $openssl' install finished...'
fi

# 安装pcre
pcre='pcre-8.37'
if [ ! -d $php_install_path/pcre ]; then
	echo 'installing '$pcre' ...'
	if [ ! -f $base_path/$pcre.tar.gz ]; then
		echo $pcre'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$pcre.tar.gz ftp://ftp.csx.cam.ac.uk/pub/software/programming/pcre/$pcre.tar.gz || exit
		echo 'download '$pcre' finished...'
	fi
	tar zxvf $base_path/$pcre.tar.gz -C $install_path || exit
	cd $install_path/$pcre
	./configure --prefix=$php_install_path/pcre && make && make install || exit
	yes|cp $php_install_path/pcre/bin/* /usr/bin/
	echo $pcre' install finished...'
fi

# 安装curl
curl='curl-7.45.0'
if [ ! -d $php_install_path/curl ]; then
	echo 'installing '$curl' ...'
	if [ ! -f $base_path/$curl.tar.gz ]; then
		echo $curl'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/$curl.tar.gz http://curl.haxx.se/download/$curl.tar.gz || exit
		echo 'download '$curl' finished...'
	fi
	tar zxvf $base_path/$curl.tar.gz -C $install_path || exit
	cd $install_path/$curl
	./configure --prefix=$php_install_path/curl && make && make install || exit
	yes|cp $php_install_path/curl/bin/* /usr/bin/
	echo $curl' install finished...'
fi

# 安装libmcrypt
libmcrypt='2.5.7'
if [ ! -d $php_install_path/libmcrypt ]; then
	echo 'installing libmcrypt-'$libmcrypt' ...'
	if [ ! -f $base_path/libmcrypt-$libmcrypt.tar.gz ]; then
		echo 'libmcrypt-'$libmcrypt'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/libmcrypt-$libmcrypt.tar.gz ftp://mcrypt.hellug.gr/pub/crypto/mcrypt/libmcrypt/libmcrypt-$libmcrypt.tar.gz || exit
		echo 'download libmcrypt-'$libmcrypt' finished...'
	fi
	tar zxvf $base_path/libmcrypt-$libmcrypt.tar.gz -C $install_path || exit
	cd $install_path/libmcrypt-$libmcrypt
	./configure --prefix=$php_install_path/libmcrypt && make && make install || exit
	yes|cp $php_install_path/libmcrypt/bin/* /usr/bin/
	echo 'libmcrypt-'$libmcrypt' install finished...'
fi

# 安装bz2
if [ ! -d $php_install_path/bz2 ]; then
	bz2='1.0.6'
	echo 'installing '$ncurses'...'
	if [ ! -f $base_path/bzip2-$bz2.tar.gz ]; then
	echo $ncurses'.tar.gz is not exists, system will going to download it...'
		wget -O $base_path/bzip2-$bz2.tar.gz http://www.bzip.org/$bz2/bzip2-$bz2.tar.gz || exit
		echo 'download bzip2-'$bz2' finished...'
	fi
	tar zxvf $base_path/bzip2-$bz2.tar.gz -C $install_path || exit
	cd $install_path/bzip2-$bz2
	make -f Makefile-libbz2_so && make && make install || exit 
fi

# 安装GD库
# # libpng
if [ ! -d $php_install_path/libpng ]; then
	libpng='1.6.19'
	if [ ! -f $base_path/libpng-$libpng.tar.gz ]; then
		wget -O $base_path/libpng-$libpng.tar.gz http://jaist.dl.sourceforge.net/project/libpng/libpng16/$libpng/libpng-$libpng.tar.gz || exit
	fi
	tar zxvf $base_path/libpng-$libpng.tar.gz -C $install_path || exit
	cd $install_path/libpng-$libpng
	./configure --prefix=$php_install_path/libpng && make && make install || exit
	yes|cp $php_install_path/libpng/bin/* /usr/bin/
fi
# jpeg
if [ ! -d $php_install_path/jpeg ]; then
	if [ ! -f $base_path/jpegsrc.tar.gz ]; then
		wget -O $base_path/jpegsrc.tar.gz http://www.ijg.org/files/jpegsrc.v9a.tar.gz || exit
	fi
	tar zxvf $base_path/jpegsrc.tar.gz -C $install_path || exit
	cd $install_path/jpeg-9a
	./configure --prefix=$php_install_path/jpeg && make && make install || exit
	yes|cp $php_install_path/jpeg/bin/* /usr/bin/
fi
# freetype
if [ ! -d $php_install_path/freetype ]; then
	freetype='freetype-2.6.1'
	if [ ! -f $base_path/$freetype.tar.gz ]; then
		wget -O $base_path/$freetype.tar.gz http://download.savannah.gnu.org/releases/freetype/$freetype.tar.gz || exit
	fi
	rm -rf $install_path/$freetype
	tar zxvf $base_path/$freetype.tar.gz -C $install_path || exit
	cd $install_path/$freetype
	./configure --prefix=$php_install_path/freetype && make && make install || exit
	yes|cp $php_install_path/freetype/bin/* /usr/bin/
fi

echo 'installing php-'$php_version'...'
if [ ! -f $base_path/php-$php_version.tar.gz ]; then
	echo 'php-'$php_version'.tar.gz is not exists, system will going to download it...'
	wget -O $base_path/php-$php_version.tar.gz http://cn2.php.net/distributions/php-$php_version.tar.gz || exit
	echo 'download php-'$php_version' finished...'
fi
rm -rf $install_path/php-$php_version
tar zxvf $base_path/php-$php_version.tar.gz -C $install_path || exit
cd $install_path/php-$php_version

#如果安装了mysql，就把mysql扩展当作内核安装
if [ -d $php_install_path/mysql ]; then
	mysql_install='--with-mysql='$php_install_path'/mysql -with-mysqli='$php_install_path'/mysql/bin/mysql_config'
fi


./configure --prefix=$php_install_path/php --with-config-file-path=$php_install_path/php/etc --with-pcre-dir=$php_install_path/pcre --with-libxml-dir=$php_install_path/libxml2 --with-openssl-dir=$php_install_path/openssl --with-zlib-dir=$php_install_path/zlib $php_mysql_install_str --with-curl=$php_install_path/curl --with-curlwrappers --with-iconv-dir=$php_install_path/libiconv --with-mcrypt=$php_install_path/libmcrypt --with-jpeg-dir=$php_install_path/jpeg --with-png-dir=$php_install_path/libpng --with-freetype-dir=$php_install_path/freetype $mysql_install --enable-mysqlnd --with-mhash --with-snmp --with-xmlrpc --with-bz2 --with-openssl --enable-mbstring --enable-fpm --enable-zip --enable-sockets --enable-soap --enable-xml --enable-zend-signals --enable-wddx --enable-calendar --enable-mbstring --enable-bcmath --enable-ftp --enable-shmop --enable-gd-native-ttf --enable-exif --enable-sysvmsg --with-pic --enable-sysvsem --enable-sysvshm && make && make install || exit
echo 'php-'$php_version' install finshed...'

# 新建php.ini 
echo 'create php.ini...'
cp $install_path/php-$php_version/php.ini-production $php_install_path/php/etc/php.ini || exit
sed -i 's/expose_php = On/expose_php = Off/' $php_install_path/php/etc/php.ini || exit   #屏蔽php的版本
sed -i 's/display_errors = Off/display_errors = On/' $php_install_path/php/etc/php.ini || exit   #让php显示报错
sed -i 's/;date.timezone =/date.timezone = Asia\/Shanghai/' $php_install_path/php/etc/php.ini || exit #根据系统的时区设置php的时区
#sed -i 's/error_reporting = E_ALL & ~E_DEPRECATED & ~E_STRICT/error_reporting = E_ALL & ~E_DEPRECATED & ~E_STRICT & ~E_NOTICE/' $php_install_path/php/etc/php.ini || exit #修改报错等级

# 新建php-fpm.conf
echo 'create php-fpm.conf...'
mv $php_install_path/php/etc/php-fpm.conf.default $php_install_path/php/etc/php-fpm.conf || exit
#添加www用户组
user='www'
group='www'
user_exists=$(id -nu $user)
if [ ! $user_exists ]; then
	echo 'add www user...'
	/usr/sbin/groupadd -f $group
	/usr/sbin/useradd -g $group $user
fi
sed -i 's/user = nobody/user = www/' $php_install_path/php/etc/php-fpm.conf || exit
sed -i 's/group = nobody/group = www/' $php_install_path/php/etc/php-fpm.conf || exit

#启动php-fpm
yes|cp -rf $php_install_path/php/bin/* /usr/bin/
yes|cp -rf $php_install_path/php/sbin/* /usr/bin/
killall php-fpm && php-fpm

#开机自启动
echo '' >> /etc/rc.d/rc.local
echo 'php-fpm' >> /etc/rc.d/rc.local
$(source /etc/rc.d/rc.local)