package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 企业所有专案的标签
 * @author ruan
 */
@Pojo
@Message
public class DCompanyCaseTag extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 标签id(自定义的+100000000)
	 */
	private long typeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getTypeId() {
		return typeId;
	}

	/**
	 * 这个方法给反射用，我们不要调用
	 * @param typeId
	 */
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
}