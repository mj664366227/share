package com.share.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.share.admin.common.SessionKey;
import com.share.admin.common.URL;
import com.share.admin.util.SessionUtil;
import com.share.core.annotation.Menu;
import com.share.core.annotation.processor.MenuProcessor;
import com.share.core.util.JSONObject;
import com.share.core.util.Secret;

@Controller
public class UserController {
	@Autowired
	private MenuProcessor menuProcessor;

	/**
	 * 登录
	 */
	@RequestMapping(value = URL.UserLogin)
	public ModelAndView login(JSONObject parameters, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = parameters.getString("username");
		String password = parameters.getString("password");
		if ("admin".equals(username) && "admin".equals(password)) {
			SessionUtil.addValue(request.getSession(), SessionKey.LoginData, Secret.SHA(username + password));
			response.sendRedirect(URL.Index);
			return null;
		}
		return new ModelAndView("user/login");
	}

	/**
	 * 登出
	 */
	@RequestMapping(value = URL.UserLogout)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionUtil.removeValue(request.getSession(), SessionKey.LoginData);
		response.sendRedirect(URL.UserLogin);
	}

	/**
	 * 首页
	 */
	@RequestMapping(value = URL.Index)
	public Model index(Model model) {
		model.addAttribute("menu", menuProcessor.getAllMenu());
		model.addAttribute("url", menuProcessor.getAllUrl());
		return model;
	}

	@RequestMapping(value = "/admin/1", method = RequestMethod.GET)
	@Menu(menu = "示例1", parentMenu = "系统")
	public void demo1() {
	}

	@RequestMapping(value = "/admin/3", method = RequestMethod.GET)
	@Menu(menu = "示例2", parentMenu = "系统")
	public void demo2() {
	}

	@RequestMapping(value = "/admin/4", method = RequestMethod.GET)
	@Menu(menu = "示例3", parentMenu = "系统")
	public void demo3() {
	}
}