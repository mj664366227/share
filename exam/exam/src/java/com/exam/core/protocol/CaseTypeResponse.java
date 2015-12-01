package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

/**
 * 专案类型
 * @author ruan
 */
public class CaseTypeResponse extends SResponse {
	/**
	 * 类型id
	 */
	private long id;
	/**
	 * 类型名字
	 */
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
