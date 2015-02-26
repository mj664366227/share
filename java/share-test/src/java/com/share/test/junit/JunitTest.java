package com.share.test.junit;

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
	public void junitTest() throws Exception {
		String bucketName = "user";
		String key = "key3";
		riak.KV.store(bucketName, key, 22);
		System.exit(0);
	}
}