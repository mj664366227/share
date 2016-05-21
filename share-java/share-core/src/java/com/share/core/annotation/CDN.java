package com.share.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * CDN注解(使用了此注解的url可以加入cdn缓存)
 * @author ruan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CDN {
	/**
	 * 缓存时间
	 * @author ruan 
	 */
	int value();

	/**
	 * 时间单位
	 * @author ruan 
	 */
	TimeUnit unit();
}