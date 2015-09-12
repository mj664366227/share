package com.share.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

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
	 * 提供下载文件的服务
	 * @param file 被下载的文件
	 * @param response http response对象
	 */
	public final static void downloadFile(File file, HttpServletResponse response) {
		String fileName = file.getName().trim();
		if (!file.exists()) {
			logger.error("file " + fileName + " not exists！");
			return;
		}
		logger.info("downlading file: " + fileName);

		long fileLength = file.length();
		if (fileLength <= 0) {
			logger.error("{}'s fileLength = 0", fileName);
			return;
		}

		/* 创建输入流 */
		InputStream inStream = null;
		/* 创建输出流 */
		ServletOutputStream servletOS = null;
		try {
			response.reset();
			response.setContentType("application/x-msdownload");
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + "." + fileName.substring(fileName.lastIndexOf(".") + 1) + "");
			response.setContentLengthLong(fileLength);

			inStream = new FileInputStream(file);
			servletOS = response.getOutputStream();
			int readLength;
			byte[] buf = new byte[1024];
			while ((readLength = inStream.read(buf)) != -1) {
				servletOS.write(buf, 0, readLength);
				servletOS.flush();
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (servletOS != null) {
					servletOS.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 发送数据(自动转换JSON格式)
	 * @param data 数据对象
	 * @param response
	 * @throws IOException
	 */
	public final static void send(SResponse data, ServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("status", 0);
		json.put("data", data);
		json.put("time", Time.now(true));

		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(json.toString().getBytes());
			outputStream.flush();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
	
	/**
	 * 发送成功(只返回1)
	 */
	public final static void sendSuccess(ServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("status", 0);
		json.put("data", 1);
		json.put("time", Time.now(true));

		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(json.toString().getBytes());
			outputStream.flush();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 发送错误(自动转换JSON格式)
	 * @param error 错误对象
	 * @param response http response对象
	 * @throws IOException
	 */
	public final static void sendError(Error error, ServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("status", error.getErrorCode());
		json.put("errorMsg", error.getErrorMsg());
		json.put("time", Time.now());

		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(json.toString().getBytes());
			outputStream.flush();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
}