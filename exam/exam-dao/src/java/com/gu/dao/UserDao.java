package com.gu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.gu.core.enums.Platform;
import com.gu.core.enums.Sex;
import com.gu.core.enums.UserPointsAddEvent;
import com.gu.core.enums.UserPointsSubEvent;
import com.gu.core.enums.UserScoreAddEvent;
import com.gu.core.enums.UserScoreSubEvent;
import com.gu.core.util.MathUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.db.GuConfigDbService;
import com.gu.dao.db.GuLogDbService;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DFocusCompany;
import com.gu.dao.model.DFocusUser;
import com.gu.dao.model.DStoreMobile;
import com.gu.dao.model.DUser;
import com.gu.dao.model.DUserAccusation;
import com.gu.dao.model.DUserFeedback;
import com.gu.dao.model.DUserProve;
import com.gu.dao.model.DUserSetting;

/**
 * 用户dao类
 * @author luo
 */
@Component
public class UserDao {
	private final static Logger logger = LoggerFactory.getLogger(UserDao.class);
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;
	@Autowired
	private GuLogDbService guLogDbService;
	@Autowired
	private GuConfigDbService guConfigbService;

	/**
	 * 获取经验等级配置
	 * @author ruan 
	 */
	public Map<Integer, Integer> getLevelExpConfig() {
		String sql = "SELECT * FROM `level_exp_config` order by `level` asc";
		List<Map<String, Object>> list = guConfigbService.queryList(sql);
		Map<Integer, Integer> data = new LinkedHashMap<Integer, Integer>(list.size());
		for (Map<String, Object> map : list) {
			data.put(StringUtil.getInt(map.get("level")), StringUtil.getInt(map.get("exp")));
		}
		return data;
	}

	/**
	 * 新增下一级的经验配置
	 * @param needExp 所需经验
	 */
	public void addLevelExpConfig(int needExp) {
		String sql = "INSERT INTO `level_exp_config`(`level`, `exp`) VALUES (?,?)";
		guConfigbService.update(sql, getLevelExpConfig().size() + 1, needExp);
	}

	/**
	 * 删除等级配置
	 * @param level 等级
	 */
	public boolean deleteLevelExpConfig(int level) {
		if (level != getLevelExpConfig().size()) {
			return false;
		}
		String sql = "delete from `level_exp_config` where `level`=?";
		return guConfigbService.update(sql, level);
	}

	/**
	 * 获取新增一句话亮身份配置
	 */
	public List<String> getUserIdentityConfig() {
		String sql = "SELECT * FROM `user_identity_config` order by `id` asc";
		List<Map<String, Object>> list = guConfigbService.queryList(sql);
		List<String> data = new ArrayList<String>(list.size());
		for (Map<String, Object> map : list) {
			data.add(StringUtil.getString(map.get("content")));
		}
		return data;
	}

	/**
	 * 新增一句话亮身份配置
	 */
	public void addUserIdentityConfig(String identity) {
		String sql = "INSERT INTO `user_identity_config`( `content`) VALUES (?)";
		guConfigbService.update(sql, identity);
	}

	/**
	 * 删除一句话亮身份配置
	 */
	public void deleteUserIdentityConfig(String identity) {
		String sql = "delete from `user_identity_config` where  `content`=?";
		guConfigbService.update(sql, identity);
	}

	/**
	 * 判断一句话亮身份配置是否重复
	 */
	public boolean existsUserIdentityConfig(String identity) {
		String sql = "select * from `user_identity_config` where  `content`=?";
		return !guConfigbService.queryList(sql, identity).isEmpty();
	}

	/**
	 * 添加用户方法(手机)(id自动生成, level等级1, 其他属性""或0)
	 * @param nickname 用户昵称
	 * @param mobile 手机号
	 * @param sex 性别(男1女2)
	 * @param identity 
	 * @param cityId 
	 * @param provinceId 
	 * @param birthday 
	 * @return
	 */
	public DUser addUserByMobile(String nickname, String mobile, Sex sex, String password, String avatar, String identity, int provinceId, int cityId, int birthday, Platform platform) {
		DUser user = new DUser();
		user.setCreateTime(Time.now());
		user.setNickname(nickname);
		user.setMobile(mobile);
		user.setSex(sex.getValue());
		user.setPassword(password);
		user.setAvatarImage(avatar);
		user.setIdentity(identity);
		user.setProvinceId(provinceId);
		user.setCityId(cityId);
		user.setBirthday(birthday);
		user.setPlatform(platform.getValue());
		return guMasterDbService.save(user);
	}

