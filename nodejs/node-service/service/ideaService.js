const commonService = requireService('commonService');
const ideaDao = requireDao("ideaDao");
const DUser = requireT('DUser');
const DProjectIdea = requireT('DProjectIdea');
const DProjectIdeaComment = requireT('DProjectIdeaComment');
const DProjectIdeaCommentReply = requireT('DProjectIdeaCommentReply');
const DProjectIdeaFile = requireT('DProjectIdeaFile');
const DProjectIdeaLink = requireT('DProjectIdeaLink');
const DProjectIdeaAdded = requireT('DProjectIdeaAdded');
const ProjectIdeaFileResponse = requireHttpRes('ProjectIdeaFileResponse');
const ProjectIdeaLinkResponse = requireHttpRes('ProjectIdeaLinkResponse');
const ProjectIdeaAddedResponse = requireHttpRes('ProjectIdeaAddedResponse');
const ProjectIdeaBaseListResponse = requireHttpRes('ProjectIdeaBaseListResponse');
const ProjectIdeaBase = requireHttpBase('ProjectIdeaBase');
const UserBase = requireHttpBase('UserBase');
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
		let projectIdea = await ideaDao.addProjectIdea(userId, projectId, title, tag, background, detail, research, cover, inspiration);
		if (!projectIdea) {
			return null;
		}
		let ideaId = projectIdea.DProjectIdea.id;
		// 统计缓存
		commonService.setNumColumn(NumKey.projectIdeaPraise, ideaId, 0);
		commonService.setNumColumn(NumKey.projectIdeaCommentNum, ideaId, 0);
		// 实体数据缓存
		redis.STRINGS.setex(KeyFactory.projectIdeaKey(ideaId), timeUtil.day2Second(30), JSONObject.encodeT(projectIdea));
		// 项目创意最近更新排列
		redis.SORTSET.zadd(KeyFactory.projectIdeaLastEditTopKey(projectId), projectIdea.DProjectIdea.lastEditTime, ideaId);
		redis.SORTSET.zadd(KeyFactory.projectIdeaLastEditTopKey(projectId, tag), projectIdea.DProjectIdea.lastEditTime, ideaId);
		// 项目创意点赞数排列
		redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseTopKey(projectId), 0, ideaId);
		redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseTopKey(projectId, tag), 0, ideaId);
		// 项目创意评论数排列
		redis.SORTSET.zadd(KeyFactory.projectIdeaCommentNumTopKey(projectId), 0, ideaId);
		redis.SORTSET.zadd(KeyFactory.projectIdeaCommentNumTopKey(projectId, tag), 0, ideaId);

		return projectIdea;
	},
	/**
	 * 更新点子
	 * @param idea
	 * @returns {Boolean}
	 */
	updateProjectIdea: async function (idea) {
		let updaetSuccess = await ideaDao.updateProjectIdea(idea);
		if (updaetSuccess) {
			let projectId = idea.DProjectIdea.projectId;
			let tag = idea.DProjectIdea.tag;
			// 实体数据缓存
			redis.STRINGS.setex(KeyFactory.projectIdeaKey(ideaId), timeUtil.day2Second(30), JSONObject.encodeT(idea));
			// 更新项目创意最近更新排列
			redis.SORTSET.zadd(KeyFactory.projectIdeaLastEditTopKey(projectId), idea.DProjectIdea.lastEditTime, ideaId);
			redis.SORTSET.zadd(KeyFactory.projectIdeaLastEditTopKey(projectId, tag), idea.DProjectIdea.lastEditTime, ideaId);
		}
		return updaetSuccess;
	},
	/**
	 * 查询点子 By ideaId
	 * @param ideaId
	 * @returns {DProjectIdea}
	 */
	getProjectIdeaById: async function (ideaId) {
		let jsonString = await redis.STRINGS.get(KeyFactory.projectIdeaKey(ideaId));
		var idea = new DProjectIdea();
		if (!jsonString) {
			idea = await ideaDao.getProjectIdeaById(ideaId);
			if (idea == null) {
				return null;
			}
			// 获取统计数据
			idea.DProjectIdea.praise = await commonService.getNumColumn(NumKey.projectIdeaPraise, idea.DProjectIdea.id);
			idea.DProjectIdea.commentNum = await commonService.getNumColumn(NumKey.projectIdeaCommentNum, idea.DProjectIdea.id);
			// 回写Redis缓存
			redis.STRINGS.setex(KeyFactory.projectIdeaKey(ideaId), timeUtil.day2Second(30), JSONObject.encodeT(idea));
			return idea;
		}
		idea = JSONObject.decodeT(jsonString, DProjectIdea);
		if (idea == null) {
			return null;
		}
		// 获取统计数据
		idea.DProjectIdea.praise = await commonService.getNumColumn(NumKey.projectIdeaPraise, idea.DProjectIdea.id);
		idea.DProjectIdea.commentNum = await commonService.getNumColumn(NumKey.projectIdeaCommentNum, idea.DProjectIdea.id);
		return idea;
	},
	/**
	 * 添加点子点赞记录
	 * @param idea 点子{DProjectIdea}
	 * @param userId
	 */
	addProjectIdeaPraise: async function (idea, userId) {
		let ideaId = idea.DProjectIdea.id;
		let projectId = idea.DProjectIdea.projectId;
		let tag = idea.DProjectIdea.tag;

		let ideaPraise = await ideaDao.addProjectIdeaPraise(ideaId, userId, projectId);
		if (!ideaPraise) {
			return null;
		}
		let ideaPraiseNum = await commonService.updateNumColumn(NumKey.projectIdeaPraise, ideaId, 1);

		// 项目创意点赞数排列
		redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseTopKey(projectId), ideaPraiseNum, ideaId);
		redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseTopKey(projectId, tag), ideaPraiseNum, ideaId);

		redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseList(ideaId), userId, userId);
		return ideaPraise;
	},
	/**
	 * 删除点子点赞记录
	 * @param idea 点子{DProjectIdea}
	 * @param userId
	 * @returns {Boolean}
	 */
	delProjectIdeaPraise: async function (idea, userId) {
		let ideaId = idea.DProjectIdea.id;
		let projectId = idea.DProjectIdea.projectId;
		let tag = idea.DProjectIdea.tag;

		let delSuccess = await ideaDao.delProjectIdeaPraise(ideaId, userId);
		if (delSuccess) {
			let ideaPraiseNum = await commonService.updateNumColumn(NumKey.projectIdeaPraise, ideaId, -1);

			// 项目创意点赞数排列
			redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseTopKey(projectId), ideaPraiseNum, ideaId);
			redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseTopKey(projectId, tag), ideaPraiseNum, ideaId);

			redis.SORTSET.zrem(KeyFactory.projectIdeaPraiseList(ideaId), userId);
		}
		return delSuccess;
	},
	/**
	 * 是否点赞
	 * @param ideaId
	 * @param userId
	 * @returns {Number} 是=1,否=0
	 */
	isProjectIdeaPraise: async function (ideaId, userId) {
		let isPraise = await redis.SORTSET.zscore(KeyFactory.projectIdeaPraiseList(ideaId), userId);
		if (!isPraise) {
			var projectIdeaPraise = await ideaDao.getProjectIdeaPraise(ideaId, userId);
			if (!projectIdeaPraise) {
				return 0;
			}
			redis.SORTSET.zadd(KeyFactory.projectIdeaPraiseList(ideaId), userId, userId);
			return 1;
		}
		return 1;
	},
	/**
	 * 添加点子附件
	 * @param ideaId
	 * @param name
	 * @param link
	 * @returns {DProjectIdeaFile}
	 */
	addProjectIdeaFile: async function (ideaId, name, link) {
		let ideaFile = await ideaDao.addProjectIdeaFile(ideaId, name, link);
		if (!ideaFile) {
			return null;
		}
		let ideaFileId = ideaFile.DProjectIdeaFile.id;
		redis.STRINGS.setex(KeyFactory.projectIdeaFileKey(ideaFileId), timeUtil.day2Second(30), JSONObject.encodeT(ideaFile));
		redis.SORTSET.zadd(KeyFactory.projectIdeaFileList(ideaId), ideaFileId, ideaFileId);
		return ideaFile;
	},
	/**
	 * 删除点子附件
	 * @param ideaId
	 * @param ideaFileId
	 * @returns {Boolean}
	 */
	delProjectIdeaFile: async function (ideaId, ideaFileId) {
		let delSuccess = await ideaDao.delProjectIdeaFile(ideaId, ideaFileId);
		if (delSuccess) {
			redis.KEYS.del(KeyFactory.projectIdeaFileKey(ideaFileId));
			redis.SORTSET.zrem(KeyFactory.projectIdeaFileList(ideaId), ideaFileId);
		}
		return delSuccess;
	},
	/**
	 * 获取点子附件列表
	 * @param ideaId
	 * @returns {Array.<ProjectIdeaFileResponse>}
	 */
	getProjectIdeaFileList: async function (ideaId) {
		let key = KeyFactory.projectIdeaFileList(ideaId);
		var ideaFileIdSet = new Set(await redis.SORTSET.zrevrange(key, 0, -1));
		if (ideaFileIdSet.size == 0) {
			var ideaFileList = await ideaDao.getProjectIdeaFileList(ideaId);
			if (ideaFileList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return [];
			}
			var scoreMembers = [];
			ideaFileList.forEach(function (ideaFile) {
				scoreMembers.push(ideaFile.DProjectIdeaFile.id);
				scoreMembers.push(ideaFile.DProjectIdeaFile.id);
				ideaFileIdSet.add(ideaFile.DProjectIdeaFile.id);
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}
		let ideaFileMap = await commonService.multiGetT(ideaFileIdSet, DProjectIdeaFile);
		// 组成返回协议
		var resArray = [];
		for (let ideaFile of ideaFileMap.values()) {
			if (ideaFile == null) {
				continue;
			}
			let projectIdeaFileResponse = new ProjectIdeaFileResponse(ideaFile);
			resArray.push(projectIdeaFileResponse);
		}
		return resArray;
	},
	/**
	 *  添加点子链接
	 * @param ideaId
	 * @param name
	 * @param link
	 * @returns {DProjectIdeaLink}
	 */
	addProjectIdeaLink: async function (ideaId, name, link) {
		let ideaLink = await ideaDao.addProjectIdeaLink(ideaId, name, link);
		if (!ideaLink) {
			return null;
		}
		let ideaLinkId = ideaLink.DProjectIdeaLink.id;
		redis.STRINGS.setex(KeyFactory.projectIdeaLinkKey(ideaLinkId), timeUtil.day2Second(30), JSONObject.encodeT(ideaLink));
		redis.SORTSET.zadd(KeyFactory.projectIdeaLinkList(ideaId), ideaLinkId, ideaLinkId);
		return ideaLink;
	},
	/**
	 * 删除点子链接
	 * @param ideaId
	 * @param ideaLinkId
	 * @returns {Boolean}
	 */
	delProjectIdeaLink: async function (ideaId, ideaLinkId) {
		let delSuccess = await ideaDao.delProjectIdeaLink(ideaId, ideaLinkId);
		if (delSuccess) {
			redis.KEYS.del(KeyFactory.projectIdeaLinkKey(ideaLinkId));
			redis.SORTSET.zrem(KeyFactory.projectIdeaLinkList(ideaId), ideaLinkId);
		}
		return delSuccess;
	},
	/**
	 * 获取点子链接列表
	 * @param ideaId
	 * @returns {Array.<ProjectIdeaLinkResponse>}
	 */
	getProjectIdeaLinkList: async function (ideaId) {
		let key = KeyFactory.projectIdeaLinkList(ideaId);
		var ideaLinkIdSet = new Set(await redis.SORTSET.zrevrange(key, 0, -1));
		if (ideaLinkIdSet.size == 0) {
			var ideaLinkList = await ideaDao.getProjectIdeaLinkList(ideaId);
			if (ideaLinkList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return [];
			}
			var scoreMembers = [];
			ideaLinkList.forEach(function (ideaLink) {
				scoreMembers.push(ideaLink.DProjectIdeaLink.id);
				scoreMembers.push(ideaLink.DProjectIdeaLink.id);
				ideaLinkIdSet.add(ideaLink.DProjectIdeaLink.id);
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}
		let ideaLinkMap = await commonService.multiGetT(ideaLinkIdSet, DProjectIdeaLink);
		// 组成返回协议
		var resArray = [];
		for (let ideaLink of ideaLinkMap.values()) {
			if (ideaLink == null) {
				continue;
			}
			let projectIdeaLinkResponse = new ProjectIdeaLinkResponse(ideaLink);
			resArray.push(projectIdeaLinkResponse);
		}
		return resArray;
	},
	/**
	 * 添加一条点子补充
	 * @param idea 点子{DProjectIdea}
	 * @param userId
	 * @param content
	 * @returns {DProjectIdeaAdded}
	 */
	addProjectIdeaAdded: async function (idea, userId, content) {
		let ideaId = idea.DProjectIdea.id;
		let projectId = idea.DProjectIdea.projectId;
		let tag = idea.DProjectIdea.tag;

		let ideaAdded = await ideaDao.addProjectIdeaAdded(ideaId, userId, content);
		if (!ideaAdded) {
			return null;
		}
		let ideaAddedId = ideaAdded.DProjectIdeaAdded.id;
		redis.STRINGS.setex(KeyFactory.projectIdeaAddedKey(ideaAddedId), timeUtil.day2Second(30), JSONObject.encodeT(ideaAdded));
		redis.SORTSET.zadd(KeyFactory.projectIdeaAddedList(ideaId), ideaAddedId, ideaAddedId);

		// 项目创意最近更新排列
		redis.SORTSET.zadd(KeyFactory.projectIdeaLastEditTopKey(projectId), ideaAdded.DProjectIdeaAdded.createTime, ideaId);
		redis.SORTSET.zadd(KeyFactory.projectIdeaLastEditTopKey(projectId, tag), ideaAdded.DProjectIdeaAdded.createTime, ideaId);

		return ideaAdded;
	},
	/**
	 * 点子补充列表
	 * @param ideaId
	 * @param page
	 * @param pageSize
	 * @returns {Array.<ProjectIdeaAddedResponse>}
	 */
	getProjectIdeaAddedList: async function (ideaId, page, pageSize) {
		var key = KeyFactory.projectIdeaAddedList(ideaId);
		var start = (page - 1) * pageSize;
		var stop = start + pageSize - 1;

		var ideaAddedIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		if (ideaAddedIdSet.size == 0) {
			var ideaAddedList = await ideaDao.getProjectIdeaAddedList(ideaId, page, page == 1 ? 1000 : pageSize);
			if (ideaAddedList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return [];
			}
			var scoreMembers = [];
			var i = 0;
			ideaAddedList.forEach(function (ideaAdded) {
				scoreMembers.push(ideaAdded.DProjectIdeaAdded.id);
				scoreMembers.push(ideaAdded.DProjectIdeaAdded.id);
				//回写1000条时,不影响第一页的条数
				if (i < pageSize) {
					ideaAddedIdSet.add(ideaAdded.DProjectIdeaAdded.id);
					i = i + 1;
				}
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}
		var ideaAddedMap = await commonService.multiGetT(ideaAddedIdSet, DProjectIdeaAdded);
		// 组成返回协议
		var resArray = [];
		for (let ideaAdded of ideaAddedMap.values()) {
			if (ideaAdded == null) {
				continue;
			}
			let projectIdeaAddedResponse = new ProjectIdeaAddedResponse(ideaAdded);
			resArray.push(projectIdeaAddedResponse);
		}
		return resArray;
	},
	/**
	 * 添加一条评论
	 * @param idea 点子{DProjectIdea}
	 * @param userId 用户id
	 * @param content 评论内容
	 * @param replyUserId 回复用户内容
	 * @returns {DProjectIdeaComment}
	 */
	addProjectIdeaComment: async function (idea, userId, content, replyUserId) {
		let ideaId = idea.DProjectIdea.id;
		let projectId = idea.DProjectIdea.projectId;
		let tag = idea.DProjectIdea.tag;
		let ideaComment = await ideaDao.addProjectIdeaComment(ideaId, userId, content, replyUserId);
		if (!ideaComment) {
			return null;
		}
		let ideaCommentId = ideaComment.DProjectIdeaComment.id;
		commonService.setNumColumn(NumKey.projectIdeaCommentReplyNum, ideaCommentId, 0);
		let ideaCommentNum = await commonService.updateNumColumn(NumKey.projectIdeaCommentNum, ideaId, 1);

		// 更新项目创意评论数排列
		redis.SORTSET.zadd(KeyFactory.projectIdeaCommentNumTopKey(projectId), ideaCommentNum, ideaId);
		redis.SORTSET.zadd(KeyFactory.projectIdeaCommentNumTopKey(projectId, tag), ideaCommentNum, ideaId);

		redis.STRINGS.setex(KeyFactory.projectIdeaCommentKey(ideaCommentId), timeUtil.day2Second(30), JSONObject.encodeT(ideaComment));
		redis.SORTSET.zadd(KeyFactory.projectIdeaCommentList(ideaId), ideaCommentId, ideaCommentId);
		return ideaComment;
	},
	/**
	 * 查询点子评论
	 * @param ideaCommentId
	 * @returns {DProjectIdeaComment}
	 */
	getProjectIdeaCommentById: async function (ideaCommentId) {
		let jsonString = await redis.STRINGS.get(KeyFactory.projectIdeaCommentKey(ideaCommentId));
		var ideaComment = new DProjectIdeaComment();
		if (!jsonString) {
			ideaComment = await ideaDao.getProjectIdeaCommentById(ideaCommentId);
			if (ideaComment == null) {
				return null;
			}
			// 获取统计数据
			ideaComment.DProjectIdeaComment.replyNum = await commonService.getNumColumn(NumKey.projectIdeaCommentReplyNum, ideaComment.DProjectIdeaComment.id);
			// 回写Redis缓存
			redis.STRINGS.setex(KeyFactory.projectIdeaCommentKey(ideaCommentId), timeUtil.day2Second(30), JSONObject.encodeT(ideaComment));
			return ideaComment;
		}
		ideaComment = JSONObject.decodeT(jsonString, DProjectIdeaComment);
		if (ideaComment == null) {
			return null;
		}
		// 获取统计数据
		ideaComment.DProjectIdeaComment.replyNum = await commonService.getNumColumn(NumKey.projectIdeaCommentReplyNum, ideaComment.DProjectIdeaComment.id);
		return ideaComment;
	},
	/**
	 * 添加点子评论回复
	 * @param userId
	 * @param ideaCommentId
	 * @param content
	 * @param replyUserId
	 * @returns {DProjectIdeaCommentReply}
	 */
	addProjectIdeaCommentReply: async function (userId, ideaCommentId, content, replyUserId) {
		let ideaCommentReply = await ideaDao.addProjectIdeaCommentReply(userId, ideaCommentId, content, replyUserId);
		if (!ideaCommentReply) {
			return null;
		}
		let ideaCommentReplyId = ideaCommentReply.DProjectIdeaCommentReply.id;
		commonService.updateNumColumn(NumKey.projectIdeaCommentReplyNum, ideaCommentId, 1);
		redis.STRINGS.setex(KeyFactory.projectIdeaCommentReplyKey(ideaCommentReplyId), timeUtil.day2Second(30), JSONObject.encodeT(ideaCommentReply));
		redis.SORTSET.zadd(KeyFactory.projectIdeaCommentReplyList(ideaCommentId), ideaCommentReplyId, ideaCommentReplyId);
		return ideaCommentReply;
	},
	/**
	 * 获取点子列表
	 * @param currentUserId 当前用户Id
	 * @param orderFieldName 排序字段
	 * @param projectId 项目id
	 * @param tag 标签
	 * @param page
	 * @param pageSize
	 * @param order 0=正序,1=倒序
	 */
	getProjectIdeaList: async function (currentUserId, orderFieldName, projectId, tag, page, pageSize, order) {
		let key = "";
		if (orderFieldName == "lastEditTime") {
			key = KeyFactory.projectIdeaLastEditTopKey(projectId, tag);
		}
		if (orderFieldName == "praise") {
			key = KeyFactory.projectIdeaPraiseTopKey(projectId, tag);
		}
		if (orderFieldName == "commentNum") {
			key = KeyFactory.projectIdeaCommentNumTopKey(projectId, tag);
		}
		if (!key) {
			throw new Error("ideaService.getProjectIdeaList orderFieldName is Error! orderFieldName: " + orderFieldName);
		}

		var start = (page - 1) * pageSize;
		var stop = start + pageSize - 1;

		var ideaBaseListResponse = new ProjectIdeaBaseListResponse();

		var ideaIdSet = new Set();
		// 区分排序
		if (order == 1) {
			ideaIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		} else {
			ideaIdSet = new Set(await redis.SORTSET.zrange(key, start, stop));
		}

		if (ideaIdSet.size == 0) {
			var ideaList = await ideaDao.getProjectIdeaList(orderFieldName, projectId, tag, page, page == 1 ? 1000 : pageSize, order);
			if (ideaList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return ideaBaseListResponse;
			}
			var scoreMembers = [];
			var i = 0;
			ideaList.forEach(function (idea) {
				scoreMembers.push(idea.DProjectIdea[orderFieldName]);
				scoreMembers.push(idea.DProjectIdea.id);
				//回写1000条时,不影响第一页的条数
				if (i < pageSize) {
					ideaIdSet.add(idea.DProjectIdea.id);
					i = i + 1;
				}
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}
		// 获取全部用户id
		var userIdSet = new Set();
		var ideaMap = await commonService.multiGetT(ideaIdSet, DProjectIdea);
		for (let idea of ideaMap.values()) {
			if (idea == null) {
				continue;
			}
			userIdSet.add(idea.DProjectIdea.userId);
		}
		// 批量获取用户
		var userMap = await commonService.multiGetT(userIdSet, DUser);
		// 组成返回协议
		for (let idea of ideaMap.values()) {
			if (idea == null) {
				continue;
			}
			let ideaBase = new ProjectIdeaBase(idea);
			let user = userMap.get(idea.DProjectIdea.userId);
			ideaBase.userBase = new UserBase(user);
			if (!currentUserId) {
				// 当前用户是否点赞
				ideaBase.isPraise = await this.isProjectIdeaPraise(ideaId, currentUserId);
			}
			ideaBaseListResponse.list.push(ideaBase);
		}
		return ideaBaseListResponse;
	}
};