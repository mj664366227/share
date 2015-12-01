package com.gu.service.msg;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.gu.core.enums.LengthLimit;
import com.gu.core.enums.MessageSign;
import com.gu.core.interfaces.MessageBody;
import com.gu.core.msgbody.company.AdminSendPointsMessageBody;
import com.gu.core.msgbody.company.CaseFinishMessageBody;
import com.gu.core.msgbody.company.FocusCaseMessageBody;
import com.gu.core.msgbody.company.IdeaPubMessageBody;
import com.gu.core.msgbody.company.UserFocusCompanyMessageBody;
import com.gu.core.msgbody.company.UserReplyCompanyMessageBody;
import com.gu.core.nsq.protocol.MessageNSQ;
import com.gu.core.protocol.CompanyMessageListResponse;
import com.gu.core.protocol.CompanyMessageResponse;
import com.gu.core.protocol.msg.company.AdminSendPointsMessage;
import com.gu.core.protocol.msg.company.CaseFinishMessage;
import com.gu.core.protocol.msg.company.FocusCaseMessage;
import com.gu.core.protocol.msg.company.IdeaPubMessage;
import com.gu.core.protocol.msg.company.UserFocusCompanyMessage;
import com.gu.core.protocol.msg.company.UserReplyCompanyMessage;
import com.gu.core.protocol.msg.msgbody.CompanyMessageBody;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.util.JSONObject;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.SystemUtil;
import com.gu.dao.MessageDao;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCompanyMessage;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DIdeaCompanyComment;
import com.gu.dao.model.DUser;
import com.gu.service.cases.CaseService;
import com.gu.service.idea.IdeaService;
import com.gu.service.user.UserService;

/**
 * 消息service
 * @author ruan
 */
