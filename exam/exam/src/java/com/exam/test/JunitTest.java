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