package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

/**
 * app版本号返回协议
 * @author ruan
 */
public class AppVersionResponse extends SResponse {
	/**
	 * app版本号
	 */
	private int appVersion;

	public int getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(int appVersion) {
		this.appVersion = appVersion;
	}
}