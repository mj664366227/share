package com.share.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.share.core.exception.HttpRequestMethodNotDefineException;

public class Test {
	private static final Logger logger = LogManager.getLogger(Test.class);

	public static void main(final String... args) {

		// Set up a simple configuration that logs on the console.

		logger.trace("trace........");
		logger.info("info...........", new HttpRequestMethodNotDefineException("xxxxxxxx"));
		logger.debug("debug..........");
		logger.error("error.............");
		logger.warn("warn..........");
		logger.fatal("Exiting application  {}", "广东省各地");
	}
}