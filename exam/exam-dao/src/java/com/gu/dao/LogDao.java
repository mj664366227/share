package com.gu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.gu.core.enums.OpenPlatform;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.db.GuLogDbService;
import com.gu.dao.model.DCaseFinishBonusCommonUserLog;
import com.gu.dao.model.DCaseFinishBonusLog;
import com.gu.dao.model.DCaseFinishBonusPrizeUserLog;
import com.gu.dao.model.DCaseFinishBonusUserLog;
import com.gu.dao.model.DCrashLog;
import com.gu.dao.model.DUserFeedback;

/**
 * 日志dao
 * @author ruan
 */
@Component
public class LogDao {
	private final static Logger logger = LoggerFactory.getLogger(LogDao.class);
	@Autowired
	private GuLogDbService guLogDbService;

	/**
	 * 记录闪退日志
	 * @param userId 用户id 
	 * @param version app版本号
	 * @param mobileType 手机机型
	 * @param os 操作系统
	 * @param content 闪退日志
	 */
	public void crashLog(long userId, String version, String mobileType, String os, String content) {
		DCrashLog crashLog = new DCrashLog();
		crashLog.setUserId(userId);
		crashLog.setVersion(version);
		crashLog.setMobileType(mobileType);
		crashLog.setContent(content);
		crashLog.setResolve(0);
		crashLog.setOs(os);
		crashLog.setCreateTime(Time.now());
		crashLog.setResolveTime(0);
		guLogDbService.save(crashLog);
	}

