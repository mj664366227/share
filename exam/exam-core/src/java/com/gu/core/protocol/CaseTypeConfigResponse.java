package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

/**
 * 专案类型配置返回协议
 * @author ruan
 */
public class CaseTypeConfigResponse extends SResponse {
	/**
	 * 专案配置列表
	 */
	private List<CaseTypeResponse> list = new ArrayList<CaseTypeResponse>();

	public List<CaseTypeResponse> getList() {
		return list;
	}

	public void setList(List<CaseTypeResponse> list) {
		this.list = list;
	}
}