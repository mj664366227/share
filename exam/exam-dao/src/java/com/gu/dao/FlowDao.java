package com.gu.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.util.Time;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DFlow;

/**
 * 吐槽dao
 * @author ruan
 */
@Component
public class FlowDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;

	/**
	 * 发表一条吐槽
	 * @param caseId 专案id
	 * @param userId 用户id
	 * @param content 吐槽内容
	 */
	public DFlow addFlow(long caseId, long userId, String content) {
		DFlow flow = new DFlow();
		flow.setCaseId(caseId);
		flow.setUserId(userId);
		flow.setCreateTime(Time.now());
		flow.setContent(content);
		return guMasterDbService.save(flow);
	}

	/**
	 * 获取吐槽列表
	 * @param caseId 专案id
	 * @param lastFlowId 上一页最后一个吐槽id
	 * @param pageSize 页面大小
	 */
	public List<DFlow> getFlowList(long caseId, long lastFlowId, int pageSize) {
		String sql = "select * from `flow` where `case_id`=? and `id` <=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DFlow.class, caseId, lastFlowId, pageSize);
	}

	/**
	 * 批量获取吐槽体
	 * @param flowIdSet 吐槽id集合
	 */
	public List<DFlow> getFlow(Set<Long> flowIdSet) {
		return guSlaveDbService.multiGetT(flowIdSet, DFlow.class);
	}

	public List<Long> flowUserIdByCaseId(long caseId) {
		String sql = "SELECT `user_id` FROM `flow` WHERE `case_id`=? group by `user_id`";
		return guSlaveDbService.queryLongList(sql, caseId);
	}
}