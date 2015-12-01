package com.gu.core.redis;

import com.gu.core.util.Time;

/**
 * redis key manage
 * @author luo
 */
public class KeyFactory {
	//废弃key:user
	//废弃key:User
	//废弃key:USER
	public final static String userKey = "DUser:";
	//废弃key:flow
	public final static String flowKey = "DFlow:";
	//废弃key:userMessage
	public final static String userMessageKey = "DUserMessage:";
	//废弃key:company
	public final static String companyKey = "DCompany:";

	/**
	 * 服务器错误短信计数
	 * @author ruan 
	 * @param date 日期(格式：yyyyMMdd)
	 */
	public final static String serverErrorKey(String date) {
		return "serverError:" + date;
	}

	/**
	 * 用户在线
	 * @author ruan 
	 * @param time 时间(格式：yyyyMMddHHmm)
	 */
	public final static String onlineUserKey(String time) {
		return "onlineUser:" + time;
	}

	/**
	 * 企业在线
	 * @author ruan 
	 * @param time 时间(格式：yyyyMMddHHmm)
	 */
	public final static String onlineCompanyKey(String time) {
		return "onlineCompany:" + time;
	}

	/**
	 * 生成user_idKey For DUser缓存
	 * @param userId user表id
	 * @return
	 */
	public final static String userKey(long userId) {
		return userKey + userId;
	}

	/**
	 * 用户支付密码错误次数限制
	 * @author ruan 
	 * @param userId 用户id
	 */
	public final static String userPaymentPasswordKey(long userId) {
		return "userPaymentPassword:" + userId + "_" + Time.date("yyyyMMdd");
	}

	/**
	 * 个人单日使用app时长统计
	 * @author ruan 
	 * @param userId
	 * @param date
	 */
	public final static String useDurationKey(long userId, String date) {
		return "useDuration:" + userId + "_" + date;
	}

	/**
	 * 生成company_idKey For DCompany缓存
	 * @param companyId
	 * @return
	 */
	public final static String companyKey(long companyId) {
		return companyKey + companyId;
	}

	/**
	 * 预存手机号
	 * @param mobile 手机号
	 */
	public final static String storeMobileKey(String mobile) {
		// 曾用storeMobile:
		// 曾用StoreMobile:
		return "DStoreMobile:" + mobile;
	}

	/**
	 * 生成company_idKey For DCompany缓存
	 * @param email
	 * @return
	 */
	public final static String companyKey(String email) {
		return companyKey + email;
	}

	/**
	 * 企业今天发布的专案次数
	 * @param companyId 企业id
	 */
	public final static String companyPublishCaseKey(long companyId) {
		return "companyPublishCaseNum:" + companyId + "_" + Time.date("yyyy-MM-dd");
	}

	/**
	 * 企业专案列表
	 * @param companyId 企业id
	 */
	public final static String companyCaseListKey(long companyId) {
		return "companyCaseList:" + companyId;
	}

	/**
	 * 企业专案列表
	 * @param companyId 企业id
	 */
	public final static String companyCasePageListKey(long companyId) {
		return "companyCasePageList:" + companyId;
	}

	/**
	 * 企业粉丝列表
	 * @param companyId 企业id
	 */
	public final static String companyFansKey(long companyId) {
		return "companyFans:" + companyId;
	}

	/**
	 * 企业邮箱认证
	 * @param companyId 企业id
	 */
	public final static String companyEmailAuthKey(long companyId) {
		return "companyEmailAuth:" + companyId;
	}

	/**
	 * 企业是否发邮箱验证
	 * @param companyId 企业id
	 */
	public final static String companySendEmailKey(long companyId) {
		return "companySendEmail:" + companyId;
	}

	/**
	 * 企业找回密码
	 * @param email 邮件地址
	 */
	public final static String companyFindPassEmailKey(String email) {
		return "companyFindPassEmail:" + email;
	}

	/**
	* 企业关注用户
	* @author ruan 
	* @param companyId 企业id
	* @param userId 用户id
	*/
	public final static String companyFocusUserKey(long companyId, long userId) {
		// 曾用companyFocusUser:
		return "DCompanyFocusUser:" + companyId + "_" + userId;
	}

