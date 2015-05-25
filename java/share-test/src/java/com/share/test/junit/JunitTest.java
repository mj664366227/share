package com.share.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.core.annotation.processor.MenuProcessor;
import com.share.core.annotation.processor.ProtocolProcessor;
import com.share.core.aspect.AspectHaHaHa;
import com.share.core.client.HttpClient;
import com.share.core.memory.Memory;
import com.share.core.mongo.Mongodb;
import com.share.core.nsq.NsqService;
import com.share.core.redis.Redis;
import com.share.core.ssdb.SSDB;
import com.share.core.threadPool.DefaultThreadPool;
import com.share.core.util.Time;
import com.share.soa.thrift.client.ThriftSocketClient;
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
	private AspectHaHaHa aspectHaHaHa;
	@Autowired
	private ThriftSocketClient thriftSocketClient;

	@Test
	public void junitTest() throws Exception {
		long t = System.nanoTime();
		thriftSocketClient.invoke("test", 123232);
		System.err.println(Time.showTime(System.nanoTime() - t));
	}
}