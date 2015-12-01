package com.exam.core.interfaces;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.exam.core.util.StringUtil;

/**
 * jsp视图
 * @author ruan
 */
public class JSPResourceViewResolver extends InternalResourceViewResolver {
	/**
	 * 返回皮肤名字
	 */
	public String getSkin() {
		return StringUtil.getString(getPrefix()).replace("/", "");
	}
}