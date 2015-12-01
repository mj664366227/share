package com.exam.dao.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.core.client.HttpClient;
import com.exam.core.jdbc.DbService;
import com.exam.core.util.StringUtil;

@Component
public class ExamDao {
	private final static Logger logger = LoggerFactory.getLogger(ExamDao.class);
	@Autowired
	private DbService db;
	@Autowired
	private HttpClient httpClient;

	/**
	 * 添加一道科目1的判断题
	 * @param baid 问题id
	 * @param question 问题内容
	 * @param image 图片
	 * @param answer 答案(true-对，false-错)
	 */
	private void addExam1_2(long baid, String question, String image, boolean answer) {
		String sql = "REPLACE INTO `exam1_2`(`id`,`question`,`image`,`answer`) VALUES (?,?,?,?)";
		db.update(sql, baid, question, image, answer ? 1 : 0);
		logger.warn("新增一道科目1的判断题：{}\t答案：{}", question, answer ? "对" : "错");
	}

	/**
	 * 添加一套科目一的题目
	 */
	public void addExam1() {
		String str = httpClient.getString("http://kaoshi.jxedt.com/guangzhou/");
		str = str.substring(str.indexOf("<div class=\"itout\">") + 20).trim();
		str = str.substring(0, str.indexOf("<script")).trim();

		String[] arr = str.split("<div class='it'");
		for (int i = 1; i < arr.length; i++) {
			String s = arr[i].trim();
			s = "<" + s.substring(0, s.indexOf("<ul class='i'>")).trim();
			int t = StringUtil.getInt(s.substring(0, s.indexOf("t=") + 6).replaceAll("[^0-9]+", ""));
			if (t == 1) {
				// 科目1判断题
				boolean answer = s.substring(s.indexOf("v=") + 3, s.indexOf("v=") + 4).trim().equals("对");
				long baid = StringUtil.getLong(s.substring(s.indexOf("baid='") + 6, s.indexOf("' rid='")));
				String question = s.substring(s.indexOf("<h3>") + 4, s.indexOf("</h3>")).trim();
				String image = "";
				if (s.indexOf("<dl>") > -1) {
					image = s.substring(s.indexOf("<dl>") + 4, s.indexOf("</dl>")).trim();
					image = image.replaceAll("<img lazysrc='", "");
					image = image.replaceAll("'/>", "");
					image = "http://ww3.sinaimg.cn/mw240/" + image;
				}
				addExam1_2(baid, question, image, answer);
			} else {
				// 科目1单选题
			}
		}
	}
}