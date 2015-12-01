package com.gu.service.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.dao.CustomDao;

/**
 * 客服中心
 * @author ruan
 */
@Component
public class CustomService {
	@Autowired
	private CustomDao customDao;

	/**
	 * 企业给客服留言 
	 * @param companyId 企业id
	 * @param content 留言内容
	 */
	public void companyLeaveMessage(long companyId, String content) {
		customDao.companyLeaveMessage(companyId, content);
	}
}
