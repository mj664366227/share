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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gu.core.enums.LengthLimit;
import com.gu.core.enums.MessageReceiver;
import com.gu.core.enums.MessageSign;
import com.gu.core.msgbody.company.FocusCaseMessageBody;
import com.gu.core.msgbody.company.UserFocusCompanyMessageBody;
import com.gu.core.nsq.NsqService;
import com.gu.core.protocol.CaseListResponse;
import com.gu.core.protocol.CaseSearchListResponse;
import com.gu.core.protocol.CaseSearchResponse;
import com.gu.core.protocol.CaseTypeResponse;
import com.gu.core.protocol.CompanyCaseListResponse;
import com.gu.core.protocol.OneCaseResponse;
import com.gu.core.protocol.UserFocusCaseListResponse;
import com.gu.core.protocol.UserFocusCaseResponse;
import com.gu.core.protocol.UserTakePartInCase;
import com.gu.core.protocol.UserTakePartInCaseListResponse;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.CaseCountDao;
import com.gu.dao.CaseDao;
import com.gu.dao.CompanyDao;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCasePraiseCount;
import com.gu.dao.model.DCaseStyleConfig;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DFocusCase;
import com.gu.dao.model.DTakePartInCase;
import com.gu.dao.model.DUser;
import com.gu.service.common.CommonService;
import com.gu.service.company.CompanyService;
import com.gu.service.idea.IdeaService;
import com.gu.service.user.UserService;

import redis.clients.jedis.ZParams;
import redis.clients.jedis.ZParams.Aggregate;

@Component
public class CaseService {
	/**
	* logger
	*/
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CaseDao caseDao;
	@Autowired
	private Redis redis;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private CaseCountDao caseCountDao;
	@Autowired
	private IdeaService ideaService;
	@Autowired
	private GuThreadPool guThreadPool;
	@Autowired
	private NsqService nsqService;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private CommonService commonService;

