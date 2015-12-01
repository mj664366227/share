package com.gu.service.system;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.gu.core.common.URLCommand;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.MathUtil;
import com.gu.core.util.StringUtil;
import com.gu.core.util.Time;
import com.gu.dao.SystemDao;
import com.gu.dao.model.DCase;
import com.gu.dao.model.DCompany;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DSystem;
import com.gu.dao.model.DUser;

@Component
public class SystemService {
	/**
	* logger
	*/
	private final static Logger logger = LoggerFactory.getLogger(SystemService.class);
	@Autowired
	private SystemDao systemDao;
	@Autowired
	private Redis redis;
	@Autowired
	private GuThreadPool guThreadPool;

	/**
	 * 系统配置缓存
	 */
	private LoadingCache<Integer, DSystem> systemConfig = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build(new CacheLoader<Integer, DSystem>() {
		public DSystem load(Integer key) throws Exception {
			logger.warn("load system config");
			return systemDao.getSystemConfig();
		}
	});

	/**
	 * 初始化系统在配置
	 */
	@PostConstruct
	public void init() {
		systemConfig.invalidateAll();
		getSystemConfig();
	}

	/**
	 * 获取系统配置
	 */
	public DSystem getSystemConfig() {
		try {
			return systemConfig.get(1);
		} catch (Exception e) {
			logger.warn("", e);
		}
		return null;
	}

	/**
	 * 统计用户在线
	 * @param userId 用户id
	 * @author ruan
	 */
	public void recordUserOnline(final long userId) {
		if (userId <= 0) {
			return;
		}
		guThreadPool.execute(() -> {
			// 写入redis
			String time = Time.date("yyyyMMddHHmm");
			String key = KeyFactory.onlineUserKey(time);
			redis.SORTSET.zadd(key, userId, String.valueOf(userId));
			redis.KEYS.expire(key, 300); // 保留5分钟
		});
	}

	/**
	* 统计企业在线
	* @param companyId 企业id
	* @param url
	* @author ruan
	*/
	public void recordCompanyOnline(final long companyId, final String url) {
		if (companyId <= 0) {
			return;
		}
		if (url.indexOf(".") > -1) {
			return;
		}
		if (URLCommand.ajax_message_num.equals(url)) {
			return;
		}
		if (url.startsWith(URLCommand.check_order.substring(0, URLCommand.check_order.indexOf("/{")))) {
			return;
		}
		guThreadPool.execute(() -> {
			// 写入redis
			String time = Time.date("yyyyMMddHHmm");
			String key = KeyFactory.onlineCompanyKey(time);
			redis.SORTSET.zadd(key, companyId, String.valueOf(companyId));
			redis.KEYS.expire(key, 300);// 保留5分钟
		});
	}

	/**
	 * 保存用户在线数据(每分钟跑一次)
	 * @author ruan
	 */
	public void saveUserOnline() {
		// 获取最近3分钟的，保证数据正确
		int now = Time.now();
		for (int i = 1; i <= 3; i++) {
			String date = Time.date("yyyyMMddHHmm", now - (i * 60));
			long num = redis.SORTSET.zcard(KeyFactory.onlineUserKey(date));
			systemDao.saveUserOnline(date, num);
			logger.warn("{} user online {}", date, MathUtil.numberFormat(num));
		}
	}

	/**
	 * 保存企业在线数据(每分钟跑一次)
	 * @author ruan
	 */
	public void saveCompanyOnline() {
		// 获取最近3分钟的，保证数据正确
		int now = Time.now();
		for (int i = 1; i <= 3; i++) {
			String date = Time.date("yyyyMMddHHmm", now - (i * 60));
			long num = redis.SORTSET.zcard(KeyFactory.onlineCompanyKey(date));
			systemDao.saveCompanyOnline(date, num);
			logger.warn("{} company online {}", date, MathUtil.numberFormat(num));
		}
	}

	/**
	 * 清空一周前的用户在线数据(每天执行一次)
	 * @author ruan
	 */
	public void clearUserOnline() {
		long date = StringUtil.getLong(Time.date("yyyyMMdd", Time.now() - 86400 * 3) + "0000");
		systemDao.clearUserOnline(date);
	}

	/**
	 * 清空一周前的企业在线数据(每天执行一次)
	 * @author ruan
	 */
	public void clearCompanyOnline() {
		long date = StringUtil.getLong(Time.date("yyyyMMdd", Time.now() - 86400 * 3) + "0000");
		systemDao.clearCompanyOnline(date);
	}

	/**
	 * 修改系统配置
	 * @param system
	 */
	public void upgateSystemConfig(DSystem system) {
		systemDao.upgateSystemConfig(system);
		init();
	}

	public List<DCompany> getCompanyList() {
		return systemDao.getCompanyList();
	}

	public int getAllCaseNumByCompanyId(long companyId) {
		return systemDao.getAllCaseNumByCompanyId(companyId);
	}

	public int getOverCaseNumByCompanyId(long companyId) {
		return systemDao.getOverCaseNumByCompanyId(companyId);
	}

	public int getFansNumByCompanyId(long companyId) {
		return systemDao.getFansNumByCompanyId(companyId);
	}

	public void update_company_count_focus_total(long companyId, int num) {
		systemDao.update_company_count_focus_total(companyId, num);
	}

	public List<DIdea> getIdeaList() {
		return systemDao.getIdeaList();
	}

	public List<DCase> getCaseList() {
		return systemDao.getCaseList();
	}

	public int getIdeaCommentNum(long ideaId) {
		return systemDao.getIdeaCommentNum(ideaId);
	}

	public int getCountPointsByUserId(long userId) {
		return systemDao.getCountPointsByUserId(userId);
	}

	public List<DUser> getUserList() {
		return systemDao.getUserList();
	}

	public void repairUserInfo() {
		systemDao.repairUserInfo();
	}
}