// 短信模块
let LOGGER = logUtil.getLogger(module.filename);

// api服务器地址
const host = "http://www.ucpaas.com/maap/sms/code";
// 云之讯帐号id
const sid = "bf2b90d891751bd9508c9f903d9dd0a9";
// 云之讯appId
const appId = "3f8394122219498eb41ad51b69e4bbb5";
// 账户授权令牌
const authToken = "16f1e8fecdf1af9f37b7fda64375575e";

module.exports = {
	/**
	 * 发送短信
	 * @param smsTemplate 短信模板
	 * @param mobile 接收手机号码
	 * @param param 参数(多参数用","分割)
	 */
	sendSMS: function (smsTemplate, mobile, ...param) {
		var now = timeUtil.date("YYYYMMDDHHmmssSSS");
		var data = {
			sid: sid,
			appId: appId,
			time: now,
			to: mobile,
			templateId: smsTemplate.templateId,
			sign: secret.md5(sid + now + authToken),
			param: param
		};
		if (param.length == 1) {
			data.param = param[0];
		} else {
			data.param = _.join(param, ',');
		}
		httpClient.post(host, data);
	},
	/**
	 * 发送验证码短信
	 * @param mobile 手机号码
	 */
	sendVerificationCodeSMS: function (mobile) {
		// 生成验证码
		let second = 600;
		let verificationCode = random.rand(100000, 999999);
		redis.STRINGS.setex(KeyFactory.verificationCodeKey(mobile), second, verificationCode);
		LOGGER.warn('send verification code SMS to %s, code: %s', mobile, verificationCode);

		// 限制，60秒后才可以重新申请
		redis.STRINGS.setex(KeyFactory.verificationCodeSendKey(mobile), 60, "1");

		// 发送短信
		this.sendSMS(smsTemplate.SmsVerifyCode, mobile, verificationCode, parseInt(second / 60));

		// 每天发送数量+1
		let verificationPerDayKey = KeyFactory.verificationPerDayKey(mobile);
		redis.STRINGS.incrby(verificationPerDayKey, 1);
		redis.KEYS.expire(verificationPerDayKey, 86400);
		return verificationCode;
	},
	/**
	 * 判断是否可以发送短信
	 * @param mobile 手机号
	 */
	canSendVerificationCode: async function (mobile) {
		//1.是否已经超过每天10条限制
		let todaySMSNum = parseInt(await redis.STRINGS.get(KeyFactory.verificationPerDayKey(mobile)));
		if (todaySMSNum >= 10) {
			return false;
		}

		//2.是否已经超过60秒限制
		return await redis.STRINGS.get(KeyFactory.verificationCodeSendKey(mobile)) == null;
	},
	/**
	 * 检测验证码是否正确
	 * @param mobile 手机号
	 * @param veriCode 待检查的验证码
	 * @return number -1:验证码超时，0:验证码不正确，1:验证码正确
	 */
	checkVerificationCode: async function (mobile, veriCode) {
		let key = KeyFactory.verificationCodeKey(mobile);
		let verificationCode = parseInt(await redis.STRINGS.get(key));
		if (verificationCode <= 0) {
			LOGGER.warn("veriCode timeout, mobile: %s", mobile);
			return -1;
		}

		if (veriCode != verificationCode) {
			LOGGER.warn("veriCode error, user input: %s, gu server: %s", veriCode, verificationCode);
			return 0;
		}

		// 验证正确了，就删除
		redis.KEYS.del(key);
		return 1;
	}
};