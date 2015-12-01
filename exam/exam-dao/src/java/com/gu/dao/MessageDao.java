package com.gu.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.gu.core.nsq.protocol.MessageNSQ;
import com.gu.core.util.Time;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DCompanyMessage;
import com.gu.dao.model.DUserMessage;

/**
 * 消息dao
 * @author luo
 */
@Component
public class MessageDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;

	/**
	 * 保存用户消息
	 * @param messageUser
	 * @return
	 */
	public DUserMessage addUserMessage(MessageNSQ messageNSQ) {
		DUserMessage userMessage = new DUserMessage();
		userMessage.setType(messageNSQ.getMessageSign() % 2000000 / 1000);
		userMessage.setSign(messageNSQ.getMessageSign());
		userMessage.setUserId(messageNSQ.getReceiverId());
		userMessage.setSenderId(messageNSQ.getSenderId());
		userMessage.setData(messageNSQ.getData());
		userMessage.setBody(messageNSQ.getBody());
		userMessage.setCreateTime(Time.now());
		return guMasterDbService.save(userMessage);
	}

	/**
	 * 用户收到的某类型消息列表
	 * @param userId 接收人id
	 * @param typeORsign 消息类型
	 * @return
	 */
	public List<DUserMessage> getUserMessageList(long userId, int typeORsign, long lastUserMessageId, int pageSize) {
		String ts = typeORsign < 100 ? "type" : "sign";
		String sql = "SELECT * FROM `user_message` WHERE `user_id`=? and `" + ts + "`=? and `id`<=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DUserMessage.class, userId, typeORsign, lastUserMessageId, pageSize);
	}

	/**
	 * 批量获取
	 * @param tmpUserMessageIdSet id集合
	 * @return
	 */
	public List<DUserMessage> getUserMessage(Set<Long> userMessageIdSet) {
		return guSlaveDbService.multiGetT(userMessageIdSet, DUserMessage.class);
	}

	/**
	 * 
	 * @param userMessageId
	 * @return
	 */
	public DUserMessage getUserMessageById(long userMessageId) {
		String sql = "SELECT * FROM `user_message` WHERE `id`=?";
		return guSlaveDbService.queryT(sql, DUserMessage.class, userMessageId);
	}

	public void delUserMessage(DUserMessage userMessage) {
		guMasterDbService.delete(userMessage);
	}

	/**
	 * 保存企业消息
	 * @param companyMessage
	 * @return
	 */
	public DCompanyMessage addCompanyMessage(MessageNSQ messageNSQ) {
		DCompanyMessage companyMessage = new DCompanyMessage();
		companyMessage.setType(messageNSQ.getMessageSign() % 3000000 / 1000);
		companyMessage.setSign(messageNSQ.getMessageSign());
		companyMessage.setCompanyId(messageNSQ.getReceiverId());
		companyMessage.setSenderId(messageNSQ.getSenderId());
		companyMessage.setData(messageNSQ.getData());
		companyMessage.setBody(messageNSQ.getBody());
		companyMessage.setCreateTime(messageNSQ.getCreateTime());
		return guMasterDbService.save(companyMessage);
	}

	/**
	 * 企业收到的某类型消息列表
	 * @param userId 接收人id
	 * @param typeORsign 消息类型
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 * @return
	 */
	public List<DCompanyMessage> getCompanyMessageList(long companyId, int typeORsign, long page, int pageSize) {
		String ts = typeORsign < 100 ? "type" : "sign";
		String sql = "SELECT * FROM `company_message` WHERE `company_id`=? and `" + ts + "`=? order by `id` desc limit ?,?";
		return guSlaveDbService.queryList(sql, DCompanyMessage.class, companyId, typeORsign, (page - 1) * pageSize, pageSize);
	}

	/**
	 * 批量获取
	 * @param tmpCompanyMessageIdSet id集合
	 * @return
	 */
	public List<DCompanyMessage> getCompanyMessage(Set<Long> tmpCompanyMessageIdSet) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from `company_message` where `id` in (");
		sql.append(Joiner.on(",").join(tmpCompanyMessageIdSet));
		sql.append(") order by `id` desc");
		return guSlaveDbService.queryList(sql.toString(), DCompanyMessage.class);
	}

	/**
	 * 记录企业消息数量
	 * @author ruan 
	 * @param companyId 企业id
	 * @param messageType 消息大类型
	 * @param messageSign 消息小类型
	 */
	public boolean recordCompanyMessageNum(long companyId, int messageType, int messageSign) {
		String sql = "INSERT INTO `company_message_num`(`company_id`, `message_type`, `message_sign`, `num`) VALUES (?,?,?,?)";
		return guMasterDbService.update(sql, companyId, messageType, messageSign, 1);
	}

	/**
	 * 更新企业消息数量
	 * @author ruan 
	 * @param companyId 企业id
	 * @param messageType 消息大类型
	 * @param messageSign 消息小类型
	 * @param num 数量
	 */
	public boolean updateCompanyMessageNum(long companyId, int messageType, int messageSign, int num) {
		String sql = "update `company_message_num` set `num`=? where `company_id`=? and `message_type`=? and `message_sign`=?";
		return guMasterDbService.update(sql, num, companyId, messageType, messageSign);
	}

	/**
	 * 记录企业消息数量
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public List<Map<String, Object>> getCompanyMessageNum(long companyId) {
		String sql = "select * from `company_message_num` where `company_id`=?";
		return guSlaveDbService.queryList(sql, companyId);
	}

	/**
	 * 清空企业消息数量
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public void clearCompanyMessageNum(long companyId, int typeORsign) {
		String ts = typeORsign < 100 ? "message_type" : "message_sign";
		String sql = "delete from `company_message_num` where `company_id`=? and `" + ts + "`=?";
		guMasterDbService.update(sql, companyId, typeORsign);
	}

	/**
	 * 记录用户消息数量
	 * @author luo 
	 * @param userId 用户id
	 * @param messageType 消息大类型
	 * @param messageSign 消息小类型
	 */
	public boolean recordUserMessageNum(long userId, int messageType, int messageSign) {
		String sql = "INSERT INTO `user_message_num`(`user_id`, `message_type`, `message_sign`, `num`) VALUES (?,?,?,?)";
		return guMasterDbService.update(sql, userId, messageType, messageSign, 1);
	}

	/**
	 * 更新用户消息数量
	 * @author luo 
	 * @param userId 用户id
	 * @param messageType 消息大类型
	 * @param messageSign 消息小类型
	 * @param num 数量
	 */
	public boolean updateUserMessageNum(long userId, int messageType, int messageSign, int num) {
		String sql = "update `user_message_num` set `num`=? where `user_id`=? and `message_type`=? and `message_sign`=?";
		return guMasterDbService.update(sql, num, userId, messageType, messageSign);
	}

	/**
	 * 记录用户消息数量
	 * @author luo 
	 * @param userId 用户id
	 */
	public List<Map<String, Object>> getUserMessageNum(long userId) {
		String sql = "select * from `user_message_num` where `user_id`=?";
		return guSlaveDbService.queryList(sql, userId);
	}

	/**
	 * 清空用户消息数量
	 * @author luo 
	 * @param userId 用户id
	 */
	public void clearUserMessageNum(long userId, int typeORsign) {
		String ts = typeORsign < 100 ? "message_type" : "message_sign";
		String sql = "delete from `user_message_num` where `user_id`=? and `" + ts + "`=?";
		guMasterDbService.update(sql, userId, typeORsign);
	}
}