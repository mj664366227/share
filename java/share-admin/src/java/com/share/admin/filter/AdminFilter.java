package com.share.admin.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.admin.common.SessionKey;
import com.share.admin.common.URL;
import com.share.admin.util.SessionUtil;

public class AdminFilter implements Filter {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * url白名单
	 */
	private static Set<String> urlWhiteList = new HashSet<>();

	static {
		urlWhiteList.add("/");
		urlWhiteList.add(URL.UserLogin);
		urlWhiteList.add(".css");
		urlWhiteList.add(".js");
		urlWhiteList.add(".ico");
		urlWhiteList.add(".map");
		urlWhiteList.add(".eot");
		urlWhiteList.add(".otf");
		urlWhiteList.add(".svg");
		urlWhiteList.add(".ttf");
		urlWhiteList.add(".woff");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI().trim();
		String session = SessionUtil.getString(req.getSession(), SessionKey.LoginData);
		logger.info(url);
		if("/".equals(url)){
			res.sendRedirect(URL.Index);
			return;
		}
		if (!session.isEmpty() && url.equals(URL.UserLogin)) {
			res.sendRedirect(URL.Index);
			return;
		}
		if (session.isEmpty() && !isInWhiteList(url)) {
			res.sendRedirect(URL.UserLogin);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	/**
	 * 是否在白名单里面
	 * @param url
	 */
	private boolean isInWhiteList(String url) {
		for (String str : urlWhiteList) {
			if (url.endsWith(str)) {
				return true;
			}
		}
		return false;
	}
}