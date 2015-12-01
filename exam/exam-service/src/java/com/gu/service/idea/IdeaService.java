package com.gu.service.idea;

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

import com.google.gson.reflect.TypeToken;
import com.gu.core.protocol.CompanyCommentListResponse;
import com.gu.core.protocol.CompanyCommentResponse;
import com.gu.core.protocol.IdeaCommentListResponse;
import com.gu.core.protocol.IdeaCommentResponse;
import com.gu.core.protocol.IdeaListResponse;
import com.gu.core.protocol.IdeaResponse;
import com.gu.core.protocol.base.UserBaseResponse;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.FileSystem;
import com.gu.core.util.JSONObject;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.dao.CaseCountDao;
import com.gu.dao.CompanyCountDao;
import com.gu.dao.IdeaDao;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DIdeaComment;
import com.gu.dao.model.DIdeaCompanyComment;
import com.gu.dao.model.DIdeaPraise;
import com.gu.dao.model.DUser;
import com.gu.service.cases.CaseService;
import com.gu.service.common.CommonService;
import com.gu.service.user.UserService;

/**
 * 创意(点子)service
 * @author luo
 */
@Component
public class IdeaService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(IdeaService.class);
	@Autowired
	private Redis redis;
	@Autowired
	private IdeaDao ideaDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyCountDao companyCountDao;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CaseCountDao caseCountDao;
	@Autowired
	private CommonService commonService;
	@Autowired
	private GuThreadPool guThreadPool;

	private final static long long12 = 1000000000000l;

	/**
	 * SortSet列表加占位
	 * @param key
	 */
	public void addSortSetAnchor(String key) {
		redis.SORTSET.zadd(key, -1, "-1");
	}

	/**
	 * 发布一条创意
	 * @param user 用户对象
	 * @param caseId 专案ID
	 * @param createTime 添加时间
	 * @param title 标题
	 * @param content 内容
	 * @param image1 图片1
	 * @param image2 图片2
	 * @param image3 图片3
	 * @param dCase 专案对象
	 */
	public DIdea addIdea(DUser user, long caseId, String title, String content, String visit, String image1, String image2, String image3, DCase dCase) {
		DIdea idea = ideaDao.addIdea(user.getId(), caseId, title, content, visit, image1, image2, image3);
		if (idea == null) {
			return null;
		}

		// 单独统计点赞数
		commonService.setCountColumnRedis(CountKey.ideaPraise, idea.getId(), 0);
		// 单独统计评论数
		commonService.setCountColumnRedis(CountKey.ideaCommentNum, idea.getId(), 0);

		guThreadPool.execute(() -> {

			// 发布点子算参与
			caseService.takePartInCase(user.getId(), caseId);

			redis.STRINGS.setex(KeyFactory.ideaKey(idea.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(idea));
			//专案的创意排名
			int praise = commonService.getCountColumn(CountKey.ideaPraise, idea.getId());
			redis.SORTSET.zadd(KeyFactory.ideaTopKey(caseId), getScore(praise, idea.getLastPraiseTime()), String.valueOf(idea.getId()));
			//专案的创意列表
			redis.SORTSET.zadd(KeyFactory.ideaListKey(caseId), idea.getId(), String.valueOf(idea.getId()));
			//创意只能发一次
			redis.SORTSET.zadd(KeyFactory.userIdeaPubKey(caseId), user.getId(), String.valueOf(user.getId()));

			// 加创意统计
			caseCountDao.addCaseCountIdea(caseId);
			int ideaNum = commonService.getCountColumn(CountKey.caseIdeaNum, dCase.getId());
			caseCountDao.addCaseCountIdeaTotal(dCase, ideaNum);
			companyCountDao.addCompanyCountIdea(dCase.getCompanyId());
		});
		return idea;
	}

	/**
	 * 删除点子
	 * @author ruan 
	 * @param idea 点子对象
	 */
	public void deleteIdea(final DIdea idea) {
		if (idea == null) {
			return;
		}
		guThreadPool.execute(() -> {
			// 删除数据库
			ideaDao.deleteIdea(idea.getId());

			// 删除缓存
			redis.KEYS.del(KeyFactory.ideaKey(idea.getId()));

			// 删除专案的创意排名
			redis.SORTSET.zrem(KeyFactory.ideaTopKey(idea.getCaseId()), String.valueOf(idea.getId()));

			// 删除专案的创意列表
			redis.SORTSET.zrem(KeyFactory.ideaListKey(idea.getCaseId()), String.valueOf(idea.getId()));

			// 删除创意只能发一次
			redis.SORTSET.zrem(KeyFactory.userIdeaPubKey(idea.getCaseId()), String.valueOf(idea.getUserId()));

			// 点子数-1
			commonService.updateCountColumn(CountKey.caseIdeaNum, idea.getCaseId(), -1);
		});
	}

	/**
	 * 添加创意评论
	 * @param userId 用户ID
	 * @param ideaId 创意ID
	 * @param caseId 专案id
	 * @param content 评论内容
	 * @param replyUserId 回复的用户ID
	 */
	public DIdeaComment addIdeaComment(long userId, long ideaId, long caseId, String content, long replyUserId) {
		DIdeaComment ideaComment = ideaDao.addIdeaComment(userId, ideaId, content, replyUserId);
		if (ideaComment == null) {
			return null;
		}

		redis.STRINGS.setex(KeyFactory.ideaCommentKey(ideaComment.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(ideaComment));
		redis.SORTSET.zadd(KeyFactory.ideaCommentListKey(ideaId), ideaComment.getId(), String.valueOf(ideaComment.getId()));
		return ideaComment;
	}

	/**
	 * 企业回复/评论创意
	 * @param companyId 企业ID
	 * @param ideaId 创意ID
	 * @param userId 用户id
	 * @param content 评论内容
	 */
	public DIdeaCompanyComment companyAddIdeaCompanyComment(long companyId, long ideaId, long userId, String content) {
		return addIdeaCompanyComment(companyId, ideaId, userId, content, 1);
	}

	/**
	 * 用户回复/评论创意
	 * @param companyId 企业ID
	 * @param ideaId 创意ID
	 * @param userId 用户id
	 * @param content 评论内容
	 */
	public DIdeaCompanyComment userAddIdeaCompanyComment(long companyId, long ideaId, long userId, String content) {
		return addIdeaCompanyComment(companyId, ideaId, userId, content, 2);
	}

	/**
	 * 添加企业回复/评论创意
	 * @param companyId 企业ID
	 * @param ideaId 创意ID
	 * @param userId 用户id
	 * @param content 评论内容
	 * @param action 动作(1-我回复了xx 2-xx回复了我)
	 */
	private DIdeaCompanyComment addIdeaCompanyComment(long companyId, long ideaId, long userId, String content, int action) {
		DIdeaCompanyComment ideaCompanyComment = ideaDao.addIdeaCompanyComment(companyId, ideaId, userId, content, action);
		if (ideaCompanyComment == null) {
			return null;
		}

		redis.STRINGS.setex(KeyFactory.ideaCompanyCommentKey(ideaCompanyComment.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(ideaCompanyComment));
		redis.SORTSET.zadd(KeyFactory.ideaCompanyCommentListKey(ideaId), ideaCompanyComment.getId(), String.valueOf(ideaCompanyComment.getId()));
		return ideaCompanyComment;
	}

	/**
	 * 查询创意 By ideaId
	 * @param ideaId 创意ID
	 */
	public DIdea getIdeaById(long ideaId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.ideaKey(ideaId).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			DIdea idea = ideaDao.getIdeaById(ideaId);
			if (idea == null) {
				return null;
			}

			int praise = commonService.getCountColumn(CountKey.ideaPraise, idea.getId());
			//写入Redis缓存
			redis.STRINGS.setex(KeyFactory.ideaKey(idea.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(idea));
			redis.SORTSET.zadd(KeyFactory.ideaTopKey(idea.getCaseId()), getScore(praise, idea.getLastPraiseTime()), String.valueOf(idea.getId()));
			redis.SORTSET.zadd(KeyFactory.ideaListKey(idea.getCaseId()), idea.getId(), String.valueOf(idea.getId()));
			return idea;
		}

		return SerialUtil.fromBytes(b, DIdea.class);
	}

	/**
	 * 查询创意评论 By ideaCommentId
	 * @param ideaCommentId 创意评论ID
	 */
	public DIdeaComment getIdeaCommentById(long ideaCommentId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.ideaCommentKey(ideaCommentId).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			DIdeaComment ideaComment = ideaDao.getIdeaCommentById(ideaCommentId);
			if (ideaComment == null) {
				return null;
			}
			//写入Redis缓存
			redis.STRINGS.setex(KeyFactory.ideaCommentKey(ideaComment.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(ideaComment));
			redis.SORTSET.zadd(KeyFactory.ideaCommentListKey(ideaComment.getIdeaId()), ideaComment.getId(), String.valueOf(ideaComment.getId()));
			return ideaComment;
		}

		return SerialUtil.fromBytes(b, DIdeaComment.class);
	}

	/**
	 * 创意评论
	 * @author luo 
	 * @param ideaComment 创意评论对象
	 */
	public void deleteIdeaComment(final DIdeaComment ideaComment) {
		if (ideaComment == null) {
			return;
		}
		guThreadPool.execute(() -> {
			// 删除数据库
			ideaDao.deleteIdeaComment(ideaComment.getId());

			// 删除缓存
			redis.KEYS.del(KeyFactory.ideaCommentKey(ideaComment.getId()));

			// 删除创意评论列表
			redis.SORTSET.zrem(KeyFactory.ideaCommentListKey(ideaComment.getIdeaId()), String.valueOf(ideaComment.getId()));

			// 评论数-1
			commonService.updateCountColumn(CountKey.ideaCommentNum, ideaComment.getIdeaId(), -1);

			logger.warn("delete ideaComment {}", ideaComment.getId());
		});
	}

	/**
	 * 企业回复创意评论列表
	 * @author ruan 
	 * @param ideaId 创意id
	 */
	public CompanyCommentListResponse getIdeaCompanyComment(long ideaId) {
		CompanyCommentListResponse companyCommentListResponse = new CompanyCommentListResponse();
		String key = KeyFactory.ideaCompanyCommentListKey(ideaId);

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, 0, Integer.MAX_VALUE);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DIdeaCompanyComment> ideaCompanyCommentList = ideaDao.getIdeaCompanyComment(ideaId);
			if (ideaCompanyCommentList == null || ideaCompanyCommentList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return companyCommentListResponse;
			}
			int size = ideaCompanyCommentList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DIdeaCompanyComment ideaCompanyComment : ideaCompanyCommentList) {
				String id = String.valueOf(ideaCompanyComment.getId());
				scoreMembers.put(id, StringUtil.getDouble(id));
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取企业回复创意评论
		Map<Long, DIdeaCompanyComment> ideaCompanyCommentMap = multiGetCompanyComment(listFromCache);
		Set<String> userIdSet = new HashSet<>(ideaCompanyCommentMap.size());
		for (Entry<Long, DIdeaCompanyComment> e : ideaCompanyCommentMap.entrySet()) {
			DIdeaCompanyComment ideaCompanyComment = e.getValue();
			if (ideaCompanyComment == null) {
				continue;
			}
			CompanyCommentResponse companyCommentResponse = new CompanyCommentResponse();
			companyCommentResponse.setUserId(ideaCompanyComment.getUserId());
			companyCommentResponse.setContent(ideaCompanyComment.getContent());
			companyCommentResponse.setAction(ideaCompanyComment.getAction());
			companyCommentResponse.setCreateTime(ideaCompanyComment.getCreateTime());
			companyCommentListResponse.addList(companyCommentResponse);
			userIdSet.add(String.valueOf(ideaCompanyComment.getUserId()));
		}

		// 评论获取用户
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		for (CompanyCommentResponse companyCommentResponse : companyCommentListResponse.getList()) {
			DUser user = userMap.get(companyCommentResponse.getUserId());
			if (user == null) {
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			companyCommentResponse.setUserBase(userBaseResponse);
			companyCommentResponse.setUsername(user.getNickname());
		}
		return companyCommentListResponse;
	}

	/**
	 * 企业回复创意评论列表
	 * @author ruan 
	 * @param ideaId 创意id
	 */
	public CompanyCommentListResponse getIdeaCompanyCommentList(DIdea idea, long lastIdeaCompanyCommentId, int pageSize) {
		CompanyCommentListResponse companyCommentListResponse = new CompanyCommentListResponse();
		long ideaId = idea.getId();
		String key = KeyFactory.ideaCompanyCommentListKey(ideaId);

		// 只有第一页才拿总条数
		if (lastIdeaCompanyCommentId == Long.MAX_VALUE) {
			int total = (int) redis.SORTSET.zcard(key);
			if (redis.SORTSET.zscore(key, "-1") == -1) {
				total -= 1;
			}
			companyCommentListResponse.setTotal(total);
		}

		// 先从缓存拿数据
		lastIdeaCompanyCommentId = lastIdeaCompanyCommentId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastIdeaCompanyCommentId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DIdeaCompanyComment> ideaCompanyCommentList = ideaDao.getIdeaCompanyComment(ideaId, lastIdeaCompanyCommentId, pageSize);
			if (ideaCompanyCommentList == null || ideaCompanyCommentList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return companyCommentListResponse;
			}
			int size = ideaCompanyCommentList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DIdeaCompanyComment ideaCompanyComment : ideaCompanyCommentList) {
				String id = String.valueOf(ideaCompanyComment.getId());
				scoreMembers.put(id, ideaCompanyComment.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取创意评论
		Map<Long, DIdeaCompanyComment> ideaCompanyCommentMap = multiGetCompanyComment(listFromCache);

		// 组织评论列表
		Set<String> userIdSet = new LinkedHashSet<String>(pageSize);
		List<CompanyCommentResponse> companyCommentResponseList = new ArrayList<CompanyCommentResponse>(pageSize);
		for (Entry<Long, DIdeaCompanyComment> e : ideaCompanyCommentMap.entrySet()) {
			DIdeaCompanyComment ideaCompanyComment = e.getValue();
			CompanyCommentResponse companyCommentResponse = new CompanyCommentResponse();
			companyCommentResponse.setUserId(ideaCompanyComment.getUserId());
			companyCommentResponse.setContent(ideaCompanyComment.getContent());
			companyCommentResponse.setAction(ideaCompanyComment.getAction());
			companyCommentResponse.setCreateTime(ideaCompanyComment.getCreateTime());
			companyCommentListResponse.addList(companyCommentResponse);
			userIdSet.add(String.valueOf(ideaCompanyComment.getUserId()));

			// 用来获取用户信息
			userIdSet.add(String.valueOf(ideaCompanyComment.getUserId()));
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<CompanyCommentResponse> it = companyCommentResponseList.iterator();
		while (it.hasNext()) {
			CompanyCommentResponse companyCommentResponse = it.next();
			DUser user = userMap.get(companyCommentResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("IdeaService.getIdeaCompanyCommentList, can not get user, userId: {}", companyCommentResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			companyCommentResponse.setUserBase(userBaseResponse);
			companyCommentResponse.setUsername(user.getNickname());
		}
		return companyCommentListResponse;
	}

	/**
	 * 批量获取企业回复创意评论
	 * @author ruan 
	 * @param companyCommentIdSet id集合
	 */
	public Map<Long, DIdeaCompanyComment> multiGetCompanyComment(Set<String> companyCommentIdSet) {
		// 去掉占位符
		companyCommentIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (companyCommentIdSet.isEmpty()) {
			return new HashMap<>(0);
		}
		// 首先去缓存拿
		int size = companyCommentIdSet.size();
		List<String> keyList = new ArrayList<String>(size);
		Map<Long, DIdeaCompanyComment> data = new LinkedHashMap<>(size);
		for (String companyCommentId : companyCommentIdSet) {
			long id = StringUtil.getLong(companyCommentId);
			keyList.add(KeyFactory.ideaCompanyCommentKey(id));
			data.put(id, null);
		}

		// 判断哪些缓存是有的，哪些是缓存没有的
		List<byte[]> byteList = redis.STRINGS.mget(keyList);
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DIdeaCompanyComment ideaCompanyComment = SerialUtil.fromBytes(bytes, DIdeaCompanyComment.class);
				if (ideaCompanyComment == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(ideaCompanyComment.getId(), ideaCompanyComment);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpCompanyCommentIdSet = new HashSet<Long>(size);
		for (Entry<Long, DIdeaCompanyComment> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpCompanyCommentIdSet.add(e.getKey());
			}
		}
		if (tmpCompanyCommentIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DIdeaCompanyComment> ideaCompanyCommentList = ideaDao.getIdeaCompanyComment(tmpCompanyCommentIdSet);
		if (ideaCompanyCommentList == null || ideaCompanyCommentList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(ideaCompanyCommentList.size());
		for (DIdeaCompanyComment ideaCompanyComment : ideaCompanyCommentList) {
			// 如果数据库查到就回写到缓存
			data.put(ideaCompanyComment.getId(), ideaCompanyComment);
			keysValuesMap.put(KeyFactory.ideaCommentKey(ideaCompanyComment.getId()).getBytes(), SerialUtil.toBytes(ideaCompanyComment));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 查询企业回复/评论创意 By ideaCompanyCommentId
	 * @param ideaCompanyCommentId 创意评论ID
	 */
	public DIdeaCompanyComment getIdeaCompanyCommentById(long ideaCompanyCommentId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.ideaCompanyCommentKey(ideaCompanyCommentId).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			DIdeaCompanyComment ideaCompanyComment = ideaDao.getIdeaCompanyCommentById(ideaCompanyCommentId);
			if (ideaCompanyComment == null) {
				return null;
			}
			//写入Redis缓存
			redis.STRINGS.setex(KeyFactory.ideaCompanyCommentKey(ideaCompanyComment.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(ideaCompanyComment));
			redis.SORTSET.zadd(KeyFactory.ideaCompanyCommentListKey(ideaCompanyComment.getIdeaId()), ideaCompanyComment.getId(), String.valueOf(ideaCompanyComment.getId()));
			return ideaCompanyComment;
		}

		return SerialUtil.fromBytes(b, DIdeaCompanyComment.class);
	}

	/**
	 * 查询是否已发表过创意
	 * @param userId 用户ID
	 * @param caseId 专案id
	 * @return
	 */
	public boolean isIdeaPub(long userId, long caseId) {
		// 先查缓存
		// 如果缓存查不到，取mysql 500条，判断一下这500条之中有没有
		// 如果没有，先回写这500条
		// 然后再从mysql读取，如果读得到就回写，读不到就真的没有了
		String key = KeyFactory.userIdeaPubKey(caseId);
		double score = redis.SORTSET.zscore(key, String.valueOf(userId));
		if (score <= 0) {
			// 一次性拿500条
			List<Long> userIdList = ideaDao.getIdeaListUserId(caseId, Long.MAX_VALUE, 500);
			if (userIdList == null || userIdList.isEmpty()) {
				// 再查一次都没有，那就是真的没有了
				return false;
			}

			// 判断这500条有没有
			boolean isIdeaPub = userIdList.contains(String.valueOf(userId));
			if (!isIdeaPub) {
				// 如果没有，再去数据库查一次，然后回写
				DIdea idea = ideaDao.getIdeaByUserIdCaseId(userId, caseId);
				if (idea == null) {
					return false;
				}

				// 如果有，再回写一次
				redis.SORTSET.zadd(KeyFactory.userIdeaPubKey(caseId), userId, String.valueOf(userId));
			}
		}
		return true;
	}

	/**
	 * 判断当前用户是否能看到这个创意
	 * @param currentUserId 当前用户Id
	 * @param userId 创意用户Id
	 * @param visit 权限字段
	 * @return
	 */
	private boolean isVisit(long currentUserId, long userId, String visit) {
		// 如果是web工程直接返回true
		if ("gu-web".equals(FileSystem.getProjectName())) {
			return true;
		}
		if (StringUtil.getString(visit).isEmpty()) {//TODO 兼容1.0 旧数据缓存
			return true;
		}
		if (currentUserId == userId) {//自己能看见自己发表的
			return true;
		}
		HashSet<String> idSet = JSONObject.decode("[" + visit + "]", new TypeToken<HashSet<String>>() {
		}.getType());
		if (idSet.size() == 1) {
			Iterator<String> iterator = idSet.iterator();
			String id = iterator.next();
			switch (id) {
			case "0"://0=不公开
				return false;
			case "-1"://-1=all
				return true;
			case "-2"://-2=friend
				if (currentUserId <= 0) {
					return false;
				}
				return userService.isFocusUser(currentUserId, userId) && userService.isFocusUser(userId, currentUserId);
			default:
				return idSet.contains(String.valueOf(currentUserId));
			}
		}
		return idSet.contains(String.valueOf(currentUserId));
	}

	/**
	 * 获取创意列表
	 * @param currentUserId 当前用户Id
	 * @param dCase 专案对象
	 * @param lastIdeaPraise 上一页最后一个游标(用getScore(praise,createTime)组装)
	 * @param pageSize 页面大小
	 */
	public IdeaListResponse getIdeaList(long currentUserId, DCase dCase, long lastIdeaId, int pageSize) {
		IdeaListResponse ideaListResponse = new IdeaListResponse();
		long caseId = dCase.getId();
		String key = KeyFactory.ideaListKey(caseId);

		// 只有第一页才拿总条数
		if (lastIdeaId == Long.MAX_VALUE) {
			ideaListResponse.setTotal(commonService.getCountColumn(CountKey.caseIdeaNum, dCase.getId()));
		}

		// 先从缓存拿数据
		lastIdeaId = lastIdeaId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastIdeaId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DIdea> ideaList = ideaDao.getIdeaList(caseId, lastIdeaId, pageSize);
			if (ideaList == null || ideaList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return ideaListResponse;
			}
			int size = ideaList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DIdea dIdea : ideaList) {
				String id = String.valueOf(dIdea.getId());
				scoreMembers.put(id, StringUtil.getDouble(id));
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		//批量获取创意
		Map<Long, DIdea> ideaMap = multiGetIdea(listFromCache);
		Map<Long, Integer> praiseMap = commonService.multiGetCountColumn(listFromCache, CountKey.ideaPraise);
		Map<Long, Integer> commentNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.ideaCommentNum);

		//组织创意列表
		Set<String> userIdSet = new LinkedHashSet<String>(pageSize);
		List<IdeaResponse> ideaResponseList = new ArrayList<IdeaResponse>();
		for (Entry<Long, DIdea> e : ideaMap.entrySet()) {
			DIdea idea = e.getValue();
			if (idea == null) {
				ideaListResponse.setTotal(ideaListResponse.getTotal() - 1);
				continue;
			}

			if (!isVisit(currentUserId, idea.getUserId(), idea.getVisit())) {
				ideaListResponse.setTotal(ideaListResponse.getTotal() - 1);
				continue;
			}

			IdeaResponse ideaResponse = new IdeaResponse();
			ideaResponse.setIdeaId(idea.getId());
			ideaResponse.setUserId(idea.getUserId());
			ideaResponse.setCreateTime(idea.getCreateTime());
			ideaResponse.setPraise(StringUtil.getInt(praiseMap.get(idea.getId())));
			ideaResponse.setTitle(idea.getTitle());
			ideaResponse.setContent(idea.getContent());
			ideaResponse.setImageList(idea.getImageList());
			//获取创意评论总数
			ideaResponse.setIdeaCommentCount(StringUtil.getInt(commentNumMap.get(idea.getId())));
			ideaResponseList.add(ideaResponse);

			//用来获取用户信息
			userIdSet.add(String.valueOf(idea.getUserId()));
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<IdeaResponse> it = ideaResponseList.iterator();
		while (it.hasNext()) {
			IdeaResponse ideaResponse = it.next();
			DUser user = userMap.get(ideaResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("IdeaService.getIdeaList, can not get user, userId: {}", ideaResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			ideaResponse.setUserBase(userBaseResponse);
			ideaResponse.setNickname(user.getNickname());
			ideaResponse.setAvatarImage(user.getAvatarImage());
			ideaResponse.setIdentity(user.getIdentity());
			if (currentUserId > 0) {
				ideaResponse.setIsPraise(isIdeaPraise(currentUserId, ideaResponse.getIdeaId()) ? 1 : 0);
			}
		}
		ideaListResponse.setIdeaResponseList(ideaResponseList);
		return ideaListResponse;
	}

	/**
	 * 批量获取创意
	 * @param ideaIdSet 创意id集合
	 */
	public Map<Long, DIdea> multiGetIdea(Set<String> ideaIdSet) {
		// 去掉占位符
		ideaIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (ideaIdSet.isEmpty()) {
			return new HashMap<Long, DIdea>(0);
		}
		// 首先去缓存拿
		int size = ideaIdSet.size();
		List<String> ideaKeyList = new ArrayList<String>(size);
		for (String ideaId : ideaIdSet) {
			ideaKeyList.add(KeyFactory.ideaKey(StringUtil.getLong(ideaId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(ideaKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DIdea> data = new LinkedHashMap<>(size);
		for (String ideaId : ideaIdSet) {
			data.put(StringUtil.getLong(ideaId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DIdea idea = SerialUtil.fromBytes(bytes, DIdea.class);
				if (idea == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(idea.getId(), idea);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpIdeaIdSet = new HashSet<Long>(size);
		for (Entry<Long, DIdea> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpIdeaIdSet.add(e.getKey());
			}
		}
		if (tmpIdeaIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DIdea> ideaList = ideaDao.getIdea(tmpIdeaIdSet);
		if (ideaList == null || ideaList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(ideaList.size());
		for (DIdea idea : ideaList) {
			// 如果数据库查到就回写到缓存
			data.put(idea.getId(), idea);
			keysValuesMap.put(KeyFactory.ideaKey(idea.getId()).getBytes(), SerialUtil.toBytes(idea));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 获取创意评论列表
	 * @param ideaId 创意id
	 * @param lastIdeaCommentId 上一页最后一个创意评论id
	 * @param pageSize 页面大小
	 */
	public IdeaCommentListResponse getIdeaCommentList(DIdea idea, long lastIdeaCommentId, int pageSize) {
		IdeaCommentListResponse ideaCommentListResponse = new IdeaCommentListResponse();
		long ideaId = idea.getId();
		String key = KeyFactory.ideaCommentListKey(ideaId);

		// 只有第一页才拿总条数
		if (lastIdeaCommentId == Long.MAX_VALUE) {
			ideaCommentListResponse.setTotal(commonService.getCountColumn(CountKey.ideaCommentNum, ideaId));
		}

		// 先从缓存拿数据
		lastIdeaCommentId = lastIdeaCommentId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastIdeaCommentId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DIdeaComment> ideaCommentList = ideaDao.getIdeaCommentList(ideaId, lastIdeaCommentId, pageSize);
			if (ideaCommentList == null || ideaCommentList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return ideaCommentListResponse;
			}
			int size = ideaCommentList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DIdeaComment ideaComment : ideaCommentList) {
				String id = String.valueOf(ideaComment.getId());
				scoreMembers.put(id, ideaComment.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取创意评论
		Map<Long, DIdeaComment> ideaCommentMap = multiGetIdeaComment(listFromCache);

		// 组织评论列表
		Set<String> userIdSet = new LinkedHashSet<String>(pageSize);
		List<IdeaCommentResponse> ideaCommentResponseList = new ArrayList<IdeaCommentResponse>(pageSize);
		for (Entry<Long, DIdeaComment> e : ideaCommentMap.entrySet()) {
			DIdeaComment ideaComment = e.getValue();
			IdeaCommentResponse ideaCommentResponse = new IdeaCommentResponse();
			ideaCommentResponse.setIdeaCommentId(ideaComment.getId());
			ideaCommentResponse.setUserId(ideaComment.getUserId());
			ideaCommentResponse.setCreateTime(ideaComment.getCreateTime());
			ideaCommentResponse.setContent(ideaComment.getContent());
			ideaCommentResponse.setReplyUserId(ideaComment.getReplyUserId());
			ideaCommentResponseList.add(ideaCommentResponse);

			// 用来获取用户信息
			userIdSet.add(String.valueOf(ideaComment.getUserId()));
			if (ideaComment.getReplyUserId() != 0) {
				userIdSet.add(String.valueOf(ideaComment.getReplyUserId()));
			}
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<IdeaCommentResponse> it = ideaCommentResponseList.iterator();
		while (it.hasNext()) {
			IdeaCommentResponse ideaCommentResponse = it.next();
			DUser user = userMap.get(ideaCommentResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("IdeaService.getIdeaCommentList, can not get user, userId: {}", ideaCommentResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			ideaCommentResponse.setUserBase(userBaseResponse);
			ideaCommentResponse.setNickname(user.getNickname());
			ideaCommentResponse.setAvatarImage(user.getAvatarImage());
			ideaCommentResponse.setIdentity(user.getIdentity());
			//回复的用户
			ideaCommentResponse.setReplyUserBase(null);
			if (ideaCommentResponse.getReplyUserId() > 0) {
				DUser replyUser = userMap.get(ideaCommentResponse.getReplyUserId());
				if (replyUser != null) {
					UserBaseResponse replyUserBsaeResponse = userService.getUserBaseResponse(replyUser);
					ideaCommentResponse.setReplyUserBase(replyUserBsaeResponse);
				}
			}
		}
		ideaCommentListResponse.setIdeaCommentResponseList(ideaCommentResponseList);
		return ideaCommentListResponse;
	}

	/**
	 * 批量获取创意评论
	 * @param ideaCommentIdSet 创意评论id集合
	 */
	public Map<Long, DIdeaComment> multiGetIdeaComment(Set<String> ideaCommentIdSet) {
		// 去掉占位符
		ideaCommentIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (ideaCommentIdSet.isEmpty()) {
			return new HashMap<Long, DIdeaComment>(0);
		}
		// 首先去缓存拿
		int size = ideaCommentIdSet.size();
		List<String> ideaCommentKeyList = new ArrayList<String>(size);
		for (String ideaCommentId : ideaCommentIdSet) {
			ideaCommentKeyList.add(KeyFactory.ideaCommentKey(StringUtil.getLong(ideaCommentId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(ideaCommentKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DIdeaComment> data = new LinkedHashMap<>(size);
		for (String ideaCommentId : ideaCommentIdSet) {
			data.put(StringUtil.getLong(ideaCommentId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DIdeaComment ideaComment = SerialUtil.fromBytes(bytes, DIdeaComment.class);
				if (ideaComment == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(ideaComment.getId(), ideaComment);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpIdeaCommentIdSet = new HashSet<Long>(size);
		for (Entry<Long, DIdeaComment> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpIdeaCommentIdSet.add(e.getKey());
			}
		}
		if (tmpIdeaCommentIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DIdeaComment> ideaCommentList = ideaDao.getIdeaComment(tmpIdeaCommentIdSet);
		if (ideaCommentList == null || ideaCommentList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(ideaCommentList.size());
		for (DIdeaComment ideaComment : ideaCommentList) {
			// 如果数据库查到就回写到缓存
			data.put(ideaComment.getId(), ideaComment);
			keysValuesMap.put(KeyFactory.ideaCommentKey(ideaComment.getId()).getBytes(), SerialUtil.toBytes(ideaComment));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 创意前n名(创意条数少于20返回0)
	 * @param currentUserId 当前用户id
	 * @param caseId 专案id
	 * @param n 返回最大数量
	 * @param topSize 创意数超过时才显示top3
	 * @return
	 */
	public IdeaListResponse getIdeaTop(long currentUserId, long caseId, int n, int topSize) {
		IdeaListResponse ideaListResponse = new IdeaListResponse();
		String key = KeyFactory.ideaTopKey(caseId);
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, 0, n - 1);
		long ideaCount = redis.SORTSET.zcard(key);
		if (listFromCache == null || listFromCache.isEmpty() || ideaCount < topSize) {
			List<DIdea> ideaList = ideaDao.getIdeaTop(caseId, topSize * 6);
			if (ideaList == null || ideaList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return ideaListResponse;
			}
			int size = ideaList.size();
			listFromCache = new LinkedHashSet<String>(size);
			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			int i = 1;
			for (DIdea idea : ideaList) {
				String id = String.valueOf(idea.getId());
				int praise = commonService.getCountColumn(CountKey.ideaPraise, idea.getId());
				scoreMembers.put(id, getScore(praise, idea.getLastPraiseTime()) * 1D);
				if (i <= n) {//只要前N条
					listFromCache.add(id);
					i++;
				}
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}
		ideaCount = redis.SORTSET.zcard(key);
		if (ideaCount < topSize) {
			return ideaListResponse;
		}

		//批量获取创意
		Map<Long, DIdea> ideaMap = multiGetIdea(listFromCache);
		Map<Long, Integer> praiseMap = commonService.multiGetCountColumn(listFromCache, CountKey.ideaPraise);
		Map<Long, Integer> commentNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.ideaCommentNum);

		//组织创意列表
		Set<String> userIdSet = new LinkedHashSet<String>(listFromCache.size());
		List<IdeaResponse> ideaResponseList = new ArrayList<IdeaResponse>();
		for (Entry<Long, DIdea> e : ideaMap.entrySet()) {
			DIdea idea = e.getValue();
			if (idea == null) {
				ideaListResponse.setTotal(ideaListResponse.getTotal() - 1);
				continue;
			}
			if (!isVisit(currentUserId, idea.getUserId(), idea.getVisit())) {
				continue;
			}
			IdeaResponse ideaResponse = new IdeaResponse();
			ideaResponse.setIdeaId(idea.getId());
			ideaResponse.setUserId(idea.getUserId());
			ideaResponse.setCreateTime(idea.getCreateTime());
			ideaResponse.setPraise(StringUtil.getInt(praiseMap.get(idea.getId())));
			ideaResponse.setTitle(idea.getTitle());
			ideaResponse.setContent(idea.getContent());
			ideaResponse.setImageList(idea.getImageList());
			//获取创意评论总数
			ideaResponse.setIdeaCommentCount(StringUtil.getInt(commentNumMap.get(idea.getId())));
			ideaResponseList.add(ideaResponse);

			//用来获取用户信息
			userIdSet.add(String.valueOf(idea.getUserId()));
		}

		// 批量获取用户信息，组装协议
		Map<Long, DUser> userMap = userService.multiGetUser(userIdSet);
		Iterator<IdeaResponse> it = ideaResponseList.iterator();
		while (it.hasNext()) {
			IdeaResponse ideaResponse = it.next();
			DUser user = userMap.get(ideaResponse.getUserId());
			if (user == null) {
				// 用户迭代器，用户有获取不到用户信息的时候，就把节点删除
				it.remove();
				logger.warn("IdeaService.getIdeaTop, can not get user, userId: {}", ideaResponse.getUserId());
				continue;
			}
			UserBaseResponse userBaseResponse = userService.getUserBaseResponse(user);
			ideaResponse.setUserBase(userBaseResponse);
			ideaResponse.setNickname(user.getNickname());
			ideaResponse.setAvatarImage(user.getAvatarImage());
			ideaResponse.setIdentity(user.getIdentity());
			ideaResponse.setIsPraise(isIdeaPraise(currentUserId, ideaResponse.getIdeaId()) ? 1 : 0);
		}
		ideaListResponse.setIdeaResponseList(ideaResponseList);
		return ideaListResponse;
	}

	/**
	 * 用点赞数和时间组合成权重(最大支持922万赞,2032年)
	 * @param praise 点赞数
	 * @param lastPraiseTime idea最后点赞时间
	 * @return
	 */
	public long getScore(int praise, long lastPraiseTime) {
		long score = praise * long12 + (long12 - (lastPraiseTime - long12));
		return score;
	}

	/**
	 * 用权重反推点赞数
	 * @param score
	 * @return
	 */
	public int getPraiseByScore(long score) {
		int praise = (int) (score / long12);
		return praise;
	}

	/**
	 * 用权重反推idea最后点赞时间
	 * @param score
	 * @return
	 */
	public long getLastPraiseTimeByScore(long score) {
		long lastPraiseTime = (long12 - (score % long12) + long12);
		return lastPraiseTime;
	}

	/**
	 * 更新idea
	 * @param idea
	 */
	public boolean updateIdea(DIdea idea) {
		boolean updaetSuccess = ideaDao.updateIdea(idea);
		if (updaetSuccess) {
			redis.STRINGS.setex(KeyFactory.ideaKey(idea.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(idea));
		}
		return updaetSuccess;
	}

	/**
	 * 创意赞总数+1
	 * @param idea
	 * @return 创意实体
	 */
	public boolean updateIdeaUpOne(DIdea idea) {
		boolean updateSuccess = commonService.updateCountColumn(CountKey.ideaPraise, idea.getId(), 1);
		if (updateSuccess) {
			int praise = commonService.getCountColumn(CountKey.ideaPraise, idea.getId());
			idea.setPraise(praise);
			redis.STRINGS.setex(KeyFactory.ideaKey(idea.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(idea));
			redis.SORTSET.zadd(KeyFactory.ideaTopKey(idea.getCaseId()), getScore(praise, idea.getLastPraiseTime()), String.valueOf(idea.getId()));
			redis.SORTSET.zadd(KeyFactory.ideaListKey(idea.getCaseId()), idea.getId(), String.valueOf(idea.getId()));
		}
		return updateSuccess;
	}

	/**
	 * 创意赞总数-1
	 * @param idea
	 * @return 创意实体
	 */
	public boolean updateIdeaDownOne(DIdea idea) {
		boolean updateSuccess = commonService.updateCountColumn(CountKey.ideaPraise, idea.getId(), -1);
		if (updateSuccess) {
			int praise = commonService.getCountColumn(CountKey.ideaPraise, idea.getId());
			idea.setPraise(praise);
			redis.STRINGS.setex(KeyFactory.ideaKey(idea.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(idea));
			redis.SORTSET.zadd(KeyFactory.ideaTopKey(idea.getCaseId()), getScore(praise, idea.getLastPraiseTime()), String.valueOf(idea.getId()));
			redis.SORTSET.zadd(KeyFactory.ideaListKey(idea.getCaseId()), idea.getId(), String.valueOf(idea.getId()));
		}
		return updateSuccess;
	}

	/**
	 * 判断用户点赞创意记录(防重复)
	 * @param userId 用户id
	 * @param ideaId 创意id
	 * @return true=已经点赞,false=没有点赞
	 */
	public boolean isIdeaPraise(long userId, long ideaId) {
		boolean isPraise = redis.SORTSET.zscore(KeyFactory.ideaPraiseKey(ideaId), String.valueOf(userId)) > 0;
		if (!isPraise) {
			DIdeaPraise ideaPraise = ideaDao.getIdeaPraise(userId, ideaId);
			if (ideaPraise == null) {
				return false;
			}
			redis.SORTSET.zadd(KeyFactory.ideaPraiseKey(ideaId), ideaPraise.getId(), String.valueOf(userId));
			redis.SORTSET.zadd(KeyFactory.casePraiseKey(ideaPraise.getCaseId()), ideaPraise.getId(), String.valueOf(userId));
			isPraise = true;
		}
		return isPraise;
	}

	/**
	 * 添加用户点赞创意记录(防重复)
	 * @param userId 用户id
	 * @param ideaId 创意id
	 * @return null=点赞失败
	 */
	public DIdeaPraise addIdeaPraise(long userId, long ideaId, long caseId) {
		//增加一条点赞创意数据
		DIdeaPraise ideaPraise = ideaDao.addIdeaPraise(userId, ideaId, caseId);
		if (ideaPraise == null) {
			return null;
		}
		redis.SORTSET.zadd(KeyFactory.ideaPraiseKey(ideaId), ideaPraise.getId(), String.valueOf(userId));
		redis.SORTSET.zadd(KeyFactory.casePraiseKey(ideaPraise.getCaseId()), ideaPraise.getId(), String.valueOf(userId));
		return ideaPraise;
	}

	/**
	 * 删除用户点赞创意记录(防重复)
	 * @param userId 用户id
	 * @param ideaId 创意id
	 * @return null=取消点赞失败
	 */
	public boolean deleteIdeaPraise(long userId, long ideaId) {
		boolean updateSuccess = ideaDao.deleteIdeaPraise(userId, ideaId);
		if (updateSuccess) {
			redis.SORTSET.zrem(KeyFactory.ideaPraiseKey(ideaId), String.valueOf(userId));
			redis.SORTSET.zrem(KeyFactory.casePraiseKey(ideaId), String.valueOf(userId));
		}
		return updateSuccess;
	}
}
