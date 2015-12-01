package com.gu.dao.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;
import com.gu.core.util.SerialUtil;

/**
 * 专案自动过期
 * @author ruan
 */
@Pojo
@Message
public class DTmpCaseExpireQueue extends DSuper implements Delayed {
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 专案结束时间
	 */
	private int endTime;

	public int compareTo(Delayed o) {
		if (o == null) {
			return 1;
		}
		if (o == this) {
			return 0;
		}
		if (o instanceof DTmpCaseExpireQueue) {
			DTmpCaseExpireQueue companyCase = (DTmpCaseExpireQueue) o;
			if (endTime > companyCase.getEndTime()) {
				return 1;
			} else if (endTime == companyCase.getEndTime()) {
				return 0;
			} else {
				return -1;
			}
		}
		long diff = getDelay(null) - o.getDelay(null);
		return diff > 0 ? 1 : diff == 0 ? 0 : -1;
	}

	public long getDelay(TimeUnit unit) {
		return endTime * 1000L - System.currentTimeMillis();
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public final static DTmpCaseExpireQueue valueOf(byte[] data) {
		return SerialUtil.fromBytes(data, DTmpCaseExpireQueue.class);
	}
}