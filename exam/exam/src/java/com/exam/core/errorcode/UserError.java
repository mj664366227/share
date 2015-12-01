package com.exam.core.errorcode;

import com.exam.core.interfaces.Error;

public enum UserError implements Error {
	/**
	 * 错误的手机号码(2001001)
	 */
	mobileError(2001001, "错误的手机号码"),
	/**
	 * 您的操作太快啦	
	 */
	operatingTooFast(2001002, "您的操作太快啦"),
	/**
	 * 验证码超时(2001003)
	 */
	verifyCodeTimeout(2001003, "验证码超时"),
	/**
	 * 验证码错误(2001004)
	 */
	verifyCodeError(2001004, "验证码错误"),
	/**
	 * 验证码错误(2001005)
	 */
	sexError(2001005, "用户性别错误"),
	/**
	 * 错误的qq(2001006)
	 */
	qqError(2001006, "错误的qq"),
	/**
	 * 错误的微信(2001007)
	 */
	wechatError(2001007, "错误的微信"),
	/**
	 * 错误的微博(2001008)
	 */
	weiboError(2001008, "错误的微博"),
	/**
	 * 错误的openid(2001009)
	 */
	openidError(2001009, "错误的openid"),
	/**
	 * 错误的昵称(2001010)
	 */
	nicknameError(2001010, "错误的昵称"),
	/**
	 * 昵称已经被使用(2001011)
	 */
	nicknameOnlyError(2001011, "昵称已经被使用"),
	/**
	 * 用户不存在(2001012)
	 */
	userNotExists(2001012, "用户不存在"),
	/**
	 * 用户关注该企业(2001013)
	 */
	userHasFocusCpmpany(2001013, "用户已关注该企业"),
	/**
	 * 用户关注企业失败(2001014)
	 */
	userFocusCompanyError(2001014, "用户关注企业失败"),
	/**
	 * 关注用户失败(2001015)
	 */
	userFocusError(2001015, "关注用户失败"),
	/**
	 * 密码错误(2001016)
	 */
	passwordError(2001016, "密码错误"),
	/**
	 * 生日大于当前时间(2001017)
	 */
	birthdayError(2001017, "生日大于当前时间"),
	/**
	 * 手机已经被使用(2001018)
	 */
	mobileOnlyError(2001018, "手机已经被使用"),
	/**
	 * 错误的省份id(2001019)
	 */
	provinceIdError(2001019, "错误的省份id"),
	/**
	 * 错误的城市id(2001020)
	 */
	cityIdError(2001020, "错误的城市id"),
	/**
	 * 错误的一句话亮身份(2001021)
	 */
	identityError(2001021, "错误的一句话亮身份"),
	/**
	 * 您的帐号已在另外一台设备登录，请及时修改您的密码(2001022)
	 */
	otherLoginError(2001022, "您的帐号已在另外一台设备登录，请及时修改您的密码"),
	/**
	 * 用户未关注该企业(2001023)
	 */
	userHasNotFocusCpmpany(2001023, "用户未关注该企业"),
	/**
	 * 用户取消关注企业失败(2001024)
	 */
	userUnFocusCompanyError(2001024, "用户取消关注企业失败"),
	/**
	 * 微信号已经被绑定(2001025)
	 */
	wechatIsBindError(2001025, "微信号已经被绑定"),
	/**
	 * 微信未绑定(2001026)
	 */
	wechatNotBindError(2001026, "微信未绑定"),
	/**
	 * G点小于设定值无法提现(2001027)
	 */
	checkoutLimitError(2001027, "G点小于设定值无法提现"),
	/**
	 * 提现失败(2001028)
	 */
	checkoutError(2001028, "提现失败"),
	/**
	 * 你已经关注此用户(2001029)
	 */
	userHasFocusError(2001029, "你已经关注此用户"),
	/**
	* 用户不匹配(2001030)
	*/
	userNotEqual(2001030, "用户不匹配"),
	/**
	* 用户实名认证已通过(2001031)
	*/
	userIsProve(2001031, "用户实名认证已通过"),
	/**
	 * 你还没关注此用户(2001032)
	 */
	userHasNotFocusError(2001032, "你还没关注此用户"),
	/**
	 * 取消关注用户失败(2001033)
	 */
	userUnFocusError(2001033, "取消关注用户失败"),
	/**
	 * 请先通过实名认证(2001034)
	 */
	userNotIsProve(2001034, "请先通过实名认证"),
	/**
	 * 临时错误码(2001035)
	 */
	userTemporaryError(2001035, "很抱歉，您今天没有抢到卡位名额... 可以明天再试！ 或待全面更新2.0版本时再行注册～您没注册奖品是不会随便送出的喔！"),
	/**
	 * 支付密码累计输错5次(2001036)
	 */
	paymentPasswordErrorOver(2001036, "亲，今天的支付密码已经累计输错5次，请明天再试！"),
	/**
	 * 支付密码错误(2001037)
	 */
	paymentPasswordError(2001037, "支付密码错误"),
	/**
	 * 最少提现100G点(2001038)
	 */
	checkoutMinError(2001038, "最少提现100G点"),
	/**
	 * 支付密码未设置(2001039)
	 */
	paymentPasswordNotSetError(2001039, "支付密码未设置"),
	/**
	 * 重复关注别人会给对方造成骚扰(2001040)
	 */
	repeatFocusUserError(2001040, "重复关注别人会给对方造成骚扰！"),
	/**
	 * 最多提现2000000G点(2001041)
	 */
	checkoutMaxError(2001041, "最多提现2000000G点"),
	/**
	 * 剩余的G点不足(2001042)
	 */
	gpointsNotEnoughError(2001042, "剩余的G点不足");

	private int errorCode;
	private String errorMsg;

	private UserError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