	/**
	 * 批量获取用户
	 * @param userIdSet 用户id集合
	 */
	public List<DUser> getUser(Set<Long> userIdSet) {
		return guSlaveDbService.multiGetT(userIdSet, DUser.class);
	}

	/**
	 * 修改用户信息
	 * @param user 用户对象
	 */
	public boolean updateUser(DUser user) {
		return guMasterDbService.update(user);
	}

	/**
	 * 昵称唯一性检查
	 * @param nickname 昵称
	 * @return false=已被使用
	 */
	public boolean nicknameOnly(String nickname) {
		return guSlaveDbService.queryList("SELECT `nickname` FROM `user` WHERE `nickname`=?", nickname).isEmpty();
	}

	/**
	 * 手机唯一性检查
	 * @param mobile 手机
	 * @return false=已被使用
	 */
	public boolean mobileOnly(String mobile) {
		return guSlaveDbService.queryList("SELECT `mobile` FROM `user` WHERE `mobile`=?", mobile).isEmpty();
	}

	/**
	 * 预留手机号
	 * @param mobile 手机号码
	 * @param points 赠送点数
	 */
	public DStoreMobile storeMobile(String mobile, int points) {
		DStoreMobile storeMobile = new DStoreMobile();
		storeMobile.setMobile(mobile);
		storeMobile.setPoints(points);
		storeMobile.setCreateTime(Time.now());
		guMasterDbService.save(storeMobile);
		return storeMobile;
	}

	/**
	 * 获取预留手机号
	 * @param mobile 手机号码
	 */
	public DStoreMobile getStoreMobile(String mobile) {
		String sql = "select * from `store_mobile` where mobile=?";
		return guSlaveDbService.queryT(sql, DStoreMobile.class, mobile);
	}

	/**
	 * 删除预留手机记录
	 * @param storeMobile
	 * @return
	 */
	public boolean deleteStoreMobile(DStoreMobile storeMobile) {
		return guMasterDbService.delete(storeMobile);
	}

	/**
	 * 查询用户 By userId
	 * @param userId user表id
	 * @return
	 */
	public DUser getUserById(long userId) {
		return guSlaveDbService.queryT("SELECT * FROM `user` WHERE `id`=?", DUser.class, userId);
	}

	/**
	 * 查询用户 By 手机
	 * @param mobile 用户手机
	 * @return
	 */
	public DUser getUserByMobile(String mobile) {
		return guSlaveDbService.queryT("SELECT * FROM `user` WHERE `mobile`=?", DUser.class, mobile);
	}

	/**
	 * 用户关注企业
	 * @param userId 用户id
	 * @param companyId 企业id
	 */
	public DFocusCompany focusCompany(long userId, long companyId) {
		DFocusCompany focusCompany = new DFocusCompany();
		focusCompany.setUserId(userId);
		focusCompany.setCompanyId(companyId);
		focusCompany.setCreateTime(Time.now(true));
		return guMasterDbService.save(focusCompany);
	}

	/**
	 * 用户取消关注企业
	 * @param userId 用户id
	 * @param companyId 企业id
	 */
	public boolean unfocusCompany(long userId, long companyId) {
		String sql = "delete from `focus_company` where `user_id`=? and `company_id`=?";
		return guMasterDbService.update(sql, userId, companyId);
	}

