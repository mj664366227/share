package com.gu.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.general.DistrictService;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.db.GuLogDbService;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.model.CaseCountResult;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DUser;

/**
 * 专案统计专用dao类
 * @author luo
 */
@Component
public class CaseCountDao {
	@Autowired
	private GuLogDbService guLogDbService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private GuMasterDbService guMasterDbService;

	/**
	 * 参与专案用户城市统计
	 * @param caseId 专案id
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 */
	public void addCaseCountCity(long caseId, long provinceId, long cityId) {
		if (provinceId <= 0 || cityId <= 0) {
			return;
		}
		String sql = "insert into `case_count_city` (`case_id`,`province_id`,`city_id`,`count`) values (?,?,?,1)  on duplicate key update `count`=`count`+1";
		guLogDbService.update(sql, caseId, provinceId, cityId);
	}

	/**
	 * 参与专案用户省份统计
	 * @param caseId 专案id
	 * @param provinceId 省份id
	 */
	public void addCaseCountProvince(long caseId, long provinceId) {
		if (provinceId <= 0) {
			return;
		}
		String sql = "insert into `case_count_province` (`case_id`,`province_id`,`count`) values (?,?,1)  on duplicate key update `count`=`count`+1";
		guLogDbService.update(sql, caseId, provinceId);
	}

	/**
	 * 参与专案用户性别统计
	 * @param caseId 专案id
	 * @param sex 性别 1=男 2=女
	 */
	public void addCaseCountSex(long caseId, int sex) {
		String sql_boy = "insert into `case_count_sex` (`case_id`,`boy`) values (?,1)  on duplicate key update `boy`=`boy`+1";
		String sql_girl = "insert into `case_count_sex` (`case_id`,`girl`) values (?,1)  on duplicate key update `girl`=`girl`+1";
		if (sex == 1) {
			guLogDbService.update(sql_boy, caseId);
		} else if (sex == 2) {
			guLogDbService.update(sql_girl, caseId);
		}
	}

	/**
	 * 参与专案实名认证统计
	 * @param caseId 专案id
	 * @param user 用户对象
	 */
	public void addCaseCountProve(long caseId, DUser user) {
		String sql_prove = "insert into `case_count_prove` (`case_id`,`prove`) values (?,1)  on duplicate key update `prove`=`prove`+1";
		String sql_un_prove = "insert into `case_count_prove` (`case_id`,`un_prove`) values (?,1)  on duplicate key update `un_prove`=`un_prove`+1";
		if (user.getIsProve() == 1) {
			guLogDbService.update(sql_prove, caseId);
		} else {
			guLogDbService.update(sql_un_prove, caseId);
		}
	}

	/**
	 * 参与专案年龄段统计
	 * @param caseId 专案id
	 * @param user 用户对象
	 */
	public void addCaseCountAge(long caseId, DUser user) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_age` (`case_id`");
		int year = StringUtil.getInt(Time.date("yyyy", user.getBirthday()));
		if (year < 1970) {
			//70前
			sql.append(",`before70`) values (?,1) ");
		} else if (year < 1980) {
			//70后
			sql.append(",`since70`) values (?,1) ");
		} else if (year < 1985) {
			//80后
			sql.append(",`since80`) values (?,1) ");
		} else if (year < 1990) {
			//85后
			sql.append(",`since85`) values (?,1) ");
		} else if (year < 1995) {
			//90后
			sql.append(",`since90`) values (?,1) ");
		} else if (year < 2000) {
			//95后
			sql.append(",`since95`) values (?,1) ");
		} else {
			//00后
			sql.append(",`since00`) values (?,1) ");
		}

		sql.append(" on duplicate key update ");

		if (year < 1970) {
			//70前
			sql.append(" `before70`=`before70`+1 ");
		} else if (year < 1980) {
			//70后
			sql.append(" `since70`=`since70`+1 ");
		} else if (year < 1985) {
			//80后
			sql.append(" `since80`=`since80`+1 ");
		} else if (year < 1990) {
			//85后
			sql.append(" `since85`=`since85`+1 ");
		} else if (year < 1995) {
			//90后
			sql.append(" `since90`=`since90`+1 ");
		} else if (year < 2000) {
			//95后
			sql.append(" `since95`=`since95`+1 ");
		} else {
			//00后
			sql.append(" `since00`=`since00`+1 ");
		}
		guLogDbService.update(sql.toString(), caseId);
	}

	/**
	 * 添加专案参与统计
	 * @param caseId 专案id
	 */
	public void addCaseCountTakePartIn(long caseId) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_takepartin` (`date`,`case_id`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("',?,'1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString(), caseId);
	}

	/**
	 * 添加专案参与总数统计
	 * @param dCase 专案对象
	 */
	public void addCaseCountTakePartInTotal(DCase dCase, int takePartInNum) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_takepartin_total` (`date`,`case_id`,`h");
		sql.append(h);
		sql.append("`,`total`) values ('");
		sql.append(date);
		sql.append("',?,?,?) on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`=?,`total`=?");
		guLogDbService.update(sql.toString(), dCase.getId(), takePartInNum, takePartInNum, takePartInNum, takePartInNum);
	}

