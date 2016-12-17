#linux docker
#运行例子：sh install-docker.sh 1.12.5 /usr/local
 
#定义本程序的当前目录
base_path=$(pwd)
ntpdate ntp.api.bz

#处理外部参数
docker_version=$1
docker_install_path=$2 
if [ ! $docker_version ] || [ ! $docker_install_path ]; then
	echo 'error command!!! you must input docker version and install path...'
	echo 'for example: sh install-docker.sh 1.12.5 /usr/local'
	exit
fi

#建立临时安装目录
echo 'preparing working path...'
install_path='/install'
rm -rf $install_path
mkdir -p $install_path

#旧版本的docker
rm -rf $docker_install_path/docker
rm -rf /usr/bin/docker
rm -rf /usr/bin/docker-containerd
rm -rf /usr/bin/docker-containerd-ctr
rm -rf /usr/bin/docker-containerd-shim
rm -rf /usr/bin/docker-proxy
rm -rf /usr/bin/docker-runc
rm -rf /usr/bin/dockerd

#下载docker
if [ ! -f $base_path/docker-$docker_version.tgz ]; then
	echo 'docker-'$docker_version'.tgz is not exists, system will going to download it...'
	wget -O $base_path/docker-$docker_version.tgz http://install.ruanzhijun.cn/docker-$docker_version.tgz || exit
	echo 'download docker-'$docker_version'.tgz finished...'
fi
tar xvf $base_path/docker-$docker_version.tgz -C $docker_install_path || exit
cd $docker_install_path/docker
ln -s $docker_install_path/docker/docker /usr/bin/docker
ln -s $docker_install_path/docker/docker-containerd /usr/bin/docker-containerd
ln -s $docker_install_path/docker/docker-containerd-ctr /usr/bin/docker-containerd-ctr
ln -s $docker_install_path/docker/docker-containerd-shim /usr/bin/docker-containerd-shim
ln -s $docker_install_path/docker/docker-proxy /usr/bin/docker-proxy
ln -s $docker_install_path/docker/docker-runc /usr/bin/docker-runc
ln -s $docker_install_path/docker/dockerd /usr/bin/dockerd

#打印docker版本
docker -v