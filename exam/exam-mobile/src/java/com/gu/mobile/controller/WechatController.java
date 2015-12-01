package com.gu.mobile.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.core.common.URLCommand;
import com.gu.core.util.JSONObject;
import com.gu.core.util.Time;
import com.gu.mobile.service.WechatService;

/**
 * 微信控制器
 * @author ruan
 */
@Controller("MobileWechatController")
public class WechatController {
	@Autowired
	private WechatService wechatService;

	/**
	 * 登录微信回调
	 */
	@RequestMapping(value = URLCommand.wechat_group_talk)
	public ModelAndView wechatGroupTalk(JSONObject data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = data.getString("code");
		if (code.isEmpty()) {
			wechatService.redirect2login(URLCommand.wechat_group_talk + "/", response);
			return null;
		}

		// 如果用户拒绝授权
		ModelAndView model = new ModelAndView("groupTalk/index");
		model.addObject("week", Time.getDayOfWeekString());
		model.addObject("now", Time.now());
		if ("authdeny".equals(code)) {
			model.addObject("headimgurl", "/mobile/default/groupTalk/images/logo_lg.jpg");
			model.addObject("nickname", "共做室");
			return model;
		}

		// 用户确认授权，获取用户的信息
		JSONObject josn = wechatService.getAccessTokenByCode(code);
		josn = wechatService.getUserInfo(josn.getString("access_token"), josn.getString("openid"));
		model.addAllObjects(josn.toMap());

		// 返回分享信息
		Map<String, Object> dataMap = data.toMap();
		if (dataMap.containsKey("from")) {
			wechatService.redirect2login(URLCommand.wechat_group_talk + "/", response);
			return null;
		}
		Map<String, String> wechatShareData = wechatService.getWechatShareData(dataMap, URLCommand.wechat_group_talk);
		model.addAllObjects(wechatShareData);
		return model;
	}
}