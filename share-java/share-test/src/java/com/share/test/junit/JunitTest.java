package com.share.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.core.component.JwtService;
import com.share.core.util.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:share-test.xml" })
public class JunitTest {
	private final static Logger logger = LoggerFactory.getLogger(JunitTest.class);

	@Autowired
	private JwtService jwtService;

	@Test
	public void junitTest() throws Exception {
		JSONObject data = new JSONObject();
		data.put("a", 1);
		data.put("b", 2);
		data.put("c", 3);
		data.put("d", 4);
		System.err.println(data);
		String encode = jwtService.sign(data);
		System.err.println("encode: " + encode);
		JSONObject json = jwtService.verify(encode);
		System.err.println(json);
	}
}