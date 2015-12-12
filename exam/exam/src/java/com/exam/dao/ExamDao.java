package com.exam.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.core.client.HttpClient;
import com.exam.core.jdbc.DbService;
import com.exam.core.util.FileSystem;
import com.exam.core.util.RandomUtil;
import com.exam.core.util.Secret;
import com.exam.core.util.StringUtil;
import com.exam.dao.model.DAnswer;
import com.exam.dao.model.DJudge;
import com.exam.dao.model.DSelect;

@Component
public class ExamDao {
	private final static Logger logger = LoggerFactory.getLogger(ExamDao.class);
	@Autowired
	private DbService db;
	@Autowired
	private HttpClient httpClient;
	/**
	 * 会话map
	 */
	private Map<String, String> sessionMap = new ConcurrentHashMap<>();
	/**
	 * 题目分析map
	 */
	private Map<String, Object> analysisMap = new ConcurrentHashMap<>();
	/**
	 * 科目一题目缓存
	 */
	private Map<String, Map<Long, Object>> exam1Map = new ConcurrentHashMap<>();
	/**
	 * 科目四题目缓存
	 */
	private Map<String, Map<Long, Object>> exam4Map = new ConcurrentHashMap<>();
	/**
	 * 科目一判断题缓存
	 */
	private Map<Long, DJudge> exam1_2 = new ConcurrentHashMap<Long, DJudge>();
	/**
	 * 科目一选择题缓存
	 */
	private Map<Long, DSelect> exam1_4 = new ConcurrentHashMap<Long, DSelect>();
	/**
	 * 科目四判断题缓存
	 */
	private Map<Long, DJudge> exam4_2 = new ConcurrentHashMap<Long, DJudge>();
	/**
	 * 科目四单选题缓存
	 */
	private Map<Long, DSelect> exam4_4 = new ConcurrentHashMap<Long, DSelect>();
	/**
	 * 科目四多选题缓存
	 */
	private Map<Long, DSelect> exam4_4_4 = new ConcurrentHashMap<Long, DSelect>();
	/**
	 * 答案缓存
	 */
	private Map<Long, DAnswer> answer = new ConcurrentHashMap<Long, DAnswer>();
	/**
	 * 线程池
	 */
	private ExecutorService executor = Executors.newFixedThreadPool(2);

	/**
	 * 初始化
	 * @author ruan
	 */
	@PostConstruct
	public void init() {
		//初始化科目一判断题
		initExam1_2();

		//初始化科目一选择题
		initExam1_4();

		//初始化科目四判断题
		initExam4_2();

		//初始化科目四单选题
		initExam4_4();

		//初始化科目四多选题
		initExam4_4_4();

		//初始化答案
		initAnswer();

		// 自动爬题目
		autoGetExam();
	}

	/**
	 * 自动爬题目
	 */
	public void autoGetExam() {
		for (int i = 0; i < 2; i++) {
			executor.execute(new Runnable() {
				public void run() {
					while (true) {
						addExam1();
						addExam4();
						try {
							Thread.sleep(10000);
						} catch (Exception e) {
							logger.error("", e);
						}
					}
				}
			});
		}
	}

	/**
	 * 初始化科目一判断题
	 * @author ruan
	 */
	private void initExam1_2() {
		String sql = "SELECT * FROM `exam1_2` order by `id` asc";
		for (DJudge judge : db.queryList(sql, DJudge.class)) {
			exam1_2.put(judge.getId(), judge);
		}
		logger.warn("初始化科目一判断题完成！");
	}

	/**
	 * 初始化科目一选择题
	 * @author ruan
	 */
	private void initExam1_4() {
		String sql = "SELECT * FROM `exam1_4` order by `id` asc";
		for (Map<String, Object> data : db.queryList(sql)) {
			DSelect select = map2DSelect(data);
			exam1_4.put(select.getId(), select);
		}
		logger.warn("初始化科目一选择题完成！");
	}

