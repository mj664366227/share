package com.share.core.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * handler基类
 * @author ruan
 *
 */
public abstract class BaseHandler {
	/**
	 * logger
	 */
	protected Logger logger = LogManager.getLogger(getClass());
}