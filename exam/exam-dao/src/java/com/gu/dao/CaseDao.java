package com.gu.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.gu.core.protocol.CaseSearchResponse;
import com.gu.core.util.MathUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.db.GuConfigDbService;
import com.gu.dao.db.GuLogDbService;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DBonusPercentConfig;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCasePraiseCount;
import com.gu.dao.model.DCaseStyleConfig;
import com.gu.dao.model.DFocusCase;
import com.gu.dao.model.DFocusOverCase;
import com.gu.dao.model.DTakePartInCase;
import com.gu.dao.model.DTmpCaseExpireQueue;

/**
 * 专案dao类
 * @author luo
 */
@Component
public class CaseDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;
	@Autowired
	private GuConfigDbService guConfigbService;
	@Autowired
	private GuLogDbService guLogDbService;

	/**
	 * 获取专案类型配置
	 */
	public List<Map<String, Object>> getCaseTypeConfig() {
		String sql = "SELECT * FROM  `case_type` order by id desc";
		return guConfigbService.queryList(sql);
	}

	/**
	 * 新增专案类型设置
	 * @param type 类型名字
	 */
	public void addCaseTypeConfig(String type) {
		String sql = "INSERT INTO `case_type`(`name`) VALUES (?)";
		guConfigbService.update(sql, type);
	}

	/**
	 * 判断专案类型是否重复
	 * @param type 类型名字
	 */
	public boolean existsCaseTypeConfig(String type) {
		String sql = "select `name` from `case_type` where `name`=?";
		return !guConfigbService.queryList(sql, type).isEmpty();
	}

	/**
	 * 删除专案类型设置
	 * @param id 类型id
	 */
	public void deleteCaseTypeConfig(int id) {
		String sql = "DELETE FROM `case_type` WHERE `id`=?";
		guConfigbService.update(sql, id);
	}

	/**
	 * 获取专案有效天数配置
	 */
	public Map<Integer, String> getCaseExpireConfig() {
		String sql = "SELECT * FROM  `case_expire` order by `seconds` asc";
		List<Map<String, Object>> list = guConfigbService.queryList(sql);
		Map<Integer, String> data = new LinkedHashMap<Integer, String>(list.size());
		for (Map<String, Object> map : list) {
			data.put(StringUtil.getInt(map.get("seconds")), StringUtil.getString(map.get("description")));
		}
		return data;
	}

	/**
	 * 保存专案有效天数配置
	 */
	public void saveCaseExpireConfig(Map<Integer, String> config) {
		guConfigbService.update("truncate `case_expire`");
		String sql = "REPLACE INTO `case_expire`(`seconds`,`description`) VALUES (?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>(config.size());
		for (Entry<Integer, String> e : config.entrySet()) {
			Object[] obj = new Object[2];
			obj[0] = e.getKey();
			obj[1] = StringUtil.getString(e.getValue());
			batchArgs.add(obj);
		}
		guConfigbService.batchUpdate(sql, batchArgs);
	}

	/**
	 * 删除专案有效天数配置
	 */
	public void deleteCaseExpireConfig(int seconds) {
		String sql = "delete from `case_expire` where `seconds`=?";
		guConfigbService.update(sql, seconds);
	}

	/**
	 * 获取专案金额配置
	 */
	public Map<Integer, String> getCasePointsConfig() {
		String sql = "SELECT * FROM  `case_points` order by `points` asc";
		List<Map<String, Object>> list = guConfigbService.queryList(sql);
		Map<Integer, String> data = new LinkedHashMap<Integer, String>(list.size());
		for (Map<String, Object> map : list) {
			data.put(StringUtil.getInt(map.get("points")), StringUtil.getString(map.get("description")));
		}
		return data;
	}

	/**
	 * 删除专案金额配置
	 */
	public void deleteCasePointsConfig(int points) {
		String sql = "delete from `case_points` where `points`=?";
		guConfigbService.update(sql, points);
	}

	/**
	 * 添加专案金额配置
	 */
	public void addCasePointsConfig(int points) {
		String sql = "replace INTO `case_points`(`points`,`description`) VALUES (?,?)";
		guConfigbService.update(sql, points, MathUtil.numberFormat(points));
	}

	/**
	 * 获取专案分钱规则2.0
	 */
	public List<DBonusPercentConfig> getBonusPercentConfig() {
		String sql = "SELECT * FROM `bonus_percent_config` order by id asc";
		return guConfigbService.queryList(sql, DBonusPercentConfig.class);
	}

	/**
	 * 修改分钱规则配置
	 * @author ruan 
	 * @param data
	 */
	public void updateBonusPercentConfig(Map<Integer, String> data) {
		for (Entry<Integer, String> e : data.entrySet()) {
			String[] arr = e.getValue().split(",");
			String sql = "update `bonus_percent_config` set `percent`=?,`points_max`=? where `id`=?";
			guConfigbService.update(sql, StringUtil.getString(arr[0]), StringUtil.getString(arr[1]), e.getKey());
		}
	}

	/**
	 * 获取专案征集类型配置
	 */
	public Map<Long, DCaseStyleConfig> getCaseStyleConfig() {
		Map<Long, DCaseStyleConfig> map = new LinkedHashMap<>();
		String sql = "SELECT * FROM  `case_style` order by `id` asc";
		List<DCaseStyleConfig> list = guConfigbService.queryList(sql, DCaseStyleConfig.class);
		for (DCaseStyleConfig caseStyleConfig : list) {
			map.put(caseStyleConfig.getId(), caseStyleConfig);
		}
		return map;
	}

	/**
	 * 判断专案征集类型是否重复
	 * @param name 类型名字
	 */
	public boolean existsCaseStyleConfig(String name) {
		String sql = "select `name` from `case_style` where `name`=?";
		return !guConfigbService.queryList(sql, name).isEmpty();
	}

	/**
	 * 新增专案征集类型配置
	 * @param name 类型名字
	 * @param points 金额
	 */
	public void addCaseStyleConfig(String name, int points) {
		String sql = "INSERT INTO `case_style`(`name`,`points`) VALUES (?,?)";
		guConfigbService.update(sql, name, points);
	}

	/**
	 * 删除专案征集类型配置
	 * @param id 类型id
	 */
	public void deleteCaseStyleConfig(int id) {
		String sql = "DELETE FROM `case_style` WHERE `id`=?";
		guConfigbService.update(sql, id);
	}

	/**
	 * 添加专案
	 * @param companyId 企业id
	 * @param name 专案名称
	 * @param description 专案描述
	 * @param type 专案类型
	 * @param caseExpire 专案有效天数
	 * @param points 专案金额
	 * @param caseStyle 专案征集类型
	 * @param image1  专案图片1
	 * @param image2  专案图片2
	 * @param image3  专案图片3
	 * @param image4  专案图片4
	 * @param image5  专案图片5
	 * @param video 视频地址
	 */
	public DCase addCase(long companyId, String name, String description, String type, int caseExpire, int points, long caseStyle, String image1, String image2, String image3, String image4, String image5, String video) {
		DCase dCase = new DCase();
		dCase.setName(name);
		dCase.setCompanyId(companyId);
		dCase.setDescription(description);
		dCase.setType(type);
		dCase.setCreateTime(Time.now());
		dCase.setEndTime(dCase.getCreateTime() + caseExpire);
		dCase.setPoints(points);
		dCase.setStyle((int) caseStyle);
		dCase.setImage1(image1);
		dCase.setImage2(image2);
		dCase.setImage3(image3);
		dCase.setImage4(image4);
		dCase.setImage5(image5);
		dCase.setVideo(video);
		return guMasterDbService.save(dCase);
	}

	/**
	 * 查询专案
	 * @param caseId 专案ID
	 * @return
	 */
	public DCase getCaseById(long caseId) {
		return guSlaveDbService.queryT("SELECT * FROM `case` WHERE `id`=?", DCase.class, caseId);
	}

	/**
	 * 根据专案类型获取专案列表
	 * @param caseType 专案类型
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public List<DCase> getCaseList(int caseType, long lastCaseId, int pageSize) {
		String sql = "select * from `case` where `type`=? and `is_show`=1 and `id` <=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DCase.class, caseType, lastCaseId, pageSize);
	}

	/**
	 * 获取所有专案列表
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public List<DCase> getAllCaseList(long lastCaseId, int pageSize) {
		String sql = "select * from `case` where `id` <=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DCase.class, lastCaseId, pageSize);
	}

	/**
	 * 获取最新专案列表
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public List<DCase> getNewCaseList(long lastCaseId, int pageSize) {
		String sql = "select * from `case` where `id` <=? and `end_time` < ? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DCase.class, lastCaseId, Time.now(), pageSize);
	}

	/**
	 * 根据企业获取专案列表
	 * @param companyId 企业id
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public List<DCase> getCompanyCaseList(long companyId, long lastCaseId, int pageSize) {
		String sql = "select * from `case` where `company_id`=? and `id` <=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DCase.class, companyId, lastCaseId, pageSize);
	}

	/**
	* 根据企业id获取专案列表
	* @param companyId 企业id
	* @param page 当前页码
	* @param pageSize 页面大小
	*/
	public List<DCase> getCompanyCaseList(long companyId, int page, int pageSize) {
		String sql = "select * from `case` where `company_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql.toString(), DCase.class, companyId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 修改专案 
	 * @param dCase 专案对象
	 */
	public boolean updateCase(DCase dCase) {
		return guMasterDbService.update(dCase);
	}

	/**
	 * 批量获取专案
	 * @param caseIdSet 专案id集合
	 */
	public List<DCase> getCase(Set<Long> caseIdSet) {
		return guSlaveDbService.multiGetT(caseIdSet, DCase.class);
	}

	/**
	 * 获取专案各种统计
	 * @author ruan 
	 * @param caseId 专案id
	 */
	public DCase getCaseCount(long caseId) {
		String sql = "select * from `case` where `id`=?";
		return guSlaveDbService.queryT(sql, DCase.class, caseId);
	}

	/**
	 * 用户对一个专案的所有创意的总点赞次数统计
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public DCasePraiseCount getCasePraiseCount(long userId, long caseId) {
		String sql = "SELECT * FROM `case_praise_count` WHERE `user_id`=? and `case_id`=? ";
		return guSlaveDbService.queryT(sql, DCasePraiseCount.class, userId, caseId);
	}

	/**
	 * 用户关注专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public DFocusCase focusCase(long userId, long caseId) {
		DFocusCase focusCase = new DFocusCase();
		focusCase.setUserId(userId);
		focusCase.setCaseId(caseId);
		focusCase.setCreateTime(Time.now(true));
		return guMasterDbService.save(focusCase);
	}

	/**
	 * 用户取消关注专案
	 * @param focusCase
	 */
	public boolean unFocusCase(DFocusCase focusCase) {
		return guMasterDbService.delete(focusCase);
	}

	/**
	 * 取消关注专案(进行中)
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public boolean unfocusCase(long userId, long caseId) {
		String sql = "delete from `focus_case` where `user_id`=? and `case_id`=?";
		return guMasterDbService.update(sql, userId, caseId);
	}

	/**
	 * 用户关注过的专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public DFocusOverCase focusOverCase(long userId, long caseId) {
		DFocusOverCase focusOverCase = new DFocusOverCase();
		focusOverCase.setUserId(userId);
		focusOverCase.setCaseId(caseId);
		focusOverCase.setCreateTime(Time.now(true));
		return guMasterDbService.save(focusOverCase);
	}

	/**
	 * 用户是否已经关注过这个专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public DFocusCase isFocusCase(long userId, long caseId) {
		String sql = "select * from `focus_case` where `case_id`=? and `user_id`=?";
		return guSlaveDbService.queryT(sql, DFocusCase.class, caseId, userId);
	}

	/**
	 * 用户是否已经参与过这个专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public DTakePartInCase isTakePartInCase(long userId, long caseId) {
		String sql = "select * from `take_part_in_case` where `user_id`=? and `case_id`=?";
		return guSlaveDbService.queryT(sql, DTakePartInCase.class, userId, caseId);
	}

	/**
	 * 获取专案粉丝
	 * @param caseId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)	
	 */
	public List<DFocusCase> getCaseFansList(long caseId, int page, int pageSize) {
		String sql = "select * from `focus_case` where `case_id`=? order by `id` limit ?,?";
		return guSlaveDbService.queryList(sql.toString(), DFocusCase.class, caseId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 批量获取关注专案数据
	 * @param caseId 专案id
	 * @param userIdSet 用户id集合
	 */
	public List<DFocusCase> getFocusCase(long caseId, Set<Long> userIdSet) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from `focus_case` where `case_id`='");
		sql.append(caseId);
		sql.append("' and `user_id` in (");
		sql.append(Joiner.on(",").join(userIdSet));
		sql.append(") order by `id` desc");
		return guSlaveDbService.queryList(sql.toString(), DFocusCase.class);
	}

	/**
	 * 获取关注专案数据
	 * @param caseId 专案id
	 * @param userId 用户id
	 */
	public DFocusCase getFocusCase(long caseId, long userId) {
		String sql = "select * from `focus_case` where `case_id`=? and `user_id`=?";
		return guSlaveDbService.queryT(sql.toString(), DFocusCase.class, caseId, userId);
	}

	/**
	 * 添加用户对一个专案的所有创意的总点赞次数统计
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public DCasePraiseCount addCasePraiseCount(long userId, long caseId) {
		DCasePraiseCount casePraiseCount = new DCasePraiseCount();
		casePraiseCount.setUserId(userId);
		casePraiseCount.setCaseId(caseId);
		casePraiseCount.setCreateTime(Time.now(true));
		casePraiseCount.setCount(1);
		return guMasterDbService.save(casePraiseCount);
	}

	/**
	 * 更新用户对专案创意的总点赞次数+1
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public boolean updateCasePraiseCountUpOne(long userId, long caseId) {
		String sql = "UPDATE `case_praise_count` SET `count`=`count`+1,`create_time`=? WHERE `user_id`=? and `case_id`=?";
		return guSlaveDbService.update(sql, Time.now(true), userId, caseId);
	}

	/**
	 * 更新用户对专案创意的总点赞次数-1
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public boolean updateCasePraiseCountDownOne(long userId, long caseId) {
		String sql = "UPDATE `case_praise_count` SET `count`=`count`-1,`create_time`=? WHERE `user_id`=? and `case_id`=?";
		return guSlaveDbService.update(sql, Time.now(true), userId, caseId);
	}

	/**
	 * 保存数据到专案过期队列临时表
	 * @param args 参数列表
	 */
	public void saveCaseExpireQueueData(List<Object[]> args) {
		String sql = "insert into `tmp_case_expire_queue`(`case_id`,`end_time`) values (?,?)";
		guMasterDbService.batchUpdate(sql, args);
	}

	/**
	 * 获取专案过期队列临时数据
	 */
	public List<DTmpCaseExpireQueue> getCaseExpireQueueData() {
		String sql = "SELECT * FROM `tmp_case_expire_queue`";
		return guSlaveDbService.queryList(sql, DTmpCaseExpireQueue.class);
	}

	/**
	 * 清除专案过期队列临时数据
	 */
	public void cleanCaseExpireQueueData() {
		String sql = "TRUNCATE `tmp_case_expire_queue`";
		guMasterDbService.update(sql);
	}

	/**
	 * 记录用户参与过的专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public DTakePartInCase takePartInCase(long userId, long caseId) {
		DTakePartInCase takePartInCase = new DTakePartInCase();
		takePartInCase.setCreateTime(Time.now(true));
		takePartInCase.setCaseId(caseId);
		takePartInCase.setUserId(userId);
		return guMasterDbService.save(takePartInCase);
	}

	/**
	 * 获取用户参与过的专案列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)
	 */
	public List<DTakePartInCase> getUserHasTakePartInCaseList(long userId, int page, int pageSize) {
		String sql = "select * from `take_part_in_case` where `user_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql.toString(), DTakePartInCase.class, userId, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 用户关注的专案列表
	 * @param caseId 专案id
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public List<DFocusCase> userFocusCaseList(long caseId, int lastCaseId, int pageSize) {
		String sql = "select * from `focus_case` where `user_id`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DFocusCase.class, caseId, lastCaseId, pageSize);
	}

	/**
	 * 专案搜索
	 * @param search 搜索字段
	 * @param lastCaseId 专案id游标
	 * @param pageSize 数目
	 * @return
	 */
	public List<CaseSearchResponse> searchByName(String search, long lastCaseId, int pageSize) {
		List<CaseSearchResponse> list = new ArrayList<CaseSearchResponse>();
		String sql = "SELECT * FROM `case` WHERE `name` like ? and `id`<? order by `id` desc limit ?";
		List<DCase> caseList = guSlaveDbService.queryList(sql, DCase.class, "%" + search + "%", lastCaseId, pageSize);
		for (DCase dCase : caseList) {
			CaseSearchResponse caseSearchResponse = new CaseSearchResponse();
			caseSearchResponse.setCaseId(dCase.getId());
			caseSearchResponse.setCaseName(dCase.getName());
			caseSearchResponse.setCompanyId(dCase.getCompanyId());

			String sql_company = "SELECT `logo_image` FROM `company` WHERE `id`=?";
			String logoImage = guSlaveDbService.queryString(sql_company, dCase.getCompanyId());
			caseSearchResponse.setCompanyLogo(logoImage);

			list.add(caseSearchResponse);
		}
		return list;
	}

	/**
	 * 专案id搜索
	 * @param search
	 * @return
	 */
	public List<Long> searchCaseIdByName(String search) {
		String sql = "SELECT id FROM `case` WHERE `name` like ? ";
		return guSlaveDbService.queryLongList(sql, "%" + search + "%");
	}

	/**
	 * 专案id搜索
	 * @param search
	 * @return
	 */
	public List<Long> searchCaseIdByName(Long companyId, String search) {
		String sql = "SELECT id FROM `case` WHERE `name` like ? and `company_id`=?";
		return guSlaveDbService.queryLongList(sql, "%" + search + "%", companyId);
	}

	/**
	 * 记录企业每周发布的专案数
	 * @author ruan 
	 * @param week 周
	 * @param companyNum 企业数
	 */
	public void recordCompanyPubCaseWeek(int week, int companyNum) {
		String sql = "INSERT INTO `company_pub_case_week`(`week`,`company_num`,`case_num`) VALUES (?,?,?) on duplicate key update `company_num`=?,`case_num`=`case_num`+1";
		guLogDbService.update(sql, week, companyNum, 1, companyNum);
	}

	/**
	 * 记录企业每月发布的专案数
	 * @author ruan 
	 * @param month 月T
	 * @param companyNum 企业数
	 */
	public void recordCompanyPubCaseMonth(int month, int companyNum) {
		String sql = "INSERT INTO `company_pub_case_month`(`month`,`company_num`,`case_num`) VALUES (?,?,?) on duplicate key update `company_num`=?,`case_num`=`case_num`+1";
		guLogDbService.update(sql, month, companyNum, 1, companyNum);
	}

	/**
	 * 当前专案总数
	 * @author ruan 
	 */
	public int getTotalCaseNum() {
		String sql = "select count(*) from `case`";
		return guSlaveDbService.queryInt(sql);
	}

	/**
	 * 记录每周发布专案数统计
	 * @author ruan 
	 * @param week 周
	 */
	public void recordCasePubWeek(int week) {
		String sql = "INSERT INTO `case_pub_week`(`week`,`case_num`) VALUES (?,?) on duplicate key update `case_num`=`case_num`+1";
		guLogDbService.update(sql, week, 1);
	}

	/**
	 * 专案概况统计
	 * @author ruan 
	 * @param week 周
	 * @param takePartInNum 参与数
	 * @param ideaNum 点子数
	 */
	public void recordCaseTotalCount(int week, int takePartInNum, int ideaNum) {
		String sql = "INSERT INTO `case_total_count` (`week`,`case_num`,`take_part_in_num`,`idea_num`) VALUES (?,?,?,?) on duplicate key update `case_num`=`case_num`+1,`take_part_in_num`=`take_part_in_num`+?,`idea_num`=`idea_num`+?";
		guLogDbService.update(sql, week, 1, takePartInNum, ideaNum, takePartInNum, ideaNum);
	}

	public int getPraiseNum(long caseId) {
		String sql = "select `praise_num` from `case` where `id`=?";
		return guSlaveDbService.queryInt(sql, caseId);
	}
}