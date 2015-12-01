package com.gu.service.cases;

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
import com.gu.dao.CaseCountDao;
import com.gu.dao.model.CaseCountResult;

@Component
public class CaseCountService {
	private final static Logger logger = LoggerFactory.getLogger(CaseCountService.class);
	@Autowired
	private CaseCountDao caseCountDao;
	/**
	 * 缓存时间
	 */
	private final static int cacheDuration = 5;
	/**
	 * 缓存时间单位 
	 */
	private final static TimeUnit cacheUnit = SystemUtil.isProduct() ? TimeUnit.MINUTES : TimeUnit.MILLISECONDS;
	/**
	 * 专案性别统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> caseCountSexCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long caseId) throws Exception {
			logger.warn("load case count sex, caseId: {}", caseId);
			return caseCountDao.getCaseCountSex(caseId);
		}
	});

	/**
	 * 专案实名认证统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> caseCountProveCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long caseId) throws Exception {
			logger.warn("load case count prove cache, caseId: {}", caseId);
			return caseCountDao.getCaseCountProve(caseId);
		}
	});

	/**
	 * 专案年龄段统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> caseCountAgeCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long caseId) throws Exception {
			logger.warn("load case count age cache, caseId: {}", caseId);
			return caseCountDao.getCaseCountAge(caseId);
		}
	});

	/**
	 * 专案关注统计缓存
	 */
	private LoadingCache<Long, CaseCountResult> caseCountFocusCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, CaseCountResult>() {
		public CaseCountResult load(Long caseId) throws Exception {
			logger.warn("load case count focus cache, caseId: {}", caseId);
			return caseCountDao.getCaseCountResult(caseId);
		}
	});

	/**
	 * 专案用户省份统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> caseCountProvinceCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long caseId) throws Exception {
			logger.warn("load case count province cache, caseId: {}", caseId);
			return caseCountDao.getCaseCountProvince(caseId, 1, 999);
		}
	});

	/**
	 * 专案用户城市统计缓存
	 */
	private LoadingCache<Long, Map<String, Object>> caseCountCityCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<Long, Map<String, Object>>() {
		public Map<String, Object> load(Long caseId) throws Exception {
			logger.warn("load case count city cache, caseId: {}", caseId);
			return caseCountDao.getCaseCountCity(caseId, 1, 999);
		}
	});

	/**
	 * 点子数走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> caseCountIdeaCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load case count idea cache, key: {}", key);
			String[] arr = key.split(",");
			return caseCountDao.getCaseCountIdea(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});
	
	/**
	 * 点子总数走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> caseCountIdeaTotalCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load case count idea total cache, key: {}", key);
			String[] arr = key.split(",");
			return caseCountDao.getCaseCountIdeaTotal(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 新增关注走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> caseCountFocusNewCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load case count focus new cache, key: {}", key);
			String[] arr = key.split(",");
			return caseCountDao.getCaseCountFocusNew(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 专案关注总数走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> caseCountFocusTotalCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load case count focus total cache, key: {}", key);
			String[] arr = key.split(",");
			return caseCountDao.getCaseCountFocusTotal(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 专案取消关注走势缓存
	 */
	private LoadingCache<String, Map<String, Object>> caseCountUnFocusCache = CacheBuilder.newBuilder().expireAfterWrite(cacheDuration, cacheUnit).build(new CacheLoader<String, Map<String, Object>>() {
		public Map<String, Object> load(String key) throws Exception {
			logger.warn("load case count unfocus total cache, key: {}", key);
			String[] arr = key.split(",");
			return caseCountDao.getCaseCountUnFocus(StringUtil.getLong(arr[0]), StringUtil.getInt(arr[1]), StringUtil.getInt(arr[2]));
		}
	});

	/**
	 * 获取参与专案性别统计
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountSex(long caseId) {
		try {
			return caseCountSexCache.get(caseId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取参与专案实名认证统计
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountProve(long caseId) {
		try {
			return caseCountProveCache.get(caseId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取参与专案年龄段统计
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountAge(long caseId) {
		try {
			return caseCountAgeCache.get(caseId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取专案关注统计
	 * @param caseId 专案id
	 */
	public CaseCountResult getCaseCountFocus(long caseId) {
		try {
			return caseCountFocusCache.get(caseId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取专案用户省份统计
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountProvince(long caseId) {
		try {
			return caseCountProvinceCache.get(caseId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取专案用户省份统计
	 * @param caseId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCaseCountProvince(long caseId, int page, int pageSize) {
		Map<String, Object> data = getCaseCountProvince(caseId);
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
	 * 获取专案用户城市统计
	 * @author ruan 
	 * @param caseId 专案id
	 */
	public Map<String, Object> getCaseCountCity(long caseId) {
		try {
			return caseCountCityCache.get(caseId);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取专案用户城市统计
	 * @author ruan 
	 * @param caseId 专案id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCaseCountCity(long caseId, int page, int pageSize) {
		Map<String, Object> data = getCaseCountCity(caseId);
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
	 * 专案关注走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountFocusNew(long caseId, int st, int et) {
		try {
			return caseCountFocusNewCache.get(caseId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 专案取消关注走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountUnFocus(long caseId, int st, int et) {
		try {
			return caseCountUnFocusCache.get(caseId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 专案关注总数走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountFocusTotal(long caseId, int st, int et) {
		try {
			return caseCountFocusTotalCache.get(caseId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 专案点子数走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountIdea(long caseId, int st, int et) {
		try {
			return caseCountIdeaCache.get(caseId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	/**
	 * 专案点子总数走势
	 * @author ruan 
	 * @param caseId 专案id
	 * @param st 开始时间戳
	 * @param et 结束时间戳
	 */
	public Map<String, Object> getCaseCountIdeaTotal(long caseId, int st, int et) {
		try {
			return caseCountIdeaTotalCache.get(caseId + "," + st + "," + et);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
}