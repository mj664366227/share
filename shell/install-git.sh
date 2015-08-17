#linux git
#运行例子：sh install-git.sh /usr/local
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate time.nist.gov

#定义本程序的当前目录
base_path=$(pwd)

#处理外部参数
git_install_path=$1
if [ ! $git_install_path ]; then
	echo 'error command!!! you must input git install path...'
	echo 'for example: sh install-git.sh /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path
cd $install_path

yum -y install curl curl-devel  zlib-devel  openssl-devel  perl  cpio  expat-devel  gettext-devel

if [ ! -f $base_path/git.zip ]; then
	echo 'git.zip is not exists, system will going to download it...'
	wget -O $base_path/git.zip https://github.com/git/git/archive/master.zip || exit
	echo 'download git.zip finished...'
fi
unzip git.zip || exit

cd $install_path/git-src
./configure --prefix=$git_install_path/git && make && make install || exit 
yes|cp -rf $git_install_path/git/bin/* /usr/bin/
git -version

