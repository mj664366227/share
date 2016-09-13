package com.share.test.http.server;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.core.annotation.Menu;

@Controller
public class adminController {
	@RequestMapping(value = "/admin/1", method = RequestMethod.GET)
	@Menu(menu = "辅导费", parentMenu = "admin")
	public void admin1(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/admin/2", method = RequestMethod.GET)
	@Menu(menu = "dderd", parentMenu = "admin")
	public void admin2(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/admin/3", method = RequestMethod.GET)
	@Menu(menu = "你好", parentMenu = "admin")
	public void admin3(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/admin/4", method = RequestMethod.GET)
	@Menu(menu = "zz", parentMenu = "admin")
	public void admin4(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/admin/5", method = RequestMethod.GET)
	@Menu(menu = "dmin5", parentMenu = "admin")
	public void admin5(PrintWriter printWriter) {
	}

	@RequestMapping(value = "/admin/6", method = RequestMethod.GET)
	@Menu(menu = "admin6", parentMenu = "b")
	public void admin6(PrintWriter printWriter) {
	}
}
