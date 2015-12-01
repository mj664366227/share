package com.gu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.util.Time;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DWechatOpen;

@Component
public class ThirdPartyDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;

	public DWechatOpen getWechatByOpenId(String openId) {
		return guSlaveDbService.queryT("SELECT * FROM `wechat_open` WHERE `open_id`=?", DWechatOpen.class, openId);
	}

	public DWechatOpen getWechatByUserId(long userId) {
		return guSlaveDbService.queryT("SELECT * FROM `wechat_open` WHERE `user_id`=?", DWechatOpen.class, userId);
	}

	public DWechatOpen addWechatOpen(long userId, String openId) {
		DWechatOpen wechatOpen = new DWechatOpen();
		wechatOpen.setUserId(userId);
		wechatOpen.setOpenId(openId);
		wechatOpen.setCreateTime(Time.now());
		return guMasterDbService.save(wechatOpen);
	}

	public void delWechatOpen(DWechatOpen wechatOpen) {
		guMasterDbService.delete(wechatOpen);
	}

}
