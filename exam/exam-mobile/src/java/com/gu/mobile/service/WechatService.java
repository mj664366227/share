package com.gu.mobile.service;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gu.core.client.HttpClient;
import com.gu.core.util.JSONObject;
import com.gu.core.util.RandomUtil;
import com.gu.core.util.Secret;
import com.gu.core.util.SystemUtil;
import com.gu.core.util.Time;

/**
 * 微信分享服务
 * @author ruan
 */
@Component
public class WechatService {
	private final static Logger logger = LoggerFactory.getLogger(WechatService.class);
	@Autowired
	private HttpClient httpClient;
	private final static String appId = "wx037c69f5f8c13e61";
	private final static String appSecret = "8ac1fde6c3b2a4e9766d47662109d36e";

	/**
	 * accessToken缓存
	 */
	private LoadingCache<Integer, JSONObject> accessTokenCache = CacheBuilder.newBuilder().expireAfterWrite(7100, TimeUnit.SECONDS).build(new CacheLoader<Integer, JSONObject>() {
		public JSONObject load(Integer key) throws Exception {
			String url = "https://api.weixin.qq.com/cgi-bin/token";
			Map<String, Object> data = new HashMap<>();
			data.put("grant_type", "client_credential");
			data.put("appid", appId);
			data.put("secret", appSecret);
			JSONObject json = JSONObject.decode(httpClient.get(url, data));
			logger.warn("get accessToken from wechat, {}", json);
			return json;
		}
	});

	/**
	 * accessToken by code 缓存
	 */
	private LoadingCache<String, JSONObject> accessTokenByCode = CacheBuilder.newBuilder().expireAfterWrite(7100, TimeUnit.SECONDS).build(new CacheLoader<String, JSONObject>() {
		public JSONObject load(String code) throws Exception {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
			Map<String, Object> data = new HashMap<>();
			data.put("appid", "wx71b54f7bb3586db2");
			data.put("secret", "d4f9d23bc820623e46541a0f845caf0b");
			data.put("code", code);
			data.put("grant_type", "authorization_code");
			JSONObject json = JSONObject.decode(httpClient.get(url, data));
			logger.warn("get accessToken by code from wechat, {}", json);
			return json;
		}
	});

	/**
	 * jsapi ticket缓存
	 */
	private LoadingCache<String, JSONObject> jsapiTicketCache = CacheBuilder.newBuilder().expireAfterWrite(7100, TimeUnit.SECONDS).build(new CacheLoader<String, JSONObject>() {
		public JSONObject load(String accessToken) throws Exception {
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
			Map<String, Object> data = new HashMap<>();
			data.put("access_token", accessToken);
			data.put("type", "jsapi");
			JSONObject json = JSONObject.decode(httpClient.get(url, data));
			logger.warn("get jsapi ticket from wechat, {}", json);
			return json;
		}
	});

	/**
	 * 获取accessToken
	 * @author ruan 
	 * @return
	 */
	private JSONObject getAccessToken() {
		try {
			return accessTokenCache.get(1);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 获取jsapi ticket
	 * @author ruan 
	 * @param accessToken
	 */
	private JSONObject getTicket(String accessToken) {
		try {
			return jsapiTicketCache.get(accessToken);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 获取微信分享的数据
	 * @param data 网络请求过来的数据
	 * @param url 分享链接
	 */
	public Map<String, String> getWechatShareData(Map<String, Object> data, String url) {
		JSONObject accessTokenJSON = getAccessToken();
		JSONObject ticket = getTicket(accessTokenJSON.getString("access_token"));
		StringBuilder link = new StringBuilder();
		link.append("https://gatherup.cc/mobile");
		link.append(url);
		link.append("/");
		if (!data.isEmpty()) {
			data.remove("ip");
			link.append("?");
			for (Entry<String, Object> e : data.entrySet()) {
				link.append(e.getKey());
				link.append("=");
				link.append(e.getValue());
				link.append("&");
			}

			int len = link.length();
			link.delete(len - 1, len);
		}
		Map<String, String> dataMap = sign(ticket.getString("ticket"), link.toString());
		dataMap.put("appId", appId);
		dataMap.put("link", link.toString());
		return dataMap;
	}

	/**
	 * 通过code换取网页授权access_token
	 * @author ruan
	 * @param code
	 */
	public JSONObject getAccessTokenByCode(String code) {
		try {
			return accessTokenByCode.get(code);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 获取用户信息
	 * @author ruan 
	 * @param accessToken
	 * @param openId
	 */
	public JSONObject getUserInfo(String accessToken, String openId) {
		String url = "https://api.weixin.qq.com/sns/userinfo";
		Map<String, Object> data = new HashMap<>();
		data.put("access_token", accessToken);
		data.put("openid", openId);
		data.put("lang", "zh_CN");
		return JSONObject.decode(httpClient.get(url, data));
	}

	private Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = Secret.md5(String.valueOf(System.nanoTime()) + String.valueOf(RandomUtil.rand(100000000, 999999999)));
		String timestamp = String.valueOf(Time.now());
		String string1;
		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (Exception e) {
			logger.error("", e);
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private final static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 重定向到微信登录页面
	 * @author ruan 
	 * @param callbackURL 回调url
	 * @param response
	 */
	public void redirect2login(String callbackURL, HttpServletResponse response) {
		String appId = "wx71b54f7bb3586db2";
		String redirectUri = "https://gatherup.cc/mobile" + callbackURL;
		String scope = "snsapi_userinfo";
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=1#wechat_redirect";
		try {
			url = String.format(url, appId, URLEncoder.encode(redirectUri, SystemUtil.getSystemCharsetString()), scope);
			response.sendRedirect(url);
			logger.warn("redirect to: {}", url);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