@Component
public class CompanyMessageService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(CompanyMessageService.class);
	/**
	 * 方法map
	 */
	private Map<String, Method> methodMap = new ConcurrentHashMap<>();
	/**
	 * context
	 */
	@Autowired
	private ApplicationContext context;
	@Autowired
	private Redis redis;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private IdeaService ideaService;
	private Object obj;

	private CompanyMessageService() {
		// 企业消息
		String packageName = "com.gu.core.msgbody.company";
		Set<Class<?>> classSet = SystemUtil.getClasses(packageName);
		if (classSet == null || classSet.isEmpty()) {
			throw new RuntimeException("package " + packageName + " is empty");
		}
		// 判断有无解解析消息方法
		for (Class<?> c : classSet) {
			try {
				if (MessageBody.class.isAssignableFrom(c)) {
					methodMap.put(c.getSimpleName(), getClass().getMethod("get" + c.getSimpleName(), c, DCompanyMessage.class));
				} else {
					throw new RuntimeException(c.getName() + " must extends " + MessageBody.class.getName());
				}
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error("", e);
				System.exit(0);
			}
		}
	}

	/**
	 * SortSet列表加占位
	 * @param key
	 */
	public void addSortSetAnchor(String key) {
		redis.SORTSET.zadd(key, -1, "-1");
	}

	/**
	 * 保存企业消息
	 * @param messageNSQ
	 * @return
	 */
	public DCompanyMessage addCompanyMessage(MessageNSQ messageNSQ) {
		DCompanyMessage companyMessage = messageDao.addCompanyMessage(messageNSQ);
		if (companyMessage == null) {
			return null;
		}
		redis.STRINGS.set(KeyFactory.companyMessageKey(companyMessage.getId()), SerialUtil.toBytes(companyMessage));
		redis.SORTSET.zadd(KeyFactory.companyMessageListKey(companyMessage.getCompanyId(), companyMessage.getType()), companyMessage.getId(), String.valueOf(companyMessage.getId()));
		redis.SORTSET.zadd(KeyFactory.companyMessageListKey(companyMessage.getCompanyId(), companyMessage.getSign()), companyMessage.getId(), String.valueOf(companyMessage.getId()));

		// 记录消息条数
		recordCompanyMessageNum(companyMessage.getCompanyId(), companyMessage.getType(), companyMessage.getSign());
		return companyMessage;
	}

	/**
	 * 获取企业消息列表
	 * @param userId
	 * @param typeORsign
	 * @param lastUserMessageId
	 * @param pageSize
	 * @return
	 */
	public CompanyMessageListResponse getCompanyMessageList(long companyId, int typeORsign, int page, int pageSize) {
		CompanyMessageListResponse companyMessageResponse = new CompanyMessageListResponse();
		String key = KeyFactory.companyMessageListKey(companyId, typeORsign);

		// 先从缓存拿
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			// 如果数缓存没有，穿透数据库查1000条
			List<DCompanyMessage> companyMessageList = messageDao.getCompanyMessageList(companyId, typeORsign, page, page == 1 ? LengthLimit.reloadFromRedisLen.getLength() : pageSize);
			if (companyMessageList == null || companyMessageList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return companyMessageResponse;
			}
			int size = companyMessageList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCompanyMessage companyMessage : companyMessageList) {
				String id = String.valueOf(companyMessage.getId());
				scoreMembers.put(id, companyMessage.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		int total = (int) redis.SORTSET.zcard(key);
		if (redis.SORTSET.zscore(key, "-1") == -1D) {
			total -= 1;
		}
		companyMessageResponse.setTotal(total);

		// 批量获取
		Map<Long, DCompanyMessage> companyMessageMap = multiGetCompanyMessage(listFromCache);

		//组装返回协议
		List<CompanyMessageResponse> companyMessageResponseList = new ArrayList<CompanyMessageResponse>();
		try {
			int i = 0;
			for (Entry<Long, DCompanyMessage> e : companyMessageMap.entrySet()) {
				DCompanyMessage companyMessage = e.getValue();
				if (companyMessage == null) {
					continue;
				}
				MessageBody messageBody;
				if (companyMessage.getBody() == null || companyMessage.getBody().isEmpty()) {
					//TODO 兼容1.0 旧数据BLOB格式保存
					continue;
				} else {
					messageBody = JSONObject.decode(companyMessage.getBody(), MessageSign.signOf(companyMessage.getSign()).getClazz());
				}
				if (messageBody == null) {
					// 有可能反序列化不成功
					continue;
				}
				if (++i > pageSize) {
					break;
				}
				String className = messageBody.getClass().getSimpleName();
				Method method = methodMap.get(className);
				// 获取本类的实例
				if (obj == null) {
					//这样是为了防止并发
					synchronized (this) {
						obj = context.getBean(getClass());
					}
				}
				companyMessageResponseList.add((CompanyMessageResponse) method.invoke(obj, messageBody, companyMessage));
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		companyMessageResponse.setList(companyMessageResponseList);
		return companyMessageResponse;
	}

	/**
	 * 批量获取用户消息
	 * @param userMessageIdSet
	 * @return
	 */
	private Map<Long, DCompanyMessage> multiGetCompanyMessage(Set<String> companyMessageIdSet) {
		// 去掉占位符
		companyMessageIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (companyMessageIdSet.isEmpty()) {
			return new HashMap<Long, DCompanyMessage>(0);
		}
		// 首先去缓存拿
		int size = companyMessageIdSet.size();
		List<String> companyMessageKeyList = new ArrayList<String>(size);
		for (String companyMessageId : companyMessageIdSet) {
			companyMessageKeyList.add(KeyFactory.companyMessageKey(StringUtil.getLong(companyMessageId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(companyMessageKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的

		Map<Long, DCompanyMessage> data = new LinkedHashMap<>(size);
		for (String companyMessageId : companyMessageIdSet) {
			data.put(StringUtil.getLong(companyMessageId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DCompanyMessage companyMessage = SerialUtil.fromBytes(bytes, DCompanyMessage.class);
				if (companyMessage == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(companyMessage.getId(), companyMessage);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpCompanyMessageIdSet = new HashSet<Long>(size);
		for (Entry<Long, DCompanyMessage> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpCompanyMessageIdSet.add(e.getKey());
			}
		}
		if (tmpCompanyMessageIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DCompanyMessage> companyMessageList = messageDao.getCompanyMessage(tmpCompanyMessageIdSet);
		if (companyMessageList == null || companyMessageList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(companyMessageList.size());
		for (DCompanyMessage companyMessage : companyMessageList) {
			data.put(companyMessage.getId(), companyMessage);
			keysValuesMap.put(KeyFactory.companyMessageKey(companyMessage.getId()).getBytes(), SerialUtil.toBytes(companyMessage));
		}
		redis.STRINGS.mset(keysValuesMap);
		return data;
	}

	/**
	 * set消息基本公共内容(消息id,大类,小类,企业id,添加时间)
	 * @param companyMessageResponse
	 * @param companyMessage
	 */
	private void setMessageResponsePublic(CompanyMessageResponse companyMessageResponse, CompanyMessageBody companyMessageBody, DCompanyMessage companyMessage) {
		companyMessageResponse.setCompanyMessageId(companyMessage.getId());
		companyMessageResponse.setType(companyMessage.getType());
		companyMessageResponse.setSign(companyMessage.getSign());
		companyMessageResponse.setCompanyId(companyMessage.getCompanyId());
		companyMessageResponse.setCreateTime(companyMessage.getCreateTime());
		companyMessageResponse.setBody(companyMessageBody);
	}

	/**
	 * 组装企业消息(3002001, 用户回复企业)
	 * @param messageBody
	 * @param companyMessage
	 * @return
	 */
	public CompanyMessageResponse getUserReplyCompanyMessageBody(UserReplyCompanyMessageBody messageBody, DCompanyMessage companyMessage) {
		CompanyMessageResponse companyMessageResponse = new CompanyMessageResponse();
		UserReplyCompanyMessage userReplyCompanyMessage = new UserReplyCompanyMessage();
		//消息专有内容
		long ideaId = messageBody.getIdeaId();
		DIdea idea = ideaService.getIdeaById(ideaId);
		userReplyCompanyMessage.setIdeaId(ideaId);
		userReplyCompanyMessage.setIdeaTitle(idea.getTitle());
		userReplyCompanyMessage.setIdeaCreateTime(idea.getCreateTime());
		userReplyCompanyMessage.setCaseId(idea.getCaseId());

		long ideaCompanyCommentId = messageBody.getIdeaCompanyCommentId();
		DIdeaCompanyComment ideaCompanyComment = ideaService.getIdeaCompanyCommentById(ideaCompanyCommentId);
		userReplyCompanyMessage.setIdeaCompanyCommentId(ideaCompanyCommentId);
		userReplyCompanyMessage.setReplyContent(ideaCompanyComment.getContent());
		userReplyCompanyMessage.setReplyCreateTime(ideaCompanyComment.getCreateTime());

		long fromId = messageBody.getFromId();
		if (fromId != 0) {
			DIdeaCompanyComment fromCompanyComment = ideaService.getIdeaCompanyCommentById(fromId);
			userReplyCompanyMessage.setFromId(fromId);
			userReplyCompanyMessage.setFromContent(fromCompanyComment.getContent());
			userReplyCompanyMessage.setFromCreateTime(fromCompanyComment.getCreateTime());
		}

		//消息发送者
		DUser sender = userService.getUserById(companyMessage.getSenderId());
		companyMessageResponse.setSenderId(sender.getId());
		companyMessageResponse.setSenderName(sender.getNickname());
		companyMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(companyMessageResponse, userReplyCompanyMessage, companyMessage);

		return companyMessageResponse;
	}

	/**
	 * 组装企业消息(3003001,用户关注企业)
	 * @param messageBody
	 * @param companyMessage
	 * @return
	 */
	public CompanyMessageResponse getUserFocusCompanyMessageBody(UserFocusCompanyMessageBody messageBody, DCompanyMessage companyMessage) {
		CompanyMessageResponse companyMessageResponse = new CompanyMessageResponse();
		UserFocusCompanyMessage userFocusCompanyMessage = new UserFocusCompanyMessage();
		//消息专有内容
		long userId = messageBody.getUserId();
		userFocusCompanyMessage.setUserId(userId);
		//消息发送者
		DUser sender = userService.getUserById(companyMessage.getSenderId());
		companyMessageResponse.setSenderId(sender.getId());
		companyMessageResponse.setSenderName(sender.getNickname());
		companyMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(companyMessageResponse, userFocusCompanyMessage, companyMessage);

		return companyMessageResponse;
	}

	/**
	 * 组装企业消息(3004001, 用户提交创意)
	 * @param messageBody
	 * @param companyMessage
	 * @return
	 */
	public CompanyMessageResponse getIdeaPubMessageBody(IdeaPubMessageBody messageBody, DCompanyMessage companyMessage) {
		CompanyMessageResponse companyMessageResponse = new CompanyMessageResponse();
		IdeaPubMessage ideaPubMessage = new IdeaPubMessage();
		//消息专有内容
		long ideaId = messageBody.getIdeaId();
		ideaPubMessage.setIdeaId(ideaId);
		long caseId = messageBody.getCaseId();
		DCase dCase = caseService.getCaseById(caseId);
		ideaPubMessage.setCaseId(caseId);
		ideaPubMessage.setCaseName(dCase.getName());
		//消息发送者
		DUser sender = userService.getUserById(companyMessage.getSenderId());
		companyMessageResponse.setSenderId(sender.getId());
		companyMessageResponse.setSenderName(sender.getNickname());
		companyMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(companyMessageResponse, ideaPubMessage, companyMessage);

		return companyMessageResponse;
	}

	/**
	 * 组装企业消息(3004002, 用户关注专案)
	 * @param messageBody
	 * @param companyMessage
	 * @return
	 */
	public CompanyMessageResponse getFocusCaseMessageBody(FocusCaseMessageBody messageBody, DCompanyMessage companyMessage) {
		CompanyMessageResponse companyMessageResponse = new CompanyMessageResponse();
		FocusCaseMessage focusCaseMessage = new FocusCaseMessage();
		//消息专有内容
		long caseId = messageBody.getCaseId();
		DCase dCase = caseService.getCaseById(caseId);
		focusCaseMessage.setCaseId(caseId);
		focusCaseMessage.setCaseName(dCase.getName());
		//消息发送者
		DUser sender = userService.getUserById(companyMessage.getSenderId());
		companyMessageResponse.setSenderId(sender.getId());
		companyMessageResponse.setSenderName(sender.getNickname());
		companyMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(companyMessageResponse, focusCaseMessage, companyMessage);

		return companyMessageResponse;
	}

	/**
	 * 组装企业消息(3001001, 后台赠送G点)
	 * @param messageBody
	 * @param companyMessage
	 * @return
	 */
	public CompanyMessageResponse getAdminSendPointsMessageBody(AdminSendPointsMessageBody messageBody, DCompanyMessage companyMessage) {
		CompanyMessageResponse companyMessageResponse = new CompanyMessageResponse();
		AdminSendPointsMessage adminSendPointsMessage = new AdminSendPointsMessage();
		//消息专有内容
		adminSendPointsMessage.setPoints(messageBody.getPoints());
		//消息发送者
		long senderId = companyMessage.getSenderId();
		companyMessageResponse.setSenderId(senderId);
		companyMessageResponse.setSenderName("管理员");
		companyMessageResponse.setSenderImage("");
		//消息基本公共内容
		setMessageResponsePublic(companyMessageResponse, adminSendPointsMessage, companyMessage);
		return companyMessageResponse;
	}

	/**
	 * 组装企业消息(3001002, 专案结算)
	 * @param messageBody
	 * @param companyMessage
	 * @return
	 */
	public CompanyMessageResponse getCaseFinishMessageBody(CaseFinishMessageBody messageBody, DCompanyMessage companyMessage) {
		CompanyMessageResponse companyMessageResponse = new CompanyMessageResponse();
		CaseFinishMessage caseFinishMessage = new CaseFinishMessage();
		//消息专有内容
		DCase dCase = caseService.getCaseById(messageBody.getCaseId());
		if (dCase == null) {
			return null;
		}
		caseFinishMessage.setCaseId(dCase.getId());
		caseFinishMessage.setCaseName(dCase.getName());
		caseFinishMessage.setPointsPlan(messageBody.getPointsPlan());
		caseFinishMessage.setPointsReal(messageBody.getPointsReal());
		caseFinishMessage.setPointsSurplus(messageBody.getPointsSurplus());
		//消息发送者
		long senderId = companyMessage.getSenderId();
		companyMessageResponse.setSenderId(senderId);
		companyMessageResponse.setSenderName("管理员");
		companyMessageResponse.setSenderImage("");
		//消息基本公共内容
		setMessageResponsePublic(companyMessageResponse, caseFinishMessage, companyMessage);
		return companyMessageResponse;
	}

	/**
	 * 记录企业消息数量
	 * @author ruan 
	 * @param companyId 企业id
	 * @param messageType 消息大类型
	 * @param messageSign 消息小类型
	 */
	public void recordCompanyMessageNum(long companyId, int messageType, int messageSign) {
		List<Map<String, Object>> data = getCompanyMessageNum(companyId);

		Map<String, Object> numMap = null;
		for (Map<String, Object> map : data) {
			if (StringUtil.getInt(map.get("message_sign")) != messageSign) {
				continue;
			}
			if (StringUtil.getInt(map.get("message_type")) != messageType) {
				continue;
			}
			int num = StringUtil.getInt(map.get("num")) + 1;
			map.put("num", num);
			if (!messageDao.updateCompanyMessageNum(companyId, messageType, messageSign, num)) {
				return;
			}
			numMap = map;
		}

		if (numMap == null || numMap.isEmpty()) {
			Map<String, Object> map = new HashMap<>(4);
			map.put("company_id", companyId);
			map.put("message_type", messageType);
			map.put("message_sign", messageSign);
			map.put("num", 1);
			data.add(map);
			if (!messageDao.recordCompanyMessageNum(companyId, messageType, messageSign)) {
				return;
			}
		}

		String key = KeyFactory.companyMessageNumKey(companyId);
		redis.STRINGS.setex(key, (int) TimeUnit.DAYS.toSeconds(30), JSONObject.encode(data));
	}

	/**
	 * 记录企业消息数量
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public List<Map<String, Object>> getCompanyMessageNum(long companyId) {
		String key = KeyFactory.companyMessageNumKey(companyId);
		String redisData = StringUtil.getString(redis.STRINGS.get(key));
		List<Map<String, Object>> data = null;
		if (redisData.isEmpty()) {
			data = messageDao.getCompanyMessageNum(companyId);
			redis.STRINGS.setex(key, (int) TimeUnit.DAYS.toSeconds(30), JSONObject.encode(data));
		} else {
			data = JSONObject.decode(redisData, new TypeToken<List<Map<String, Object>>>() {
			}.getType());
		}
		return data;
	}

	/**
	 * 清空企业消息数量
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public void clearCompanyMessageNum(long companyId, int typeORsign) {
		messageDao.clearCompanyMessageNum(companyId, typeORsign);
		String key = KeyFactory.companyMessageNumKey(companyId);
		List<Map<String, Object>> data = getCompanyMessageNum(companyId);
		List<Map<String, Object>> removeMap = new ArrayList<>();
		for (Map<String, Object> map : data) {
			if (StringUtil.getInt(map.get("message_type")) == typeORsign || StringUtil.getInt(map.get("message_sign")) == typeORsign) {
				removeMap.add(map);
			}
		}
		data.removeAll(removeMap);
		if (data.isEmpty()) {
			redis.KEYS.del(key);
		}
		redis.STRINGS.setex(key, (int) TimeUnit.DAYS.toSeconds(30), JSONObject.encode(data));
	}
}