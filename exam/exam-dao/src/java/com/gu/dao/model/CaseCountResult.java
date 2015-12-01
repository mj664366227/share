package com.gu.dao.model;

/**
 * 专案统计总况
 * @author ruan
 */
public class CaseCountResult {
	/**
	 * 今天参与数
	 */
	private int todayTakePartIn;
	/**
	 * 昨天参与数
	 */
	private int yesterdayTakePartIn;
	/**
	 * 今天参与增长率
	 */
	private double todayTakePartInGrowthRate;
	/**
	 * 今天总参与数
	 */
	private int todayTotalTakePartIn;
	/**
	 * 昨天总参与数
	 */
	private int yesterdayTotalTakePartIn;
	/**
	 * 今天总参与增长率
	 */
	private double todayTotalTakePartInGrowthRate;
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
	 * 今天累计关注总数
	 */
	private int todayTotalFocus;
	/**
	 * 昨天累计关注总数
	 */
	private int yesterdayTotalFocus;
	/**
	 * 今天累计关注总数增长率
	 */
	private double todayTotalFocusGrowthRate;
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
	/**
	 * 今天累计点子数
	 */
	private int todayIdeaTotalNum;
	/**
	 * 昨天累计点子数
	 */
	private int yesterdayIdeaTotalNum;
	/**
	 * 今天累计点子数增长率
	 */
	private double todayIdeaTotalNumGrowthRate;

	public int getTodayTakePartIn() {
		return todayTakePartIn;
	}

	public void setTodayTakePartIn(int todayTakePartIn) {
		this.todayTakePartIn = todayTakePartIn;
	}

	public int getYesterdayTakePartIn() {
		return yesterdayTakePartIn;
	}

	public void setYesterdayTakePartIn(int yesterdayTakePartIn) {
		this.yesterdayTakePartIn = yesterdayTakePartIn;
	}

	public double getTodayTakePartInGrowthRate() {
		return todayTakePartInGrowthRate;
	}

	public void setTodayTakePartInGrowthRate(double todayTakePartInGrowthRate) {
		this.todayTakePartInGrowthRate = todayTakePartInGrowthRate;
	}

	public int getTodayTotalTakePartIn() {
		return todayTotalTakePartIn;
	}

	public void setTodayTotalTakePartIn(int todayTotalTakePartIn) {
		this.todayTotalTakePartIn = todayTotalTakePartIn;
	}

	public int getYesterdayTotalTakePartIn() {
		return yesterdayTotalTakePartIn;
	}

	public void setYesterdayTotalTakePartIn(int yesterdayTotalTakePartIn) {
		this.yesterdayTotalTakePartIn = yesterdayTotalTakePartIn;
	}

	public double getTodayTotalTakePartInGrowthRate() {
		return todayTotalTakePartInGrowthRate;
	}

	public void setTodayTotalTakePartInGrowthRate(double todayTotalTakePartInGrowthRate) {
		this.todayTotalTakePartInGrowthRate = todayTotalTakePartInGrowthRate;
	}

	public int getTodayFocus() {
		return todayFocus;
	}

	public void setTodayFocus(int todayFocus) {
		this.todayFocus = todayFocus;
	}

	public int getYesterdayFocus() {
		return yesterdayFocus;
	}

	public void setYesterdayFocus(int yesterdayFocus) {
		this.yesterdayFocus = yesterdayFocus;
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

	public int getYesterdayUnfocus() {
		return yesterdayUnfocus;
	}

	public void setYesterdayUnfocus(int yesterdayUnfocus) {
		this.yesterdayUnfocus = yesterdayUnfocus;
	}

	public double getTodayUnfocusGrowthRate() {
		return todayUnfocusGrowthRate;
	}

	public void setTodayUnfocusGrowthRate(double todayUnfocusGrowthRate) {
		this.todayUnfocusGrowthRate = todayUnfocusGrowthRate;
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

	public int getTodayIdeaTotalNum() {
		return todayIdeaTotalNum;
	}

	public void setTodayIdeaTotalNum(int todayIdeaTotalNum) {
		this.todayIdeaTotalNum = todayIdeaTotalNum;
	}

	public int getYesterdayIdeaTotalNum() {
		return yesterdayIdeaTotalNum;
	}

	public void setYesterdayIdeaTotalNum(int yesterdayIdeaTotalNum) {
		this.yesterdayIdeaTotalNum = yesterdayIdeaTotalNum;
	}

	public double getTodayIdeaTotalNumGrowthRate() {
		return todayIdeaTotalNumGrowthRate;
	}

	public void setTodayIdeaTotalNumGrowthRate(double todayIdeaTotalNumGrowthRate) {
		this.todayIdeaTotalNumGrowthRate = todayIdeaTotalNumGrowthRate;
	}

	public int getTodayIdeaNum() {
		return todayIdeaNum;
	}

	public void setTodayTotalFocusGrowthRate(double todayTotalFocusGrowthRate) {
		this.todayTotalFocusGrowthRate = todayTotalFocusGrowthRate;
	}

}