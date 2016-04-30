// userDao

var DUser = requireT('DUser');
var DUserProve = requireT('DUserProve');

module.exports = {
	/**
	 * 查询用户 By 用户id
	 * @param userId 用户id
	 */
	getUserById: async function (userId) {
		return await guSlaveDbService.queryT("SELECT * FROM `user` WHERE `id`=?", DUser, userId);
	},
	/**
	 * 查询用户 By 手机
	 * @param mobile 手机号
	 */
	getUserByMobile: async function (mobile) {
		return await guSlaveDbService.queryT("SELECT * FROM `user` WHERE `mobile`=?", DUser, mobile);
	},
	/**
	 * 用户注册
	 * @param mobile 用户手机
	 * @param password 登录密码
	 * @param nickname 用户昵称
	 */
	addUser: async function (mobile, password, nickname) {
		let user = new DUser();
		user.DUser.nickname = nickname;
		user.DUser.password = password;
		user.DUser.mobile = mobile;
		user.DUser.qq = "";
		user.DUser.wechat = "";
		user.DUser.weibo = "";
		user.DUser.sex = 2;
		user.DUser.provinceId = 0;
		user.DUser.avatarImage = "";
		user.DUser.signature = "";
		user.DUser.birthday = 0;
		user.DUser.status = 0;
		user.DUser.identity = "";
		user.DUser.code = "";
		user.DUser.points = 0;
		user.DUser.level = 1;
		user.DUser.exp = 0;
		user.DUser.score = 0;
		user.DUser.createTime = 0;
		user.DUser.isProve = 0;
		user.DUser.lastLoginTime = 0;
		user.DUser.platform = 5;// PC注册
		user.DUser.caseNum = 0;
		user.DUser.tagNum = 0;
		user.DUser.pushKey = "";
		user.DUser.paymentPassword = "";
		user.DUser.job = "";
		user.DUser.specialty = "";
		user.DUser.creativeMan = 0;
		return await guMasterDbService.save(user);
	},
	/**
	 * 记录新增用户日志
	 */
	newUserLog: function () {
		let date = parseInt(timeUtil.date("YYYYMMDD"));
		let h = parseInt(timeUtil.date("HH")) + 1;
		let sql = 'insert into `new_user` (`date`,`h' + h + '`) values ';
		sql += "('" + date + "','1') on duplicate key update ";
		sql += "`h" + h + "`=`h" + h + "`+1";
		guLogDbService.query(sql);
	},
	/**
	 * 判断用户昵称是否唯一
	 * @param nickname 用户昵称
	 */
	nicknameOnly: async function (nickname) {
		let user = await guSlaveDbService.query("SELECT `nickname` FROM `user` WHERE `nickname`=? limit 1", nickname);
		if (user.length <= 0) {
			return true;
		}
		return false;
	},
	/**
	 * 判断用户手机号是否唯一
	 * @param mobile 手机号
	 */
	userMobileOnly: async function (mobile) {
		let user = await guSlaveDbService.query("SELECT `mobile` FROM `user` WHERE `mobile`=? limit 1", mobile);
		if (user.length <= 0) {
			return true;
		}
		return false;
	},
	/**
	 * 添加实名认证信息
	 * @param userId 用户id
	 * @param proveInfo 认证信息
	 * @param identityCard 身份证
	 */
	addUserProve: async function (userId, proveInfo, identityCard) {
		let userProve = new DUserProve();
		userProve.DUserProve.userId = userId;
		userProve.DUserProve.proveInfo = proveInfo;
		userProve.DUserProve.identityCard = identityCard;
		userProve.DUserProve.otherCard = "";
		userProve.DUserProve.isProve = 0;
		userProve.DUserProve.createTime = timeUtil.now();
		userProve.DUserProve.checkTime = 0;
		return await guMasterDbService.save(userProve);
	},
	/**
	 * 获取用户实名认证信息
	 * @param userId 用户id
	 * @returns {Array.<DUserProve>} Array&lt;DUserProve&gt;
	 */
	getUserProve: async function (userId) {
		let sql = "select * from `user_prove` where `user_id`=?";
		return await guSlaveDbService.queryT(sql, DUserProve, userId);
	},
	/**
	 * 更新实名认证信息
	 * @param userId 用户id
	 * @param proveInfo 认证信息
	 * @param identityCard 身份证
	 */
	updateUserProve: function (userId, proveInfo, identityCard) {
		let sql = "update `user_prove` SET `prove_info`=?,`identity_card`=?,`other_card`=? where `user_id`=?";
		guMasterDbService.query(sql, proveInfo, identityCard, userId);
	},
	/**
	 * 修改用户资料
	 * @param user 用户对象
	 */
	updateUserInfo: function (user) {
		guMasterDbService.updateT(user);
	},
	/**
	 * 创意人列表
	 * @param page
	 * @param pageSize
	 * @returns {Array.<DUser>} Array&lt;DUser&gt;
	 */
	getCreativeManList: async function (page, pageSize) {
		var sql = "select * from `user` where `creative_man`=1 order by `id` desc limit ?,?";
		return await guSlaveDbService.queryTList(sql, DUser, (page - 1) * pageSize, pageSize);
	}
};