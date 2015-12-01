package com.gu.core.protocol.msg.company;

import com.gu.core.protocol.msg.msgbody.CompanyMessageBody;

/**
 * 管理员送G点
 * @author luo
 */
public class AdminSendPointsMessage extends CompanyMessageBody {

	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
