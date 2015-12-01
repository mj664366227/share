package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 积分商城商品
 * @author ruan
 */
@Pojo
@Message
public class DShopGoods extends DSuper {
	/**
	 * 商品id
	 */
	private long id;
	/**
	 * 商品名字
	 */
	private String name;
	/**
	 * 商品简介
	 */
	private String introduce;
	/**
	 * 兑换分数
	 */
	private int score;
	/**
	 * 数量
	 */
	private int num;
	/**
	 * 分类
	 */
	private String type;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 商家
	 */
	private long companyId;
	/**
	 * 添加时间
	 */
	private int createTime;
	/**
	 * 图片1
	 */
	private String image1;

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

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}
}