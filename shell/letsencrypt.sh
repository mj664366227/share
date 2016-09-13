# 使用Let's Encrypt生成https证书
# 参考文章：http://www.tuicool.com/articles/yyEvmau

#建立临时安装目录
echo 'preparing working path...'
install_path='/letsencrypt'
rm -rf $install_path
mkdir -p $install_path

# 下载所需文件
cd $install_path
wget https://raw.githubusercontent.com/xdtianyu/scripts/master/lets-encrypt/letsencrypt.sh  
chmod 777 letsencrypt.sh 

# 创建配置文件
touch letsencrypt.conf
echo 'ACCOUNT_KEY="letsencrypt-account.key"' >> letsencrypt.conf
echo 'DOMAIN_KEY="ruanzhijun.com.key"' >> letsencrypt.conf
echo 'DOMAIN_DIR="/"' >> letsencrypt.conf
echo 'DOMAINS="DNS:ruanzhijun.com"' >> letsencrypt.conf
echo '#ECC=TRUE' >> letsencrypt.conf
echo '#LIGHTTPD=TRUE' >> letsencrypt.conf

# 生成证书
./letsencrypt.sh letsencrypt.conf

# https://www.ssllabs.com/ssltest
