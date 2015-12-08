package com.exam.core.util;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http服务器工具
 * @author ruan
 */
public final class HttpServerUtil {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(HttpServerUtil.class);

	private HttpServerUtil() {
	}

	/**
	 * 发送数据
	 * @author ruan 
	 * @param object
	 * @param response
	 */
	public final static void send(Object json, ServletResponse response) {
		ServletOutputStream outputStream = null;
		try {
			response.setContentType("application/json;charset=" + SystemUtil.getSystemCharsetString());
			outputStream = response.getOutputStream();
			outputStream.write(json.toString().trim().getBytes());
			outputStream.flush();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			json = null;
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
}