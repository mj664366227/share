package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 用户
 * @author ruan
 */
@Pojo
@Message
public class DUser extends DSuper {
	/**
	 * 用户id
	 */
	private long id;
	/**
	 * 用户昵称
	 */
	private String nickname = "";
	/**
	 * 密码
	 */
	private String password = "";
	/**
	 * 手机号
	 */
	private String mobile = "";
	/**
	 * QQ
	 */
	private String qq = "";
	/**
	 * 微信
	 */
	private String wechat = "";
	/**
	 * 微博
	 */
	private String weibo = "";
	/**
	 * 性别(男1女2)
	 */
	private int sex = 2;
	/**
	 * 省份ID
	 */
	private int provinceId = 0;
	/**
	 * 城市ID
	 */
	private int cityId = 0;
	/**
	 * 头像
	 */
	private String avatarImage = "";
	/**
	 * 个性签名
	 */
	private String signature = "";
	/**
	 * 出生年月日
	 */
	private int birthday;
	/**
	 * 一句话亮身份
	 */
	private String identity = "";
	/**
	 * 点数
	 */
	private int points;
	/**
	 * 等级
	 */
	private int level;
	/**
	 * 经验
	 */
	private int exp;
	/**
	 * 积分
	 */
	private int score;
	/**
	 * 注册时间
	 */
	private int createTime;
	/**
	 * 是否实名认证
	 */
	private int isProve;
	/**
	 * 上次登录时间
	 */
	private int lastLoginTime = 0;
	/**
	 * 平台
	 */
	private int platform = 0;
	/**
	 * 推送标识
	 */
	private String pushKey = "";
	/**
	 * 支付密码
	 */
	private String paymentPassword = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
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

	public String getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getIsProve() {
		return isProve;
	}

	public void setIsProve(int isProve) {
		this.isProve = isProve;
	}

	public int getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public String getPushKey() {
		return pushKey;
	}

	public void setPushKey(String pushKey) {
		if (!pushKey.isEmpty()) {
			this.pushKey = pushKey;
		}
	}

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}
}
