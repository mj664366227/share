package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

/**
 * 吐槽列表返回协议
 * @author ruan
 */
public class FlowListResponse extends SResponse {
	/**
	 * 吐槽总数
	 */
	private int total = 0;
	/**
	 * 吐槽列表
	 */
	private List<FlowResponse> flowResponseList = new ArrayList<FlowResponse>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<FlowResponse> getFlowResponseList() {
		return flowResponseList;
	}

	public void addFlowResponse(FlowResponse flowResponse) {
		this.flowResponseList.add(flowResponse);
	}

	public void setFlowResponseList(List<FlowResponse> flowResponseList) {
		this.flowResponseList = flowResponseList;
	}
}