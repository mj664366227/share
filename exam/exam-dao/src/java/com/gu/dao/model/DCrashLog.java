package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

@Pojo
@Message
public class DCrashLog extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * app版本号
	 */
	private String version;
	/**
	 * 手机机型
	 */
	private String mobileType;
	/**
	 * 操作系统
	 */
	private String os;
	/**
	 * 闪退日志
	 */
	private String content;
	/**
	 * 是否已解决(0-未解决 1-已解决)
	 */
	private int resolve;
	/**
	 * 记录时间	
	 */
	private int createTime;
	/**
	 * 解决时间
	 */
	private int resolveTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getResolve() {
		return resolve;
	}

	public void setResolve(int resolve) {
		this.resolve = resolve;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getResolveTime() {
		return resolveTime;
	}

	public void setResolveTime(int resolveTime) {
		this.resolveTime = resolveTime;
	}
}