	/**
	 * 用户关注用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public DFocusUser focusUser(long userId, long friendId) {
		DFocusUser focusUser = new DFocusUser();
		focusUser.setUserId(userId);
		focusUser.setCreateTime(Time.now(true));
		focusUser.setFriendId(friendId);
		return guMasterDbService.save(focusUser);
	}

	/**
	 * 用户取消关注用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public boolean unfocusUser(long userId, long friendId) {
		String sql = "delete from `focus_user` where `user_id`=? and `friend_id`=?";
		return guMasterDbService.update(sql, userId, friendId);
	}

	/**
	 * 判断用户是否关注某用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public DFocusUser isFocus(long userId, long friendId) {
		String sql = "select * from focus_user where user_id=? and friend_id=?";
		return guSlaveDbService.queryT(sql, DFocusUser.class, userId, friendId);
	}

	/**
	 * 我的关注列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)	
	 */
	public List<DFocusUser> getUserFocusList(long userId, int page, int pageSize) {
		String sql = "select * from `focus_user` where `user_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DFocusUser.class, userId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 获取我的粉丝列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)
	 */
	public List<DFocusUser> getUserFansList(long userId, int page, int pageSize) {
		String sql = "select * from `focus_user` where `friend_id`=?  order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DFocusUser.class, userId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 获取用户关注的企业列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)
	 */
	public List<DFocusCompany> getUserFocusCompanyList(long userId, int page, int pageSize) {
		String sql = "select * from `focus_company` where `user_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DFocusCompany.class, userId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 是否已经关注该企业
	 * @param userId 用户id
	 * @param companyId 企业id
	 */
	public DFocusCompany isFocusCompany(long userId, long companyId) {
		String sql = "select * from `focus_company` where `user_id`=? and `company_id`=?";
		return guSlaveDbService.queryT(sql, DFocusCompany.class, userId, userId);
	}

	/**
	 * 用户加钱日志
	 * @param userId 用户id
	 * @param points 加了的点数
	 * @param event 加钱事件
	 */
	public void userPointAddLog(long userId, int points, UserPointsAddEvent event) {
		String sql = "insert into `user_points_add_log`( `user_id`, `points`, `event`, `create_time`) values (?,?,?,?)";
		guLogDbService.update(sql, userId, points, event.getValue(), Time.now());
	}

	/**
	 * 用户扣钱日志
	 * @param userId 用户id
	 * @param points 扣了的点数
	 * @param event 扣钱事件
	 */
	public void userPointSubLog(long userId, int points, UserPointsSubEvent event) {
		String sql = "insert into `user_points_sub_log`( `user_id`, `points`,`event`,`create_time`) values (?,?,?,?)";
		guLogDbService.update(sql, userId, points, event.getValue(), Time.now());
	}

	/**
	 * 用户加积分日志
	 * @param userId 用户id
	 * @param score 加了的积分
	 * @param event 加积分事件
	 */
	public void userScoreAddLog(long userId, int score, UserScoreAddEvent event) {
		String sql = "insert into `user_score_add_log`( `user_id`, `score`, `event`, `create_time`) values (?,?,?,?)";
		guLogDbService.update(sql, userId, score, event.getValue(), Time.now());
	}

	/**
	 * 用户扣积分日志
	 * @param userId 用户id
	 * @param score 扣了的积分
	 * @param event 扣积分事件
	 */
	public void userScoreSubLog(long userId, int score, UserScoreSubEvent event) {
		String sql = "insert into `user_score_sub_log`( `user_id`, `score`,`event`,`create_time`) values (?,?,?,?)";
		guLogDbService.update(sql, userId, score, event.getValue(), Time.now());
	}

	/**
	 * 记录新增用户日志
	 */
	public void newUserLog() {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `new_user` (`date`,`h");
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
	 * 日活跃用户统计
	 * @param date 日期
	 * @param hour 小时
	 */
	public void recordDailyActiveUser(String date, int hour) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `daily_active_user` (`date`,`h");
		sql.append(hour);
		sql.append("`) values ('");
		sql.append(date);
		sql.append("','1') on duplicate key update `h");
		sql.append(hour);
		sql.append("`=`h");
		sql.append(hour);
		sql.append("`+1");
		guLogDbService.update(sql.toString());
	}

	/**
	 * 当前用户总数
	 * @author ruan 
	 */
	public int getTotalUserNum() {
		String sql = "select count(*) from `user`";
		return guSlaveDbService.queryInt(sql);
	}

	/**
	 * 日活跃用户统计
	 * @param date 日期
	 */
	public void recordDailyActiveUserTotal(String date) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into `daily_active_user` (`date`,`total`) values (?,1) ");
		sql.append(" on duplicate key update `total`=`total`+1");
		guLogDbService.update(sql.toString(), date);
	}

	/**
	 * 记录最近7天活跃用户
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public void recordRecent7Active(String startDate, String endDate) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `user_recent7_active`(`start_date`, `end_date`, `num`) VALUES (?,?,'1')");
		sql.append(" on duplicate key update `num`=`num`+1");
		guLogDbService.update(sql.toString(), startDate, endDate);
	}

	/**
	 * 获取某个时间段活跃用户数
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public Map<String, Object> getRecentActive(String startDate, String endDate) {
		int sDate = Time.str2time(startDate, "yyyyMMdd");
		int eDate = Time.str2time(endDate, "yyyyMMdd");
		int day = (eDate - sDate) / 86400 + 1;
		String sql = "select * from `user_recent" + day + "_active` where `start_date`=? and `end_date`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, startDate, endDate);
		if (list == null || list.isEmpty()) {
			return new HashMap<>(0);
		}
		return list.get(0);
	}

	/**
	 * 获取过去n天平均日使用时长
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public double getUseAverage7(String startDate, String endDate) {
		String sql = "SELECT * FROM `use_average` WHERE `start_date`=? and `end_date`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, startDate, endDate);
		if (list == null || list.isEmpty()) {
			return 0.0;
		}

		Map<String, Object> map = list.get(0);
		int userNum = StringUtil.getInt(map.get("user_num"));
		int duration = StringUtil.getInt(map.get("duration"));
		return userNum <= 0 ? 0 : MathUtil.round(duration / userNum, 20);
	}

	/**
	 * 记录最近14天活跃用户
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public void recordRecent14Active(String startDate, String endDate) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `user_recent14_active`(`start_date`, `end_date`, `num`) VALUES (?,?,'1')");
		sql.append(" on duplicate key update `num`=`num`+1");
		guLogDbService.update(sql.toString(), startDate, endDate);
	}

	/**
	 * 获取日活跃率
	 * @author ruan 
	 */
	public Map<String, Object> getDailyActiveRate() {
		int now = Time.now();
		String today = Time.date("yyyyMMdd", now);
		String yesterday = Time.date("yyyyMMdd", now - 86400);
		String sql = "select * from `active_user` where `date`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, yesterday);

		// 昨天的
		Map<String, Object> totalMap = new HashMap<>();
		totalMap.put("today", 0.0);
		totalMap.put("yesterday", 0.0);
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				int regNum = StringUtil.getInt(map.remove("reg_num"));
				int active = StringUtil.getInt(map.remove("active"));
				totalMap.put("yesterday", regNum <= 0 ? 0 : MathUtil.round(active * 100.0 / regNum, 2));
				totalMap.put("yesterdayRegNum", regNum);
				totalMap.put("yesterdayActiveNum", active);
			}
		}

		// 今天的
		int totalUserNum = getTotalUserNum();
		sql = "select `total` from `daily_active_user` where `date`=? order by `date` desc limit 1";
		list = guLogDbService.queryList(sql, today);
		int dailyActiveUser = 0;
		if (list != null && !list.isEmpty()) {
			dailyActiveUser = StringUtil.getInt(list.get(0).get("total"));
		}
		totalMap.put("today", MathUtil.round(dailyActiveUser * 100.0 / totalUserNum, 2));
		totalMap.put("todayRegNum", totalUserNum);
		totalMap.put("todayActiveNum", dailyActiveUser);

		totalMap.put("growthRate", MathUtil.round(StringUtil.getDouble(totalMap.get("today")) - StringUtil.getDouble(totalMap.get("yesterday")), 2));
		return totalMap;
	}

	/**
	 * 记录今天活跃率
	 * @author ruan
	 */
	public void recordDailyActiveRate() {
		String date = Time.date("yyyyMMdd");

		// 今天总活跃数
		String sql = "select `total` from `daily_active_user` where `date`=? order by `date` desc limit 1";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, date);
		int dailyActiveUser = 0;
		if (list != null && !list.isEmpty()) {
			dailyActiveUser = StringUtil.getInt(list.get(0).get("total"));
		}

		// 当前总用户数
		int totalUserNum = getTotalUserNum();

		sql = "INSERT INTO `active_user`(`date`, `reg_num`, `active`) VALUES (?,?,?)";
		guLogDbService.update(sql, date, totalUserNum, dailyActiveUser);
	}

