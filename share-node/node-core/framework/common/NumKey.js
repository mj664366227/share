var NumKey = function (table, column, key, pk, seconds) {
	return {
		table: table,
		column: column,
		key: key,
		pk: pk,
		seconds: seconds,
		getKey: function (id) {
			return this.key + id;
		}
	}
};
var timeUtil = require("../core/util/timeUtil");
module.exports = {
	/**
	 * 创意点赞数
	 */
	ideaPraise: NumKey("idea", "praise", "ideaPraiseNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 创意创意值
	 */
	ideaCreative: NumKey("idea", "creative", "ideaCreativeNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 创意评论数
	 */
	ideaCommentNum: NumKey("idea", "comment_num", "ideaCommentNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 创意评论回复条数
	 */
	ideaCommentReplyNum: NumKey("idea_comment", "reply_num", "ideaCommentReplyNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 创意评论点赞数
	 */
	ideaCommentPraiseNum: NumKey("idea_comment", "praise_num", "ideaCommentPraiseNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 专案金额
	 */
	casePoints: NumKey("case", "points", "casePoints:", "id", timeUtil.day2Second(30)),
	/**
	 * 专案点赞数
	 */
	casePraiseNum: NumKey("case", "praise_num", "casePraiseNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 专案吐槽数
	 */
	caseFlowNum: NumKey("case", "flow_num", "caseFlowNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 专案创意数
	 */
	caseIdeaNum: NumKey("case", "idea_num", "caseIdeaNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 专案粉丝数
	 */
	caseFansNum: NumKey("case", "fans_num", "caseFansNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 专案参与数
	 */
	caseTakePartInNum: NumKey("case", "take_part_in_num", "caseTakePartInNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 用户G点数
	 */
	userPoints: NumKey("user", "points", "userPoints:", "id", 0),
	/**
	 * 用户积分数
	 */
	userScore: NumKey("user", "score", "userScore:", "id", 0),
	/**
	 * 用户创意值
	 */
	userExp: NumKey("user", "exp", "userExp:", "id", 0),
	/**
	 * 用户等级
	 */
	userLevel: NumKey("user", "level", "userLevel:", "id", 0),
	/**
	 * 用户发布专案数
	 */
	userCaseNum: NumKey("user", "case_num", "userCaseNum:", "id", 0),
	/**
	 * 用户关注领域数
	 */
	userTagNum: NumKey("user", "tag_num", "userTagNum:", "id", 0),
	/**
	 * 企业G点数
	 */
	companyPoints: NumKey("company", "points", "companyPoints:", "id", 0),
	/**
	 * 企业所有专案数
	 */
	companyAllCaseNum: NumKey("company", "all_case_num", "companyAllCaseNum:", "id", 0),
	/**
	 * 企业进行专案数
	 */
	companyGoingCaseNum: NumKey("company", "going_case_num", "companyGoingCaseNum:", "id", 0),
	/**
	 * 企业结束专案数
	 */
	companyOverCaseNum: NumKey("company", "over_case_num", "companyOverCaseNum:", "id", 0),
	/**
	 * 企业粉丝数
	 */
	companyFansNum: NumKey("company", "fans_num", "companyFansNum:", "id", 0),
	/**
	 * 企业专案收藏数
	 */
	companyCollectNum: NumKey("company", "collect_num", "companyCollectNum:", "id", 0),
	/**
	 * 企业关注用户数
	 */
	companyFocusUserNum: NumKey("company", "focus_user_num", "companyFocusUserNum:", "id", 0),
	/**
	 * 企业剩余发专案次数
	 */
	companyPushCaseNum: NumKey("company", "push_case_num", "companyPushCaseNum:", "id", 0),
	/**
	 * 企业报告数量
	 */
	companyReportNum: NumKey("company", "report_num", "companyReportNum:", "id", 0),
	/**
	 * 创意圈点赞数
	 */
	marketOpusPraise: NumKey("market_opus", "praise", "marketOpusPraiseNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 创意圈评论数
	 */
	marketOpusCommentNum: NumKey("market_opus", "comment_num", "marketOpusCommentNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 商品数量数
	 */
	shopGoodsNum: NumKey("shop_goods", "num", "shopGoodsNum:", "id", 0),
	/**
	 * 大咖说点赞数
	 */
	superstarOpusPraise: NumKey("superstar_opus", "praise", "superstarOpusPraiseNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 大咖说评论数
	 */
	superstarOpusCommentNum: NumKey("superstar_opus", "comment_num", "superstarOpusCommentNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 奖品数量
	 */
	awardNum: NumKey("case_award", "num", "caseAwardNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 问题选项选择次数统计
	 */
	askChoiceChooseNum: NumKey("case_ask_choice", "choose_num", "caseAskChoiceChooseNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 自定义标签企业使用数
	 */
	companyDefineTag: NumKey("define_tag", "company_num", "defineTagCompanyNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 自定义标签用户使用数
	 */
	userDefineTag: NumKey("define_tag", "user_num", "defineTagUserNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 用户参与所得创意值
	 */
	userDimensionJoin: NumKey("user_dimension", "join", "userDimensionJoin:", "user_id", timeUtil.day2Second(30)),
	/**
	 * 用户被点赞所得创意值
	 */
	userDimensionPraise: NumKey("user_dimension", "praise", "userDimensionPraise:", "user_id", timeUtil.day2Second(30)),
	/**
	 * 用户合作所得创意值
	 */
	userDimensionCollaboration: NumKey("user_dimension", "collaboration", "userDimensionCollaboration:", "user_id", timeUtil.day2Second(30)),
	/**
	 * 用户被转发所得创意值
	 */
	userDimensionForward: NumKey("user_dimension", "forward", "userDimensionForward:", "user_id", timeUtil.day2Second(30)),
	/**
	 * 用户被企业选中所得创意值
	 */
	userDimensionSelect: NumKey("user_dimension", "select", "userDimensionSelect:", "user_id", timeUtil.day2Second(30)),
	/**
	 * 项目创意点赞数
	 */
	projectIdeaPraise: NumKey("project_idea", "praise", "projectIdeaPraiseNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 项目创意评论数
	 */
	projectIdeaCommentNum: NumKey("project_idea", "comment_num", "projectIdeaCommentNum:", "id", timeUtil.day2Second(30)),
	/**
	 * 项目评论回复数
	 */
	projectIdeaCommentReplyNum: NumKey("project_idea_comment", "reply_num", "projectIdeaCommentReplyNum:", "id", timeUtil.day2Second(30))
};