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
import com.gu.dao.model.CompanyCountResult;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DUser;

/**
 * 企业粉丝统计专用dao类
 * @author luo
 */
@Component
public class CompanyCountDao {
	@Autowired
	private GuLogDbService guLogDbService;
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private DistrictService districtService;

	/**
	 * 关注企业用户城市统计
	 * @param companyId 专案id
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 */
	public void addCompanyCountCity(long companyId, long provinceId, long cityId) {
		if (provinceId <= 0 || cityId <= 0) {
			return;
		}
		String sql = "insert into `company_count_city` (`company_id`,`province_id`,`city_id`,`count`) values (?,?,?,1)  on duplicate key update `count`=`count`+1";
		guLogDbService.update(sql, companyId, provinceId, cityId);
	}

	/**
	 * 关注企业用户省份统计
	 * @param companyId 专案id
	 * @param provinceId 省份id
	 */
	public void addCompanyCountProvince(long companyId, long provinceId) {
		if (provinceId <= 0) {
			return;
		}
		String sql = "insert into `company_count_province` (`company_id`,`province_id`,`count`) values (?,?,1)  on duplicate key update `count`=`count`+1";
		guLogDbService.update(sql, companyId, provinceId);
	}

	/**
	 * 关注企业用户性别统计
	 * @param companyId 专案id
	 * @param sex 性别 1=男 2=女
	 */
	public void addCompanyCountSex(long companyId, int sex) {
		String sql_boy = "insert into `company_count_sex` (`company_id`,`boy`) values (?,1)  on duplicate key update `boy`=`boy`+1";
		String sql_girl = "insert into `company_count_sex` (`company_id`,`girl`) values (?,1)  on duplicate key update `girl`=`girl`+1";
		if (sex == 1) {
			guLogDbService.update(sql_boy, companyId);
		} else if (sex == 2) {
			guLogDbService.update(sql_girl, companyId);
		}
	}

	/**
	 * 关注企业实名认证统计
	 * @param companyId 专案id
	 * @param user 用户对象
	 */
	public void addCompanyCountProve(long companyId, DUser user) {
		String sql_prove = "insert into `company_count_prove` (`company_id`,`prove`) values (?,1)  on duplicate key update `prove`=`prove`+1";
		String sql_un_prove = "insert into `company_count_prove` (`company_id`,`un_prove`) values (?,1)  on duplicate key update `un_prove`=`un_prove`+1";
		if (user.getIsProve() == 1) {
			guLogDbService.update(sql_prove, companyId);
		} else {
			guLogDbService.update(sql_un_prove, companyId);
		}
	}

	/**
	 * 关注企业年龄段统计
	 * @param companyId 专案id
	 * @param user 用户对象
	 */
	public void addCompanyCountAge(long companyId, DUser user) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `company_count_age` (`company_id`");
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
		guLogDbService.update(sql.toString(), companyId);
	}

