package com.exam.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exam.core.interfaces.BaseFilter;
import com.exam.core.util.FileSystem;
import com.exam.core.util.Secret;
import com.exam.core.util.StringUtil;

public class ExamFilter extends BaseFilter {
	private final static Logger logger = LoggerFactory.getLogger(ExamFilter.class);
	@Override
	public void destroy() {
	}

	@Override
	protected boolean doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = StringUtil.getString(request.getRequestURI());
		HttpSession httpSession = request.getSession();
		if ("/".equals(url)) {
			httpSession.removeAttribute("login");
			response.sendRedirect("/login");
			return false;
		}
		if ("/login".equals(url)) {
			return true;
		}

		String login = StringUtil.getString(httpSession.getAttribute("login"));
		if (!login.equals(Secret.md5(FileSystem.getPropertyString("password") + FileSystem.getPropertyString("system.key")))) {
			response.sendRedirect("/login");
			return false;
		}

		if (url.indexOf(".") <= -1) {
			logger.info(url);
		}
		return true;
	}
}