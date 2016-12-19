#centos7.2获取本机(内网)ip
ifconfig > tmp
temp=$(awk '{print;exit}' tmp | cut -f 1 -d ":")
ip=$(ifconfig $temp | grep "netmask" | cut -b 14-26)
rm -rf tmp
echo $ip