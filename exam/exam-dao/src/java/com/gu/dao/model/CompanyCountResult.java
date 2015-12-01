package com.gu.dao.model;

/**
 * 企业关注统计总况
 * @author ruan
 */
public class CompanyCountResult {
	/**
	 * 今天关注数
	 */
	private int todayFocus;
	/**
	 * 昨天关注数
	 */
	private int yesterdayFocus;
	/**
	 * 今天关注增长率
	 */
	private double todayFocusGrowthRate;
	/**
	 * 今天取消关注数
	 */
	private int todayUnfocus;
	/**
	 * 昨天取消关注数
	 */
	private int yesterdayUnfocus;
	/**
	 * 今天取消关注增长率
	 */
	private double todayUnfocusGrowthRate;
	/**
	 * 今天关注总数
	 */
	private int todayTotalFocus;
	/**
	 * 昨天关注总数
	 */
	private int yesterdayTotalFocus;
	/**
	 * 今天关注总数增长率
	 */
	private double todayTotalFocusGrowthRate;
	/**
	 * 今天关注净增数
	 */
	private int todayGrowth;
	/**
	 * 昨天关注净增数
	 */
	private int yesterdayGrowth;
	/**
	 * 今天净增数数增长率
	 */
	private double todayGrowthRate;
	/**
	 * 今天新增点子数
	 */
	private int todayIdeaNum;
	/**
	 * 昨天新增点子数
	 */
	private int yesterdayIdeaNum;
	/**
	 * 今天新增点子数增长率
	 */
	private double todayIdeaNumGrowthRate;

	public int getTodayFocus() {
		return todayFocus;
	}

	public void setTodayFocus(int todayFocus) {
		this.todayFocus = todayFocus;
	}

	public double getTodayFocusGrowthRate() {
		return todayFocusGrowthRate;
	}

	public void setTodayFocusGrowthRate(double todayFocusGrowthRate) {
		this.todayFocusGrowthRate = todayFocusGrowthRate;
	}

	public int getTodayUnfocus() {
		return todayUnfocus;
	}

	public void setTodayUnfocus(int todayUnfocus) {
		this.todayUnfocus = todayUnfocus;
	}

	public double getTodayUnfocusGrowthRate() {
		return todayUnfocusGrowthRate;
	}

	public void setTodayUnfocusGrowthRate(double todayUnfocusGrowthRate) {
		this.todayUnfocusGrowthRate = todayUnfocusGrowthRate;
	}

	public int getYesterdayFocus() {
		return yesterdayFocus;
	}

	public void setYesterdayFocus(int yesterdayFocus) {
		this.yesterdayFocus = yesterdayFocus;
	}

	public int getYesterdayUnfocus() {
		return yesterdayUnfocus;
	}

	public void setYesterdayUnfocus(int yesterdayUnfocus) {
		this.yesterdayUnfocus = yesterdayUnfocus;
	}

	public int getTodayTotalFocus() {
		return todayTotalFocus;
	}

	public void setTodayTotalFocus(int todayTotalFocus) {
		this.todayTotalFocus = todayTotalFocus;
	}

	public int getYesterdayTotalFocus() {
		return yesterdayTotalFocus;
	}

	public void setYesterdayTotalFocus(int yesterdayTotalFocus) {
		this.yesterdayTotalFocus = yesterdayTotalFocus;
	}

	public double getTodayTotalFocusGrowthRate() {
		return todayTotalFocusGrowthRate;
	}

	public void setTodayTotalFocusGrowthRate(double todayTotalFocusGrowthRate) {
		this.todayTotalFocusGrowthRate = todayTotalFocusGrowthRate;
	}

	public int getTodayGrowth() {
		return todayGrowth;
	}

	public void setTodayGrowth(int todayGrowth) {
		this.todayGrowth = todayGrowth;
	}

	public int getYesterdayGrowth() {
		return yesterdayGrowth;
	}

	public void setYesterdayGrowth(int yesterdayGrowth) {
		this.yesterdayGrowth = yesterdayGrowth;
	}

	public double getTodayGrowthRate() {
		return todayGrowthRate;
	}

	public void setTodayGrowthRate(double todayGrowthRate) {
		this.todayGrowthRate = todayGrowthRate;
	}

	public int getTodayIdeaNum() {
		return todayIdeaNum;
	}

	public void setTodayIdeaNum(int todayIdeaNum) {
		this.todayIdeaNum = todayIdeaNum;
	}

	public int getYesterdayIdeaNum() {
		return yesterdayIdeaNum;
	}

	public void setYesterdayIdeaNum(int yesterdayIdeaNum) {
		this.yesterdayIdeaNum = yesterdayIdeaNum;
	}

	public double getTodayIdeaNumGrowthRate() {
		return todayIdeaNumGrowthRate;
	}

	public void setTodayIdeaNumGrowthRate(double todayIdeaNumGrowthRate) {
		this.todayIdeaNumGrowthRate = todayIdeaNumGrowthRate;
	}

}