#linux gradle
#运行例子：sh install-gradle.sh /usr/local

#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#处理外部参数
gradle_install_path=$1
if [ ! $gradle_install_path ]; then
	echo 'error command!!! you must input gradle version and install path...'
	echo 'for example: sh install-gradle.sh /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#下载gradle
gradle='gradle-2.3'
if [ ! -d $install_path/$gradle ]; then
	echo 'installing '$gradle' ...'
	if [ ! -f $base_path/$gradle.zip ]; then
		echo $gradle'-bin.zip is not exists, system will going to download it...'
		wget -O $base_path/$gradle.zip --no-check-certificate https://services.gradle.org/distributions/$gradle-bin.zip || exit
		echo 'download '$gradle' finished...'
	fi
	unzip $gradle.zip
	rm -rf $gradle_install_path/gradle
	mkdir -p $gradle_install_path/gradle
	yes|cp -rf $gradle/* $gradle_install_path/gradle/
	rm -rf $gradle_install_path/gradle/media
	rm -rf $gradle_install_path/gradle/init.d
	rm -rf $gradle_install_path/gradle/bin/gradle.bat
	rm -rf $gradle_install_path/gradle/NOTICE
	rm -rf $gradle_install_path/gradle/LICENSE
	rm -rf $gradle_install_path/gradle/*.html
	rm -rf $gradle_install_path/gradle/*.txt
	rm -rf $gradle
	cd /usr/bin/
	ln -s $gradle_install_path/gradle/bin/gradle gradle
fi

gradle -version