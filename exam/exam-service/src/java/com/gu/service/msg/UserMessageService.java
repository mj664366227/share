package com.gu.service.msg;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.gu.core.enums.MessageSign;
import com.gu.core.interfaces.MessageBody;
import com.gu.core.msgbody.user.CaseFinishBonusMessageBody;
import com.gu.core.msgbody.user.CaseNewMessageBody;
import com.gu.core.msgbody.user.CompanyReplyUserMessageBody;
import com.gu.core.msgbody.user.DelIdeaMessageBody;
import com.gu.core.msgbody.user.IdeaCommentPubMessageBody;
import com.gu.core.msgbody.user.IdeaCommentReplyMessageBody;
import com.gu.core.msgbody.user.IdeaPraiseUpMessageBody;
import com.gu.core.msgbody.user.MarketOpusCommentPubMessageBody;
import com.gu.core.msgbody.user.MarketOpusCommentReplyMessageBody;
import com.gu.core.msgbody.user.UserFocusMessageBody;
import com.gu.core.msgbody.user.UserInvitePubIdeaMessageBody;
import com.gu.core.nsq.protocol.MessageNSQ;
import com.gu.core.protocol.UserMessageListResponse;
import com.gu.core.protocol.UserMessageResponse;
import com.gu.core.protocol.msg.msgbody.UserMessageBody;
import com.gu.core.protocol.msg.user.CaseFinishBonusMessage;
import com.gu.core.protocol.msg.user.CaseNewMessage;
import com.gu.core.protocol.msg.user.CompanyReplyUserMessage;
import com.gu.core.protocol.msg.user.DelIdeaMessage;
import com.gu.core.protocol.msg.user.IdeaCommentPubMessage;
import com.gu.core.protocol.msg.user.IdeaCommentReplyMessage;
import com.gu.core.protocol.msg.user.IdeaPraiseUpMessage;
import com.gu.core.protocol.msg.user.MarketOpusCommentPubMessage;
import com.gu.core.protocol.msg.user.MarketOpusCommentReplyMessage;
import com.gu.core.protocol.msg.user.UserFocusMessage;
import com.gu.core.protocol.msg.user.UserInvitePubIdeaMessage;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.util.JSONObject;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.SystemUtil;
import com.gu.dao.MessageDao;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DIdeaComment;
import com.gu.dao.model.DIdeaCompanyComment;
import com.gu.dao.model.DMarketOpusComment;
import com.gu.dao.model.DUser;
import com.gu.dao.model.DUserMessage;
import com.gu.service.cases.CaseService;
import com.gu.service.common.CommonService;
import com.gu.service.company.CompanyService;
import com.gu.service.idea.IdeaService;
import com.gu.service.market.MarketService;
import com.gu.service.user.UserService;

/**
 * 消息service
 * @author ruan
 */
