package com.gu.service.user;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gu.core.enums.Platform;
import com.gu.core.enums.Sex;
import com.gu.core.enums.UserPointsAddEvent;
import com.gu.core.enums.UserPointsSubEvent;
import com.gu.core.enums.UserScoreAddEvent;
import com.gu.core.enums.UserScoreSubEvent;
import com.gu.core.errorcode.UserError;
import com.gu.core.protocol.UserFansListResponse;
import com.gu.core.protocol.UserFansResponse;
import com.gu.core.protocol.UserFocusCompany;
import com.gu.core.protocol.UserFocusCompanyList;
import com.gu.core.protocol.UserFocusListResponse;
import com.gu.core.protocol.UserFocusResponse;
import com.gu.core.protocol.UserInfoResponse;
import com.gu.core.protocol.UserSearchListResponse;
import com.gu.core.protocol.UserSettingResponse;
import com.gu.core.protocol.base.UserBaseResponse;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.JSONObject;
import com.gu.core.util.Secret;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.SystemUtil;
import com.gu.core.util.Time;
import com.gu.dao.CompanyCountDao;
import com.gu.dao.UserDao;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DFocusCompany;
import com.gu.dao.model.DFocusUser;
import com.gu.dao.model.DStoreMobile;
import com.gu.dao.model.DUser;
import com.gu.dao.model.DUserAccusation;
import com.gu.dao.model.DUserFeedback;
import com.gu.dao.model.DUserProve;
import com.gu.dao.model.DUserSetting;
import com.gu.dao.model.DWechatOpen;
import com.gu.service.cases.CaseService;
import com.gu.service.common.CommonService;
import com.gu.service.company.CompanyService;

/**
 * 用户service类
 * @author luo
 */
@Component
public class UserService {
	/**
	* logger
	*/
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private Redis redis;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CaseService caseService;

	@Autowired
	private CompanyCountDao companyCountDao;

	@Autowired
	private GuThreadPool guThreadPool;

	@Autowired
	private ThirdPartyService thirdPartyService;

	@Autowired
	private CommonService commonService;

	/**
	 * 用户实名认证缓存
	 */
	private static Cache<String, Cache<String, DUserProve>> userProveCache = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build();

	/**
	 * 写入数据
	 * @author ruan 
	 * @param key 键
	 * @param duration 持续时间
	 * @param timeUnit 时间单位
	 * @param userProve 要写入的值 
	 */
	public void putUserProveCache(String key, long duration, TimeUnit timeUnit, DUserProve userProve) {
		Cache<String, DUserProve> tmp = CacheBuilder.newBuilder().expireAfterWrite(duration, timeUnit).build();
		tmp.put(key, userProve);
		userProveCache.put(key, tmp);
	}

	/**
	 * 获取数据
	 * @author ruan 
	 * @param key 键
	 */
	public DUserProve getUserProveCache(String key) {
		Cache<String, DUserProve> tmp = userProveCache.getIfPresent(key);
		if (tmp == null) {
			return null;
		}
		return tmp.getIfPresent(key);
	}

