package com.share.core.interfaces;

import com.share.core.util.JSONObject;

/**
 * 所有pojo对象的超类
 */
public abstract class DSuper {
	/**
	 * toString方法
	 */
	public String toString() {
		return JSONObject.encode(this);
	}
}