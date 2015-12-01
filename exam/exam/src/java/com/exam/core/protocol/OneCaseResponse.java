package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

/**
 * 专案结构体
 * @author ruan
 */
public class OneCaseResponse extends SResponse {
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 企业名称
	 */
	private String companyName;
	/**
	 * 专案id	
	 */
	private long caseId;
	/**
	 * 专案封面图片
	 */
	private String image;
	/**
	 * 企业头像
	 */
	private String companyLogo;
	/**
	 * 专案金额
	 */
	private int points;
	/**
	 * 专案名称
	 */
	private String name;
	/**
	 * 当前用户是否已关注
	 */
	private int isFocus;
	/**
	 * 是否已经结束
	 */
	private int isOver;
	/**
	 * 当前用户是否发表过创意
	 */
	private int isIdeaPub;
	/**
	 * 专案标签列表
	 */
	private List<CaseTypeResponse> tagList = new ArrayList<CaseTypeResponse>();
	/**
	 * 剩余时间
	 */
	private int leftTime;
	/**
	 * 用户关注专案id	
	 */
	private int focusCaseId;
	/**
	 * 索引值
	 */
	private int index;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getIsOver() {
		return isOver;
	}

	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}

	public int getIsIdeaPub() {
		return isIdeaPub;
	}

	public void setIsIdeaPub(int isIdeaPub) {
		this.isIdeaPub = isIdeaPub;
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

	public List<CaseTypeResponse> getTagList() {
		return tagList;
	}

	public void setTagList(List<CaseTypeResponse> tagList) {
		this.tagList = tagList;
	}

	public int getFocusCaseId() {
		return focusCaseId;
	}

	public void setFocusCaseId(int focusCaseId) {
		this.focusCaseId = focusCaseId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}