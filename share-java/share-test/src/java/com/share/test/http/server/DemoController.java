package com.share.test.http.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.share.test.protocol.ReqUserReg;
import com.share.test.protocol.ResUserReg;

@Controller
public class DemoController {
	/**
	 * 用户注册
	 */
	@PostMapping("/user/reg")
	public ResUserReg userReg(ReqUserReg reqUserReg) {
		ResUserReg resUserReg = new ResUserReg();
		resUserReg.setUserId(12);
		return resUserReg;
	}

	/**
	 * 用户登录
	 */
	@PostMapping("/user/login")
	public void userLogin() {
	}

	@GetMapping("/user/list")
	public void userList() {
	}
}