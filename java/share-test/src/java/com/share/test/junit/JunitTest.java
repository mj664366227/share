package com.share.test.junit;

import java.util.List;

import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basho.riak.client.core.query.Namespace;
import com.share.core.annotation.processor.MenuProcessor;
import com.share.core.annotation.processor.ProtocolProcessor;
import com.share.core.client.HttpClient;
import com.share.core.memory.Memory;
import com.share.core.mongo.Mongodb;
import com.share.core.nsq.NsqService;
import com.share.core.redis.Redis;
import com.share.core.riak.Riak;
import com.share.core.ssdb.SSDB;
import com.share.core.system.SystemProperty;
import com.share.core.threadPool.DefaultThreadPool;
import com.share.test.db.AdminDbService;

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
	@Autowired
	private Riak riak;

	@Test
	public void junitTest() throws TTransportException, InterruptedException {
		riak.store("test","test", 123);
		List<Namespace> list = riak.listBuckets();
		System.err.println(list);
		/*byte[] b = new byte[24];
		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(b);
		ReqDemo req = new ReqDemo();
		req.loadFromBuffer(buf);
		byte[] bytes = httpClient.post("http://127.0.0.1:8080/demo/data",b);
		System.err.println(bytes);
		nsqService.produce("a", new byte[1]);*/
	}
}