	/**
	 * 初始化科目四判断题
	 * @author ruan
	 */
	private void initExam4_2() {
		String sql = "SELECT * FROM `exam4_2` order by `id` asc";
		for (DJudge judge : db.queryList(sql, DJudge.class)) {
			exam4_2.put(judge.getId(), judge);
		}
		logger.warn("初始化科目四判断题完成！");
	}

	/**
	 * 初始化科目四单选题
	 * @author ruan
	 */
	private void initExam4_4() {
		String sql = "SELECT * FROM `exam4_4` order by `id` asc";
		for (Map<String, Object> data : db.queryList(sql)) {
			DSelect select = map2DSelect(data);
			exam4_4.put(select.getId(), select);
		}
		logger.warn("初始化科目四单选题完成！");
	}

	/**
	 * 初始化科目四多选题
	 * @author ruan
	 */
	private void initExam4_4_4() {
		String sql = "SELECT * FROM `exam4_4_4` order by `id` asc";
		for (Map<String, Object> data : db.queryList(sql)) {
			DSelect select = map2DSelect(data);
			exam4_4_4.put(select.getId(), select);
		}
		logger.warn("初始化科目四多选题完成！");
	}

	/**
	 * 初始化答案
	 * @author ruan
	 */
	private void initAnswer() {
		String sql = "SELECT * FROM `answer` order by `baid` asc";
		for (DAnswer answer : db.queryList(sql, DAnswer.class)) {
			this.answer.put(answer.getBaid(), answer);
		}
		logger.warn("初始化答案完成！");
	}

	/**
	 * map转成DSelect
	 * @author ruan 
	 * @param data
	 */
	private DSelect map2DSelect(Map<String, Object> data) {
		DSelect select = new DSelect();
		select.setId(StringUtil.getLong(data.get("id")));
		select.setQuestion(StringUtil.getString(data.get("question")));
		select.setImage(StringUtil.getString(data.get("image")));
		select.setFlashurl(StringUtil.getString(data.get("flashurl")));
		select.setA(StringUtil.getString(data.get("a")));
		select.setB(StringUtil.getString(data.get("b")));
		select.setC(StringUtil.getString(data.get("c")));
		select.setD(StringUtil.getString(data.get("d")));
		select.setAnswer(StringUtil.getString(data.get("answer")));
		return select;
	}

	/**
	 * 获取一套科目一的题目
	 * @author ruan 
	 * @return
	 */
	public Map<Long, Object> getExam1(String sessionId) {
		Map<Long, Object> examMap = exam1Map.get(sessionId);
		if (examMap == null) {
			examMap = new LinkedHashMap<>(100);
			Object[] keySet = exam1_2.keySet().toArray();
			int random = RandomUtil.rand(0, keySet.length - 1);
			// 40道判断题
			while (examMap.size() < 40) {
				DJudge judge = exam1_2.get(keySet[random]);
				examMap.put(judge.getId(), judge);
				random = RandomUtil.rand(0, keySet.length - 1);
			}

			// 60道单选题
			keySet = exam1_4.keySet().toArray();
			while (examMap.size() < 100) {
				random = RandomUtil.rand(0, keySet.length - 1);
				DSelect select = exam1_4.get(keySet[random]);
				examMap.put(select.getId(), select);
			}
			exam1Map.put(sessionId, examMap);
		}
		return examMap;
	}

	/**
	 * 获取一套科目四的题目
	 * @author ruan 
	 * @return
	 */
	public Map<Long, Object> getExam4(String sessionId) {
		Map<Long, Object> examMap = exam4Map.get(sessionId);
		if (examMap == null) {
			examMap = new LinkedHashMap<>(100);
			Object[] keySet = exam4_2.keySet().toArray();
			int random = RandomUtil.rand(0, keySet.length - 1);
			// 22道判断题
			while (examMap.size() < 22) {
				DJudge judge = exam4_2.get(keySet[random]);
				examMap.put(judge.getId(), judge);
				random = RandomUtil.rand(0, keySet.length - 1);
			}

			// 23道单选题
			keySet = exam4_4.keySet().toArray();
			while (examMap.size() < 45) {
				random = RandomUtil.rand(0, keySet.length - 1);
				DSelect select = exam4_4.get(keySet[random]);
				examMap.put(select.getId(), select);
			}

			// 5道多选题
			keySet = exam4_4_4.keySet().toArray();
			while (examMap.size() < 50) {
				random = RandomUtil.rand(0, keySet.length - 1);
				DSelect select = exam4_4_4.get(keySet[random]);
				examMap.put(select.getId(), select);
			}
			exam4Map.put(sessionId, examMap);
		}
		return examMap;
	}

