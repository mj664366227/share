/**
 * 微信SDK
 */
import request from "request";
import util from "util";
const {sha1} = requireCommon('secret');
const RedisKey = think.config('redis_key');

// https证书配置(支付类接口用到)
const agentOptions = {
	// key: filesystem.isLinux() ? fs.readFileSync('/srv/etc/cert/pay/wechatSDK_key.pem') : fs.readFileSync(AppDir + '/../gu-core/src/resources/cert/pay/wechatSDK_key.pem'),
	// cert: filesystem.isLinux() ? fs.readFileSync('/srv/etc/cert/pay/wechatSDK_cert.pem') : fs.readFileSync(AppDir + '/../gu-core/src/resources/cert/pay/wechatSDK_cert.pem'),
	// passphrase: mchId
};

// jssdk api列表
const jsApiList = [
	"checkJsApi",
	"onMenuShareTimeline",
	"onMenuShareAppMessage",
	"onMenuShareQQ",
	"onMenuShareWeibo",
	"hideMenuItems",
	"showMenuItems",
	"hideAllNonBaseMenuItem",
	"showAllNonBaseMenuItem",
	"translateVoice",
	"startRecord",
	"stopRecord",
	"onRecordEnd",
	"playVoice",
	"pauseVoice",
	"stopVoice",
	"uploadVoice",
	"downloadVoice",
	"chooseImage",
	"previewImage",
	"uploadImage",
	"downloadImage",
	"getNetworkType",
	"openLocation",
	"getLocation",
	"hideOptionMenu",
	"showOptionMenu",
	"closeWindow",
	"scanQRCode",
	"chooseWXPay",
	"openProductSpecificView",
	"addCard",
	"chooseCard",
	"openCard"
];
export default  {
	/**
	 * 获取AccessToken
	 */
	getAccessToken: async function () {
		await getMzConfig();
		let accessTokenKey = RedisKey.wechat.accessTokenKey();
		let accessToken = await redis.get(accessTokenKey);
		if (accessToken) {
			return accessToken;
		}

		let data = {
			'grant_type': 'client_credential',
			'appid': MZ.mzConfig.weixin_mp.appid,
			'secret': MZ.mzConfig.weixin_mp.secret
		};
		let json = JSON.parse(await get('https://api.weixin.qq.com/cgi-bin/token', data, agentOptions));
		redis.setex(accessTokenKey, 6500, json.access_token);
		return json.access_token;
	},
	/**
	 * 获取jsapi ticket
	 * @param accessToken
	 */
	getTicket: async function (accessToken) {
		let ticketKey = RedisKey.wechat.ticketKey(accessToken);
		let ticket = await redis.get(ticketKey);
		if (ticket) {
			return ticket;
		}

		let data = {
			'access_token': accessToken,
			'type': 'jsapi'
		};
		let json = JSON.parse(await get('https://api.weixin.qq.com/cgi-bin/ticket/getticket', data, agentOptions));
		redis.setex(ticketKey, 6500, json.ticket);
		return json.ticket;
	},
	/**
	 * 生成微信分享数据
	 * @param url 要分享的网络地址
	 */
	genWechatShareData: async function (url) {
		let accessToken = await this.getAccessToken();
		let ticket = await this.getTicket(accessToken);

		// 计算sign值
		let nonceStr = think.uuid(30);
		let timestamp = parseInt(moment.now() / 1000);
		let signature = sha1(`jsapi_ticket=${ticket}&noncestr=${nonceStr}&timestamp=${timestamp}&url=${url}`);

		// 返回的数据
		return {
			"nonceStr": nonceStr,
			"timestamp": timestamp,
			"signature": signature,
			"appId": MZ.mzConfig.weixin_mp.appid,
			"link": url,
			"jsApiList": jsApiList
		};
	},
	/**
	 * 商户支付给用户
	 * @param openId 用户的第三方id
	 * @param orderId 订单id
	 * @param amount 支付金额(单位：分)
	 * @param ip 请求支付端的ip地址
	 */
	mchPay: async function (openId, orderId, amount, ip) {
		let param = {};
		param.mch_appid = appId;
		param.mchid = mchId;
		param.nonce_str = think.uuid();
		param.partner_trade_no = orderId;
		param.openid = openId;
		param.check_name = "NO_CHECK";
		param.amount = amount;
		param.desc = "xxxxxxxxxxx";
		param.spbill_create_ip = ip;
		param.sign = genWechatSign(param);
		let xml = await post("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", stringUtil.json2xml(param), loginAgentOptions);
		return stringUtil.xml2json(xml);
	},
	/**
	 * 微信二维码支付
	 * @param orderId 订单id
	 * @param description 支付描述(例如：G点充值)
	 * @param ip 支付机器的ip
	 * @param amount 要支付的金额(单位：分)
	 * @param attach 附加数据
	 * @param notifyUrl 回调地址
	 */
	payByWechatQRCode: async function (orderId, description, ip, amount, attach, notifyUrl) {
		let param = {};
		param.appid = appId;
		param.mch_id = mchId;
		param.nonce_str = think.uuid();
		param.body = description;
		param.out_trade_no = _.toString(orderId);
		param.total_fee = _.toString(amount);
		param.spbill_create_ip = ip;
		param.notify_url = notifyUrl;
		param.trade_type = "NATIVE";
		param.attach = attach;
		param.sign = genWechatSign(param);
		let xml = await post("https://api.mch.weixin.qq.com/pay/unifiedorder", stringUtil.json2xml(param), loginAgentOptions);
		return stringUtil.xml2json(xml);
	},
	/**
	 * 微信App支付
	 * @param orderId 订单id
	 * @param description 支付描述(例如：G点充值)
	 * @param ip 支付机器的ip
	 * @param amount 要支付的金额(单位：分)
	 * @param attach 附加数据
	 * @param notifyUrl 回调地址
	 */
	payByApp: async function (orderId, description, ip, amount, attach, notifyUrl) {
		let param = {};
		param.appid = appId;
		param.mch_id = mchId;
		param.nonce_str = think.uuid();
		param.body = description;
		param.out_trade_no = _.toString(orderId);
		param.total_fee = _.toString(amount);
		param.spbill_create_ip = ip;
		param.notify_url = notifyUrl;
		param.trade_type = "APP";
		param.attach = JSON.stringify(attach);
		param.sign = genWechatSign(param);
		let xml = await post("https://api.mch.weixin.qq.com/pay/unifiedorder", stringUtil.json2xml(param), payAgentOptions);
		let result = await stringUtil.xml2json(xml);
		if (result['result_code'] !== 'SUCCESS') {
			LOG.warn("pay by wechat app error! %s", JSON.stringify(result));
			return {};
		}
		LOG.warn(JSON.stringify(result));

		// 返回拉起微信支付app的数据
		let payData = {};
		payData.appid = appId;
		payData.partnerid = mchId;
		payData.prepayid = result.prepay_id;
		payData.package = 'Sign=WXPay';
		payData.noncestr = think.uuid();
		payData.timestamp = _.toString(moment.now());
		payData.sign = genWechatSign(payData);
		payData.returnData = result;
		return payData;
	},
	/**
	 * 微信jsapi支付
	 * @param orderId 订单id
	 * @param openId 第三方id
	 * @param description 支付描述(例如：G点充值)
	 * @param ip 支付机器的ip
	 * @param amount 要支付的金额(单位：分)
	 * @param attach 附加数据
	 * @param notifyUrl 回调地址
	 */
	payByJsapi: async function (orderId, openId, description, ip, amount, attach, notifyUrl) {
		let param = {};
		param.appid = appId;
		param.mch_id = mchId;
		param.nonce_str = think.uuid();
		param.body = description;
		param.out_trade_no = _.toString(orderId);
		param.total_fee = _.toString(amount);
		param.spbill_create_ip = ip;
		param.notify_url = notifyUrl;
		param.trade_type = "JSAPI";
		param.attach = JSON.stringify(attach);
		param.openid = openId;
		param.sign = genWechatSign(param);
		let xml = await post("https://api.mch.weixin.qq.com/pay/unifiedorder", stringUtil.json2xml(param), loginAgentOptions);
		let result = await stringUtil.xml2json(xml);
		if (result['result_code'] !== 'SUCCESS') {
			LOG.warn("pay by wechat app error! %s", JSON.stringify(result));
			return {};
		}
		LOG.warn(JSON.stringify(result));

		// 返回拉起微信支付jsapi的数据
		let payData = {};
		payData.appId = appId;
		payData.package = util.format('prepay_id=%s', result.prepay_id);
		payData.signType = 'MD5';
		payData.nonceStr = think.uuid();
		payData.timeStamp = _.toString(moment.now());
		payData.paySign = genWechatSign(payData);
		payData.returnData = result;
		return payData;
	},
	/**
	 * 通过code换取网页授权access_token
	 * @param code 用户授权后获得的code
	 */
	getAccessTokenByCode: async function (code) {
		await getMzConfig();
		let url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		let data = {
			'appid': MZ.mzConfig.weixin_mp.appid,
			'secret': MZ.mzConfig.weixin_mp.secret,
			"code": code,
			"grant_type": "authorization_code"
		};
		let json = await get(url, data, agentOptions);
		LOG.warn("get accessToken by code from wechat, code: %s, data: %s", code, json);
		return JSON.parse(json);
	},
	/**
	 * 用户授权页面
	 * @param callback 如果用户授权成功要回调页面url
	 * @param res res对象
	 */
	authorize: function (callback, res) {
		let scope = "snsapi_userinfo";
		let url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=1#wechat_redirect";
		url = util.format(url, appId, encodeURIComponent(callback), scope);
		LOG.warn("redirect to: %s", url);
		res.redirect(url);
	},
	/**
	 * 获取用户信息
	 * @param accessToken 用户首页的访问凭据
	 * @param openId 用户的第三方id
	 */
	getUserInfo: async function (accessToken, openId) {
		let url = "https://api.weixin.qq.com/sns/userinfo";
		let data = {
			"access_token": accessToken,
			"openid": openId,
			"lang": "zh_CN"
		};
		return JSON.parse(await get(url, data, loginAgentOptions));
	},
	/**
	 * 解析回调数据
	 * 鉴于微信这么恶心的字节流回调和nodejs这么恶心的解析字节流的方法，所以写了这个函数
	 * 这个是目前比较优雅的办法了
	 * @param req 请求对象
	 */
	parseCallbackData: async function (req) {
		var bytes = '';
		return new Promise(function (resolve, reject) {
			req.on('data', function (data) {
				bytes += data.toString();
			});
			req.on('end', function () {
				resolve(stringUtil.xml2json(bytes));
			});
		}).catch(function (error) {
			LOG.error(error);
		});
	},
	/**
	 * 把长链换成短链
	 * @param link 链接
	 */
	getShortLink: async function (link) {
		let accessToken = await this.getAccessToken();
		let url = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=" + accessToken;
		let data = {
			action: 'long2short',
			long_url: link
		};
		return JSON.parse(await post(url, data, loginAgentOptions));
	}
};


