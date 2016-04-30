const commonService = requireService('commonService');
const userDao = requireDao("userDao");
const DUser = requireT("DUser");
const UserBase = requireHttpBase("UserBase");
const UserBaseListResponse = requireHttpRes("UserBaseListResponse");
module.exports = {
	/**
	 * 查询用户 By 用户id
	 * @param userId 用户id
	 */
	getUserById: async function (userId) {
		let userKey = KeyFactory.userKey(userId);
		let json = await redis.STRINGS.get(userKey);
		let user = null;
		if (!json) {
			user = await userDao.getUserById(userId);
			if (!user) {
				return null;
			}
			json = JSONObject.encodeT(user);
			redis.STRINGS.setex(userKey, timeUtil.day2Second(30), json);
			redis.STRINGS.setex(KeyFactory.mobileKey(user.DUser.mobile), timeUtil.day2Second(30), json);
			return user;
		}
		user = JSONObject.decodeT(json, DUser);
		if (!user) {
			return null;
		}

		user.DUser.points = await commonService.getNumColumn(NumKey.userPoints, user.DUser.id);
		user.DUser.score = await commonService.getNumColumn(NumKey.userScore, user.DUser.id);
		user.DUser.caseNum = await commonService.getNumColumn(NumKey.userCaseNum, user.DUser.id);
		user.DUser.tagNum = await commonService.getNumColumn(NumKey.userTagNum, user.DUser.id);
		user.DUser.exp = await commonService.getNumColumn(NumKey.userExp, user.DUser.id);
		user.DUser.level = await commonService.getNumColumn(NumKey.userLevel, user.DUser.id);
		return user;
	},
	/**
	 * 查询用户 By 手机
	 * @param mobile 手机号
	 */
	getUserByMobile: async function (mobile) {
		let mobileKey = KeyFactory.mobileKey(mobile);
		let json = await redis.STRINGS.get(mobileKey);
		let user = null;
		if (!json) {
			user = await userDao.getUserByMobile(mobile);
			if (!user) {
				return null;
			}
			json = JSONObject.encodeT(user);
			redis.STRINGS.setex(KeyFactory.userKey(user.DUser.id), timeUtil.day2Second(30), json);
			redis.STRINGS.setex(mobileKey, timeUtil.day2Second(30), json);
			return user;
		}
		user = JSONObject.decodeT(json, DUser);
		if (!user) {
			return null;
		}

		user.DUser.points = await commonService.getNumColumn(NumKey.userPoints, user.DUser.id);
		user.DUser.score = await commonService.getNumColumn(NumKey.userScore, user.DUser.id);
		user.DUser.caseNum = await commonService.getNumColumn(NumKey.userCaseNum, user.DUser.id);
		user.DUser.tagNum = await commonService.getNumColumn(NumKey.userTagNum, user.DUser.id);
		user.DUser.exp = await commonService.getNumColumn(NumKey.userExp, user.DUser.id);
		user.DUser.level = await commonService.getNumColumn(NumKey.userLevel, user.DUser.id);
		return user;
	},
	/**
	 * 生成用户登录密码
	 * @param mobile 用户手机
	 * @param password 登录密码
	 */
	genUserPassword: async function (mobile, password) {
		let systemKey = properties['system.key'];
		return secret.md5(secret.sha1(password) + systemKey + base64.encode(mobile));
	},
	/**
	 * 用户注册
	 * @param mobile 用户手机
	 * @param password 登录密码
	 * @param nickname 用户昵称
	 */
	addUser: async function (mobile, password, nickname) {
		// 写入数据库
		password = await this.genUserPassword(mobile, password);
		let user = await userDao.addUser(mobile, password, nickname);
		let json = JSON.stringify(user.DUser);

		// 写入redis
		redis.STRINGS.setex(KeyFactory.userKey(user.DUser.id), timeUtil.day2Second(30), json);
		redis.STRINGS.setex(KeyFactory.mobileKey(user.DUser.mobile), timeUtil.day2Second(30), json);

		// 新增用户日志
		userDao.newUserLog();
		return user.DUser;
	},
	/**
	 * 判断用户昵称是否唯一
	 * @param nickname 用户昵称
	 */
	nicknameOnly: async function (nickname) {
		return await userDao.nicknameOnly(nickname);
	},
	/**
	 * 判断用户手机号是否唯一
	 * @param mobile 手机号
	 */
	userMobileOnly: async function (mobile) {
		return await userDao.userMobileOnly(mobile);
	},
	/**
	 * 添加实名认证信息
	 * @param userId 用户id
	 * @param proveInfo 认证信息
	 * @param identityCard 身份证
	 */
	addUserProve: async function (userId, proveInfo, identityCard) {
		await userDao.addUserProve(userId, proveInfo, identityCard);
	},
	/**
	 * 获取用户实名认证信息
	 * @param userId 用户id
	 */
	getUserProve: async function (userId) {
		return await userDao.getUserProve(userId);
	},
	/**
	 * 更新实名认证信息
	 * @param userId 用户id
	 * @param proveInfo 认证信息
	 * @param identityCard 身份证
	 */
	updateUserProve: function (userId, proveInfo, identityCard) {
		userDao.updateUserProve(userId, proveInfo, identityCard);
	},
	/**
	 * 修改用户资料
	 * @param user 用户对象
	 */
	updateUserInfo: function (user) {
		userDao.updateUserInfo(user);
	},
	/**
	 * 创意人列表
	 * @param page
	 * @param pageSize
	 */
	getCreativeManList: async function (page, pageSize) {
		var key = KeyFactory.userCreativeManListKey();
		var start = (page - 1) * pageSize;
		var stop = start + pageSize - 1;

		var userBaseListResponse = new UserBaseListResponse();
		userBaseListResponse.total = await redis.SORTSET.zcard(key);

		var userIdSet = new Set(await redis.SORTSET.zrevrange(key, start, stop));
		if (userIdSet.size == 0) {
			var userList = await userDao.getCreativeManList(page, page == 1 ? 1000 : pageSize);
			if (userList.length == 0) {
				// 穿透db时加占位
				commonService.addSortSetAnchor(key);
				// 数据库也没有，返回
				return userBaseListResponse;
			}
			var scoreMembers = [];
			var i = 0;
			userList.forEach(function (user) {
				scoreMembers.push(user.DUser.id);
				scoreMembers.push(user.DUser.id);
				if (i < pageSize) {//回写1000条时,不影响第一页的条数
					userIdSet.add(user.DUser.id);
					i = i + 1;
				}
			});
			await redis.SORTSET.zadd(key, scoreMembers);
		}
		var userMap = await commonService.multiGetT(userIdSet, DUser);
		for (let user of userMap.values()) {
			if (user == null) {
				continue;
			}
			let userBase = new UserBase(user);
			userBaseListResponse.list.push(userBase);
		}
		return userBaseListResponse;
	}
};