	/**
	 * 获取一套科目一的题目的答案
	 */
	public Map<Long, String> getExam1Answer(String sessionId) {
		Map<Long, String> answerMap = new LinkedHashMap<>(100);
		for (Entry<Long, Object> e : getExam1(sessionId).entrySet()) {
			DAnswer answer = getAnswer(e.getKey());
			if (answer == null) {
				continue;
			}
			answerMap.put(answer.getBaid(), answer.getAnswer() + "," + answer.getExplain());
		}
		return answerMap;
	}

	/**
	 * 获取一套科目四的题目的答案
	 */
	public Map<Long, String> getExam4Answer(String sessionId) {
		Map<Long, String> answerMap = new LinkedHashMap<>(100);
		for (Entry<Long, Object> e : getExam4(sessionId).entrySet()) {
			DAnswer answer = getAnswer(e.getKey());
			if (answer == null) {
				continue;
			}
			answerMap.put(answer.getBaid(), answer.getAnswer() + "," + answer.getExplain());
		}
		return answerMap;
	}

	/**
	 * 获取科目一判断题
	 * @author ruan 
	 * @param id
	 */
	public DJudge getExam1_2(long id) {
		DJudge judge = exam1_2.get(id);
		if (judge == null) {
			String sql = "SELECT * FROM `exam1_2` where `id`=?";
			judge = db.queryT(sql, DJudge.class, id);
			if (judge == null) {
				return null;
			}
			exam1_2.put(judge.getId(), judge);
		}
		return judge;
	}

	/**
	 * 获取科目一选择题
	 * @author ruan 
	 * @param id
	 */
	public DSelect getExam1_4(long id) {
		DSelect select = exam1_4.get(id);
		if (select == null) {
			String sql = "SELECT * FROM `exam1_4` where `id`=?";
			List<Map<String, Object>> list = db.queryList(sql, id);
			if (list == null || list.isEmpty()) {
				return null;
			}
			select = map2DSelect(list.get(0));
			exam1_4.put(select.getId(), select);
		}
		return select;
	}

	/**
	 * 获取科目四判断题
	 * @author ruan 
	 * @param id
	 */
	public DJudge getExam4_2(long id) {
		DJudge judge = exam4_2.get(id);
		if (judge == null) {
			String sql = "SELECT * FROM `exam4_2` where `id`=?";
			judge = db.queryT(sql, DJudge.class, id);
			if (judge == null) {
				return null;
			}
			exam4_2.put(judge.getId(), judge);
		}
		return judge;
	}

	/**
	 * 获取科目四单选题
	 * @author ruan 
	 * @param id
	 */
	public DSelect getExam4_4(long id) {
		DSelect select = exam4_4.get(id);
		if (select == null) {
			String sql = "SELECT * FROM `exam4_4` where `id`=?";
			List<Map<String, Object>> list = db.queryList(sql, id);
			if (list == null || list.isEmpty()) {
				return null;
			}
			select = map2DSelect(list.get(0));
			exam4_4.put(select.getId(), select);
		}
		return select;
	}

	/**
	 * 获取科目四多选题
	 * @author ruan 
	 * @param id
	 */
	public DSelect getExam4_4_4(long id) {
		DSelect select = exam4_4_4.get(id);
		if (select == null) {
			String sql = "SELECT * FROM `exam4_4_4` where `id`=?";
			List<Map<String, Object>> list = db.queryList(sql, id);
			if (list == null || list.isEmpty()) {
				return null;
			}
			select = map2DSelect(list.get(0));
			exam4_4_4.put(select.getId(), select);
		}
		return select;
	}

