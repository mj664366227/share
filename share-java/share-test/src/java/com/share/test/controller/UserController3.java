package com.share.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.share.protocol.ReqUserReg;
import com.share.protocol.ResUserReg;

@Controller("反对地方模块")
public class UserController3 {
	/**
	 * 用户注册
	 */
	@PostMapping("/user3/reg")
	public ResUserReg userReg(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * 用户登录
	 */
	@PostMapping("/user3/login")
	public ResUserReg userLogin(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * 用户列表
	 */
	@GetMapping("/user3/list")
	public ResUserReg userList(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}
	
	/**
	 * 用户注册2
	 */
	@PostMapping("/user3/reg2")
	public ResUserReg userReg2(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * 用户登录2
	 */
	@PostMapping("/user3/login2")
	public ResUserReg userLogin2(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * 用户列表2
	 */
	@GetMapping("/user3/list2")
	public ResUserReg userList2(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}
}