	/**
	 * 用户搜索
	 * @param search 搜索字段
	 * @param lastUserId 用户id游标
	 * @param pageSize 数目
	 */
	public List<DUser> searchByNickame(String search, long lastUserId, int pageSize) {
		String sql = "SELECT * FROM `user` WHERE `nickname` like ? and `id`<? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DUser.class, "%" + search + "%", lastUserId, pageSize);
	}

	/**
	 * 记录用户登录时间
	 * @param user
	 * @return
	 */
	public boolean addLogintime(DUser user) {
		int nowTime = Time.now();
		String sql = "insert into `user_logintime_log`(`user_id`, `reg_time`, `login_time`) values (?,?,?)";
		return guLogDbService.update(sql, user.getId(), user.getCreateTime(), nowTime);
	}

	/**
	 * 保存用户意见反馈
	 * @param userId
	 * @param feedback
	 * @return
	 */
	public DUserFeedback addUserFeedback(long userId, String feedback) {
		DUserFeedback userFeedback = new DUserFeedback();
		userFeedback.setUserId(userId);
		userFeedback.setFeedback(feedback);
		userFeedback.setCreateTime(Time.now());
		return guLogDbService.save(userFeedback);
	}

	/**
	 * 保存用户举报
	 * @param userId
	 * @param type
	 * @param typeId
	 * @return
	 */
	public DUserAccusation addUserAccusation(long userId, int type, int typeId, String content) {
		DUserAccusation userAccusation = new DUserAccusation();
		userAccusation.setUserId(userId);
		userAccusation.setType(type);
		userAccusation.setTypeId(typeId);
		userAccusation.setCreateTime(Time.now());
		userAccusation.setContent(content);
		return guLogDbService.save(userAccusation);
	}

