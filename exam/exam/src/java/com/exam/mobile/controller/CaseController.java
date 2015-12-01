package com.exam.mobile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CaseController {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(CaseController.class);

	/**
	 * 下载app
	 */
	@RequestMapping(value = "/index")
	public ModelAndView downApp() {
		System.err.println(1111111111);
		return new ModelAndView("case/downApp");
	}
}