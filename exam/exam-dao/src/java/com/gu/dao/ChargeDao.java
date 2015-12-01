package com.gu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.enums.ChargeChannel;
import com.gu.core.util.Time;
import com.gu.dao.db.GuLogDbService;

@Component
public class ChargeDao {
	@Autowired
	private GuLogDbService guLogDbService;

	/**
	 * 记录充值流水
	 * @author ruan 
	 * @param orderId 订单号
	 * @param companyId 企业id
	 * @param points 充值点数
	 * @param chargeChannel 充值渠道
	 */
	public void addChargeLog(long orderId, long companyId, int points, ChargeChannel chargeChannel) {
		String sql = "INSERT INTO `charge_log`(`order_id`, `company_id`, `points`, `channel`, `status`, `create_time`) VALUES (?,?,?,?,?,?)";
		guLogDbService.update(sql, orderId, companyId, points, chargeChannel.getValue(), 0, Time.now());
	}

	/**
	 * 更新订单状态
	 * @author ruan 
	 * @param orderId
	 */
	public void updateChargeStatus(long orderId) {
		String sql = "update `charge_log` set `status`=1 where `order_id`=?";
		guLogDbService.update(sql, orderId);
	}
}