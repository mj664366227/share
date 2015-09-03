package com.share.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring获取bean类(一般情况下用@Autowired不建议使用本类，实在想不到办法的情况下才使用)
 */
public class SpringUtil implements ApplicationContextAware {
	/**
	 * 当前IOC
	 */
	private static ApplicationContext applicationContext;
	/**
	 * 设置当前上下文环境，此方法由spring自动装配
	 */
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}

	/**
	 * 从当前IOC获取bean
	 * @param id bean的id
	 */
	public static Object getObject(String id) {
		return applicationContext.getBean(id);
	}

	/**
	 * 从当前IOC获取bean
	 * @param clazz bean的类
	 */
	@SuppressWarnings("unchecked")
	public final static <T> T getBean(Class<T> clazz) {
		String className = clazz.getSimpleName();
		className = className.substring(0, 1).toLowerCase() + className.substring(1);
		return (T) getObject(className);
	}
}