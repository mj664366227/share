/**
 * 用户模块控制器
 */
const userService = requireService('userService');
const projectService = requireService('projectService');
const commonService = requireService('commonService');
const smsService = requireService("smsService");
const distributedSessionService = requireService("distributedSessionService");
var DUser = requireT("DUser");
var UserBase = requireHttpBase("UserBase");
var UserInfoResponse = requireHttpRes("UserInfoResponse");
var UserLoginResponse = requireHttpRes("UserLoginResponse");
let LOGGER = logUtil.getLogger(module.filename);
module.exports = {
	/**
	 * 用户登录
	 */
	userLogin: {
		url: URLCommand.userLogin,
		method: "post",
		func: async function (req, res, param) {
			let mobile = param.mobile;
			let password = param.password;
			if (!mobile || !password) {
				LOGGER.warn('mobile: %s, password: %s', mobile, password);
				return errorCode.systemError.parameterError;
			}

			if (!/^[0-9]{11,}$/.test(mobile)) {
				LOGGER.warn('mobile error, mobile: %s', mobile);
				return errorCode.systemError.parameterError;
			}

			// 获取用户
			let user = await userService.getUserByMobile(mobile);
			if (!user) {
				return errorCode.userError.userNotExists;
			}

			// 验证密码
			if (user.DUser.password !== await userService.genUserPassword(mobile, password)) {
				return errorCode.userError.passwordError;
			}

			// 写入session
			distributedSessionService.add(req, res, sessionKey, user.DUser.id);

			var userLoginResponse = new UserLoginResponse();
			userLoginResponse.userId = user.DUser.id;
			return userLoginResponse;
		}
	},
	/**
	 * 用户登出
	 */
	userLogout: {
		url: URLCommand.userLogout,
		method: "post",
		func: async function (req, res, param) {
			distributedSessionService.remove(req, res, sessionKey);
			return 1;
		}
	},
	/**
	 * 用户注册
	 */
	userReg: {
		url: URLCommand.userReg,
		method: "post",
		func: async function (req, res, param) {
			// 验证必填参数
			let mobile = param.mobile;
			let password = param.password;
			let code = param.code;
			let nickname = param.nickname;
			if (!mobile || !password || !code || !nickname) {
				LOGGER.warn('params error! mobile: %s, password: %s, code: %s, nickname: %s', mobile, password, code, nickname);
				return errorCode.systemError.parameterError;
			}

			// 验证手机号
			if (!/^[0-9]{11,}$/.test(mobile)) {
				LOGGER.warn('mobile error, mobile: %s', mobile);
				return errorCode.systemError.parameterError;
			}

			// 验证密码
			if (password.length < 6 || password.length > 16) {
				LOGGER.warn('password length error, password: %s', password);
				return errorCode.systemError.parameterError;
			}

			// 检查验证码
			if (code.length != 6) {
				LOGGER.warn('code length error, code: %s', code);
				return errorCode.systemError.parameterError;
			}

			// 检查验证码是否正确
			let result = smsService.checkVerificationCode(mobile, code);
			if (result == -1) {
				return errorCode.userError.verifyCodeTimeout;
			}

			if (result == 0) {
				return errorCode.userError.verifyCodeError;
			}

			// 判断用户名是否重复
			if (!await userService.nicknameOnly(nickname)) {
				LOGGER.warn('nickname is exists, nickname: %s', nickname);
				return errorCode.systemError.parameterError;
			}

			// 新增用户
			userService.addUser(mobile, password, nickname);
			return 1;
		}
	},
	/**
	 * 创意人列表
	 */
	creativeManList: {
		url: URLCommand.creativeManList,
		method: "get",
		func: async function (req, res, param) {
			let page = gatherupUtil.getPage(param.page);
			let pageSize = gatherupUtil.getPageSize(param.pageSize);
			let data = await userService.getCreativeManList(page, pageSize);
			data.page = page;
			data.pageSize = pageSize;
			data.maxPage = Math.ceil(data.total / pageSize);
			return data;
		}
	},
	/**
	 * 获取短信
	 */
	getsms: {
		url: URLCommand.getsms,
		method: "post",
		func: async function (req, res, param) {
			// 验证手机号
			let mobile = param.mobile;
			if (!mobile) {
				LOGGER.warn('check yzm,mobile is empty!');
				return errorCode.systemError.parameterError;
			}
			if (!/^[0-9]{11,}$/.test(mobile)) {
				LOGGER.warn('mobile error, mobile: %s', mobile);
				return errorCode.systemError.parameterError;
			}

			// 判断是否可以发送短信
			if (!await smsService.canSendVerificationCode(mobile)) {
				return errorCode.userError.operatingTooFast;
			}

			// 发送短信验证码
			smsService.sendVerificationCodeSMS(mobile);
			return 1;
		}
	},
	/**
	 * 用户名是否被注册
	 */
	userNameOnly: {
		url: URLCommand.userNameOnly,
		method: "get",
		func: async function (req, res, param) {
			// 判断昵称长度
			let nickname = param.nickname;
			if (!nickname) {
				LOGGER.warn('nickname is empty!');
				return errorCode.systemError.parameterError;
			}
			if (nickname.length > 15) {
				LOGGER.warn('nickname too long, nickname: %s', nickname);
				return errorCode.systemError.parameterError;
			}
			let boolean = await userService.nicknameOnly(nickname);
			return boolean ? 0 : 1;
		}
	},
	/**
	 * 手机是否被注册
	 */
	userMobileOnly: {
		url: URLCommand.userMobileOnly,
		method: "get",
		func: async function (req, res, param) {
			let mobile = param.mobile;
			if (!mobile) {
				LOGGER.warn('mobile is empty!');
				return errorCode.systemError.parameterError;
			}
			if (!/^1[0-9]{10}$/.test(mobile)) {
				LOGGER.warn('mobile error, mobile: %s', mobile);
				return errorCode.systemError.parameterError;
			}
			let user = await userService.userMobileOnly(mobile);
			return user ? 0 : 1;
		}
	},
	/**
	 * 个人资料
	 */
	userInfo: {
		url: URLCommand.userInfo,
		method: "post",
		func: async function (req, res, param) {
			let userId = param.userId;
			if (!userId) {
				LOGGER.warn('userId is empty!');
				return errorCode.systemError.parameterError;
			}
			let user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}
			return new UserInfoResponse(user);
		}
	},
	/**
	 * 修改个人资料
	 */
	userInfoUpdate: {
		url: URLCommand.userInfoUpdate,
		method: "post",
		func: async function (req, res, param) {
			// 昵称
			let nickname = param.nickname;
			if (!nickname) {
				LOGGER.warn('nickname is empty!');
				return errorCode.systemError.parameterError;
			}

			// 头像
			let avatarImage = param.avatarImage;

			// 邮箱
			let email = param.email;

			// 生日
			let birthday = parseInt(param.birthday);
			if (birthday) {
				let now = timeUtil.now();
				birthday = timeUtil.str2Time(birthday);
				if (birthday >= now) {
					logger.warn("birthday more than now or birthday is illegal, birthday %s:", param.birthday);
					return errorCode.systemError.parameterError;
				}
			}

			// 验证省市id
			let provinceId = parseInt(param.provinceId);
			let cityId = parseInt(param.cityId);

			// 真实姓名
			let realname = param.realname;

			// 特长
			let specialty = param.specialty;

			// 公司
			let agency = param.agency;

			// 职业
			let job = param.job;

			// 身份证
			let identityCard = param.identityCard;

			// 验证用户id
			let userId = parseInt(param.userId);
			if (!userId) {
				LOGGER.warn('userId is error, userId: %s', userId);
				return errorCode.systemError.parameterError;
			}
			var user = await userService.getUserById(userId);
			if (!user) {
				LOGGER.warn('user not exists, userId: %s', userId);
				return errorCode.userError.userNotExists;
			}

			// 修改个人资料
			userService.updateUserInfo(user);
			return 1;
		}
	}
};