/**
 * post获取数据
 * @param url 请求地址
 * @param data 请求数据
 * @param agentOptions 代理配置
 */
function post(url, data, agentOptions) {
	return new Promise(function (resolve, reject) {
		request.post({url: url, form: data, agentOptions: agentOptions}, function (err, httpResponse, body) {
			if (err) {
				reject(err);
				return;
			}
			resolve(body);
		})
	}).catch(function (e) {
		LOG.error(e);
	});
}

/**
 * get获取数据
 * @param url 请求地址
 * @param data 请求数据
 * @param agentOptions 代理配置
 */
function get(url, data, agentOptions) {
	url += '?';
	let keyArray = Object.keys(data);
	for (let key of keyArray) {
		url += `${key}=${data[key]}&`;
	}
	return new Promise(function (resolve, reject) {
		request.get({url: url, form: data, agentOptions: agentOptions}, function (err, httpResponse, body) {
			if (err) {
				reject(err);
				return;
			}
			resolve(body);
		})
	}).catch(function (e) {
		LOG.error(e);
	});
}

/**
 * 生成微信接口sign值
 * @param data
 */
function genWechatSign(data) {
	let str = '';

	// 按照key排序
	let keyList = sortUtil.sortKey(data);
	for (let key of keyList) {
		str += `${key}=${data[key]}&`;
	}
	//str += `key=${key}`;
	return think.md5(str).toUpperCase();
}