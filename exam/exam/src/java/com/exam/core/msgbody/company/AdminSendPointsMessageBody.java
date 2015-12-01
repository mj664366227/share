package com.exam.core.msgbody.company;

import com.exam.core.interfaces.MessageBody;

public class AdminSendPointsMessageBody extends MessageBody {

	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
