package com.gu.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.db.GuConfigDbService;
import com.gu.dao.db.GuLogDbService;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DFocusCompany;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DSystem;
import com.gu.dao.model.DUser;

@Component
public class SystemDao {
	private final static Logger logger = LoggerFactory.getLogger(SystemDao.class);
	@Autowired
	private GuConfigDbService guConfigDbService;
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;
	@Autowired
	private GuLogDbService guLogDbService;

	/**
	 * 获取系统配置
	 */
	public DSystem getSystemConfig() {
		String sql = "select * from `system` where `id`=1";
		return guConfigDbService.queryT(sql, DSystem.class);
	}

	/**
	 * 修改系统配置
	 * @param system
	 */
	public void upgateSystemConfig(DSystem system) {
		guConfigDbService.update(system);
	}

	public List<DCompany> getCompanyList() {
		String sql = "SELECT * FROM `company`";
		return guSlaveDbService.queryList(sql, DCompany.class);
	}

	public int getAllCaseNumByCompanyId(long companyId) {
		String sql = "SELECT count(`id`) FROM `case` WHERE `company_id`=?";
		return guSlaveDbService.queryInt(sql, companyId);
	}

	public int getOverCaseNumByCompanyId(long companyId) {
		String sql = "SELECT count(`id`) FROM `case` WHERE `company_id`=? and `end_time`<=?";
		return guSlaveDbService.queryInt(sql, companyId, Time.now());
	}

	public int getFansNumByCompanyId(long companyId) {
		String sql = "SELECT count(`id`) FROM `focus_company` WHERE `company_id`=?";
		return guSlaveDbService.queryInt(sql, companyId);
	}

	public void update_company_count_focus_total(long companyId, int num) {
		String sql = "INSERT INTO `company_count_focus_total`(`date`,`company_id`,`h1`,`total`) VALUES ('20151021',?,?,?) ";
		guLogDbService.update(sql, companyId, num, num);
	}

	public List<DIdea> getIdeaList() {
		String sql = "SELECT * FROM `idea`";
		return guSlaveDbService.queryList(sql, DIdea.class);
	}

	public int getIdeaCommentNum(long ideaId) {
		String sql = "SELECT count(`id`) FROM `idea_comment` WHERE `idea_id`=?";
		return guSlaveDbService.queryInt(sql, ideaId);
	}

	public int getCountPointsByUserId(long userId) {
		String sql = "SELECT IFNULL(sum(`points`),0) as sum FROM `user_points_add_log` WHERE `user_id`=?";
		return guLogDbService.queryInt(sql, userId);
	}

	public List<DUser> getUserList() {
		String sql = "SELECT * FROM `user`";
		return guSlaveDbService.queryList(sql, DUser.class);
	}

	public void repairUserInfo() {
		int page = 1;
		int pageSize = 1000;

		// 清空数据
		guLogDbService.update("truncate `user_app_version`");
		guLogDbService.update("truncate `user_mobile_type`");
		guLogDbService.update("truncate `user`");

		String sql = "select * from `user` order by `id` asc limit ?,?";
		List<Map<String, Object>> list = guSlaveDbService.queryList(sql, (page - 1) * pageSize, pageSize);
		List<String> sqlList = new ArrayList<>();
		while (list != null && !list.isEmpty()) {
			for (Map<String, Object> data : list) {
				String sql1 = "INSERT INTO `user`(`id`,`version`,`mobile_type`) VALUES ('%s','%s','%s')";
				sqlList.add(String.format(sql1, StringUtil.getString(data.get("id")), "100", "未知"));
			}
			page = page + 1;
			list = guLogDbService.queryList(sql, (page - 1) * pageSize, pageSize);
			if (list == null) {
				list = Lists.newArrayList();
			}
		}

		guLogDbService.batchUpdate(sqlList.toArray(new String[sqlList.size()]));
	}

	/**
	 * 保存用户在线数据
	 * @param date 日期
	 * @param num 在线数量
	 * @author ruan
	 */
	public void saveUserOnline(String date, long num) {
		String sql = "REPLACE INTO `online_user`(`date`,`num`) VALUES (?,?)";
		guLogDbService.update(sql, date, num);
	}

	/**
	 * 保存企业在线数据
	 * @param date 日期
	 * @param num 在线数量
	 * @author ruan
	 */
	public void saveCompanyOnline(String date, long num) {
		String sql = "REPLACE INTO `online_company`(`date`,`num`) VALUES (?,?)";
		guLogDbService.update(sql, date, num);
	}

	/**
	 * 清空指定日期前的用户在线数据
	 * @param date 日期
	 * @author ruan
	 */
	public void clearUserOnline(long date) {
		String sql = "delete from `online_user` where `date` <=?";
		guLogDbService.update(sql, date);
	}

	/**
	 * 清空指定日期前的企业在线数据
	 * @param date 日期
	 * @author ruan
	 */
	public void clearCompanyOnline(long date) {
		String sql = "delete from `online_company` where `date` <=?";
		guLogDbService.update(sql, date);
	}

	public int getCompanyFocus(long companyId) {
		String sql = "select count(*) from `company_focus` where `company_id` =?";
		return guSlaveDbService.queryInt(sql, companyId);
	}

	public int getCompanyCollect(long companyId) {
		String sql = "select count(*) from `collect_idea` where `company_id` =?";
		return guSlaveDbService.queryInt(sql, companyId);
	}

	public List<DCase> getCaseList() {
		String sql = "SELECT * FROM `case`";
		return guSlaveDbService.queryList(sql, DCase.class);
	}

	public int repairCompanyFans(long companyId) {
		String sql = "select * from `focus_company` where company_id=?";
		Set<Long> userSet = new HashSet<>();
		for (DFocusCompany focusCompany : guSlaveDbService.queryList(sql, DFocusCompany.class, companyId)) {
			if (!userSet.add(focusCompany.getUserId())) {
				String xxx = "delete from `focus_company` where id=?";
				guMasterDbService.update(xxx, focusCompany.getId());
				logger.warn("删除重复用户：{}", focusCompany.getUserId());
			}
		}
		sql = "select count(*) from `focus_company` where company_id=?";
		return guSlaveDbService.queryInt(sql, companyId);
	}
}