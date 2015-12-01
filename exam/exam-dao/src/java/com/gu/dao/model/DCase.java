package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;
import com.gu.core.protocol.CompanyCase;

/**
 * case表
 * @author ruan
 */
@Pojo
@Message
public class DCase extends DSuper {
	/**
	 * 专案id
	 */
	private long id;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 专案名称
	 */
	private String name = "";
	/**
	 * 专案描述
	 */
	private String description = "";
	/**
	 * 专案类型
	 */
	private String type = "";
	/**
	 * 发布时间
	 */
	private int createTime;
	/**
	 * 专案结束时间
	 */
	private int endTime;
	/**
	 * 专案金额
	 */
	private int points;
	/**
	 * 专案图片1
	 */
	private String image1 = "";
	/**
	 * 专案图片2
	 */
	private String image2 = "";

	/**
	 * 专案图片3
	 */
	private String image3 = "";
	/**
	 * 专案图片4
	 */
	private String image4 = "";
	/**
	 * 专案图片5
	 */
	private String image5 = "";
	/**
	 * 参与人数
	 */
	private int takePartInNum;
	/**
	 * 粉丝数
	 */
	private int fansNum;
	/**
	 * 创意数
	 */
	private int ideaNum;
	/**
	 * 吐槽数
	 */
	private int flowNum;
	/**
	 * 点赞数
	 */
	private int praiseNum;
	/**
	 * 视频地址
	 */
	private String video = "";
	/**
	 * 是否显示(1-是 0-否，默认1)
	 */
	private int isShow = 0;
	/**
	 * 征集类型
	 */
	private int style;
	/**
	 * 是否审核通过(1-是 0-否)
	 */
	private int isPass = 0;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getTakePartInNum() {
		return takePartInNum;
	}

	public void setTakePartInNum(int takePartInNum) {
		this.takePartInNum = takePartInNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
		this.fansNum = this.fansNum < 0 ? 0 : this.fansNum;
	}

	public int getIdeaNum() {
		return ideaNum;
	}

	public void setIdeaNum(int ideaNum) {
		this.ideaNum = ideaNum;
	}

	public int getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(int flowNum) {
		this.flowNum = flowNum;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getIsPass() {
		return isPass;
	}

	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}

	/**
	 * 转成CompanyCase对象
	 * @author ruan 
	 * @param now 当前时间
	 * @param fansNum 
	 */
	public CompanyCase toCompanyCase(int now, int fansNum, int takePartInNum) {
		CompanyCase companyCase = new CompanyCase();
		companyCase.setCaseId(this.getId());
		companyCase.setImage(this.getImage1());
		companyCase.setName(this.getName());
		companyCase.setIsOver(this.getEndTime() < now ? 1 : 0);
		companyCase.setPoints(this.getPoints());
		companyCase.setCreateTime(this.getCreateTime());
		companyCase.setEndTime(this.getEndTime());
		companyCase.setTakePartInNum(takePartInNum);
		companyCase.setFansNum(fansNum);
		companyCase.setIsPass(getIsPass());
		return companyCase;
	}
}