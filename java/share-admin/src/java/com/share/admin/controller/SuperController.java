package com.share.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.share.admin.common.SessionKey;
import com.share.admin.common.URL;
import com.share.core.annotation.processor.MenuProcessor;
import com.share.core.general.CaptchaService;
import com.share.core.session.LocalSession;
import com.share.core.util.JSONObject;
import com.share.core.util.Secret;

@Controller
public class SuperController {
	@Autowired
	private MenuProcessor menuProcessor;
	@Autowired
	private LocalSession session;
	@Autowired
	private CaptchaService yanZhengMaService;

	/**
	 * 登录
	 */
	@RequestMapping(value = URL.userlogin)
	public ModelAndView login(JSONObject parameters, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = parameters.getString("username");
		String password = parameters.getString("password");
		if ("admin".equals(username) && "admin".equals(password)) {
			session.addValue(request, response, SessionKey.LoginData.toString(), Secret.sha(username + password));
			response.sendRedirect(URL.index);
			return null;
		}
		return new ModelAndView("super/login");
	}

	/**
	 * 输出验证码
	 */
	@RequestMapping(value = URL.captcha)
	public void captcha(JSONObject parameters, HttpServletRequest request, HttpServletResponse response) {
		yanZhengMaService.getRandomStringImg(100, 40, 4, request, response);
	}

	/**
	 * 登出
	 */
	@RequestMapping(value = URL.userlogout)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		session.removeValue(request, response, SessionKey.LoginData.toString());
		response.sendRedirect(URL.userlogin);
	}

	/**
	 * 首页
	 */
	@RequestMapping(value = URL.index)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("super/frame");
		//model.addAttribute("menu", menuProcessor.getAllMenu());
		//model.addAttribute("url", menuProcessor.getAllUrl());
		return model;
	}

	/**
	 * 左边
	 * @author ruan
	 */
	@RequestMapping(value = URL.left, method = RequestMethod.GET)
	public ModelAndView left() {
		return new ModelAndView("super/left");
	}

	/**
	 * 上面
	 * @author ruan
	 */
	@RequestMapping(value = URL.top, method = RequestMethod.GET)
	public ModelAndView top() {
		return new ModelAndView("super/top");
	}

	/**
	 * 后台首页
	 * @author ruan
	 */
	@RequestMapping(value = URL.main, method = RequestMethod.GET)
	public ModelAndView main() {
		return new ModelAndView("super/main");
	}
}