由于riak是由erlang编写的数据库，所以首先要安装erlang，这里选择R14B04版本，riak选择1.4.2版本。

在安装erlang之前，先要安装openssl，本来安装erlang可以不需要依赖openssl的，但是装riak的时候需要依赖erlang依赖openssl编译出的crypto，所以就要先装上了。

这里是安装[openssl](https://github.com/ruanzhijun/share/blob/master/shell/install-erlang.sh)、[erlang](https://github.com/ruanzhijun/share/blob/master/shell/install-erlang.sh)、[riak](https://github.com/ruanzhijun/share/blob/master/shell/install-riak.sh)的命令脚本。

riak启动命令：
```
[root@localhost ~]# /install/riak-1.4.2/rel/riak/bin/riak
Usage: riak {start | stop| restart | reboot | ping | console | attach | 
                    attach-direct | ertspath | chkconfig | escript | version | 
                    getpid | top [-interval N] [-sort reductions|memory|msg_q] [-lines N] }
```

配置Riak Control。Riak Control是riak自带的图形化管理界面。

以下是配置Riak Control的方法：

```
修改app.config

设置https的端口：{https, [{ "127.0.0.1", 8069 }]}

启用ssl密钥文件：{ssl, [
       {certfile, "./etc/cert.pem"},
       {keyfile, "./etc/key.pem"}
      ]},
	  
启用控制面板
{riak_control, [
         %% Set to false to disable the admin panel.
          {enabled,true}

定义用户名和密码
{auth, userlist}

{userlist, [{"user", "pass"}
           ]},
		   
		
开启admin		
{admin, true}

完成！重启riak。
```

[demo项目](ssssssssssssssssssss)，里面有详细注释。
