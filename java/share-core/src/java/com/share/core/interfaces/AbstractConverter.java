package com.share.core.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.share.core.util.SystemUtil;

/**
 * 数据转换器
 * @author ruan
 *
 */
public abstract class AbstractConverter implements HandlerMethodArgumentResolver {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 系统字符集	
	 */
	protected String chatset = SystemUtil.getSystemCharsetString();

	public boolean supportsParameter(MethodParameter parameter) {
		return true;
	}

	public abstract Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory);
}