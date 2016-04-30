const commonService = requireService('commonService');
const projectDao = requireDao("projectDao");
const DProjectUserJoin = requireT("DProjectUserJoin");
const DProject = requireT("DProject");
const DProjectResearch = requireT("DProjectResearch");
const DProjectResearchComment = requireT("DProjectResearchComment");
const DCompany = requireT("DCompany");
const ProjectBase = requireHttpBase("ProjectBase");
const CompanyBase = requireHttpBase("CompanyBase");
const UserBase = requireHttpBase("UserBase");
const ProjectBaseListResponse = requireHttpRes("ProjectBaseListResponse");
const ProjectResearchListResponse = requireHttpRes("ProjectResearchListResponse");
const ProjectResearchResponse = requireHttpRes("ProjectResearchResponse");
const ProjectResearchCommentListResponse = requireHttpRes("ProjectResearchCommentListResponse");
const ProjectResearchCommentResponse = requireHttpRes("ProjectResearchCommentResponse");
module.exports = {
	/**
	 * 项目详情
	 * @param projectId
	 * @returns {DProject}
	 */
	getProjectById: async function (projectId) {
		var jsonString = await redis.STRINGS.get(KeyFactory.projectKey(projectId));
		var project = new DProject();
		if (!jsonString) {
			project = await projectDao.getProjectById(projectId);
			if (project == null) {
				return null;
			}
			// 回写Redis缓存
			redis.STRINGS.setex(KeyFactory.projectKey(projectId), timeUtil.day2Second(30), JSON.stringify(project.DProject));
			return project;
		}
		return JSONObject.decodeT(jsonString, DProject);
	},
	/**
	 * 进行中的项目
	 * @param page
	 * @param pageSize
	 * @returns {ProjectBaseListResponse}
	 */
	getGoingProjectList: async function (page, pageSize) {
		var key = KeyFactory.allGoingProjectList();
		var start = (page - 1) * pageSize;
		var stop = start + pageSize - 1;

		var projectBaseListResponse = new ProjectBaseListResponse();

		var projectIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		if (projectIdSet.size == 0) {
			var projectList = await projectDao.getGoingProjectList(page, pageSize);
			if (projectList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return projectBaseListResponse;
			}
			var scoreMembers = [];
			projectList.forEach(function (project) {
				scoreMembers.push(project.DProject.id);
				scoreMembers.push(project.DProject.id);
				projectIdSet.add(project.DProject.id);
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}
		// 获取全部企业id
		var companyIdArr = [];
		var projectMap = await commonService.multiGetT(projectIdSet, DProject);
		for (let project of projectMap.values()) {
			if (project == null) {
				continue;
			}
			companyIdArr.push.apply(companyIdArr, _.split(project.DProject.agency, ","));
		}
		// 批量获取企业
		var companyIdSet = new Set(companyIdArr);
		var companyMap = await commonService.multiGetT(companyIdSet, DCompany);
		// 组成返回协议
		for (let project of projectMap.values()) {
			if (project == null) {
				continue;
			}
			var projectBase = new ProjectBase(project);
			var companyIdArr2 = _.split(project.DProject.agency, ",");
			// 给返回协议添加变量
			for (let companyId of companyIdArr2) {
				var company = companyMap.get(_.toNumber(companyId));
				if (!company) {
					continue;
				}
				var companyBase = new CompanyBase(company);
				projectBase.agencyList.push(companyBase);
			}
			projectBaseListResponse.list.push(projectBase);
		}
		return projectBaseListResponse;
	},
	/**
	 * 已结束的项目
	 * @param page
	 * @param pageSize
	 * @returns {ProjectBaseListResponse}
	 */
	getOverProjectList: async function (page, pageSize) {
		var key = KeyFactory.allOverProjectList();
		var start = (page - 1) * pageSize;
		var stop = start + pageSize - 1;

		var projectBaseListResponse = new ProjectBaseListResponse();

		var projectIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		if (projectIdSet.size == 0) {
			var projectList = await projectDao.getOverProjectList(page, page == 1 ? 1000 : pageSize);
			if (projectList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return projectBaseListResponse;
			}
			var scoreMembers = [];
			var i = 0;
			projectList.forEach(function (project) {
				scoreMembers.push(project.DProject.id);
				scoreMembers.push(project.DProject.id);
				//回写1000条时,不影响第一页的条数
				if (i < pageSize) {
					projectIdSet.add(project.DProject.id);
					i = i + 1;
				}
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}
		// 获取全部企业id
		var companyIdArr = [];
		var projectMap = await commonService.multiGetT(projectIdSet, DProject);
		for (let project of projectMap.values()) {
			if (project == null) {
				continue;
			}
			companyIdArr.push.apply(companyIdArr, _.split(project.DProject.agency, ","));
		}
		// 批量获取企业
		var companyIdSet = new Set(companyIdArr);
		var companyMap = await commonService.multiGetT(companyIdSet, DCompany);
		// 组成返回协议
		for (let project of projectMap.values()) {
			if (project == null) {
				continue;
			}
			var projectBase = new ProjectBase(project);
			var companyIdArr2 = _.split(project.DProject.agency, ",");
			// 给返回协议添加变量
			for (let companyId of companyIdArr2) {
				var company = companyMap.get(_.toNumber(companyId));
				if (!company) {
					continue;
				}
				var companyBase = new CompanyBase(company);
				projectBase.agencyList.push(companyBase);
			}
			projectBaseListResponse.list.push(projectBase);
		}
		return projectBaseListResponse;
	},
	/**
	 * 用户参与项目
	 * @param userId
	 * @param projectId
	 */
	joinProject: async function (userId, projectId) {
		let isJoin = await this.isJoinProject(userId, projectId);
		if (!isJoin) {
			// 添加用户参与项目记录
			var projectUserJoin = await projectDao.addProjectUserJoin(userId, projectId);
			if (projectUserJoin.DProjectUserJoin.id > 0) {
				var key = KeyFactory.projetcUserJoinList(userId);
				redis.SORTSET.zadd(key, projectUserJoin.DProjectUserJoin.id, projectId);
			}
		}
	},
	/**
	 * 判断用户是否参与项目
	 * @param userId
	 * @param projectId
	 */
	isJoinProject: async function (userId, projectId) {
		let key = KeyFactory.projetcUserJoinList(userId);
		var score = await redis.SORTSET.zscore(key, projectId);
		if (score == null) {
			// 缓存没有,到数据库查
			var projectUserJoin = await projectDao.getProjectUserJoin(userId, projectId);
			if (projectUserJoin.DProjectUserJoin.id == 0) {
				return false;
			}
			// 数据库有,回写缓存
			redis.SORTSET.zadd(key, projectUserJoin.DProjectUserJoin.id, projectId);
			return true;
		}
		return true;
	},
	/**
	 * 用户参与项目列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @returns {ProjectBaseListResponse}
	 */
	getProjectUserJoinList: async function (userId, page, pageSize) {
		let key = KeyFactory.projetcUserJoinList(userId);
		var start = (page - 1) * pageSize;
		var stop = start + pageSize - 1;

		var projectBaseListResponse = new ProjectBaseListResponse();

		var projectIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		if (projectIdSet.size == 0) {
			var projectUserJoinList = projectDao.getProjectUserJoinList(userId, page, page == 1 ? 1000 : pageSize);
			if (projectUserJoinList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return projectBaseListResponse;
			}
			var scoreMembers = [];
			var i = 0;
			projectUserJoinList.forEach(function (projectUserJoin) {
				scoreMembers.push(projectUserJoin.DProjectUserJoin.id);
				scoreMembers.push(projectUserJoin.DProjectUserJoin.projectId);
				//回写1000条时,不影响第一页的条数
				if (i < pageSize) {
					projectIdSet.add(projectUserJoin.DProjectUserJoin.projectId);
					i = i + 1;
				}
			});
			redis.SORTSET.zadd(key, scoreMembers);
		}
		var projectMap = await commonService.multiGetT(projectIdSet, DProject);
		for (let project of projectMap.values()) {
			if (project == null) {
				continue;
			}
			var projectBase = new ProjectBase(project);
			projectBaseListResponse.list.push(projectBase);
		}
		return projectBaseListResponse;
	},
	/**
	 * 添加资料收集
	 * @param projectId
	 * @param title
	 * @param content
	 */
	addProjectResearch: async function (projectId, title, content) {
		let projectResearch = await projectDao.addProjectResearch(projectId, title, content);
		if (!projectResearch) {
			return null;
		}
		let projectResearchKey = KeyFactory.projectResearchKey(projectResearch.DProjectResearch.id);
		redis.STRINGS.setex(projectResearchKey, timeUtil.day2Second(30), JSONObject.encodeT(projectResearch));

		let projectResearchListKey = KeyFactory.projectResearchListKey(projectResearch.projectId);
		redis.SORTSET.zadd(projectResearchListKey, projectResearch.DProjectResearch.id, projectResearch.DProjectResearch.id);
		return projectResearch;
	},
	/**
	 * 获取资料收集
	 * @param researchId 资料
	 */
	getProjectResearch: async function (researchId) {
		let key = KeyFactory.projectResearchKey(researchId);
		let json = await redis.STRINGS.get(key);
		let projectResearch = null;
		if (!json) {
			projectResearch = await projectDao.getProjectResearch(researchId);
			if (!projectResearch) {
				return null;
			}
			redis.STRINGS.setex(key, timeUtil.day2Second(30), JSONObject.encodeT(projectResearch));
			return projectResearch;
		}
		return JSONObject.decodeT(json, DProjectResearch);
	},
	/**
	 * 更新资料调研
	 * @param projectResearch 资料调研对象
	 */
	updateProjectResearch: function (projectResearch) {
		projectDao.updateProjectResearch(projectResearch);
		let key = KeyFactory.projectResearchKey(projectResearch.DProjectResearch.id);
		redis.STRINGS.setex(key, timeUtil.day2Second(30), JSONObject.encodeT(projectResearch));
	},
	/**
	 * 获取项目调研资料列表
	 * @param projectId 项目id
	 * @param page 当前页码
	 * @param pageSize 页面大小
	 */
	getProjectResearchList: async function (projectId, page, pageSize) {
		let key = KeyFactory.projectResearchListKey(projectId);
		let start = (page - 1) * pageSize;
		let stop = start + pageSize - 1;
		let projectResearchListResponse = new ProjectResearchListResponse();
		var researchIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		if (researchIdSet.size == 0) {
			var projectResearchList = projectDao.getProjectResearchList(projectId, page, page == 1 ? 1000 : pageSize);
			if (projectResearchList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return projectResearchListResponse;
			}
			var scoreMembers = [];
			var i = 0;
			projectResearchList.forEach(function (projectResearch) {
				scoreMembers.push(projectResearch.DProjectResearch.id);
				scoreMembers.push(projectResearch.DProjectResearch.id);
				//回写1000条时,不影响第一页的条数
				if (i < pageSize) {
					researchIdSet.add(projectResearch.DProjectResearch.id);
					i = i + 1;
				}
			});
			redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取资料
		let researchMap = await commonService.multiGetT(researchIdSet, DProjectResearch);
		for (let projectResearch of researchMap.values()) {
			if (projectResearch == null) {
				continue;
			}
			var projectResearchResponse = new ProjectResearchResponse();
			projectResearchResponse.title = projectResearch.title;
			projectResearchResponse.createTime = projectResearch.createTime;
			//projectResearchResponse.image = '';
			projectResearchListResponse.list.push(projectResearchResponse);
		}
		return projectResearchListResponse;
	},
	/**
	 * 添加一条调研资料评论
	 * @param researchId 资料id
	 * @param userId 用户id
	 * @param content 评论内容
	 * @param replyUserId 回复用户id
	 */
	addProjectResearchComment: async function (researchId, userId, content, replyUserId) {
		let projectResearchComment = await projectDao.addProjectResearchComment(researchId, userId, content, replyUserId);
		if (!projectResearchComment) {
			return null;
		}

		let projectResearchCommentKey = KeyFactory.projectResearchCommentKey(projectResearchComment.DProjectResearchComment.id);
		redis.STRINGS.setex(projectResearchCommentKey, timeUtil.day2Second(30), JSONObject.encodeT(projectResearchComment));

		let projectResearchCommentListKey = KeyFactory.projectResearchCommentListKey(researchId);
		redis.SORTSET.zadd(projectResearchCommentListKey, projectResearchComment.DProjectResearchComment.id, projectResearchComment.DProjectResearchComment.id);
		return projectResearchComment;
	},
	/**
	 * 调研资料评论列表
	 * @param researchId 资料id
	 * @param page 页码
	 * @param pageSize 页面大小
	 */
	getProjectResearchCommentList: async function (researchId, page, pageSize) {
		var key = KeyFactory.projectResearchCommentListKey(researchId);
		var start = (page - 1) * pageSize;
		var stop = start + pageSize - 1;

		let projectResearchCommentListResponse = new ProjectResearchCommentListResponse();
		var commentIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		if (commentIdSet.size == 0) {
			var commentList = await projectDao.getProjectResearchCommentList(researchId, page, pageSize);
			if (commentList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return projectResearchCommentListResponse;
			}
			var scoreMembers = [];
			commentList.forEach(function (projectResearchComment) {
				scoreMembers.push(projectResearchComment.DProjectResearchComment.id);
				scoreMembers.push(projectResearchComment.DProjectResearchComment.id);
				commentIdSet.add(projectResearchComment.DProjectResearchComment.id);
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}

		// 批量获取评论
		var userIdSet = new Set();
		let commentMap = await commonService.multiGetT(commentIdSet, DProjectResearchComment);
		for (let comment of commentMap.values()) {
			if (comment == null) {
				continue;
			}
			userIdSet.add(comment.userId);
			userIdSet.add(comment.replyUserId);
		}

		// 批量获取用户
		let userMap = await commonService.multiGetT(userIdSet, DUser);
		for (let comment of commentMap.values()) {
			if (comment == null) {
				continue;
			}
			let user = userMap.get(comment.userId);
			if (user == null) {
				continue;
			}
			let projectResearchComment = new ProjectResearchCommentResponse();
			projectResearchComment.userBase = new UserBase(user);
			projectResearchComment.createTime = comment.createTime;
			projectResearchComment.content = comment.content;
			let replyUser = userMap.get(comment.replyUserId);
			if (replyUser) {
				projectResearchComment.replyUserBase = replyUser;
			}
			projectResearchCommentListResponse.list.push(projectResearchComment);
		}
		return projectResearchCommentListResponse;
	}
};