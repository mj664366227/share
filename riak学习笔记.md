#安装erlang

由于riak是由erlang编写的数据库，所以首先要安装erlang。

这里选择R16B03版本。

在安装erlang，先要安装openssl，本来安装erlang可以不需要依赖openssl的，但是装riak的时候需要依赖openssl，所以就要先装上了。

这里是安装[openssl](https://github.com/ruanzhijun/share/blob/master/shell/install-erlang.sh)、[erlang](https://github.com/ruanzhijun/share/blob/master/shell/install-erlang.sh)、[riak](https://github.com/ruanzhijun/share/blob/master/shell/install-riak.sh)的命令脚本。