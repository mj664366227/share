package com.gu.core.general;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.gu.core.client.HttpClient;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.JSONObject;
import com.gu.core.util.Secret;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;

/**
 * 云之讯短信服务
 * @author ruan
 */
@Component
public class UcpaasService {
	/**
	* logger
	*/
	private final static Logger logger = LoggerFactory.getLogger(UcpaasService.class);
	/**
	 * api服务器地址
	 */
	private final static String host = "http://www.ucpaas.com/maap/sms/code";
	/**
	 * 云之讯帐号id
	 */
	private final static String sid = "bf2b90d891751bd9508c9f903d9dd0a9";
	/**
	 * 云之讯appId
	 */
	private final static String appId = "3f8394122219498eb41ad51b69e4bbb5";
	/**
	 * 账户授权令牌
	 */
	private final static String authToken = "16f1e8fecdf1af9f37b7fda64375575e";
	@Autowired
	private HttpClient httpClient;
	@Autowired
	private GuThreadPool guThreadPool;

	/**
	 * 短信模板
	 * @author ruan
	 */
	public enum TemplateId {
		/**
		 * 短信验证码(15321)<br>
		 * 【共做室APP】验证码{1}，{2}分钟输入有效，脑洞大开，创意头脑风暴嗨起来哟！
		 */
		SmsVerifyCode("15321"), 
		/**
		 * 企业发布专案通知后台审核(16152)<br>
		 * 【共做室】{1}公司已经发布了专案{2}，请尽快审核！
		 */
		ApproveCase("16152"), 
		/**
		 * 服务器发生错误(16149)<br>
		 * 【共做室】服务器发生错误，{1}
		 */
		ServerError("16149"), 
		/**
		 * 品牌注册成功提示(16150)<br>
		 * 【共做室】品牌“{1}”注册成功！
		 */
		CompanyRegisterSuccess("16150"), 
		/**
		 * 重置密码短信提示(16151)<br>
		 * 【共做室】您正在重置密码，验证码是{1}，{2}分钟输入有效！
		 */
		ResetPassword("16151");
		/**
		 * 短信模板id
		 */
		private String templateId = "";

		private TemplateId(String templateId) {
			this.templateId = templateId;
		}

		public String getTemplateId() {
			return templateId;
		}
	}

	/**
	 * 发送短信
	 * @param templateId 短信模板
	 * @param mobile 手机号
	 * @param params 参数
	 */
	public void sendSMS(TemplateId templateId, String mobile, String... params) {
		guThreadPool.execute(() -> {
			long now = StringUtil.getLong(Time.date("yyyyMMddHHmmssSSS"));
			Map<String, Object> data = new HashMap<>(7);
			data.put("sid", sid);
			data.put("appId", appId);
			data.put("time", now);
			data.put("to", String.valueOf(mobile));
			data.put("templateId", String.valueOf(templateId.getTemplateId()));
			if (params.length <= 1) {
				data.put("param", StringUtil.getString(params[0]));
			} else {
				data.put("param", Joiner.on(",").join(params));
			}
			// MD5加密（账户id+时间戳+账户授权令牌）
			data.put("sign", Secret.md5(sid + now + authToken));
			JSONObject json = JSONObject.decode(httpClient.postNotUrlencode(host, data));
			if (logger.isInfoEnabled()) {
				logger.info(json.toString());
			}
			json = json.getJSON("resp");
			if (json == null || json.isEmpty()) {
				logger.error("can not get any data from ucpass");
				return;
			}
			int respCode = json.getInt("respCode");
			if (respCode != 0) {
				logger.error("send sms to ucpass error! error code: {}", respCode);
				return;
			}
		});
	}
}