package com.gu.core.redis;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * redis key of count manage
 * @author luo
 */
public enum CountKey {
	/**
	 * 创意点赞数
	 */
	ideaPraise("idea", "praise", "ideaPraiseNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 创意评论数
	 */
	ideaCommentNum("idea", "comment_num", "ideaCommentNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 专案点赞数
	 */
	casePraiseNum("case", "praise_num", "casePraiseNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 专案吐槽数
	 */
	caseFlowNum("case", "flow_num", "caseFlowNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 专案创意数
	 */
	caseIdeaNum("case", "idea_num", "caseIdeaNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 专案粉丝数
	 */
	caseFansNum("case", "fans_num", "caseFansNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 专案参与数
	 */
	caseTakePartInNum("case", "take_part_in_num", "caseTakePartInNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 用户G点数
	 */
	userPoints("user", "points", "userPoints:", 0),
	/**
	 * 用户积分数
	 */
	userScore("user", "score", "userScore:", 0),
	/**
	 * 企业G点数
	 */
	companyPoints("company", "points", "companyPoints:", 0),
	/**
	 * 企业所有专案数
	 */
	companyAllCaseNum("company", "all_case_num", "companyAllCaseNum:", 0),
	/**
	 * 企业进行专案数
	 */
	companyGoingCaseNum("company", "going_case_num", "companyGoingCaseNum:", 0),
	/**
	 * 企业结束专案数
	 */
	companyOverCaseNum("company", "over_case_num", "companyOverCaseNum:", 0),
	/**
	 * 企业粉丝数
	 */
	companyFansNum("company", "fans_num", "companyFansNum:", 0),
	/**
	 * 企业专案收藏数
	 */
	companyCollectNum("company", "collect_num", "companyCollectNum:", 0),
	/**
	 * 企业关注用户数
	 */
	companyFocusUserNum("company", "focus_user_num", "companyFocusUserNum:", 0),
	/**
	 * 创意圈点赞数
	 */
	marketOpusPraise("market_opus", "praise", "marketOpusPraiseNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 创意圈评论数
	 */
	marketOpusCommentNum("market_opus", "comment_num", "marketOpusCommentNum:", TimeUnit.DAYS.toSeconds(30)),
	/**
	 * 商品数量数
	 */
	shopGoodsNum("shop_goods", "num", "shopGoodsNum:", 0);

	private static HashMap<String, HashMap<String, String>> tableColumnMap = new HashMap<String, HashMap<String, String>>();

	static {
		for (CountKey countKey : CountKey.values()) {
			HashMap<String, String> columnMap = tableColumnMap.get(countKey.getTable());
			if (columnMap == null) {
				columnMap = new HashMap<String, String>();
				tableColumnMap.put(countKey.getTable(), columnMap);
			}
			tableColumnMap.get(countKey.getTable()).put(countKey.getColumn(), countKey.getColumn());
		}
	}

	private String table;
	private String column;
	private String key;
	private long seconds;

	private CountKey(String table, String column, String key, long seconds) {
		this.table = table;
		this.column = column;
		this.key = key;
		this.seconds = seconds;
	}

	public String getTable() {
		return table;
	}

	public String getColumn() {
		return column;
	}

	public String getKey(long id) {
		return key + id;
	}

	public String getPrefixKey() {
		return key;
	}

	/**
	 * 缓存超时时间(0=永不超时)
	 * @return
	 */
	public int getSeconds() {
		return (int) seconds;
	}

	public static HashMap<String, String> getColumnMap(String table) {
		return tableColumnMap.get(table);
	}
}