	/**
	 * 添加专案关注统计
	 * @param companyId 专案id
	 */
	public void addCompanyCountFocus(long companyId) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `company_count_focus` (`date`,`company_id`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("',?,'1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString(), companyId);
	}

	/**
	 * 添加企业点子统计
	 * @param companyId 专案id
	 */
	public void addCompanyCountIdea(long companyId) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `company_count_idea` (`date`,`company_id`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("',?,'1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString(), companyId);
	}

	/**
	 * 添加专案取消关注统计
	 * @param companyId 专案id	
	 */
	public void addCompanyCountUnFocus(long companyId) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `company_count_unfocus` (`date`,`company_id`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("',?,'1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString(), companyId);
	}

	/**
	 * 添加专案关注总数统计
	 * @param company 企业对象
	 */
	public void addCompanyCountFocusTotal(DCompany company, int fansNum) {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `company_count_focus_total` (`date`,`company_id`,`h");
		sql.append(h);
		sql.append("`,`total`) values ('");
		sql.append(date);
		sql.append("',?,?,?) on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`=?,`total`=?");
		guLogDbService.update(sql.toString(), company.getId(), fansNum, fansNum, fansNum, fansNum);
	}

	/**
	 * 减少关注企业用户城市统计
	 * @param companyId 专案id
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 */
	public void subCompanyCountCity(long companyId, long provinceId, long cityId) {
		if (provinceId <= 0 || cityId <= 0) {
			return;
		}
		String sql = "update `company_count_city` set `count`=`count`-1 where `company_id`=? and `province_id`=? and `city_id`=?";
		guLogDbService.update(sql, companyId, provinceId, cityId);
	}

	/**
	 * 减少关注企业用户省份统计
	 * @param companyId 专案id
	 * @param provinceId 省份id
	 */
	public void subCompanyCountProvince(long companyId, long provinceId) {
		if (provinceId <= 0) {
			return;
		}
		String sql = "update `company_count_province` set `count`=`count`-1 where `company_id`=? and `province_id`=?";
		guLogDbService.update(sql, companyId, provinceId);
	}

	/**
	 * 减少关注企业用户性别统计
	 * @param companyId 专案id
	 * @param sex 性别 1=男 2=女
	 */
	public void subCompanyCountSex(long companyId, int sex) {
		String sql_boy = "update `company_count_sex` set `boy`=`boy`-1 where `company_id`=?";
		String sql_girl = "update `company_count_sex` set `girl`=`girl`-1 where `company_id`=?";
		if (sex == 1) {
			guLogDbService.update(sql_boy, companyId);
		} else if (sex == 2) {
			guLogDbService.update(sql_girl, companyId);
		}
	}

	/**
	 * 减少关注企业实名认证统计
	 * @param companyId 专案id
	 * @param user 用户对象
	 */
	public void subCompanyCountProve(long companyId, DUser user) {
		String sql_prove = "update `company_count_prove` set `prove`=`prove`-1 where `company_id`=?";
		String sql_un_prove = "update `company_count_prove` set `un_prove`=`un_prove`-1 where `company_id`=?";
		if (user.getIsProve() == 1) {
			guLogDbService.update(sql_prove, companyId);
		} else {
			guLogDbService.update(sql_un_prove, companyId);
		}
	}

	/**
	 * 减少关注企业年龄段统计
	 * @param companyId 专案id
	 * @param user 用户对象
	 */
	public void subCompanyCountAge(long companyId, DUser user) {
		StringBuilder sql = new StringBuilder();
		sql.append("update `company_count_age` set ");
		int year = StringUtil.getInt(Time.date("yyyy", user.getBirthday()));
		if (year < 1970) {
			//70前
			sql.append("`before70`=`before70`-1");
		} else if (year < 1980) {
			//70后
			sql.append("`since70`=`since70`-1");
		} else if (year < 1985) {
			//80后
			sql.append("`since80`=`since80`-1");
		} else if (year < 1990) {
			//85后
			sql.append("`since85`=`since85`-1");
		} else if (year < 1995) {
			//90后
			sql.append("`since90`=`since90`-1");
		} else if (year < 2000) {
			//95后
			sql.append("`since95`=`since95`-1");
		} else {
			//00后
			sql.append("`since00`=`since00`-1");
		}

		sql.append(" where `company_id`=?");
		guLogDbService.update(sql.toString(), companyId);
	}

	/**
	 * 获取关注企业性别统计
	 * @param companyId 专案id
	 */
	public Map<String, Object> getCompanyCountSex(long companyId) {
		String sql = "SELECT * FROM  `company_count_sex` where `company_id`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId);
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
	 * 获取关注企业实名认证统计
	 * @param companyId 专案id
	 */
	public Map<String, Object> getCompanyCountProve(long companyId) {
		String sql = "SELECT * FROM  `company_count_prove` where `company_id`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId);
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
	 * 获取关注企业年龄段统计
	 * @param companyId 专案id
	 */
	public Map<String, Object> getCompanyCountAge(long companyId) {
		String sql = "SELECT * FROM  `company_count_age` where `company_id`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId);
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
	 * 获取专案关注统计
	 * @param companyId 专案id
	 */
	public CompanyCountResult getCompanyCountResult(long companyId) {
		int now = Time.now();
		int today = StringUtil.getInt(Time.date("yyyyMMdd", now));
		int yesterday = StringUtil.getInt(Time.date("yyyyMMdd", now - 86400));
		CompanyCountResult companyCountFocus = new CompanyCountResult();

		// 关注数
		String sqlFocus = "SELECT * FROM  `company_count_focus` where `company_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> focusList = guLogDbService.queryList(sqlFocus, companyId, today, yesterday);
		for (Map<String, Object> map : focusList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				companyCountFocus.setTodayFocus(countMap(map));
			}
			if (date == yesterday) {
				companyCountFocus.setYesterdayFocus(countMap(map));
				double rate = companyCountFocus.getTodayFocus() * 100.0 / companyCountFocus.getYesterdayFocus() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				companyCountFocus.setTodayFocusGrowthRate(companyCountFocus.getYesterdayFocus() <= 0 ? 100.0 : rate);
			}
		}

		// 取消关注数
		String sqlUnfocus = "SELECT * FROM  `company_count_unfocus` where `company_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> unfocusList = guLogDbService.queryList(sqlUnfocus, companyId, today, yesterday);
		for (Map<String, Object> map : unfocusList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				companyCountFocus.setTodayUnfocus(countMap(map));
			}
			if (date == yesterday) {
				companyCountFocus.setYesterdayUnfocus(countMap(map));
				double rate = companyCountFocus.getTodayUnfocus() * 100.0 / companyCountFocus.getYesterdayUnfocus() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				companyCountFocus.setTodayUnfocusGrowthRate(companyCountFocus.getYesterdayUnfocus() <= 0 ? 100.0 : rate);
			}
		}

		// 关注总数
		String sqlTotalFocus = "SELECT * FROM  `company_count_focus_total` where `company_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> totalfocusList = guLogDbService.queryList(sqlTotalFocus, companyId, today, yesterday);
		boolean noToday = false;
		for (Map<String, Object> map : totalfocusList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				noToday = true;
				companyCountFocus.setTodayTotalFocus(StringUtil.getInt(map.get("total")));
			}
			if (date == yesterday) {
				companyCountFocus.setYesterdayTotalFocus(StringUtil.getInt(map.get("total")));
				double rate = companyCountFocus.getTodayTotalFocus() * 100.0 / companyCountFocus.getYesterdayTotalFocus() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				companyCountFocus.setTodayTotalFocusGrowthRate(companyCountFocus.getYesterdayTotalFocus() <= 0 ? 100.0 : rate);
			}
		}
		if (!noToday) {
			String sql = "select `fans_num` from `company` where `id`=?";
			int fansNum = guMasterDbService.queryInt(sql, companyId);
			sql = "insert into `company_count_focus_total` (`date`,`company_id`,`total`) values (?,?,?)";
			guLogDbService.update(sql, today, companyId, fansNum);

			companyCountFocus.setTodayTotalFocus(fansNum);
			double rate = companyCountFocus.getTodayTotalFocus() * 100.0 / companyCountFocus.getYesterdayTotalFocus() - 100;
			rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
			companyCountFocus.setTodayTotalFocusGrowthRate(companyCountFocus.getYesterdayTotalFocus() <= 0 ? 100.0 : rate);
		}

		// 净增数
		companyCountFocus.setTodayGrowth(companyCountFocus.getTodayFocus() - companyCountFocus.getTodayUnfocus());
		companyCountFocus.setYesterdayGrowth(companyCountFocus.getYesterdayFocus() - companyCountFocus.getYesterdayUnfocus());
		double rate = companyCountFocus.getTodayGrowth() * 100.0 / companyCountFocus.getYesterdayGrowth() - 100;
		rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
		companyCountFocus.setTodayGrowthRate(companyCountFocus.getYesterdayGrowth() <= 0 ? 100.0 : rate);

		// 企业点子数
		String sqlIdea = "SELECT * FROM  `company_count_idea` where `company_id`=? and (`date`=? or `date`=?) order by `date` desc";
		List<Map<String, Object>> ideaList = guLogDbService.queryList(sqlIdea, companyId, today, yesterday);
		for (Map<String, Object> map : ideaList) {
			int date = StringUtil.getInt(map.get("date"));
			if (date == today) {
				companyCountFocus.setTodayIdeaNum(countMap(map));
			}
			if (date == yesterday) {
				companyCountFocus.setYesterdayIdeaNum(countMap(map));
				rate = companyCountFocus.getTodayIdeaNum() * 100.0 / companyCountFocus.getYesterdayIdeaNum() - 100;
				rate = StringUtil.getDouble(new DecimalFormat("0.00").format(rate));
				companyCountFocus.setTodayIdeaNumGrowthRate(companyCountFocus.getYesterdayIdeaNum() <= 0 ? 100.0 : rate);
			}
		}
		return companyCountFocus;
	}

	/**
	 * 获取专案用户省份统计
	 * @param companyId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public Map<String, Object> getCompanyCountProvince(long companyId, int page, int pageSize) {
		String sql = "SELECT * FROM  `company_count_province` where `company_id`=? order by `count` desc";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId);
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
				data.remove("company_id");
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
	 * @param companyId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public Map<String, Object> getCompanyCountCity(long companyId, int page, int pageSize) {
		String sql = "SELECT * FROM  `company_count_city` where `company_id`=? order by `count` desc";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId);
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
	 * 专案新增关注走势
	 * @author ruan 
	 * @param companyId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountFocusNew(long companyId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `company_count_focus` where `company_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId, st_, et_);
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
	 * 专案点子数走势
	 * @author ruan 
	 * @param companyId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountIdea(long companyId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `company_count_idea` where `company_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId, st_, et_);
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
	 * 专案取消关注走势
	 * @author ruan 
	 * @param companyId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountUnFocus(long companyId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `company_count_unfocus` where `company_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId, st_, et_);
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
	 * @param companyId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountFocusTotal(long companyId, int st, int et) {
		String st_ = Time.date("yyyyMMdd", st);
		String et_ = Time.date("yyyyMMdd", et);
		String sql = "SELECT * FROM  `company_count_focus_total` where `company_id` =? and `date` >= ? and `date` <=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, companyId, st_, et_);
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
	 * 专案净增关注走势
	 * @author ruan 
	 * @param companyId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCompanyCountGrowth(long companyId, int st, int et) {
		Map<String, Object> focus = getCompanyCountFocusNew(companyId, st, et);
		Map<String, Object> unfocus = getCompanyCountUnFocus(companyId, st, et);

		List<Integer> numList = new ArrayList<>();
		for (int i = st; i <= et; i = i + 86400) {
			numList.add(0);
		}

		List<Integer> focusList = (List<Integer>) focus.get("num");
		List<Integer> unfocusList = (List<Integer>) unfocus.get("num");

		int size = numList.size();
		for (int i = 0; i < size; i++) {
			int focusNum = focusList.get(i);
			int unfocusNum = unfocusList.get(i);
			numList.set(i, focusNum - unfocusNum);
		}

		Map<String, Object> map = new HashMap<>(2);
		map.put("num", numList);
		map.put("date", focus.get("date"));
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