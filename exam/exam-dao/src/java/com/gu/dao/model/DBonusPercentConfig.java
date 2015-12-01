package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 分红百分比表
 * @author luo
 */
@Pojo
@Message
public class DBonusPercentConfig extends DSuper {
	/**
	 * id
	 */
	private long id;
	/**
	 * 分红名称
	 */
	private String name;
	/**
	 * 分红背包
	 */
	private int percent;
	/**
	 * 平均每人上限(G点)(-1为没上限)
	 */
	private int pointsMax;
	/**
	 * 分红备注说明
	 */
	private String remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public int getPointsMax() {
		return pointsMax;
	}

	public void setPointsMax(int pointsMax) {
		this.pointsMax = pointsMax;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
