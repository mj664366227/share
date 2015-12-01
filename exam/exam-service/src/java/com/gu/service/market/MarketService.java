package com.gu.service.market;

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

import com.gu.core.protocol.MarketOpusCommentListResponse;
import com.gu.core.protocol.MarketOpusCommentResponse;
import com.gu.core.protocol.MarketOpusListResponse;
import com.gu.core.protocol.MarketOpusResponse;
import com.gu.core.protocol.base.UserBaseResponse;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.dao.MarketDao;
import com.gu.dao.model.DMarketOpus;
import com.gu.dao.model.DMarketOpusComment;
import com.gu.dao.model.DMarketOpusPraise;
import com.gu.dao.model.DUser;
import com.gu.service.common.CommonService;
import com.gu.service.user.UserService;

/**
 * 创意圈service
 * @author luo
 */
@Component
public class MarketService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(MarketService.class);
	@Autowired
	private Redis redis;
	@Autowired
	private MarketDao marketDao;
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
	 * 发布一条创意圈_作品
	 * @param userId 用户ID
	 * @param title 标题
	 * @param content 内容
	 * @param image1 图片1
	 * @param image2 图片2
	 * @param image3 图片3
	 * @return
	 */
	public DMarketOpus addMarketOpus(long userId, String title, String content, String image1, String image2, String image3) {
		DMarketOpus marketOpus = marketDao.addMarketOpus(userId, title, content, image1, image2, image3);
		if (marketOpus == null) {
			return null;
		}

		// 单独统计点赞数
		commonService.setCountColumnRedis(CountKey.marketOpusPraise, marketOpus.getId(), 0);
		// 单独统计评论数
		commonService.setCountColumnRedis(CountKey.marketOpusCommentNum, marketOpus.getId(), 0);
		
		
		redis.STRINGS.setex(KeyFactory.marketOpusKey(marketOpus.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(marketOpus));
		redis.SORTSET.zadd(KeyFactory.marketOpusListKey(), marketOpus.getId(), String.valueOf(marketOpus.getId()));
		redis.SORTSET.zadd(KeyFactory.marketOpusListKey(marketOpus.getUserId()), marketOpus.getId(), String.valueOf(marketOpus.getId()));
		return marketOpus;
	}

	/**
	 * 添加创意圈_作品评论
	 * @param userId 用户ID
	 * @param marketOpusId 作品ID
	 * @param content 评论内容
	 * @return
	 */
	public DMarketOpusComment addMarketOpusComment(long userId, long marketOpusId, String content, long replyUserId) {
		DMarketOpusComment marketOpusComment = marketDao.addMarketOpusComment(userId, marketOpusId, content, replyUserId);
		if (marketOpusComment == null) {
			return null;
		}
		redis.STRINGS.setex(KeyFactory.marketOpusCommentKey(marketOpusComment.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(marketOpusComment));
		redis.SORTSET.zadd(KeyFactory.marketOpusCommentListKey(marketOpusComment.getMarketOpusId()), marketOpusComment.getId(), String.valueOf(marketOpusComment.getId()));
		return marketOpusComment;
	}

	/**
	 * 添加创意圈_作品点赞记录
	 * @param userId
	 * @param marketOpusId
	 * @return
	 */
	public DMarketOpusPraise addMarketOpusPraise(long userId, DMarketOpus marketOpus) {
		DMarketOpusPraise marketOpusPraise = marketDao.addMarketOpusPraise(userId, marketOpus.getId());
		if (marketOpusPraise == null) {
			return null;
		}
		commonService.updateCountColumn(CountKey.marketOpusPraise, marketOpus.getId(), 1);
		redis.SORTSET.zadd(KeyFactory.marketOpusPraiseKey(marketOpus.getId()), marketOpusPraise.getId(), String.valueOf(userId));
		return marketOpusPraise;
	}

	/**
	 * 删除创意圈_作品点赞记录
	 * @param userId
	 * @param marketOpusId
	 * @return
	 */
	public boolean delMarketOpusPraise(long userId, DMarketOpus marketOpus) {
		boolean b = marketDao.delMarketOpusPraise(userId, marketOpus.getId());
		if (b) {
			commonService.updateCountColumn(CountKey.marketOpusPraise, marketOpus.getId(), -1);
			redis.SORTSET.zrem(KeyFactory.marketOpusPraiseKey(marketOpus.getId()), String.valueOf(userId));
		}
		return b;
	}

	/**
	 * 查询创意圈_作品 By marketOpusId
	 * @param marketOpusId 创意圈_作品ID
	 */
	public DMarketOpus getMarketOpusById(long marketOpusId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.marketOpusKey(marketOpusId).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			DMarketOpus marketOpus = marketDao.getMarketOpusById(marketOpusId);
			if (marketOpus == null) {
				return null;
			}
			//写入Redis缓存
			redis.STRINGS.setex(KeyFactory.marketOpusKey(marketOpus.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(marketOpus));
			redis.SORTSET.zadd(KeyFactory.marketOpusListKey(), marketOpus.getId(), String.valueOf(marketOpus.getId()));
			redis.SORTSET.zadd(KeyFactory.marketOpusListKey(marketOpus.getUserId()), marketOpus.getId(), String.valueOf(marketOpus.getId()));
			return marketOpus;
		}

		return SerialUtil.fromBytes(b, DMarketOpus.class);
	}

	/**
	 * 查询创意圈_作品评论 By marketOpusCommentId
	 * @param marketOpusCommentId 创意圈_作品评论ID
	 */
	public DMarketOpusComment getMarketOpusCommentById(long marketOpusCommentId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.marketOpusCommentKey(marketOpusCommentId).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			DMarketOpusComment marketOpusComment = marketDao.getMarketOpusCommentById(marketOpusCommentId);
			if (marketOpusComment == null) {
				return null;
			}
			//写入Redis缓存
			redis.STRINGS.setex(KeyFactory.marketOpusCommentKey(marketOpusComment.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(marketOpusComment));
			redis.SORTSET.zadd(KeyFactory.marketOpusCommentListKey(marketOpusComment.getMarketOpusId()), marketOpusComment.getId(), String.valueOf(marketOpusComment.getId()));
			return marketOpusComment;
		}

		return SerialUtil.fromBytes(b, DMarketOpusComment.class);
	}

	/**
	 * 更新marketOpus
	 * @param marketOpus
	 * @return
	 */
	public boolean updateMarketOpus(DMarketOpus marketOpus) {
		boolean updateSuccess = marketDao.updateMarketOpus(marketOpus);
		if (updateSuccess) {
			redis.STRINGS.setex(KeyFactory.marketOpusKey(marketOpus.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(marketOpus));
		}
		return updateSuccess;
	}

	/**
	 * 创意圈_作品列表(全部)
	 * @param currentUserId 当前用户
	 * @param lastMarketOpusId
	 * @param pageSize
	 * @return
	 */
	public MarketOpusListResponse getMarketOpusList(long currentUserId, long lastMarketOpusId, int pageSize) {
		return getMarketOpusList(currentUserId, 0, lastMarketOpusId, pageSize);
	}

	/**
	 * 创意圈_作品列表(个人)
	 * @param currentUserId 当前用户
	 * @param userId 要查看的用户
	 * @param lastMarketOpusId
	 * @param pageSize
	 * @return
	 */
	public MarketOpusListResponse getMarketOpusList(long currentUserId, long userId, long lastMarketOpusId, int pageSize) {
		MarketOpusListResponse marketOpusListResponse = new MarketOpusListResponse();
		String key = KeyFactory.marketOpusListKey(userId);

		// 只有第一页才拿总条数
		if (lastMarketOpusId == Long.MAX_VALUE) {
			long total = redis.SORTSET.zcard(key);
			if (redis.SORTSET.zscore(key, "-1") == -1) {
				total -= 1;
			}
			marketOpusListResponse.setTotal((int) total);
		}

		// 先从缓存拿数据
		lastMarketOpusId = lastMarketOpusId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastMarketOpusId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DMarketOpus> marketOpusList = marketDao.getMarketOpusList(lastMarketOpusId, pageSize);
			if (marketOpusList == null || marketOpusList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return marketOpusListResponse;
			}
			int size = marketOpusList.size();
			listFromCache = new LinkedHashSet<String>(size);
			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DMarketOpus marketOpus : marketOpusList) {
				String id = String.valueOf(marketOpus.getId());
				scoreMembers.put(id, StringUtil.getDouble(id));
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		//批量获取创意
		Map<Long, DMarketOpus> marketOpusMap = multiGetMarketOpus(listFromCache);
		Map<Long, Integer> marketOpusPraiseMap = commonService.multiGetCountColumn(listFromCache, CountKey.marketOpusPraise);

		//组织创意列表
		Set<String> userIdSet = new LinkedHashSet<String>(pageSize);
		List<MarketOpusResponse> marketOpusResponseList = new ArrayList<MarketOpusResponse>();
		for (Entry<Long, DMarketOpus> e : marketOpusMap.entrySet()) {
			DMarketOpus marketOpus = e.getValue();
			MarketOpusResponse marketOpusResponse = new MarketOpusResponse();
			marketOpusResponse.setMarketOpusId(marketOpus.getId());
			marketOpusResponse.setUserId(marketOpus.getUserId());
			marketOpusResponse.setCreateTime(marketOpus.getCreateTime());
			marketOpusResponse.setPraise(StringUtil.getInt(marketOpusPraiseMap.get(marketOpus.getId())));
			marketOpusResponse.setTitle(marketOpus.getTitle());
			marketOpusResponse.setContent(marketOpus.getContent());
			marketOpusResponse.setImageList(marketOpus.getImageList());
			//获取创意评论总数
			int marketOpusCommentNum = commonService.getCountColumn(CountKey.marketOpusCommentNum, marketOpus.getId());
			marketOpusResponse.setOpusCommentCount(marketOpusCommentNum);
			marketOpusResponseList.add(marketOpusResponse);

			//用来获取用户信息
			userIdSet.add(String.valueOf(marketOpus.getUserId()));
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<MarketOpusResponse> it = marketOpusResponseList.iterator();
		while (it.hasNext()) {
			MarketOpusResponse marketOpusResponse = it.next();
			DUser user = userMap.get(marketOpusResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("MarketService.getMarketOpusList, can not get user, userId: {}", marketOpusResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			marketOpusResponse.setUserBase(userBaseResponse);
			if (currentUserId > 0) {
				marketOpusResponse.setIsPraise(isMarketOpusPraise(currentUserId, marketOpusResponse.getMarketOpusId()) ? 1 : 0);
			}
		}
		marketOpusListResponse.setMarketOpusResponseList(marketOpusResponseList);
		return marketOpusListResponse;
	}

	/**
	 * 批量获取创意圈_作品
	 * @param marketOpusIdSet 创意圈_作品id集合
	 */
	public Map<Long, DMarketOpus> multiGetMarketOpus(Set<String> marketOpusIdSet) {
		// 去掉占位符
		marketOpusIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (marketOpusIdSet.isEmpty()) {
			return new HashMap<Long, DMarketOpus>(0);
		}
		// 首先去缓存拿
		int size = marketOpusIdSet.size();
		List<String> marketOpusKeyList = new ArrayList<String>(size);
		for (String marketOpusId : marketOpusIdSet) {
			marketOpusKeyList.add(KeyFactory.marketOpusKey(StringUtil.getLong(marketOpusId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(marketOpusKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DMarketOpus> data = new LinkedHashMap<>(size);
		for (String marketOpusId : marketOpusIdSet) {
			data.put(StringUtil.getLong(marketOpusId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DMarketOpus marketOpus = SerialUtil.fromBytes(bytes, DMarketOpus.class);
				if (marketOpus == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(marketOpus.getId(), marketOpus);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpMarketOpusIdSet = new HashSet<Long>(size);
		for (Entry<Long, DMarketOpus> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpMarketOpusIdSet.add(e.getKey());
			}
		}
		if (tmpMarketOpusIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DMarketOpus> marketOpusList = marketDao.getMarketOpus(tmpMarketOpusIdSet);
		if (marketOpusList == null || marketOpusList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(marketOpusList.size());
		for (DMarketOpus marketOpus : marketOpusList) {
			// 如果数据库查到就回写到缓存
			data.put(marketOpus.getId(), marketOpus);
			keysValuesMap.put(KeyFactory.marketOpusKey(marketOpus.getId()).getBytes(), SerialUtil.toBytes(marketOpus));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 获取创意评论列表
	 * @param ideaId 创意id
	 * @param lastMarketOpusCommentId 上一页最后一个创意评论id
	 * @param pageSize 页面大小
	 */
	public MarketOpusCommentListResponse getMarketOpusCommentList(DMarketOpus marketOpus, long lastMarketOpusCommentId, int pageSize) {
		MarketOpusCommentListResponse marketOpusCommentListResponse = new MarketOpusCommentListResponse();
		long marketOpusId = marketOpus.getId();
		String key = KeyFactory.marketOpusCommentListKey(marketOpusId);

		// 只有第一页才拿总条数
		if (lastMarketOpusCommentId == Long.MAX_VALUE) {
			marketOpusCommentListResponse.setTotal(commonService.getCountColumn(CountKey.marketOpusCommentNum, marketOpusId));
		}

		// 先从缓存拿数据
		lastMarketOpusCommentId = lastMarketOpusCommentId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastMarketOpusCommentId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DMarketOpusComment> marketOpusCommentList = marketDao.getMarketOpusCommentList(marketOpusId, lastMarketOpusCommentId, pageSize);
			if (marketOpusCommentList == null || marketOpusCommentList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return marketOpusCommentListResponse;
			}
			int size = marketOpusCommentList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DMarketOpusComment marketOpusComment : marketOpusCommentList) {
				String id = String.valueOf(marketOpusComment.getId());
				scoreMembers.put(id, marketOpusComment.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取创意评论
		Map<Long, DMarketOpusComment> marketOpusCommentMap = multiGetMarketOpusComment(listFromCache);

		// 组织评论列表
		Set<String> userIdSet = new LinkedHashSet<String>(pageSize);
		List<MarketOpusCommentResponse> marketOpusCommentResponseList = new ArrayList<MarketOpusCommentResponse>(pageSize);
		for (Entry<Long, DMarketOpusComment> e : marketOpusCommentMap.entrySet()) {
			DMarketOpusComment marketOpusComment = e.getValue();
			MarketOpusCommentResponse marketOpusCommentResponse = new MarketOpusCommentResponse();
			marketOpusCommentResponse.setMarketOpusCommentId(marketOpusComment.getId());
			marketOpusCommentResponse.setUserId(marketOpusComment.getUserId());
			marketOpusCommentResponse.setCreateTime(marketOpusComment.getCreateTime());
			marketOpusCommentResponse.setContent(marketOpusComment.getContent());
			marketOpusCommentResponse.setReplyUserId(marketOpusComment.getReplyUserId());
			marketOpusCommentResponseList.add(marketOpusCommentResponse);

			// 用来获取用户信息
			userIdSet.add(String.valueOf(marketOpusComment.getUserId()));
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<MarketOpusCommentResponse> it = marketOpusCommentResponseList.iterator();
		while (it.hasNext()) {
			MarketOpusCommentResponse marketOpusCommentResponse = it.next();
			DUser user = userMap.get(marketOpusCommentResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("IdeaService.getMarketOpusCommentList, can not get user, userId: {}", marketOpusCommentResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			marketOpusCommentResponse.setUserBase(userBaseResponse);
			//回复的用户
			marketOpusCommentResponse.setReplyUserBase(null);
			if (marketOpusCommentResponse.getReplyUserId() > 0) {
				DUser replyUser = userMap.get(marketOpusCommentResponse.getReplyUserId());
				if (replyUser != null) {
					UserBaseResponse replyUserBsaeResponse = userService.getUserBaseResponse(replyUser);
					marketOpusCommentResponse.setReplyUserBase(replyUserBsaeResponse);
				}
			}
		}
		marketOpusCommentListResponse.setMarketOpusCommentResponseList(marketOpusCommentResponseList);
		return marketOpusCommentListResponse;
	}

	/**
	 * 批量获取创意圈_作品评论
	 * @param marketOpusCommentIdSet 创意圈_作品评论id集合
	 */
	public Map<Long, DMarketOpusComment> multiGetMarketOpusComment(Set<String> marketOpusCommentIdSet) {
		// 去掉占位符
		marketOpusCommentIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (marketOpusCommentIdSet.isEmpty()) {
			return new HashMap<Long, DMarketOpusComment>(0);
		}
		// 首先去缓存拿
		int size = marketOpusCommentIdSet.size();
		List<String> marketOpusCommentKeyList = new ArrayList<String>(size);
		for (String marketOpusCommentId : marketOpusCommentIdSet) {
			marketOpusCommentKeyList.add(KeyFactory.marketOpusCommentKey(StringUtil.getLong(marketOpusCommentId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(marketOpusCommentKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DMarketOpusComment> data = new LinkedHashMap<>(size);
		for (String marketOpusCommentId : marketOpusCommentIdSet) {
			data.put(StringUtil.getLong(marketOpusCommentId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DMarketOpusComment marketOpusComment = SerialUtil.fromBytes(bytes, DMarketOpusComment.class);
				if (marketOpusComment == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(marketOpusComment.getId(), marketOpusComment);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpMarketOpusCommentIdSet = new HashSet<Long>(size);
		for (Entry<Long, DMarketOpusComment> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpMarketOpusCommentIdSet.add(e.getKey());
			}
		}
		if (tmpMarketOpusCommentIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DMarketOpusComment> marketOpusCommentList = marketDao.getMarketOpusComment(tmpMarketOpusCommentIdSet);
		if (marketOpusCommentList == null || marketOpusCommentList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(marketOpusCommentList.size());
		for (DMarketOpusComment marketOpusComment : marketOpusCommentList) {
			// 如果数据库查到就回写到缓存
			data.put(marketOpusComment.getId(), marketOpusComment);
			keysValuesMap.put(KeyFactory.marketOpusCommentKey(marketOpusComment.getId()).getBytes(), SerialUtil.toBytes(marketOpusComment));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 判断用户点赞创意圈_作品记录(防重复)
	 * @param userId 用户id
	 * @param marketOpusId 创意id
	 * @return true=已经点赞,false=没有点赞
	 */
	public boolean isMarketOpusPraise(long userId, long marketOpusId) {
		if (userId <= 0 || marketOpusId <= 0) {
			return false;
		}
		boolean isPraise = redis.SORTSET.zscore(KeyFactory.marketOpusPraiseKey(marketOpusId), String.valueOf(userId)) > 0;
		if (!isPraise) {
			DMarketOpusPraise marketOpusPraise = marketDao.getMarketOpusPraise(userId, marketOpusId);
			if (marketOpusPraise == null) {
				return false;
			}
			redis.SORTSET.zadd(KeyFactory.marketOpusPraiseKey(marketOpusId), marketOpusPraise.getId(), String.valueOf(userId));
			isPraise = true;
		}
		return isPraise;
	}

}