	/**
	 * 企业关注用户列表
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public final static String companyFocusUserListKey(long companyId) {
		return "companyFocusUserList:" + companyId;
	}

	/**
	 * 记录企业G点支出
	 * @author ruan 
	 * @param companyId 企业id
	 * @param caseId 专案id
	 */
	public final static String companyExpenseKey(long companyId, long caseId) {
		// 曾用companyExpense:
		return "DCompanyExpense:" + companyId + "_" + caseId;
	}

	/**
	 * 记录企业G点支出列表
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public final static String companyExpenseListKey(long companyId) {
		return "companyExpenseList:" + companyId;
	}

	/**
	 * 企业消息条数
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public final static String companyMessageNumKey(long companyId) {
		return "companyMessageNum:" + companyId;
	}

	/**
	 * 用户消息条数
	 * @author luo 
	 * @param userId 用户id
	 */
	public final static String userMessageNumKey(long userId) {
		return "userMessageNum:" + userId;
	}

	/**
	 * 判断用户是否可以关注某用户
	 * @param userId 关注人id
	 * @param friendId 被关注人id
	 */
	public final static String isCanFocusKey(long userId, long friendId) {
		return "isCanFocus:" + userId + "_" + friendId + "_" + Time.date("yyyyMMdd");
	}

	/**
	 * 最近7天活跃企业key
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public static String companyRecent7ActiveKey(String startDate, String endDate) {
		return "companyRecent7Active:" + startDate + "_" + endDate;
	}

	/**
	 * 最近14天活跃企业key
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public static String companyRecent14ActiveKey(String startDate, String endDate) {
		return "companyRecent14Active:" + startDate + "_" + endDate;
	}

	/**
	 * 发布专案的品牌统计
	 * @author ruan 
	 * @param date 日期
	 */
	public static String companyPubCaseKey(String date) {
		return "companyPubCase:" + date;
	}

	/**
	 * 平均每个品牌每周发布专案的次数
	 * @author ruan 
	 * @param week 周
	 */
	public static String companyPubCaseWeekKey(int week) {
		return "companyPubCaseWeek:" + week;
	}

	/**
	 * 平均每个品牌每月发布专案的次数
	 * @author ruan 
	 * @param month 月
	 */
	public static String companyPubCaseMonthKey(int month) {
		return "companyPubCaseMonth:" + month;
	}

	/**
	 * 企业收藏点子列表
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public final static String collectIdeaListKey(long companyId) {
		return "collectIdeaList:" + companyId;
	}

	/**
	 * 获取验证码的key
	 * @param mobile 手机号
	 */
	public final static String verificationCodeKey(String mobile) {
		return "verifyCode:" + mobile;
	}

	/**
	 * 验证码是否可以发送key
	 * @param mobile 机号
	 * @return
	 */
	public final static String verificationCodeSendKey(String mobile) {
		return "verifySend:" + mobile;
	}

	/**
	 * 统计每天可以发送的短信数量
	 * @param mobile 机号
	 * @return
	 */
	public final static String verificationPerDayKey(String mobile) {
		return "verifyPerDay:" + mobile + "_" + Time.date("yyyy-MM-dd");
	}

	/**
	 * 生成mobilerKey For DUser缓存
	 * @param mobile
	 * @return
	 */
	public final static String mobileKey(String mobile) {
		return userKey + mobile;
	}

	/**
	 * caseKey
	 * @param caseId 专案id
	 */
	public final static String caseKey(long caseId) {
		// 曾用case:
		// 曾用Case:
		// 曾用CASE:
		return "DCase:" + caseId;
	}

	/**
	 * 专案粉丝列表
	 * @param caseId 专案id
	 */
	public final static String caseFansListKey(long caseId) {
		// 曾用caseFansList:
		return "CaseFansList:" + caseId;
	}

	/**
	 * 用户关注专案
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public final static String focusCaseKey(long userId, long caseId) {
		// 曾用focusCase:
		return "DFocusCase:" + userId + "_" + caseId;
	}

	/**
	 * 分类专案列表
	 * @param caseType 专案类型
	 */
	public final static String caseListKey(int caseType) {
		return "caseList:" + caseType;
	}

