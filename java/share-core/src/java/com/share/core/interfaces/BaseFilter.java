package com.share.core.interfaces;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.util.SpringUtil;

/**
 * 所有filter类都继承这个类
 * @author ruan
 */
public abstract class BaseFilter implements Filter {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 当前皮肤名
	 */
	private String skin;
	/**
	 * 是否初始化完成
	 */
	private boolean init = false;

	public void init(FilterConfig config) throws ServletException {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				skin = SpringUtil.getBean(JSPResourceViewResolver.class).getSkin();
				timer.cancel();
				logger.info("jsp skin name: {}", skin);
				init = true;
			}
		}, 10000);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		if (!getIsInit()) {
			logger.warn("not init finish");
			res.sendError(HttpStatus.BAD_GATEWAY_502);
			return;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		doFilter(req, res, chain);
		request.setAttribute("skin", getSkin());
		chain.doFilter(request, response);
	}

	protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

	/**
	 * 获取皮肤名
	 * @author ruan 
	 */
	private String getSkin() {
		return skin;
	}

	/**
	 * 获取是否已经完成初始化
	 * @author ruan 
	 */
	public boolean getIsInit() {
		return init;
	}
}