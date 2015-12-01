package com.gu.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gu.core.util.SortUtil.Order;

/**
 * 微信第三方接口工具
 * @author ruan
 */
public class WechatUtil {
	private final static Logger logger = LoggerFactory.getLogger(WechatUtil.class);

	/**
	 * 生成微信接口sign值
	 * @author ruan 
	 * @param data
	 */
	public final static String genWechatSign(Map<String, String> data) {
		StringBuilder str = new StringBuilder();
		List<String> keyList = SortUtil.sortMapKey(data, Order.ASC);
		for (String key : keyList) {
			str.append(key);
			str.append("=");
			str.append(StringUtil.getString(data.get(key)));
			str.append("&");
		}
		str.append("key=GongZuoShi2015InTitAndRoomIsCCIC");
		return Secret.md5(str.toString()).toUpperCase();
	}

	/**
	 * 把xml字符串转成map
	 * @author ruan 
	 * @param str
	 */
	@SuppressWarnings("unchecked")
	public final static Map<String, String> xml2map(String str) {
		Map<String, String> map = new HashMap<>();
		try {
			Document doc = DocumentHelper.parseText(str);
			List<Element> list = doc.getRootElement().elements();
			for (Element element : list) {
				map.put(StringUtil.getString(element.getName()), StringUtil.getString(element.getText()));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return map;
	}

	/**
	 * 把map转成xml字符串
	 * @author ruan 
	 * @param map
	 */
	public final static String map2xml(Map<String, String> map) {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		for (Entry<String, String> e : map.entrySet()) {
			String key = StringUtil.getString(e.getKey());
			String value = StringUtil.getString(e.getValue());
			if (key.isEmpty() || value.isEmpty()) {
				continue;
			}
			xml.append("<");
			xml.append(key);
			xml.append(">");
			xml.append(value);
			xml.append("</");
			xml.append(key);
			xml.append(">");
		}
		xml.append("</xml>");
		return xml.toString();
	}
}