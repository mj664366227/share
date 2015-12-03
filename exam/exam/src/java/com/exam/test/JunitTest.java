package com.exam.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exam.dao.ExamDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:exam.xml" })
public class JunitTest {
	@Autowired
	private ExamDao examDao;
	private ExecutorService executor = Executors.newFixedThreadPool(20);

	// http://tts.baidu.com/text2audio?lan=zh&pid=101&ie=UTF-8&text=%E3%80%90%E6%A6%82%E6%8B%AC%E4%BB%8B%E7%BB%8D%E3%80%91%20%E8%AF%AD%E9%9F%B3%E8%87%AA%E5%8A%A8%E7%94%9F%E6%88%90%E5%99%A8%20%E6%98%AF%E4%B8%80%E6%AC%BE%E5%85%8D%E8%B4%B9%E7%89%88%E7%9A%84%20%E6%96%87%E5%AD%97%E8%BD%AC%E6%8D%A2%E8%AF%AD%E9%9F%B3%E8%BD%AF%E4%BB%B6%20,%E9%87%87%E7%94%A8%E4%BA%86%E5%9B%BD%E9%99%85%E9%A2%86%E5%85%88%E7%9A%84%E8%AF%AD%E9%9F%B3%E5%90%88%E6%88%90%E6%8A%80%E6%9C%AF,%E5%8F%AF%E4%BB%A5%E5%B0%86%E6%96%87%E6%9C%AC%E6%96%87%E5%AD%97%E6%8D%A2%E5%8C%96%E6%88%90%E9%9F%B3%E9%A2%91%E6%A0%BC%E5%BC%8F%E6%96%87%E4%BB%B6%E3%80%82%E8%BD%AF%E4%BB%B6%E6%94%AF%E6%8C%81%E5%9B%9B%E5%B7%9D%E8%AF%9D%E3%80%81%E7%B2%A4%E8%AF%AD%E7%AD%89%E3%80%81...%EF%BC%81&spd=3

	@Test
	public void test() throws Exception {
		for (int i = 0; i < 20; i++) {
			executor.execute(new Runnable() {
				public void run() {
					while (true) {
						examDao.addExam1();
						examDao.addExam4();
					}
				}
			});
		}
		Thread.sleep(Long.MAX_VALUE);
	}
}