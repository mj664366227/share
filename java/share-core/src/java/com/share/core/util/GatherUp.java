package com.share.core.util;

import com.google.common.base.Joiner;

/**
 * gu项目专用方法
 * @author ruan
 */
public final class GatherUp {
	/**
	 * 创建时间格式化
	 */
	public final static String createTimeFormat(int time) {
		int restTme = Time.now() - time;
		if (restTme <= 60) {
			return "刚刚";
		} else if (restTme < 3600) {
			return (restTme / 60) + "分钟前";
		} else if (restTme < 86400) {
			return (restTme / 3600) + "小时前";
		} else if (restTme < 86400 * 7) {
			return (restTme / 86400) + "天前";
		} else {
			return Time.date(time);
		}
	}

	public final static String timeFormat(int time) {
		return Time.date(time);
	}

	/**
	 * 分页大小限制
	 * @param pageSize
	 */
	public final static int pageSizeLimit(int pageSize) {
		pageSize = pageSize < 0 ? 0 : pageSize;
		pageSize = pageSize > 20 ? 20 : pageSize;
		return pageSize;
	}

	/**
	 * 页码控制
	 * @param page
	 */
	public final static int page(int page) {
		return page <= 0 ? 1 : page;
	}

	/**
	 * 剪切url，并拼接参数
	 * @param url
	 * @param objects
	 */
	public final static String cutURL(String url, Object... objects) {
		return url.substring(0, url.indexOf("/{") + 1) + Joiner.on("/").join(objects);
	}

	/**
	 * 将一组key相近的value，尽量排在前面
	 * @author ruan 
	 * @param json json对象
	 * @param prefix 相似key的前缀
	 * @param max 最大序号
	 */
	public final static void inFront(JSONObject json, String prefix, int max) {
		for (int i = 1; i <= max; i++) {
			String key = prefix + i;
			if (json.getString(key).isEmpty()) {
				for (int j = i + 1; j <= max; j++) {
					String subKey = prefix + j;
					String subValue = json.getString(subKey);
					if (!subValue.isEmpty()) {
						json.remove(subKey);
						json.put(key, subValue);
						break;
					}
				}
			}
		}
	}

	/**
	 * 数字格式化
	 * @author ruan 
	 * @param number
	 */
	public final static String guNumberFormat(int number) {
		if (number > 10000) {
			return (number / 1000) + " K";
		}
		return String.valueOf(number);
	}

	public final static int ceil(double number) {
		return (int) Math.ceil(number);
	}
}