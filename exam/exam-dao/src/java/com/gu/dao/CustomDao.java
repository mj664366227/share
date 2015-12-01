package com.gu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.util.Time;
import com.gu.dao.db.GuLogDbService;

/**
 * 客服相关
 * @author ruan
 */
@Component
public class CustomDao {
	@Autowired
	private GuLogDbService guLogDbService;

	/**
	 * 企业给客服留言 
	 * @param companyId 企业id
	 * @param content 留言内容
	 */
	public void companyLeaveMessage(long companyId, String content) {
		String sql = "INSERT INTO `company_message`( `company_id`, `content`, `create_time`) VALUES (?,?,?)";
		guLogDbService.update(sql, companyId, content, Time.now());
	}
}