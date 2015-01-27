package com.share.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 系统工具
 * 
 * @author ruan
 * 
 */
public final class SystemUtil {
	/**
	 * 获取类所在的物理硬盘的路径
	 * 
	 * @author ruan
	 * @param clazz
	 * @return
	 */
	public final static String getClassPath(Class<?> clazz) {
		return clazz.getClassLoader().getResource(clazz.getName().replace(".", "/") + ".class").getFile().replace(clazz.getSimpleName() + ".class", "").trim();
	}

	/**
	 * 获取当前操作系统的名称
	 * @author ruan
	 * @return
	 */
	public final static String getOsName() {
		return System.getProperty("os.name").trim();
	}

	/**
	 * 获取一个方法的属性
	 * @author ruan
	 * @param method
	 * @return
	 */
	public final static String getMethodModifier(Method method) {
		return Modifier.toString(method.getModifiers() & Modifier.methodModifiers());
	}

	/**
	 * 异常信息转字符串
	 * @author ruan
	 * @param e
	 * @return
	 */
	public final static String stackTrace2String(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		return sw.toString().trim();
	}
}
