package com.share.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.share.core.annotation.processor.MenuProcessor;
import com.share.core.annotation.processor.ProtocolProcessor;
import com.share.core.aspect.AspectHaHaHa;
import com.share.core.client.HttpClient;
import com.share.core.data.data.DUser;
import com.share.core.memory.Memory;
import com.share.core.mongo.Mongodb;
import com.share.core.nsq.NsqService;
import com.share.core.redis.Redis;
import com.share.core.ssdb.SSDB;
import com.share.core.threadPool.DefaultThreadPool;
import com.share.soa.thrift.protocol.ShareObjectService;
import com.share.soa.thrift.protocol.Userf;
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
	private ShareObjectService.Iface shareObjectService;
	@Autowired
	private Userf.Iface user;

	@Test
	public void junitTest() throws Exception {
		BasicDBObject q = new BasicDBObject();
		q.put("_id", 1);
		System.err.println(mongodb.find(DUser.class, q));
	}
}