	/**
	 * 获取答案
	 * @author ruan 
	 * @param id
	 */
	public DAnswer getAnswer(long id) {
		DAnswer answer = this.answer.get(id);
		if (answer == null) {
			String sql = "SELECT * FROM `answer` where `baid`=?";
			answer = db.queryT(sql, DAnswer.class, id);
			if (answer == null) {
				return null;
			}
			this.answer.put(answer.getBaid(), answer);
		}
		return answer;
	}

	/**
	 * 添加一道科目一的判断题
	 * @param baid 问题id
	 * @param question 问题内容
	 * @param image 图片
	 * @param answer 答案(true-对，false-错)
	 */
	private void addExam1_2(long baid, String question, String image, boolean answer) {
		if (getExam1_2(baid) != null) {
			return;
		}
		String sql = "REPLACE INTO `exam1_2`(`id`,`question`,`image`,`answer`) VALUES (?,?,?,?)";
		db.update(sql, baid, question, image, answer ? 1 : 0);
		logger.warn("新增一道科目1的判断题：{}\t答案：{}", question, answer ? "对" : "错");
		addQuestionAnswer(baid);
	}

	/**
	 * 添加一道科目1的选择题
	 * @author ruan 
	 * @param baid 题目id
	 * @param question 问题内容
	 * @param image 图片
	 * @param answer 答案
	 * @param a A选项
	 * @param b B选项
	 * @param c C选项
	 * @param d D选项
	 */
	private void addExam1_4(long baid, String question, String image, String answer, String a, String b, String c, String d) {
		if (getExam1_4(baid) != null) {
			return;
		}
		String sql = "REPLACE INTO `exam1_4`(`id`,`question`,`a`,`b`,`c`,`d`,`image`,`answer`) VALUES (?,?,?,?,?,?,?,?)";
		db.update(sql, baid, question, a, b, c, b, image, answer);
		logger.warn("新增一道科目1的选择题：{}\t答案：{}", question, answer);
		addQuestionAnswer(baid);
	}

	/**
	 * 添加一道科目四的判断题
	 * @param baid 问题id
	 * @param question 问题内容
	 * @param image 图片
	 * @param answer 答案(true-对，false-错)
	 */
	private void addExam4_2(long baid, String question, String image, boolean answer) {
		if (getExam4_2(baid) != null) {
			return;
		}
		String sql = "REPLACE INTO `exam4_2`(`id`,`question`,`image`,`answer`) VALUES (?,?,?,?)";
		db.update(sql, baid, question, image, answer ? 1 : 0);
		logger.warn("新增一道科目4的判断题：{}\t答案：{}", question, answer ? "对" : "错");
		addQuestionAnswer(baid);
	}

	/**
	 * 添加一道科目4的单选题
	 * @author ruan 
	 * @param baid 题目id
	 * @param question 问题内容
	 * @param image 图片
	 * @param flashurl 动画
	 * @param answer 答案
	 * @param a A选项
	 * @param b B选项
	 * @param c C选项
	 * @param d D选项
	 */
	private void addExam4_4(long baid, String question, String image, String flashurl, String answer, String a, String b, String c, String d) {
		if (getExam4_4(baid) != null) {
			return;
		}
		String sql = "REPLACE INTO `exam4_4`(`id`,`question`,`a`,`b`,`c`,`d`,`image`,`flashurl`,`answer`) VALUES (?,?,?,?,?,?,?,?,?)";
		db.update(sql, baid, question, a, b, c, b, image, flashurl, answer);
		logger.warn("新增一道科目4的单选题：{}\t答案：{}", question, answer);
		addQuestionAnswer(baid);
	}

