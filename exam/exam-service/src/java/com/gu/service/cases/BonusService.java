package com.gu.service.cases;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gu.core.enums.CompanyPointsAddEvent;
import com.gu.core.enums.MessageReceiver;
import com.gu.core.enums.MessageSign;
import com.gu.core.enums.UserPointsAddEvent;
import com.gu.core.enums.UserScoreAddEvent;
import com.gu.core.general.GetuiService;
import com.gu.core.msgbody.company.CaseFinishMessageBody;
import com.gu.core.msgbody.user.CaseFinishBonusMessageBody;
import com.gu.core.nsq.NsqService;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.JSONObject;
import com.gu.core.util.MathUtil;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.CaseDao;
import com.gu.dao.FlowDao;
import com.gu.dao.IdeaDao;
import com.gu.dao.LogDao;
import com.gu.dao.model.DBonusPercentConfig;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCaseFinishBonusCommonUserLog;
import com.gu.dao.model.DCaseFinishBonusLog;
import com.gu.dao.model.DCaseFinishBonusPrizeUserLog;
import com.gu.dao.model.DCaseFinishBonusUserLog;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DUser;
import com.gu.service.common.CommonService;
import com.gu.service.company.CompanyService;
import com.gu.service.user.UserService;

@Component
public class BonusService {
	/**
	* logger
	*/
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IdeaDao ideaDao;
	@Autowired
	private CaseDao caseDao;
	@Autowired
	private FlowDao flowDao;
	@Autowired
	private LogDao logDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private NsqService nsqService;
	@Autowired
	private GetuiService getuiService;
	@Autowired
	private Redis redis;
	@Autowired
	private CommonService commonService;
	@Autowired
	private GuThreadPool guThreadPool;

