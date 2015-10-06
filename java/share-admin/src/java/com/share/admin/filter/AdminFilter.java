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

public class AdminFilter extends BaseFilter {
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

	protected boolean doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = request.getRequestURI().trim();
		String sessionString = session.getString(request, response, SessionKey.LoginData.toString());
		logger.info(url);
		if ("/".equals(url)) {
			response.sendRedirect(URL.Index);
			return false;
		}
		if (!sessionString.isEmpty() && url.equals(URL.UserLogin)) {
			response.sendRedirect(URL.Index);
			return false;
		}
		if (sessionString.isEmpty() && !isInWhiteList(url)) {
			response.sendRedirect(URL.UserLogin);
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