package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

public class UserFocusCaseResponse extends SResponse {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 企业logo
	 */
	private String companyLogo = "";
	/**
	 * 专案标题
	 */
	private String caseTitle = "";
	/**
	 * 专案标签列表
	 */
	private List<CaseTypeResponse> tag = new ArrayList<CaseTypeResponse>();
	/**
	 * 剩余时间
	 */
	private int leftTime;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public List<CaseTypeResponse> getTag() {
		return tag;
	}

	public void setTag(List<CaseTypeResponse> tag) {
		this.tag = tag;
	}

	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
