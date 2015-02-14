由于riak是由erlang编写的数据库，所以首先要安装erlang，这里选择R15B01版本，riak选择2.0.4版本。

在安装erlang之前，先要安装openssl，本来安装erlang可以不需要依赖openssl的，但是装riak的时候需要依赖erlang依赖openssl编译出的crypto，所以就要先装上了。

这里是安装[openssl](https://github.com/ruanzhijun/share/blob/master/shell/install-erlang.sh)、[erlang](https://github.com/ruanzhijun/share/blob/master/shell/install-erlang.sh)、[riak](https://github.com/ruanzhijun/share/blob/master/shell/install-riak.sh)的命令脚本。