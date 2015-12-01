package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

/**
 * 返回用户详细信息
 * @author luo
 */
public class UserInfoResponse extends SResponse {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 用户昵称
	 */
	private String nickname = "";
	/**
	 * 用户头像
	 */
	private String avatarImage = "";
	/**
	 * 性别(男1女2)
	 */
	private int sex;
	/**
	 * 省份
	 */
	private int provinceId = 0;
	/**
	 * 城市
	 */
	private int cityId = 0;
	/**
	 * 个性签名
	 */
	private String signature = "";
	/**
	 * 等级
	 */
	private int level;
	/**
	 * 出生年月日
	 */
	private int birthday;
	/**
	 * 一句话亮身份
	 */
	private String identity;
	/**
	 * 参与专案数
	 */
	private int takePartInCaseNum;
	/**
	 * 关注企业
	 */
	private int fosusCompanyNum;
	/**
	 * 关注的人
	 */
	private int fosusUserNum;
	/**
	 * 正关注你 
	 */
	private int fansNum;
	/**
	 * 积分
	 */
	private int score;
	/**
	 * G点
	 */
	private int points;
	/**
	 * 是否已经关注ta(1-是 0-否)
	 */
	private Integer isFocus;
	/**
	 * 本次登录时间
	 */
	private Integer thisLoginTime;
	/**
	 * 是否已经绑定微信
	 */
	private Integer isBindWechat;
	/**
	 * 用户设置
	 */
	private UserSettingResponse userSetting;
	/**
	 * 是否实名认证
	 */
	private Integer isProve;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTakePartInCaseNum() {
		return takePartInCaseNum;
	}

	public void setTakePartInCaseNum(int takePartInCaseNum) {
		this.takePartInCaseNum = takePartInCaseNum;
	}

	public int getFosusCompanyNum() {
		return fosusCompanyNum;
	}

	public void setFosusCompanyNum(int fosusCompanyNum) {
		this.fosusCompanyNum = fosusCompanyNum;
	}

	public int getFosusUserNum() {
		return fosusUserNum;
	}

	public void setFosusUserNum(int fosusUserNum) {
		this.fosusUserNum = fosusUserNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	public int getBirthday() {
		return birthday;
	}

	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Integer getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(int isFocus) {
		this.isFocus = isFocus;
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

	public UserSettingResponse getUserSetting() {
		return userSetting;
	}

	public void setUserSetting(UserSettingResponse userSetting) {
		this.userSetting = userSetting;
	}

	public Integer getThisLoginTime() {
		return thisLoginTime;
	}

	public void setThisLoginTime(int thisLoginTime) {
		this.thisLoginTime = thisLoginTime;
	}

	public Integer getIsBindWechat() {
		return isBindWechat;
	}

	public void setIsBindWechat(Integer isBindWechat) {
		this.isBindWechat = isBindWechat;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Integer getIsProve() {
		return isProve;
	}

	public void setIsProve(int isProve) {
		this.isProve = isProve;
	}
}