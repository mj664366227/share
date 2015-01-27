package com.share.core.system;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统基础配置
 * @author ruan
 *
 */
@Component
public class SystemProperty {
	/**
	 * 系统通信key
	 */
	@Value("${system.key}")
	private String systemKey;
	/**
	 * 系统语言
	 */
	@Value("${system.lang}")
	private String systemLang;
	/**
	 * 系统字符集
	 */
	@Value("${system.charset}")
	private String systemCharset;
	
	/**
	 * 私有构造函数
	 */
	private SystemProperty() {
	}

	/**
	 * 获取系统通信key
	 * @return
	 */
	public String getSystemKey() {
		return systemKey;
	}

	/**
	 * 获取系统语言
	 * @return
	 */
	public String getSystemLang() {
		return systemLang;
	}

	/**
	 * 获取系统字符集
	 * @return
	 */
	public String getSystemCharsetString() {
		return systemCharset;
	}

	/**	
	 * 获取系统字符集
	 * @return
	 */
	public Charset getSystemCharset() {
		return Charset.forName(systemCharset);
	}
}