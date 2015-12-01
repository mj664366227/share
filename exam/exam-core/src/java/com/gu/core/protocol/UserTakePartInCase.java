package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

public class UserTakePartInCase extends SResponse {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 发布企业头像
	 */
	private String companyImage = "";
	/**
	 * 专案名称
	 */
	private String name = "";
	/**
	 * 是否已经结束
	 */
	private int isOver;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getCompanyImage() {
		return companyImage;
	}

	public void setCompanyImage(String companyImage) {
		this.companyImage = companyImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsOver() {
		return isOver;
	}

	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
}