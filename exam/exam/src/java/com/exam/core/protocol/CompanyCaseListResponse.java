package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

public class CompanyCaseListResponse extends SResponse {
	/**
	 * 企业logo
	 */
	private String logo;
	/**
	 * 企业名称
	 */
	private String name;
	/**
	 * 企业简介(描述)
	 */
	private String description;
	/**
	 * 总条数
	 */
	private Integer total;
	/**
	 * 专案列表
	 */
	private List<CompanyCase> list = new ArrayList<CompanyCase>();

	public List<CompanyCase> getList() {
		return list;
	}

	public void addList(CompanyCase companyCase) {
		list.add(companyCase);
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}