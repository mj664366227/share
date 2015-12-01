package com.exam.core.protocol;

import java.util.HashSet;
import java.util.Set;

import com.exam.core.interfaces.SResponse;

/**
 * 一句话亮身份例子返回协议
 * @author luo
 */
public class UserIdentityResponse extends SResponse {
	/**
	 * 结果列表
	 */
	private Set<String> set = new HashSet<String>();

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

}
