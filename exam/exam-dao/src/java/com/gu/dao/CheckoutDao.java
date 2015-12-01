package com.gu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.enums.CheckoutType;
import com.gu.core.util.Ip;
import com.gu.core.util.Time;
import com.gu.dao.db.GuLogDbService;

@Component
public class CheckoutDao {
	@Autowired
	private GuLogDbService guLogDbService;

	/**
	 * 记录提现流水
	 * @author ruan 
	 * @param orderId 订单id
	 * @param userId 用户id
	 * @param points 提现G点
	 * @param ip 提现ip
	 * @param checkoutType 提现类型
	 */
	public void addCheckoutLog(long orderId, long userId, int points, String ip, CheckoutType checkoutType) {
		String sql = "INSERT INTO `checkout_log`(`order_id`,`user_id`,`points`,`type`,`status`,`ip`,`return_data`,`create_time`) VALUES (?,?,?,?,?,?,?,?)";
		guLogDbService.update(sql, orderId, userId, points, checkoutType.getValue(), 0, Ip.ip2Long(ip), "", Time.now());
	}

	/**
	 * 更新提现状态
	 * @author ruan 
	 * @param orderId 订单号
	 * @param status 订单状态
	 * @param returnData 腾讯返回的数据
	 */
	public void updateCheckoutStatus(long orderId, boolean status, String returnData) {
		String sql = "update `checkout_log` set `status`=?,`return_data`=? where `order_id`=?";
		guLogDbService.update(sql, status ? 1 : 0, returnData, orderId);
	}
}