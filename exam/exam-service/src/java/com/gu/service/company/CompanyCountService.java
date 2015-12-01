package com.gu.service.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.reflect.TypeToken;
import com.gu.core.util.JSONObject;
import com.gu.core.util.StringUtil;
import com.gu.core.util.SystemUtil;
import com.gu.dao.CompanyCountDao;
import com.gu.dao.model.CompanyCountResult;

@Component
public class CompanyCountService {
	private final static Logger logger = LoggerFactory.getLogger(CompanyCountService.class);
	@Autowired
	private CompanyCountDao companyCountDao;
	/**
	 * 缓存时间
	 */
	private final static int cacheDuration = 5;
	/**
	 * 缓存时间单位
	 */
	private final static TimeUnit cacheUnit = SystemUtil.isProduct() ? TimeUnit.MINUTES : TimeUnit.MILLISECONDS;
	/**
	 * 企业性别统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> companyCountSexCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long companyId) throws Exception {
			logger.warn("load company count sex, companyId: {}", companyId);
			return companyCountDao.getCompanyCountSex(companyId);
		}
	});

	/**
	 * 企业实名认证统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> companyCountProveCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long companyId) throws Exception {
			logger.warn("load company count prove cache, companyId: {}", companyId);
			return companyCountDao.getCompanyCountProve(companyId);
		}
	});

	/**
	 * 企业年龄段统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> companyCountAgeCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long companyId) throws Exception {
			logger.warn("load company count age cache, companyId: {}", companyId);
			return companyCountDao.getCompanyCountAge(companyId);
		}
	});

	/**
	 * 企业关注统计缓存
	 */
	private LoadingCache<Long, CompanyCountResult> companyCountFocusCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, CompanyCountResult>() {
		public CompanyCountResult load(Long companyId) throws Exception {
			logger.warn("load company count focus cache, companyId: {}", companyId);
			return companyCountDao.getCompanyCountResult(companyId);
		}
	});

	/**
	 * 企业用户省份统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> companyCountProvinceCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long companyId) throws Exception {
			logger.warn("load company count province cache, companyId: {}", companyId);
			return companyCountDao.getCompanyCountProvince(companyId, 1, 999);
		}
	});

	/**
	 * 企业用户城市统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> companyCountCityCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long companyId) throws Exception {
			logger.warn("load company count city cache, companyId: {}", companyId);
			return companyCountDao.getCompanyCountCity(companyId, 1, 999);
		}
	});

	/**
	 * 新增关注走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> companyCountFocusNewCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load company count focus new cache, key: {}", key);
			String[] arr = key.split(",");
			return companyCountDao.getCompanyCountFocusNew(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 新增关注走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> companyCountIdeaCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load company count idea cache, key: {}", key);
			String[] arr = key.split(",");
			return companyCountDao.getCompanyCountIdea(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 企业关注总数走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> companyCountFocusTotalCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load company count focus total cache, key: {}", key);
			String[] arr = key.split(",");
			return companyCountDao.getCompanyCountFocusTotal(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 企业关注净增走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> companyCountFocusGrowthCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load company count focus growth cache, key: {}", key);
			String[] arr = key.split(",");
			return companyCountDao.getCompanyCountGrowth(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 企业取消关注走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> companyCountUnFocusCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load company count unfocus cache, key: {}", key);
			String[] arr = key.split(",");
			return companyCountDao.getCompanyCountUnFocus(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 获取参与企业性别统计
	 * @param companyId 企业id
	 */
	public Map<String, Object> getCompanyCountSex(long companyId) {
		try {
			return companyCountSexCache.get(companyId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取参与企业实名认证统计
	 * @param companyId 企业id
	 */
	public Map<String, Object> getCompanyCountProve(long companyId) {
		try {
			return companyCountProveCache.get(companyId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取参与企业年龄段统计
	 * @param companyId 企业id
	 */
	public Map<String, Object> getCompanyCountAge(long companyId) {
		try {
			return companyCountAgeCache.get(companyId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取企业关注统计
	 * @param companyId 企业id
	 */
	public CompanyCountResult getCompanyCountFocus(long companyId) {
		try {
			return companyCountFocusCache.get(companyId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取企业用户省份统计
	 * @param companyId 企业id
	 */
	public Map<String, Object> getCompanyCountProvince(long companyId) {
		try {
			return companyCountProvinceCache.get(companyId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取企业用户省份统计
	 * @param companyId 企业id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCompanyCountProvince(long companyId, int page, int pageSize) {
		Map<String, Object> data = getCompanyCountProvince(companyId);
		Map<String, Object> encodeMap = JSONObject.decode(JSONObject.encode(data), new TypeToken<Map<String, Object>>() {
		}.getType());
		List<Map<String, Object>> list = (List<Map<String, Object>>) encodeMap.get("list");
		List<Map<String, Object>> result = new ArrayList<>(pageSize);
		int begin = (page - 1) * pageSize;
		int end = begin + pageSize - 1;
		int size = list.size();
		for (int i = begin; i < end; i++) {
			if (i >= size) {
				break;
			}
			result.add(list.get(i));
		}
		encodeMap.put("list", result);
		return encodeMap;
	}

	/**
	 * 获取企业用户城市统计
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public Map<String, Object> getCompanyCountCity(long companyId) {
		try {
			return companyCountCityCache.get(companyId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取企业用户城市统计
	 * @author ruan 
	 * @param companyId 企业id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCompanyCountCity(long companyId, int page, int pageSize) {
		Map<String, Object> data = getCompanyCountCity(companyId);
		Map<String, Object> encodeMap = JSONObject.decode(JSONObject.encode(data), new TypeToken<Map<String, Object>>() {
		}.getType());
		List<Map<String, Object>> list = (List<Map<String, Object>>) encodeMap.get("list");
		List<Map<String, Object>> result = new ArrayList<>(pageSize);
		int begin = (page - 1) * pageSize;
		int end = begin + pageSize - 1;
		int size = list.size();
		for (int i = begin; i < end; i++) {
			if (i >= size) {
				break;
			}
			result.add(list.get(i));
		}
		encodeMap.put("list", result);
		return encodeMap;
	}

	/**
	 * 企业参与关注走势
	 * @author ruan 
	 * @param companyId 企业id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountFocusNew(long companyId, int st, int et) {
		try {
			return companyCountFocusNewCache.get(companyId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 企业点子数走势
	 * @author ruan 
	 * @param companyId 企业id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountIdea(long companyId, int st, int et) {
		try {
			return companyCountIdeaCache.get(companyId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 企业关注总数走势
	 * @author ruan 
	 * @param companyId 企业id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountFocusTotal(long companyId, int st, int et) {
		try {
			return companyCountFocusTotalCache.get(companyId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 专案净增关注走势
	 * @author ruan 
	 * @param companyId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountGrowth(long companyId, int st, int et) {
		try {
			return companyCountFocusGrowthCache.get(companyId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 专案取消关注走势
	 * @author ruan 
	 * @param companyId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCompanyCountUnFocus(long companyId, int st, int et) {
		try {
			return companyCountUnFocusCache.get(companyId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
}