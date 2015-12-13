package com.exam.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exam.core.util.FileSystem;
import com.exam.core.util.HttpServerUtil;
import com.exam.core.util.JSONObject;
import com.exam.core.util.Secret;
import com.exam.core.util.StringUtil;
import com.exam.dao.ExamDao;
import com.exam.dao.model.DAnswer;

@Controller
public class ExamController {
	@Autowired
	private ExamDao examDao;

	/**
	 * login
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(JSONObject data, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("login");
		String password = data.getString("password");
		if (request.getMethod().equals(RequestMethod.POST.toString())) {
			if (!password.equals(FileSystem.getPropertyString("password"))) {
				model.addObject("tips", "密码错误");
				return model;
			}
			session.setAttribute("login", Secret.md5(FileSystem.getPropertyString("password") + FileSystem.getPropertyString("system.key")));
			response.sendRedirect("/index");
		}

		return model;
	}

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
		String userId = examDao.getSession(session);
		Map<Long, Object> examMap = examDao.getExam1(userId);
		if (request.getMethod().equals(RequestMethod.POST.toString()) && id > 0) {
			HttpServerUtil.send(JSONObject.encode(examMap.get(id)), response);
			return null;
		}

		ModelAndView model = new ModelAndView("exam");
		model.addObject("title", "科目一");
		model.addObject("kemu", 1);
		model.addObject("examMap", examMap);
		model.addObject("time", 60 * 45);
		model.addObject("pass", 90);
		model.addObject("userId", userId);
		return model;
	}

	/**
	 * exam4
	 */
	@RequestMapping(value = "/exam4")
	public ModelAndView exam4(JSONObject data, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		long id = data.getLong("id");
		String userId = examDao.getSession(session);
		Map<Long, Object> examMap = examDao.getExam4(userId);
		if (request.getMethod().equals(RequestMethod.POST.toString()) && id > 0) {
			HttpServerUtil.send(JSONObject.encode(examMap.get(id)), response);
			return null;
		}

		ModelAndView model = new ModelAndView("exam");
		model.addObject("title", "科目四");
		model.addObject("kemu", 4);
		model.addObject("examMap", examMap);
		model.addObject("time", 60 * 30);
		model.addObject("pass", 90);
		model.addObject("userId", userId);
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

	/**
	 * 获取结果
	 * @param data
	 * @param session
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/result")
	public ModelAndView result(JSONObject data, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		data.remove("ip");
		String userId = data.getString("userId");
		String from = data.getString("from");

		// 学员答案
		Map<String, Object> learnerAnswer = data.toMap();

		// 标准答案
		int step = 0;
		Map<Long, String> answerMap = null;
		if (from.equals("exam1")) {
			answerMap = examDao.getExam1Answer(userId);
			step = 1;
		} else if (from.equals("exam4")) {
			answerMap = examDao.getExam4Answer(userId);
			step = 2;
		} else {
			answerMap = new HashMap<>(0);
			return null;
		}

		// 计算得分
		int score = 0;
		for (Entry<String, Object> e : learnerAnswer.entrySet()) {
			String standerAnswer = StringUtil.getString(StringUtil.getString(answerMap.get(StringUtil.getLong(e.getKey()))).split(",")[0]);
			if (standerAnswer.equals(StringUtil.getString(e.getValue()))) {
				score = score + step;
			}
		}
		examDao.removeSession(session);
		examDao.storeAnalysis(userId, learnerAnswer, answerMap);

		ModelAndView model = new ModelAndView("result");
		model.addObject("userId", userId);
		model.addObject("score", score);
		model.addObject("from", from);
		return model;
	}

	/**
	 * 答案解析
	 * @param data
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/explain")
	public ModelAndView explain(JSONObject data, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String userId = data.getString("userId");
		String from = data.getString("from");

		Map<String, Object> analysisMap = examDao.getAnalysis(userId);
		Map<Long, Object> examMap = null;
		if (from.equals("exam1")) {
			examMap = examDao.getExam1(userId);
		} else if (from.equals("exam4")) {
			examMap = examDao.getExam4(userId);
		} else {
			examMap = new HashMap<>(0);
			return null;
		}

		ModelAndView model = new ModelAndView("explain");
		model.addObject("learnerAnswer", analysisMap.get("learnerAnswer"));
		model.addObject("answerMap", analysisMap.get("answerMap"));
		model.addObject("examMap", examMap);
		return model;
	}
}