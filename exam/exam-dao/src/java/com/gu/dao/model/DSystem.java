package com.gu.dao.model;

import java.util.Arrays;
import java.util.Set;

import org.msgpack.annotation.Message;

import com.google.common.collect.Sets;
import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 系统参数配置
 * @author ruan
 */
@Pojo
@Message
public class DSystem extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 网站关键字
	 */
	private String keywords = "";
	/**
	 * 网站描述
	 */
	private String description = "";
	/**
	 * G币汇率
	 */
	private int gbExchangeRate;
	/**
	 * 企业每天可以发的专案数
	 */
	private int companyPubCasePerDay;
	/**
	 * app版本
	 */
	private int appVersion;
	/**
	 * 接收短信手机
	 */
	private String smsMobiles = "";
	/**
	 * 最小提现G点
	 */
	private int minCheckoutPoints;
	/**
	 * 测试充值的企业
	 */
	private String testChargeCompany = "";

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGbExchangeRate() {
		return gbExchangeRate;
	}

	public void setGbExchangeRate(int gbExchangeRate) {
		this.gbExchangeRate = gbExchangeRate;
	}

	public int getCompanyPubCasePerDay() {
		return companyPubCasePerDay;
	}

	public void setCompanyPubCasePerDay(int companyPubCasePerDay) {
		this.companyPubCasePerDay = companyPubCasePerDay;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(int appVersion) {
		this.appVersion = appVersion;
	}

	public String getSmsMobiles() {
		return smsMobiles;
	}

	public void setSmsMobiles(String smsMobiles) {
		this.smsMobiles = smsMobiles;
	}

	public int getMinCheckoutPoints() {
		return minCheckoutPoints;
	}

	public void setMinCheckoutPoints(int minCheckoutPoints) {
		this.minCheckoutPoints = minCheckoutPoints;
	}

	public String getTestChargeCompany() {
		return testChargeCompany;
	}

	public Set<String> getTestChargeCompanySet() {
		return Sets.newCopyOnWriteArraySet(Arrays.asList(testChargeCompany.split(";")));
	}

	public void setTestChargeCompany(String testChargeCompany) {
		this.testChargeCompany = testChargeCompany;
	}
}