	/**
	 * app启动次数统计
	 */
	public void recordStart() {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `start` (`date`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("','1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString());
	}

	/**
	 * 启动人数统计(分时)
	 * @param now 当前时间
	 */
	public void recordStartUser(int now) {
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `start_user` (`date`,`h");
		sql.append(h);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("','1') on duplicate key update `h");
		sql.append(h);
		sql.append("`=`h");
		sql.append(h);
		sql.append("`+1");
		guLogDbService.update(sql.toString());
	}

	/**
	 * 今天启动总人数统计
	 * @param now 当前时间
	 */
	public void recordStartTotalUser(int now) {
		String date = Time.date("yyyyMMdd", now);
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `start_user` (`date`,`total`) values ('");
		sql.append(date);
		sql.append("','1') on duplicate key update `total`=`total`+1");
		guLogDbService.update(sql.toString());
	}

	/**
	 * 今天某用户启动总次数统计
	 * @param now 当前时间
	 * @param userId 用户id
	 */
	public void recordStartUserTimes(int now, long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `start_distribution_log`(`date`, `user_id`, `start_times`) ");
		sql.append(" VALUES (?,?,1) on duplicate key update `start_times`=`start_times`+1 ");
		guLogDbService.update(sql.toString(), Time.date("yyyyMMdd", now), userId);
	}

	/**
	 * 汇总昨天启动人数分布(每天凌晨4点执行)
	 */
	public void summaryStartUserTimes() {
		int page = 1;
		int pageSize = 1000;
		int now = Time.now();
		int date = StringUtil.getInt(Time.date("yyyyMMdd", now - 86400));
		String sql = "select user_id,start_times from `start_distribution_log` where `date` = ? limit ?,?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, date, (page - 1) * pageSize, pageSize);
		boolean isFirstEmpty = list == null || list.isEmpty();

		Map<String, Integer> startDistribution = new HashMap<>(6);
		if (list != null) {
			while (!list.isEmpty()) {
				for (Map<String, Object> data : list) {
					int times = StringUtil.getInt(data.get("start_times"));
					String startRange = getStartRange(times);
					startDistribution.put(startRange, StringUtil.getInt(startDistribution.get(startRange)) + 1);
				}
				page = page + 1;
				list = guLogDbService.queryList(sql, date, (page - 1) * pageSize, pageSize);
				if (list == null) {
					list = Lists.newArrayList();
				}
			}
		}

		logger.warn("start distribution: {}", startDistribution.toString());

		// 汇总数据
		StringBuilder summarySql = new StringBuilder();
		summarySql.append("INSERT INTO `start_distribution` ");
		summarySql.append(" (`date`, ");
		for (Entry<String, Integer> e : startDistribution.entrySet()) {
			summarySql.append("`");
			summarySql.append(e.getKey());
			summarySql.append("`,");
		}
		int len = summarySql.length();
		summarySql.delete(len - (isFirstEmpty ? 2 : 1), len);
		summarySql.append(") VALUES ( ");
		summarySql.append("'");
		summarySql.append(date);
		summarySql.append("',");
		for (Entry<String, Integer> e : startDistribution.entrySet()) {
			summarySql.append("'");
			summarySql.append(e.getValue());
			summarySql.append("',");
		}
		len = summarySql.length();
		summarySql.delete(len - 1, len);
		summarySql.append(")");
		guLogDbService.update(summarySql.toString());

		// 删除7天前的数据数据
		summarySql.delete(0, len);
		summarySql.append("delete from `start_distribution_log` where `date`=?");
		guLogDbService.update(summarySql.toString(), Time.date("yyyyMMdd", now - 86400 * 7));
	}

	/**
	 * 获取启动次数范围
	 * @author ruan 
	 * @param times 启动次数
	 */
	private String getStartRange(long times) {
		if (times <= 2 && times >= 1) {
			return "range1_2";
		} else if (times <= 5 && times >= 3) {
			return "range3_5";
		} else if (times <= 9 && times >= 6) {
			return "range6_9";
		} else if (times <= 19 && times >= 10) {
			return "range10_19";
		} else if (times <= 49 && times >= 20) {
			return "range20_49";
		} else {
			return "range50plus";
		}
	}

	/**
	 * 统计之前30天的用户留存率
	 */
	public void addUserRemain() {
		//今天不统计,从昨天开始
		long yesterdayBegin = Time.dayBegin(Time.now(true), -1);
		long yesterdayEnd = yesterdayBegin + (86399 * 1000);

		String[] insert_or_update = new String[30];

		for (int i = 1; i < 31; i++) {
			long dayBegin = Time.dayBegin(yesterdayBegin, -i);
			long dayEnd = dayBegin + (86399 * 1000);
			int date = Time.intDate(dayBegin);

			String reg_num_sql = "SELECT count(distinct `user_id`) FROM `user_logintime_log` WHERE `reg_time`>=? and `reg_time`<=?";
			String login_count_for_yesterdat_sql = "SELECT count(distinct `user_id`) FROM `user_logintime_log` WHERE `reg_time`>=? and `reg_time`<=? and `login_time`>=? and `login_time`<=?";

			int reg_num = guLogDbService.queryInt(reg_num_sql, dayBegin / 1000, dayEnd / 1000);
			int login_count = guLogDbService.queryInt(login_count_for_yesterdat_sql, dayBegin / 1000, dayEnd / 1000, yesterdayBegin / 1000, yesterdayEnd / 1000);

			StringBuilder insert_or_update_sql = new StringBuilder();
			insert_or_update_sql.append("INSERT INTO `user_day_remain`(`date`, `reg_num`, `d");
			insert_or_update_sql.append(i);
			insert_or_update_sql.append("`) VALUES (");
			insert_or_update_sql.append(date);
			insert_or_update_sql.append(",");
			insert_or_update_sql.append(reg_num);
			insert_or_update_sql.append(",");
			insert_or_update_sql.append(login_count);
			insert_or_update_sql.append(") on duplicate key update `reg_num`=");
			insert_or_update_sql.append(reg_num);
			insert_or_update_sql.append(",`d");
			insert_or_update_sql.append(i);
			insert_or_update_sql.append("`=");
			insert_or_update_sql.append(login_count);

			insert_or_update[i - 1] = insert_or_update_sql.toString();
		}
		guLogDbService.batchUpdate(insert_or_update);
	}

	/**
	 * 统计之前30天的企业留存率
	 */
	public void addCompanyRemain() {
		//今天不统计,从昨天开始
		long yesterdayBegin = Time.dayBegin(Time.now(true), -1);
		long yesterdayEnd = yesterdayBegin + (86399 * 1000);

		String[] insert_or_update = new String[30];

		for (int i = 1; i < 31; i++) {
			long dayBegin = Time.dayBegin(yesterdayBegin, -i);
			long dayEnd = dayBegin + (86399 * 1000);
			int date = Time.intDate(dayBegin);

			String reg_num_sql = "SELECT count(distinct `company_id`) FROM `company_logintime_log` WHERE `reg_time`>=? and `reg_time`<=?";
			String login_count_for_yesterdat_sql = "SELECT count(distinct `company_id`) FROM `company_logintime_log` WHERE `reg_time`>=? and `reg_time`<=? and `login_time`>=? and `login_time`<=?";

			int reg_num = guLogDbService.queryInt(reg_num_sql, dayBegin / 1000, dayEnd / 1000);
			int login_count = guLogDbService.queryInt(login_count_for_yesterdat_sql, dayBegin / 1000, dayEnd / 1000, yesterdayBegin / 1000, yesterdayEnd / 1000);

			StringBuilder insert_or_update_sql = new StringBuilder();
			insert_or_update_sql.append("INSERT INTO `company_day_remain`(`date`, `reg_num`, `d");
			insert_or_update_sql.append(i);
			insert_or_update_sql.append("`) VALUES (");
			insert_or_update_sql.append(date);
			insert_or_update_sql.append(",");
			insert_or_update_sql.append(reg_num);
			insert_or_update_sql.append(",");
			insert_or_update_sql.append(login_count);
			insert_or_update_sql.append(") on duplicate key update `reg_num`=");
			insert_or_update_sql.append(reg_num);
			insert_or_update_sql.append(",`d");
			insert_or_update_sql.append(i);
			insert_or_update_sql.append("`=");
			insert_or_update_sql.append(login_count);

			insert_or_update[i - 1] = insert_or_update_sql.toString();
		}
		guLogDbService.batchUpdate(insert_or_update);
	}

	/**
	 * 保存专案分红整体数据
	 * @param caseFinishBonusLog
	 */
	public void saveCaseFinishBonusLog(DCaseFinishBonusLog caseFinishBonusLog) {
		guLogDbService.save(caseFinishBonusLog);
	}

	/**
	 * 保存专案分红普通参与用户数据
	 * @param bonusCommonUserLog
	 */
	public void saveCaseFinishBonusCommonUserLog(DCaseFinishBonusCommonUserLog bonusCommonUserLog) {
		guLogDbService.save(bonusCommonUserLog);
	}

	/**
	 * 保存专案分红创意获奖用户数据
	 * @param bonusPrizeUserLog
	 */
	public void saveCaseFinishBonusPrizeUserLog(DCaseFinishBonusPrizeUserLog bonusPrizeUserLog) {
		guLogDbService.save(bonusPrizeUserLog);
	}

	/**
	 * 查询专案分红创意获奖用户数据
	 * @param caseId
	 * @return
	 */
	public List<Map<String, Object>> getBonusPrizeUser(long caseId) {
		String sql = "SELECT `user_id`, `bonus_points`, `create_time`, `bonus_name` FROM `case_finish_bonus_prize_user_log` WHERE `case_id`=? and `bonus_points`>0 order by `bonus_points` desc";
		return guLogDbService.queryList(sql, caseId);
	}

	/**
	 * 查询专案分红创意获奖用户数据
	 * @param caseId
	 * @param bonusName
	 * @return
	 */
	public List<Map<String, Object>> getBonusPrizeUser(long caseId, String bonusName) {
		String sql = "SELECT `user_id`, `bonus_points`, `create_time`, `bonus_name` FROM `case_finish_bonus_prize_user_log` WHERE `case_id`=? and `bonus_name`=? and `bonus_points`>0 order by `bonus_points` desc";
		return guLogDbService.queryList(sql, caseId, bonusName);
	}

	/**
	 * 查询专案分红普通参与用户数据
	 * @param caseId
	 * @return
	 */
	public List<Map<String, Object>> getBonusCommonUser(long caseId) {
		String sql = "SELECT `bonus_points_average`, `create_time`, `bonus_name`, `user_id_list` FROM `case_finish_bonus_common_user_log` WHERE `case_id`=? order by `bonus_points_average` desc";
		return guLogDbService.queryList(sql, caseId);
	}

	/**
	 * 查询专案分红普通参与用户数据
	 * @param caseId
	 * @param bonusName
	 * @return
	 */
	public List<Map<String, Object>> getBonusCommonUser(long caseId, String bonusName) {
		String sql = "SELECT `bonus_points_average`, `create_time`, `bonus_name`, `user_id_list` FROM `case_finish_bonus_common_user_log` WHERE `case_id`=? and `bonus_name`=? order by `bonus_points_average` desc";
		return guLogDbService.queryList(sql, caseId, bonusName);
	}

	/**
	 * 查询用户意见反馈列表
	 * @return
	 */
	public List<DUserFeedback> getUserFeedbackList() {
		String sql = "SELECT * FROM `user_feedback` order by `id` desc";
		return guLogDbService.queryList(sql, DUserFeedback.class);
	}

	/**
	 * 分享统计
	 * @author ruan 
	 * @param openPlatform 第三方平台
	 */
	public void addShareStatistics(OpenPlatform openPlatform) {
		String date = Time.date("yyyyMMdd");
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `share_statistics` ");
		sql.append("(`date`,`platform`,`share_times`,`share_clicks`,`down_nums`) ");
		sql.append(" values ('");
		sql.append(date);
		sql.append("','");
		sql.append(openPlatform.getValue());
		sql.append("','1','0','0') ");
		sql.append(" on duplicate key update `share_times`=`share_times`+1");
		guLogDbService.update(sql.toString());
	}

	/**
	 * 分享点击数统计
	 * @param openPlatform 第三方平台
	 */
	public void addShareClicksStatistics(OpenPlatform openPlatform) {
		String date = Time.date("yyyyMMdd");
		String sql = "update `share_statistics` set `share_clicks`=`share_clicks`+1 where `date`=? and `platform`=?";
		guLogDbService.update(sql, date, openPlatform.getValue());
	}

	/**
	 * 分享下载数统计
	 * @param openPlatform 第三方平台
	 */
	public void addShareDownloadStatistics(OpenPlatform openPlatform) {
		String date = Time.date("yyyyMMdd");
		String sql = "update `share_statistics` set `down_nums`=`down_nums`+1 where `date`=? and `platform`=?";
		guLogDbService.update(sql, date, openPlatform.getValue());
	}

	/**
	 * 保存按用户合并的分红记录
	 * @param caseFinishBonusUserLog
	 */
	public DCaseFinishBonusUserLog saveCaseFinishBonusUserLog(DCaseFinishBonusUserLog caseFinishBonusUserLog) {
		return guLogDbService.save(caseFinishBonusUserLog);
	}

	/**
	 * 查询按用户合并的分红记录
	 * @param caseId
	 * @return
	 */
	public List<DCaseFinishBonusUserLog> getBonuUserLogList(long caseId) {
		String sql = "SELECT * FROM `case_finish_bonus_user_log` WHERE `case_id`=? order by `bonus_points` desc";
		return guLogDbService.queryList(sql, DCaseFinishBonusUserLog.class, caseId);
	}

	/**
	 * 批量获取
	 * @param tmpBonusUserLogIdSet
	 * @return
	 */
	public List<DCaseFinishBonusUserLog> getBonusUserLog(Set<Long> tmpBonusUserLogIdSet) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from `case_finish_bonus_user_log` where `id` in (");
		sql.append(Joiner.on(",").join(tmpBonusUserLogIdSet));
		sql.append(") order by `bonus_points` desc");
		return guLogDbService.queryList(sql.toString(), DCaseFinishBonusUserLog.class);
	}

	public List<DCaseFinishBonusPrizeUserLog> BonusPrizeUserLogListByCaseId(long caseId) {
		String sql = "SELECT * FROM `case_finish_bonus_prize_user_log` WHERE `case_id`=? order by `bonus_points` desc";
		return guLogDbService.queryList(sql, DCaseFinishBonusPrizeUserLog.class, caseId);
	}

	public List<DCaseFinishBonusCommonUserLog> BonusCommonUserLogListByCaseId(long caseId) {
		String sql = "SELECT * FROM `case_finish_bonus_common_user_log` WHERE `case_id`=? order by `bonus_name`";
		return guLogDbService.queryList(sql, DCaseFinishBonusCommonUserLog.class, caseId);
	}
}