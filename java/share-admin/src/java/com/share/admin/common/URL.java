package com.share.admin.common;

/**
 * url定义(不要/user/login这种定义方式，直接用/userlogin，就是一层过，这样可以避免好多问题)
 */
public final class URL {
	/**
	 * 登录
	 */
	public final static String UserLogin = "/userlogin";
	/**
	 * 登出
	 */
	public final static String UserLogout = "/userlogout";
	/**
	 * 首页
	 */
	public final static String Index = "/index";
}