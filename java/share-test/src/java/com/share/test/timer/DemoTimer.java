package com.share.test.timer;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoTimer {
	public Logger logger = Logger.getLogger(getClass());
	
	@Scheduled(cron = "* * * * * *")
	public void demoTimer() {
		logger.info("demo 定时器，１ｓ一次");
	}
}