	/**
	 * 升级经验配置缓存
	 */
	private LoadingCache<Integer, Map<Integer, Integer>> levelExpConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, Map<Integer, Integer>>() {
		public Map<Integer, Integer> load(Integer key) throws Exception {
			logger.warn("load level exp config");
			return userDao.getLevelExpConfig();
		}
	});

	/**
	 * 一句话亮身份例子缓存
	 */
	private LoadingCache<Integer, List<String>> userIdentityConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, List<String>>() {
		public List<String> load(Integer key) throws Exception {
			logger.warn("load user identity config");
			return userDao.getUserIdentityConfig();
		}
	});

	/**
	 * 初始化升级经验配置
	 */
	public void initLevelExpConfig() {
		levelExpConfig.invalidateAll();
		getLevelExpConfig();
	}

	/**
	 * 新增下一级的配置
	 * @param needExp 所需经验
	 */
	public void addLevelExpConfig(int needExp) {
		userDao.addLevelExpConfig(needExp);
	}

	/**
	 * 删除等级配置
	 * @param level 等级
	 */
	public boolean deleteLevelExpConfig(int level) {
		return userDao.deleteLevelExpConfig(level);
	}

	/**
	 * 初始化一句话亮身份例子配置
	 */
	public void initUserIdentityConfig() {
		userIdentityConfig.invalidateAll();
		getUserIdentityConfig();
	}

	/**
	 * 初始化配置数据
	 */
	@PostConstruct
	public void init() {
		initLevelExpConfig();
		initUserIdentityConfig();
	}

	/**
	 * 获取升级经验配置
	 */
	public Map<Integer, Integer> getLevelExpConfig() {
		try {
			return levelExpConfig.get(1);
		} catch (ExecutionException e) {
			logger.error("", e);
		}
		return new HashMap<Integer, Integer>(0);
	}

	/**
	 * 新增一句话亮身份配置
	 */
	public void addUserIdentityConfig(String identity) {
		userDao.addUserIdentityConfig(identity);
	}

	/**
	 * 删除一句话亮身份配置
	 */
	public void deleteUserIdentityConfig(String identity) {
		userDao.deleteUserIdentityConfig(identity);
	}

	/**
	 * 判断一句话亮身份配置是否重复
	 */
	public boolean existsUserIdentityConfig(String identity) {
		return userDao.existsUserIdentityConfig(identity);
	}

	/**
	 * 获取一句话亮身份例子
	 */
	public List<String> getUserIdentityConfig() {
		try {
			return userIdentityConfig.get(1);
		} catch (ExecutionException e) {
			logger.error("", e);
		}
		return new ArrayList<String>(0);
	}

	/**
	 * 用户升级方法(请先把积分和经验先加好)
	 * @param user 用户实体
	 */
	public void userLevelUp(DUser user) {
		Map<Integer, Integer> expConfig = getLevelExpConfig();
		int userLevel = user.getLevel();
		int userExp = user.getExp();
		int nextLevel = userLevel + 1;
		int needExp = StringUtil.getInt(expConfig.get(nextLevel));
		while (userExp >= needExp && needExp > 0) {
			userExp = userExp - needExp;
			userLevel = nextLevel;
			nextLevel = userLevel + 1;
			needExp = StringUtil.getInt(expConfig.get(nextLevel));
			logger.warn("user \"{}\" level up, level: {}", user.getNickname(), nextLevel);
		}

		user.setLevel(userLevel);
		user.setExp(userExp);
	}

	/**
	 * 生成用户登录密码
	 * @param mobile 用户手机
	 * @param password 登录密码
	 */
	public String genUserPassword(String mobile, String password) {
		return Secret.md5(Secret.sha(password) + SystemUtil.getSystemKey() + Secret.base64EncodeToString(String.valueOf(mobile)));
	}

	/**
	 * SortSet列表加占位
	 * @param key
	 */
	public void addSortSetAnchor(String key) {
		redis.SORTSET.zadd(key, -1, "-1");
	}

	public UserBaseResponse getUserBaseResponse(DUser user) {
		UserBaseResponse userBaseResponse = new UserBaseResponse(user.getId(), user.getNickname(), user.getAvatarImage(), user.getIdentity(), user.getLevel(), user.getSex(), user.getIsProve());
		return userBaseResponse;
	}

	/**
	 * 添加用户方法(手机注册)(id自动生成, level等级1, 其他属性""或0)
	 * @param nickname 用户昵称
	 * @param mobile 手机号
	 * @param sex 性别(男1女2)
	 * @param avatar
	 * @param identity 
	 * @param cityId 
	 * @param provinceId 
	 * @return
	 */
	public DUser addUserByMobile(String nickname, String mobile, Sex sex, String password, String avatar, String identity, int provinceId, int cityId, int birthday, Platform platform) {
		DUser user = userDao.addUserByMobile(nickname, mobile, sex, genUserPassword(mobile, password), avatar, identity, provinceId, cityId, birthday, platform);
		if (user == null) {
			return null;
		}
		// 单独统计G点数
		commonService.setCountColumnRedis(CountKey.userPoints, user.getId(), 0);
		// 单独统计积分数
		commonService.setCountColumnRedis(CountKey.userScore, user.getId(), 0);

		redis.STRINGS.setex(KeyFactory.userKey(user.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(user));
		redis.STRINGS.setex(KeyFactory.mobileKey(user.getMobile()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(user));

		// 记录新增用户
		userDao.newUserLog();
		return user;
	}

	/**
	 * 昵称唯一性检查
	 * @param nickname 昵称
	 * @return false=已被使用
	 */
	public boolean nicknameOnly(String nickname) {
		return userDao.nicknameOnly(nickname);
	}

	/**
	 * 手机唯一性检查
	 * @param mobile 手机
	 * @return false=已被使用
	 */
	public boolean mobileOnly(String mobile) {
		return userDao.mobileOnly(mobile);
	}

	/**
	 * 预留手机号
	 * @param mobile 手机号码
	 * @param points 赠送点数
	 */
	public DStoreMobile storeMobile(String mobile, int points) {
		DStoreMobile storeMobile = userDao.storeMobile(mobile, points);
		if (storeMobile == null) {
			return null;
		}

		redis.STRINGS.setex(KeyFactory.storeMobileKey(mobile).getBytes(), 86400, SerialUtil.toBytes(storeMobile));
		return storeMobile;
	}

	/**
	 * 获取预留手机号
	 * @param mobile 手机号码
	 */
	public DStoreMobile getStoreMobile(String mobile) {
		String key = KeyFactory.storeMobileKey(mobile);
		byte[] bytes = redis.STRINGS.get(key.getBytes());
		if (bytes == null || bytes.length <= 0) {
			DStoreMobile storeMobile = userDao.getStoreMobile(mobile);
			if (storeMobile == null) {
				return null;
			}
			redis.STRINGS.setex(key.getBytes(), 86400, SerialUtil.toBytes(storeMobile));
			return storeMobile;
		}
		return SerialUtil.fromBytes(bytes, DStoreMobile.class);
	}

	/**
	 * 查询用户 By 手机
	 * @param mobile 用户手机
	 * @return
	 */
	public DUser getUserByMobile(String mobile) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.mobileKey(mobile).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			DUser user = userDao.getUserByMobile(mobile);
			if (user == null) {
				return null;
			}
			//写入Redis缓存
			redis.STRINGS.set(KeyFactory.mobileKey(user.getMobile()), SerialUtil.toBytes(user));
			return user;
		}
		return SerialUtil.fromBytes(b, DUser.class);
	}

	/**
	 * 查询用户 By userId
	 * @param userId user表id
	 * @return
	 */
	public DUser getUserById(long userId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.userKey(userId).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			DUser user = userDao.getUserById(userId);
			if (user == null) {
				return null;
			}
			//写入Redis缓存
			redis.STRINGS.set(KeyFactory.userKey(user.getId()), SerialUtil.toBytes(user));
			return user;
		}
		return SerialUtil.fromBytes(b, DUser.class);
	}

	/**
	 * 获取用户信息
	 * @param user 用户对象
	 */
	public UserInfoResponse getUserInfo(DUser user) {
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setUserId(user.getId());
		userInfoResponse.setNickname(user.getNickname());
		userInfoResponse.setAvatarImage(user.getAvatarImage());
		userInfoResponse.setSex(user.getSex());
		userInfoResponse.setProvinceId(user.getProvinceId());
		userInfoResponse.setCityId(user.getCityId());
		userInfoResponse.setSignature(user.getSignature());
		userInfoResponse.setLevel(user.getLevel());
		userInfoResponse.setBirthday(user.getBirthday());
		userInfoResponse.setIdentity(user.getIdentity());
		int score = commonService.getCountColumn(CountKey.userScore, user.getId());
		userInfoResponse.setScore(score);
		int points = commonService.getCountColumn(CountKey.userPoints, user.getId());
		userInfoResponse.setPoints(points);

		// 是否绑定微信
		DWechatOpen wechatOpen = thirdPartyService.getWechatByUserId(user.getId());
		userInfoResponse.setIsBindWechat(wechatOpen == null ? 0 : 1);

		// 参与专案数
		userInfoResponse.setTakePartInCaseNum(caseService.getUserHasTakePartInCaseNum(user.getId()));

		// 关注企业数
		userInfoResponse.setFosusCompanyNum(getUserFocusCompanyNum(user.getId()));

		// 关注人数
		userInfoResponse.setFosusUserNum(getUserFocusNum(user.getId()));

		// 粉丝数
		userInfoResponse.setFansNum(getUserFansNum(user.getId()));
		return userInfoResponse;
	}

	/**
	 * 修改用户信息
	 * @param user 用户对象
	 */
	public boolean updateUser(DUser user) {
		boolean updaetSuccess = userDao.updateUser(user);
		if (updaetSuccess) {
			redis.STRINGS.setex(KeyFactory.userKey(user.getId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(user));
			redis.STRINGS.setex(KeyFactory.mobileKey(user.getMobile()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(user));
		}
		return updaetSuccess;
	}

	/**
	 * 批量获取用户
	 * @param userIdSet 用户id集合
	 */
	public Map<Long, DUser> multiGetUser(Set<String> userIdSet) {
		return commonService.multiGetT(userIdSet, DUser.class);
	}

	/**
	 * 是否已经关注该企业
	 * @param userId 用户id
	 * @param companyId 企业id
	 */
	public boolean isFocusCompany(long userId, long companyId) {
		// 先查缓存
		// 如果缓存查不到，取mysql 500条，判断一下这500条之中有没有
		// 如果没有，先回写这500条
		// 然后再从mysql读取，如果读得到就回写，读不到就真的没有了
		String key = KeyFactory.userFocusCompanyKey(userId);
		double score = redis.SORTSET.zscore(key, String.valueOf(companyId));
		if (score <= 0) {
			getUserFocusCompanyList(userId, 1, 500);
			score = redis.SORTSET.zscore(key, String.valueOf(companyId));
			if (score > 0) {
				return true;
			}
			DFocusCompany focusCompany = userDao.isFocusCompany(userId, companyId);
			if (focusCompany == null) {
				return false;
			}
			return redis.SORTSET.zadd(key, focusCompany.getId(), String.valueOf(companyId)) >= 1;
		}
		return true;
	}

	/**
	 * 用户关注企业
	 * @param user 用户对象
	 * @param companyId 企业id
	 */
	public boolean focusCompany(DUser user, long companyId) {
		DFocusCompany focusCompany = userDao.focusCompany(user.getId(), companyId);
		if (focusCompany == null) {
			return false;
		}
		boolean b = redis.SORTSET.zadd(KeyFactory.userFocusCompanyKey(user.getId()), focusCompany.getId(), String.valueOf(companyId)) >= 1;
		b = b && redis.SORTSET.zadd(KeyFactory.companyFansKey(companyId), focusCompany.getId(), String.valueOf(user.getId())) >= 1;
		if (!b) {
			return false;
		}

		guThreadPool.execute(() -> {
			// 企业粉丝数+1
			DCompany company = companyService.getCompanyById(companyId);
			if (company == null) {
				logger.warn("company is not exists, companyId: {}", companyId);
				return;
			}
			commonService.updateCountColumn(CountKey.companyFansNum, companyId, 1);

			// 统计
			companyCountDao.addCompanyCountCity(companyId, user.getProvinceId(), user.getCityId());
			companyCountDao.addCompanyCountProvince(companyId, user.getProvinceId());
			companyCountDao.addCompanyCountSex(companyId, user.getSex());
			companyCountDao.addCompanyCountProve(companyId, user);
			companyCountDao.addCompanyCountAge(companyId, user);
			companyCountDao.addCompanyCountFocus(companyId);
			int fansNum = commonService.getCountColumn(CountKey.companyFansNum, companyId);
			companyCountDao.addCompanyCountFocusTotal(company, fansNum);
		});
		return true;
	}

	/**
	 * 用户取消关注企业
	 * @param user 用户对象
	 * @param companyId 企业id
	 */
	public boolean unfocusCompany(DUser user, long companyId) {
		// 删除数据库
		if (!userDao.unfocusCompany(user.getId(), companyId)) {
			return false;
		}

		// 删除缓存
		boolean b = redis.SORTSET.zrem(KeyFactory.userFocusCompanyKey(user.getId()), String.valueOf(companyId)) >= 1;
		b = b && redis.SORTSET.zrem(KeyFactory.companyFansKey(companyId), String.valueOf(user.getId())) >= 1;
		if (!b) {
			return false;
		}

		guThreadPool.execute(() -> {
			// 企业粉丝数-1
			DCompany company = companyService.getCompanyById(companyId);
			if (company == null) {
				logger.warn("company is not exists, companyId: {}", companyId);
				return;
			}
			commonService.updateCountColumn(CountKey.companyFansNum, companyId, -1);

			// 修正统计数据
			companyCountDao.subCompanyCountCity(companyId, user.getProvinceId(), user.getCityId());
			companyCountDao.subCompanyCountProvince(companyId, user.getProvinceId());
			companyCountDao.subCompanyCountSex(companyId, user.getSex());
			companyCountDao.subCompanyCountProve(companyId, user);
			companyCountDao.subCompanyCountAge(companyId, user);
			companyCountDao.addCompanyCountUnFocus(companyId);
			int fansNum = commonService.getCountColumn(CountKey.companyFansNum, companyId);
			companyCountDao.addCompanyCountFocusTotal(company, fansNum);
		});
		return true;
	}

	/**
	 * 获取用户关注企业的数量
	 * @param userId 用户id
	 */
	public int getUserFocusCompanyNum(long userId) {
		String key = KeyFactory.userFocusCompanyKey(userId);
		int userFocusCompanyNum = (int) redis.SORTSET.zcard(key);
		if (userFocusCompanyNum <= 0) {
			getUserFocusCompanyList(userId, 1, 500);
			userFocusCompanyNum = (int) redis.SORTSET.zcard(key);
		}
		if (redis.SORTSET.zscore(key, "-1") == -1) {
			userFocusCompanyNum--;
		}
		return userFocusCompanyNum;
	}

	/**
	 * 获取用户关注的企业列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)
	 */
	public UserFocusCompanyList getUserFocusCompanyList(long userId, int page, int pageSize) {
		String key = KeyFactory.userFocusCompanyKey(userId);
		UserFocusCompanyList userFocusCompanyList = new UserFocusCompanyList();

		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusCompany> focusCompanyList = userDao.getUserFocusCompanyList(userId, page, pageSize);
			if (focusCompanyList == null || focusCompanyList.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return userFocusCompanyList;
			}

			int size = focusCompanyList.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusCompany focusCompany : focusCompanyList) {
				String companyId = String.valueOf(focusCompany.getCompanyId());
				scoreMembers.put(companyId, focusCompany.getId() * 1D);
				listFromCache.add(companyId);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取企业
		Map<Long, DCompany> companyMap = companyService.multiGetCompany(listFromCache);
		for (Entry<Long, DCompany> e : companyMap.entrySet()) {
			DCompany company = e.getValue();
			if (company == null) {
				continue;
			}
			UserFocusCompany userFocusCompany = new UserFocusCompany();
			userFocusCompany.setCompanyId(company.getId());
			userFocusCompany.setCompanyLogo(company.getLogoImage());
			userFocusCompany.setCompanyName(company.getName());
			userFocusCompanyList.addList(userFocusCompany);
		}
		return userFocusCompanyList;
	}

	/**
	 * 用户关注用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public boolean focusUser(long userId, long friendId) {
		DFocusUser focusUser = userDao.focusUser(userId, friendId);
		if (focusUser == null) {
			return false;
		}

		// 我关注别人
		// 就是我自己的关注人列表加一条数据
		// 别人的粉丝列表加上我
		String userFocus = KeyFactory.userFocusKey(userId);
		boolean b = redis.SORTSET.zadd(userFocus, focusUser.getId(), String.valueOf(focusUser.getFriendId())) >= 1;
		if (!b) {
			return false;
		}

		String userFans = KeyFactory.userFansKey(friendId);
		b = b & redis.SORTSET.zadd(userFans, focusUser.getId(), String.valueOf(focusUser.getUserId())) >= 1;
		return b;
	}

	/**
	 * 用户取消关注用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public boolean unfocusUser(long userId, long friendId) {
		if (!userDao.unfocusUser(userId, friendId)) {
			return false;
		}

		boolean b = redis.SORTSET.zrem(KeyFactory.userFocusKey(userId), String.valueOf(friendId)) >= 1;
		if (!b) {
			return false;
		}
		b = b & redis.SORTSET.zrem(KeyFactory.userFansKey(friendId), String.valueOf(userId)) >= 1;
		return b;
	}

	/**
	 * 判断用户是否可以关注某用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public boolean isCanFocus(long userId, long friendId) {
		String key = KeyFactory.isCanFocusKey(userId, friendId);
		boolean b = redis.STRINGS.incrBy(key, 1) <= 3;
		redis.KEYS.expire(key, 86400);
		return b;
	}

	/**
	 * 判断用户是否关注某用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public boolean isFocusUser(long userId, long friendId) {
		String key = KeyFactory.userFocusKey(userId);
		double score = redis.SORTSET.zscore(key, String.valueOf(friendId));
		if (score <= 0) {
			userFocusList(userId, 1, 500);
			score = redis.SORTSET.zscore(key, String.valueOf(friendId));
			if (score > 0) {
				return true;
			}
			DFocusUser focusUser = userDao.isFocus(userId, friendId);
			if (focusUser == null) {
				return false;
			}
			boolean b = redis.SORTSET.zadd(key, focusUser.getId(), String.valueOf(friendId)) >= 1;
			b = b & redis.SORTSET.zadd(KeyFactory.userFansKey(friendId), focusUser.getId(), String.valueOf(userId)) >= 1;
			return b;
		}
		return true;
	}

	/**
	 * 获取我的关注数
	 * @param userId 用户id
	 */
	public int getUserFocusNum(long userId) {
		String key = KeyFactory.userFocusKey(userId);
		int userFocusNum = (int) redis.SORTSET.zcard(key);
		if (userFocusNum <= 0) {
			userFocusList(userId, 1, 500);
			userFocusNum = (int) redis.SORTSET.zcard(key);
		}
		if (redis.SORTSET.zscore(key, "-1") == -1) {
			userFocusNum--;
		}
		return userFocusNum;
	}

	/**
	 * 我的关注列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)	
	 */
	public UserFocusListResponse userFocusList(long userId, int page, int pageSize) {
		String key = KeyFactory.userFocusKey(userId);
		UserFocusListResponse userFocusList = new UserFocusListResponse();

		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusUser> userFocusLists = userDao.getUserFocusList(userId, page, pageSize);
			if (userFocusLists == null || userFocusLists.isEmpty()) {
				// 穿透db是加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return userFocusList;
			}

			int size = userFocusLists.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusUser focusUser : userFocusLists) {
				String uid = String.valueOf(focusUser.getFriendId());
				scoreMembers.put(uid, focusUser.getId() * 1D);
				listFromCache.add(uid);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取用户
		Map<Long, DUser> friendMap = multiGetUser(listFromCache);
		for (Entry<Long, DUser> e : friendMap.entrySet()) {
			DUser user = e.getValue();
			if (user == null) {
				continue;
			}
			UserFocusResponse userFocusResponse = new UserFocusResponse();
			userFocusResponse.setUserId(user.getId());
			userFocusResponse.setName(user.getNickname());
			userFocusResponse.setAvatar(user.getAvatarImage());
			userFocusResponse.setLevel(user.getLevel());
			userFocusList.addList(userFocusResponse);
		}
		return userFocusList;
	}

	/**
	 * 获取用户粉丝数
	 * @param userId 用户id
	 */
	public int getUserFansNum(long userId) {
		String key = KeyFactory.userFansKey(userId);
		int userFansNum = (int) redis.SORTSET.zcard(key);
		if (userFansNum <= 0) {
			userFansList(userId, 1, 500);
			userFansNum = (int) redis.SORTSET.zcard(key);
		}
		if (redis.SORTSET.zscore(key, "-1") == -1) {
			userFansNum--;
		}
		return userFansNum;
	}

	/**
	 * 我的粉丝列表
	 * @param userId 用户id
	 * @param page 当前页码
	 * @param pageSize 页面大小(最大20)	
	 */
	public UserFansListResponse userFansList(long userId, int page, int pageSize) {
		String key = KeyFactory.userFansKey(userId);
		UserFansListResponse UserFansList = new UserFansListResponse();

		int start = (page - 1) * pageSize;
		int end = start + pageSize - 1;

		// 先从缓存拿数据
		Set<String> listFromCache = redis.SORTSET.zrevrange(key, start, end);
		if (listFromCache == null || listFromCache.isEmpty()) {
			List<DFocusUser> UserFansLists = userDao.getUserFansList(userId, page, pageSize);
			if (UserFansLists == null || UserFansLists.isEmpty()) {
				// 穿透db时加占位
				addSortSetAnchor(key);
				// 数据库也没有，返回
				return UserFansList;
			}

			int size = UserFansLists.size();
			listFromCache = new LinkedHashSet<String>(size);

			// 数据库有，批量回写缓存
			Map<String, Double> scoreMembers = new HashMap<String, Double>(size);
			for (DFocusUser focusUser : UserFansLists) {
				String uid = String.valueOf(focusUser.getUserId());
				scoreMembers.put(uid, focusUser.getId() * 1D);
				listFromCache.add(uid);
			}
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取用户
		Map<Long, DUser> friendMap = multiGetUser(listFromCache);
		for (Entry<Long, DUser> e : friendMap.entrySet()) {
			DUser user = e.getValue();
			if (user == null) {
				continue;
			}
			UserFansResponse userFansResponse = new UserFansResponse();
			userFansResponse.setUserId(user.getId());
			userFansResponse.setName(user.getNickname());
			userFansResponse.setAvatar(user.getAvatarImage());
			userFansResponse.setLevel(user.getLevel());
			UserFansList.addList(userFansResponse);
		}
		return UserFansList;
	}

	/**
	 * 是否可以吐槽
	 * @param userId 用户id
	 */
	public boolean isCanFlow(long userId) {
		String userFlowCountKey = KeyFactory.userFlowCountKey(userId);
		long result = redis.STRINGS.incrBy(userFlowCountKey, 1);
		return result > 5;
	}

	/**
	 * 给用户加G点
	 * @param userId 用户id
	 * @param points 加了的G点
	 * @param event 加钱事件
	 */
	public boolean addPoints(final long userId, int points, UserPointsAddEvent event) {
		// 写入数据库(写入缓存那里都要连带锁在一起，这样安全一点)
		points = Math.abs(points);

		DUser user = getUserById(userId);
		if (user == null) {
			logger.warn("addPoints, user not exists, userId: {}", userId);
			return false;
		}
		boolean b = commonService.updateCountColumn(CountKey.userPoints, userId, points);
		if (!b) {
			logger.warn("update user points faild! userId: {}, points: {}", user.getId(), points);
			return false;
		}

		// 记录日志
		userDao.userPointAddLog(userId, points, event);
		return true;
	}

	/**
	 * 扣用户G点
	 * @param userId 用户id
	 * @param points 要扣的G点
	 * @param event 扣钱事件
	 */
	public boolean subPoints(long userId, int points, UserPointsSubEvent event) {
		// 判断是否够钱(写入缓存那里都要连带锁在一起，这样安全一点)
		points = Math.abs(points);
		DUser user = getUserById(userId);
		if (user == null) {
			logger.warn("subPoints, user not exists, userId: {}", userId);
			return false;
		}

		int nowPoints = commonService.getCountColumn(CountKey.userPoints, userId);
		if (nowPoints - points < 0) {
			logger.warn("user points not enough! userId: {}, points: {}", user.getId(), points);
			return false;
		}

		// 写入数据库
		boolean b = commonService.updateCountColumn(CountKey.userPoints, userId, -points);
		if (!b) {
			logger.warn("update user points faild! userId: {}, points: {}", user.getId(), points);
			return false;
		}

		// 记录日志
		userDao.userPointSubLog(userId, points, event);
		return true;
	}

	/**
	 * 给用户加积分
	 * @param userId 用户id
	 * @param score 加了的积分
	 * @param event 加积分事件
	 */
	public boolean addScore(long userId, int score, UserScoreAddEvent event) {
		// 写入数据库(写入缓存那里都要连带锁在一起，这样安全一点)
		score = Math.abs(score);
		DUser user = getUserById(userId);
		if (user == null) {
			logger.warn("addScore, user not exists, userId: {}", userId);
			return false;
		}

		boolean b = commonService.updateCountColumn(CountKey.userScore, userId, score);
		if (!b) {
			logger.warn("update user score faild! userId: {}, score: {}", user.getId(), score);
			return false;
		}

		// 记录日志
		userDao.userScoreAddLog(userId, score, event);
		return true;
	}

	/**
	 * 扣用户积分
	 * @param userId 用户id
	 * @param score 要扣的积分
	 * @param event 扣积分事件
	 */
	public boolean subScore(long userId, int score, UserScoreSubEvent event) {
		// 判断是否够积分(写入缓存那里都要连带锁在一起，这样安全一点)
		score = Math.abs(score);
		DUser user = getUserById(userId);
		if (user == null) {
			logger.warn("subScore, user not exists, userId: {}", userId);
			return false;
		}
		int nowScore = commonService.getCountColumn(CountKey.userScore, user.getId());
		if (nowScore - score < 0) {
			logger.warn("user score not enough! userId: {}, score: {}", user.getId(), score);
			return false;
		}

		// 写入数据库
		boolean b = commonService.updateCountColumn(CountKey.userScore, userId, -score);
		if (!b) {
			logger.warn("update user score faild! userId: {}, score: {}", user.getId(), score);
			return false;
		}

		// 记录日志
		userDao.userScoreSubLog(userId, score, event);
		return true;
	}

	/**
	 * 日活跃用户统计
	 * @param user 用户对象
	 * @param data 前端传过来的数据
	 */
	public void recordDailyActiveUser(final DUser user, final JSONObject data) {
		guThreadPool.execute(() -> {
			long t = System.nanoTime();

			int now = Time.now();
			String date = Time.date("yyyyMMdd", now);
			int hour = StringUtil.getInt(Time.date("H", now)) + 1;

			// 这个小时没记录过
			String dailyActiveUserHourKey = KeyFactory.dailyActiveUserListKey(date, hour);
			if (redis.SORTSET.zscore(dailyActiveUserHourKey, String.valueOf(user.getId())) <= 0) {
				userDao.recordDailyActiveUser(date, hour);

				redis.SORTSET.zadd(dailyActiveUserHourKey, user.getId(), String.valueOf(user.getId()));
				redis.KEYS.expire(dailyActiveUserHourKey, 7200);
			}

			// 今天没记录过
			String dailyActiveUserKey = KeyFactory.dailyActiveUserListKey(date);
			if (redis.SORTSET.zscore(dailyActiveUserKey, String.valueOf(user.getId())) <= 0) {
				userDao.recordDailyActiveUserTotal(date);

				redis.SORTSET.zadd(dailyActiveUserKey, user.getId(), String.valueOf(user.getId()));
				int dayEnd = Time.dayEnd(date, "yyyyMMdd");
				redis.KEYS.expire(dailyActiveUserKey, dayEnd - now + 10);

				//用户经验+3 
				user.setExp(user.getExp() + 3);
				userLevelUp(user);
				addScore(user.getId(), 3, UserScoreAddEvent.Login);//自带updateUser
			}

			// 过去7天活跃
			LinkedHashMap<String, String> keyMemebrMap = new LinkedHashMap<String, String>(21);
			for (int i = now; i > now - 86400 * 7; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 7);
				keyMemebrMap.put(KeyFactory.userRecent7ActiveKey(startDate, endDate), String.valueOf(user.getId()));
			}

			// 过去14天活跃
			for (int i = now; i > now - 86400 * 14; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 14);
				keyMemebrMap.put(KeyFactory.userRecent14ActiveKey(startDate, endDate), String.valueOf(user.getId()));
			}

			// 批量获取所有
			Map<String, Map<String, Double>> keyScoreMembers = new HashMap<String, Map<String, Double>>(21);
			Map<String, Double> scoreMap = redis.SORTSET.zscore(keyMemebrMap);
			for (int i = now; i > now - 86400 * 7; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 7);
				String recent7ActiveKey = KeyFactory.userRecent7ActiveKey(startDate, endDate);
				double score = StringUtil.getDouble(scoreMap.get(recent7ActiveKey));
				if (score <= 0) {
					userDao.recordRecent7Active(startDate, endDate);
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, recent7ActiveKey, user.getId(), String.valueOf(user.getId()));
					redis.KEYS.expire(recent7ActiveKey, 86400 * 8);
				}
			}
			for (int i = now; i > now - 86400 * 14; i = i - 86400) {
				String endDate = Time.date("yyyyMMdd", i - 86400);
				String startDate = Time.date("yyyyMMdd", i - 86400 * 14);
				String recent14ActiveKey = KeyFactory.userRecent14ActiveKey(startDate, endDate);
				double score = StringUtil.getDouble(scoreMap.get(recent14ActiveKey));
				if (score <= 0) {
					userDao.recordRecent14Active(startDate, endDate);
					redis.SORTSET.genKeyScoreMembers(keyScoreMembers, recent14ActiveKey, user.getId(), String.valueOf(user.getId()));
					redis.KEYS.expire(recent14ActiveKey, 86400 * 15);
				}
			}
			if (!keyScoreMembers.isEmpty()) {
				redis.SORTSET.zadd(keyScoreMembers);
			}

			// 记录用户信息
			userDao.recordUserInfo(user.getId(), data.getInt("version"), data.getString("mobileType"));

			logger.warn("recordDailyActiveUser after login exec time: {}", Time.showTime(System.nanoTime() - t));
		});
	}

	/**
	 * 用户搜索
	 * @param search 搜索字段
	 * @param lastUserId 用户id游标
	 * @param pageSize 数目
	 * @return
	 */
	public UserSearchListResponse searchByNickame(String search, long lastUserId, int pageSize) {
		UserSearchListResponse userSearchListResponse = new UserSearchListResponse();
		List<DUser> userList = userDao.searchByNickame(search, lastUserId, pageSize);
		List<UserBaseResponse> list = new ArrayList<UserBaseResponse>();
		for (DUser user : userList) {
			UserBaseResponse userBaseResponse = getUserBaseResponse(user);
			list.add(userBaseResponse);
		}
		userSearchListResponse.setList(list);
		return userSearchListResponse;
	}

	/**
	 * 记录用户最后登录时间(只统计在30内注册的)
	 * @param user 用户实体
	 * @return
	 */
	public boolean recordUserLogintime(DUser user) {
		if (user.getCreateTime() > (Time.now() - (35 * 86400))) {
			return userDao.addLogintime(user);
		}
		return false;
	}

	/**
	 * 保存用户意见反馈
	 * @param userId
	 * @param feedback
	 * @return
	 */
	public boolean addUserFeedback(long userId, String feedback) {
		DUserFeedback userFeedback = userDao.addUserFeedback(userId, feedback);
		if (userFeedback == null) {
			return false;
		}
		return true;
	}

	/**
	 * 保存用户举报
	 * @param userId
	 * @param type
	 * @param typeId
	 * @return
	 */
	public boolean addUserAccusation(long userId, int type, int typeId, String content) {
		DUserAccusation userAccusation = userDao.addUserAccusation(userId, type, typeId, content);
		if (userAccusation == null) {
			return false;
		}
		return true;
	}

	/**
	 * 删除预留手机记录
	 * @param storeMobile
	 * @return
	 */
	public boolean deleteStoreMobile(DStoreMobile storeMobile) {
		boolean b = userDao.deleteStoreMobile(storeMobile);
		if (b) {
			String key = KeyFactory.storeMobileKey(storeMobile.getMobile());
			redis.KEYS.del(key);
		}
		return b;
	}

	/**
	 * 添加实名认证资料
	 * @param userId 用户id
	 * @param company 公司
	 * @param job 职位
	 * @param name 名字
	 * @param identityCard 身份证
	 * @param workCard 工作证
	 * @param otherCard 其他证件
	 * @return
	 */
	public DUserProve addUserProve(long userId, String proveInfo, String identityCard, String otherCard) {
		DUserProve userProve = userDao.addUserProve(userId, proveInfo, identityCard, otherCard);
		return userProve;
	}

	/**
	 * 更新实名认证资料
	 * @param userProve
	 * @return
	 */
	public boolean updateUserProve(int isProve, long userId) {
		return userDao.updateUserProve(isProve, userId);
	}

	/**
	 * 更新实名认证资料
	 * @param userProve
	 * @return
	 */
	public DUserProve updateUserProve(long userId, String proveInfo, String identityCard, String otherCard) {
		return userDao.updateUserProve(userId, proveInfo, identityCard, otherCard);
	}

	/**
	 * 查询实名认证信息列表
	 * @param isProve
	 * @return
	 */
	public Map<String, Object> getUserProveList(int isProve, int page, int pageSize) {
		return userDao.getUserProveList(isProve, page, pageSize);
	}

	/**
	 * 查询实名认证信息
	 * @param userId
	 * @return
	 */
	public DUserProve getUserProveByUserId(long userId) {
		return userDao.getUserProveByUserId(userId);
	}

	/**
	 * 保存用户设置
	 * @param userId 用户id
	 * @param allVisit 对所有人可见
	 * @param friendVisit 对朋友可见
	 * @return
	 */
	public boolean saveUserSetting(long userId, int allVisit, int friendVisit) {
		boolean b = userDao.saveUserSetting(userId, allVisit, friendVisit);
		if (b) {
			DUserSetting userSetting = new DUserSetting();
			userSetting.setUserId(userId);
			userSetting.setAllVisit(allVisit);
			userSetting.setFriendVisit(friendVisit);

			redis.STRINGS.setex(KeyFactory.userSettingKey(userId).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(userSetting));
		}
		return b;
	}

	/**
	 * 查询用户设置
	 * @param userId
	 * @return
	 */
	public UserSettingResponse getUserSetting(long userId) {
		UserSettingResponse userSettingResponse = new UserSettingResponse();
		DUserSetting userSetting;
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.userSettingKey(userId).getBytes());
		if (b == null || b.length <= 0) {
			//读取MySql数据
			userSetting = userDao.getUserSetting(userId);

			//旧版本没用户设置
			if (userSetting == null) {
				//保存默认用户设置
				saveUserSetting(userId, 1, 1);
				//组装返回协议
				userSettingResponse.setUserId(userId);
				userSettingResponse.setAllVisit(1);
				userSettingResponse.setFriendVisit(1);
				return userSettingResponse;
			}
			//写入Redis缓存
			redis.STRINGS.setex(KeyFactory.userSettingKey(userId).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), SerialUtil.toBytes(userSetting));
		} else {
			//反序列化
			userSetting = SerialUtil.fromBytes(b, DUserSetting.class);
		}
		//组装返回协议
		userSettingResponse.setUserId(userId);
		userSettingResponse.setAllVisit(userSetting.getAllVisit());
		userSettingResponse.setFriendVisit(userSetting.getFriendVisit());

		return userSettingResponse;
	}

	/**
	 * 用户使用时长统计
	 * @author ruan 
	 * @param userId 用户id
	 * @param duration 使用时长
	 */
	public void addUseDistributionLog(final long userId, final int duration) {
		guThreadPool.execute(() -> {
			// 统计个人今天累计使用时长，如果超过20分钟，就加10点经验和积分
			String useDurationKey = KeyFactory.useDurationKey(userId, Time.date("yyyyMMdd"));
			long result = redis.STRINGS.incrBy(useDurationKey, duration);
			redis.KEYS.expire(useDurationKey, 86400);
			if (result >= 1200) {
				DUser user = getUserById(userId);
				if (user == null) {
					logger.warn("addUseDistributionLog, user not exists, userId: {}", userId);
					return;
				}
				user.setExp(user.getExp() + 10);
				userLevelUp(user);
				addScore(userId, 10, UserScoreAddEvent.online20min);//自带updateUser
			}
			userDao.addUseDistributionLog(userId, duration);
		});
	}

	/**
	 * 验证支付密码
	 * @author ruan 
	 * @param userId 用户id
	 * @param paymentPassword 支付密码
	 */
	public UserError checkPaymentPassword(long userId, String paymentPassword) {
		// 验证错误次数
		String userPaymentPasswordKey = KeyFactory.userPaymentPasswordKey(userId);
		int errorTimes = StringUtil.getInt(redis.STRINGS.get(userPaymentPasswordKey));
		if (errorTimes >= 5) {
			logger.warn("user payment password error times >= 5, userId: {}, error times: {}", userId, errorTimes);
			return UserError.paymentPasswordErrorOver;
		}

		// 获取用户对象
		DUser user = getUserById(userId);
		if (user == null) {
			logger.warn("user not exists, userId: {}", userId);
			return UserError.userNotExists;
		}

		if (user.getPaymentPassword().isEmpty()) {
			logger.warn("payment password not set, userId: {}", userId);
			return UserError.paymentPasswordNotSetError;
		}

		if (!user.getPaymentPassword().equals(genUserPassword(user.getMobile(), paymentPassword))) {
			redis.STRINGS.incrBy(userPaymentPasswordKey, 1);// 错误了的话，要+1
			redis.KEYS.expire(userPaymentPasswordKey, 86400);
			logger.warn("payment password incorrect, userId: {}, payment password: {}", userId, paymentPassword);
			return UserError.paymentPasswordError;
		}
		return null;
	}
}