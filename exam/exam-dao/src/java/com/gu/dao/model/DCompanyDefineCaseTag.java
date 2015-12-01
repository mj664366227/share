package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 企业自定义标签
 * @author ruan
 */
@Pojo
@Message
public class DCompanyDefineCaseTag extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 标签名
	 */
	private String name = "";
	/**
	 * 有多少个专案使用这个标签
	 */
	private int caseNum;

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

	public int getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(int caseNum) {
		this.caseNum = caseNum;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}
