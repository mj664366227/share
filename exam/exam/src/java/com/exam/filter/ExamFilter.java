package com.exam.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exam.core.interfaces.BaseFilter;
import com.exam.core.util.FileSystem;
import com.exam.core.util.StringUtil;
import com.exam.dao.ExamDao;

public class ExamFilter extends BaseFilter {
	private final static Logger logger = LoggerFactory.getLogger(ExamFilter.class);
	/**
	 * 项目名
	 */
	private final static String projectName = FileSystem.getProjectName().substring(FileSystem.getProjectName().indexOf("-") + 1);

	@Override
	public void destroy() {
	}

	@Override
	protected boolean doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = StringUtil.getString(request.getRequestURI());
		logger.info(url);
		if ("/".equals(url)) {
			response.sendRedirect("/index");
			return false;
		}
		request.setAttribute("skin", "/" + projectName + "/" + getSkin());
		return true;
	}
}