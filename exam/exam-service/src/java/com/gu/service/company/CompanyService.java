package com.gu.service.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.reflect.TypeToken;
import com.gu.core.enums.CompanyPointsAddEvent;
import com.gu.core.enums.CompanyPointsSubEvent;
import com.gu.core.enums.LengthLimit;
import com.gu.core.protocol.CompanyFansListResponse;
import com.gu.core.protocol.CompanyFansResponse;
import com.gu.core.protocol.CompanySearchListResponse;
import com.gu.core.protocol.base.CompanyBaseResponse;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.JSONObject;
import com.gu.core.util.Secret;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.CompanyDao;
import com.gu.dao.model.DCollectIdea;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DCompanyCaseTag;
import com.gu.dao.model.DCompanyDefineCaseTag;
import com.gu.dao.model.DCompanyExpense;
import com.gu.dao.model.DCompanyFocus;
import com.gu.dao.model.DFocusCompany;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DUser;
import com.gu.service.common.CommonService;
import com.gu.service.idea.IdeaService;
import com.gu.service.user.UserService;

/**
 * 企业service
 * @author ruan
 */
@Component
public class CompanyService {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private Redis redis;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UserService userService;
	@Autowired
	private IdeaService ideaService;
	@Autowired
	private CommonService commonService;
	@Value("${system.key}")
	private String key;
	@Autowired
	private GuThreadPool guThreadPool;

