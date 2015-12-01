package com.gu.service.cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
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

import com.gu.core.protocol.CaseBonusResponse;
import com.gu.core.protocol.base.UserBaseResponse;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.util.JSONObject;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.dao.LogDao;
import com.gu.dao.model.DBonusPercentConfig;
import com.gu.dao.model.DCaseFinishBonusUserLog;
import com.gu.dao.model.DUser;
import com.gu.service.user.UserService;

@Component
public class CaseFinishService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(CaseFinishService.class);
	@Autowired
	private Redis redis;

	@Autowired
	private LogDao LogDao;

	@Autowired
	private UserService userService;

	@Autowired
	private BonusService bonusService;

	/**
	 * SortSet列表加占位
	 * @param key
	 */
	public void addSortSetAnchor(String key) {
		redis.SORTSET.zadd(key, -1, "-1");
	}

	/**
	 * 查询专案分红创意获奖用户数据
	 * @param caseId
	 * @return
	 */
	public List<CaseBonusResponse> getBonusPrizeUser(long caseId) {
		List<CaseBonusResponse> list = new ArrayList<CaseBonusResponse>();
		List<Map<String, Object>> mapList = LogDao.getBonusPrizeUser(caseId);
		Map<String, DBonusPercentConfig> bonusPercentConfigMap = bonusService.getBonusPercentConfigMap();
		for (Map<String, Object> map : mapList) {
			CaseBonusResponse caseBonusResponse = new CaseBonusResponse();

			DUser user = userService.getUserById(StringUtil.getLong(map.get("user_id")));
			if (user == null) {
				continue;
			}
			caseBonusResponse.setUserId(user.getId());
			caseBonusResponse.setNickname(user.getNickname());
			caseBonusResponse.setAvatarImage(user.getAvatarImage());
			caseBonusResponse.setLevel(user.getLevel());

			caseBonusResponse.setPoints(StringUtil.getInt(map.get("bonus_points")));
			caseBonusResponse.setCreateTime(StringUtil.getInt(map.get("create_time")) * 1000L);

			String remark = StringUtil.getString(map.get("bonus_name"));
			DBonusPercentConfig bonusPercentConfig = bonusPercentConfigMap.get(remark);
			caseBonusResponse.setRemark(bonusPercentConfig.getRemark());

			list.add(caseBonusResponse);
		}
		return list;
	}

	/**
	 * 查询专案分红创意获奖用户数据
	 * @param caseId
	 * @param bonusName
	 * @return
	 */
	public List<CaseBonusResponse> getBonusPrizeUser(long caseId, String bonusName) {
		List<CaseBonusResponse> list = new ArrayList<CaseBonusResponse>();
		List<Map<String, Object>> mapList = LogDao.getBonusPrizeUser(caseId, bonusName);
		Map<String, DBonusPercentConfig> bonusPercentConfigMap = bonusService.getBonusPercentConfigMap();
		for (Map<String, Object> map : mapList) {
			CaseBonusResponse caseBonusResponse = new CaseBonusResponse();

			DUser user = userService.getUserById(StringUtil.getLong(map.get("user_id")));
			if (user == null) {
				continue;
			}
			caseBonusResponse.setUserId(user.getId());
			caseBonusResponse.setNickname(user.getNickname());
			caseBonusResponse.setAvatarImage(user.getAvatarImage());
			caseBonusResponse.setLevel(user.getLevel());

			caseBonusResponse.setPoints(StringUtil.getInt(map.get("bonus_points")));
			caseBonusResponse.setCreateTime(StringUtil.getInt(map.get("create_time")) * 1000L);

			String remark = StringUtil.getString(map.get("bonus_name"));
			DBonusPercentConfig bonusPercentConfig = bonusPercentConfigMap.get(remark);
			caseBonusResponse.setRemark(bonusPercentConfig.getRemark());

			list.add(caseBonusResponse);
		}
		return list;
	}

	/**
	 * 查询专案分红普通参与用户数据
	 * @param caseId
	 * @return
	 */
	public List<CaseBonusResponse> getBonusCommonUser(long caseId) {
		List<CaseBonusResponse> list = new ArrayList<CaseBonusResponse>();
		List<Map<String, Object>> mapList = LogDao.getBonusCommonUser(caseId);
		Map<String, DBonusPercentConfig> bonusPercentConfigMap = bonusService.getBonusPercentConfigMap();
		for (Map<String, Object> map : mapList) {

			int points = StringUtil.getInt(map.get("bonus_points_average"));
			long createTime = StringUtil.getInt(map.get("create_time")) * 1000L;
			String remark = StringUtil.getString(map.get("bonus_name"));

			String userIdListStr = StringUtil.getString(map.get("user_id_list"));
			List<Object> userIdList = JSONObject.decode(userIdListStr, List.class);

			if (userIdList != null && !userIdList.isEmpty()) {
				for (Object id : userIdList) {
					long userId = StringUtil.getLong(id);
					CaseBonusResponse caseBonusResponse = new CaseBonusResponse();
					DUser user = userService.getUserById(userId);
					if (user == null) {
						continue;
					}
					caseBonusResponse.setUserId(user.getId());
					caseBonusResponse.setNickname(user.getNickname());
					caseBonusResponse.setAvatarImage(user.getAvatarImage());
					caseBonusResponse.setLevel(user.getLevel());

					caseBonusResponse.setPoints(points);
					caseBonusResponse.setCreateTime(createTime);
					DBonusPercentConfig bonusPercentConfig = bonusPercentConfigMap.get(remark);
					caseBonusResponse.setRemark(bonusPercentConfig.getRemark());

					list.add(caseBonusResponse);
				}
			}
		}
		return list;
	}

	/**
	 * 查询专案分红普通参与用户数据
	 * @param caseId
	 * @param bonusName
	 * @return
	 */
	public List<CaseBonusResponse> getBonusCommonUser(long caseId, String bonusName) {
		List<CaseBonusResponse> list = new ArrayList<CaseBonusResponse>();
		List<Map<String, Object>> mapList = LogDao.getBonusCommonUser(caseId, bonusName);
		Map<String, DBonusPercentConfig> bonusPercentConfigMap = bonusService.getBonusPercentConfigMap();
		for (Map<String, Object> map : mapList) {

			int points = StringUtil.getInt(map.get("bonus_points_average"));
			long createTime = StringUtil.getInt(map.get("create_time")) * 1000L;
			String remark = StringUtil.getString(map.get("bonus_name"));

			String userIdListStr = StringUtil.getString(map.get("user_id_list"));
			List<Object> userIdList = JSONObject.decode(userIdListStr, List.class);

			if (userIdList != null && !userIdList.isEmpty()) {
				for (Object id : userIdList) {
					long userId = StringUtil.getLong(id);
					CaseBonusResponse caseBonusResponse = new CaseBonusResponse();
					DUser user = userService.getUserById(userId);
					if (user == null) {
						continue;
					}
					caseBonusResponse.setUserId(user.getId());
					caseBonusResponse.setNickname(user.getNickname());
					caseBonusResponse.setAvatarImage(user.getAvatarImage());
					caseBonusResponse.setLevel(user.getLevel());

					caseBonusResponse.setPoints(points);
					caseBonusResponse.setCreateTime(createTime);
					DBonusPercentConfig bonusPercentConfig = bonusPercentConfigMap.get(remark);
					caseBonusResponse.setRemark(bonusPercentConfig.getRemark());

					list.add(caseBonusResponse);
				}
			}
		}
		return list;
	}

	public List<CaseBonusResponse> getBonusUserLog(long caseId) {
		List<CaseBonusResponse> list = new ArrayList<CaseBonusResponse>();
		String key = KeyFactory.CaseFinishBonusUserLogListKey(caseId);

		Set<String> listFromCache = redis.SORTSET.zrevrange(key, 0, -1);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCaseFinishBonusUserLog> bonusUserLogList = LogDao.getBonuUserLogList(caseId);
			if (bonusUserLogList == null || bonusUserLogList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return list;
			}
			int size = bonusUserLogList.size();
			listFromCache = new LinkedHashSet<String>(size);
			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCaseFinishBonusUserLog bonusUserLog : bonusUserLogList) {
				String id = String.valueOf(bonusUserLog.getId());
				scoreMembers.put(id, bonusUserLog.getBonusPoints() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取创意评论
		Map<Long, DCaseFinishBonusUserLog> bonusUserLogMap = multiGetBonusUserLog(listFromCache);

		// 组织评论列表
		Set<String> userIdSet = new LinkedHashSet<String>(listFromCache.size());
		for (Entry<Long, DCaseFinishBonusUserLog> e : bonusUserLogMap.entrySet()) {
			DCaseFinishBonusUserLog bonusUserLog = e.getValue();

			CaseBonusResponse caseBonusResponse = new CaseBonusResponse();
			caseBonusResponse.setPoints(bonusUserLog.getBonusPoints());
			caseBonusResponse.setCreateTime(bonusUserLog.getCreateTime());
			caseBonusResponse.setRemark(bonusUserLog.getBonusRemark());
			caseBonusResponse.setUserId(bonusUserLog.getUserId());

			list.add(caseBonusResponse);

			// 用来获取用户信息
			userIdSet.add(String.valueOf(bonusUserLog.getUserId()));
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<CaseBonusResponse> it = list.iterator();
		while (it.hasNext()) {
			CaseBonusResponse caseBonusResponse = it.next();
			DUser user = userMap.get(caseBonusResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("CaseFinishService.getBonusUserLog, can not get user, userId: {}", caseBonusResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			caseBonusResponse.setUserBase(userBaseResponse);
			caseBonusResponse.setUserId(user.getId());
			caseBonusResponse.setNickname(user.getNickname());
			caseBonusResponse.setAvatarImage(user.getAvatarImage());
			caseBonusResponse.setLevel(user.getLevel());
		}

		return list;
	}

	/**
	 * 批量获取
	 * @param bonusUserLogIdSet
	 * @return
	 */
	private Map<Long, DCaseFinishBonusUserLog> multiGetBonusUserLog(Set<String> bonusUserLogIdSet) {
		// 去掉占位符
		bonusUserLogIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (bonusUserLogIdSet.isEmpty()) {
			return new HashMap<Long, DCaseFinishBonusUserLog>(0);
		}
		// 首先去缓存拿
		int size = bonusUserLogIdSet.size();
		List<String> bonusUserLogKeyList = new ArrayList<String>(size);
		for (String bonusUserLogId : bonusUserLogIdSet) {
			bonusUserLogKeyList.add(KeyFactory.CaseFinishBonusUserLogKey(StringUtil.getLong(bonusUserLogId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(bonusUserLogKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DCaseFinishBonusUserLog> data = new LinkedHashMap<>(size);
		for (String bonusUserLogId : bonusUserLogIdSet) {
			data.put(StringUtil.getLong(bonusUserLogId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DCaseFinishBonusUserLog bonusUserLog = SerialUtil.fromBytes(bytes, DCaseFinishBonusUserLog.class);
				if (bonusUserLog == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(bonusUserLog.getId(), bonusUserLog);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpBonusUserLogIdSet = new HashSet<Long>(size);
		for (Entry<Long, DCaseFinishBonusUserLog> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpBonusUserLogIdSet.add(e.getKey());
			}
		}
		if (tmpBonusUserLogIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DCaseFinishBonusUserLog> bonusUserLogList = LogDao.getBonusUserLog(tmpBonusUserLogIdSet);
		if (bonusUserLogList == null || bonusUserLogList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(bonusUserLogList.size());
		for (DCaseFinishBonusUserLog bonusUserLog : bonusUserLogList) {
			// 如果数据库查到就回写到缓存
			data.put(bonusUserLog.getId(), bonusUserLog);
			keysValuesMap.put(KeyFactory.CaseFinishBonusUserLogKey(bonusUserLog.getId()).getBytes(), SerialUtil.toBytes(bonusUserLog));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}
}
