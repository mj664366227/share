var DProjectUserJoin = requireT("DProjectUserJoin");
var DProjectResearch = requireT("DProjectResearch");
var DProjectResearchComment = requireT("DProjectResearchComment");
var DProject = requireT("DProject");
module.exports = {
	/**
	 * 项目详情
	 * @param projectId
	 */
	getProjectById: async function (projectId) {
		var sql = "select * from `project` where `id`=?";
		return await guSlaveDbService.queryT(sql, DProject, projectId);
	},
	/**
	 * 进行中的项目
	 * @returns {Array.<DProject>}
	 */
	getGoingProjectList: async function (page, pageSize) {
		var sql = "select * from `project` where `end_time`>? order by `id` desc limit ?,?";
		return await guSlaveDbService.queryTList(sql, DProject, timeUtil.now(), (page - 1) * pageSize, pageSize);
	},
	/**
	 * 已结束的项目
	 * @param page
	 * @param pageSize
	 * @returns {Array.<DProject>} Array&lt;DProject&gt;
	 */
	getOverProjectList: async function (page, pageSize) {
		var sql = "select * from `project` where `end_time`<=? order by `id` desc limit ?,?";
		return await guSlaveDbService.queryTList(sql, DProject, timeUtil.now(), (page - 1) * pageSize, pageSize);
	},
	/**
	 * 添加用户参与项目记录
	 * @param userId
	 * @param projectId
	 */
	addProjectUserJoin: async function (userId, projectId) {
		var projectUserJoin = new DProjectUserJoin();
		projectUserJoin.DProjectUserJoin.userId = userId;
		projectUserJoin.DProjectUserJoin.projectId = projectId;
		projectUserJoin.DProjectUserJoin.createTime = timeUtil.now();
		return await guMasterDbService.save(projectUserJoin);
	},
	/**
	 * 用户参与项目列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @returns {Array.<DProjectUserJoin>}
	 */
	getProjectUserJoinList: async function (userId, page, pageSize) {
		var sql = "select * from `project_user_join` where `user_id`=? order by `id` desc limit ?,?";
		return await guSlaveDbService.queryTList(sql, DProjectUserJoin, userId, (page - 1) * pageSize, pageSize);
	},
	/**
	 * 用户是否参与项目
	 * @param userId
	 * @param projectId
	 * @returns {Array.<DProjectUserJoin>}
	 */
	getProjectUserJoin: async function (userId, projectId) {
		var sql = "select * from `project_user_join` where `user_id`=? and `project_id`=?";
		return await guSlaveDbService.queryT(sql, DProjectUserJoin, userId, projectId);
	},
	/**
	 * 添加资料收集
	 * @param projectId
	 * @param title
	 * @param content
	 */
	addProjectResearch: async function (projectId, title, content) {
		var projectResearch = new DProjectResearch();
		projectResearch.DProjectResearch.projectId = projectId;
		projectResearch.DProjectResearch.title = title;
		projectResearch.DProjectResearch.content = content;
		projectResearch.DProjectResearch.createTime = timeUtil.now();
		return await guMasterDbService.save(projectResearch);
	},
	/**
	 * 获取资料收集
	 * @param researchId 资料id
	 */
	getProjectResearch: async function (researchId) {
		var sql = "select * from `project_research` where `id`=?";
		return await guSlaveDbService.queryT(sql, DProjectResearch, researchId);
	},
	/**
	 * 更新资料调研
	 * @param projectResearch 资料调研对象
	 */
	updateProjectResearch: async function (projectResearch) {
		return await guMasterDbService.updateT(projectResearch);
	},
	/**
	 * 获取项目调研资料列表
	 * @param projectId 项目id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	getProjectResearchList: async function (projectId, page, pageSize) {
		let sql = 'select * from `project_research` where `project_id`=? order by `id` desc limit ?,?';
		return await guSlaveDbService.queryTList(sql, DProjectResearch, projectId, (page - 1) * pageSize, pageSize);
	},
	/**
	 * 添加一条调研资料评论
	 * @param researchId 资料id
	 * @param userId 用户id
	 * @param content 评论内容
	 * @param replyUserId 回复用户id
	 */
	addProjectResearchComment: async function (researchId, userId, content, replyUserId) {
		let projectResearchComment = new DProjectResearchComment();
		projectResearchComment.DProjectResearchComment.researchId = researchId;
		projectResearchComment.DProjectResearchComment.userId = userId;
		projectResearchComment.DProjectResearchComment.content = content;
		projectResearchComment.DProjectResearchComment.replyUserId = replyUserId;
		projectResearchComment.DProjectResearchComment.createTime = timeUtil.now();
		return await guMasterDbService.save(projectResearchComment);
	},
	/**
	 * 查询点子评论
	 * @param researchCommentId
	 */
	getProjectResearchCommentById: async function (researchCommentId) {
		let sql = "SELECT * FROM `project_research_comment` WHERE `id`=?";
		return await guSlaveDbService.queryT(sql, DProjectResearchComment, researchCommentId);
	},
	/**
	 * 调研资料评论列表
	 * @param researchId 资料id
	 * @param page 页码
	 * @param pageSize 页面大小
	 */
	getProjectResearchCommentList: async function (researchId, page, pageSize) {
		var sql = "select * from `project_research_comment` where `research_id`=? order by `id` desc limit ?,?";
		return await guSlaveDbService.queryTList(sql, DProjectResearchComment, researchId, (page - 1) * pageSize, pageSize);
	}
};