// 短信模板

var smsTemplate = function (templateId) {
	return {
		templateId: templateId
	}
};

module.exports = {
	/**
	 * 短信验证码(15321)<br>
	 * 【共做室APP】验证码{1}，{2}分钟输入有效，脑洞大开，创意头脑风暴嗨起来哟！
	 */
	SmsVerifyCode: smsTemplate("15321"),
	/**
	 * 企业发布专案通知后台审核(16152)<br>
	 * 【共做室】{1}公司已经发布了专案{2}，请尽快审核！
	 */
	ApproveCase: smsTemplate("16152"),
	/**
	 * 服务器发生错误(16149)<br>
	 * 【共做室】服务器发生错误，{1}
	 */
	ServerError: smsTemplate("16149"),
	/**
	 * 品牌注册成功提示(16150)<br>
	 * 【共做室】品牌“{1}”注册成功！
	 */
	CompanyRegisterSuccess: smsTemplate("16150"),
	/**
	 * 重置密码短信提示(16151)<br>
	 * 【共做室】您正在重置密码，验证码是{1}，{2}分钟输入有效！
	 */
	ResetPassword: smsTemplate("16151"),
	/**
	 * 设置支付密码短信提示(18693)<br>
	 * 【共做室】您正在设置支付密码，验证码是({1})，{2}分钟输入有效！
	 */
	SetPaymentPassword: smsTemplate("18693"),
	/**
	 * 企业充值成功(19366)<br>
	 * 【共做室】你已成功充值 {1} 元，获得共做室平台的{2}，请登录共做室官网查看具体详情，发布专案征集创意！
	 */
	ChargeSuccess: smsTemplate("19366"),
	/**
	 * 推荐点子提醒(19433)<br>
	 * 【共做室】{1} 品牌发布的专案 {2} 还有 {3} 小时结束，请尽快登录后台推荐点子！
	 */
	IdeaIntroduce: smsTemplate("19433"),
	/**
	 * 品牌管理验证(19435)<br>
	 * 【共做室】您正在设置品牌管理员手机，验证码是：{1}，{2} 分钟内输入有效！
	 */
	CompanyVerifyCode: smsTemplate("19435"),
	/**
	 * 余额不足提醒(19489)<br>
	 * 【共做室】微信商户号余额只有 {1} 元，请尽快充值！
	 */
	MchiBalanceNotEnough: smsTemplate("19489"),
	/**
	 * 企业充值提醒(19706)<br>
	 * 【共做室】{1} 公司充值了 {2} 元，合计 {3} G币。
	 */
	CompanyChargeTips: smsTemplate("19706"),
	/**
	 * 企业充值成功2(21844)<br>
	 * 【共做室】您已成功充值{1}，在{2}前，尊享{3}次发布项目机会。请在有效期内登录共做室官网(gatherup.cc)发布项目征集创意。
	 */
	ChargeSuccess2: smsTemplate("21844"),
	/**
	 * 充值成功提醒(21847)<br>
	 * 【共做室】{1} 公司充值 {2} 元，套餐： {3}，到期时间：{4}，剩下专案次数：{5}。
	 */
	CompanyChargeTips2: smsTemplate("21847"),
	/**
	 * 企业充值失败(21849)<br>
	 * 【共做室】你充值的套餐已下架，充值不成功！管理员确认后，充值的金额将原路返回。如有疑问，请联系管理员，电话：{1}。
	 */
	ChargeFail: smsTemplate("21849"),
	/**
	 * 充值失败提醒(21848)<br>
	 * 【共做室】{1} 公司充值的套餐不存在，套餐ID：{2}，充值金额：{3}。请尽快确认，并把金额原路返回。
	 */
	CompanyChargeTips3: smsTemplate("21848")
};