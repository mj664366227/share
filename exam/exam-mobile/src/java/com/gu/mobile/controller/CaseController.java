package com.gu.mobile.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gu.core.common.URLCommand;
import com.gu.core.protocol.IdeaListResponse;
import com.gu.core.protocol.IdeaResponse;
import com.gu.core.util.GatherUp;
import com.gu.core.util.JSONObject;
import com.gu.core.util.Secret;
import com.gu.core.util.StringUtil;
import com.gu.core.util.SystemUtil;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DStoreMobile;
import com.gu.dao.model.DUser;
import com.gu.mobile.service.WechatService;
import com.gu.service.cases.CaseService;
import com.gu.service.company.CompanyService;
import com.gu.service.idea.IdeaService;
import com.gu.service.user.UserService;

/**
 * 专案控制器
 * @author ruan
 */
@Controller("MobileCaseController")
public class CaseController {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(CaseController.class);
	private final static Pattern numberPattern = Pattern.compile("^\\d+$");
	@Autowired
	private IdeaService ideaService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private WechatService wechatService;

	/**
	 * mobile工程专案idea列表
	 */
	@RequestMapping(value = URLCommand.case_idea_list, method = RequestMethod.GET)
	public ModelAndView caseIdeaList(@PathVariable("data") Object data, JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("case/list");

		// 解密数据
		String decodeString = Secret.base64DecodeToString(Secret.base64DecodeToString(Secret.base64DecodeToString(URLDecoder.decode(StringUtil.getString(data), SystemUtil.getSystemCharsetString()))));
		JSONObject parameters = JSONObject.decode(decodeString);

		// 验证参数数量
		if (parameters.size() != 2) {
			logger.warn("error jsonstring, json: {}", parameters);
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 判断有没有time这个字段
		if (!parameters.containsKey("time")) {
			logger.warn("missing key 'time', json: {}", parameters);
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 验证专案是否存在
		long caseId = parameters.getLong("caseId");
		DCase dCase = caseService.getCaseById(caseId);
		if (dCase == null) {
			logger.warn("case not exists, caseId: {}", caseId);
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 验证企业是否存在
		DCompany company = companyService.getCompanyById(dCase.getCompanyId());
		if (company == null) {
			logger.warn("company not exists, companyId: {}", dCase.getCompanyId());
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 获取专案创意列表
		IdeaListResponse ideaListResponse = ideaService.getIdeaTop(0L, dCase.getId(), 5, 1);
		List<IdeaResponse> ideaList = ideaListResponse.getIdeaResponseList();
		model.addObject("case", dCase);
		model.addObject("company", company);
		model.addObject("ideaList", ideaList);

		// 分享给微信
		model.addAllObjects(wechatService.getWechatShareData(json.toMap(), GatherUp.cutURL(URLCommand.case_idea_list, StringUtil.urlEncode(StringUtil.getString(data)))));
		return model;
	}

	/**
	 * mobile工程专案我分享的idea
	 */
	@RequestMapping(value = URLCommand.case_idea_share, method = RequestMethod.GET)
	public ModelAndView caseIdeaShare(@PathVariable("data") Object data, JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("case/share");

		// 解密数据
		String decodeString = Secret.base64DecodeToString(Secret.base64DecodeToString(Secret.base64DecodeToString(StringUtil.urlDecode(StringUtil.getString(data)))));
		JSONObject parameters = JSONObject.decode(decodeString);

		// 验证参数数量
		if (parameters.size() != 3) {
			logger.warn("error jsonstring, json: {}", parameters);
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 判断有没有time这个字段
		if (!parameters.containsKey("time")) {
			logger.warn("missing key 'time', json: {}", parameters);
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 验证专案是否存在
		long caseId = parameters.getLong("caseId");
		DCase dCase = caseService.getCaseById(caseId);
		if (dCase == null) {
			logger.warn("case not exists, caseId: {}", caseId);
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 验证idea是否存在
		long ideaId = parameters.getLong("ideaId");
		DIdea idea = ideaService.getIdeaById(ideaId);
		if (idea == null) {
			logger.warn("idea not exists, ideaId: {}", ideaId);
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 获取idea作者的信息
		DUser user = userService.getUserById(idea.getUserId());
		if (user == null) {
			logger.warn("user not exists, userId: {}", idea.getUserId());
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		// 获取企业
		DCompany company = companyService.getCompanyById(dCase.getCompanyId());
		if (company == null) {
			logger.warn("company not exists, companyId: {}", dCase.getCompanyId());
			response.sendRedirect(URLCommand.error_404);
			return null;
		}

		model.addObject("case", dCase);
		model.addObject("user", user);
		model.addObject("idea", idea);
		model.addObject("company", company);

		// 其他点子
		IdeaListResponse ideaList = ideaService.getIdeaList(0, dCase, Long.MAX_VALUE, 3);
		List<IdeaResponse> idealist = ideaList.getIdeaResponseList();
		Iterator<IdeaResponse> it = idealist.iterator();
		boolean b = false;
		while (it.hasNext()) {
			IdeaResponse ideaResponse = it.next();
			if (idea.getId() == ideaResponse.getIdeaId()) {
				it.remove();
				b = true;
			}
			ideaResponse.setContent(StringUtil.filterEmoji(ideaResponse.getContent()));
		}
		if (!b) {
			idealist.remove(idealist.size() - 1);
		}
		model.addObject("ideaList", idealist);

		// 分享给微信
		model.addAllObjects(wechatService.getWechatShareData(json.toMap(), GatherUp.cutURL(URLCommand.case_idea_share, StringUtil.urlEncode(StringUtil.getString(data)))));
		return model;
	}

	/**
	 * 输入手机号送G点
	 */
	@RequestMapping(value = URLCommand.store)
	public ModelAndView store(JSONObject parameters, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("case/store");

		if (!request.getMethod().equals(RequestMethod.POST.toString())) {
			return model;
		}

		// 验证手机号
		String mobile = parameters.getString("mobile");
		if (!numberPattern.matcher(mobile).matches()) {
			logger.warn("error mobile number: {}", mobile);
			model.addObject("result", 1);
			return model;
		}
		if (!userService.mobileOnly(mobile)) {
			logger.warn("mobile has use, mobile: {}", mobile);
			model.addObject("result", 2);
			return model;
		}

		// 检查这个手机号预留过没有
		DStoreMobile storeMobile = userService.getStoreMobile(mobile);
		if (storeMobile != null) {
			logger.warn("mobile has store, mobile: {}", mobile);
			model.addObject("result", 3);
			return model;
		}

		// 保存手机号(预留送10点)
		userService.storeMobile(mobile, 10);
		model.addObject("result", 0);
		return model;
	}

	/**
	 * 下载app
	 */
	@RequestMapping(value = URLCommand.downApp)
	public ModelAndView downApp() {
		return new ModelAndView("case/downApp");
	}
}