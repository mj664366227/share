package com.share.core.hook;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Hook {
	/**
	 * shutdown work
	 */
	private class HookRunnable implements Runnable {
		@Override
		public void run() {
			//System.err.println("hook...");
		}
	}

	@PostConstruct
	public void init() {
		Runtime.getRuntime().addShutdownHook(new Thread(new HookRunnable()));
	}
}
