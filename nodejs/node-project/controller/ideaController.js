/**
 * 点子模块控制器
 */
const LOGGER = logUtil.getLogger(module.filename);
const projectService = requireService("projectService");
const userService = requireService("userService");
const ideaService = requireService("ideaService");
const DProjectIdea = requireT("DProjectIdea");
const UserBase = requireHttpBase("UserBase");
const ProjectIdeaInfoResponse = requireHttpRes("ProjectIdeaInfoResponse");
module.exports = {
	/**
	 * 发表点子
	 */
	ideaPub: {
		url: URLCommand.ideaPub,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查项目
			let projectId = _.toNumber(param.projectId);
			if (!projectId) {
				LOGGER.warn('projectId is empty!');
				return errorCode.systemError.parameterError;
			}
			let project = await projectService.getProjectById(projectId);
			if (!project) {
				LOGGER.warn('project not exists, projectId: %s', projectId);
				return errorCode.projectError.projectNotExists;
			}
			// 标题
			let title = param.title;
			if (!title) {
				LOGGER.warn('title is empty!');
				return errorCode.systemError.parameterError;
			}
			// 所属标签
			let tag = _.toNumber(param.tag);
			if (!title) {
				LOGGER.warn('tag is empty!');
				return errorCode.systemError.parameterError;
			}
			// 背景介绍
			let background = param.background;
			if (!background) {
				LOGGER.warn('background is empty!');
				return errorCode.systemError.parameterError;
			}
			// 详细介绍
			let detail = param.detail;
			if (!detail) {
				LOGGER.warn('detail is empty!');
				return errorCode.systemError.parameterError;
			}
			// 调研资料
			let research = param.research;
			if (!research) {
				LOGGER.warn('research is empty!');
				return errorCode.systemError.parameterError;
			}
			// 封面
			let cover = param.cover;
			if (!cover) {
				LOGGER.warn('cover is empty!');
				return errorCode.systemError.parameterError;
			}
			// 灵感启发
			let inspiration = param.inspiration;
			if (!inspiration) {
				LOGGER.warn('inspiration is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.addProjectIdea(userId, projectId, title, tag, background, detail, research, cover, inspiration);
			if (projectIdea == null) {
				logger.warn("write projectIdea error! userId: %s, projectId: %s, title: %s", userId, projectId, title);
				return errorCode.systemError.unknowError;
			}
			let ideaId = projectIdea.DProjectIdea.id;
			let fileArray = param.file;
			for (let file of fileArray) {
				ideaService.addProjectIdeaFile(ideaId, file.name, file.link);
			}
			let linkArray = param.link;
			for (let link of linkArray) {
				ideaService.addProjectIdeaFile(ideaId, link.name, link.link);
			}
			return {ideaId: ideaId};
		}
	},
	/**
	 * 编辑点子
	 */
	ideaEdit: {
		url: URLCommand.ideaEdit,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 匹配用户
			if (userId != projectIdea.DProjectIdea.userId) {
				LOGGER.warn('user != ideaUserId, userId: %s ,ideaUserId: %s', userId, projectIdea.DProjectIdea.userId);
				return errorCode.ideaError.ideaUserNotEqual;
			}
			// 标题
			let title = param.title;
			if (!title) {
				LOGGER.warn('title is empty!');
				return errorCode.systemError.parameterError;
			}
			// 所属标签
			let tag = param.tag;
			if (!title) {
				LOGGER.warn('tag is empty!');
				return errorCode.systemError.parameterError;
			}
			// 背景介绍
			let background = param.background;
			if (!background) {
				LOGGER.warn('background is empty!');
				return errorCode.systemError.parameterError;
			}
			// 详细介绍
			let detail = param.detail;
			if (!detail) {
				LOGGER.warn('detail is empty!');
				return errorCode.systemError.parameterError;
			}
			// 调研资料
			let research = param.research;
			if (!research) {
				LOGGER.warn('research is empty!');
				return errorCode.systemError.parameterError;
			}
			// 封面
			let cover = param.cover;
			if (!cover) {
				LOGGER.warn('cover is empty!');
				return errorCode.systemError.parameterError;
			}
			// 灵感启发
			let inspiration = param.inspiration;
			if (!inspiration) {
				LOGGER.warn('inspiration is empty!');
				return errorCode.systemError.parameterError;
			}

			projectIdea.DProjectIdea.title = title;
			projectIdea.DProjectIdea.tag = tag;
			projectIdea.DProjectIdea.background = background;
			projectIdea.DProjectIdea.detail = detail;
			projectIdea.DProjectIdea.research = research;
			projectIdea.DProjectIdea.cover = cover;
			projectIdea.DProjectIdea.inspiration = inspiration;
			projectIdea.DProjectIdea.lastEditTime = timeUtil.now();

			let updaetSuccess = await ideaService.updateProjectIdea(projectIdea);
			if (!updaetSuccess) {
				logger.warn("update projectIdea error! userId: %s, ideaId: %s", userId, ideaId);
				return errorCode.systemError.unknowError;
			}
			return 1;
		}
	},
	/**
	 * 点子详情
	 */
	ideaInfo: {
		url: URLCommand.ideaInfo,
		method: "get",
		func: async function (req, res, param) {
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea is not exists! ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			let projectIdeaInfoResponse = new ProjectIdeaInfoResponse(projectIdea);
			// 发布点子的用户信息
			let projectIdeaUser = await userService.getUserById(projectIdea.DProjectIdea.userId);
			projectIdeaInfoResponse.userBase = new UserBase(projectIdeaUser);
			// 点子附件
			projectIdeaInfoResponse.file = await ideaService.getProjectIdeaFileList(ideaId);
			// 点子链接
			projectIdeaInfoResponse.link = await ideaService.getProjectIdeaLinkList(ideaId);
			// 点子补充列表
			projectIdeaInfoResponse.addedList = await ideaService.getProjectIdeaAddedList(ideaId, 1, 5);

			let userId = _.toNumber(param.userId);
			if (userId) {
				// 获取用户
				let user = await userService.getUserByMobile(mobile);
				if (!user) {
					return errorCode.userError.userNotExists;
				}
				// 判断用户是否点赞
				projectIdeaInfoResponse.isPraise = await ideaService.isProjectIdeaPraise(ideaId, userId);
			}
			return projectIdeaInfoResponse;
		}
	},
	/**
	 * 发布点子补充
	 */
	ideaAddedPub: {
		url: URLCommand.ideaAddedPub,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 检查内容
			let content = param.content;
			if (!content) {
				LOGGER.warn('content is empty!');
				return errorCode.systemError.parameterError;
			}
			// 匹配用户
			if (userId != projectIdea.DProjectIdea.userId) {
				LOGGER.warn('user != ideaUserId, userId: %s ,ideaUserId: %s', userId, projectIdea.DProjectIdea.userId);
				return errorCode.ideaError.ideaUserNotEqual;
			}
			let ideaAdded = await ideaService.addProjectIdeaAdded(projectIdea, userId, content);
			if (!ideaAdded) {
				LOGGER.warn('add ideaAdded is not success. userId: %s, ideaId: %s', userId, ideaId);
				return errorCode.systemError.unknowError;
			}
			// 更新点子最后编辑时间
			idea.DProjectIdea.lastEditTime = timeUtil.now();
			ideaService.updateProjectIdea(idea);

			let ideaAddedId = ideaAdded.DProjectIdeaAdded.id;
			return {ideaAddedId: ideaAddedId};
		}
	},
	/**
	 * 添加附件
	 */
	ideaFileAdd: {
		url: URLCommand.ideaFileAdd,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 匹配用户
			if (userId != projectIdea.DProjectIdea.userId) {
				LOGGER.warn('user != ideaUserId, userId: %s ,ideaUserId: %s', userId, projectIdea.DProjectIdea.userId);
				return errorCode.ideaError.ideaUserNotEqual;
			}
			// 文件名
			let name = param.name;
			if (!name) {
				LOGGER.warn('name is empty!');
				return errorCode.systemError.parameterError;
			}
			// 文件链接
			let link = param.link;
			if (!link) {
				LOGGER.warn('link is empty!');
				return errorCode.systemError.parameterError;
			}
			// 执行添加
			let ideaFile = await ideaService.addProjectIdeaFile(ideaId, name, link);
			if (!ideaFile) {
				LOGGER.warn('add ideaFile is not success. userId: %s, ideaId: %s', userId, ideaId);
				return errorCode.systemError.unknowError;
			}
			let ideaFileId = ideaFile.DProjectIdeaFile.id;
			return {ideaFileId: ideaFileId};
		}
	},
	/**
	 * 删除附件
	 */
	ideaFileDel: {
		url: URLCommand.ideaFileDel,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 匹配用户
			if (userId != projectIdea.DProjectIdea.userId) {
				LOGGER.warn('user != ideaUserId, userId: %s ,ideaUserId: %s', userId, projectIdea.DProjectIdea.userId);
				return errorCode.ideaError.ideaUserNotEqual;
			}
			let ideaFileId = _.toNumber(param.ideaFileId);
			if (!ideaFileId) {
				LOGGER.warn('ideaFileId is empty!');
				return errorCode.systemError.parameterError;
			}
			// 执行删除
			let delSuccess = await ideaService.delProjectIdeaFile(ideaId, ideaFileId);
			if (!delSuccess) {
				LOGGER.warn('del ideaFile is not success. userId: %s, ideaId: %s, ideaFileId: %s,', userId, ideaId, ideaFileId);
				return errorCode.systemError.unknowError;
			}
			return 1;
		}
	},
	/**
	 * 添加链接
	 */
	ideaLinkAdd: {
		url: URLCommand.ideaLinkAdd,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 匹配用户
			if (userId != projectIdea.DProjectIdea.userId) {
				LOGGER.warn('user != ideaUserId, userId: %s ,ideaUserId: %s', userId, projectIdea.DProjectIdea.userId);
				return errorCode.ideaError.ideaUserNotEqual;
			}
			// 链接名
			let name = param.name;
			if (!name) {
				LOGGER.warn('name is empty!');
				return errorCode.systemError.parameterError;
			}
			// 链接
			let link = param.link;
			if (!link) {
				LOGGER.warn('link is empty!');
				return errorCode.systemError.parameterError;
			}
			// 执行添加
			let ideaLink = await ideaService.addProjectIdeaLink(ideaId, name, link);
			if (!ideaLink) {
				LOGGER.warn('add ideaLink is not success. userId: %s, ideaId: %s', userId, ideaId);
				return errorCode.systemError.unknowError;
			}
			let ideaLinkId = ideaLink.DProjectIdeaLink.id;
			return {ideaLinkId: ideaLinkId};
		}
	},
	/**
	 * 删除链接
	 */
	ideaLinkDel: {
		url: URLCommand.ideaLinkDel,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 匹配用户
			if (userId != projectIdea.DProjectIdea.userId) {
				LOGGER.warn('user != ideaUserId, userId: %s ,ideaUserId: %s', userId, projectIdea.DProjectIdea.userId);
				return errorCode.ideaError.ideaUserNotEqual;
			}
			let ideaLinkId = _.toNumber(param.ideaLinkId);
			if (!ideaLinkId) {
				LOGGER.warn('ideaLinkId is empty!');
				return errorCode.systemError.parameterError;
			}
			// 执行删除
			let delSuccess = await ideaService.delProjectIdeaLink(ideaId, ideaLinkId);
			if (!delSuccess) {
				LOGGER.warn('del ideaLink is not success. userId: %s, ideaId: %s, ideaLinkId: %s', userId, ideaId, ideaLinkId);
				return errorCode.systemError.unknowError;
			}
			return 1;
		}
	},
	/**
	 * 点子点赞
	 */
	ideaPraiseUp: {
		url: URLCommand.ideaPraiseUp,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 检查项目
			let projectId = projectIdea.DProjectIdea.projectId;
			if (!projectId) {
				LOGGER.warn('projectId is empty!');
				return errorCode.systemError.parameterError;
			}
			let project = await projectService.getProjectById(projectId);
			if (!project) {
				LOGGER.warn('project not exists, projectId: %s', projectId);
				return errorCode.projectError.projectNotExists;
			}
			// 判断项目是否过期
			if (project.DProject.endTime < timeUtil.now()) {
				LOGGER.warn('project over time, projectId: %s', projectId);
				return errorCode.projectError.projectOvertime;
			}
			// 判断是否已经点赞
			let isPraise = await ideaService.isProjectIdeaPraise(ideaId, userId);
			if (isPraise) {
				LOGGER.warn('user is praise idea, ideaId: %s, userId: %s ', ideaId, userId);
				return errorCode.ideaError.isPraiseError;
			}
			// 添加点赞记录
			let ideaPraise = await ideaService.addProjectIdeaPraise(projectIdea, userId, projectId);
			if (!ideaPraise) {
				LOGGER.warn('add ideaPraise is not success. userId: %s, ideaId: %s', userId, ideaId);
				return errorCode.systemError.unknowError;
			}
			return 1;
		}
	},
	/**
	 * 点子取消点赞
	 */
	ideaPraiseDown: {
		url: URLCommand.ideaPraiseDown,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 检查项目
			let projectId = projectIdea.DProjectIdea.projectId;
			if (!projectId) {
				LOGGER.warn('projectId is empty!');
				return errorCode.systemError.parameterError;
			}
			let project = await projectService.getProjectById(projectId);
			if (!project) {
				LOGGER.warn('project not exists, projectId: %s', projectId);
				return errorCode.projectError.projectNotExists;
			}
			// 判断项目是否过期
			if (project.DProject.endTime < timeUtil.now()) {
				LOGGER.warn('project over time, projectId: %s', projectId);
				return errorCode.projectError.projectOvertime;
			}
			// 判断是否已经点赞
			let isPraise = await ideaService.isProjectIdeaPraise(projectIdea, userId);
			if (!isPraise) {
				LOGGER.warn('user is not praise idea, ideaId: %s, userId: %s', ideaId, userId);
				return errorCode.ideaError.notPraiseError;
			}
			// 删除点赞记录
			let delSuccess = await ideaService.delProjectIdeaPraise(ideaId, userId);
			if (!delSuccess) {
				LOGGER.warn('del ideaPraise is not success. userId: %s, ideaId: %s', userId, ideaId);
				return errorCode.systemError.unknowError;
			}
			return 1;
		}
	},
	/**
	 * 发布点子评论
	 */
	ideaCommentPub: {
		url: URLCommand.ideaCommentPub,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子
			let ideaId = _.toNumber(param.ideaId);
			if (!ideaId) {
				LOGGER.warn('ideaId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			// 检查内容
			let content = param.content;
			if (!content) {
				LOGGER.warn('content is empty!');
				return errorCode.systemError.parameterError;
			}
			// 检查被回复的用户
			let replyUserId = _.toNumber(param.replyUserId);
			if (replyUserId) {
				let replyUser = await userService.getUserById(replyUserId);
				if (!replyUser) {
					LOGGER.warn('replyUser not exists, replyUserId: %s', replyUserId);
					return errorCode.userError.replyUserNotExists;
				}
			} else {
				replyUserId = 0;
			}

			// 添加点子评论
			let ideaComment = await ideaService.addProjectIdeaComment(projectIdea, userId, content, replyUserId);
			if (!ideaComment) {
				logger.warn("write projectIdeaComment error! userId: %s, ideaId: %s, content: %s", userId, ideaId, content);
				return errorCode.systemError.unknowError;
			}
			let ideaCommentId = ideaComment.DProjectIdeaComment.id;
			return {ideaCommentId: ideaCommentId};
		}
	},
	/**
	 * 发布点子评论回复
	 */
	ideaCommentReplyPub: {
		url: URLCommand.ideaCommentReplyPub,
		method: "post",
		func: async function (req, res, param) {
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			// 检查点子评论
			let ideaCommentId = _.toNumber(param.ideaCommentId);
			if (!ideaCommentId) {
				LOGGER.warn('ideaCommentId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectIdeaComment = await ideaService.getProjectIdeaCommentById(ideaCommentId);
			if (!projectIdeaComment) {
				LOGGER.warn('projectIdeaComment not exists, ideaCommentId: %s', ideaCommentId);
				return errorCode.ideaError.ideaCommentNotExists;
			}
			// 检查内容
			let content = param.content;
			if (!content) {
				LOGGER.warn('content is empty!');
				return errorCode.systemError.parameterError;
			}
			// 检查被回复的用户
			let replyUserId = _.toNumber(param.replyUserId);
			if (replyUserId) {
				let replyUser = await userService.getUserById(replyUserId);
				if (!replyUser) {
					LOGGER.warn('replyUser not exists, replyUserId: %s', replyUserId);
					return errorCode.userError.replyUserNotExists;
				}
			} else {
				replyUserId = 0;
			}
			// 添加点子评论回复
			let ideaCommentReply = await ideaService.addProjectIdeaCommentReply(userId, ideaCommentId, content, replyUserId);
			if (!ideaCommentReply) {
				logger.warn("write projectIdeaCommentReply error! userId: %s, ideaCommentId: %s, content: %s", userId, ideaCommentId, content);
				return errorCode.systemError.unknowError;
			}
			let ideaCommentReplyId = ideaCommentReply.DProjectIdeaCommentReply.id;
			return {ideaCommentReplyId: ideaCommentReplyId};
		}
	},
	/**
	 * 点子列表
	 */
	ideaList: {
		url: URLCommand.ideaList,
		method: "get",
		func: async function (req, res, param) {
			let orderFieldName = req.params.orderFieldName;
			if (!orderFieldName) {
				LOGGER.warn('url is error! url: %s, orderFieldName: %s', URLCommand.ideaList, orderFieldName);
				return errorCode.systemError.parameterError;
			}

			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			let order = !param.order || param.order != 1 ? 0 : 1;
			let tag = param.tag;
			// 检查用户
			let userId = _.toNumber(param.userId);
			if (userId) {
				let user = await userService.getUserById(userId);
				if (!user) {
					LOGGER.warn('user not exists, userId: %s', userId);
					return errorCode.userError.userNotExists;
				}
			} else {
				userId = 0;
			}
			// 检查项目
			let projectId = _.toNumber(param.projectId);
			if (!projectId) {
				LOGGER.warn('projectId is empty!');
				return errorCode.systemError.parameterError;
			}
			let project = await projectService.getProjectById(projectId);
			if (!project) {
				LOGGER.warn('project not exists, projectId: %s', projectId);
				return errorCode.projectError.projectNotExists;
			}
			let data = await ideaService.getProjectIdeaList(userId, orderFieldName, projectId, tag, page, pageSize, order);
			data.page = page;
			data.pageSize = pageSize;
			return data;
		}
	},
	/**
	 * 点子补充列表
	 */
	ideaAddedList: {
		url: URLCommand.ideaAddedList,
		method: "get",
		func: async function (req, res, param) {
			let ideaId = _.toNumber(param.ideaId);
			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			if (!ideaId || !page || !pageSize) {
				LOGGER.warn('error param! ideaId: %s, page: %s, pageSize: %s', ideaId, page, pageSize);
				return errorCode.systemError.parameterError;
			}
			// 检查点子
			let projectIdea = await ideaService.getProjectIdeaById(ideaId);
			if (!projectIdea) {
				LOGGER.warn('projectIdea not exists, ideaId: %s', ideaId);
				return errorCode.ideaError.ideaNotExists;
			}
			let data = {};
			data.list = await ideaService.getProjectIdeaAddedList(ideaId, page, pageSize);
			data.page = page;
			data.pageSize = pageSize;
			return data;
		}
	}
};