package com.gu.service.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.protocol.FlowListResponse;
import com.gu.core.protocol.FlowResponse;
import com.gu.core.protocol.base.UserBaseResponse;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.util.SerialUtil;
import com.gu.dao.FlowDao;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DFlow;
import com.gu.dao.model.DUser;
import com.gu.service.common.CommonService;
import com.gu.service.user.UserService;

/**
 * 吐槽service
 * @author ruan
 */
@Component
public class FlowService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(FlowService.class);
	@Autowired
	private Redis redis;
	@Autowired
	private FlowDao flowDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonService commonService;

	/**
	 * SortSet列表加占位
	 * @param key
	 */
	public void addSortSetAnchor(String key) {
		redis.SORTSET.zadd(key, -1, "-1");
	}

	/**
	 * 发表一条吐槽
	 * @param caseId 专案id
	 * @param userId 用户id
	 * @param content 吐槽内容
	 * @param dCase 专案对象
	 */
	public DFlow addFlow(long caseId, long userId, String content, DCase dCase) {
		DFlow flow = flowDao.addFlow(caseId, userId, content);
		if (flow == null) {
			return null;
		}

		redis.STRINGS.setex(KeyFactory.flowKey(flow.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(flow));
		redis.SORTSET.zadd(KeyFactory.flowListKey(caseId), flow.getId(), String.valueOf(flow.getId()));
		redis.SORTSET.zadd(KeyFactory.caseFlowKey(caseId), flow.getId(), String.valueOf(flow.getUserId()));
		return flow;
	}

	/**
	 * 获取吐槽列表
	 * @param dCase 专案对象
	 * @param lastFlowId 上一页最后一个吐槽id
	 * @param pageSize 页面大小
	 */
	public FlowListResponse getFlowList(DCase dCase, long lastFlowId, int pageSize) {
		FlowListResponse flowListResponse = new FlowListResponse();
		String key = KeyFactory.flowListKey(dCase.getId());
		String caseFlowKey = KeyFactory.caseFlowKey(dCase.getId());

		// 只有第一页才拿总条数
		if (lastFlowId == Long.MAX_VALUE) {
			flowListResponse.setTotal(commonService.getCountColumn(CountKey.caseFlowNum, dCase.getId()));
		}

		// 先从缓存拿数据
		lastFlowId = lastFlowId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastFlowId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFlow> flowList = flowDao.getFlowList(dCase.getId(), lastFlowId, pageSize);
			if (flowList == null || flowList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return flowListResponse;
			}

			int size = flowList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers_flow = new HashMap<String, Double>(size);
			Map<String, Double> scoreMembers_user = new HashMap<String, Double>(size);
			for (DFlow flow : flowList) {
				String id = String.valueOf(flow.getId());
				String userId = String.valueOf(flow.getUserId());
				scoreMembers_flow.put(id, flow.getId() * 1D);
				scoreMembers_user.put(userId, flow.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers_flow);
			redis.SORTSET.zadd(caseFlowKey, scoreMembers_user);
		}

		// 批量获取吐槽体
		Map<Long, DFlow> flowMap = multiGetFlow(listFromCache);

		// 组织吐槽列表
		Set<String> userIdSet = new LinkedHashSet<String>(pageSize);
		List<FlowResponse> flowResponseList = new ArrayList<FlowResponse>(pageSize);
		for (Entry<Long, DFlow> e : flowMap.entrySet()) {
			DFlow flow = e.getValue();
			FlowResponse flowResponse = new FlowResponse();
			flowResponse.setFlowId(flow.getId());
			flowResponse.setUserId(flow.getUserId());
			flowResponse.setContent(flow.getContent());
			flowResponse.setCreateTime(flow.getCreateTime());
			flowResponseList.add(flowResponse);

			// 用来获取用户信息
			userIdSet.add(String.valueOf(flow.getUserId()));
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<FlowResponse> it = flowResponseList.iterator();
		while (it.hasNext()) {
			FlowResponse flowResponse = it.next();
			DUser user = userMap.get(flowResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("FlowService.getFlowList, can not get user, userId: {}", flowResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			flowResponse.setUserBase(userBaseResponse);
			flowResponse.setNickname(user.getNickname());
			flowResponse.setAvatarImage(user.getAvatarImage());
			flowResponse.setIdentity(user.getIdentity());

		}
		flowListResponse.setFlowResponseList(flowResponseList);
		return flowListResponse;
	}

	/**
	 * 批量获取吐槽体
	 * @param flowIdSet 吐槽id集合
	 */
	public Map<Long, DFlow> multiGetFlow(Set<String> flowIdSet) {
		return commonService.multiGetT(flowIdSet, DFlow.class);
	}
}