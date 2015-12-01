package com.exam.core.protocol.msg.company;

import com.exam.core.protocol.msg.msgbody.CompanyMessageBody;

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