	/**
	 * 添加一道科目1的选择题
	 * @author ruan 
	 * @param baid 题目id
	 * @param question 问题内容
	 * @param image 图片
	 * @param answer 答案
	 * @param a A选项
	 * @param b B选项
	 * @param c C选项
	 * @param d D选项
	 */
	private void addExam4_4_4(long baid, String question, String image, String answer, String a, String b, String c, String d) {
		if (getExam4_4_4(baid) != null) {
			return;
		}
		String sql = "REPLACE INTO `exam4_4_4`(`id`,`question`,`a`,`b`,`c`,`d`,`image`,`answer`) VALUES (?,?,?,?,?,?,?,?)";
		db.update(sql, baid, question, a, b, c, b, image, answer);
		logger.warn("新增一道科目4的多选题：{}\t答案：{}", question, answer);
		addQuestionAnswer(baid);
	}

	/**
	 * 添加一条题目的答案
	 * @author ruan  
	 * @param baid 题目id
	 */
	private void addQuestionAnswer(long baid) {
		String str = httpClient.getString("http://tieba.jxedt.com/posts_" + baid + ".html");
		str = str.substring(str.indexOf("firsttitle")).trim();
		String answer = StringUtil.filterHTML(str.substring(str.indexOf("<font"), str.indexOf("</font>")));
		str = str.substring(str.indexOf("bcon") - 15);
		String explain = StringUtil.filterHTML(str.substring(0, str.indexOf("</div>")));

		// 写入数据库
		String sql = "REPLACE INTO `answer`(`baid`,`answer`,`explain`) VALUES (?,?,?)";
		db.update(sql, baid, answer, explain);
		logger.warn("新增题目{}的答案解释，{}", baid, explain);
	}

