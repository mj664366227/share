package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

public class CompanyCase extends SResponse {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 专案封面
	 */
	private String image;
	/**
	 * 专案名称
	 */
	private String name;
	/**
	 * 专案金额(为什么是对象？因为如果是对象的话，不赋值，转json的时候就没有这个字段了)
	 */
	private Integer points;
	/**
	 * 参与人数(为什么是对象？因为如果是对象的话，不赋值，转json的时候就没有这个字段了)
	 */
	private Integer takePartInNum;
	/**
	 * 专案发布时间(为什么是对象？因为如果是对象的话，不赋值，转json的时候就没有这个字段了)
	 */
	private Integer createTime;
	/**
	 * 结束时间
	 */
	private Integer endTime;
	/**
	 * 粉丝数
	 */
	private Integer fansNum;
	/**
	 * 是否已经结束
	 */
	private int isOver;
	/**
	 * 是否通过审核
	 */
	private Integer isPass;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Integer getTakePartInNum() {
		return takePartInNum;
	}

	public void setTakePartInNum(int takePartInNum) {
		this.takePartInNum = takePartInNum;
	}

	public int getIsOver() {
		return isOver;
	}

	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public Integer getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}
}