	/**
	 * 专案分钱规则Map<名字,百分比>
	 */
	private LoadingCache<Integer, Map<String, DBonusPercentConfig>> bonusPercentConfigMap = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, Map<String, DBonusPercentConfig>>() {
		public Map<String, DBonusPercentConfig> load(Integer key) throws Exception {
			logger.warn("load bonus_percent_config");
			List<DBonusPercentConfig> list = caseDao.getBonusPercentConfig();
			Map<String, DBonusPercentConfig> map = new LinkedHashMap<String, DBonusPercentConfig>();
			for (DBonusPercentConfig bonusPercentConfig : list) {
				map.put(bonusPercentConfig.getName(), bonusPercentConfig);
			}
			return map;
		}
	});

	/**
	 * 初始化配置数据
	 */
	@PostConstruct
	public void init() {
		try {
			bonusPercentConfigMap.invalidateAll();
			getBonusPercentConfigMap();
		} catch (Exception e) {
			logger.error("load bonus percent config", e);
		}
	}

	/**
	 * 获取专案分钱规则Map<名字,百分比>
	 */
	public Map<String, DBonusPercentConfig> getBonusPercentConfigMap() {
		try {
			return bonusPercentConfigMap.get(1);
		} catch (ExecutionException e) {
			logger.error("load bonus percent config", e);
		}
		return new HashMap<String, DBonusPercentConfig>(0);
	}

	/**
	 * 修改分钱规则配置
	 * @author ruan 
	 * @param data
	 */
	public void updateBonusPercentConfig(Map<Integer, String> data) {
		caseDao.updateBonusPercentConfig(data);
	}

	/**
	 * 专案结算总方法
	 * @param dCase
	 * @return
	 */
	public DCaseFinishBonusLog caseFinishBonusLog(DCase dCase) {
		DCaseFinishBonusLog caseFinishBonusLog = new DCaseFinishBonusLog();
		List<DCaseFinishBonusCommonUserLog> bonusCommonUserLogList = new ArrayList<>();

		//吐槽分红规则(去重)
		DCaseFinishBonusCommonUserLog bonusCommonUserLog_flow = flow(dCase);
		if (bonusCommonUserLog_flow != null) {
			logDao.saveCaseFinishBonusCommonUserLog(bonusCommonUserLog_flow);
			bonusCommonUserLogList.add(bonusCommonUserLog_flow);
		}
		//全部点赞人分红规则(去重)
		DCaseFinishBonusCommonUserLog bonusCommonUserLog_praiseAll = praiseAll(dCase);
		if (bonusCommonUserLog_praiseAll != null) {
			logDao.saveCaseFinishBonusCommonUserLog(bonusCommonUserLog_praiseAll);
			bonusCommonUserLogList.add(bonusCommonUserLog_praiseAll);
		}
		//全部创意分红规则(平均分,去重)
		DCaseFinishBonusCommonUserLog bonusCommonUserLog_ideaAll = ideaAll(dCase);
		if (bonusCommonUserLog_ideaAll != null) {
			logDao.saveCaseFinishBonusCommonUserLog(bonusCommonUserLog_ideaAll);
			bonusCommonUserLogList.add(bonusCommonUserLog_ideaAll);
		}

		int pointsPlan = dCase.getPoints();
		int pointsReal = 0;
		int pointsSurplus = 0;//剩下的G点(除不尽)
		for (DCaseFinishBonusCommonUserLog bonusCommonUserLog : bonusCommonUserLogList) {
			pointsSurplus += bonusCommonUserLog.getBonusPointsSurplus();
		}

		List<DCaseFinishBonusPrizeUserLog> bonusPrizeUserLogList = new ArrayList<>();

		//读取奖励线,发创意人数超过N个才进入奖励机制(去重)
		DBonusPercentConfig bonusPercentConfig = getBonusPercentConfigMap().get("bonusLine");
		int bonusLine = 20;
		if (bonusPercentConfig != null) {
			bonusLine = bonusPercentConfig.getPointsMax();
		}

		if (bonusCommonUserLog_ideaAll.getUserNum() >= bonusLine) {//创意超过X个才进入奖励机制
			//点赞前3创意的人分红规则(去重)
			DCaseFinishBonusCommonUserLog bonusCommonUserLog_praiseTopThree = praiseTopThree(dCase);
			logDao.saveCaseFinishBonusCommonUserLog(bonusCommonUserLog_praiseTopThree);
			bonusCommonUserLogList.add(bonusCommonUserLog_praiseTopThree);
			pointsSurplus += bonusCommonUserLog_praiseTopThree.getBonusPointsSurplus();//剩下的G点(除不尽)

			//全部创意获赞比例分红规则(idea获赞数/case总赞数)
			Map<Integer, List<DCaseFinishBonusPrizeUserLog>> map = ideaTop(dCase);//返回剩余G点(除不尽) 和 分红list
			for (Map.Entry<Integer, List<DCaseFinishBonusPrizeUserLog>> e : map.entrySet()) {
				bonusPrizeUserLogList = e.getValue();
				pointsSurplus += e.getKey();
			}
		} else {
			int precentSurplus = getBonusPercentConfigMap().get("ideaTop").getPercent() + getBonusPercentConfigMap().get("praiseTopThree").getPercent();
			pointsSurplus += (pointsPlan / 100 * precentSurplus);
		}
		pointsReal = pointsPlan - pointsSurplus;

		caseFinishBonusLog.setCaseId(dCase.getId());
		caseFinishBonusLog.setCompanyId(dCase.getCompanyId());
		caseFinishBonusLog.setPointsPlan(pointsPlan);
		caseFinishBonusLog.setPointsReal(pointsReal);
		caseFinishBonusLog.setPointsSurplus(pointsSurplus);
		caseFinishBonusLog.setFinishTime(Time.now());

		logDao.saveCaseFinishBonusLog(caseFinishBonusLog);

		//剩余G点返回给企业
		DCompany company = companyService.getCompanyById(dCase.getCompanyId());
		companyService.addPoints(company.getId(), caseFinishBonusLog.getPointsSurplus(), CompanyPointsAddEvent.caseFinish);

		// 记录支出的G点
		companyService.addCompanyExpense(dCase.getCompanyId(), dCase.getId(), caseFinishBonusLog.getPointsReal());

		//发送一条信息给有分红的用户
		CaseFinishMessageBody messageBody = new CaseFinishMessageBody();
		messageBody.setCaseId(dCase.getId());
		messageBody.setPointsPlan(pointsPlan);
		messageBody.setPointsReal(pointsReal);
		messageBody.setPointsSurplus(pointsSurplus);
		nsqService.msgsend(MessageReceiver.company, dCase.getCompanyId(), MessageSign.caseFinish, 0, messageBody);

		//按人合并所有分红记录
		mergeBonusUserLog(bonusPrizeUserLogList, bonusCommonUserLogList, dCase);

		return caseFinishBonusLog;
	}

	/**
	 * 按用户合并所有分红记录
	 * @param bonusPrizeUserLogList 进入奖励机制ideaTop分红记录
	 * @param bonusCommonUserLogList 普通参与用户分红记录
	 */
	private void mergeBonusUserLog(List<DCaseFinishBonusPrizeUserLog> bonusPrizeUserLogList, List<DCaseFinishBonusCommonUserLog> bonusCommonUserLogList, DCase dCase) {
		Map<String, DBonusPercentConfig> map = getBonusPercentConfigMap();
		Map<Long, DCaseFinishBonusUserLog> bonusUserLogMap = new HashMap<>();
		long caseId = dCase.getId();
		if (!bonusPrizeUserLogList.isEmpty()) {
			DBonusPercentConfig bonusPercentConfig = map.get("ideaTop");
			for (DCaseFinishBonusPrizeUserLog bonusPrizeUserLog : bonusPrizeUserLogList) {
				DCaseFinishBonusUserLog bonusUserLog = new DCaseFinishBonusUserLog();
				bonusUserLog.setCaseId(caseId);
				bonusUserLog.setUserId(bonusPrizeUserLog.getUserId());
				bonusUserLog.setBonusPoints(bonusPrizeUserLog.getBonusPoints());
				bonusUserLog.setCreateTime(bonusPrizeUserLog.getCreateTime());
				bonusUserLog.setBonusRemark(bonusPercentConfig.getRemark());
				bonusUserLogMap.put(bonusPrizeUserLog.getUserId(), bonusUserLog);
			}
		}

		if (!bonusCommonUserLogList.isEmpty()) {
			for (DCaseFinishBonusCommonUserLog bonusCommonUserLog : bonusCommonUserLogList) {
				List<Object> userIdList = JSONObject.decode(bonusCommonUserLog.getUserIdList(), List.class);
				//平均到每个人的分红为0,或分红人数是0,不处理
				if (bonusCommonUserLog.getBonusPointsAverage() == 0 || userIdList == null || userIdList.isEmpty()) {
					continue;
				}
				DBonusPercentConfig bonusPercentConfig = map.get(bonusCommonUserLog.getBonusName());
				for (Object id : userIdList) {
					long userId = StringUtil.getLong(id);
					if (bonusUserLogMap.get(userId) == null) {
						DCaseFinishBonusUserLog bonusUserLog = new DCaseFinishBonusUserLog();
						bonusUserLog.setCaseId(caseId);
						bonusUserLog.setUserId(userId);
						bonusUserLog.setBonusPoints(bonusCommonUserLog.getBonusPointsAverage());
						bonusUserLog.setCreateTime(bonusCommonUserLog.getCreateTime());
						bonusUserLog.setBonusRemark(bonusPercentConfig.getRemark());
						bonusUserLogMap.put(userId, bonusUserLog);
					} else {
						DCaseFinishBonusUserLog bonusUserLog = bonusUserLogMap.get(userId);
						bonusUserLog.setBonusPoints(bonusUserLog.getBonusPoints() + bonusCommonUserLog.getBonusPointsAverage());
						bonusUserLog.setCreateTime(bonusCommonUserLog.getCreateTime());
						bonusUserLog.setBonusRemark(bonusUserLog.getBonusRemark() + "，" + bonusPercentConfig.getRemark());
						bonusUserLogMap.put(userId, bonusUserLog);
					}
				}
			}
		}

		List<DCaseFinishBonusUserLog> list = new ArrayList<>(bonusUserLogMap.values());
		String key = KeyFactory.CaseFinishBonusUserLogListKey(caseId);
		for (DCaseFinishBonusUserLog caseFinishBonusUserLog : list) {
			DCaseFinishBonusUserLog bonusUserLog = logDao.saveCaseFinishBonusUserLog(caseFinishBonusUserLog);
			if (bonusUserLog == null) {
				logger.warn("logDao.saveCaseFinishBonusUserLog is fail, caseId :{}, userId :{}, points :{}, remark :{}", caseFinishBonusUserLog.getCaseId(), caseFinishBonusUserLog.getUserId(), caseFinishBonusUserLog.getBonusPoints(), caseFinishBonusUserLog.getBonusRemark());
			}
			redis.SORTSET.zadd(key, bonusUserLog.getBonusPoints(), String.valueOf(bonusUserLog.getId()));
			redis.STRINGS.setex(KeyFactory.CaseFinishBonusUserLogKey(bonusUserLog.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(bonusUserLog));

			guThreadPool.execute(() -> {
				//发送一条信息给有分红的用户
				CaseFinishBonusMessageBody messageBody = new CaseFinishBonusMessageBody();
				messageBody.setCaseId(caseId);
				messageBody.setPoints(bonusUserLog.getBonusPoints());
				nsqService.msgsend(MessageReceiver.user, bonusUserLog.getUserId(), MessageSign.caseFinishBonus, 0, messageBody);

				//发送一条推送给有分红的用户
				DUser user = userService.getUserById(bonusUserLog.getUserId());
				if (!user.getPushKey().isEmpty()) {
					getuiService.pushToSingle(user.getPushKey(), "你参与的专案《" + dCase.getName() + "》结束啦！恭喜你获得了" + messageBody.getPoints() + "G点！");
				}
			});
		}
	}

	/**
	 * 按创意获赞比例分(获赞数/总赞数)(当发创意人数(去重)超过N个的时候才会出现奖励机制)
	 * @param dCase
	 */
	private Map<Integer, List<DCaseFinishBonusPrizeUserLog>> ideaTop(DCase dCase) {
		List<DCaseFinishBonusPrizeUserLog> bonusPrizeUserLogList = new ArrayList<>();
		Map<Integer, List<DCaseFinishBonusPrizeUserLog>> map = new HashMap<>();

		DBonusPercentConfig bonusPercentConfig = getBonusPercentConfigMap().get("ideaTop");
		String bonusName = bonusPercentConfig.getName();
		long caseId = dCase.getId();
		int percent = bonusPercentConfig.getPercent();

		int bonusPointsNum = dCase.getPoints() / 100 * percent;
		int casePraise = caseDao.getPraiseNum(dCase.getId());

		if (casePraise == 0) {//总点赞数为0,分毛线
			map.put(0, bonusPrizeUserLogList);
			return map;
		}

		long t = System.nanoTime();
		List<Map<String, Object>> ideaList = ideaDao.getIdeaTopAll(caseId);
		logger.warn("ideaDao.getIdeaTopAll(caseId) exec time: {}", Time.showTime(System.nanoTime() - t));

		int ideaListSize = ideaList.size();
		int ideaPrecentCount = 0;//idea总百分比
		int ideaPointsCount = 0;//分出去的总G点
		HashSet<Long> userIdSet = new HashSet<Long>();//用户去重

		for (int i = 0; i < ideaListSize; i++) {
			Map<String, Object> idea = ideaList.get(i);

			//用户去重
			long userId = StringUtil.getLong(idea.get("user_id"));
			if (userIdSet.contains(userId)) {
				continue;
			}
			userIdSet.add(userId);

			int ideaPraise = StringUtil.getInt(idea.get("praise"));

			//计算分红百分比
			int n = StringUtil.getString(bonusPointsNum).length() + 1;//保留多少位小数
			double ideaPercent = MathUtil.round((ideaPraise * 100.0 / casePraise), n);

			int bonusPoints = (int) (bonusPointsNum * ideaPercent / 100);

			//分红G点大于1才更新数据库
			if (bonusPoints > 0) {
				ideaPrecentCount += ideaPercent;
				ideaPointsCount += bonusPoints;

				DCaseFinishBonusPrizeUserLog bonusPrizeUserLog = new DCaseFinishBonusPrizeUserLog();
				bonusPrizeUserLog.setCaseId(caseId);
				bonusPrizeUserLog.setPercent(percent);
				bonusPrizeUserLog.setBonusPointsNum(bonusPointsNum);
				bonusPrizeUserLog.setUserId(userId);
				bonusPrizeUserLog.setIdeaId(StringUtil.getLong(idea.get("id")));
				bonusPrizeUserLog.setCasePraise(casePraise);
				bonusPrizeUserLog.setIdeaPraise(ideaPraise);
				bonusPrizeUserLog.setIdeaPercent(new BigDecimal(ideaPercent));
				bonusPrizeUserLog.setBonusPoints(bonusPoints);
				bonusPrizeUserLog.setCreateTime(Time.now());
				bonusPrizeUserLog.setBonusName(bonusName);
				logDao.saveCaseFinishBonusPrizeUserLog(bonusPrizeUserLog);
				bonusPrizeUserLogList.add(bonusPrizeUserLog);

				DUser user = userService.getUserById(userId);
				if (user == null) {
					continue;
				}

				int beforePoints = commonService.getCountColumn(CountKey.userPoints, userId);//加之前的G点
				int afterPoints = beforePoints + bonusPoints;//加之后的G点
				boolean updateSuccess = userService.addPoints(userId, bonusPoints, UserPointsAddEvent.caseFinish);//写用户加G点日志
				logger.warn("user add points because 'ideaTop' of case finish! userId: {}, caseId: {}, beforePoints: {}, afterPoints: {}, success: {}", user.getId(), caseId, beforePoints, afterPoints, updateSuccess);

				// 点子前3，每人+50经验积分
				if (i >= 0 && i <= 2) {
					user.setExp(user.getExp() + 50);
					userService.userLevelUp(user);
					int beforeScore = commonService.getCountColumn(CountKey.userScore, user.getId());
					int afterScore = beforeScore + 50;
					updateSuccess = userService.addScore(user.getId(), 50, UserScoreAddEvent.ideaTop3);//自带updateUser
					logger.warn("user add {} score because 'idea rank {}' of case finish! userId: {}, caseId: {}, beforeScore: {}, afterScore: {}, success: {}", 50, (i + 1), user.getId(), caseId, beforeScore, afterScore, updateSuccess);
				}
			}
		}
		logger.warn("user add points because 'ideaTop' of case finish! ideaPrecentCount: {}", ideaPrecentCount);
		logger.warn("user add points because 'ideaTop' of case finish! ideaPointsCount: {}", ideaPointsCount);

		int pointsSurplus = bonusPointsNum - ideaPointsCount;
		map.put(pointsSurplus, bonusPrizeUserLogList);

		return map;
	}

	/**
	 * 前3创意的点赞人(不去重)(当点⼦超过15个的时候才会出现奖励机制)
	 * @param dCase
	 * @return
	 */
	private DCaseFinishBonusCommonUserLog praiseTopThree(DCase dCase) {
		DBonusPercentConfig bonusPercentConfig = getBonusPercentConfigMap().get("praiseTopThree");
		long caseId = dCase.getId();

		//取前3创意
		long t = System.nanoTime();
		List<DIdea> ideaList = ideaDao.getIdeaTop(caseId, 3);
		logger.warn("ideaDao.getIdeaTop(caseId, 3) exec time: {}", Time.showTime(System.nanoTime() - t));

		List<Long> ideaIdList = new ArrayList<Long>();
		for (DIdea idea : ideaList) {
			ideaIdList.add(idea.getId());
		}
		//取点前3创意赞的用户(去重)
		List<Long> userIdList = ideaDao.praiseAllUserOfIdeas(ideaIdList);

		DCaseFinishBonusCommonUserLog bonusCommonUserLog = buildBonusCommonUserLog(bonusPercentConfig, userIdList, dCase);
		if (bonusCommonUserLog == null) {
			return null;
		}

		if (bonusCommonUserLog.getUserNum() > 0) {
			for (Long userId : userIdList) {
				DUser user = userService.getUserById(userId);
				if (user == null) {
					continue;
				}
				int bonusPointsAverage = bonusCommonUserLog.getBonusPointsAverage();
				int beforePoints = commonService.getCountColumn(CountKey.userPoints, userId);//加之前的G点
				int afterPoints = beforePoints + bonusPointsAverage;//加之后的G点
				//分红平均G点大于1才更新数据库
				if (bonusPointsAverage > 1) {
					boolean updateSuccess = userService.addPoints(user.getId(), bonusPointsAverage, UserPointsAddEvent.caseFinish);//写用户加G点日志
					logger.warn("user add points because 'praiseTopThree' of case finish! userId: {}, caseId: {}, beforePoints: {}, afterPoints: {}, success: {}", user.getId(), caseId, beforePoints, afterPoints, updateSuccess);
				}
			}
		}
		return bonusCommonUserLog;

	}

	/**
	 * 提出创意的人分红方法(创意的人人有份,用户去重)
	 * @param dCase
	 * @return
	 */
	private DCaseFinishBonusCommonUserLog ideaAll(DCase dCase) {
		DBonusPercentConfig bonusPercentConfig = getBonusPercentConfigMap().get("ideaAll");
		long caseId = dCase.getId();

		long t = System.nanoTime();
		List<Long> userIdList = ideaDao.ideaUserOfCase(caseId);//要分红的用户(一个用户能发N个创意,sql已去重)
		logger.warn("ideaDao.ideaUserOfCase(caseId) exec time: {}", Time.showTime(System.nanoTime() - t));

		DCaseFinishBonusCommonUserLog bonusCommonUserLog = buildBonusCommonUserLog(bonusPercentConfig, userIdList, dCase);
		if (bonusCommonUserLog == null) {
			return null;
		}

		if (bonusCommonUserLog.getUserNum() > 0) {
			for (Long userId : userIdList) {
				DUser user = userService.getUserById(userId);
				if (user == null) {
					continue;
				}
				int bonusPointsAverage = bonusCommonUserLog.getBonusPointsAverage();
				int beforePoints = commonService.getCountColumn(CountKey.userPoints, userId);//加之前的G点
				int afterPoints = beforePoints + bonusPointsAverage;//加之后的G点
				//分红平均G点大于1才更新数据库
				if (bonusPointsAverage > 1) {
					boolean updateSuccess = userService.addPoints(user.getId(), bonusPointsAverage, UserPointsAddEvent.caseFinish);//写用户加G点日志
					logger.warn("user add points because 'ideaAll' of case finish! userId: {}, caseId: {}, beforePoints: {}, afterPoints: {}, success: {}", user.getId(), caseId, beforePoints, afterPoints, updateSuccess);
				}
			}
		}
		return bonusCommonUserLog;
	}

	/**
	 * 吐槽专案的人分红方法(吐槽的人人有份)
	 * @param dCase
	 * @return
	 */
	private DCaseFinishBonusCommonUserLog flow(DCase dCase) {
		DBonusPercentConfig bonusPercentConfig = getBonusPercentConfigMap().get("flow");
		long caseId = dCase.getId();

		long t = System.nanoTime();
		List<Long> userIdList = flowDao.flowUserIdByCaseId(caseId);//要分红的用户(已去重)
		logger.warn("flowDao.flowUserOfCase(caseId) exec time: {}", Time.showTime(System.nanoTime() - t));

		DCaseFinishBonusCommonUserLog bonusCommonUserLog = buildBonusCommonUserLog(bonusPercentConfig, userIdList, dCase);
		if (bonusCommonUserLog == null) {
			return null;
		}

		if (bonusCommonUserLog.getUserNum() > 0) {
			for (Long userId : userIdList) {
				DUser user = userService.getUserById(userId);
				if (user == null) {
					continue;
				}
				int bonusPointsAverage = bonusCommonUserLog.getBonusPointsAverage();
				int beforePoints = commonService.getCountColumn(CountKey.userPoints, userId);//加之前的G点
				int afterPoints = beforePoints + bonusPointsAverage;//加之后的G点
				//分红平均G点大于1才更新数据库
				if (bonusPointsAverage > 1) {
					boolean updateSuccess = userService.addPoints(user.getId(), bonusPointsAverage, UserPointsAddEvent.caseFinish);//写用户加G点日志
					logger.warn("user add points because 'flow' of case finish! userId: {}, caseId: {}, beforePoints: {}, afterPoints: {}, success: {}", user.getId(), caseId, beforePoints, afterPoints, updateSuccess);
				}
			}
		}
		return bonusCommonUserLog;
	}

	/**
	 * 点赞专案创意的人分红方法(点赞的人人有份)
	 * @param dCase
	 */
	private DCaseFinishBonusCommonUserLog praiseAll(DCase dCase) {
		DBonusPercentConfig bonusPercentConfig = getBonusPercentConfigMap().get("praiseAll");
		long caseId = dCase.getId();

		long t = System.nanoTime();
		List<Long> userIdList = ideaDao.praiseAllUserOfCase(caseId);//要分红的用户(已去重)
		logger.warn("ideaDao.praiseAllUserOfCase(caseId) exec time: {}", Time.showTime(System.nanoTime() - t));

		DCaseFinishBonusCommonUserLog bonusCommonUserLog = buildBonusCommonUserLog(bonusPercentConfig, userIdList, dCase);
		if (bonusCommonUserLog == null) {
			return null;
		}

		if (bonusCommonUserLog.getUserNum() > 0) {
			for (Long userId : userIdList) {
				DUser user = userService.getUserById(userId);
				if (user == null) {
					continue;
				}
				int bonusPointsAverage = bonusCommonUserLog.getBonusPointsAverage();
				int beforePoints = commonService.getCountColumn(CountKey.userPoints, userId);//加之前的G点
				int afterPoints = beforePoints + bonusPointsAverage;//加之后的G点
				//分红平均G点大于1才更新数据库
				if (bonusPointsAverage > 1) {
					boolean updateSuccess = userService.addPoints(user.getId(), bonusPointsAverage, UserPointsAddEvent.caseFinish);//写用户加G点日志
					logger.warn("user add points because 'praiseAll' of case finish! userId: {}, caseId: {}, beforePoints: {}, afterPoints: {}, success: {}", user.getId(), caseId, beforePoints, afterPoints, updateSuccess);
				}
			}
		}
		return bonusCommonUserLog;
	}

	/**
	 * 普通参与用户分红日志组装方法
	 * @param bonusPercentConfig 分红百分比配置
	 * @param userIdList 用户id列表
	 * @param dCase 专案实体
	 * @return
	 */
	private DCaseFinishBonusCommonUserLog buildBonusCommonUserLog(DBonusPercentConfig bonusPercentConfig, List<Long> userIdList, DCase dCase) {
		DCaseFinishBonusCommonUserLog bonusCommonUserLog = new DCaseFinishBonusCommonUserLog();

		int userIdsNum = userIdList.size();//要分红的用户总数
		logger.warn("bonusPercentConfig.getName(): {}, userIdList.size(): {}", bonusPercentConfig.getName(), userIdsNum);

		int percent = bonusPercentConfig.getPercent();//分红的百分比
		if (percent <= 0) {
			return null;
		}
		int bonusPointsPlan = dCase.getPoints() / 100 * percent;//要分红的G点

		//必须保存的参数
		bonusCommonUserLog.setCaseId(dCase.getId());
		bonusCommonUserLog.setPercent(percent);
		bonusCommonUserLog.setBonusPointsPlan(bonusPointsPlan);
		bonusCommonUserLog.setBonusPointsSurplus(bonusPointsPlan);//没清算前,剩余分红G点为计划分红G点
		bonusCommonUserLog.setBonusName(bonusPercentConfig.getName());
		bonusCommonUserLog.setCreateTime(Time.now());
		bonusCommonUserLog.setUserIdList("");

		//有用户才分钱,数值默认为0
		if (userIdsNum > 0) {
			int pointsMax = bonusPercentConfig.getPointsMax();//每个用户分到的上限
			int bonusPointsAverage = bonusPointsPlan / userIdsNum;//平均分红G点
			if (bonusPointsAverage > pointsMax && pointsMax > 0) {//有G点上限,且平均G点超过上限
				bonusPointsAverage = pointsMax;
			}

			int bonusPointsReal = bonusPointsAverage * userIdsNum;//实际分出的G点
			int bonusPointsSurplus = bonusPointsPlan - bonusPointsReal;//剩余的G点
			String userIdListJson = JSONObject.encode(userIdList);

			bonusCommonUserLog.setBonusPointsReal(bonusPointsReal);
			bonusCommonUserLog.setBonusPointsSurplus(bonusPointsSurplus);
			bonusCommonUserLog.setBonusPointsAverage(bonusPointsAverage);
			bonusCommonUserLog.setUserNum(userIdsNum);
			bonusCommonUserLog.setUserIdList(userIdListJson);
		}

		return bonusCommonUserLog;
	}
}
