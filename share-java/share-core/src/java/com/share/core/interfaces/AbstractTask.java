package com.share.core.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 线程任务基类
 */
public abstract class AbstractTask implements Runnable {
	/**
	 * logger
	 */
	protected Logger logger = LogManager.getLogger(getClass());

	/**
	 * toString方法
	 */
	public String toString() {
		return getClass().getSimpleName();
	}
}