@Component
public class UserMessageService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(UserMessageService.class);
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
	private IdeaService ideaService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private MarketService marketService;
	private Object obj;

	private UserMessageService() {
		// 用户消息
		String packageName = "com.gu.core.msgbody.user";
		Set<Class<?>> classSet = SystemUtil.getClasses(packageName);
		if (classSet == null || classSet.isEmpty()) {
			throw new RuntimeException("package " + packageName + " is empty");
		}
		// 判断有无解解析消息方法
		for (Class<?> c : classSet) {
			try {
				if (MessageBody.class.isAssignableFrom(c)) {
					methodMap.put(c.getSimpleName(), getClass().getMethod("get" + c.getSimpleName(), c, DUserMessage.class));
				} else {
					throw new RuntimeException(c.getName() + " must extends " + MessageBody.class.getName());
				}
			} catch (Exception e) {
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
	 * 保存用户消息
	 * @param messageNSQ
	 * @return
	 */
	public DUserMessage addUserMessage(MessageNSQ messageNSQ) {
		DUserMessage userMessage = messageDao.addUserMessage(messageNSQ);
		if (userMessage == null) {
			return null;
		}
		redis.STRINGS.set(KeyFactory.userMessageKey(userMessage.getId()), SerialUtil.toBytes(userMessage));
		redis.SORTSET.zadd(KeyFactory.userMessageListKey(userMessage.getUserId(), userMessage.getType()), userMessage.getId(), String.valueOf(userMessage.getId()));
		redis.SORTSET.zadd(KeyFactory.userMessageListKey(userMessage.getUserId(), userMessage.getSign()), userMessage.getId(), String.valueOf(userMessage.getId()));

		// 记录消息条数
		recordUserMessageNum(userMessage.getUserId(), userMessage.getType(), userMessage.getSign());

		return userMessage;
	}

	/**
	 * 获取用户消息列表
	 * @param userId
	 * @param typeORsign
	 * @param lastUserMessageId
	 * @param pageSize
	 * @return
	 */
	public UserMessageListResponse getUserMessageList(long userId, int typeORsign, long lastUserMessageId, int pageSize) {
		UserMessageListResponse userMessageListResponse = new UserMessageListResponse();
		String key = KeyFactory.userMessageListKey(userId, typeORsign);

		// 先从缓存拿数据
		lastUserMessageId = lastUserMessageId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastUserMessageId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DUserMessage> userMessageList = messageDao.getUserMessageList(userId, typeORsign, lastUserMessageId, pageSize);
			if (userMessageList == null || userMessageList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return userMessageListResponse;
			}
			int size = userMessageList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DUserMessage userMessage : userMessageList) {
				String id = String.valueOf(userMessage.getId());
				scoreMembers.put(id, userMessage.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取
		Map<Long, DUserMessage> userMessageMap = multiGetUserMessage(listFromCache);

		//组装返回协议
		List<UserMessageResponse> userMessageResponseList = new ArrayList<UserMessageResponse>();
		try {
			for (Entry<Long, DUserMessage> e : userMessageMap.entrySet()) {
				DUserMessage userMessage = e.getValue();

				MessageBody messageBody;
				if (userMessage.getBody() == null || userMessage.getBody().isEmpty()) {
					//TODO 兼容1.0 旧数据BLOB格式保存
					continue;
				} else {
					messageBody = JSONObject.decode(userMessage.getBody(), MessageSign.signOf(userMessage.getSign()).getClazz());
				}
				if (messageBody == null) {
					// 有可能反序列化不成功
					continue;
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
				Object object = method.invoke(obj, messageBody, userMessage);
				if (object == null) {
					continue;
				}
				userMessageResponseList.add((UserMessageResponse) object);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		userMessageListResponse.setList(userMessageResponseList);
		return userMessageListResponse;
	}

	/**
	 * 批量获取用户消息
	 * @param userMessageIdSet
	 * @return
	 */
	private Map<Long, DUserMessage> multiGetUserMessage(Set<String> userMessageIdSet) {
		return commonService.multiGetT(userMessageIdSet, DUserMessage.class);
	}

	/**
	 * 组织用户消息公共内容
	 * @author ruan 
	 * @param userMessageResponse
	 * @param userMessage
	 */
	private void setMessageResponsePublic(UserMessageResponse userMessageResponse, UserMessageBody userMessageBody, DUserMessage userMessage) {
		userMessageResponse.setUserMessageId(userMessage.getId());
		userMessageResponse.setType(userMessage.getType());
		userMessageResponse.setSign(userMessage.getSign());
		userMessageResponse.setUserId(userMessage.getUserId());
		userMessageResponse.setCreateTime(userMessage.getCreateTime());
		userMessageResponse.setBody(userMessageBody);
	}

	/**
	 * 组装用户消息(2002001,新增粉丝消息)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getUserFocusMessageBody(UserFocusMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		UserFocusMessage userFocusMessage = new UserFocusMessage();
		//消息专有内容
		boolean isFocusUser = userService.isFocusUser(userMessage.getUserId(), userMessage.getSenderId());
		DUser sender = userService.getUserById(userMessage.getSenderId());
		userFocusMessage.setSenderIdentity(sender.getIdentity());
		userFocusMessage.setIsFocus(isFocusUser ? 1 : 0);
		//消息发送者
		userMessageResponse.setSenderId(sender.getId());
		userMessageResponse.setSenderName(sender.getNickname());
		userMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, userFocusMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 组装用户消息(2002002,创意评论消息)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getIdeaCommentPubMessageBody(IdeaCommentPubMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		IdeaCommentPubMessage ideaCommentPubMessage = new IdeaCommentPubMessage();
		//消息专有内容
		long ideaCommentId = messageBody.getIdeaCommentId();
		DIdeaComment ideaComment = ideaService.getIdeaCommentById(ideaCommentId);
		ideaCommentPubMessage.setIdeaId(ideaComment.getIdeaId());
		ideaCommentPubMessage.setIdeaCommentId(ideaComment.getId());
		ideaCommentPubMessage.setContent(ideaComment.getContent());
		//消息发送者
		DUser sender = userService.getUserById(userMessage.getSenderId());
		userMessageResponse.setSenderId(sender.getId());
		userMessageResponse.setSenderName(sender.getNickname());
		userMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, ideaCommentPubMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 组装用户消息(2002003,创意获赞消息)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getIdeaPraiseUpMessageBody(IdeaPraiseUpMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		IdeaPraiseUpMessage ideaPraiseUpMessage = new IdeaPraiseUpMessage();
		//消息专有内容
		long ideaId = messageBody.getIdeaId();
		//DIdea idea = ideaService.getIdeaById(ideaId);
		ideaPraiseUpMessage.setIdeaId(ideaId);
		//消息发送者
		DUser sender = userService.getUserById(userMessage.getSenderId());
		userMessageResponse.setSenderId(sender.getId());
		userMessageResponse.setSenderName(sender.getNickname());
		userMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, ideaPraiseUpMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 组装用户消息(2003001,企业回复创意)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getCompanyReplyUserMessageBody(CompanyReplyUserMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		CompanyReplyUserMessage companyReplyUserMessage = new CompanyReplyUserMessage();
		//消息专有内容
		long ideaCompanyCommentId = messageBody.getIdeaCompanyCommentId();
		DIdeaCompanyComment ideaCompanyComment = ideaService.getIdeaCompanyCommentById(ideaCompanyCommentId);
		companyReplyUserMessage.setIdeaCompanyCommentId(ideaCompanyComment.getId());
		companyReplyUserMessage.setIdeaId(ideaCompanyComment.getIdeaId());
		companyReplyUserMessage.setContent(ideaCompanyComment.getContent());
		//消息发送者
		DCompany company = companyService.getCompanyById(ideaCompanyComment.getCompanyId());
		userMessageResponse.setSenderId(company.getId());
		userMessageResponse.setSenderName(company.getName());
		userMessageResponse.setSenderImage(company.getLogoImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, companyReplyUserMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 发布专案邀请消息(2002004)
	 * @author ruan 
	 * @param messageBody
	 * @param userMessage
	 */
	public UserMessageResponse getUserInvitePubIdeaMessageBody(UserInvitePubIdeaMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		UserInvitePubIdeaMessage userInvitePubIdeaMessage = new UserInvitePubIdeaMessage();
		//消息专有内容
		userInvitePubIdeaMessage.setCaseId(messageBody.getCaseId());
		//消息发送者
		DUser user = userService.getUserById(userMessage.getSenderId());
		userMessageResponse.setSenderId(user.getId());
		userMessageResponse.setSenderName(user.getNickname());
		userMessageResponse.setSenderImage(user.getAvatarImage());
		//消息基本公共内容user
		setMessageResponsePublic(userMessageResponse, userInvitePubIdeaMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 组装用户消息(2002005,创意评论被回复消息)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getIdeaCommentReplyMessageBody(IdeaCommentReplyMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		IdeaCommentReplyMessage ideaCommentReplyMessage = new IdeaCommentReplyMessage();
		//消息专有内容
		long ideaCommentId = messageBody.getIdeaCommentId();
		DIdeaComment ideaComment = ideaService.getIdeaCommentById(ideaCommentId);
		ideaCommentReplyMessage.setIdeaId(ideaComment.getIdeaId());
		ideaCommentReplyMessage.setIdeaCommentId(ideaComment.getId());
		ideaCommentReplyMessage.setContent(ideaComment.getContent());
		//消息发送者
		DUser sender = userService.getUserById(userMessage.getSenderId());
		userMessageResponse.setSenderId(sender.getId());
		userMessageResponse.setSenderName(sender.getNickname());
		userMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, ideaCommentReplyMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 组装用户消息(2002006,创意圈_作品评论消息)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getMarketOpusCommentPubMessageBody(MarketOpusCommentPubMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		MarketOpusCommentPubMessage marketOpusCommentPubMessage = new MarketOpusCommentPubMessage();
		//消息专有内容
		long marketOpusCommentId = messageBody.getMarketOpusCommentId();
		DMarketOpusComment marketOpusComment = marketService.getMarketOpusCommentById(marketOpusCommentId);
		marketOpusCommentPubMessage.setMarketOpusId(marketOpusComment.getMarketOpusId());
		marketOpusCommentPubMessage.setMarketOpusCommentId(marketOpusComment.getId());
		marketOpusCommentPubMessage.setContent(marketOpusComment.getContent());
		//消息发送者
		DUser sender = userService.getUserById(userMessage.getSenderId());
		userMessageResponse.setSenderId(sender.getId());
		userMessageResponse.setSenderName(sender.getNickname());
		userMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, marketOpusCommentPubMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 组装用户消息(2002007,创意圈_作品评论被回复消息)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getMarketOpusCommentReplyMessageBody(MarketOpusCommentReplyMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		MarketOpusCommentReplyMessage marketOpusCommentReplyMessage = new MarketOpusCommentReplyMessage();
		//消息专有内容
		long marketOpusCommentId = messageBody.getMarketOpusCommentId();
		DMarketOpusComment marketOpusComment = marketService.getMarketOpusCommentById(marketOpusCommentId);
		marketOpusCommentReplyMessage.setMarketOpusId(marketOpusComment.getMarketOpusId());
		marketOpusCommentReplyMessage.setMarketOpusCommentId(marketOpusComment.getId());
		marketOpusCommentReplyMessage.setContent(marketOpusComment.getContent());
		//消息发送者
		DUser sender = userService.getUserById(userMessage.getSenderId());
		userMessageResponse.setSenderId(sender.getId());
		userMessageResponse.setSenderName(sender.getNickname());
		userMessageResponse.setSenderImage(sender.getAvatarImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, marketOpusCommentReplyMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 删除点子消息(2001001)
	 * @author ruan 
	 * @param messageBody
	 * @param userMessage
	 */
	public UserMessageResponse getDelIdeaMessageBody(DelIdeaMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		DelIdeaMessage delIdeaMessage = new DelIdeaMessage();
		//消息专有内容
		DCase dCase = caseService.getCaseById(messageBody.getCaseId());
		if (dCase == null) {
			return null;
		}
		delIdeaMessage.setContent("系统管理员删除了您在专案《" + dCase.getName() + "》中发布的创意！");
		//消息基本公共内容user
		setMessageResponsePublic(userMessageResponse, delIdeaMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 专案结束分红消息(2001002)
	 * @author ruan 
	 * @param messageBody
	 * @param userMessage
	 */
	public UserMessageResponse getCaseFinishBonusMessageBody(CaseFinishBonusMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		CaseFinishBonusMessage caseFinishBonusMessage = new CaseFinishBonusMessage();
		//消息专有内容
		DCase dCase = caseService.getCaseById(messageBody.getCaseId());
		if (dCase == null) {
			return null;
		}
		caseFinishBonusMessage.setCaseId(dCase.getId());
		caseFinishBonusMessage.setCaseName(dCase.getName());
		caseFinishBonusMessage.setPoints(messageBody.getPoints());
		caseFinishBonusMessage.setContent("你参与的专案《" + dCase.getName() + "》结束啦！恭喜你获得了" + messageBody.getPoints() + "G点！");
		//消息基本公共内容user
		setMessageResponsePublic(userMessageResponse, caseFinishBonusMessage, userMessage);
		return userMessageResponse;
	}

	/**
	 * 组装用户消息(2003002,企业新发布专案)
	 * @param messageBody
	 * @param userMessage
	 * @return
	 */
	public UserMessageResponse getCaseNewMessageBody(CaseNewMessageBody messageBody, DUserMessage userMessage) {
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		CaseNewMessage caseNewMessage = new CaseNewMessage();
		//消息专有内容
		DCase dCase = caseService.getCaseById(messageBody.getCaseId());
		if (dCase == null) {
			return null;
		}
		caseNewMessage.setCaseId(dCase.getId());
		caseNewMessage.setCaseName(dCase.getName());
		//消息发送者
		DCompany company = companyService.getCompanyById(dCase.getCompanyId());
		if (company == null) {
			return null;
		}
		userMessageResponse.setSenderId(company.getId());
		userMessageResponse.setSenderName(company.getName());
		userMessageResponse.setSenderImage(company.getLogoImage());
		//消息基本公共内容
		setMessageResponsePublic(userMessageResponse, caseNewMessage, userMessage);
		return userMessageResponse;
	}

	public DUserMessage getUserMessageById(long userMessageId) {
		byte[] b = redis.STRINGS.get(KeyFactory.userMessageKey(userMessageId).getBytes());
		if (b == null || b.length <= 0) {
			DUserMessage userMessage = messageDao.getUserMessageById(userMessageId);
			if (userMessage == null) {
				return null;
			}
			redis.STRINGS.set(KeyFactory.userMessageKey(userMessage.getId()), SerialUtil.toBytes(userMessage));
			return userMessage;
		}
		return SerialUtil.fromBytes(b, DUserMessage.class);
	}

	public void delUserMessage(DUserMessage userMessage) {
		messageDao.delUserMessage(userMessage);
		redis.KEYS.del(KeyFactory.userMessageKey(userMessage.getId()));
		redis.SORTSET.zrem(KeyFactory.userMessageListKey(userMessage.getUserId(), userMessage.getType()), String.valueOf(userMessage.getId()));
		redis.SORTSET.zrem(KeyFactory.userMessageListKey(userMessage.getUserId(), userMessage.getSign()), String.valueOf(userMessage.getId()));
	}

	/**
	 * 记录用户消息数量
	 * @author luo 
	 * @param userId 用户id
	 * @param messageType 消息大类型
	 * @param messageSign 消息小类型
	 */
	public void recordUserMessageNum(long userId, int messageType, int messageSign) {
		List<Map<String, Object>> data = getUserMessageNum(userId);

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
			if (!messageDao.updateUserMessageNum(userId, messageType, messageSign, num)) {
				return;
			}
			numMap = map;
		}

		if (numMap == null || numMap.isEmpty()) {
			Map<String, Object> map = new HashMap<>(4);
			map.put("user_id", userId);
			map.put("message_type", messageType);
			map.put("message_sign", messageSign);
			map.put("num", 1);
			data.add(map);
			if (!messageDao.recordUserMessageNum(userId, messageType, messageSign)) {
				return;
			}
		}

		// 更新redis
		String key = KeyFactory.userMessageNumKey(userId);
		redis.STRINGS.setex(key, (int) TimeUnit.DAYS.toSeconds(30), JSONObject.encode(data));
	}

	/**
	 * 记录用户消息数量
	 * @author luo 
	 * @param userId 用户id
	 */
	public List<Map<String, Object>> getUserMessageNum(long userId) {
		String key = KeyFactory.userMessageNumKey(userId);
		String redisData = StringUtil.getString(redis.STRINGS.get(key));
		List<Map<String, Object>> data = null;
		if (redisData.isEmpty()) {
			data = messageDao.getUserMessageNum(userId);
			redis.STRINGS.setex(key, (int) TimeUnit.DAYS.toSeconds(30), JSONObject.encode(data));
		} else {
			data = JSONObject.decode(redisData, new TypeToken<List<Map<String, Object>>>() {
			}.getType());
		}
		return data;
	}

	/**
	 * 清空用户消息数量
	 * @author luo 
	 * @param userId 用户id
	 */
	public void clearUserMessageNum(long userId, int typeORsign) {
		messageDao.clearUserMessageNum(userId, typeORsign);
		String key = KeyFactory.userMessageNumKey(userId);
		List<Map<String, Object>> data = getUserMessageNum(userId);
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