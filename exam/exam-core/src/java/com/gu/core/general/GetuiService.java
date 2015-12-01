package com.gu.core.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gu.core.threadPool.GuThreadPool;

/**
 * 个推服务
 * @author ruan
 */
@Component
public class GetuiService {
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * appId
	 */
	@Value("${getui.appId}")
	private String appId;
	/**
	 * appkey
	 */
	@Value("${getui.appkey}")
	private String appkey;
	/**
	 * master
	 */
	@Value("${getui.master}")
	private String master;
	/**
	 * host
	 */
	@Value("${getui.host}")
	private String host;
	@Autowired
	private GuThreadPool guThreadPool;

	/**
	 * 发送推送到指定设备
	 * @author ruan 
	 * @param pushKey 推送标识
	 * @param content 推送内容(总大小不可以超过2KB)
	 */
	public void pushToSingle(String pushKey, String content) {
		guThreadPool.execute(() -> {
			TransmissionTemplate template = new TransmissionTemplate();
			template.setAppId(appId);
			template.setAppkey(appkey);
			template.setTransmissionContent(content);
			template.setTransmissionType(1);
			APNPayload payload = new APNPayload();
			payload.setBadge(1);
			payload.setSound("default");
			payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
			template.setAPNInfo(payload);

			SingleMessage message = new SingleMessage();
			message.setOffline(true);
			message.setData(template);
			message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(pushKey);
			IGtPush push = new IGtPush(host, appkey, master);

			try {
				IPushResult ret = push.pushMessageToSingle(message, target);
				logger.warn(ret.getResponse().toString());
			} catch (Exception e) {
				logger.error("", e);
			}
		});
	}
}