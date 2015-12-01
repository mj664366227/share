package com.gu.core.annotation.processor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gu.core.annotation.CDN;
import com.gu.core.exception.ErrorAnnotationException;
import com.gu.core.exception.HttpRequestMethodNotDefineException;
import com.gu.core.exception.URLNotDefineException;
import com.gu.core.general.VirtualCDNService;
import com.gu.core.util.Secret;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;

/**
 * 控制器注解解析器<br>
 * 用来限制有使用@RequestMapping注解的方法一定要有value和method两个值	
 * @author ruan
 */
public class ControllerProcessor extends AnnotationProcessor {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ControllerProcessor.class);
	/**
	 * cdn缓存时间map
	 */
	private static Map<String, Long> cdnCache = new ConcurrentHashMap<>();

	/**
	 * 私有构造函数，只能通过spring实例化
	 */
	private ControllerProcessor() {
	}

	protected void resolve(Object object, Class<?> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			// 只对有使用@RequestMapping注解的方法有限制
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			if (requestMapping == null) {
				continue;
			}
			if (!RequestMapping.class.equals(requestMapping.annotationType())) {
				continue;
			}
			if (requestMapping.value().length <= 0) {
				throw new URLNotDefineException("method  " + method + " not defined url");
			}

			RequestMethod[] requestMethod = requestMapping.method();
			if (requestMethod.length <= 0) {
				throw new HttpRequestMethodNotDefineException("method " + method + " not defined method");
			}

			// 如果使用了@RequestMapping注解的方法，且method是get的话，就一定要用@CDN注解；如果是post的话，就不可以用CDN缓存
			CDN cdn = method.getAnnotation(CDN.class);
			if (RequestMethod.POST.equals(requestMethod[0]) && cdn != null) {
				throw new ErrorAnnotationException("method " + method + " don't need cdn cache");
			}
			//if (RequestMethod.GET.equals(requestMethod[0]) && cdn == null) {
			//	throw new ErrorAnnotationException("method " + method + "  miss cdn cache");
			//}

			if (cdn != null) {
				String url = StringUtil.getString(requestMapping.value()[0]);
				logger.info("url: {} add cdn cache, time: {}", url, Time.showTime(cdn.unit().toNanos(cdn.value())));
				cdnCache.put(url, cdn.unit().toSeconds(cdn.value()));
			}
		}
	}

	protected void resolve(Object object, Method method) {
		throw new RuntimeException();
	}

	/**
	 * 获取cdn缓存数据
	 * @author ruan 
	 * @param request
	 */
	public Object getCdnCache(HttpServletRequest request) {
		return VirtualCDNService.get(getCDNCacheKey(request));
	}

	/**
	 * 获取cdn缓存时长
	 * @author ruan 
	 * @param url
	 */
	public final static long getCdnCacheDuration(String url) {
		return StringUtil.getLong(cdnCache.get(url));
	}

	/**
	 * 拼接cdn缓存key
	 * @author ruan 
	 * @param request
	 */
	public final static String getCDNCacheKey(HttpServletRequest request) {
		StringBuilder cdnKey = new StringBuilder(StringUtil.getString(request.getRequestURI()));
		for (Entry<String, String[]> e : request.getParameterMap().entrySet()) {
			String key = StringUtil.getString(e.getKey());
			if ("time".equals(key) || "sign".equals(key)) {
				continue;
			}
			cdnKey.append(key);
			cdnKey.append(StringUtil.urlDecode(e.getValue()[0]));
		}
		return Secret.md5(cdnKey.toString());
	}

}