	/**
	 * 企业分类专案列表
	 * @param companyId 企业id
	 * @param caseType 专案类型
	 */
	public final static String caseListKey(long companyId, int caseType) {
		return "companyCaseList:" + companyId + "_" + caseType;
	}

	/**
	 * 企业进行中的专案列表
	 * @param companyId 企业id
	 */
	public final static String goingCaseListKey(long companyId) {
		return "companyGoingCaseList:" + companyId;
	}

	/**
	 * 企业进行中的专案列表
	 * @param companyId 企业id
	 * @param caseType 专案类型
	 */
	public final static String goingCaseListKey(long companyId, int caseType) {
		return "companyGoingCaseList:" + companyId + "_" + caseType;
	}

	/**
	 * 企业完结了的专案列表
	 * @param companyId 企业id
	 */
	public final static String overCaseListKey(long companyId) {
		return "companyOverCaseList:" + companyId;
	}

	/**
	 * 企业完结了的专案列表
	 * @param companyId 企业id
	 * @param caseType 专案类型
	 */
	public final static String overCaseListKey(long companyId, int caseType) {
		return "companyOverCaseList:" + companyId + "_" + caseType;
	}

	/**
	 * 企业分类专案联合列表
	 * @param companyId 企业id
	 */
	public final static String caseListUnionKey(long companyId) {
		return "companyCaseUnionList:" + companyId;
	}

	/**
	 * 全部专案列表
	 */
	public final static String allCaseListKey() {
		return "allCaseList";
	}

	/**
	 * 最新专案列表
	 */
	public final static String newCaseListKey() {
		return "newCaseList";
	}

	/**
	 * 吐槽key
	 * @param flowId 吐槽id
	 * @return
	 */
	public final static String flowKey(long flowId) {
		return flowKey + flowId;
	}

	/**
	 * 吐槽列表
	 * @param caseId 专案id
	 */
	public final static String flowListKey(long caseId) {
		return "flowList:" + caseId;
	}

	/**
	 * 创意key
	 * @param ideaId 创意id
	 * @return
	 */
	public final static String ideaKey(long ideaId) {
		// 曾用Idea
		return "DIdea:" + ideaId;
	}

	/**
	 * 创意TopKey
	 * @param caseId 专案id
	 * @return
	 */
	public static String ideaTopKey(long caseId) {
		return "ideaTop:" + caseId;
	}

	/**
	 * 创意ListKey
	 * @param caseId 专案id
	 * @return
	 */
	public static String ideaListKey(long caseId) {
		return "ideaList:" + caseId;
	}

	/**
	 * 启动人数去重
	 * @author ruan 
	 */
	public static String startUserListKey(String date) {
		return "startUserList:" + date;
	}

	/**
	 * 创意评论key
	 * @param ideaCommentId 创意评论id
	 */
	public final static String ideaCommentKey(long ideaCommentId) {
		// 曾ideaComment:
		return "DIdeaComment:" + ideaCommentId;
	}

	/**
	 * 创意评论listKey
	 * @param ideaId 创意id
	 */
	public final static String ideaCommentListKey(long ideaId) {
		return "ideaCommentList:" + ideaId;
	}

	/**
	 * 企业回复创意key
	 * @param ideaCommentId 企业回复创意id
	 */
	public static String ideaCompanyCommentKey(long ideaCompanyCommentId) {
		// 曾用ideaCompanyComment:
		return "DIdeaCompanyComment:" + ideaCompanyCommentId;
	}

	/**
	 * 企业回复创意listKey
	 * @param ideaId 创意id
	 */
	public static String ideaCompanyCommentListKey(long ideaId) {
		return "ideaCompanyCommentList:" + ideaId;
	}

	/**
	 * 用户关注的专案列表
	 * @param userId 用户id
	 */
	public final static String userFocusCaseListKey(long userId) {
		return "userFocusCasesList:" + userId;
	}

