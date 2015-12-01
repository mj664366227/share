package com.gu.service.checkout;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gu.core.client.HttpClient;
import com.gu.core.enums.CheckoutType;
import com.gu.core.util.IdGender;
import com.gu.core.util.JSONObject;
import com.gu.core.util.RandomUtil;
import com.gu.core.util.Secret;
import com.gu.core.util.StringUtil;
import com.gu.core.util.WechatUtil;
import com.gu.dao.CheckoutDao;

/**
 * 提现服务
 * @author ruan
 */
@Component
public class CheckoutService {
	private final static Logger logger = LoggerFactory.getLogger(CheckoutService.class);
	@Autowired
	private CheckoutDao checkoutDao;
	@Autowired
	private HttpClient httpClient;
	@Value("${wechat.appid}")
	private String wechatAppid;
	@Value("${wechat.mchid}")
	private String wechatMchid;

	/**
	 * 微信提现
	 * @author ruan 
	 * @param userId 用户id
	 * @param openId 第三方id
	 * @param ip 充值机器ip
	 * @param points 要提现的G点
	 */
	public boolean wechat(long userId, String openId, String ip, int points) {
		// 先记录流水
		long orderId = IdGender.genUniqueId();
		checkoutDao.addCheckoutLog(orderId, userId, points, ip, CheckoutType.wechat);

		// 提现
		Map<String, String> param = new HashMap<>();
		param.put("mch_appid", wechatAppid);
		param.put("mchid", wechatMchid);
		param.put("nonce_str", Secret.md5(String.valueOf(System.nanoTime()) + String.valueOf(RandomUtil.rand(100000000, 999999999))));
		param.put("partner_trade_no", String.valueOf(orderId));
		param.put("openid", openId);
		param.put("check_name", "NO_CHECK");
		param.put("amount", String.valueOf(points));
		param.put("desc", "G点提现");
		param.put("spbill_create_ip", ip);
		param.put("sign", WechatUtil.genWechatSign(param));
		String xml = httpClient.post("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", WechatUtil.map2xml(param));
		Map<String, String> result = WechatUtil.xml2map(xml);
		logger.warn(result.toString());
		if (!"SUCCESS".equals(StringUtil.getString(result.get("result_code")))) {
			checkoutDao.updateCheckoutStatus(orderId, false, JSONObject.encode(result));
			logger.warn("checkout exception! {}", result);
			return false;
		}

		// 更新订单状态
		checkoutDao.updateCheckoutStatus(orderId, true, JSONObject.encode(result));
		return true;
	}

	/**
	 * 更新提现状态
	 * @author ruan 
	 * @param orderId
	 */
	public void updateCheckoutStatus(long orderId) {
		checkoutDao.updateCheckoutStatus(orderId, true, JSONObject.encode(getTransferInfo(orderId)));
	}

	/**
	 * 获取某一笔提现的数据
	 * @author ruan 
	 * @param orderId 订单id
	 */
	public Map<String, String> getTransferInfo(long orderId) {
		Map<String, String> param = new HashMap<>();
		param.put("appid", wechatAppid);
		param.put("mch_id", wechatMchid);
		param.put("nonce_str", Secret.md5(String.valueOf(System.nanoTime()) + String.valueOf(RandomUtil.rand(100000000, 999999999))));
		param.put("partner_trade_no", String.valueOf(orderId));
		param.put("sign", WechatUtil.genWechatSign(param));
		String xml = httpClient.post("https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo", WechatUtil.map2xml(param));
		return WechatUtil.xml2map(xml);
	}
}