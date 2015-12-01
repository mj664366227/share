package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

/**
 * 专案搜索返回协议单体
 * @author luo
 */
public class CaseSearchResponse extends SResponse {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 专案名称
	 */
	private String caseName;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 企业logo
	 */
	private String companyLogo;

	/**
	 * TODO 兼容1.0
	 * 专案名称 
	 */
	private String name;
	/**
	 * TODO 兼容1.0
	 * 企业logo
	 */
	private String logoImage;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
		//TODO 兼容1.0
		this.name = caseName;
	}

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
		//TODO 兼容1.0
		this.logoImage = companyLogo;
	}

	public String getName() {
		return name;
	}

	public String getLogoImage() {
		return logoImage;
	}

}