	/**
	 * 用户参与过的专案
	 * @param userId 用户id
	 */
	public final static String userHasTakePartInCaseKey(long userId) {
		return "userHasTakePartInCaseList:" + userId;
	}

	/**
	 * 用户关注企业列表
	 * @param userId 用户id
	 */
	public final static String userFocusCompanyKey(long userId) {
		return "userFocusCompanyList:" + userId;
	}

	/**
	 * 用户关注的人列表
	 * @param userId 用户id
	 */
	public final static String userFocusKey(long userId) {
		return "userFocusList:" + userId;
	}

	/**
	 * 用户粉丝列表
	 * @param userId 用户id
	 */
	public final static String userFansKey(long userId) {
		return "userFansList:" + userId;
	}

	/**
	 * 用户吐槽统计
	 * @param userId 用户id
	 */
	public final static String userFlowCountKey(long userId) {
		return "userFlowCount:" + userId;
	}

	/**
	 * 用户实名认证key
	 * @param userId 用户id
	 */
	public final static String userProveKey(long userId) {
		return "userProve:" + userId;
	}

	/**
	 * 创意获赞表key
	 * @param ideaId 创意id
	 */
	public final static String ideaPraiseKey(long ideaId) {
		return "ideaPraise:" + ideaId;
	}

	/**
	 * 专案获赞表key
	 * @param caseId 专案id
	 */
	public final static String casePraiseKey(long caseId) {
		return "casePraise:" + caseId;
	}

	/**
	 * 专案获吐槽表key
	 * @param caseId 专案id
	 */
	public final static String caseFlowKey(long caseId) {
		return "caseFlow:" + caseId;
	}

	/**
	 * 用户对专案创意点赞次数统计key
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public static String casePraiseCountKey(long userId, long caseId) {
		// 曾用casePraiseCount：
		return "DCasePraiseCount:" + userId + "_" + caseId;
	}

	/**
	 * 用户每个专案只能发一次创意key
	 * @param userId 用户id
	 * @param caseId 专案id
	 */
	public static String userIdeaPubKey(long caseId) {
		return "userIdeaPub:" + caseId;
	}

	/**
	 * 日活跃用户列表(当天总的)
	 * @param date 日期
	 */
	public static String dailyActiveUserListKey(String date) {
		return "dailyActiveUserList:" + date;
	}

	/**
	 * 日活跃用户列表(分时)
	 * @param date 日期
	 * @param hour 小时
	 */
	public static String dailyActiveUserListKey(String date, int hour) {
		return "dailyActiveUserList:" + date + "_" + hour;
	}

	/**
	 * 日活跃企业户列表(当天总的)
	 * @param date 日期
	 */
	public static String dailyActiveCompanyListKey(String date) {
		return "dailyActiveCompanyList:" + date;
	}

	/**
	 * 日活跃企业户列表(分时)
	 * @param date 日期
	 */
	public static String dailyActiveCompanyListKey(String date, int hour) {
		return "dailyActiveCompanyList:" + date + "_" + hour;
	}

	/**
	 * userMessageKey
	 * @param userMessageId 用户消息id
	 * @return
	 */
	public static String userMessageKey(long userMessageId) {
		return userMessageKey + userMessageId;
	}

	/**
	 * 用户消息列表
	 * @param userId 用户id
	 * @param type 消息类型
	 * @return
	 */
	public static String userMessageListKey(long userId, int type) {
		return "userMessageList:" + userId + "_" + type;
	}

	/**
	 * 企业自定义标签列表
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public final static String companyDefineCaseTagListKey(long companyId) {
		return "companyDefineCaseTagList:" + companyId;
	}

	/**
	 * 企业所有专案的标签
	 * @author ruan 
	 * @param companyId 企业id
	 */
	public final static String companyCaseTagListKey(long companyId) {
		return "companyCaseTagList:" + companyId;
	}

	/**
	 * 用户设置
	 * @author luo 
	 * @param userId
	 */
	public final static String userSettingKey(long userId) {
		// 曾用userSetting:
		return "DUserSetting:" + userId;
	}

