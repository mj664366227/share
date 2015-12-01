package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

/**
 * 企业详情
 * @author ruan
 */
public class CompanyInfoResponse extends SResponse {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 企业品牌
	 */
	private String companyName;
	/**
	 * 企业logo
	 */
	private String companyLogo;
	/**
	 * 企业海报
	 */
	private String companyPic;
	/**
	 * 企业介绍
	 */
	private String description;
	/**
	 * 一句话介绍企业
	 */
	private String introduce;
	/**
	 * 我是否已关注
	 */
	private int isFocus;
	/**
	 * 专案数
	 */
	private int caseNum;
	/**
	 * 粉丝数
	 */
	private int fansNum;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getCompanyPic() {
		return companyPic;
	}

	public void setCompanyPic(String companyPic) {
		this.companyPic = companyPic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(int isFocus) {
		this.isFocus = isFocus;
	}

	public int getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(int caseNum) {
		this.caseNum = caseNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}