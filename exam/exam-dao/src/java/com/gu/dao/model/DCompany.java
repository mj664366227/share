package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 企业用户
 * @author luo
 */
@Pojo
@Message
public class DCompany extends DSuper {
	/**
	 * 企业id
	 */
	private long id;
	/**
	 * 企业名称
	 */
	private String name = "";
	/**
	 * 密码
	 */
	private String password = "";
	/**
	 * 企业简介(描述)
	 */
	private String description = "";
	/**
	 *企业领域(类型)
	 */
	private long typeId;
	/**
	 * 企业logo
	 */
	private String logoImage = "";
	/**
	 * 管理员姓名
	 */
	private String adminName = "";
	/**
	 * 管理员电话
	 */
	private String adminPhone = "";
	/**
	 * 管理员邮箱
	 */
	private String adminEmail = "";
	/**
	 * 注册时间
	 */
	private int createTime;
	/**
	 * 省份ID
	 */
	private int provinceId;
	/**
	 * 城市ID
	 */
	private int cityId;
	/**
	 * 点数(金额)
	 */
	private int points;
	/**
	 * 企业全称
	 */
	private String fullname = "";
	/**
	 * 是否已经通过企业邮箱认证
	 */
	private int isAuth = 0;
	/**
	 * 一句话介绍公司品牌
	 */
	private String introduce = "";
	/**
	 * 所有专案数量
	 */
	private int allCaseNum = 0;
	/**
	 * 进行专案数量
	 */
	private int goingCaseNum = 0;
	/**
	 * 完结专案数量
	 */
	private int overCaseNum = 0;
	/**
	 * 粉丝数量
	 */
	private int fansNum = 0;
	/**
	 * 企业图片
	 */
	private String pic = "";
	/**
	 * 专案收藏数
	 */
	private int collectNum = 0;
	/**
	 * 上次登录时间
	 */
	private int lastLoginTime = 0;
	/**
	 * 关注用户数
	 */
	private int focusUserNum = 0;
	/**
	 * 官网地址
	 */
	private String website = "";
	/**
	 * 微信公众号二维码地址
	 */
	private String wechatMpQr = "";

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getAllCaseNum() {
		return allCaseNum;
	}

	public void setAllCaseNum(int allCaseNum) {
		this.allCaseNum = allCaseNum;
	}

	public int getGoingCaseNum() {
		return goingCaseNum;
	}

	public void setGoingCaseNum(int goingCaseNum) {
		this.goingCaseNum = goingCaseNum;
		this.goingCaseNum = this.goingCaseNum < 0 ? 0 : this.goingCaseNum;
	}

	public int getOverCaseNum() {
		return overCaseNum;
	}

	public void setOverCaseNum(int overCaseNum) {
		this.overCaseNum = overCaseNum;
		this.goingCaseNum = this.goingCaseNum < 0 ? 0 : this.goingCaseNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
		this.fansNum = this.fansNum < 0 ? 0 : this.fansNum;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getFocusUserNum() {
		return focusUserNum;
	}

	public void setFocusUserNum(int focusUserNum) {
		this.focusUserNum = focusUserNum;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWechatMpQr() {
		return wechatMpQr;
	}

	public void setWechatMpQr(String wechatMpQr) {
		this.wechatMpQr = wechatMpQr;
	}
}
