###说明
######使用每一种后端语言实现一个可立刻上手编程的框架。目前只实现了php和java。
######把常用的linux服务器软件的安装过程编辑成shell脚本，实现一键傻瓜式配置生产环境。

---

###已实现项目
######shareTools
一些在日常开发过程中小工具的集合，例如：md5计算，urlencode等等。

######monitor
一个类似监控宝的服务器监控程序，基于snmp v3实现。


```java
package com.share.test.junit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.core.annotation.processor.MenuProcessor;
import com.share.core.annotation.processor.ProtocolProcessor;
import com.share.core.client.HttpClient;
import com.share.core.memory.Memory;
import com.share.core.mongo.Mongodb;
import com.share.core.nsq.NsqService;
import com.share.core.redis.Redis;
import com.share.core.ssdb.SSDB;
import com.share.core.system.SystemProperty;
import com.share.core.threadPool.DefaultThreadPool;
import com.share.test.db.AdminDbService;
import com.share.test.protocol.ReqDemo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:share-test.xml" })
public class JunitTest {
	@Autowired
	private AdminDbService jdbc;
	@Autowired
	private Redis redis;
	@Autowired
	private SSDB ssdb;
	@Autowired
	private HttpClient httpClient;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private Memory memory;
	@Autowired
	private ProtocolProcessor protocolProcessor;
	@Autowired
	private DefaultThreadPool threadPool;
	@Autowired
	private Mongodb mongodb;
	@Autowired
	private NsqService nsqService;
	@Autowired
	private MenuProcessor menuProcessor;
	@Autowired
	private SystemProperty systemProperty;

	@Test
	public void junitTest() throws TTransportException, InterruptedException {
		byte[] b = new byte[24];
		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(b);
		ReqDemo req = new ReqDemo();
		req.loadFromBuffer(buf);
		byte[] bytes = httpClient.post("http://127.0.0.1:8080/demo/data",b);
		System.err.println(bytes);
		nsqService.produce("a", new byte[1]);
	}
}
```