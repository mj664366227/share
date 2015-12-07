package com.exam.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exam.dao.ExamDao;

@Controller
public class ExamController {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ExamController.class);

	@Autowired
	private ExamDao examDao;

	/**
	 * index
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	/**
	 * exam1
	 */
	@RequestMapping(value = "/exam1")
	public ModelAndView exam1() {
		ModelAndView model = new ModelAndView("exam");
		Map<Long, Object> examMap = examDao.getExam1();
		model.addObject("title", "科目一");
		model.addObject("examMap", examMap);
		return model;
	}

	/**
	 * exam4
	 */
	@RequestMapping(value = "/exam4")
	public ModelAndView exam4() {
		return new ModelAndView("exam");
	}
}