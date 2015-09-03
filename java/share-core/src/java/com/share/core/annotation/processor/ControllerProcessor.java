package com.share.core.annotation.processor;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;

import com.share.core.exception.HttpRequestMethodNotDefineException;
import com.share.core.exception.URLNotDefineException;

/**
 * 控制器注解解析器<br>
 * 用来限制有使用@RequestMapping注解的方法一定要有value和method两个值	
 * @author ruan
 */
public class ControllerProcessor extends AnnotationProcessor {
	protected void resolve(Object object, Class<?> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			// 只对有使用@RequestMapping注解的方法有限制
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if (!RequestMapping.class.equals(requestMapping.annotationType())) {
				continue;
			}
			if (requestMapping.value().length <= 0) {
				throw new URLNotDefineException("方法 " + method + " URL未定义");
			}
			if (requestMapping.method().length <= 0) {
				throw new HttpRequestMethodNotDefineException("方法 " + method + " method未定义");
			}
		}
	}

	protected void resolve(Object object, Method method) {
		throw new RuntimeException();
	}

}