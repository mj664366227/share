package com.exam.dao.model;

import com.exam.core.annotation.Pojo;
import com.exam.core.interfaces.DSuper;

/**
 * 管理员
 * @author ruan
 */
@Pojo
public class DAdmin extends DSuper {
	/**
	 * 管理员id
	 */
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
