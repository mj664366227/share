package com.gu.core.protocol.base;

import com.gu.core.interfaces.SResponse;

/**
 * 企业搜索返回协议单体
 * @author ruan
 */
public class CompanyBaseResponse extends SResponse {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 企业名字
	 */
	private String name;
	/**
	 * 企业logo
	 */
	private String logo;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
