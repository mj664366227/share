package com.exam.core.protocol;

import com.exam.core.enums.FocusStatus;
import com.exam.core.interfaces.SResponse;

/**
 * 用户关注用户动作返回协议
 * @author ruan
 */
public class UserDoFocusResponse extends SResponse {
	/**
	 * 关注状态
	 */
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(FocusStatus status) {
		this.status = status.getStatus();
	}
}