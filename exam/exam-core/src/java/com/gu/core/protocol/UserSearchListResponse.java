package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.base.UserBaseResponse;

/**
 * 用户搜索返回协议
 * @author luo
 */
public class UserSearchListResponse extends SResponse {
	/**
	 * 结果列表
	 */
	private List<UserBaseResponse> list = new ArrayList<UserBaseResponse>();

	public List<UserBaseResponse> getList() {
		return list;
	}

	public void setList(List<UserBaseResponse> list) {
		this.list = list;
	}

}
