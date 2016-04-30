/**
 * 项目控制器
 */
const LOGGER = logUtil.getLogger(module.filename);
const projectService = requireService('projectService');
const userService = requireService("userService");
const ResearchInfoResponse = requireHttpRes("ResearchInfoResponse");
module.exports = {
	/**
	 * 项目详情
	 */
	proInfo: {
		url: URLCommand.proInfo,
		method: "get",
		func: async function (req, res, param) {

		}
	},
	/**
	 * 进行中项目列表
	 */
	proGoingList: {
		url: URLCommand.proGoingList,
		method: "get",
		func: async function (req, res, param) {
			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			let data = await projectService.getGoingProjectList(page, pageSize);
			data.page = page;
			data.pageSize = pageSize;
			return data;
		}
	},
	/**
	 * 已结束目列表
	 */
	proOverList: {
		url: URLCommand.proOverList,
		method: "get",
		func: async function (req, res, param) {
			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			let data = await projectService.getOverProjectList(page, pageSize);
			data.page = page;
			data.pageSize = pageSize;
			return data;
		}
	},
	/**
	 * 用户参与项目列表
	 */
	userJoinList: {
		url: URLCommand.userJoinList,
		method: "get",
		func: async function (req, res, param) {
			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			if (!page || !pageSize) {
				LOGGER.warn('error param! page: %s, pageSize: %s', page, pageSize);
				return errorCode.systemError.parameterError;
			}
			let data = await projectService.getProjectUserJoinList(page, pageSize);
			data.page = page;
			data.pageSize = pageSize;
			return data;
		}
	},
	/**
	 * 上传资料调研
	 */
	researchPub: {
		url: URLCommand.researchPub,
		method: "post",
		func: async function (req, res, param) {
			// 验证参数
			let title = param.title;
			let content = param.content;
			if (!title || !content) {
				LOGGER.warn('error param! title: %s, content: %s', title, content);
				return errorCode.systemError.parameterError;
			}

			// 验证项目
			let projectId = parseInt(param.projectId);
			if (!projectId) {
				LOGGER.warn('project not exists, projectId: %s', projectId);
				return errorCode.systemError.parameterError;
			}
			let project = await projectService.getProjectById(projectId);
			if (!project) {
				LOGGER.warn('project not exists, projectId: %s', projectId);
				return errorCode.systemError.parameterError;
			}

			projectService.addProjectResearch(projectId, title, content);
			return 1;
		}
	},
	/**
	 * 资料列表
	 */
	researchList: {
		url: URLCommand.researchList,
		method: "get",
		func: async function (req, res, param) {
			// 获取参数
			let projectId = parseInt(param.projectId);
			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			if (!projectId || !page || !pageSize) {
				LOGGER.warn('error param! projectId: %s, page: %s, pageSize: %s', projectId, page, pageSize);
				return errorCode.systemError.parameterError;
			}

			// 验证项目是否存在
			let project = await projectService.getProjectById(projectId);
			if (!project) {
				LOGGER.warn('project not exists, projectId: %s', projectId);
				return errorCode.systemError.parameterError;
			}

			let data = await projectService.getProjectResearchList(projectId, page, pageSize);
			data.page = page;
			data.pageSize = pageSize;
			return data;
		}
	},
	/**
	 * 发布资料调研评论
	 */
	researchCommentPub: {
		url: URLCommand.researchCommentPub,
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
			// 检查资料
			let researchId = _.toNumber(param.researchId);
			if (!researchId) {
				LOGGER.warn('researchId is empty!');
				return errorCode.systemError.parameterError;
			}
			let projectResearch = await projectService.getProjectResearch(researchId);
			if (!projectResearch) {
				LOGGER.warn('projectResearch not exists, researchId: %s', researchId);
				return errorCode.researchError.researchNotExists;
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

			// 写入评论
			projectService.addProjectResearchComment(researchId, userId, content, replyUserId);
			return 1;
		}
	},
	/**
	 * 资料调研评论列表
	 */
	researchCommentList: {
		url: URLCommand.researchCommentList,
		method: "get",
		func: async function (req, res, param) {
			let researchId = _.toNumber(param.researchId);
			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			if (!researchId || !page || !pageSize) {
				LOGGER.warn('error param! researchId: %s, page: %s, pageSize: %s', researchId, page, pageSize);
				return errorCode.systemError.parameterError;
			}

			// 检查资料
			let projectResearch = await projectService.getProjectResearch(researchId);
			if (!projectResearch) {
				LOGGER.warn('projectResearch not exists, researchId: %s', researchId);
				return errorCode.researchError.researchNotExists;
			}

			// 获取评论列表
			let data = projectService.getProjectResearchCommentList(researchId, page, pageSize);
			data.page = page;
			data.pageSize = pageSize;
			return data;
		}
	},
	/**
	 * 资料详情
	 */
	researchInfo: {
		url: URLCommand.researchInfo,
		method: "get",
		func: async function (req, res, param) {
			// 检查资料
			let researchId = _.toNumber(param.researchId);
			if (!researchId) {
				LOGGER.warn('error param! researchId: %s', researchId);
				return errorCode.systemError.parameterError;
			}

			let projectResearch = await projectService.getProjectResearch(researchId);
			if (!projectResearch) {
				LOGGER.warn('projectResearch not exists, researchId: %s', researchId);
				return errorCode.researchError.researchNotExists;
			}

			let researchInfoResponse = new ResearchInfoResponse();
			researchInfoResponse.title = projectResearch.title;
			researchInfoResponse.content = projectResearch.content;
			return researchInfoResponse;
		}
	},
	/**
	 * 编辑资料
	 */
	researchEdit: {
		url: URLCommand.researchEdit,
		method: "post",
		func: async function (req, res, param) {
			// 验证参数
			let title = param.title;
			let content = param.content;
			if (!title || !content) {
				LOGGER.warn('error param! title: %s, content: %s', title, content);
				return errorCode.systemError.parameterError;
			}

			// 检查资料
			let researchId = _.toNumber(param.researchId);
			if (!researchId) {
				LOGGER.warn('error param! researchId: %s', researchId);
				return errorCode.systemError.parameterError;
			}

			let projectResearch = await projectService.getProjectResearch(researchId);
			if (!projectResearch) {
				LOGGER.warn('projectResearch not exists, researchId: %s', researchId);
				return errorCode.researchError.researchNotExists;
			}

			// 修改资料
			projectService.updateProjectResearch(projectResearch);
			return 1;
		}
	}
};