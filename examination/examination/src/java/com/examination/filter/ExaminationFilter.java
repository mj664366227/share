package com.examination.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.examination.core.interfaces.BaseFilter;
import com.examination.core.util.StringUtil;
import com.examination.core.util.Time;

public class ExaminationFilter extends BaseFilter {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 系统启动时间
	 */
	private final static int systemStartTime = Time.now();

	@Override
	public boolean doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = StringUtil.getString(request.getRequestURI());
		request.setAttribute("version", systemStartTime);
		logger.info(url);
		return true;
	}

	@Override
	public void destroy() {
	}
}