	/**
	 * 专案搜索缓存
	 * @param search 搜索关键字
	 * @return
	 */
	public final static String caseSearchKey(String search) {
		return "caseSearchKey:" + search;
	}

	/**
	 * 企业后台专案搜索缓存
	 * @param companyId 企业id
	 * @param search 搜索关键字
	 * @return
	 */
	public final static String companyCaseSearchKey(long companyId, String search) {
		return "companyCaseSearchKey:" + companyId + "_" + search;
	}

	/**
	 * 企业后台专案搜索的搜索关键字缓存
	 * @param companyId 企业id
	 * @return
	 */
	public final static String companyCaseSearchKeySetKey(long companyId) {
		return "companyCaseSearchKeySetKey:" + companyId;
	}

	public static String companyMessageKey(long companyId) {
		// 曾用 companyMessage:
		return "DCompanyMessage:" + companyId;
	}

	public static String companyMessageListKey(long companyId, int type) {
		return "companyMessageList:" + companyId + "_" + type;
	}

	/**
	 * web工程用户的最后登录时间
	 */
	public static String webLastLoginTimeKey(long companyId) {
		return "WebLastLoginTime:" + companyId;
	}

	/**
	 * app端用户的最后登录时间
	 */
	public static String appLastLoginTimeKey(long userId) {
		return "AppLastLoginTime:" + userId;
	}

	/**
	 * 按用户合并的分红记录列表
	 * @param caseId
	 * @return
	 */
	public static String CaseFinishBonusUserLogListKey(long caseId) {
		return "caseFinishBonusUserLogList:" + caseId;
	}

	/**
	 * 按用户合并的分红记录
	 * @param bonusUserLogId
	 * @return
	 */
	public static String CaseFinishBonusUserLogKey(long bonusUserLogId) {
		// 曾用caseFinishBonusUserLog:
		return "DCaseFinishBonusUserLog:" + bonusUserLogId;
	}

	/**
	 * 最近7天活跃用户key
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public static String userRecent7ActiveKey(String startDate, String endDate) {
		return "userRecent7Active:" + startDate + "_" + endDate;
	}

	/**
	 * 最近14天活跃用户key
	 * @author ruan 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 */
	public static String userRecent14ActiveKey(String startDate, String endDate) {
		return "userRecent14Active:" + startDate + "_" + endDate;
	}

	/**
	 * 创意圈_作品key
	 * @param opusId 创意圈_作品id
	 * @return
	 */
	public final static String marketOpusKey(long marketOpusId) {
		// 曾用 marketOpus:
		return "DMarketOpus:" + marketOpusId;
	}

	/**
	 * 创意圈_作品评论key
	 * @param opusId 创意圈_作品评论id
	 * @return
	 */
	public static String marketOpusCommentKey(long opusCommentId) {
		// 曾用marketOpusComment：
		return "DMarketOpusComment:" + opusCommentId;
	}

	/**
	 * 创意圈_作品列表(全部:userId=0) 同用一个getMarketOpusList,不能去掉0
	 * @return
	 */
	public static String marketOpusListKey() {
		return "marketOpusListKey:0";
	}

	/**
	 * 创意圈_作品列表(个人:userId>0,全部:userId=0)
	 * @param userId
	 * @return
	 */
	public static String marketOpusListKey(long userId) {
		return "marketOpusListKey:" + userId;
	}

	/**
	 * 创意圈_作品评论列表key
	 * @param opusId 创意圈_作品id
	 * @return
	 */
	public static String marketOpusCommentListKey(long opusId) {
		return "marketOpusCommentList:" + opusId;
	}

	/**
	 * 创意圈_作品获赞表key
	 * @param ideaId 创意圈_作品id
	 */
	public static String marketOpusPraiseKey(long opusId) {
		// 曾用marketOpusPraise:
		return "DMarketOpusPraise:" + opusId;
	}

	public static String wechatOpenKey(String openId) {
		// 曾用wechatOpen:
		return "DWechatOpen:" + openId;
	}

	public static String wechatOpenKey(long userId) {
		// 曾用wechatOpen:
		return "DWechatOpen:" + userId;
	}
}