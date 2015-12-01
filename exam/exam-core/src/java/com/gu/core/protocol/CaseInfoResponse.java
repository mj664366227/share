package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

/**
 * 专案简介返回协议
 * @author ruan
 */
public class CaseInfoResponse extends SResponse {
	/**
	 * 专案描述
	 */
	private String description;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 专案标题
	 */
	private String title;
	/**
	 * 企业logo
	 */
	private String companyLogo;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 是否已关注(1-是 0-否)
	 */
	private int isFocus;
	/**
	 * 专案金额
	 */
	private int points;
	/**
	 * 剩余时间
	 */
	private int leftTime;
	/**
	 * 当前用户是否发表过创意
	 */
	private int isIdeaPub;
	/**
	 * 专案图片列表
	 */
	private List<String> imageList = new ArrayList<String>();
	/**
	 * TODO 兼容1.0
	 * 专案图片列表
	 */
	private List<String> imagesList = imageList;
	/**
	 * 专案标签列表
	 */
	private List<CaseTypeResponse> tagList = new ArrayList<CaseTypeResponse>();

	public List<String> getImageList() {
		return imageList;
	}

	public void addImageList(String image) {
		imageList.add(image);
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public int getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(int isFocus) {
		this.isFocus = isFocus;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}

	public List<CaseTypeResponse> getTagList() {
		return tagList;
	}

	public void setTagList(List<CaseTypeResponse> tagList) {
		this.tagList = tagList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsIdeaPub() {
		return isIdeaPub;
	}

	public void setIsIdeaPub(int isIdeaPub) {
		this.isIdeaPub = isIdeaPub;
	}
}