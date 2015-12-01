package com.gu.core.interfaces;

import com.gu.core.util.JSONObject;

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

	/**
	 * toJSON方法
	 */
	public JSONObject toJSON() {
		return JSONObject.decode(JSONObject.encode(this));
	}
}