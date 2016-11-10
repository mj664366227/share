package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * 用户表
 */
@Pojo
public class DUser extends DSuper {
	/**
	 * 绑定企业id
	 */
	private long companyId;
	/**
	 * 用户名称
	 */
	private String nickname;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 支付密码
	 */
	private String paymentPassword;
	/**
	 * 最后登录时间
	 */
	private int lastLoginTime;
	/**
	 * 用户状态(0-正常 1-封号)
	 */
	private byte status;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * QQ号码
	 */
	private String qq;
	/**
	 * 微信
	 */
	private String wechat;
	/**
	 * 微博
	 */
	private String weibo;
	/**
	 * 平台：1-安卓，2-IOS，3-IPAD
	 */
	private int platform;
	/**
	 * 推送标识
	 */
	private String pushKey;
	/**
	 * 设备号
	 */
	private String udid;
	/**
	 * 性别
	 */
	private byte sex;
	/**
	 * 省份ID
	 */
	private String provinceId;
	/**
	 * 城市ID
	 */
	private String cityId;
	/**
	 * 头像地址
	 */
	private String avatarImage;
	/**
	 * 个性签名
	 */
	private String signature;
	/**
	 * 出生年月日
	 */
	private int birthday;
	/**
	 * 一句话亮身份
	 */
	private String identity;
	/**
	 * 职业
	 */
	private String job;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 机构
	 */
	private String agency;
	/**
	 * 特长
	 */
	private String specialty;
	/**
	 * 优惠码
	 */
	private String code;
	/**
	 * 发布专案数
	 */
	private int caseNum;
	/**
	 * 关注领域数
	 */
	private int tagNum;
	/**
	 * 点数(金额)
	 */
	private int points;
	/**
	 * 等级
	 */
	private String level;
	/**
	 * 经验
	 */
	private double exp;
	/**
	 * 积分
	 */
	private int score;
	/**
	 * 是否实名认证
	 */
	private byte isProve;
	/**
	 * 创意人标识
	 */
	private int creativeMan;
	/**
	 * 参与项目数
	 */
	private int joinNum;
	/**
	 * 好友数
	 */
	private int friendsNum;
	/**
	 * 地区
	 */
	private int region;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 * 获取绑定企业id
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置绑定企业id
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 获取用户名称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置用户名称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取登录密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取支付密码
	 */
	public String getPaymentPassword() {
		return paymentPassword;
	}

	/**
	 * 设置支付密码
	 */
	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	/**
	 * 获取最后登录时间
	 */
	public int getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 设置最后登录时间
	 */
	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 获取用户状态(0-正常 1-封号)
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * 设置用户状态(0-正常 1-封号)
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * 获取手机号码
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取QQ号码
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * 设置QQ号码
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 获取微信
	 */
	public String getWechat() {
		return wechat;
	}

	/**
	 * 设置微信
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	/**
	 * 获取微博
	 */
	public String getWeibo() {
		return weibo;
	}

	/**
	 * 设置微博
	 */
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	/**
	 * 获取平台：1-安卓，2-IOS，3-IPAD
	 */
	public int getPlatform() {
		return platform;
	}

	/**
	 * 设置平台：1-安卓，2-IOS，3-IPAD
	 */
	public void setPlatform(int platform) {
		this.platform = platform;
	}

	/**
	 * 获取推送标识
	 */
	public String getPushKey() {
		return pushKey;
	}

	/**
	 * 设置推送标识
	 */
	public void setPushKey(String pushKey) {
		this.pushKey = pushKey;
	}

	/**
	 * 获取设备号
	 */
	public String getUdid() {
		return udid;
	}

	/**
	 * 设置设备号
	 */
	public void setUdid(String udid) {
		this.udid = udid;
	}

	/**
	 * 获取性别
	 */
	public byte getSex() {
		return sex;
	}

	/**
	 * 设置性别
	 */
	public void setSex(byte sex) {
		this.sex = sex;
	}

	/**
	 * 获取省份ID
	 */
	public String getProvinceId() {
		return provinceId;
	}

	/**
	 * 设置省份ID
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 获取城市ID
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * 设置城市ID
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取头像地址
	 */
	public String getAvatarImage() {
		return avatarImage;
	}

	/**
	 * 设置头像地址
	 */
	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	/**
	 * 获取个性签名
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 设置个性签名
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 获取出生年月日
	 */
	public int getBirthday() {
		return birthday;
	}

	/**
	 * 设置出生年月日
	 */
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取一句话亮身份
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * 设置一句话亮身份
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * 获取职业
	 */
	public String getJob() {
		return job;
	}

	/**
	 * 设置职业
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * 获取邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取真实姓名
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * 设置真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 获取机构
	 */
	public String getAgency() {
		return agency;
	}

	/**
	 * 设置机构
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}

	/**
	 * 获取特长
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * 设置特长
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * 获取优惠码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置优惠码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取发布专案数
	 */
	public int getCaseNum() {
		return caseNum;
	}

	/**
	 * 设置发布专案数
	 */
	public void setCaseNum(int caseNum) {
		this.caseNum = caseNum;
	}

	/**
	 * 获取关注领域数
	 */
	public int getTagNum() {
		return tagNum;
	}

	/**
	 * 设置关注领域数
	 */
	public void setTagNum(int tagNum) {
		this.tagNum = tagNum;
	}

	/**
	 * 获取点数(金额)
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 设置点数(金额)
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * 获取等级
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 设置等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 获取经验
	 */
	public double getExp() {
		return exp;
	}

	/**
	 * 设置经验
	 */
	public void setExp(double exp) {
		this.exp = exp;
	}

	/**
	 * 获取积分
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 设置积分
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * 获取是否实名认证
	 */
	public byte getIsProve() {
		return isProve;
	}

	/**
	 * 设置是否实名认证
	 */
	public void setIsProve(byte isProve) {
		this.isProve = isProve;
	}

	/**
	 * 获取创意人标识
	 */
	public int getCreativeMan() {
		return creativeMan;
	}

	/**
	 * 设置创意人标识
	 */
	public void setCreativeMan(int creativeMan) {
		this.creativeMan = creativeMan;
	}

	/**
	 * 获取参与项目数
	 */
	public int getJoinNum() {
		return joinNum;
	}

	/**
	 * 设置参与项目数
	 */
	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	/**
	 * 获取好友数
	 */
	public int getFriendsNum() {
		return friendsNum;
	}

	/**
	 * 设置好友数
	 */
	public void setFriendsNum(int friendsNum) {
		this.friendsNum = friendsNum;
	}

	/**
	 * 获取地区
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * 设置地区
	 */
	public void setRegion(int region) {
		this.region = region;
	}

	/**
	 * 获取经度
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * 设置经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取纬度
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * 设置纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


}