	/**
	 * 添加专案关注统计
	 * @param caseId 专案id
	 */
	public void addCaseCountFocus(long caseId) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_focus` (`date`,`case_id`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("',?,'1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString(), caseId);
	}

	/**
	 * 添加专案取消关注统计
	 * @param caseId 专案id
	 */
	public void addCaseCountUnFocus(long caseId) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_unfocus` (`date`,`case_id`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("',?,'1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString(), caseId);
	}

	/**
	 * 添加专案关注总数统计
	 * @param dCase 专案对象
	 */
	public void addCaseCountFocusTotal(DCase dCase, int fansNum) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_focus_total` (`date`,`case_id`,`h");
		sql.append(h);
		sql.append("`,`total`) values ('");
		sql.append(date);
		sql.append("',?,?,?) on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`=?,`total`=?");
		guLogDbService.update(sql.toString(), dCase.getId(), fansNum, fansNum, fansNum, fansNum);
	}

	/**
	 * 添加专案创意统计
	 * @param caseId 专案id
	 */
	public void addCaseCountIdea(long caseId) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_idea` (`date`,`case_id`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("',?,'1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString(), caseId);
	}

	/**
	 * 添加专案创意总数统计
	 * @param dCase 专案对象
	 */
	public void addCaseCountIdeaTotal(DCase dCase, int ideaNum) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `case_count_idea_total` (`date`,`case_id`,`h");
		sql.append(h);
		sql.append("`,`total`) values ('");
		sql.append(date);
		sql.append("',?,?,?) on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`=?,`total`=?");
		guLogDbService.update(sql.toString(), dCase.getId(), ideaNum, ideaNum, ideaNum, ideaNum);
	}

	/**
	 * 获取参与专案性别统计
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountSex(long caseId) {
		String sql = "SELECT * FROM  `case_count_sex` where `case_id`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId);
		HashMap<String, Object> data = new HashMap<String, Object>();

		List<Map<String, Object>> series = new ArrayList<>(2);
		int boyNum = 0, girlNum = 0;
		if (list != null && !list.isEmpty()) {
			Map<String, Object> listData = list.get(0);
			boyNum = StringUtil.getInt(listData.get("boy"));
			girlNum = StringUtil.getInt(listData.get("girl"));
		}

		// 男
		Map<String, Object> boy = new HashMap<String, Object>(3);
		boy.put("name", "男");
		boy.put("type", "bar");
		boy.put("data", new int[] { boyNum });
		series.add(boy);

		// 女
		Map<String, Object> girl = new HashMap<String, Object>(3);
		girl.put("name", "女");
		girl.put("type", "bar");
		girl.put("data", new int[] { girlNum });
		series.add(girl);
		data.put("series", series);

		// 统计选项
		data.put("legend", new String[] { "男", "女" });
		return data;
	}

	/**
	 * 获取参与专案实名认证统计
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountProve(long caseId) {
		String sql = "SELECT * FROM  `case_count_prove` where `case_id`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId);
		HashMap<String, Object> data = new HashMap<String, Object>();

		List<Map<String, Object>> series = new ArrayList<>(2);
		int proveNum = 0, unProveNum = 0;
		if (list != null && !list.isEmpty()) {
			Map<String, Object> listData = list.get(0);
			proveNum = StringUtil.getInt(listData.get("prove"));
			unProveNum = StringUtil.getInt(listData.get("un_prove"));
		}

		// 已认证
		Map<String, Object> prove = new HashMap<String, Object>(3);
		prove.put("name", "已认证");
		prove.put("type", "bar");
		prove.put("data", new int[] { proveNum });
		series.add(prove);

		// 未认证
		Map<String, Object> unProve = new HashMap<String, Object>(3);
		unProve.put("name", "未认证");
		unProve.put("type", "bar");
		unProve.put("data", new int[] { unProveNum });
		series.add(unProve);
		data.put("series", series);

		// 统计选项
		data.put("legend", new String[] { "已认证", "未认证" });
		return data;
	}

	/**
	 * 获取参与专案年龄段统计
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountAge(long caseId) {
		String sql = "SELECT * FROM  `case_count_age` where `case_id`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId);
		HashMap<String, Object> data = new HashMap<String, Object>();

		List<Map<String, Object>> series = new ArrayList<>(2);
		int before70Num = 0, since70Num = 0, since80Num = 0, since85Num = 0, since90Num = 0, since95Num = 0, since00Num = 0;
		if (list != null && !list.isEmpty()) {
			Map<String, Object> listData = list.get(0);
			before70Num = StringUtil.getInt(listData.get("before70"));
			since70Num = StringUtil.getInt(listData.get("since70"));
			since80Num = StringUtil.getInt(listData.get("since80"));
			since85Num = StringUtil.getInt(listData.get("since85"));
			since90Num = StringUtil.getInt(listData.get("since90"));
			since95Num = StringUtil.getInt(listData.get("since95"));
			since00Num = StringUtil.getInt(listData.get("since00"));
		}

		// 70前
		Map<String, Object> before70 = new HashMap<String, Object>(3);
		before70.put("name", "70前");
		before70.put("type", "bar");
		before70.put("data", new int[] { before70Num });
		series.add(before70);

		// 70后
		Map<String, Object> since70 = new HashMap<String, Object>(3);
		since70.put("name", "70后");
		since70.put("type", "bar");
		since70.put("data", new int[] { since70Num });
		series.add(since70);

		// 80后
		Map<String, Object> since80 = new HashMap<String, Object>(3);
		since80.put("name", "80后");
		since80.put("type", "bar");
		since80.put("data", new int[] { since80Num });
		series.add(since80);

		// 85后
		Map<String, Object> since85 = new HashMap<String, Object>(3);
		since85.put("name", "85后");
		since85.put("type", "bar");
		since85.put("data", new int[] { since85Num });
		series.add(since85);

		// 90后
		Map<String, Object> since90 = new HashMap<String, Object>(3);
		since90.put("name", "90后");
		since90.put("type", "bar");
		since90.put("data", new int[] { since90Num });
		series.add(since90);

		// 95后
		Map<String, Object> since95 = new HashMap<String, Object>(3);
		since95.put("name", "95后");
		since95.put("type", "bar");
		since95.put("data", new int[] { since95Num });
		series.add(since95);

		// 00后
		Map<String, Object> since00 = new HashMap<String, Object>(3);
		since00.put("name", "00后");
		since00.put("type", "bar");
		since00.put("data", new int[] { since00Num });
		series.add(since00);

		data.put("series", series);

		// 统计选项
		data.put("legend", new String[] { "70前", "70后", "80后", "85后", "90后", "95后", "00后" });
		return data;
	}

	/**
	 * 获取专案参与统计
	 * @param caseId 专案id
	 */
	public CaseCountResult getCaseCountResult(long caseId) {
		int now = Time.now();
		int today = StringUtil.getInt(Time.date("yyyyMMdd", now));
		int yesterday = StringUtil.getInt(Time.date("yyyyMMdd", now - 86400));
		CaseCountResult caseCountResult = new CaseCountResult();

		// 参与数
		String sqlTakePartIn = "SELECT * FROM  `case_count_takepartin` where `case_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> takePartInList = guLogDbService.queryList(sqlTakePartIn, caseId, today, yesterday);
		for (Map<String, Object> map : takePartInList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				caseCountResult.setTodayTakePartIn(countMap(map));
			}
			if (date == yesterday) {
				caseCountResult.setYesterdayTakePartIn(countMap(map));
				double rate = caseCountResult.getTodayTakePartIn() * 100.0 / caseCountResult.getYesterdayTakePartIn() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				caseCountResult.setTodayTakePartInGrowthRate(caseCountResult.getYesterdayTakePartIn() <= 0 ? 100.0 : rate);
			}
		}

		// 参与总数
		String sqlTotalTakePartIn = "SELECT * FROM  `case_count_takepartin_total` where `case_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> totalTakePartInList = guLogDbService.queryList(sqlTotalTakePartIn, caseId, today, yesterday);
		boolean noToday = false;
		for (Map<String, Object> map : totalTakePartInList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				noToday = true;
				caseCountResult.setTodayTotalTakePartIn(StringUtil.getInt(map.get("total")));
			}
			if (date == yesterday) {
				caseCountResult.setYesterdayTotalTakePartIn(StringUtil.getInt(map.get("total")));
				double rate = caseCountResult.getTodayTotalTakePartIn() * 100.0 / caseCountResult.getYesterdayTotalTakePartIn() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				caseCountResult.setTodayTotalTakePartInGrowthRate(caseCountResult.getYesterdayTotalTakePartIn() <= 0 ? 100.0 : rate);
			}
		}
		if (!noToday) {
			String sql = "select `take_part_in_num` from `case` where `id`=?";
			int takepartinNum = guMasterDbService.queryInt(sql, caseId);
			sql = "insert into `case_count_takepartin_total` (`date`,`case_id`,`total`) values (?,?,?)";
			guLogDbService.update(sql, today, caseId, takepartinNum);

			caseCountResult.setTodayTotalTakePartIn(takepartinNum);
			double rate = caseCountResult.getTodayTotalTakePartIn() * 100.0 / caseCountResult.getYesterdayTotalTakePartIn() - 100;
			rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
			caseCountResult.setTodayTotalTakePartInGrowthRate(caseCountResult.getYesterdayTotalTakePartIn() <= 0 ? 100.0 : rate);
		}

		// 新增点子数
		String sqlIdea = "SELECT * FROM  `case_count_idea` where `case_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> ideaList = guLogDbService.queryList(sqlIdea, caseId, today, yesterday);
		for (Map<String, Object> map : ideaList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				caseCountResult.setTodayIdeaNum(countMap(map));
			}
			if (date == yesterday) {
				caseCountResult.setYesterdayIdeaNum(countMap(map));
				double rate = caseCountResult.getTodayIdeaNum() * 100.0 / caseCountResult.getYesterdayIdeaNum() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				caseCountResult.setTodayIdeaNumGrowthRate(caseCountResult.getYesterdayIdeaNum() <= 0 ? 100.0 : rate);
			}
		}

		// 累计点子数
		String sqlTotalIdea = "SELECT * FROM  `case_count_idea_total` where `case_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> totalIdeaList = guLogDbService.queryList(sqlTotalIdea, caseId, today, yesterday);
		noToday = false;
		for (Map<String, Object> map : totalIdeaList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				noToday = true;
				caseCountResult.setTodayIdeaTotalNum(StringUtil.getInt(map.get("total")));
			}
			if (date == yesterday) {
				caseCountResult.setYesterdayIdeaTotalNum(StringUtil.getInt(map.get("total")));
				double rate = caseCountResult.getTodayIdeaTotalNum() * 100.0 / caseCountResult.getYesterdayIdeaTotalNum() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				caseCountResult.setTodayIdeaTotalNumGrowthRate(caseCountResult.getYesterdayIdeaTotalNum() <= 0 ? 100.0 : rate);
			}
		}
		if (!noToday) {
			String sql = "select `idea_num` from `case` where `id`=?";
			int ideaNum = guMasterDbService.queryInt(sql, caseId);
			sql = "insert into `case_count_idea_total` (`date`,`case_id`,`total`) values (?,?,?)";
			guLogDbService.update(sql, today, caseId, ideaNum);

			caseCountResult.setTodayIdeaTotalNum(ideaNum);
			double rate = caseCountResult.getTodayIdeaTotalNum() * 100.0 / caseCountResult.getYesterdayIdeaTotalNum() - 100;
			rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
			caseCountResult.setTodayIdeaTotalNumGrowthRate(caseCountResult.getYesterdayIdeaTotalNum() <= 0 ? 100.0 : rate);
		}

		// 新增关注数
		String sqlFocus = "SELECT * FROM  `case_count_focus` where `case_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> focusList = guLogDbService.queryList(sqlFocus, caseId, today, yesterday);
		for (Map<String, Object> map : focusList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				caseCountResult.setTodayFocus(countMap(map));
			}
			if (date == yesterday) {
				caseCountResult.setYesterdayFocus(countMap(map));
				double rate = caseCountResult.getTodayFocus() * 100.0 / caseCountResult.getYesterdayFocus() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				caseCountResult.setTodayFocusGrowthRate(caseCountResult.getYesterdayFocus() <= 0 ? 100.0 : rate);
			}
		}

		// 累计关注总数
		String sqlTotalFocus = "SELECT * FROM  `case_count_focus_total` where `case_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> totalFocusList = guLogDbService.queryList(sqlTotalFocus, caseId, today, yesterday);
		noToday = false;
		for (Map<String, Object> map : totalFocusList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				noToday = true;
				caseCountResult.setTodayTotalFocus(StringUtil.getInt(map.get("total")));
			}
			if (date == yesterday) {
				caseCountResult.setYesterdayTotalFocus(StringUtil.getInt(map.get("total")));
				double rate = caseCountResult.getTodayTotalFocus() * 100.0 / caseCountResult.getYesterdayTotalFocus() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				caseCountResult.setTodayTotalFocusGrowthRate(caseCountResult.getYesterdayTotalFocus() <= 0 ? 100.0 : rate);
			}
		}
		if (!noToday) {
			String sql = "select `fans_num` from `case` where `id`=?";
			int fansNum = guMasterDbService.queryInt(sql, caseId);
			sql = "insert into `case_count_focus_total` (`date`,`case_id`,`total`) values (?,?,?)";
			guLogDbService.update(sql, today, caseId, fansNum);

			caseCountResult.setTodayTotalFocus(fansNum);
			double rate = caseCountResult.getTodayTotalFocus() * 100.0 / caseCountResult.getYesterdayTotalFocus() - 100;
			rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
			caseCountResult.setTodayTotalFocusGrowthRate(caseCountResult.getYesterdayTotalFocus() <= 0 ? 100.0 : rate);
		}

		// 取消关注数
		String sqlUnFocus = "SELECT * FROM  `case_count_unfocus` where `case_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> unfocusList = guLogDbService.queryList(sqlUnFocus, caseId, today, yesterday);
		for (Map<String, Object> map : unfocusList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				caseCountResult.setTodayUnfocus(countMap(map));
			}
			if (date == yesterday) {
				caseCountResult.setYesterdayUnfocus(countMap(map));
				double rate = caseCountResult.getTodayUnfocus() * 100.0 / caseCountResult.getYesterdayUnfocus() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				caseCountResult.setTodayUnfocusGrowthRate(caseCountResult.getYesterdayUnfocus() <= 0 ? 100.0 : rate);
			}
		}
		return caseCountResult;
	}

	/**
	 * 获取专案用户省份统计
	 * @param caseId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public Map<String, Object> getCaseCountProvince(long caseId, int page, int pageSize) {
		String sql = "SELECT * FROM  `case_count_province` where `case_id`=? order by `count` desc";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId);
		List<Map<String, Object>> result = new ArrayList<>(list.size());
		Map<Integer, String> provinceMap = new LinkedHashMap<>(list.size());

		int begin = (page - 1) * pageSize;
		int end = begin + pageSize - 1;
		int i = -1;
		int total = 0;
		for (Map<String, Object> data : list) {
			int provinceId = StringUtil.getInt(data.remove("province_id"));
			String provinceName = districtService.getProvince(provinceId);
			provinceMap.put(provinceId, provinceName);

			i = i + 1;
			if (begin <= i && i <= end) {
				data.remove("case_id");
				data.put("name", provinceName);
				data.put("value", StringUtil.getInt(data.remove("count")));
				result.add(data);
			}

			total += StringUtil.getInt(data.get("value"));
		}

		Map<String, Object> data = new HashMap<String, Object>(2);
		data.put("list", result);
		data.put("total", total);
		data.put("provinceMap", provinceMap);
		return data;
	}

	/**
	 * 获取专案用户城市统计
	 * @author ruan 
	 * @param caseId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public Map<String, Object> getCaseCountCity(long caseId, int page, int pageSize) {
		String sql = "SELECT * FROM  `case_count_city` where `case_id`=? order by `count` desc";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId);
		List<Map<String, Object>> result = new ArrayList<>(list.size());
		Map<String, Object> data = new HashMap<String, Object>();

		// 省  => (市 => 数量)
		int size = list.size();
		int total = 0;
		int begin = (page - 1) * pageSize;
		int end = begin + pageSize - 1;
		int i = -1;
		Map<Integer, List<Map<String, Object>>> map = new HashMap<>(size);
		for (Map<String, Object> m : list) {
			int provinceId = StringUtil.getInt(m.get("province_id"));
			int cityId = StringUtil.getInt(m.get("city_id"));
			if (provinceId <= 0 || cityId <= 0) {
				continue;
			}

			List<Map<String, Object>> cityList = map.get(provinceId);
			if (cityList == null) {
				cityList = new ArrayList<>(size);
				map.put(provinceId, cityList);
			}

			Map<String, Object> mm = new HashMap<>();
			mm.put("cityName", districtService.getCity(provinceId, cityId));
			mm.put("count", StringUtil.getInt(m.get("count")));
			cityList.add(mm);
			map.put(provinceId, cityList);

			total += StringUtil.getInt(m.get("count"));

			i = i + 1;
			if (begin <= i && i <= end) {
				m.put("cityName", districtService.getCity(provinceId, cityId));
				result.add(m);
			}
		}
		data.put("map", map);

		// 所有城市的列表
		data.put("list", result);
		data.put("listSize", list.size());

		// 总数
		data.put("total", total);
		return data;
	}

	/**
	 * 专案关注走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountFocusNew(long caseId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `case_count_focus` where `case_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId, st_, et_);
		Map<Integer, Integer> numMap = new HashMap<>(list.size());
		for (Map<String, Object> data : list) {
			numMap.put(StringUtil.getInt(data.get("date")), countMap(data));
		}

		List<String> dateList = new ArrayList<>();
		List<Integer> numList = new ArrayList<>();
		for (int i = st; i <= et; i = i + 86400) {
			dateList.add(Time.date("yyyy-MM-dd", i));
			numList.add(0);
		}

		Map<String, Object> map = new HashMap<>(2);

		int size = dateList.size();
		for (int i = 0; i < size; i++) {
			int num = StringUtil.getInt(numMap.get(StringUtil.getInt(dateList.get(i).replace("-", ""))));
			if (num <= 0) {
				continue;
			}
			numList.set(i, num);
		}

		map.put("num", numList);
		map.put("date", dateList);
		return map;
	}

	/**
	 * 点子数走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountIdea(long caseId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `case_count_idea` where `case_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId, st_, et_);
		Map<Integer, Integer> numMap = new HashMap<>(list.size());
		for (Map<String, Object> data : list) {
			numMap.put(StringUtil.getInt(data.get("date")), countMap(data));
		}

		List<String> dateList = new ArrayList<>();
		List<Integer> numList = new ArrayList<>();
		for (int i = st; i <= et; i = i + 86400) {
			dateList.add(Time.date("yyyy-MM-dd", i));
			numList.add(0);
		}

		Map<String, Object> map = new HashMap<>(2);

		int size = dateList.size();
		for (int i = 0; i < size; i++) {
			int num = StringUtil.getInt(numMap.get(StringUtil.getInt(dateList.get(i).replace("-", ""))));
			if (num <= 0) {
				continue;
			}
			numList.set(i, num);
		}

		map.put("num", numList);
		map.put("date", dateList);
		return map;
	}

	/**
	 * 点子总数走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountIdeaTotal(long caseId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `case_count_idea_total` where `case_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId, st_, et_);
		Map<Integer, Integer> numMap = new HashMap<>(list.size());
		for (Map<String, Object> data : list) {
			numMap.put(StringUtil.getInt(data.get("date")), StringUtil.getInt(data.get("total")));
		}

		List<String> dateList = new ArrayList<>();
		List<Integer> numList = new ArrayList<>();
		for (int i = st; i <= et; i = i + 86400) {
			dateList.add(Time.date("yyyy-MM-dd", i));
			numList.add(0);
		}

		Map<String, Object> map = new HashMap<>(2);

		int size = dateList.size();
		for (int i = 0; i < size; i++) {
			int num = StringUtil.getInt(numMap.get(StringUtil.getInt(dateList.get(i).replace("-", ""))));
			if (num <= 0) {
				continue;
			}
			numList.set(i, num);
		}

		map.put("num", numList);
		map.put("date", dateList);
		return map;
	}

	/**
	 * 专案取消关注走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountUnFocus(long caseId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `case_count_unfocus` where `case_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId, st_, et_);
		Map<Integer, Integer> numMap = new HashMap<>(list.size());
		for (Map<String, Object> data : list) {
			numMap.put(StringUtil.getInt(data.get("date")), countMap(data));
		}

		List<String> dateList = new ArrayList<>();
		List<Integer> numList = new ArrayList<>();
		for (int i = st; i <= et; i = i + 86400) {
			dateList.add(Time.date("yyyy-MM-dd", i));
			numList.add(0);
		}

		Map<String, Object> map = new HashMap<>(2);

		int size = dateList.size();
		for (int i = 0; i < size; i++) {
			int num = StringUtil.getInt(numMap.get(StringUtil.getInt(dateList.get(i).replace("-", ""))));
			if (num <= 0) {
				continue;
			}
			numList.set(i, num);
		}

		map.put("num", numList);
		map.put("date", dateList);
		return map;
	}

	/**
	 * 专案关注总数走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountFocusTotal(long caseId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `case_count_focus_total` where `case_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, caseId, st_, et_);
		Map<Integer, Integer> numMap = new HashMap<>(list.size());
		for (Map<String, Object> data : list) {
			numMap.put(StringUtil.getInt(data.get("date")), StringUtil.getInt(data.get("total")));
		}

		List<String> dateList = new ArrayList<>();
		List<Integer> numList = new ArrayList<>();
		for (int i = st; i <= et; i = i + 86400) {
			dateList.add(Time.date("yyyy-MM-dd", i));
			numList.add(0);
		}

		Map<String, Object> map = new HashMap<>(2);

		int size = dateList.size();
		for (int i = 0; i < size; i++) {
			int num = StringUtil.getInt(numMap.get(StringUtil.getInt(dateList.get(i).replace("-", ""))));
			if (num <= 0) {
				continue;
			}
			numList.set(i, num);
		}

		map.put("num", numList);
		map.put("date", dateList);
		return map;
	}

	/**
	 * 计算map的总和
	 */
	private int countMap(Map<String, Object> map) {
		int total = 0;
		for (Entry<String, Object> e : map.entrySet()) {
			if (!e.getKey().matches("^h\\d+$")) {
				continue;
			}
			total += StringUtil.getInt(e.getValue());
		}
		return total;
	}
}