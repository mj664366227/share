package com.gu.service.user;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.redis.KeyFactory;
import com.gu.core.redis.Redis;
import com.gu.core.util.SerialUtil;
import com.gu.dao.ThirdPartyDao;
import com.gu.dao.model.DWechatOpen;

@Component
public class ThirdPartyService {
	@Autowired
	private Redis redis;

	@Autowired
	private ThirdPartyDao thirdPartyDao;

	public DWechatOpen getWechatByOpenId(String openId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.wechatOpenKey(openId).getBytes());
		if (b == null || b.length <= 0) {
			DWechatOpen wechatOpen = thirdPartyDao.getWechatByOpenId(openId);
			if (wechatOpen == null) {
				return null;
			}
			//写入Redis缓存
			saveWechatOpenToCache(wechatOpen);
			return wechatOpen;
		}
		return SerialUtil.fromBytes(b, DWechatOpen.class);
	}

	public DWechatOpen getWechatByUserId(long userId) {
		//读取Redis缓存
		byte[] b = redis.STRINGS.get(KeyFactory.wechatOpenKey(userId).getBytes());
		if (b == null || b.length <= 0) {
			DWechatOpen wechatOpen = thirdPartyDao.getWechatByUserId(userId);
			if (wechatOpen == null) {
				return null;
			}
			//写入Redis缓存
			saveWechatOpenToCache(wechatOpen);
			return wechatOpen;
		}
		return SerialUtil.fromBytes(b, DWechatOpen.class);
	}

	public void delWechatOpen(DWechatOpen wechatOpen){
		redis.KEYS.del(KeyFactory.wechatOpenKey(wechatOpen.getOpenId()));
		redis.KEYS.del(KeyFactory.wechatOpenKey(wechatOpen.getUserId()));
		thirdPartyDao.delWechatOpen(wechatOpen);
	}

	public DWechatOpen addWechatOpen(long userId, String openId) {
		DWechatOpen wechatOpen = thirdPartyDao.addWechatOpen(userId, openId);
		if (wechatOpen == null) {
			return null;
		}
		saveWechatOpenToCache(wechatOpen);
		return wechatOpen;
	}

	private void saveWechatOpenToCache(DWechatOpen wechatOpen) {
		byte[] b = SerialUtil.toBytes(wechatOpen);
		redis.STRINGS.setex(KeyFactory.wechatOpenKey(wechatOpen.getOpenId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), b);
		redis.STRINGS.setex(KeyFactory.wechatOpenKey(wechatOpen.getUserId()).getBytes(), (int) TimeUnit.DAYS.toSeconds(30), b);
	}
}