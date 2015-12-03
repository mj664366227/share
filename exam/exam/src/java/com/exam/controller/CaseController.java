package com.exam.controller;

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
	 * index
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
}