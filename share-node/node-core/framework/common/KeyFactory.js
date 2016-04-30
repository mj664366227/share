// redis key 工厂

const userKey = "Auser:";
const companyKey = "Acompany:";
const chargeLogKey = "AchargeLog:";
const userAddressKey = "AuserAddress:";
const projectKey = "Aproject:";
const projectIdeaKey = "AprojectIdea:";
const projectIdeaFileKey = "AprojectIdeaFile:";
const projectIdeaLinkKey = "AprojectIdeaLink:";
const projectIdeaAddedKey = "AprojectIdeaAdded:";
const projectResearchKey = "AprojectResearch:";
const projectIdeaCommentKey = "AprojectIdeaComment:";
const projectIdeaCommentReplyKey = "AprojectIdeaCommentReply:";
const projectResearchCommentKey = "AprojectResearchComment:";

module.exports = {
	_userKey: userKey,
	_companyKey: companyKey,
	_chargeLogKey: chargeLogKey,
	_userAddressKey: userAddressKey,
	_projectKey: projectKey,
	_projectIdeaKey: projectIdeaKey,
	_projectIdeaFileKey: projectIdeaFileKey,
	_projectIdeaLinkKey: projectIdeaLinkKey,
	_projectIdeaAddedKey: projectIdeaAddedKey,
	_projectResearchKey: projectResearchKey,
	_projectIdeaCommentKey: projectIdeaCommentKey,
	_projectIdeaCommentReplyKey: projectIdeaCommentReplyKey,
	_projectResearchCommentKey: projectResearchCommentKey,
	/**
	 * 用户key
	 * @param userId 用户id
	 */
	userKey: function (userId) {
		return userKey + userId;
	},
	/**
	 * 用户key
	 * @param mobile 手机号
	 */
	mobileKey: function (mobile) {
		return userKey + mobile;
	},
	/**
	 * 充值对象key
	 * @param orderId 订单号
	 */
	chargeLogKey: function (orderId) {
		return chargeLogKey + orderId;
	},
	/**
	 * 用户收货地址
	 * @param userId 用户id
	 */
	userAddressKey: function (userId) {
		return userAddressKey + userId;
	},
	/**
	 * 获取验证码的key
	 * @param mobile 手机号
	 */
	verificationCodeKey: function (mobile) {
		return "verifyCode:" + mobile;
	},
	/**
	 * 统计每天可以发送的短信数量
	 * @param mobile 机号
	 */
	verificationPerDayKey: function (mobile) {
		return "verifyPerDay:" + mobile + "_" + timeUtil.date("YYYY-MM-DD");
	},
	/**
	 * 验证码是否可以发送key
	 * @param mobile 手机号
	 */
	verificationCodeSendKey: function (mobile) {
		return "verifySend:" + mobile;
	},
	/**
	 * 用户创意人列表
	 */
	userCreativeManListKey: function () {
		return "userCreativeMan";
	},
	/**
	 * 用户参与项目列表
	 */
	projetcUserJoinList: function (userId) {
		return "projetcUserJoinList:" + userId;
	},
	/**
	 * 企业key
	 * @param companyId
	 */
	companyKey: function (companyId) {
		return companyKey + companyId;
	},
	/**
	 * 项目key
	 * @param projectId
	 */
	projectKey: function (projectId) {
		return projectKey + projectId;
	},
	/**
	 * 进行中的项目列表
	 */
	allGoingProjectList: function () {
		return "allGoingProjectList";
	},
	/**
	 * 已结束的项目列表
	 */
	allOverProjectList: function () {
		return "allOverProjectList";
	},
	/**
	 * 项目创意
	 */
	projectIdeaKey: function (ideaId) {
		return projectIdeaKey + ideaId;
	},
	/**
	 * 项目创意文件
	 */
	projectIdeaFileKey: function (ideaFileId) {
		return projectIdeaFileKey + ideaFileId;
	},
	/**
	 * 项目创意链接
	 */
	projectIdeaLinkKey: function (ideaLinkId) {
		return projectIdeaLinkKey + ideaLinkId;
	},
	/**
	 * 创意点赞列表
	 * @param ideaId
	 */
	projectIdeaPraiseList: function (ideaId) {
		return "projectIdeaPraiseList:" + ideaId;
	},
	/**
	 * 项目创意最近更新排列
	 * @param projectId
	 * @param tagId
	 */
	projectIdeaLastEditTopKey: function (projectId, tagId) {
		return "projectIdeaLastEditTopKey:" + projectId + (!tagId ? "" : "_" + tagId);

	},
	/**
	 * 项目创意点赞数排列
	 * @param projectId
	 * @param tagId
	 */
	projectIdeaPraiseTopKey: function (projectId, tagId) {
		return "projectIdeaPraiseTopKey:" + projectId + (!tagId ? "" : "_" + tagId);
	},
	/**
	 * 项目创意评论数排列
	 * @param projectId
	 * @param tagId
	 */
	projectIdeaCommentNumTopKey: function (projectId, tagId) {
		return "projectIdeaCommentNumTopKey:" + projectId + (!tagId ? "" : "_" + tagId);
	},
	/**
	 * 项目资料key
	 * @param researchId 资源id
	 */
	projectResearchKey: function (researchId) {
		return projectResearchKey + researchId;
	},
	/**
	 * 项目资料列表key
	 * @param projectId 项目id
	 */
	projectResearchListKey: function (projectId) {
		return "projectResearchList:" + projectId;
	},
	/**
	 * 点子文件列表
	 * @param ideaId
	 */
	projectIdeaFileList: function (ideaId) {
		return "projectIdeaFileList:" + ideaId;
	},
	/**
	 * 点子链接列表
	 * @param ideaId
	 */
	projectIdeaLinkList: function (ideaId) {
		return "projectIdeaLinkList:" + ideaId;
	},
	/**
	 * 项目点子补充key
	 * @param ideaAddedId
	 */
	projectIdeaAddedKey: function (ideaAddedId) {
		return projectIdeaAddedKey + ideaAddedId;
	},
	/**
	 * 项目点子补充列表
	 * @param ideaId
	 */
	projectIdeaAddedList: function (ideaId) {
		return 'projectIdeaAddedList' + ideaId;
	},
	/**
	 * 项目点子评论key
	 * @param ideaCommentId 项目评论id
	 */
	projectIdeaCommentKey: function (ideaCommentId) {
		return projectIdeaCommentKey + ideaCommentId;
	},
	/**
	 * 项目点子评论列表
	 * @param ideaId 项目点子id
	 */
	projectIdeaCommentList: function (ideaId) {
		return 'projectIdeaCommentList:' + ideaId;
	},
	/**
	 * 项目点子评论回复key
	 * @param ideaCommentReplyKeyId 项目点子评论回复id
	 */
	projectIdeaCommentReplyKey: function (ideaCommentReplyKeyId) {
		return projectIdeaCommentReplyKey + ideaCommentReplyKeyId;
	},
	/**
	 * 项目点子评论回复列表
	 * @param ideaCommentId 项目点子评论id
	 */
	projectIdeaCommentReplyList: function (ideaCommentId) {
		return 'projectIdeaCommentReplyList:' + ideaCommentId;
	},
	/**
	 * 项目调研资料评论
	 * @param researchCommentId 调研资料评论id
	 */
	projectResearchCommentKey: function (researchCommentId) {
		return projectResearchCommentKey + researchCommentId;
	},
	/**
	 * 项目调研资料评论列表
	 * @param researchId 资料id
	 */
	projectResearchCommentListKey: function (researchId) {
		return "projectResearchCommentList:" + researchId;
	}
};