	/**
	 * 专案类型配置缓存
	 */
	private LoadingCache<Integer, List<Map<String, Object>>> caseTypeConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, List<Map<String, Object>>>() {
		public List<Map<String, Object>> load(Integer key) throws Exception {
			logger.warn("load case type config");
			return caseDao.getCaseTypeConfig();
		}
	});

	/**
	 * 专案有效天数配置缓存
	 */
	private LoadingCache<Integer, Map<Integer, String>> caseExpireConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, Map<Integer, String>>() {
		public Map<Integer, String> load(Integer key) throws Exception {
			logger.warn("load case expire config");
			return caseDao.getCaseExpireConfig();
		}
	});

	/**
	 * 专案金额配置缓存
	 */
	private LoadingCache<Integer, Map<Integer, String>> casePointsConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, Map<Integer, String>>() {
		public Map<Integer, String> load(Integer key) throws Exception {
			logger.warn("load case points config");
			return caseDao.getCasePointsConfig();
		}
	});

	/**
	 * 专案征集类型配置缓存
	 */
	private LoadingCache<Integer, Map<Long, DCaseStyleConfig>> caseStyleConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, Map<Long, DCaseStyleConfig>>() {
		public Map<Long, DCaseStyleConfig> load(Integer key) throws Exception {
			logger.warn("load case stype config");
			return caseDao.getCaseStyleConfig();
		}
	});

	/**
	 * 初始化配置数据
	 */
	@PostConstruct
	public void init() {
		initCaseTypeConfig();
		initCaseExpireConfig();
		initCasePointsConfig();
		initCaseStyleConfig();
	}

	/**
	 * 初始化专案类型配置
	 */
	public void initCaseTypeConfig() {
		caseTypeConfig.invalidateAll();
		getCaseTypeConfig();
	}

	/**
	 * 初始化专案征集类型配置
	 */
	public void initCaseStyleConfig() {
		caseStyleConfig.invalidateAll();
		getCaseStyleConfig();
	}

	/**
	 * 新增专案类型设置
	 * @param type 类型名字
	 */
	public void addCaseTypeConfig(String type) {
		caseDao.addCaseTypeConfig(type);
	}

	/**
	 * 判断专案类型是否重复
	 * @param type 类型名字
	 */
	public boolean existsCaseTypeConfig(String type) {
		return caseDao.existsCaseTypeConfig(type);
	}

	/**
	 * 删除专案类型设置
	 * @param id 类型id
	 */
	public void deleteCaseTypeConfig(int id) {
		caseDao.deleteCaseTypeConfig(id);
	}

	/**
	 * 初始化专案有效天数配置
	 */
	public void initCaseExpireConfig() {
		caseExpireConfig.invalidateAll();
		getCaseExpireConfig();
	}

	/**
	 * 初始化专案金额配置
	 */
	public void initCasePointsConfig() {
		casePointsConfig.invalidateAll();
		getCasePointsConfig();
	}

	/**
	 * 删除专案金额配置
	 */
	public void deleteCasePointsConfig(int points) {
		caseDao.deleteCasePointsConfig(points);
	}

	/**
	 * 添加专案金额配置
	 */
	public void addCasePointsConfig(int points) {
		caseDao.addCasePointsConfig(points);
	}

	/**
	 * 获取专案类型配置
	 * @param pageSize 页面大小
	 * @param page 页码
	 */
	public List<CaseTypeResponse> getCaseTypeConfig(int pageSize, int page) {
		List<Map<String, Object>> caseTypeConfig = getCaseTypeConfig();
		List<CaseTypeResponse> list = new ArrayList<CaseTypeResponse>();

		pageSize = pageSize <= 0 ? 100 : pageSize;
		int size = caseTypeConfig.size();
		int begin = (page - 1) * pageSize;
		int end = begin + pageSize - 1;

		for (int i = begin; i <= end; i++) {
			if (i >= size) {
				return list;
			}
			Map<String, Object> tmp = caseTypeConfig.get(i);
			long id = StringUtil.getLong(tmp.get("id"));
			String name = StringUtil.getString(tmp.get("name"));
			if (id <= 0 || name.isEmpty()) {
				continue;
			}
			CaseTypeResponse caseTypeResponse = new CaseTypeResponse();
			caseTypeResponse.setId(id);
			caseTypeResponse.setName(name);
			list.add(caseTypeResponse);
		}
		return list;
	}

	/**
	 * 获取专案类型配置map
	 * @author ruan 
	 */
	public Map<Long, String> getCaseTypeConfigMap() {
		List<Map<String, Object>> list = getCaseTypeConfig();
		Map<Long, String> map = new HashMap<Long, String>(list.size());
		for (Map<String, Object> tmp : list) {
			map.put(StringUtil.getLong(tmp.get("id")), StringUtil.getString(tmp.get("name")));
		}
		return map;
	}

	/**
	 * 获取专案类型配置
	 */
	private List<Map<String, Object>> getCaseTypeConfig() {
		try {
			return caseTypeConfig.get(1);
		} catch (ExecutionException e) {
			logger.error("", e);
		}
		return new ArrayList<Map<String, Object>>(0);
	}

	/**
	 * 获取专案有效天数配置缓存
	 */
	public Map<Integer, String> getCaseExpireConfig() {
		try {
			return caseExpireConfig.get(1);
		} catch (ExecutionException e) {
			logger.error("", e);
		}
		return new HashMap<Integer, String>(0);
	}

	/**
	 * 获取专案征集类型配置
	 */
	public Map<Long, DCaseStyleConfig> getCaseStyleConfig() {
		try {
			return caseStyleConfig.get(1);
		} catch (ExecutionException e) {
			logger.error("", e);
		}
		return new HashMap<Long, DCaseStyleConfig>(0);
	}

	/**
	 * 判断专案征集类型是否重复
	 * @param name 类型名字
	 */
	public boolean existsCaseStyleConfig(String name) {
		return caseDao.existsCaseStyleConfig(name);
	}

	/**
	 * 新增专案征集类型设置
	 * @param name 类型名字
	 * @param points 金额
	 */
	public void addCaseStyleConfig(String name, int points) {
		caseDao.addCaseStyleConfig(name, points);
	}

	/**
	 * 删除专案征集类型配置
	 * @param id 类型id
	 */
	public void deleteCaseStyleConfig(int id) {
		caseDao.deleteCaseStyleConfig(id);
	}

	/**
	 * 删除专案有效天数配置
	 */
	public void deleteCaseExpireConfig(int seconds) {
		caseDao.deleteCaseExpireConfig(seconds);
	}

	/**
	 * 保存专案有效天数配置
	 */
	public void saveCaseExpireConfig(Map<Integer, String> config) {
		caseDao.saveCaseExpireConfig(config);
	}

	/**
	 * 获取专案金额配置缓存
	 */
	public Map<Integer, String> getCasePointsConfig() {
		try {
			return casePointsConfig.get(1);
		} catch (ExecutionException e) {
			logger.error("", e);
		}
		return new HashMap<Integer, String>(0);
	}

	/**
	 * 判断专案类型配置是否存在
	 * @param caseType 专案类型
	 */
	public boolean isCaseTypeConfigExists(int caseType) {
		for (Map<String, Object> tmp : getCaseTypeConfig()) {
			if (StringUtil.getInt(tmp.get("id")) == caseType) {
				return true;
			}
		}
		return false;
	}

	/**
	 * SortSet列表加占位
	 * @param key
	 */
	public void addSortSetAnchor(String key) {
		redis.SORTSET.zadd(key, -1, "-1");
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
	public DCase addCase(final long companyId, String name, String description, String type, int caseExpire, int points, long caseStyle, String image1, String image2, String image3, String image4, String image5, String video) {
		// 写入专案
		DCase dCase = caseDao.addCase(companyId, name, description, type, caseExpire, points, caseStyle, image1, image2, image3, image4, image5, video);
		if (dCase == null) {
			return null;
		}

		// 单独统计点赞数
		commonService.setCountColumnRedis(CountKey.casePraiseNum, dCase.getId(), 0);
		// 单独统计吐槽数
		commonService.setCountColumnRedis(CountKey.caseFlowNum, dCase.getId(), 0);
		// 单独统计创意数
		commonService.setCountColumnRedis(CountKey.caseIdeaNum, dCase.getId(), 0);
		// 单独统计粉丝数
		commonService.setCountColumnRedis(CountKey.caseFansNum, dCase.getId(), 0);
		// 单独统计参与数
		commonService.setCountColumnRedis(CountKey.caseTakePartInNum, dCase.getId(), 0);

		saveCaseToCache(dCase);

		guThreadPool.execute(() -> {

			// 统计发布专案数
			int weekOfYear = StringUtil.getInt(Time.date("yyyy") + Time.getWeekOfYear());
			int monthOfYear = StringUtil.getInt(Time.date("yyyy") + Time.getMonthOfYear());

			// 每天发布专案品牌数统计
			String date = Time.date("yyyyMMdd");
			String companyPubCaseKey = KeyFactory.companyPubCaseKey(date);
			if (redis.SORTSET.zscore(companyPubCaseKey, String.valueOf(companyId)) <= 0) {
				redis.SORTSET.zadd(companyPubCaseKey, companyId, String.valueOf(companyId));
				redis.KEYS.expire(companyPubCaseKey, 86402);
				companyDao.recordCompanyPubCase(date);
			}

			// 每周
			String companyPubCaseWeekKey = KeyFactory.companyPubCaseWeekKey(weekOfYear);
			redis.SORTSET.zadd(companyPubCaseWeekKey, companyId, String.valueOf(companyId));
			caseDao.recordCompanyPubCaseWeek(weekOfYear, (int) redis.SORTSET.zcard(companyPubCaseWeekKey));
			redis.KEYS.expire(companyPubCaseWeekKey, 86400 * 8);

			// 每月
			String companyPubCaseMonthKey = KeyFactory.companyPubCaseMonthKey(monthOfYear);
			redis.SORTSET.zadd(companyPubCaseMonthKey, companyId, String.valueOf(companyId));
			caseDao.recordCompanyPubCaseMonth(monthOfYear, (int) redis.SORTSET.zcard(companyPubCaseMonthKey));
			redis.KEYS.expire(companyPubCaseMonthKey, 86400 * 32);

			// 每周发表专案统计
			caseDao.recordCasePubWeek(weekOfYear);
		});
		return dCase;
	}

	/**
	 * 根据专案id获取专案
	 * @param caseId 专案id
	 */
	public DCase getCaseById(long caseId) {
		byte[] b = redis.STRINGS.get(KeyFactory.caseKey(caseId).getBytes());
		DCase dCase = null;
		if (b == null || b.length <= 0) {
			dCase = caseDao.getCaseById(caseId);
			if (dCase == null) {
				return null;
			}
			saveCaseToCache(dCase);
			return dCase;
		} else {
			dCase = SerialUtil.fromBytes(b, DCase.class);
			if (dCase == null) {
				return null;
			}
		}
		dCase.setFansNum(commonService.getCountColumn(CountKey.caseFansNum, dCase.getId()));
		dCase.setFlowNum(commonService.getCountColumn(CountKey.caseFlowNum, dCase.getId()));
		dCase.setIdeaNum(commonService.getCountColumn(CountKey.caseIdeaNum, dCase.getId()));
		dCase.setPraiseNum(commonService.getCountColumn(CountKey.casePraiseNum, dCase.getId()));
		dCase.setTakePartInNum(commonService.getCountColumn(CountKey.caseTakePartInNum, dCase.getId()));
		return dCase;
	}

	/**
	 * 修改专案 
	 * @param dCase 专案对象
	 */
	public boolean updateCase(DCase dCase) {
		if (!caseDao.updateCase(dCase)) {
			return false;
		}

		saveCaseToCache(dCase);
		return true;
	}

	/**
	 * 把case对象写入缓存
	 * @author ruan 
	 * @param dCase 专案对象
	 */
	public void saveCaseToCache(final DCase dCase) {
		guThreadPool.execute(() -> {
			Map<String, Map<String, Double>> keyScoreMembers = new HashMap<String, Map<String, Double>>();
			boolean isOver = dCase.getEndTime() < Time.now();
			String[] tagList = dCase.getType().split(",");

			// 企业搜索专案KEY缓存
			long companyId = dCase.getCompanyId();
			Set<String> searchKeySet = redis.SETS.smembers(KeyFactory.companyCaseSearchKeySetKey(companyId));
			// 更新企业搜索专案缓存
			for (String searchKey : searchKeySet) {
				Pattern p = Pattern.compile(".*" + searchKey + ".*");
				// 匹配专案名称
				if (p.matcher(dCase.getName()).matches()) {
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.companyCaseSearchKey(companyId, searchKey), dCase.getId(), String.valueOf(dCase.getId()));
				}
				// 匹配专案标签
				for (String caseType : tagList) {
					if (p.matcher(caseType).matches()) {
						redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.companyCaseSearchKey(companyId, searchKey), dCase.getId(), String.valueOf(dCase.getId()));
					}
				}
			}

			redis.STRINGS.setex(KeyFactory.caseKey(dCase.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(dCase));
			for (String caseType : tagList) {
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.caseListKey(StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.caseListKey(dCase.getCompanyId(), StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
			}
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.allCaseListKey(), dCase.getId(), String.valueOf(dCase.getId()));
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.newCaseListKey(), dCase.getId(), String.valueOf(dCase.getId()));
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.companyCaseListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.companyCasePageListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));

			// 如果已过期，就添加到过期列表中；否则是进行列表
			if (isOver) {
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.overCaseListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));
				for (String caseType : tagList) {
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.overCaseListKey(dCase.getCompanyId(), StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
				}
			} else {
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.goingCaseListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));
				for (String caseType : tagList) {
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.goingCaseListKey(dCase.getCompanyId(), StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
				}
			}

			redis.SORTSET.zadd(keyScoreMembers);
		});
	}

	/**
	* 根据企业id获取专案列表
	* @param company 企业对象
	* @param tag 标签选择
	* @param action tap选择(allCase、goingCase、overCase)
	* @param page 当前页码
	* @param pageSize 页面大小
	*/
	public CompanyCaseListResponse getCompanyCaseList(DCompany company, Set<Long> tag, String action, int page, int pageSize) {
		String key = null;
		CompanyCaseListResponse companyCaseListResponse = new CompanyCaseListResponse();

		// 先从缓存拿
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 标签查询
		Set<String> listFromCache = null;
		boolean isTagEmpty = tag.isEmpty();
		if (!isTagEmpty) {
			// redis查询参数
			key = KeyFactory.caseListUnionKey(company.getId());
			ZParams zParams = new ZParams();
			zParams.aggregate(Aggregate.MIN);

			// 组装列表key
			int size = tag.size(), i = 0;
			String[] sets = new String[size];
			for (long tagId : tag) {
				if (++i > size) {
					break;
				}
				switch (action) {
				case "allCase":
					sets[i - 1] = KeyFactory.caseListKey(company.getId(), (int) tagId);
					break;
				case "goingCase":
					sets[i - 1] = KeyFactory.goingCaseListKey(company.getId(), (int) tagId);
					break;
				case "overCase":
					sets[i - 1] = KeyFactory.overCaseListKey(company.getId(), (int) tagId);
					break;
				}
			}

			// 把这些集合做一个并集
			long total = redis.SORTSET.zunionstore(key, zParams, sets);
			companyCaseListResponse.setTotal((int) total);

			// 从并集查找结果
			listFromCache = redis.SORTSET.zrevrange(key, start, end);

			// 1秒过期，保证数据实时
			redis.KEYS.expire(key, 1);
		} else {
			// 全量查询
			switch (action) {
			case "allCase":
				key = KeyFactory.companyCasePageListKey(company.getId());
				int allCaseNum = commonService.getCountColumn(CountKey.companyAllCaseNum, company.getId());
				companyCaseListResponse.setTotal(allCaseNum);
				break;
			case "goingCase":
				key = KeyFactory.goingCaseListKey(company.getId());
				int goingCaseNum = commonService.getCountColumn(CountKey.companyGoingCaseNum, company.getId());
				companyCaseListResponse.setTotal(goingCaseNum);
				break;
			case "overCase":
				key = KeyFactory.overCaseListKey(company.getId());
				int overCaseNum = commonService.getCountColumn(CountKey.companyOverCaseNum, company.getId());
				companyCaseListResponse.setTotal(overCaseNum);
				break;
			}

			listFromCache = redis.SORTSET.zrevrange(key, start, end);
		}

		// 如果数缓存没有，穿透数据库查1000条
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCase> companyCaseList = caseDao.getCompanyCaseList(company.getId(), page, page == 1 ? LengthLimit.reloadFromRedisLen.getLength() : pageSize);
			if (companyCaseList == null || companyCaseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return companyCaseListResponse;
			}

			int now = Time.now();
			int size = companyCaseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCase dCase : companyCaseList) {
				String caseId = String.valueOf(dCase.getId());
				if (!isTagEmpty) {
					for (String type : dCase.getType().split(",")) {
						if (tag.contains(StringUtil.getLong(type))) {
							listFromCache.add(caseId);
						}
					}
				} else {
					listFromCache.add(caseId);
				}
				boolean isOver = dCase.getEndTime() < now;
				switch (action) {
				case "allCase":
					scoreMembers.put(caseId, dCase.getId() * 1D);
					break;
				case "goingCase":
					if (!isOver) {
						scoreMembers.put(caseId, dCase.getId() * 1D);
					}
					break;
				case "overCase":
					if (isOver) {
						scoreMembers.put(caseId, dCase.getId() * 1D);
					}
					break;
				}
			}
			if (!scoreMembers.isEmpty()) {
				redis.SORTSET.zadd(key, scoreMembers);
			}
		}

		// 批量获取专案
		int now = Time.now(), i = 0;
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);
		Map<Long, Integer> fansNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.caseFansNum);
		Map<Long, Integer> takePartInNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.caseTakePartInNum);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			if (++i > pageSize) {
				break;
			}
			DCase dCase = e.getValue();
			if (dCase == null) {
				continue;
			}
			boolean isOver = dCase.getEndTime() < now;
			switch (action) {
			case "allCase":
				companyCaseListResponse.addList(dCase.toCompanyCase(now, fansNumMap.get(dCase.getId()), takePartInNumMap.get(dCase.getId())));
				break;
			case "goingCase":
				if (!isOver) {
					companyCaseListResponse.addList(dCase.toCompanyCase(now, fansNumMap.get(dCase.getId()), takePartInNumMap.get(dCase.getId())));
				}
				break;
			case "overCase":
				if (isOver) {
					companyCaseListResponse.addList(dCase.toCompanyCase(now, fansNumMap.get(dCase.getId()), takePartInNumMap.get(dCase.getId())));
				}
				break;
			}
		}
		return companyCaseListResponse;
	}

	/**
	* 根据企业id获取专案列表
	* @param companyId 企业id
	* @param lastCaseId 上一页最后一个专案id
	* @param pageSize 页面大小
	*/
	public CompanyCaseListResponse getCompanyCaseList(long companyId, long lastCaseId, int pageSize) {
		String key = KeyFactory.companyCaseListKey(companyId);
		CompanyCaseListResponse companyCaseListResponse = new CompanyCaseListResponse();

		// 先从缓存拿数据
		lastCaseId = lastCaseId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastCaseId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCase> caseList = caseDao.getCompanyCaseList(companyId, lastCaseId, pageSize);
			if (caseList == null || caseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return companyCaseListResponse;
			}

			int size = caseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCase dCase : caseList) {
				String id = String.valueOf(dCase.getId());
				scoreMembers.put(id, dCase.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取专案
		int now = Time.now();
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);
		Map<Long, Integer> fansNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.caseFansNum);
		Map<Long, Integer> takePartInNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.caseTakePartInNum);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			if (dCase == null) {
				continue;
			}
			companyCaseListResponse.addList(dCase.toCompanyCase(now, fansNumMap.get(dCase.getId()), takePartInNumMap.get(dCase.getId())));
		}
		return companyCaseListResponse;
	}

	/**
	 * 根据专案类型获取专案列表
	 * @param userId 用户id
	 * @param caseType 专案类型
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public CaseListResponse getCaseList(long userId, int caseType, long lastCaseId, int pageSize) {
		String key = KeyFactory.caseListKey(caseType);
		CaseListResponse caseListResponse = new CaseListResponse();

		// 先从缓存拿数据
		lastCaseId = lastCaseId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastCaseId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCase> caseList = caseDao.getCaseList(caseType, lastCaseId, pageSize);
			if (caseList == null || caseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return caseListResponse;
			}

			int size = caseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCase dCase : caseList) {
				String id = String.valueOf(dCase.getId());
				scoreMembers.put(id, dCase.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取专案
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);

		int now = Time.now();

		// 组织专案列表
		Set<String> companyIdSet = new LinkedHashSet<String>(pageSize);
		List<OneCaseResponse> oneCaseResponseList = new ArrayList<OneCaseResponse>(pageSize);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			if (dCase.getIsShow() != 1 || dCase.getIsPass() != 1) {
				continue;
			}
			OneCaseResponse oneCaseResponse = new OneCaseResponse();
			oneCaseResponse.setCompanyId(dCase.getCompanyId());
			oneCaseResponse.setCaseId(dCase.getId());
			oneCaseResponse.setImage(dCase.getImage1());
			oneCaseResponse.setPoints(dCase.getPoints());
			oneCaseResponse.setName(dCase.getName());
			oneCaseResponse.setIsFocus(getFocusCase(userId, dCase.getId()) != null ? 1 : 0);
			oneCaseResponse.setIsIdeaPub(ideaService.isIdeaPub(userId, dCase.getId()) ? 1 : 0);
			oneCaseResponse.setIsOver(dCase.getEndTime() < now ? 1 : 0);
			oneCaseResponse.setLeftTime(dCase.getEndTime() - now);
			oneCaseResponse.setTagList(getCaseCaseTypeList(dCase));
			oneCaseResponseList.add(oneCaseResponse);

			// 用来获取企业信息
			companyIdSet.add(String.valueOf(dCase.getCompanyId()));
		}

		// 批量获取企业信息
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(companyIdSet);
		Iterator<OneCaseResponse> it = oneCaseResponseList.iterator();
		while (it.hasNext()) {
			OneCaseResponse oneCaseResponse = it.next();
			DCompany company = companyMap.get(oneCaseResponse.getCompanyId());
			if (company == null) {
				// 用迭代器，用获取不到数据的时候，就把节点删除
				it.remove();
				logger.warn("CompanyService.getCaseList, can not get company, companyId: {}", oneCaseResponse.getCompanyId());
				continue;
			}
			oneCaseResponse.setCompanyLogo(company.getLogoImage());
			oneCaseResponse.setCompanyName(company.getName());
		}

		caseListResponse.setList(oneCaseResponseList);
		return caseListResponse;
	}

	/**
	 * 获取所有专案列表
	 * @param userId 用户id
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public CaseListResponse getAllCaseList(long userId, long lastCaseId, int pageSize) {
		String key = KeyFactory.allCaseListKey();
		CaseListResponse caseListResponse = new CaseListResponse();

		// 先从缓存拿数据
		lastCaseId = lastCaseId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastCaseId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCase> caseList = caseDao.getAllCaseList(lastCaseId, pageSize);
			if (caseList == null || caseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return caseListResponse;
			}

			int size = caseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCase dCase : caseList) {
				String id = String.valueOf(dCase.getId());
				scoreMembers.put(id, dCase.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取专案
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);

		int now = Time.now();

		// 组织专案列表
		Set<String> companyIdSet = new LinkedHashSet<String>(pageSize);
		List<OneCaseResponse> oneCaseResponseList = new ArrayList<OneCaseResponse>(pageSize);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			if (dCase.getIsShow() != 1 || dCase.getIsPass() != 1) {
				continue;
			}
			OneCaseResponse oneCaseResponse = new OneCaseResponse();
			oneCaseResponse.setCompanyId(dCase.getCompanyId());
			oneCaseResponse.setCaseId(dCase.getId());
			oneCaseResponse.setImage(dCase.getImage1());
			oneCaseResponse.setPoints(dCase.getPoints());
			oneCaseResponse.setName(dCase.getName());
			oneCaseResponse.setIsFocus(getFocusCase(userId, dCase.getId()) != null ? 1 : 0);
			oneCaseResponse.setIsIdeaPub(ideaService.isIdeaPub(userId, dCase.getId()) ? 1 : 0);
			oneCaseResponse.setIsOver(dCase.getEndTime() < now ? 1 : 0);
			oneCaseResponse.setLeftTime(dCase.getEndTime() - now);
			oneCaseResponse.setTagList(getCaseCaseTypeList(dCase));
			oneCaseResponseList.add(oneCaseResponse);

			// 用来获取企业信息
			companyIdSet.add(String.valueOf(dCase.getCompanyId()));
		}

		// 批量获取企业信息
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(companyIdSet);
		Iterator<OneCaseResponse> it = oneCaseResponseList.iterator();
		while (it.hasNext()) {
			OneCaseResponse oneCaseResponse = it.next();
			DCompany company = companyMap.get(oneCaseResponse.getCompanyId());
			if (company == null) {
				// 用迭代器，用获取不到数据的时候，就把节点删除
				it.remove();
				logger.warn("CompanyService.getAllCaseList, can not get company, companyId: {}", oneCaseResponse.getCompanyId());
				continue;
			}
			oneCaseResponse.setCompanyLogo(company.getLogoImage());
			oneCaseResponse.setCompanyName(company.getName());
		}

		caseListResponse.setList(oneCaseResponseList);
		return caseListResponse;
	}

	/**
	 * 获取最新专案列表
	 * @param userId 用户id
	 * @param lastCaseId 上一页最后一个专案id
	 * @param pageSize 页面大小
	 */
	public CaseListResponse getNewCaseList(long userId, long lastCaseId, int pageSize) {
		String key = KeyFactory.newCaseListKey();
		CaseListResponse caseListResponse = new CaseListResponse();

		// 先从缓存拿数据
		lastCaseId = lastCaseId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastCaseId, -1, pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCase> caseList = caseDao.getNewCaseList(lastCaseId, pageSize);
			if (caseList == null || caseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return caseListResponse;
			}

			int size = caseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCase dCase : caseList) {
				String id = String.valueOf(dCase.getId());
				scoreMembers.put(id, dCase.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取专案
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);

		int now = Time.now();

		// 组织专案列表
		Set<String> companyIdSet = new LinkedHashSet<String>(pageSize);
		List<OneCaseResponse> oneCaseResponseList = new ArrayList<OneCaseResponse>(pageSize);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			if (dCase.getIsShow() != 1 || dCase.getIsPass() != 1) {
				continue;
			}
			OneCaseResponse oneCaseResponse = new OneCaseResponse();
			oneCaseResponse.setCompanyId(dCase.getCompanyId());
			oneCaseResponse.setCaseId(dCase.getId());
			oneCaseResponse.setImage(dCase.getImage1());
			oneCaseResponse.setPoints(dCase.getPoints());
			oneCaseResponse.setName(dCase.getName());
			oneCaseResponse.setIsFocus(getFocusCase(userId, dCase.getId()) != null ? 1 : 0);
			oneCaseResponse.setIsIdeaPub(ideaService.isIdeaPub(userId, dCase.getId()) ? 1 : 0);
			oneCaseResponse.setIsOver(dCase.getEndTime() < now ? 1 : 0);
			oneCaseResponse.setLeftTime(dCase.getEndTime() - now);
			oneCaseResponse.setTagList(getCaseCaseTypeList(dCase));
			oneCaseResponseList.add(oneCaseResponse);

			// 用来获取企业信息
			companyIdSet.add(String.valueOf(dCase.getCompanyId()));
		}

		// 批量获取企业信息
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(companyIdSet);
		Iterator<OneCaseResponse> it = oneCaseResponseList.iterator();
		while (it.hasNext()) {
			OneCaseResponse oneCaseResponse = it.next();
			DCompany company = companyMap.get(oneCaseResponse.getCompanyId());
			if (company == null) {
				// 用迭代器，用获取不到数据的时候，就把节点删除
				it.remove();
				logger.warn("CompanyService.getAllCaseList, can not get company, companyId: {}", oneCaseResponse.getCompanyId());
				continue;
			}
			oneCaseResponse.setCompanyLogo(company.getLogoImage());
			oneCaseResponse.setCompanyName(company.getName());
		}

		caseListResponse.setList(oneCaseResponseList);
		return caseListResponse;
	}

	/**
	 * 批量获取专案
	 * @param caseIdSet 专案id集合
	 */
	public Map<Long, DCase> multiGetCase(Set<String> caseIdSet) {
		// 去掉占位符
		caseIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (caseIdSet.isEmpty()) {
			return new HashMap<Long, DCase>(0);
		}
		// 首先去缓存拿
		int size = caseIdSet.size();
		List<String> caseKeyList = new ArrayList<String>(size);
		for (String caseId : caseIdSet) {
			caseKeyList.add(KeyFactory.caseKey(StringUtil.getLong(caseId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(caseKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DCase> data = new LinkedHashMap<>(size);
		for (String caseId : caseIdSet) {
			data.put(StringUtil.getLong(caseId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DCase dCase = SerialUtil.fromBytes(bytes, DCase.class);
				if (dCase == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(dCase.getId(), dCase);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpCaseIdSet = new HashSet<Long>(size);
		for (Entry<Long, DCase> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpCaseIdSet.add(e.getKey());
			}
		}
		if (tmpCaseIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DCase> caseList = caseDao.getCase(tmpCaseIdSet);
		if (caseList == null || caseList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}

		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(caseList.size());
		Map<String, Map<String, Double>> keyScoreMembers = new HashMap<String, Map<String, Double>>();
		for (DCase dCase : caseList) {
			// 如果数据库查到就回写到缓存
			data.put(dCase.getId(), dCase);

			boolean isOver = dCase.getEndTime() < Time.now();
			String[] tagList = dCase.getType().split(",");

			keysValuesMap.put(KeyFactory.caseKey(dCase.getId()).getBytes(), SerialUtil.toBytes(dCase));

			for (String caseType : tagList) {
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.caseListKey(StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.caseListKey(dCase.getCompanyId(), StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
			}
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.allCaseListKey(), dCase.getId(), String.valueOf(dCase.getId()));
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.newCaseListKey(), dCase.getId(), String.valueOf(dCase.getId()));
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.companyCaseListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.companyCasePageListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));

			// 如果已过期，就添加到过期列表中；否则是进行列表
			if (isOver) {
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.overCaseListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));
				for (String caseType : tagList) {
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.overCaseListKey(dCase.getCompanyId(), StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
				}
			} else {
				redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.goingCaseListKey(dCase.getCompanyId()), dCase.getId(), String.valueOf(dCase.getId()));
				for (String caseType : tagList) {
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.goingCaseListKey(dCase.getCompanyId(), StringUtil.getInt(caseType)), dCase.getId(), String.valueOf(dCase.getId()));
				}
			}
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		redis.SORTSET.zadd(keyScoreMembers);
		return data;
	}

	/**
	 * 用户关注专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public boolean focusCase(final long userId, final long caseId) {
		DFocusCase focusCase = caseDao.focusCase(userId, caseId);
		if (focusCase == null) {
			return false;
		}

		// 企业后台可以看到关注此专案的用户
		redis.STRINGS.setex(KeyFactory.focusCaseKey(userId, caseId).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(focusCase));
		boolean b = redis.SORTSET.zadd(KeyFactory.caseFansListKey(caseId), focusCase.getId(), String.valueOf(userId)) >= 1;
		if (!b) {
			return false;
		}

		// 记录我关注的专案列表
		b = b & redis.SORTSET.zadd(KeyFactory.userFocusCaseListKey(userId), focusCase.getId(), String.valueOf(caseId)) >= 1;
		if (!b) {
			return false;
		}

		guThreadPool.execute(() -> {
			// 关注专案，相当于关注企业
			DCase dCase = getCaseById(caseId);
			if (dCase == null) {
				logger.warn("case not exists, caseId: {}", caseId);
				return;
			}

			// 粉丝数+1
			commonService.updateCountColumn(CountKey.caseFansNum, caseId, 1);

			// 判断是否已经关注过该企业
			if (!userService.isFocusCompany(userId, dCase.getCompanyId())) {
				DCompany company = companyService.getCompanyById(dCase.getCompanyId());
				if (company == null) {
					logger.warn("company not exists, companyId: {}", dCase.getCompanyId());
					return;
				}
				commonService.updateCountColumn(CountKey.companyFansNum, dCase.getCompanyId(), 1);

				DUser user = userService.getUserById(userId);
				if (user != null) {
					userService.focusCompany(user, company.getId());
				}

				// 统计
				caseCountDao.addCaseCountFocus(caseId);
				int fansNum = commonService.getCountColumn(CountKey.caseFansNum, caseId);
				caseCountDao.addCaseCountFocusTotal(dCase, fansNum);

				//发送一条信息给被关注企业
				//设置接消息内容
				UserFocusCompanyMessageBody messageBody = new UserFocusCompanyMessageBody();
				messageBody.setUserId(userId);
				nsqService.msgsend(MessageReceiver.company, company.getId(), MessageSign.userFocusCompany, userId, messageBody);
			}

			//发送一条信息给被关注专案所属的企业
			//设置接消息内容
			FocusCaseMessageBody messageBody = new FocusCaseMessageBody();
			messageBody.setCaseId(caseId);
			nsqService.msgsend(MessageReceiver.company, dCase.getCompanyId(), MessageSign.focusCase, userId, messageBody);
		});

		return true;
	}

	/**
	 * 用户取消关注专案
	 * @param focusCase
	 */
	public boolean unFocusCase(final DFocusCase focusCase) {
		if (!caseDao.unFocusCase(focusCase)) {
			return false;
		}
		final DUser user = userService.getUserById(focusCase.getUserId());
		if (user == null) {
			return false;
		}
		guThreadPool.execute(() -> {
			// 删除缓存
			redis.KEYS.del(KeyFactory.focusCaseKey(focusCase.getUserId(), focusCase.getCaseId()));
			redis.SORTSET.zrem(KeyFactory.caseFansListKey(focusCase.getCaseId()), String.valueOf(focusCase.getUserId()));
			redis.SORTSET.zrem(KeyFactory.userFocusCaseListKey(focusCase.getUserId()), String.valueOf(focusCase.getCaseId()));

			// 锁caseId，解决并发带来统计出错的问题
			DCase dCase = getCaseById(focusCase.getCaseId());
			if (dCase == null) {
				logger.warn("case not exists, caseId: {}", focusCase.getCaseId());
				return;
			}

			// 粉丝数-1
			commonService.updateCountColumn(CountKey.caseFansNum, focusCase.getCaseId(), -1);

			// 统计
			caseCountDao.addCaseCountUnFocus(dCase.getId());
			int fansNum = commonService.getCountColumn(CountKey.caseFansNum, dCase.getId());
			caseCountDao.addCaseCountFocusTotal(dCase, fansNum);
		});
		return true;
	}

	/**
	 * 用户关注的专案列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public UserFocusCaseListResponse userFocusCaseList(long userId, int page, int pageSize) {
		String key = KeyFactory.userFocusCaseListKey(userId);
		UserFocusCaseListResponse userFocusCaseListResponse = new UserFocusCaseListResponse();

		// 先从缓存拿
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusCase> userFocusCaseList = caseDao.userFocusCaseList(userId, start, pageSize);
			if (userFocusCaseList == null || userFocusCaseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return userFocusCaseListResponse;
			}

			int size = userFocusCaseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusCase focusCase : userFocusCaseList) {
				String caseId = String.valueOf(focusCase.getCaseId());
				scoreMembers.put(caseId, focusCase.getId() * 1D);
				listFromCache.add(caseId);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取专案
		Set<String> multiGetComapnyIdSet = new LinkedHashSet<String>(pageSize);
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			UserFocusCaseResponse userFocusCaseResponse = new UserFocusCaseResponse();
			userFocusCaseResponse.setCaseId(dCase.getId());
			userFocusCaseResponse.setCaseTitle(dCase.getName());
			userFocusCaseResponse.setCompanyId(dCase.getCompanyId());
			userFocusCaseResponse.setTag(getCaseCaseTypeList(dCase));
			userFocusCaseResponse.setLeftTime(dCase.getEndTime() - Time.now());
			multiGetComapnyIdSet.add(String.valueOf(dCase.getCompanyId()));
			userFocusCaseListResponse.addList(userFocusCaseResponse);
		}

		// 批量获取企业信息
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(multiGetComapnyIdSet);
		for (UserFocusCaseResponse userFocusCaseResponse : userFocusCaseListResponse.getList()) {
			DCompany company = companyMap.get(userFocusCaseResponse.getCompanyId());
			if (company == null) {
				logger.warn("CaseService.userFocusCaseList, can not get company, companyId: {}", userFocusCaseResponse.getCompanyId());
				continue;
			}
			userFocusCaseResponse.setCompanyLogo(company.getLogoImage());
			userFocusCaseResponse.setCompanyName(company.getName());
		}

		return userFocusCaseListResponse;
	}

	/**
	 * 用户关注的专案列表
	 * @param userId 用户id
	 * @param lastCaseId 上次的专案id
	 * @param pageSize 页面大小
	 */
	public CaseListResponse userFocusCaseListV2(long userId, int lastFocusCaseId, int pageSize) {
		String key = KeyFactory.userFocusCaseListKey(userId);
		CaseListResponse caseListResponse = new CaseListResponse();

		// 先从缓存拿
		lastFocusCaseId = lastFocusCaseId <= 0 ? lastFocusCaseId : lastFocusCaseId + 1;
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, lastFocusCaseId, lastFocusCaseId + pageSize - 1);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusCase> userFocusCaseList = caseDao.userFocusCaseList(userId, lastFocusCaseId, pageSize);
			if (userFocusCaseList == null || userFocusCaseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return caseListResponse;
			}

			int size = userFocusCaseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusCase focusCase : userFocusCaseList) {
				String caseId = String.valueOf(focusCase.getCaseId());
				scoreMembers.put(caseId, focusCase.getId() * 1D);
				listFromCache.add(caseId);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取专案
		int now = Time.now(), i = lastFocusCaseId;
		Set<String> multiGetComapnyIdSet = new LinkedHashSet<String>(pageSize);
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			if (dCase == null) {
				continue;
			}
			OneCaseResponse oneCaseResponse = new OneCaseResponse();
			oneCaseResponse.setCompanyId(dCase.getCompanyId());
			oneCaseResponse.setCaseId(dCase.getId());
			oneCaseResponse.setImage(dCase.getImage1());
			oneCaseResponse.setPoints(dCase.getPoints());
			oneCaseResponse.setName(dCase.getName());
			oneCaseResponse.setIsFocus(getFocusCase(userId, dCase.getId()) != null ? 1 : 0);
			oneCaseResponse.setIsIdeaPub(ideaService.isIdeaPub(userId, dCase.getId()) ? 1 : 0);
			oneCaseResponse.setIsOver(dCase.getEndTime() < now ? 1 : 0);
			oneCaseResponse.setLeftTime(dCase.getEndTime() - now);
			oneCaseResponse.setTagList(getCaseCaseTypeList(dCase));
			oneCaseResponse.setFocusCaseId(i++);
			oneCaseResponse.setIndex(oneCaseResponse.getFocusCaseId());
			multiGetComapnyIdSet.add(String.valueOf(dCase.getCompanyId()));
			caseListResponse.addList(oneCaseResponse);
		}

		// 批量获取企业信息
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(multiGetComapnyIdSet);
		for (OneCaseResponse oneCaseResponse : caseListResponse.getList()) {
			DCompany company = companyMap.get(oneCaseResponse.getCompanyId());
			if (company == null) {
				logger.warn("CaseService.userFocusCaseList, can not get company, companyId: {}", oneCaseResponse.getCompanyId());
				continue;
			}
			oneCaseResponse.setCompanyLogo(company.getLogoImage());
			oneCaseResponse.setCompanyName(company.getName());
		}

		return caseListResponse;
	}

	/**
	 * 记录用户参与过的专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public boolean takePartInCase(final long userId, final long caseId) {
		if (isTakePartInCase(userId, caseId)) {
			logger.warn("user has take part in, userId: {}, caseId: {}", userId, caseId);
			return false;
		}
		DTakePartInCase takePartInCase = caseDao.takePartInCase(userId, caseId);
		if (takePartInCase == null) {
			return false;
		}

		// 异步执行
		guThreadPool.execute(() -> {
			DCase dCase = getCaseById(caseId);
			if (dCase == null) {
				logger.warn("case not exists, caseId: {}", caseId);
				return;
			}

			// 参与数+1
			commonService.updateCountColumn(CountKey.caseTakePartInNum, caseId, 1);

			DUser user = userService.getUserById(userId);
			if (user == null) {
				return;
			}

			//参与专案统计
			caseCountDao.addCaseCountCity(caseId, user.getProvinceId(), user.getCityId());
			caseCountDao.addCaseCountProvince(caseId, user.getProvinceId());
			caseCountDao.addCaseCountSex(caseId, user.getSex());
			caseCountDao.addCaseCountProve(caseId, user);
			caseCountDao.addCaseCountAge(caseId, user);
			caseCountDao.addCaseCountTakePartIn(caseId);
			int takePartInNum = commonService.getCountColumn(CountKey.caseTakePartInNum, caseId);
			caseCountDao.addCaseCountTakePartInTotal(dCase, takePartInNum);
		});

		return redis.SORTSET.zadd(KeyFactory.userHasTakePartInCaseKey(userId), takePartInCase.getId(), String.valueOf(caseId)) >= 1;
	}

	/**
	 * 获取用户参与过的专案的数量
	 * @param userId 用户id
	 */
	public int getUserHasTakePartInCaseNum(long userId) {
		String key = KeyFactory.userHasTakePartInCaseKey(userId);
		int userHasTakePartInCaseNum = (int) redis.SORTSET.zcard(key);
		if (userHasTakePartInCaseNum <= 0) {
			getUserHasTakePartInCaseList(userId, 1, 500);
			userHasTakePartInCaseNum = (int) redis.SORTSET.zcard(key);
		}
		if (redis.SORTSET.zscore(key, "-1") == -1) {
			userHasTakePartInCaseNum--;
		}
		return userHasTakePartInCaseNum;
	}

	/**
	 * 获取用户参与过的专案列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)
	 */
	public UserTakePartInCaseListResponse getUserHasTakePartInCaseList(long userId, int page, int pageSize) {
		String key = KeyFactory.userHasTakePartInCaseKey(userId);
		UserTakePartInCaseListResponse userTakePartInCaseList = new UserTakePartInCaseListResponse();

		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DTakePartInCase> takePartInCaseList = caseDao.getUserHasTakePartInCaseList(userId, page, pageSize);
			if (takePartInCaseList == null || takePartInCaseList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return userTakePartInCaseList;
			}

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(takePartInCaseList.size());
			for (DTakePartInCase takePartInCase : takePartInCaseList) {
				scoreMembers.put(String.valueOf(takePartInCase.getCaseId()), takePartInCase.getId() * 1D);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取专案
		int now = Time.now();
		Set<String> companyIdSet = new LinkedHashSet<String>(listFromCache.size());
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			if (dCase == null) {
				continue;
			}
			UserTakePartInCase userTakePartInCase = new UserTakePartInCase();
			userTakePartInCase.setCaseId(dCase.getId());
			userTakePartInCase.setName(dCase.getName());
			userTakePartInCase.setIsOver(dCase.getEndTime() < now ? 1 : 0);
			userTakePartInCaseList.addList(userTakePartInCase);
			companyIdSet.add(String.valueOf(dCase.getCompanyId()));
		}

		List<UserTakePartInCase> list = userTakePartInCaseList.getList();
		int i = -1;

		// 批量获取企业，获取企业头像
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(companyIdSet);
		for (String companyId : companyIdSet) {
			DCompany company = companyMap.get(StringUtil.getLong(companyId));
			if (company == null) {
				continue;
			}

			UserTakePartInCase userTakePartInCase = list.get(++i);
			userTakePartInCase.setCompanyImage(company.getLogoImage());
		}

		return userTakePartInCaseList;
	}

	/**
	 * 获取专案的粉丝列表
	 * @param caseId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)
	 */
	public Map<Long, DFocusCase> getCaseFansList(long caseId, int page, int pageSize) {
		String key = KeyFactory.caseFansListKey(caseId);

		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusCase> caseFansList = caseDao.getCaseFansList(caseId, page, pageSize);
			if (caseFansList == null || caseFansList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return new HashMap<Long, DFocusCase>(0);
			}

			int size = caseFansList.size();
			listFromCache = new LinkedHashSet<String>(size);
			Map<Long, DFocusCase> data = new LinkedHashMap<>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusCase focusCase : caseFansList) {
				String userId = String.valueOf(focusCase.getUserId());
				scoreMembers.put(userId, focusCase.getId() * 1D);
				listFromCache.add(userId);
				data.put(focusCase.getUserId(), focusCase);
			}
			redis.SORTSET.zadd(key, scoreMembers);
			return data;
		}

		// 批量获取关注专案数据
		return multiGetFocusCase(caseId, listFromCache);
	}

	/**
	 * 获取关注专案数据
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public DFocusCase getFocusCase(long userId, long caseId) {
		String key = KeyFactory.focusCaseKey(userId, caseId);
		byte[] b = redis.STRINGS.get(key.getBytes());
		if (b == null || b.length <= 0) {
			DFocusCase focusCase = caseDao.getFocusCase(caseId, userId);
			if (focusCase == null) {
				return null;
			}
			redis.STRINGS.setex(KeyFactory.focusCaseKey(userId, caseId).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(focusCase));
			redis.SORTSET.zadd(KeyFactory.caseFansListKey(caseId), focusCase.getId(), String.valueOf(userId));
			redis.SORTSET.zadd(KeyFactory.userFocusCaseListKey(userId), focusCase.getId(), String.valueOf(caseId));
			return focusCase;
		}
		return SerialUtil.fromBytes(b, DFocusCase.class);
	}

	/**
	 * 批量获取关注专案数据
	 * @param caseId 专案id
	 * @param userIdSet 用户id集合
	 */
	public Map<Long, DFocusCase> multiGetFocusCase(long caseId, Set<String> userIdSet) {
		// 去掉占位符
		userIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (userIdSet.isEmpty()) {
			return new HashMap<>(0);
		}
		// 首先去缓存拿
		int size = userIdSet.size();
		List<String> keyList = new ArrayList<String>(size);
		for (String userId : userIdSet) {
			keyList.add(KeyFactory.focusCaseKey(StringUtil.getLong(userId), caseId));
		}
		List<byte[]> byteList = redis.STRINGS.mget(keyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DFocusCase> data = new LinkedHashMap<>(size);
		for (String userId : userIdSet) {
			data.put(StringUtil.getLong(userId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DFocusCase focusCase = SerialUtil.fromBytes(bytes, DFocusCase.class);
				if (focusCase == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(focusCase.getUserId(), focusCase);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpUserIdSet = new HashSet<Long>(size);
		for (Entry<Long, DFocusCase> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpUserIdSet.add(e.getKey());
			}
		}
		if (tmpUserIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DFocusCase> focusCaseList = caseDao.getFocusCase(caseId, tmpUserIdSet);
		if (focusCaseList == null || focusCaseList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(focusCaseList.size());
		for (DFocusCase focusCase : focusCaseList) {
			// 如果数据库查到就回写到缓存
			data.put(focusCase.getUserId(), focusCase);
			keysValuesMap.put(KeyFactory.focusCaseKey(focusCase.getUserId(), caseId).getBytes(), SerialUtil.toBytes(focusCase));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 用户是否已经参与过这个专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public boolean isTakePartInCase(long userId, long caseId) {
		// 先查缓存
		// 如果缓存查不到，取mysql 500条，判断一下这500条之中有没有
		// 如果没有，先回写这500条
		// 然后再从mysql读取，如果读得到就回写，读不到就真的没有了
		String key = KeyFactory.userHasTakePartInCaseKey(userId);
		double score = redis.SORTSET.zscore(key, String.valueOf(caseId));
		if (score <= 0) {
			// 一次性拿500条
			getUserHasTakePartInCaseList(userId, 1, 500);
			score = redis.SORTSET.zscore(key, String.valueOf(caseId));
			if (score <= 0) {
				// 再查一次都没有，那就是真的没有了
				return false;
			}

			// 如果没有，再去数据库查一次，然后回写
			DTakePartInCase takePartInCase = caseDao.isTakePartInCase(userId, caseId);
			if (takePartInCase == null) {
				return false;
			}

			// 如果有，再回写一次
			redis.SORTSET.zadd(key, takePartInCase.getId(), String.valueOf(caseId));
		}
		return true;
	}

	/**
	 * 返回用户对专案创意点赞次数统计
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public DCasePraiseCount getCasePraiseCount(long userId, long caseId) {
		byte[] b = redis.STRINGS.get(KeyFactory.casePraiseCountKey(userId, caseId).getBytes());
		if (b == null || b.length <= 0) {
			DCasePraiseCount casePraiseCount = caseDao.getCasePraiseCount(userId, caseId);
			if (casePraiseCount == null) {
				return null;
			}
			redis.STRINGS.set(KeyFactory.casePraiseCountKey(userId, caseId), SerialUtil.toBytes(casePraiseCount));
		}
		return SerialUtil.fromBytes(b, DCasePraiseCount.class);
	}

	/**
	 * 添加用户对专案创意点赞次数统计
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public DCasePraiseCount addCasePraiseCount(long userId, long caseId) {
		DCasePraiseCount casePraiseCount = caseDao.addCasePraiseCount(userId, caseId);
		if (casePraiseCount == null) {
			return null;
		}
		redis.STRINGS.set(KeyFactory.casePraiseCountKey(userId, caseId), SerialUtil.toBytes(casePraiseCount));
		if (getFocusCase(userId, caseId) == null) {
			focusCase(userId, caseId);
		}
		return casePraiseCount;
	}

	/**
	 * 用户对专案创意的总点赞次数+1
	 * @param casePraiseCount 用户对专案创意的总赞数实体
	 */
	public boolean updateCasePraiseCountUpOne(DCasePraiseCount casePraiseCount) {
		boolean updateSuccess = caseDao.updateCasePraiseCountUpOne(casePraiseCount.getUserId(), casePraiseCount.getCaseId());
		if (updateSuccess) {
			casePraiseCount.setCount(casePraiseCount.getCount() + 1);
			redis.STRINGS.set(KeyFactory.casePraiseCountKey(casePraiseCount.getUserId(), casePraiseCount.getCaseId()), SerialUtil.toBytes(casePraiseCount));
		}
		return updateSuccess;
	}

	/**
	 * 用户对专案创意的总点赞次数-1
	 * @param casePraiseCount 用户对专案创意的总赞数实体
	 */
	public boolean updateCasePraiseCountDownOne(DCasePraiseCount casePraiseCount) {
		boolean updateSuccess = caseDao.updateCasePraiseCountDownOne(casePraiseCount.getUserId(), casePraiseCount.getCaseId());
		if (updateSuccess) {
			casePraiseCount.setCount(casePraiseCount.getCount() - 1);
			redis.STRINGS.set(KeyFactory.casePraiseCountKey(casePraiseCount.getUserId(), casePraiseCount.getCaseId()), SerialUtil.toBytes(casePraiseCount));
		}
		return updateSuccess;
	}

	/**
	 * 专案搜索
	 * @param search 搜索字段
	 * @param lastCaseId 专案id游标
	 * @param pageSize 数目
	 * @return
	 */
	public CaseSearchListResponse searchByName(String search, long lastCaseId, int pageSize) {
		CaseSearchListResponse caseSearchListResponse = new CaseSearchListResponse();

		search = search.trim();
		String key = KeyFactory.caseSearchKey(search);
		long total = redis.SORTSET.zcard(key);
		// 没有缓存,添加缓存
		if (total == 0) {
			// 加占位防止穿透
			redis.SORTSET.zadd(key, -1, "-1");

			// 在缓存取专案类型
			List<Map<String, Object>> caseTypeList = getCaseTypeConfig();
			// 匹配专案类型
			Pattern p = Pattern.compile(".*" + search + ".*");
			Set<String> caseTypeKey = new HashSet<>();
			for (Map<String, Object> map : caseTypeList) {
				if (p.matcher(StringUtil.getString(map.get("name"))).matches()) {
					caseTypeKey.add(KeyFactory.caseListKey(StringUtil.getInt(map.get("id"))));
				}
			}
			// 判断匹配专案类型是否为空
			if (!caseTypeKey.isEmpty()) {
				String[] sets = caseTypeKey.toArray(new String[0]);
				ZParams zParams = new ZParams();
				zParams.aggregate(Aggregate.MIN);

				// 把这些集合做一个并集
				redis.SORTSET.zunionstore(key, zParams, sets);
			}

			// 5分钟过期，保证数据实时
			redis.KEYS.expire(key, 300);
			// 搜索MySql专案id
			List<Long> caseIdList = caseDao.searchCaseIdByName(search);
			Map<String, Double> scoreMembers = caseIdList.stream().collect(Collectors.toMap(e -> {
				return StringUtil.getString(e);
			} , e -> {
				return StringUtil.getDouble(e);
			}));
			// 添加进缓存
			if (!scoreMembers.isEmpty()) {
				redis.SORTSET.zadd(key, scoreMembers);
			}
		}
		caseSearchListResponse.setTotal(((int) redis.SORTSET.zcard(key)) - 1);

		lastCaseId = lastCaseId - 1;
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, lastCaseId, -1, pageSize);
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);
		Set<String> companyIdSet = new LinkedHashSet<String>(pageSize);
		List<CaseSearchResponse> caseSearchResponseList = new ArrayList<>();
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			CaseSearchResponse caseSearchResponse = new CaseSearchResponse();
			caseSearchResponse.setCaseId(dCase.getId());
			caseSearchResponse.setCaseName(dCase.getName());
			caseSearchResponse.setCompanyId(dCase.getCompanyId());

			companyIdSet.add(String.valueOf(dCase.getCompanyId()));
			caseSearchResponseList.add(caseSearchResponse);
		}

		// 批量获取企业信息
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(companyIdSet);
		Iterator<CaseSearchResponse> it = caseSearchResponseList.iterator();
		while (it.hasNext()) {
			CaseSearchResponse caseSearchResponse = it.next();
			DCompany company = companyMap.get(caseSearchResponse.getCompanyId());
			if (company == null) {
				// 用迭代器，用获取不到数据的时候，就把节点删除
				it.remove();
				logger.warn("CompanyService.getCaseList, can not get company, companyId: {}", caseSearchResponse.getCompanyId());
				continue;
			}
			caseSearchResponse.setCompanyLogo(company.getLogoImage());
		}
		caseSearchListResponse.setList(caseSearchResponseList);
		return caseSearchListResponse;
	}

	/**
	 * 企业后台专案搜索
	 * @param company 企业
	 * @param search 搜索字段
	 * @param lastCaseId 专案id游标
	 * @param pageSize 数目
	 * @return
	 */
	public CompanyCaseListResponse companySearchByName(DCompany company, String search, int page, int pageSize) {
		CompanyCaseListResponse companyCaseListResponse = new CompanyCaseListResponse();

		search = search.trim();
		String key = KeyFactory.companyCaseSearchKey(company.getId(), search);
		long total = redis.SORTSET.zcard(key);
		// 没有缓存,添加缓存
		if (total == 0) {
			// 企业后台专案搜索的搜索关键字缓存
			redis.SETS.sadd(KeyFactory.companyCaseSearchKeySetKey(company.getId()), search);

			// 加占位防止穿透
			redis.SORTSET.zadd(key, -1, "-1");

			// 在缓存取专案类型
			List<Map<String, Object>> caseTypeList = getCaseTypeConfig();
			// 匹配专案类型
			Pattern p = Pattern.compile(".*" + search + ".*");
			Set<String> caseTypeKey = new HashSet<>();
			for (Map<String, Object> map : caseTypeList) {
				if (p.matcher(StringUtil.getString(map.get("name"))).matches()) {
					caseTypeKey.add(KeyFactory.caseListKey(company.getId(), StringUtil.getInt(map.get("id"))));
				}
			}
			// 判断匹配专案类型是否为空
			if (!caseTypeKey.isEmpty()) {
				String[] sets = caseTypeKey.toArray(new String[0]);
				ZParams zParams = new ZParams();
				zParams.aggregate(Aggregate.MIN);

				// 把这些集合做一个并集
				redis.SORTSET.zunionstore(key, zParams, sets);
			}

			// 搜索MySql专案id
			List<Long> caseIdList = caseDao.searchCaseIdByName(company.getId(), search);
			Map<String, Double> scoreMembers = caseIdList.stream().collect(Collectors.toMap(e -> {
				return StringUtil.getString(e);
			} , e -> {
				return StringUtil.getDouble(e);
			}));
			// 添加进缓存
			if (!scoreMembers.isEmpty()) {
				redis.SORTSET.zadd(key, scoreMembers);
			}
		}
		companyCaseListResponse.setTotal(((int) redis.SORTSET.zcard(key)) - 1);

		// 先从缓存拿
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		Map<Long, DCase> caseMap = multiGetCase(listFromCache);
		Map<Long, Integer> fansNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.caseFansNum);
		Map<Long, Integer> takePartInNumMap = commonService.multiGetCountColumn(listFromCache, CountKey.caseTakePartInNum);
		for (Entry<Long, DCase> e : caseMap.entrySet()) {
			DCase dCase = e.getValue();
			companyCaseListResponse.addList(dCase.toCompanyCase(Time.now(), fansNumMap.get(dCase.getId()), takePartInNumMap.get(dCase.getId())));
		}
		return companyCaseListResponse;
	}

	/**
	 * 获取一个专案的标签
	 * @author ruan 
	 * @param dCase 专案对象
	 */
	public List<CaseTypeResponse> getCaseCaseTypeList(DCase dCase) {
		String[] typeArr = dCase.getType().split(",");
		List<CaseTypeResponse> list = new ArrayList<CaseTypeResponse>(typeArr.length);
		Map<Long, String> caseTypeConfigMap = getCaseTypeConfigMap();
		for (String type : typeArr) {
			long id = StringUtil.getLong(type);
			String name = StringUtil.getString(caseTypeConfigMap.get(id));
			if (id <= 0 || name.isEmpty()) {
				continue;
			}

			CaseTypeResponse caseTypeResponse = new CaseTypeResponse();
			caseTypeResponse.setId(id);
			caseTypeResponse.setName(name);
			list.add(caseTypeResponse);
		}
		return list;
	}
}
