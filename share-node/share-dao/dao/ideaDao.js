const DProjectIdea = requireT("DProjectIdea");
const DProjectIdeaPraise = requireT("DProjectIdeaPraise");
const DProjectIdeaFile = requireT('DProjectIdeaFile');
const DProjectIdeaLink = requireT('DProjectIdeaLink');
const DProjectIdeaAdded = requireT('DProjectIdeaAdded');
const DProjectIdeaComment = requireT('DProjectIdeaComment');
const DProjectIdeaCommentReply = requireT('DProjectIdeaCommentReply');
module.exports = {
	/**
	 * 添加点子
	 * @param userId 用户id
	 * @param projectId 项目id
	 * @param title 标题
	 * @param tag 所属标签
	 * @param background 背景介绍
	 * @param detail 详细介绍
	 * @param research 调研资料
	 * @param cover 封面
	 * @param inspiration 灵感启发
	 * @returns {DProjectIdea}
	 */
	addProjectIdea: async function (userId, projectId, title, tag, background, detail, research, cover, inspiration) {
		let projectIdea = new DProjectIdea();
		projectIdea.DProjectIdea.userId = userId;
		projectIdea.DProjectIdea.projectId = projectId;
		projectIdea.DProjectIdea.title = title;
		projectIdea.DProjectIdea.tag = tag;
		projectIdea.DProjectIdea.background = background;
		projectIdea.DProjectIdea.detail = detail;
		projectIdea.DProjectIdea.research = research;
		projectIdea.DProjectIdea.cover = cover;
		projectIdea.DProjectIdea.inspiration = inspiration;
		projectIdea.DProjectIdea.createTime = timeUtil.now();
		projectIdea.DProjectIdea.lastEditTime = timeUtil.now();
		return await guMasterDbService.save(projectIdea);
	},
	/**
	 * 更新点子
	 * @param projectIdea
	 * @returns {Boolean}
	 */
	updateProjectIdea: async function (projectIdea) {
		return await guMasterDbService.updateT(projectIdea);
	},
	/**
	 * 点子详情
	 * @param ideaId
	 * @returns {DProjectIdea}
	 */
	getProjectIdeaById: async function (ideaId) {
		let sql = "select * from `project_idea` where `id`=?";
		return await guSlaveDbService.queryT(sql, DProjectIdea, ideaId);
	},
	/**
	 * 添加点子记录
	 * @param ideaId
	 * @param userId
	 * @param projectId
	 */
	addProjectIdeaPraise: async function (ideaId, userId, projectId) {
		let projectIdeaPraise = new DProjectIdeaPraise();
		projectIdeaPraise.DProjectIdeaPraise.ideaId = ideaId;
		projectIdeaPraise.DProjectIdeaPraise.userId = userId;
		projectIdeaPraise.DProjectIdeaPraise.projectId = projectId;
		projectIdeaPraise.DProjectIdeaPraise.createTime = timeUtil.now();
		return await guMasterDbService.save(projectIdeaPraise);
	},
	/**
	 * 删除点子点赞记录
	 * @param ideaId
	 * @param userId
	 * @returns {Boolean}
	 */
	delProjectIdeaPraise: async function (ideaId, userId) {
		let sql = "DELETE FROM `project_idea_praise` WHERE `idea_id`=? and `user_id`=?";
		return await guMasterDbService.update(sql, ideaId, userId);
	},
	/**
	 * 查询点赞记录
	 * @param ideaId
	 * @param userId
	 * @returns {DProjectIdeaPraise}
	 */
	getProjectIdeaPraise: async function (ideaId, userId) {
		let sql = "SELECT * FROM `project_idea_praise` WHERE `project_idea_id`=? and `user_id`=?";
		return await guSlaveDbService.queryT(sql, DProjectIdeaPraise, ideaId, userId);
	},
	/**
	 * 添加点子附件
	 * @param ideaId
	 * @param name
	 * @param link
	 * @returns {DProjectIdeaFile}
	 */
	addProjectIdeaFile: async function (ideaId, name, link) {
		let projectIdeaFile = new DProjectIdeaFile();
		projectIdeaFile.DProjectIdeaFile.ideaId = ideaId;
		projectIdeaFile.DProjectIdeaFile.name = name;
		projectIdeaFile.DProjectIdeaFile.link = link;
		return await guMasterDbService.save(projectIdeaFile);
	},
	/**
	 * 删除点子附件
	 * @param ideaId
	 * @param ideaFileId
	 * @returns {Boolean}
	 */
	delProjectIdeaFile: async function (ideaId, ideaFileId) {
		let sql = "DELETE FROM `project_idea_file` WHERE `idea_id`=? and `id`=?";
		return await guMasterDbService.update(sql, ideaId, ideaFileId);
	},
	/**
	 * 点子附件列表
	 * @param ideaId
	 * @returns {Array.<DProjectIdeaFile>}
	 */
	getProjectIdeaFileList: async function (ideaId) {
		let sql = "SELECT * FROM `project_idea_file` WHERE `idea_id`=? order by `id` desc";
		return await guSlaveDbService.queryTList(sql, DProjectIdeaFile, ideaId);
	},
	/**
	 *  添加点子链接
	 * @param ideaId
	 * @param name
	 * @param link
	 * @returns {DProjectIdeaLink}
	 */
	addProjectIdeaLink: async function (ideaId, name, link) {
		let projectIdeaLink = new DProjectIdeaLink();
		projectIdeaLink.DProjectIdeaLink.ideaId = ideaId;
		projectIdeaLink.DProjectIdeaLink.name = name;
		projectIdeaLink.DProjectIdeaLink.link = link;
		return await guMasterDbService.save(projectIdeaLink);
	},
	/**
	 * 删除点子附件
	 * @param ideaId
	 * @param ideaLinkId
	 * @returns {Boolean}
	 */
	delProjectIdeaLink: async function (ideaId, ideaLinkId) {
		let sql = "DELETE FROM `project_idea_link` WHERE `idea_id`=? and `id`=?";
		return await guMasterDbService.update(sql, ideaId, ideaLinkId);
	},
	/**
	 * 点子链接列表
	 * @param ideaId
	 * @returns {Array.<DProjectIdeaLink>}
	 */
	getProjectIdeaLinkList: async function (ideaId) {
		let sql = "SELECT * FROM `project_idea_link` WHERE `idea_id`=? order by `id` desc";
		return await guSlaveDbService.queryTList(sql, DProjectIdeaLink, ideaId);
	},
	/**
	 * 添加一条点子补充
	 * @param ideaId
	 * @param userId
	 * @param content
	 * @returns {DProjectIdeaAdded}
	 */
	addProjectIdeaAdded: async function (ideaId, userId, content) {
		let projectIdeaAdded = new DProjectIdeaAdded();
		projectIdeaAdded.DProjectIdeaAdded.ideaId = ideaId;
		projectIdeaAdded.DProjectIdeaAdded.userId = userId;
		projectIdeaAdded.DProjectIdeaAdded.content = content;
		projectIdeaAdded.DProjectIdeaAdded.createTime = timeUtil.now();
		return await guMasterDbService.save(projectIdeaAdded);
	},
	/**
	 * 点子补充列表
	 * @param ideaId
	 * @param page
	 * @param pageSize
	 * @returns {Array.<DProjectIdeaAdded>}
	 */
	getProjectIdeaAddedList: async function (ideaId, page, pageSize) {
		let sql = "SELECT * FROM `project_idea_added` WHERE `idea_id`=? order by `id` desc limit ?,?";
		return await guSlaveDbService.queryTList(sql, DProjectIdeaAdded, ideaId, page, pageSize);
	},
	/**
	 * 添加一条评论
	 * @param ideaId 点子id
	 * @param userId 用户id
	 * @param content 评论内容
	 * @param replyUserId 回复用户内容
	 * @returns {DProjectIdeaComment}
	 */
	addProjectIdeaComment: async function (ideaId, userId, content, replyUserId) {
		let projectIdeaComment = new DProjectIdeaComment();
		projectIdeaComment.DProjectIdeaComment.ideaId = ideaId;
		projectIdeaComment.DProjectIdeaComment.userId = userId;
		projectIdeaComment.DProjectIdeaComment.content = content;
		projectIdeaComment.DProjectIdeaComment.replyUserId = replyUserId;
		projectIdeaComment.DProjectIdeaComment.createTime = timeUtil.now();
		projectIdeaComment.DProjectIdeaComment.replyNum = 0;
		return await guMasterDbService.save(projectIdeaComment);
	},
	/**
	 * 查询点子评论
	 * @param ideaCommentId
	 * @returns {DProjectIdeaComment}
	 */
	getProjectIdeaCommentById: async function (ideaCommentId) {
		let sql = "SELECT * FROM `project_idea_comment` WHERE `id`=?";
		return await guSlaveDbService.queryT(sql, DProjectIdeaComment, ideaCommentId);
	},
	/**
	 * 添加一条评论回复
	 * @param userId 用户id
	 * @param ideaCommentId 点子id
	 * @param content 评论内容
	 * @param replyUserId 回复用户内容
	 * @returns {DProjectIdeaCommentReply}
	 */
	addProjectIdeaCommentReply: async function (userId, ideaCommentId, content, replyUserId) {
		let projectIdeaCommentReply = new DProjectIdeaCommentReply();
		projectIdeaCommentReply.DProjectIdeaCommentReply.userId = userId;
		projectIdeaCommentReply.DProjectIdeaCommentReply.ideaCommentId = ideaCommentId;
		projectIdeaCommentReply.DProjectIdeaCommentReply.content = content;
		projectIdeaCommentReply.DProjectIdeaCommentReply.replyUserId = replyUserId;
		projectIdeaCommentReply.DProjectIdeaCommentReply.createTime = timeUtil.now();
		return await guMasterDbService.save(projectIdeaCommentReply);
	},
	/**
	 * 获取点子列表
	 * @param orderFieldName 排列方式
	 * @param projectId 项目id
	 * @param tag 标签
	 * @param page
	 * @param pageSize
	 * @param order 0=正序,1=倒序
	 * @returns {Array.<DProjectIdea>}
	 */
	getProjectIdeaList: async function (orderFieldName, projectId, tag, page, pageSize, order) {
		let orderBySql = "";
		if (orderFieldName == "lastEditTime") {
			orderBySql = " order by `last_edit_time` ";
		}
		if (orderFieldName == "praise") {
			orderBySql = " order by `praise` ";
		}
		if (orderFieldName == "commentNum") {
			orderBySql = " order by `comment_num` ";
		}
		if (orderBySql == "") {
			throw new Error("ideaService.getProjectIdeaList orderFieldName is Error");
		}
		orderBySql = orderBySql + (!order ? " asc " : " desc ");

		if (tag >= 1) {
			let sql = "SELECT * FROM `project_idea` WHERE `project_id`=? and `tag`=? " + orderBySql + " limit ?,?";
			return await guSlaveDbService.queryTList(sql, DProjectIdea, projectId, tag, page, pageSize);
		} else {
			let sql = "SELECT * FROM `project_idea` WHERE `project_id`=? " + orderBySql + " limit ?,?";
			return await guSlaveDbService.queryTList(sql, DProjectIdea, projectId, page, pageSize);
		}
	}
};