	public DUserProve addUserProve(long userId, String proveInfo, String identityCard, String otherCard) {
		DUserProve userProve = new DUserProve();
		userProve.setUserId(userId);
		userProve.setProveInfo(proveInfo);
		userProve.setIdentityCard(identityCard);
		userProve.setOtherCard(otherCard);
		userProve.setIsProve(0);
		userProve.setCreateTime(Time.now());
		return guMasterDbService.save(userProve);
	}

	/**
	 * 保存用户设置
	 * @param userId 用户id
	 * @param allVisit 对所有人可见
	 * @param friendVisit 对朋友可见
	 * @return
	 */
	public boolean saveUserSetting(long userId, int allVisit, int friendVisit) {
		String sql = "insert into `user_setting` (`user_id`,`all_visit`,`friend_visit`) values (?,?,?) on duplicate key update `all_visit`=?,`friend_visit`=?";
		return guMasterDbService.update(sql, userId, allVisit, friendVisit, allVisit, friendVisit);
	}

	/**
	 * 查询用户设置
	 * @param userId 用户id
	 * @return
	 */
	public DUserSetting getUserSetting(long userId) {
		String sql = "SELECT * FROM `user_setting` WHERE `user_id`=?";
		return guSlaveDbService.queryT(sql, DUserSetting.class, userId);
	}

	/**
	 * 查询实名认证信息列表
	 * @param isProve
	 * @return
	 */
	public Map<String, Object> getUserProveList(int isProve, int page, int pageSize) {
		Map<String, Object> data = new HashMap<>(2);

		String sql = "SELECT * FROM `user_prove` WHERE `is_prove`=? order by create_time desc limit ?,?";
		data.put("list", guSlaveDbService.queryList(sql, DUserProve.class, isProve, (page - 1) * pageSize, pageSize));

		sql = "select count(*) from `user_prove` WHERE `is_prove`=?";
		data.put("total", guSlaveDbService.queryInt(sql.toString(), isProve));
		return data;
	}

	public boolean updateUserProve(int isProve, long userId) {
		String sql = "UPDATE `user_prove` SET `is_prove`=?, `check_time`=? WHERE `user_id`=?";
		return guMasterDbService.update(sql, isProve, Time.now(), userId);
	}

	public DUserProve updateUserProve(long userId, String proveInfo, String identityCard, String otherCard) {
		int createTime = Time.now();
		String sql = "UPDATE `user_prove` SET `prove_info`=?,`identity_card`=?,`other_card`=?,`create_time`=?,`is_prove`=0 WHERE `user_id`=?";
		boolean b = guMasterDbService.update(sql, proveInfo, identityCard, otherCard, createTime, userId);
		if (b) {
			DUserProve userProve = new DUserProve();
			userProve.setUserId(userId);
			userProve.setProveInfo(proveInfo);
			userProve.setIdentityCard(identityCard);
			userProve.setOtherCard(otherCard);
			userProve.setIsProve(0);
			userProve.setCreateTime(Time.now());
			return userProve;
		}
		return null;
	}

