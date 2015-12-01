package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

public class UserFocusCompany extends SResponse {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 企业logo
	 */
	private String companyLogo;
	/**
	 * 企业名称
	 */
	private String companyName;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}