	/**
	 * 企业领域配置缓存
	 */
	private LoadingCache<Integer, Map<Long, String>> companyTypeConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, Map<Long, String>>() {
		public Map<Long, String> load(Integer key) throws Exception {
			logger.warn("load company type config");
			return companyDao.getCompanyTypeConfig();
		}
	});

	/**
	 * 初始化配置数据
	 */
	@PostConstruct
	public void init() {
		try {
			companyTypeConfig.invalidateAll();
			getCompanyTypeConfig();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 获取企业领域配置
	 */
	public Map<Long, String> getCompanyTypeConfig() {
		try {
			return companyTypeConfig.get(1);
		} catch (ExecutionException e) {
			logger.error("", e);
		}
		return new HashMap<Long, String>(0);
	}

	/**
	 * SortSet列表加占位
	 * @param key
	 */
	public void addSortSetAnchor(String key) {
		redis.SORTSET.zadd(key, -1, "-1");
	}

	/**
	 * 企业注册方法
	 * @param name 企业名称
	 * @param password 密码
	 * @param description 企业简介(描述)
	 * @param type 企业领域(类型)
	 * @param logo_image 企业logo
	 * @param admin_name 管理员姓名
	 * @param admin_phone 管理员电话
	 * @param admin_email 管理员邮箱
	 * @return
	 */
	public DCompany addCompany(String name, String password, String description, long typeId, String logoImage, String adminName, String adminPhone, String adminEmail) {
		DCompany company = companyDao.addCompany(name, genCompanyPassword(adminEmail, password), description, typeId, logoImage, adminName, adminPhone, adminEmail);
		if (company == null) {
			return null;
		}
		guThreadPool.execute(() -> {
			// 单独统计G点数
			commonService.setCountColumnRedis(CountKey.companyPoints, company.getId(), 0);
			// 单独统计所有专案数
			commonService.setCountColumnRedis(CountKey.companyAllCaseNum, company.getId(), 0);
			// 单独统计进行专案数
			commonService.setCountColumnRedis(CountKey.companyGoingCaseNum, company.getId(), 0);
			// 单独统计结束专案数
			commonService.setCountColumnRedis(CountKey.companyOverCaseNum, company.getId(), 0);
			// 单独统计粉丝数
			commonService.setCountColumnRedis(CountKey.companyFansNum, company.getId(), 0);
			// 单独统计专案收藏数
			commonService.setCountColumnRedis(CountKey.companyCollectNum, company.getId(), 0);
			// 单独统计关注用户数
			commonService.setCountColumnRedis(CountKey.companyFocusUserNum, company.getId(), 0);
		});

		redis.STRINGS.setex(KeyFactory.companyKey(company.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
		redis.STRINGS.setex(KeyFactory.companyKey(company.getAdminEmail()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
		return company;
	}

	/**
	 * 企业名字唯一性检查
	 * @param name 企业名字
	 * @return false=已被使用
	 */
	public boolean companyNameOnly(String name) {
		return companyDao.companyNameOnly(name);
	}

	/**
	 * 保存企业领域配置
	 * @param type 领域
	 */
	public void saveCaseExpireConfig(String type) {
		companyDao.saveCaseExpireConfig(type);
	}

	/**
	 * 判断企业领域配置是否重复
	 * @param type 领域
	 */
	public boolean caseExpireConfigExists(String type) {
		return companyDao.caseExpireConfigExists(type);
	}

	/**
	 * 删除企业领域配置
	 * @author ruan 
	 * @param id
	 */
	public void deleteCaseExpireConfig(long id) {
		companyDao.deleteCaseExpireConfig(id);
	}

	/**
	 * 企业全名唯一性检查
	 * @param name 企业全名
	 * @return false=已被使用
	 */
	public boolean companyFullNameOnly(String name) {
		return companyDao.companyFullNameOnly(name);
	}

	/**
	* 企业邮箱唯一性检查
	* @param name 企业名字
	* @return false=已被使用
	*/
	public boolean companyEmailOnly(String email) {
		return companyDao.companyEmailOnly(email);
	}

	/**
	 * 生成企业登录密码
	 * @param email 管理员邮箱
	 * @param password 登录密码
	 */
	public String genCompanyPassword(String email, String password) {
		return Secret.md5(Secret.sha(password) + key + Secret.base64EncodeToString(email));
	}

	/**
	 * 根据企业id获取企业
	 * @param companyId 企业
	 */
	public DCompany getCompanyById(long companyId) {
		String key = KeyFactory.companyKey(companyId);
		byte[] bytes = redis.STRINGS.get(key.getBytes());
		DCompany company = null;
		if (bytes == null || bytes.length <= 0) {
			company = companyDao.getCompanyById(companyId);
			if (company == null) {
				return null;
			}
			redis.STRINGS.setex(KeyFactory.companyKey(company.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
			redis.STRINGS.setex(KeyFactory.companyKey(company.getAdminEmail()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
		} else {
			company = SerialUtil.fromBytes(bytes, DCompany.class);
			if (company == null) {
				return null;
			}
		}
		company.setPoints(commonService.getCountColumn(CountKey.companyPoints, company.getId()));
		company.setAllCaseNum(commonService.getCountColumn(CountKey.companyAllCaseNum, company.getId()));
		company.setOverCaseNum(commonService.getCountColumn(CountKey.companyOverCaseNum, company.getId()));
		company.setGoingCaseNum(commonService.getCountColumn(CountKey.companyGoingCaseNum, company.getId()));
		company.setFocusUserNum(commonService.getCountColumn(CountKey.companyFocusUserNum, company.getId()));
		company.setFansNum(commonService.getCountColumn(CountKey.companyFansNum, company.getId()));
		company.setCollectNum(commonService.getCountColumn(CountKey.companyCollectNum, company.getId()));
		return company;
	}

	/**
	 * 根据企业邮箱获取企业
	 * @param email 企业邮箱
	 */
	public DCompany getCompanyByEmail(String email) {
		String key = KeyFactory.companyKey(email);
		byte[] bytes = redis.STRINGS.get(key.getBytes());
		if (bytes == null || bytes.length <= 0) {
			DCompany company = companyDao.getCompanyByEmail(email);
			if (company == null) {
				return null;
			}
			redis.STRINGS.setex(KeyFactory.companyKey(company.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
			redis.STRINGS.setex(KeyFactory.companyKey(company.getAdminEmail()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
			return company;
		}
		return SerialUtil.fromBytes(bytes, DCompany.class);
	}

	/**
	 * 批量获取企业
	 * @param companyIdSet 企业id集合
	 */
	public Map<Long, DCompany> multiGetCompany(Set<String> companyIdSet) {
		// 去掉占位符
		companyIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (companyIdSet.isEmpty()) {
			return new HashMap<>(0);
		}
		// 首先去缓存拿
		int size = companyIdSet.size();
		List<String> companyKeyList = new ArrayList<String>(size);
		for (String companyId : companyIdSet) {
			companyKeyList.add(KeyFactory.companyKey(StringUtil.getLong(companyId)));
		}
		List<byte[]> byteList = redis.STRINGS.mget(companyKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DCompany> data = new LinkedHashMap<>(size);
		for (String caseId : companyIdSet) {
			data.put(StringUtil.getLong(caseId), null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DCompany company = SerialUtil.fromBytes(bytes, DCompany.class);
				if (company == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(company.getId(), company);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpCompanyIdSet = new HashSet<Long>(size);
		for (Entry<Long, DCompany> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpCompanyIdSet.add(e.getKey());
			}
		}
		if (tmpCompanyIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DCompany> companyList = companyDao.getCompany(tmpCompanyIdSet);
		if (companyList == null || companyList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(companyList.size());
		for (DCompany company : companyList) {
			// 如果数据库查到就回写到缓存
			data.put(company.getId(), company);
			keysValuesMap.put(KeyFactory.companyKey(company.getId()).getBytes(), SerialUtil.toBytes(company));
			keysValuesMap.put(KeyFactory.companyKey(company.getAdminEmail()).getBytes(), SerialUtil.toBytes(company));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 修改企业信息
	 * @param company 企业对象
	 */
	public boolean updateCompany(DCompany company) {
		if (!companyDao.updateCompany(company)) {
			logger.warn("update company faild, companyId: {}", company.getId());
			return false;
		}
		redis.STRINGS.setex(KeyFactory.companyKey(company.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
		redis.STRINGS.setex(KeyFactory.companyKey(company.getAdminEmail()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(company));
		return true;
	}

	/**
	 * 给企业加G点
	 * @param companyId 企业id
	 * @param points 加了的G点
	 * @param event 加钱事件
	 */
	public boolean addPoints(long companyId, int points, CompanyPointsAddEvent event) {
		// 写入数据库(写入缓存那里都要连带锁在一起，这样安全一点)
		points = Math.abs(points);
		DCompany company = getCompanyById(companyId);
		if (company == null) {
			logger.warn("addPoints, addPoints, company not exists, companyId: {}", companyId);
			return false;
		}
		boolean b = commonService.updateCountColumn(CountKey.companyPoints, companyId, points);
		if (!b) {
			logger.warn("update company points faild! companyId: {}, points: {}", company.getId(), points);
			return false;
		}

		// 记录日志
		companyDao.companyPointAddLog(companyId, points, event);
		return true;
	}

	/**
	 * 扣企业G点
	 * @param companyId 企业id
	 * @param points 要扣的G点
	 * @param event 扣钱事件
	 */
	public boolean subPoints(long companyId, int points, CompanyPointsSubEvent event) {
		// 判断是否够钱(写入缓存那里都要连带锁在一起，这样安全一点)
		points = Math.abs(points);
		DCompany company = getCompanyById(companyId);
		if (company == null) {
			logger.warn("subPoints, addPoints, company not exists, companyId: {}", companyId);
			return false;
		}
		int nowPoints = commonService.getCountColumn(CountKey.companyPoints, companyId);
		if (nowPoints < points) {
			logger.warn("company points not enough! userId: {}, points: {}", company.getId(), points);
			return false;
		}

		// 写入数据库
		boolean b = commonService.updateCountColumn(CountKey.companyPoints, companyId, -points);
		if (!b) {
			logger.warn("update company points faild! companyId: {}, points: {}", company.getId(), points);
			return false;
		}

		// 记录日志
		companyDao.companyPointSubLog(companyId, points, event);
		return true;
	}

	/**
	 * 企业搜索
	 * @param search 搜索字段
	 * @param lastCompanyId 企业id游标
	 * @param pageSize 数目
	 * @return
	 */
	public CompanySearchListResponse searchByName(String search, long lastCompanyId, int pageSize) {
		CompanySearchListResponse companySearchListResponse = new CompanySearchListResponse();
		List<DCompany> companieList = companyDao.searchByName(search, lastCompanyId, pageSize);
		List<CompanyBaseResponse> list = new ArrayList<CompanyBaseResponse>();
		for (DCompany company : companieList) {
			CompanyBaseResponse companySearchResponse = new CompanyBaseResponse();
			companySearchResponse.setCompanyId(company.getId());
			companySearchResponse.setName(company.getName());
			companySearchResponse.setLogo(company.getLogoImage());
			list.add(companySearchResponse);
		}
		companySearchListResponse.setList(list);
		return companySearchListResponse;
	}

	public boolean recordCompanyLogintime(DCompany company) {
		if (company.getCreateTime() > (Time.now() - (35 * 86400))) {
			return companyDao.addLogintime(company);
		}
		return false;
	}

	/**
	 * 日活跃企业统计
	 * @param company 企业对象
	 */
	public void recordDailyActiveCompany(DCompany company) {
		guThreadPool.execute(() -> {
			long t = System.nanoTime();

			int now = Time.now();
			String date = Time.date("yyyyMMdd", now);
			int hour = StringUtil.getInt(Time.date("H", now)) + 1;
			String dailyActiveCompanyKey = KeyFactory.dailyActiveCompanyListKey(date);
			String dailyActiveCompanyHourKey = KeyFactory.dailyActiveCompanyListKey(date, hour);

			// 这个小时没记录过
			if (redis.SORTSET.zscore(dailyActiveCompanyHourKey, String.valueOf(company.getId())) <= 0) {
				companyDao.recordDailyActiveCompany(date, hour);

				redis.SORTSET.zadd(dailyActiveCompanyHourKey, company.getId(), String.valueOf(company.getId()));
				redis.KEYS.expire(dailyActiveCompanyHourKey, 7200);
			}

			// 今天没记录过
			if (redis.SORTSET.zscore(dailyActiveCompanyKey, String.valueOf(company.getId())) <= 0) {
				companyDao.recordDailyActiveCompanyTotal(date);

				redis.SORTSET.zadd(dailyActiveCompanyKey, company.getId(), String.valueOf(company.getId()));
				int dayEnd = Time.dayEnd(date, "yyyyMMdd");
				redis.KEYS.expire(dailyActiveCompanyKey, dayEnd - now + 10);
			}

			// 过去7天活跃
			LinkedHashMap<String, String> keyMemebrMap = new LinkedHashMap<String, String>(21);
			for (int i = now; i > now - 86400 * 7; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 7);
				keyMemebrMap.put(KeyFactory.companyRecent7ActiveKey(startDate, endDate), String.valueOf(company.getId()));
			}

			// 过去14天活跃
			for (int i = now; i > now - 86400 * 14; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 14);
				keyMemebrMap.put(KeyFactory.companyRecent14ActiveKey(startDate, endDate), String.valueOf(company.getId()));
			}

			// 批量获取所有
			Map<String, Map<String, Double>> keyScoreMembers = new HashMap<String, Map<String, Double>>(21);
			Map<String, Double> scoreMap = redis.SORTSET.zscore(keyMemebrMap);
			for (int i = now; i > now - 86400 * 7; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 7);
				String recent7ActiveKey = KeyFactory.companyRecent7ActiveKey(startDate, endDate);
				double score = StringUtil.getDouble(scoreMap.get(recent7ActiveKey));
				if (score <= 0) {
					companyDao.recordRecent7Active(startDate, endDate);
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, recent7ActiveKey, company.getId(), String.valueOf(company.getId()));
					redis.KEYS.expire(recent7ActiveKey, 86400 * 8);
				}
			}
			for (int i = now; i > now - 86400 * 14; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 14);
				String recent14ActiveKey = KeyFactory.companyRecent14ActiveKey(startDate, endDate);
				double score = StringUtil.getDouble(scoreMap.get(recent14ActiveKey));
				if (score <= 0) {
					companyDao.recordRecent14Active(startDate, endDate);
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, recent14ActiveKey, company.getId(), String.valueOf(company.getId()));
					redis.KEYS.expire(recent14ActiveKey, 86400 * 15);
				}
			}
			if (!keyScoreMembers.isEmpty()) {
				redis.SORTSET.zadd(keyScoreMembers);
			}

			logger.warn("recordDailyActiveCompany after login exec time: {}", Time.showTime(System.nanoTime() - t));
		});
	}

	/**
	 * 记录新增企业日志
	 */
	public void newCompanyLog() {
		companyDao.newCompanyLog();
	}

	/**
	 * 新增一个企业自定义标签
	 * @author ruan 
	 * @param companyId 企业id
	 * @param name 标签名
	 */
	public DCompanyDefineCaseTag addCompanyDefineCaseTag(long companyId, String name) {
		Map<String, String> companyTagMap = getCompanyDefineCaseTag(companyId);
		for (Entry<String, String> e : companyTagMap.entrySet()) {
			if (!e.getValue().equals(name)) {
				continue;
			}

			DCompanyDefineCaseTag companyDefineCaseTag = new DCompanyDefineCaseTag();
			companyDefineCaseTag.setId(StringUtil.getLong(e.getKey()));
			companyDefineCaseTag.setCompanyId(companyId);
			companyDefineCaseTag.setName(name);
			updateCompanyDefineTagCaseNum(companyDefineCaseTag);
			return companyDefineCaseTag;
		}
		DCompanyDefineCaseTag companyDefineCaseTag = companyDao.addCompanyDefineCaseTag(companyId, name);
		companyTagMap.put(String.valueOf(companyDefineCaseTag.getId()), companyDefineCaseTag.getName());
		redis.STRINGS.set(KeyFactory.companyDefineCaseTagListKey(companyId), JSONObject.encode(companyTagMap));
		return companyDefineCaseTag;
	}

	/**
	 * 批量获取企业自定义标签
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public Map<String, String> getCompanyDefineCaseTag(long companyId) {
		// 首先去缓存拿
		String key = KeyFactory.companyDefineCaseTagListKey(companyId);
		Map<String, String> listFromCache = JSONObject.decode(redis.STRINGS.get(key), new TypeToken<HashMap<String, String>>() {
		}.getType());
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCompanyDefineCaseTag> list = companyDao.getCompanyDefineCaseTag(companyId);
			if (list == null || list.isEmpty()) {
				// 数据库也没有，返回
				return new HashMap<>(0);
			}

			// 数据库有，批量回写缓存
			Map<String, String> hmsetMap = new HashMap<String, String>(list.size());
			for (DCompanyDefineCaseTag companyDefineCaseTag : list) {
				hmsetMap.put(String.valueOf(companyDefineCaseTag.getId()), companyDefineCaseTag.getName());
			}
			redis.STRINGS.set(key, JSONObject.encode(hmsetMap));
		}

		// 返回结果
		return listFromCache;
	}

	/**
	 * 更新用户自定义标签的专案数+1
	 * @author ruan 
	 * @param companyCaseTag 用户自定义标签对象
	 */
	public boolean updateCompanyDefineTagCaseNum(DCompanyDefineCaseTag companyCaseTag) {
		if (!companyDao.updateCompanyDefineTagCaseNum(companyCaseTag.getId())) {
			return false;
		}
		return true;
	}

	/**
	 * 保存企业的专案标签
	 * @author ruan 
	 * @param companyId 企业id
	 * @param tag 专案类型数组
	 */
	public void saveCompanyCaseTag(long companyId, long[] tag) {
		Map<String, Map<String, Double>> keyScoreMembers = new HashMap<String, Map<String, Double>>();
		Set<String> companyCaseTagSet = getCompanyCaseTag(companyId);
		for (long typeId : tag) {
			if (companyCaseTagSet.contains(String.valueOf(typeId))) {
				continue;
			}
			DCompanyCaseTag companyCaseTag = companyDao.saveCompanyCaseTag(companyId, typeId);
			redis.SORTSET.genKeyScoreMembers(keyScoreMembers, KeyFactory.companyCaseTagListKey(companyId), companyCaseTag.getId(), String.valueOf(companyCaseTag.getTypeId()));
		}
		redis.SORTSET.zadd(keyScoreMembers);
	}

	/**
	 * 获取企业所有专案的标签
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public Set<String> getCompanyCaseTag(long companyId) {
		String key = KeyFactory.companyCaseTagListKey(companyId);

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrangeByScore(key, Long.MAX_VALUE, 0, Integer.MAX_VALUE);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCompanyCaseTag> list = companyDao.getDCompanyCaseTag(companyId);
			if (list == null || list.isEmpty()) {
				// 穿透db时加占位
				redis.SORTSET.zadd(key, -1, "-1");
				// 数据库也没有，返回
				return new HashSet<>(0);
			}

			int size = list.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCompanyCaseTag companyCaseTag : list) {
				String id = String.valueOf(companyCaseTag.getTypeId());
				scoreMembers.put(id, companyCaseTag.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}
		listFromCache.remove("-1");
		return listFromCache;
	}

	/**
	 * 检查企业是否可以发布专案
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public void addPublishCaseNnm(long companyId) {
		String companyPublishCaseKey = KeyFactory.companyPublishCaseKey(companyId);
		redis.STRINGS.incrBy(companyPublishCaseKey, 1);
		redis.KEYS.expire(companyPublishCaseKey, Time.dayEnd() - Time.now() + 2);// 防止临界情况发生
	}

	/**
	 * 获取企业今天发布了多少个专案
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public int getCompanyTodayPublishCaseNnm(long companyId) {
		String companyPublishCaseKey = KeyFactory.companyPublishCaseKey(companyId);
		return StringUtil.getInt(redis.STRINGS.get(companyPublishCaseKey));
	}

	/**
	 * 获取企业的粉丝列表
	 * @param currentUserId 当前用户id
	 * @param companyId 企业id
	 * @param lastIndex 上次索引id
	 * @param pageSize 页面大小
	 */
	public CompanyFansListResponse getCompanyFansList(long currentUserId, long companyId, int lastIndex, int pageSize) {
		String key = KeyFactory.companyFansKey(companyId);
		CompanyFansListResponse companyFansListResponse = new CompanyFansListResponse();

		Set<String> listFromCache = redis.SORTSET.zrevrange(key, lastIndex, lastIndex + pageSize);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusCompany> userFocusCompanyList = companyDao.getCompanyFansList(companyId, lastIndex, pageSize);
			if (userFocusCompanyList == null || userFocusCompanyList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return companyFansListResponse;
			}

			int size = userFocusCompanyList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusCompany focusCompany : userFocusCompanyList) {
				String userId = String.valueOf(focusCompany.getUserId());
				scoreMembers.put(userId, focusCompany.getId() * 1D);
				listFromCache.add(userId);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取用户
		Map<Long, DUser> UserMap = userService.multiGetUser(listFromCache);
		int i = lastIndex;
		for (Entry<Long, DUser> e : UserMap.entrySet()) {
			DUser user = e.getValue();
			if (user == null) {
				continue;
			}
			CompanyFansResponse companyFansResponse = new CompanyFansResponse();
			companyFansResponse.setUserBase(userService.getUserBaseResponse(user));
			if (currentUserId == user.getId()) {
				companyFansResponse.setIsFocus(1);
			} else {
				companyFansResponse.setIsFocus(userService.isFocusUser(currentUserId, user.getId()) ? 1 : 0);
			}
			companyFansResponse.setLastIndex(i++);
			companyFansListResponse.addList(companyFansResponse);
		}
		return companyFansListResponse;
	}

	/**
	 * 获取企业的粉丝列表
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public Map<Long, DUser> getCompanyFansList(long companyId, int page, int pageSize) {
		String key = KeyFactory.companyFansKey(companyId);
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusCompany> companyFansList = companyDao.getCompanyFansList(companyId, start, page == 1 ? LengthLimit.reloadFromRedisLen.getLength() : pageSize);
			if (companyFansList == null || companyFansList.isEmpty()) {
				// 穿透db是加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return new HashMap<>(0);
			}

			int size = companyFansList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusCompany focusCompany : companyFansList) {
				String uid = String.valueOf(focusCompany.getUserId());
				scoreMembers.put(uid, focusCompany.getId() * 1D);
				listFromCache.add(uid);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取用户
		return userService.multiGetUser(listFromCache);
	}

	/**
	 * 收藏创意 
	 * @author ruan 
	 * @param companyId 企业id
	 * @param ideaId 创意id
	 */
	public DCollectIdea collectIdea(long companyId, long ideaId) {
		DCollectIdea collectIdea = companyDao.collectIdea(companyId, ideaId);
		if (collectIdea == null) {
			return null;
		}
		redis.SORTSET.zadd(KeyFactory.collectIdeaListKey(companyId), collectIdea.getCreateTime(), String.valueOf(collectIdea.getIdeaId()));
		return collectIdea;
	}

	/**
	 * 判断是否已经收藏创意 
	 * @author ruan 
	 * @param companyId 企业id
	 * @param ideaId 创意id
	 */
	public boolean isCollectedIdea(long companyId, long ideaId) {
		String key = KeyFactory.collectIdeaListKey(companyId);
		double score = redis.SORTSET.zscore(key, String.valueOf(ideaId));
		if (score <= 0) {
			getCompanyCollectIdea(companyId, 1, LengthLimit.reloadFromRedisLen.getLength());
			score = redis.SORTSET.zscore(key, String.valueOf(ideaId));
			if (score > 0) {
				return true;
			}
			DCollectIdea collectIdea = companyDao.getCompanyCollectIdea(companyId, ideaId);
			if (collectIdea == null) {
				return false;
			}
			redis.SORTSET.zadd(KeyFactory.collectIdeaListKey(companyId), collectIdea.getCreateTime(), String.valueOf(collectIdea.getIdeaId()));
			return true;
		}
		return true;
	}

	/**
	 * 获取企业收藏的点子
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public Map<Long, DIdea> getCompanyCollectIdea(long companyId, int page, int pageSize) {
		String key = KeyFactory.collectIdeaListKey(companyId);
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCollectIdea> collectIdeaList = companyDao.getCompanyCollectIdea(companyId, page, page == 1 ? LengthLimit.reloadFromRedisLen.getLength() : pageSize);
			if (collectIdeaList == null || collectIdeaList.isEmpty()) {
				// 穿透db是加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return new HashMap<>(0);
			}

			int size = collectIdeaList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCollectIdea collectIdea : collectIdeaList) {
				String ideaId = String.valueOf(collectIdea.getIdeaId());
				scoreMembers.put(ideaId, collectIdea.getCreateTime() * 1D);
				listFromCache.add(ideaId);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		return ideaService.multiGetIdea(listFromCache);
	}

	/**
	 * 企业关注用户
	 * @author ruan 
	 * @param companyId 企业id
	 * @param userId 用户id
	 */
	public DCompanyFocus focusUser(long companyId, long userId) {
		DCompanyFocus companyFocus = companyDao.focusUser(companyId, userId);
		if (companyFocus == null) {
			return null;
		}
		redis.STRINGS.setex(KeyFactory.companyFocusUserKey(companyId, userId).getBytes(), (int) TimeUnit.DAYS.toSeconds(7), SerialUtil.toBytes(companyFocus));
		redis.SORTSET.zadd(KeyFactory.companyFocusUserListKey(companyId), companyFocus.getCreateTime(), String.valueOf(userId));
		return companyFocus;
	}

	/**
	 * 企业关注用户
	 * @author ruan 
	 * @param companyId 企业id
	 * @param userId 用户id
	 */
	public boolean unfocusUser(long companyId, long userId) {
		if (!companyDao.unfocusUser(companyId, userId)) {
			return false;
		}
		redis.KEYS.del(KeyFactory.companyFocusUserKey(companyId, userId));
		redis.SORTSET.zrem(KeyFactory.companyFocusUserListKey(companyId), String.valueOf(userId));
		return true;
	}

	/**
	 * 获取企业关注的用户列表
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页面
	 * @param pageSize 页面大小
	 */
	public Map<Long, DUser> getFocusUserList(long companyId, int page, int pageSize) {
		String key = KeyFactory.companyFocusUserListKey(companyId);
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCompanyFocus> companyFocusList = companyDao.getFocusUserList(companyId, page, page == 1 ? LengthLimit.reloadFromRedisLen.getLength() : pageSize);
			if (companyFocusList == null || companyFocusList.isEmpty()) {
				// 穿透db是加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return new HashMap<>(0);
			}

			int size = companyFocusList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCompanyFocus companyFocus : companyFocusList) {
				String uid = String.valueOf(companyFocus.getUserId());
				scoreMembers.put(uid, companyFocus.getCreateTime() * 1D);
				listFromCache.add(uid);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取用户
		return userService.multiGetUser(listFromCache);
	}

	/**
	 * 获取企业有没有关注这个用户
	 * @author ruan 
	 * @param companyId 企业id
	 * @param userId 用户id
	 */
	public DCompanyFocus getFocusUser(final long companyId, final long userId) {
		byte[] key = KeyFactory.companyFocusUserKey(companyId, userId).getBytes();
		byte[] bytes = redis.STRINGS.get(key);
		if (bytes == null || bytes.length <= 0) {
			DCompanyFocus companyFocus = companyDao.getFocusUser(companyId, userId);
			if (companyFocus == null) {
				return null;
			}
			redis.STRINGS.setex(key, (int) TimeUnit.DAYS.toSeconds(7), SerialUtil.toBytes(companyFocus));
			redis.SORTSET.zadd(KeyFactory.companyFocusUserListKey(companyId), companyFocus.getCreateTime(), String.valueOf(userId));
			return companyFocus;
		}

		// 关注用户数+1
		guThreadPool.execute(() -> {
			DCompany company = getCompanyById(companyId);
			if (company != null) {
				commonService.updateCountColumn(CountKey.companyFocusUserNum, companyId, 1);
			}
		});
		return SerialUtil.fromBytes(bytes, DCompanyFocus.class);
	}

	/**
	 * 批量获取企业关注的用户
	 * @author ruan 
	 * @param companyId 企业id
	 * @param userIdSet 用户id集合
	 * @return UserId => DCompanyFocus
	 */
	public Map<Long, DCompanyFocus> multiGetFocusUser(long companyId, Set<Long> userIdSet) {
		// 去掉占位符
		userIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (userIdSet.isEmpty()) {
			return new HashMap<>(0);
		}
		// 首先去缓存拿
		int size = userIdSet.size();
		List<String> companyFocusUserKeyList = new ArrayList<String>(size);
		for (long userId : userIdSet) {
			companyFocusUserKeyList.add(KeyFactory.companyFocusUserKey(companyId, userId));
		}
		List<byte[]> byteList = redis.STRINGS.mget(companyFocusUserKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		Map<Long, DCompanyFocus> data = new LinkedHashMap<>(size);
		for (long userId : userIdSet) {
			data.put(userId, null);
		}
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DCompanyFocus companyFocus = SerialUtil.fromBytes(bytes, DCompanyFocus.class);
				if (companyFocus == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(companyFocus.getUserId(), companyFocus);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpUserIdSet = new HashSet<Long>(size);
		for (Entry<Long, DCompanyFocus> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpUserIdSet.add(e.getKey());
			}
		}
		if (tmpUserIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DCompanyFocus> companyFocusList = companyDao.getFocusUser(companyId, tmpUserIdSet);
		if (companyFocusList == null || companyFocusList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(companyFocusList.size());
		for (DCompanyFocus companyFocus : companyFocusList) {
			// 如果数据库查到就回写到缓存
			data.put(companyFocus.getUserId(), companyFocus);
			keysValuesMap.put(KeyFactory.companyFocusUserKey(companyId, companyFocus.getUserId()).getBytes(), SerialUtil.toBytes(companyFocus));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(7));
		return data;
	}

	/**
	 * 记录企业G点支出
	 * @author ruan 
	 * @param companyId 企业id
	 * @param caseId 专案id
	 * @param points 支出点数
	 */
	public DCompanyExpense addCompanyExpense(long companyId, long caseId, int points) {
		DCompanyExpense companyExpense = companyDao.addCompanyExpense(companyId, caseId, points);
		if (companyExpense == null) {
			return null;
		}
		redis.STRINGS.setex(KeyFactory.companyExpenseKey(companyId, caseId).getBytes(), (int) TimeUnit.DAYS.toSeconds(7), SerialUtil.toBytes(companyExpense));
		redis.SORTSET.zadd(KeyFactory.companyExpenseListKey(companyId), companyExpense.getId(), String.valueOf(companyExpense.getCaseId()));
		return companyExpense;
	}

	/**
	 * 获取G点支出列表
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	public Map<Long, DCompanyExpense> getCompanyExpense(long companyId, int page, int pageSize) {
		String key = KeyFactory.companyExpenseListKey(companyId);
		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DCompanyExpense> companyExpenseList = companyDao.getCompanyExpense(companyId, page, page == 1 ? LengthLimit.reloadFromRedisLen.getLength() : pageSize);
			if (companyExpenseList == null || companyExpenseList.isEmpty()) {
				// 穿透db是加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return new HashMap<>(0);
			}

			int size = companyExpenseList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DCompanyExpense companyExpense : companyExpenseList) {
				String id = String.valueOf(companyExpense.getId());
				scoreMembers.put(id, companyExpense.getId() * 1D);
				listFromCache.add(id);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}
		return multiGetCompanyExpense(companyId, listFromCache);
	}

	/**
	 * 批量获取G点支出
	 * @author ruan
	 * @param companyId 企业id 
	 * @param caseIdSet 专案id集合
	 */
	private Map<Long, DCompanyExpense> multiGetCompanyExpense(long companyId, Set<String> caseIdSet) {
		// 去掉占位符
		caseIdSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (caseIdSet.isEmpty()) {
			return new HashMap<>(0);
		}
		// 首先去缓存拿
		int size = caseIdSet.size();
		Map<Long, DCompanyExpense> data = new LinkedHashMap<>(size);
		List<String> companyFocusUserKeyList = new ArrayList<String>(size);
		for (String caseId : caseIdSet) {
			companyFocusUserKeyList.add(KeyFactory.companyExpenseKey(companyId, StringUtil.getLong(caseId)));
			data.put(StringUtil.getLong(caseId), null);
		}
		List<byte[]> byteList = redis.STRINGS.mget(companyFocusUserKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				DCompanyExpense companyExpense = SerialUtil.fromBytes(bytes, DCompanyExpense.class);
				if (companyExpense == null) {
					// 有可能反序列化不成功
					continue;
				}
				data.put(companyExpense.getCaseId(), companyExpense);
			}
		}

		// 找出缓存没有的
		Set<Long> tmpUserIdSet = new HashSet<Long>(size);
		for (Entry<Long, DCompanyExpense> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpUserIdSet.add(e.getKey());
			}
		}
		if (tmpUserIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<DCompanyExpense> companyExpenseList = companyDao.getCompanyExpense(companyId, tmpUserIdSet);
		if (companyExpenseList == null || companyExpenseList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(companyExpenseList.size());
		for (DCompanyExpense companyExpense : companyExpenseList) {
			// 如果数据库查到就回写到缓存
			data.put(companyExpense.getCaseId(), companyExpense);
			keysValuesMap.put(KeyFactory.companyExpenseKey(companyId, companyExpense.getCaseId()).getBytes(), SerialUtil.toBytes(companyExpense));
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(7));
		return data;
	}
}