	public DUserProve getUserProveByUserId(long userId) {
		String sql = "SELECT * FROM `user_prove` WHERE `user_id`=?";
		return guSlaveDbService.queryT(sql, DUserProve.class, userId);
	}

	/**
	 * 用户使用时长统计
	 * @author ruan 
	 * @param userId 用户id
	 * @param duration 使用时长
	 */
	public void addUseDistributionLog(long userId, int duration) {
		String sql = "INSERT INTO `use_distribution_log`(`date`, `user_id`, `duration`) VALUES (?,?,?) on duplicate key update `duration`=`duration`+?";
		guLogDbService.update(sql, Time.date("yyyyMMdd"), userId, duration, duration);
	}

	/**
	 * 汇总昨天用户使用时长分布(每天凌晨4点执行)
	 */
	public void summaryUseDistribution() {
		int page = 1;
		int pageSize = 1000;
		int now = Time.now();
		int date = StringUtil.getInt(Time.date("yyyyMMdd", now - 86400));
		String sql = "select user_id,duration from `use_distribution_log` where `date` = ? limit ?,?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, date, (page - 1) * pageSize, pageSize);
		boolean isFirstEmpty = list == null || list.isEmpty();

		Map<String, Integer> useDistribution = new HashMap<>(6);
		if (list != null) {
			while (!list.isEmpty()) {
				for (Map<String, Object> data : list) {
					int duration = StringUtil.getInt(data.get("duration"));
					String durationRange = getDurationRange(duration);
					useDistribution.put(durationRange, StringUtil.getInt(useDistribution.get(durationRange)) + 1);
				}
				page = page + 1;
				list = guLogDbService.queryList(sql, date, (page - 1) * pageSize, pageSize);
				if (list == null) {
					list = Lists.newArrayList();
				}
			}
		}

		logger.warn("user distribution: {}", useDistribution.toString());

		// 汇总数据
		StringBuilder summarySql = new StringBuilder();
		summarySql.append("INSERT INTO `use_distribution` ");
		summarySql.append(" (`date`, ");
		for (Entry<String, Integer> e : useDistribution.entrySet()) {
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
		for (Entry<String, Integer> e : useDistribution.entrySet()) {
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
		summarySql.append("delete from `use_distribution_log` where `date`<=?");
		guLogDbService.update(summarySql.toString(), Time.date("yyyyMMdd", now - 86400 * 7));
	}

	/**
	 * 7天使用总时长汇总
	 * @author ruan
	 */
	public void summaryUseAverage7() {
		int page = 1, pageSize = 1000, duration = 0;
		int now = Time.now();
		int startDate = StringUtil.getInt(Time.date("yyyyMMdd", now - 86400 * 7));
		int endDate = StringUtil.getInt(Time.date("yyyyMMdd", now - 86400));
		Set<Long> userIdSet = new HashSet<Long>();

		// 7天人均
		String sql = "select `user_id`,`duration` from `use_distribution_log` where `date`>=? and `date`<=? limit ?,?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, startDate, endDate, (page - 1) * pageSize, pageSize);
		while (list != null && !list.isEmpty()) {
			for (Map<String, Object> data : list) {
				duration += StringUtil.getInt(data.get("duration"));
				userIdSet.add(StringUtil.getLong(data.get("user_id")));
			}

			// 下一页
			page = page + 1;
			list = guLogDbService.queryList(sql, startDate, endDate, (page - 1) * pageSize, pageSize);
		}
		sql = "INSERT INTO `use_average` (`start_date`, `end_date`,`user_num`, `duration`) VALUES (?,?,?,?)";
		guLogDbService.update(sql, startDate, endDate, userIdSet.size(), duration);

		// 1天人均
		userIdSet.clear();
		page = 1;
		duration = 0;
		sql = "select `user_id`,`duration` from `use_distribution_log` where `date`>=? and `date`<=? limit ?,?";
		list = guLogDbService.queryList(sql, endDate, endDate, (page - 1) * pageSize, pageSize);
		while (list != null && !list.isEmpty()) {
			for (Map<String, Object> data : list) {
				duration += StringUtil.getInt(data.get("duration"));
				userIdSet.add(StringUtil.getLong(data.get("user_id")));
			}

			// 下一页
			page = page + 1;
			list = guLogDbService.queryList(sql, startDate, endDate, (page - 1) * pageSize, pageSize);
		}
		sql = "INSERT INTO `use_average` (`start_date`, `end_date`,`user_num`, `duration`) VALUES (?,?,?,?)";
		guLogDbService.update(sql, endDate, endDate, userIdSet.size(), duration);
	}

	/**
	 * 记录用户信息统计
	 * @author ruan 
	 * @param userId 用户id
	 * @param version 当时使用的app版本号
	 * @param mobileType 使用手机机型
	 */
	public void recordUserInfo(long userId, int version, String mobileType) {
		if (userId <= 0 || version <= 0 || mobileType.isEmpty()) {
			return;
		}
		String sql = "INSERT INTO `user`(`id`,`version`,`mobile_type`) VALUES (?,?,?) on duplicate key update `version`=?,`mobile_type`=?";
		guLogDbService.update(sql, userId, version, mobileType, version, mobileType);
	}

	/**
	 * 统计所有用户的app版本分布、手机型号分布
	 * @author ruan
	 */
	public void countUserInfo() {
		int page = 1;
		int pageSize = 1000;
		int i = 0;
		Map<Integer, Integer> versionMap = new HashMap<>();
		Map<String, Integer> mobileTypeMap = new HashMap<>();

		String sql = "select * from `user` order by `id` asc limit ?,?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, (page - 1) * pageSize, pageSize);
		while (list != null && !list.isEmpty()) {
			for (Map<String, Object> data : list) {
				// 版本分布
				int version = StringUtil.getInt(data.get("version"));
				versionMap.put(version, StringUtil.getInt(versionMap.get(version)) + 1);

				// 机型分布
				String mobileType = StringUtil.getString(data.get("mobile_type"));
				mobileTypeMap.put(mobileType, StringUtil.getInt(mobileTypeMap.get(mobileType)) + 1);
			}
			page = page + 1;
			list = guLogDbService.queryList(sql, (page - 1) * pageSize, pageSize);
			if (list == null) {
				list = Lists.newArrayList();
			}
		}

		// 记录结果
		if (!versionMap.isEmpty()) {
			i = 0;
			guLogDbService.update("truncate `user_app_version`");
			sql = "INSERT INTO `user_app_version`(`version`,`num`) VALUES (?,?) on duplicate key update `version`=?,`num`=?";
			List<Object[]> batchArgs = new ArrayList<>(versionMap.size());
			for (Entry<Integer, Integer> e : versionMap.entrySet()) {
				Object[] obj = new Object[4];
				obj[0] = e.getKey();
				obj[1] = e.getValue();
				obj[2] = e.getKey();
				obj[3] = e.getValue();
				batchArgs.add(obj);
				if (++i % pageSize == 0) {
					guLogDbService.batchUpdate(sql, batchArgs);
					batchArgs.clear();
				}
			}
			guLogDbService.batchUpdate(sql, batchArgs);
		}

		if (!mobileTypeMap.isEmpty()) {
			i = 0;
			guLogDbService.update("truncate `user_mobile_type`");
			sql = "INSERT INTO `user_mobile_type`(`mobile_type`,`num`) VALUES (?,?) on duplicate key update `mobile_type`=?,`num`=?";
			List<Object[]> batchArgs = new ArrayList<>(mobileTypeMap.size());
			for (Entry<String, Integer> e : mobileTypeMap.entrySet()) {
				Object[] obj = new Object[4];
				obj[0] = e.getKey();
				obj[1] = e.getValue();
				obj[2] = e.getKey();
				obj[3] = e.getValue();
				batchArgs.add(obj);
				if (++i % pageSize == 0) {
					guLogDbService.batchUpdate(sql, batchArgs);
					batchArgs.clear();
				}
			}
			guLogDbService.batchUpdate(sql, batchArgs);
		}
	}

	/**
	 * 根据时长返回范围
	 * @author ruan 
	 * @param duration
	 */
	private String getDurationRange(int duration) {
		if (duration < 60) {
			return "less_than_1m";
		} else if (duration <= 300) {
			return "range_1m_5m";
		} else if (duration <= 600) {
			return "range_6m_10m";
		} else if (duration <= 1800) {
			return "range_10m_30m";
		} else if (duration <= 3600) {
			return "range_30m_60m";
		}
		return "range_1h_plus";
	}
}