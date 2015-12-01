package com.gu.core.general;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.StringUtil;

/**
 * 云通讯短信服务
 * @author ruan
 */
@Component
public class YunTongXunSMSService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(YunTongXunSMSService.class);
	/**
	 * 请求地址
	 */
	private static String url = "";
	/**	
	 * 应用id
	 */
	private static String appId = "";

	@Autowired
	private GuThreadPool guThreadPool;

	@Autowired
	private UcpaasService ucpaasService;

	/**
	 * 短信模板
	 * @author ruan
	 */
	public enum TemplateId {
		/**
		 * 短信验证码(45386)<br>
		 * 【共做室APP】验证码({1})，{2}分钟输入有效，脑洞大开，创意头脑风暴嗨起来哟！
		 */
		SmsVerifyCode("45386"),
		/**
		 * 企业发布专案通知后台审核(42514)<br>
		 * 【共做室】{1}公司已经发布了专案{2}，请尽快审核！
		 */
		ApproveCase("42514"),
		/**
		 * 服务器发生错误(45388)<br>
		 * 【共做室】服务器发生错误，{1}
		 */
		ServerError("45388"),
		/**
		 * 品牌注册成功提示(47517)<br>
		 * 【共做室】品牌“{1}”注册成功！
		 */
		CompanyRegisterSuccess("47517"),
		/**
		 * 重置密码短信提示(47775)<br>
		 * 【共做室】您正在重置密码，验证码是({1})，{2}分钟输入有效！
		 */
		ResetPassword("47775");
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

	//初始化
	static {
		url = "app.cloopen.com";
		appId = "8a48b5514eaf512c014eb4eae8a708cb";
	}

	/**
	 * 只允许用spring注入
	 */
	private YunTongXunSMSService() {
		/*
		 切换到正式环境注意事项：
		1.将Base URL 由沙盒环境（sandboxapp.cloopen.com）替换到生产环境（app.cloopen.com）
		2.做好认证 创建应用并上线.将AppId由“测试Demo”的AppId改为自己创建的应用的AppId。
		3.将模板ID由测试模板ID 1 改为自己创建的模板ID。 
		 */
	}

	/**
	 * 发送验证码短信
	 * @param templateId 短信模板
	 * @param mobile 手机号
	 * @param params 参数
	 */
	public void sendSMS(TemplateId templateId, String mobile, String... params) {
		guThreadPool.execute(() -> {
			// 第一个字符不是1，证明不是国内手机
			if (mobile.charAt(0) != '1') {
				ucpaasService.sendSMS(com.gu.core.general.UcpaasService.TemplateId.valueOf(templateId.toString()), mobile, params);
			} else {
				//初始化SDK
				CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

				//******************************注释*********************************************
				//*初始化服务器地址和端口                                                       *
				//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
				//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
				//*******************************************************************************
				restAPI.init(url, "8883");

				//******************************注释*********************************************
				//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
				//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
				//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
				//*******************************************************************************
				restAPI.setAccount("8a48b5514e0b153e014e1ec8a7de0a38", "97799d6bdfec44558d2e70e2bb0ebc4b");

				//******************************注释*********************************	************
				//*初始化应用ID                                                                 *
				//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
				//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
				//*******************************************************************************
				restAPI.setAppId(appId);

				//******************************注释****************************************************************
				//*调用发送模板短信的接口发送短信                                                                  *
				//*参数顺序说明：                                                                                  *
				//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
				//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
				//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
				//*第三个参数是要替换的内容数组。																														       *
				//**************************************************************************************************

				//**************************************举例说明***********************************************************************
				//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
				//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
				//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
				//*********************************************************************************************************************
				HashMap<String, Object> result = restAPI.sendTemplateSMS(String.valueOf(mobile), templateId.getTemplateId(), params);
				if ("000000".equals(result.get("statusCode"))) {
					//正常返回输出data包体信息（map）
					logger.debug(StringUtil.getString(result.get("data")));
				} else {
					//异常返回输出错误码和错误信息
					logger.error("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));

					// 发送不成功，就用云之讯
					ucpaasService.sendSMS(com.gu.core.general.UcpaasService.TemplateId.valueOf(templateId.toString()), mobile, params);
				}
			}
		});
	}
}