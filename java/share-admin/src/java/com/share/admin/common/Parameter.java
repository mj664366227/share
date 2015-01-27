package com.share.admin.common;

import java.util.HashMap;
import java.util.Map;

import com.share.core.util.JSONObject;
import com.share.core.util.StringUtil;

/**
 * 参数对象
 */
public final class Parameter {
	/**
	 * 数据map
	 */
	private Map<String, Object> data = new HashMap<>();
	/**
	 * 是否可以put入数据
	 */
	private boolean canPut = true;

	/**
	 * 传入参数
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		if (!canPut) {
			return;
		}
		data.put(key, value);
	}

	/**
	 * getInt
	 * @param key
	 */
	public int getInt(String key) {
		return StringUtil.getInt(getString(key));
	}

	/**
	 * getLong
	 * @param key
	 */
	public long getLong(String key) {
		return StringUtil.getLong(getString(key));
	}

	/**
	 * getShort
	 * @param key
	 */
	public short getShort(String key) {
		return StringUtil.getShort(getString(key));
	}

	/**
	 * getByte
	 * @param key
	 */
	public byte getByte(String key) {
		return StringUtil.getByte(getString(key));
	}

	/**
	 * getString
	 * @param key
	 */
	public String getString(String key) {
		Object value = data.get(key);
		if (value == null) {
			return null;
		}
		return ((String[]) value)[0].trim();
	}

	/**
	 * getFloat
	 * @param key
	 */
	public float getFloat(String key) {
		return StringUtil.getFloat(getString(key));
	}

	/**
	 * getDouble
	 * @param key
	 */
	public double getDouble(String key) {
		return StringUtil.getDouble(getString(key));
	}

	/**
	 * getBoolean
	 * @param key
	 */
	public boolean getBoolean(String key) {
		return StringUtil.getBoolean(getString(key));
	}

	/**
	 * 获取数组
	 * @param key
	 */
	public String[] getArray(String key) {
		Object array = getString(key);
		if (array == null) {
			return null;
		}
		return (String[]) array;
	}

	/**
	 * 设置是否可传入参数	
	 * @param canPut
	 */
	public void setCanPut(boolean canPut) {
		this.canPut = canPut;
	}

	/**
	 * toString
	 */
	public String toString() {
		return JSONObject.encode(data);
	}
}