	/**
	 * 添加一套科目一的题目
	 */
	public void addExam1() {
		logger.warn("try to get exam1 from jxedt");
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
			} else if (t == 2) {
				// 科目1单选题
				String answer = StringUtil.filterHTML(s.substring(s.indexOf("v=") + 3, s.indexOf("v=") + 4));
				long baid = StringUtil.getLong(s.substring(s.indexOf("baid='") + 6, s.indexOf("' rid='")));
				String question = s.substring(s.indexOf("<h3>") + 4, s.indexOf("</h3>")).trim();
				String image = "";
				if (s.indexOf("<dl>") > -1) {
					image = s.substring(s.indexOf("<dl>") + 4, s.indexOf("</dl>")).trim();
					image = image.replaceAll("<img lazysrc='", "");
					image = image.replaceAll("'/>", "");
					image = "http://ww3.sinaimg.cn/mw240/" + image;
				}

				// 4个选项
				String item = arr[i].trim();
				item = item.substring(item.indexOf("<ul class='i'>")).trim();
				String[] itemArr = item.split("<font></font>");
				String a = "", b = "", c = "", d = "";
				for (int j = 1; j < itemArr.length; j++) {
					switch (j) {
					case 1:
						a = StringUtil.filterHTML(itemArr[j]);
						break;
					case 2:
						b = StringUtil.filterHTML(itemArr[j]);
						break;
					case 3:
						c = StringUtil.filterHTML(itemArr[j]);
						break;
					case 4:
						d = StringUtil.filterHTML(itemArr[j]);
						break;
					}
				}
				addExam1_4(baid, question, image, answer, a, b, c, d);
			}
		}
	}

	/**
	 * 添加一套科目四的题目
	 */
	public void addExam4() {
		logger.warn("try to get exam4 from jxedt");
		String str = httpClient.getString("http://km4.jxedt.com/guangzhou/");
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
				addExam4_2(baid, question, image, answer);
			} else if (t == 2) {
				// 科目4单选题
				String answer = StringUtil.filterHTML(s.substring(s.indexOf("v=") + 3, s.indexOf("v=") + 4));
				long baid = StringUtil.getLong(s.substring(s.indexOf("baid='") + 6, s.indexOf("' rid='")));
				String question = s.substring(s.indexOf("<h3>") + 4, s.indexOf("</h3>")).trim();
				String image = "";
				if (s.indexOf("<dl>") > -1) {
					image = s.substring(s.indexOf("<dl>") + 4, s.indexOf("</dl>")).trim();
					image = image.replaceAll("<img lazysrc='", "");
					image = image.replaceAll("'/>", "");
					image = "http://ww3.sinaimg.cn/mw240/" + image;
				}

				String flashurl = "";
				if (s.indexOf("flashurl") > -1) {
					s = StringUtil.getString(s.substring(s.indexOf("flashurl") + 10));
					flashurl = StringUtil.getString(s.substring(0, s.indexOf("hidden_flashplayer") - 6));
				}

				// 4个选项
				String item = arr[i].trim();
				item = item.substring(item.indexOf("<ul class='i'>")).trim();
				String[] itemArr = item.split("<font></font>");
				String a = "", b = "", c = "", d = "";
				for (int j = 1; j < itemArr.length; j++) {
					switch (j) {
					case 1:
						a = StringUtil.filterHTML(itemArr[j]);
						break;
					case 2:
						b = StringUtil.filterHTML(itemArr[j]);
						break;
					case 3:
						c = StringUtil.filterHTML(itemArr[j]);
						break;
					case 4:
						d = StringUtil.filterHTML(itemArr[j]);
						break;
					}
				}
				addExam4_4(baid, question, image, flashurl, answer, a, b, c, d);
			} else if (t == 3) {
				// 科目4多选题
				s = arr[i].trim();
				String answer = StringUtil.filterHTML(s.substring(s.indexOf("v=") + 3, s.indexOf("v=") + 10)).replaceAll("[^A-D]+", "");
				long baid = StringUtil.getLong(s.substring(s.indexOf("baid='") + 6, s.indexOf("' rid='")));
				String question = s.substring(s.indexOf("<h3>") + 4, s.indexOf("</h3>")).trim();
				String image = "";
				if (s.indexOf("<dl>") > -1) {
					image = s.substring(s.indexOf("<dl>") + 4, s.indexOf("</dl>")).trim();
					image = image.replaceAll("<img lazysrc='", "");
					image = image.replaceAll("'/>", "");
					image = "http://ww3.sinaimg.cn/mw240/" + image;
				}

				// 4个选项
				s = s.substring(s.indexOf("<ul class='i'>")).trim();
				String[] itemArr = s.split("<font></font>");
				String a = "", b = "", c = "", d = "";
				for (int j = 1; j < itemArr.length; j++) {
					switch (j) {
					case 1:
						a = StringUtil.filterHTML(itemArr[j]);
						break;
					case 2:
						b = StringUtil.filterHTML(itemArr[j]);
						break;
					case 3:
						c = StringUtil.filterHTML(itemArr[j]);
						break;
					case 4:
						d = StringUtil.filterHTML(itemArr[j]);
						break;
					}
				}
				addExam4_4_4(baid, question, image, answer, a, b, c, d);
			}
		}
	}

	/**
	 * 获取一个会话
	 * @param session
	 * @return
	 */
	public String getSession(HttpSession session) {
		String k = StringUtil.getString(session.getId());
		String v = StringUtil.getString(sessionMap.get(k));
		if (v.isEmpty()) {
			v = Secret.md5(String.valueOf(System.nanoTime()) + k + FileSystem.getPropertyString("system.key"));
			sessionMap.put(k, v);
		}
		return v;
	}

	/**
	 * 删除会话
	 * @param session
	 */
	public void removeSession(HttpSession session) {
		sessionMap.remove(StringUtil.getString(session.getId()));
	}

	/**
	 * 保存分析
	 */
	public void storeAnalysis(String sessionId, Map<String, Object> learnerAnswer, Map<Long, String> answerMap) {
		Map<String, Object> tmp = new HashMap<>(2);
		tmp.put("learnerAnswer", learnerAnswer);
		tmp.put("answerMap", answerMap);
		analysisMap.put(sessionId, tmp);
	}

	/**
	 * 获取分析
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getAnalysis(String sessionId) {
		return (Map<String, Object>) analysisMap.get(sessionId);
	}
}