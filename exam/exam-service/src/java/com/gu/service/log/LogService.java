package com.gu.service.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.gu.core.enums.OpenPlatform;
import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.threadPool.GuThreadPool;
import com.gu.core.util.Time;
import com.gu.dao.LogDao;
import com.gu.dao.model.DUserFeedback;

@Component
public class LogService {
	@Autowired
	private LogDao logDao;
	@Autowired
	private Redis redis;
	@Autowired
	private GuThreadPool guThreadPool;
	private final static Interner<String> interner = Interners.newWeakInterner();

	/**
	 * 记录闪退日志
	 * @param userId 用户id 
	 * @param version app版本号
	 * @param mobileType 手机机型
	 * @param os 操作系统
	 * @param content 闪退日志
	 */
	public void crashLog(long userId, String version, String mobileType, String os, String content) {
		logDao.crashLog(userId, version, mobileType, os, content);
	}

	/**
	 * app启动次数统计
	 */
	public void recordStart(final long userId) {
		guThreadPool.execute(() -> {
			// 这里的统计数据还是比较重要的，锁一下，对性能影响不大
			synchronized (interner.intern(KeyFactory.userKey(userId))) {
				// 记录启动的次数
				logDao.recordStart();
				int now = Time.now();

				// 记录这个人的启动次数
				logDao.recordStartUserTimes(now, userId);

				// 记录启动人数
				// 判断这个小时有没有记录过
				String keyHH = KeyFactory.startUserListKey(Time.date("yyyyMMddHH", now));
				if (redis.SORTSET.zscore(keyHH, String.valueOf(userId)) <= 0) {
					redis.SORTSET.zadd(keyHH, userId, String.valueOf(userId));
					redis.KEYS.expire(keyHH, 86400);
					logDao.recordStartUser(now);
				}

				// 判断今天有没有记录过
				String key = KeyFactory.startUserListKey(Time.date("yyyyMMdd", now));
				if (redis.SORTSET.zscore(key, String.valueOf(userId)) <= 0) {
					redis.SORTSET.zadd(key, userId, String.valueOf(userId));
					redis.KEYS.expire(key, 86400);
					logDao.recordStartTotalUser(now);
				}
			}
		});
	}

	/**
	 * 查询用户意见反馈列表
	 * @return
	 */
	public List<DUserFeedback> getUserFeedbackList() {
		return logDao.getUserFeedbackList();
	}

	/**
	 * 分享统计
	 * @author ruan 
	 * @param openPlatform 第三方平台
	 */
	public void addShareStatistics(OpenPlatform openPlatform) {
		logDao.addShareStatistics(openPlatform);
	}

	/**
	 * 分享点击数统计
	 * @param openPlatform 第三方平台
	 */
	public void addShareClicksStatistics(OpenPlatform openPlatform) {
		logDao.addShareClicksStatistics(openPlatform);
	}

	/**
	 * 分享下载数统计
	 * @param openPlatform 第三方平台
	 */
	public void addShareDownloadStatistics(OpenPlatform openPlatform) {
		logDao.addShareDownloadStatistics(openPlatform);
	}
}