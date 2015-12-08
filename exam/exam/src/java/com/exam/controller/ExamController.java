package com.exam.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exam.core.util.HttpServerUtil;
import com.exam.core.util.JSONObject;
import com.exam.dao.ExamDao;
import com.exam.dao.model.DAnswer;

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
	public ModelAndView exam1(JSONObject data, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		long id = data.getLong("id");
		Map<Long, Object> examMap = examDao.getExam1(session.getId());
		if (request.getMethod().equals(RequestMethod.POST.toString()) && id > 0) {
			HttpServerUtil.send(JSONObject.encode(examMap.get(id)), response);
			return null;
		}

		ModelAndView model = new ModelAndView("exam");
		model.addObject("title", "科目一");
		model.addObject("kemu", 1);
		model.addObject("examMap", examMap);
		model.addObject("time", 60 * 45);
		return model;
	}

	/**
	 * exam4
	 */
	@RequestMapping(value = "/exam4")
	public ModelAndView exam4(JSONObject data, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("exam");
		model.addObject("title", "科目四");
		model.addObject("kemu", 4);
		model.addObject("time", 60 * 30);
		return model;
	}

	/**
	 * getanswer
	 * @author ruan 
	 * @param data
	 * @param session
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getanswer")
	public void getanswer(JSONObject data, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		long id = data.getLong("id");
		DAnswer answer = examDao.getAnswer(id);
		HttpServerUtil.send(JSONObject.encode(answer), response);
	}
}