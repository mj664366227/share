package com.exam.core.converter;

import java.util.Map.Entry;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.exam.core.interfaces.AbstractConverter;
import com.exam.core.util.JSONObject;
import com.exam.core.util.StringUtil;

/**
 * json参数转换器
 * @author ruan
 *
 */
public class JSONConverter extends AbstractConverter {
	public JSONObject resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		mavContainer.setRequestHandled(true);
		JSONObject json = new JSONObject();
		for (Entry<String, String[]> e : webRequest.getParameterMap().entrySet()) {
			String[] arr = e.getValue();
			if (arr.length <= 0) {
				continue;
			}
			String key = e.getKey().trim();
			String value = arr[0].trim();
			if (key.isEmpty() || value.isEmpty()) {
				continue;
			}

			json.put(key, StringUtil.urlDecode(value));
		}

		json.put("ip", StringUtil.getString(webRequest.getHeader("X-Real-IP")));

		return json;
	}
}