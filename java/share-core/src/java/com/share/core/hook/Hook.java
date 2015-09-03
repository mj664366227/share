package com.share.core.hook;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Hook {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Hook.class);
	
	/**
	 * shutdown work
	 */
	private class HookRunnable implements Runnable {
		@Override
		public void run() {
			logger.info("run hook...");
		}
	}

	@PostConstruct
	public void init() {
		Runtime.getRuntime().addShutdownHook(new Thread(new HookRunnable()));
	}
}
