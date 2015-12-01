package com.examination.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.examination.core.util.JSONObject;

@Controller
public class ExamController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 专案列表
	 * @throws IOException 
	 */
	@RequestMapping(value = "index")
	public ModelAndView list(JSONObject data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("exam/index");
		System.err.println(11111111);
		logger.warn("adsdkjhsadksadyu");
		return model;
	}
}