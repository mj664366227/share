package com.share.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.core.redis.Redis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:share-test.xml" })
public class JunitTest {
	@Autowired
	private Redis redis;

	@Test
	public void junitTest() throws Exception {
		redis.KEYS.restore("11", 1, new byte[] {});
	}
}