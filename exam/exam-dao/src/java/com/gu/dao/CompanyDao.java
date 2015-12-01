package com.gu.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.gu.core.enums.CompanyPointsAddEvent;
import com.gu.core.enums.CompanyPointsSubEvent;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.db.GuConfigDbService;
import com.gu.dao.db.GuLogDbService;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DCollectIdea;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DCompanyCaseTag;
import com.gu.dao.model.DCompanyDefineCaseTag;
import com.gu.dao.model.DCompanyExpense;
import com.gu.dao.model.DCompanyFocus;
import com.gu.dao.model.DFocusCompany;

/**
 * 企业dao类
 * @author luo
 */
@Component
public class CompanyDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;
	@Autowired
	private GuConfigDbService guConfigbService;
	@Autowired
	private GuLogDbService guLogDbService;

	/**
	 * 获取企业领域配置
	 */
	public Map<Long, String> getCompanyTypeConfig() {
		String sql = "SELECT * FROM  `company_type` order by id asc";
		List<Map<String, Object>> list = guConfigbService.queryList(sql);
		Map<Long, String> data = new LinkedHashMap<Long, String>(list.size());
		for (Map<String, Object> map : list) {
			data.put(StringUtil.getLong(map.get("id")), StringUtil.getString(map.get("name")));
		}
		data.put(0l, "其他");
		return data;
	}

	/**
	 * 保存企业领域配置
	 * @param type 领域
	 */
	public void saveCaseExpireConfig(String type) {
		String sql = "INSERT INTO `company_type`(`name`) VALUES (?)";
		guConfigbService.insert(sql, "id", type);
	}

	/**
	 * 判断企业领域配置是否重复
	 * @param type 领域
	 */
	public boolean caseExpireConfigExists(String type) {
		String sql = "select * from `company_type` where `name`=?";
		return !guConfigbService.queryList(sql, type).isEmpty();
	}

	/**
	 * 删除企业领域配置
	 * @author ruan 
	 * @param id
	 */
	public void deleteCaseExpireConfig(long id) {
		String sql = "delete from `company_type` where `id`=?";
		guConfigbService.update(sql, id);
	}

	/**
	 * 当前企业总数
	 * @author ruan 
	 */
	public int getTotalCompanyNum() {
		String sql = "select count(*) from `company`";
		return guSlaveDbService.queryInt(sql);
	}

	/**
	 * 当前企业有效注册数
	 * @author ruan 
	 */
	public int getTotalCompanyEffectiveNum() {
		String sql = "select count(*) from `company` where `admin_email` !=`name`";
		return guSlaveDbService.queryInt(sql);
	}

	/**
	 * 记录今天活跃率
	 * @author ruan
	 */
	public void recordDailyActiveRate() {
		String date = Time.date("yyyyMMdd");

		// 今天总活跃数
		String sql = "select `total` from `daily_active_company` where `date`=? order by `date` desc limit 1";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, date);
		int dailyActiveUser = 0;
		if (list != null && !list.isEmpty()) {
			dailyActiveUser = StringUtil.getInt(list.get(0).get("total"));
		}

		// 当前总企业数
		int totalCompanyNum = getTotalCompanyNum();

		sql = "INSERT INTO `active_company`(`date`, `reg_num`, `active`) VALUES (?,?,?)";
		guLogDbService.update(sql, date, totalCompanyNum, dailyActiveUser);
	}

	/**
	 * 企业注册方法
	 * @param name 企业名称
	 * @param password 密码
	 * @param description 企业简介(描述)
	 * @param type 企业领域(类型)
	 * @param logo_image 企业logo
	 * @param admin_name 管理员姓名
	 * @param admin_phone 管理员电话
	 * @param admin_email 管理员邮箱
	 * @return
	 */
	public DCompany addCompany(String name, String password, String description, long typeId, String logoImage, String adminName, String adminPhone, String adminEmail) {
		DCompany company = new DCompany();
		company.setCreateTime(Time.now());
		company.setName(name);
		company.setFullname(name);
		company.setPassword(password);
		company.setDescription(description);
		company.setTypeId(typeId);
		company.setLogoImage(logoImage);
		company.setLastLoginTime(Time.now());
		company.setAdminName(adminName);
		company.setAdminPhone(adminPhone);
		company.setAdminEmail(adminEmail);
		return guMasterDbService.save(company);
	}

	/**
	 * 企业名字唯一性检查
	 * @param name 企业名字
	 * @return false=已被使用
	 */
	public boolean companyNameOnly(String name) {
		return guSlaveDbService.queryList("SELECT `name` FROM `company` WHERE `name`=?", name).isEmpty();
	}

	/**
	 * 企业全名唯一性检查
	 * @param name 企业全名
	 * @return false=已被使用
	 */
	public boolean companyFullNameOnly(String name) {
		return guSlaveDbService.queryList("SELECT `name` FROM `company` WHERE `fullname`=?", name).isEmpty();
	}

	/**
	 * 企业邮箱唯一性检查
	 * @param name 企业名字
	 * @return false=已被使用
	 */
	public boolean companyEmailOnly(String name) {
		return guSlaveDbService.queryList("SELECT `admin_email` FROM `company` WHERE `admin_email`=?", name).isEmpty();
	}

	/**
	 * 根据企业id获取企业
	 * @param companyId 企业
	 */
	public DCompany getCompanyById(long companyId) {
		String sql = "select * from  `company` where `id`=?";
		return guSlaveDbService.queryT(sql, DCompany.class, companyId);
	}

	/**
	 * 根据企业邮箱获取企业
	 * @param email 企业邮箱
	 */
	public DCompany getCompanyByEmail(String email) {
		String sql = "select * from  `company` where `admin_email`=?";
		return guSlaveDbService.queryT(sql, DCompany.class, email);
	}

	/**
	 * 批量获取企业
	 * @param companyIdSet 企业id集合
	 */
	public List<DCompany> getCompany(Set<Long> companyIdSet) {
		return guSlaveDbService.multiGetT(companyIdSet, DCompany.class);
	}

	/**
	 * 修改企业信息
	 * @param company 企业对象
	 */
	public boolean updateCompany(DCompany company) {
		return guMasterDbService.update(company);
	}

	/**
	 * 用户加钱日志
	 * @param userId 用户id
	 * @param points 加了的点数
	 * @param event 加钱事件
	 */
	public void companyPointAddLog(long userId, int points, CompanyPointsAddEvent event) {
		String sql = "insert into `company_points_add_log`( `company_id`, `points`, `event`, `create_time`) values (?,?,?,?)";
		guLogDbService.update(sql, userId, points, event.getValue(), Time.now());
	}

	/**
	 * 用户扣钱日志
	 * @param userId 用户id
	 * @param points 扣了的点数
	 * @param event 扣钱事件
	 */
	public void companyPointSubLog(long userId, int points, CompanyPointsSubEvent event) {
		String sql = "insert into `company_points_sub_log`( `company_id`, `points`,`event`,`create_time`) values (?,?,?,?)";
		guLogDbService.update(sql, userId, points, event.getValue(), Time.now());
	}

	/**
	 * 企业搜索
	 * @param search 搜索字段
	 * @param lastCompanyId 企业id游标
	 * @param pageSize 数目
	 * @return
	 */
	public List<DCompany> searchByName(String search, long lastCompanyId, int pageSize) {
		String sql = "SELECT * FROM `company` WHERE `name` like ? and `id`<? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DCompany.class, "%" + search + "%", lastCompanyId, pageSize);
	}

	public boolean addLogintime(DCompany company) {
		int nowTime = Time.now();
		String sql = "insert into `company_logintime_log`(`company_id`, `reg_time`, `login_time`) values (?,?,?)";
		return guLogDbService.update(sql, company.getId(), company.getCreateTime(), nowTime);
	}

	/**
	 * 记录新增企业日志
	 */
	public void newCompanyLog() {
		int now = Time.now();
		String date = Time.date("yyyyMMdd", now);
		int h = StringUtil.getInt(Time.date("H", now)) + 1;

		StringBuilder sql = new StringBuilder();
		sql.append("insert into `new_company` (`date`,`h");
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
	 * 日活跃企业统计
	 * @param date 日期
	 * @param hour 小时
	 */
	public void recordDailyActiveCompany(String date, int hour) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into `daily_active_company` (`date`,`h");
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
	 * 日活跃企业统计
	 * @param date 日期
	 */
	public void recordDailyActiveCompanyTotal(String date) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into `daily_active_company` (`date`,`total`) values (?,1) ");
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
		sql.append("INSERT INTO `company_recent7_active`(`start_date`, `end_date`, `num`) VALUES (?,?,'1')");
		sql.append(" on duplicate key update `num`=`num`+1");
		guLogDbService.update(sql.toString(), startDate, endDate);
	}

	/**
	 * 记录最近14天活跃用户
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public void recordRecent14Active(String startDate, String endDate) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO `company_recent14_active`(`start_date`, `end_date`, `num`) VALUES (?,?,'1')");
		sql.append(" on duplicate key update `num`=`num`+1");
		guLogDbService.update(sql.toString(), startDate, endDate);
	}

	/**
	 * 获取某个时间段活跃企业数
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public Map<String, Object> getRecentActive(String startDate, String endDate) {
		int sDate = Time.str2time(startDate, "yyyyMMdd");
		int eDate = Time.str2time(endDate, "yyyyMMdd");
		int day = (eDate - sDate) / 86400 + 1;
		String sql = "select * from `company_recent" + day + "_active` where `start_date`=? and `end_date`=?";
		List<Map<String, Object>> list = guLogDbService.queryList(sql, startDate, endDate);
		if (list == null || list.isEmpty()) {
			return new HashMap<>(0);
		}
		return list.get(0);
	}

	/**
	 * 新增一个企业自定义标签
	 * @author ruan 
	 * @param companyId 企业id
	 * @param name 标签名
	 */
	public DCompanyDefineCaseTag addCompanyDefineCaseTag(long companyId, String name) {
		DCompanyDefineCaseTag companyDefineCaseTag = new DCompanyDefineCaseTag();
		companyDefineCaseTag.setCompanyId(companyId);
		companyDefineCaseTag.setName(name);
		companyDefineCaseTag.setCaseNum(1);
		return guMasterDbService.save(companyDefineCaseTag);
	}

	/**
	 * 根据标签名查自定义标签
	 * @author ruan 
	 * @param companyId 企业id
	 * @param name 标签名
	 */
	public DCompanyDefineCaseTag getCompanyDefineCaseTag(long companyId, String name) {
		String sql = "select * from `company_define_case_tag` where `company_id`=? and `name`=?";
		return guSlaveDbService.queryT(sql, DCompanyDefineCaseTag.class, companyId, name);
	}

	/**
	 * 获取企业所有自定义标签
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public List<DCompanyDefineCaseTag> getCompanyDefineCaseTag(long companyId) {
		String sql = "select * from `company_define_case_tag` where `company_id`=?";
		return guSlaveDbService.queryList(sql, DCompanyDefineCaseTag.class, companyId);
	}

	/**
	 * 更新用户自定义标签的专案数+1
	 * @author ruan 
	 * @param id 标签id
	 */
	public boolean updateCompanyDefineTagCaseNum(long id) {
		String sql = "update `company_define_case_tag` set `case_num`=`case_num`+1 where `id`=?";
		return guMasterDbService.update(sql, id);
	}

	/**
	 * 保存企业的自定义标签
	 * @author ruan 
	 * @param companyId 企业id
	 * @param caseType 专案类型
	 */
	public DCompanyCaseTag saveCompanyCaseTag(long companyId, long caseType) {
		DCompanyCaseTag companyCaseTag = new DCompanyCaseTag();
		companyCaseTag.setCompanyId(companyId);
		companyCaseTag.setTypeId(caseType);
		return guMasterDbService.save(companyCaseTag);
	}

	/**
	 * 获取企业所有专案的标签
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public List<DCompanyCaseTag> getDCompanyCaseTag(long companyId) {
		String sql = "SELECT * FROM  `company_case_tag` where `company_id`=? order by `id` desc";
		return guSlaveDbService.queryList(sql, DCompanyCaseTag.class, companyId);
	}

	/**
	 * 获取企业的粉丝列表
	 * @author ruan 
	 * @param companyId 企业id
	 * @param lastIndex 上次最后索引
	 * @param pageSize 页面大小
	 */
	public List<DFocusCompany> getCompanyFansList(long companyId, int lastIndex, int pageSize) {
		String sql = "SELECT * FROM  `focus_company` where `company_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DFocusCompany.class, companyId, lastIndex, pageSize);
	}

	/**
	 * 获取企业关注的用户列表
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页面
	 * @param pageSize 页面大小
	 */
	public List<DCompanyFocus> getFocusUserList(long companyId, int page, int pageSize) {
		String sql = "SELECT * FROM  `company_focus` where `company_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DCompanyFocus.class, companyId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 收藏创意 
	 * @author ruan 
	 * @param companyId 企业id
	 * @param ideaId 创意id
	 */
	public DCollectIdea collectIdea(long companyId, long ideaId) {
		DCollectIdea collectIdea = new DCollectIdea();
		collectIdea.setCompanyId(companyId);
		collectIdea.setCreateTime(Time.now());
		collectIdea.setIdeaId(ideaId);
		return guMasterDbService.save(collectIdea);
	}

	/**
	 * 获取企业收藏的点子
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public List<DCollectIdea> getCompanyCollectIdea(long companyId, int page, int pageSize) {
		String sql = "select * from `collect_idea` where `company_id` = ? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DCollectIdea.class, companyId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 获取企业收藏的点子
	 * @author ruan 
	 * @param companyId 企业id
	 * @param ideaId 创意id
	 */
	public DCollectIdea getCompanyCollectIdea(long companyId, long ideaId) {
		String sql = "select * from  `collect_idea` where `company_id` =?  and `idea_id`=?";
		return guSlaveDbService.queryT(sql, DCollectIdea.class, companyId, ideaId);
	}

	/**
	 * 企业关注用户
	 * @author ruan 
	 * @param companyId 企业id
	 * @param userId 用户id
	 */
	public DCompanyFocus focusUser(long companyId, long userId) {
		DCompanyFocus companyFocus = new DCompanyFocus();
		companyFocus.setCompanyId(companyId);
		companyFocus.setUserId(userId);
		companyFocus.setCreateTime(Time.now());
		return guMasterDbService.save(companyFocus);
	}

	/**
	 * 企业取消关注用户
	 * @author ruan 
	 * @param companyId 企业id
	 * @param userId 用户id
	 */
	public boolean unfocusUser(long companyId, long userId) {
		String sql = "delete from `company_focus` where `company_id`=? and `user_id`=?";
		return guMasterDbService.update(sql, companyId, userId);
	}

	/**
	 * 获取企业有没有关注这个用户
	 * @author ruan 
	 * @param companyId 企业id
	 * @param userId 用户id
	 */
	public DCompanyFocus getFocusUser(long companyId, long userId) {
		String sql = "select * from `company_focus` where `company_id`=? and `user_id`=?";
		return guSlaveDbService.queryT(sql, DCompanyFocus.class, companyId, userId);
	}

	/**
	 * 批量获取用户
	 * @param companyId 企业id
	 * @param userIdSet 用户id集合
	 */
	public List<DCompanyFocus> getFocusUser(long companyId, Set<Long> userIdSet) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from `company_focus` where `company_id` =  ?  and `user_id` in (");
		sql.append(Joiner.on(",").join(userIdSet));
		sql.append(") order by `id` desc");
		return guSlaveDbService.queryList(sql.toString(), DCompanyFocus.class, companyId);
	}

	/**
	 * 记录企业G点支出
	 * @author ruan 
	 * @param companyId 企业id
	 * @param caseId 专案id
	 * @param points 支出点数
	 */
	public DCompanyExpense addCompanyExpense(long companyId, long caseId, int points) {
		DCompanyExpense companyExpense = new DCompanyExpense();
		companyExpense.setCaseId(caseId);
		companyExpense.setPoints(points);
		companyExpense.setCreateTime(Time.now());
		companyExpense.setCompanyId(companyId);
		return guMasterDbService.save(companyExpense);
	}

	/**
	 * 获取G点支出列表
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public List<DCompanyExpense> getCompanyExpense(long companyId, int page, int pageSize) {
		String sql = "SELECT * FROM  `company_expense` where `company_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DCompanyExpense.class, companyId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 批量获取企业G点支出
	 * @param companyId 企业id
	 * @param caseIdSet 用户id集合
	 */
	public List<DCompanyExpense> getCompanyExpense(long companyId, Set<Long> caseIdSet) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from `company_expense` where `company_id` =  ?  and `case_id` in (");
		sql.append(Joiner.on(",").join(caseIdSet));
		sql.append(") order by `id` desc");
		return guSlaveDbService.queryList(sql.toString(), DCompanyExpense.class, companyId);
	}

	/**
	 * 发布专案的品牌统计
	 * @author ruan 
	 * @param date 日期
	 */
	public void recordCompanyPubCase(String date) {
		String sql = "INSERT INTO `company_pub_case`(`date`, `company_num`) VALUES (?,?) on duplicate key update `company_num`=`company_num`+1";
		guLogDbService.update(sql, date, 1);
	}
}