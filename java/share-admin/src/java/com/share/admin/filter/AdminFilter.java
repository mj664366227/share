package com.share.admin.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.share.admin.common.SessionKey;
import com.share.admin.common.URL;
import com.share.core.interfaces.BaseFilter;
import com.share.core.util.Time;

public class AdminFilter extends BaseFilter {
	/**
	 * url白名单
	 */
	private static Set<String> urlWhiteList = new HashSet<>();
	/**
	 * 系统启动时间
	 */
	private final static int systemStartTime = Time.now();

	static {
		urlWhiteList.add("/");
		urlWhiteList.add(URL.userlogin);
		urlWhiteList.add(URL.captcha);
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

	protected boolean doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = request.getRequestURI().trim();
		String sessionString = session.getString(request, response, SessionKey.LoginData.toString());
		request.setAttribute("version", systemStartTime);
		logger.info(url);
		if ("/".equals(url)) {
			response.sendRedirect(URL.index);
			return false;
		}
		if (!sessionString.isEmpty() && url.equals(URL.userlogin)) {
			response.sendRedirect(URL.index);
			return false;
		}
		if (sessionString.isEmpty() && !isInWhiteList(url)) {
			response.sendRedirect(URL.userlogin);
			return false;
		}
		return true;
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