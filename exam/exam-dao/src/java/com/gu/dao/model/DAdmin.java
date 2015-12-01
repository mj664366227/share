package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 管理员
 * @author ruan
 */
@Pojo
@Message
public class DAdmin extends DSuper {
	/**
	 * 管理员id
	 */
	private long id;
	/**
	 * 管理员登录名
	 */
	private String nickname = "";
	/**
	 * 管理员密码
	 */
	private String password = "";
	/**
	 * 创建时间
	 */
	private int createTime;
	/**
	 * 最后登录时间
	 */
	private int lastLoginTime;
	/**
	 * 最后登录ip
	 */
	private long lastLoginIp;
	/**
	 * 权限列表
	 */
	private String rank = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public long getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(long lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
}
