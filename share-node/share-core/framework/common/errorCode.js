// 错误码
// 格式：3001001(abbbccc)
// a：固定编号
// b：模块编号
// c：错误编号
var errorCode = function (errorCode, errorMessage) {
	return {
		errorCode: errorCode,
		errorMessage: errorMessage
	}
};

module.exports = {
	/**
	 * 系统模块
	 */
	systemError: {
		/**
		 * sign错误(1001001)
		 */
		signError: errorCode(1001001, "sign错误"),
		/**
		 * 接口超时(1001002)
		 */
		timeOut: errorCode(1001002, "接口超时"),
		/**
		 * 请求参数错误(1001003)
		 */
		parameterError: errorCode(1001003, "请求参数错误"),
		/**
		 * 系统未知错误(1001004)
		 */
		unknowError: errorCode(1001004, "系统未知错误"),
		/**
		 * 图片没有上传(1001005)
		 */
		imageNullError: errorCode(1001005, "图片没有上传"),
		/**
		 * 亲,你的版本太旧了,快去更新吧！(1001006)
		 */
		versionOldError: errorCode(1001006, "亲，你的版本太旧了，快去更新吧！"),
		/**
		 * 您未登录(1001007)
		 */
		noLoginError: errorCode(1001007, "亲爱的用户，您尚未登录！")
	},
	/**
	 * 创意人模块
	 */
	userError: {
		/**
		 * 创意人不存在(3001001)
		 */
		userNotExists: errorCode(3001001, "创意人不存在"),
		/**
		 * 密码错误(3001002)
		 */
		passwordError: errorCode(3001002, "密码错误"),
		/**
		 * 您的操作太快啦(3001003)
		 */
		operatingTooFast: errorCode(3001003, "您的操作太快啦"),
		/**
		 * 验证码超时(3001004)
		 */
		verifyCodeTimeout: errorCode(3001004, "验证码超时"),
		/**
		 * 验证码错误(3001005)
		 */
		verifyCodeError: errorCode(3001005, "验证码错误"),
		/**
		 * 被回复的用户不存在(3001006)
		 */
		replyUserNotExists: errorCode(3001006, "被回复的用户不存在")
	},
	/**
	 * 项目模块
	 */
	projectError: {
		/**
		 * 项目不存在(3002001)
		 */
		projectNotExists: errorCode(3002001, "项目不存在"),
		/**
		 * 项目已过期(3002002)
		 */
		projectOvertime: errorCode(3002002, "项目已过期")
	},
	/**
	 * 点子模块
	 */
	ideaError: {
		/**
		 * 点子不存在(3003001)
		 */
		ideaNotExists: errorCode(3003001, "点子不存在"),
		/**
		 * 点子用户不匹配(3003002)
		 */
		ideaUserNotEqual: errorCode(3003002, "点子用户不匹配"),
		/**
		 * 点子已点赞(3003003)
		 */
		isPraiseError: errorCode(3003003, "点子已点赞"),
		/**
		 * 点子没有点赞(3003004)
		 */
		notPraiseError: errorCode(3003004, "点子没有点赞"),
		/**
		 * 点子评论不存在(3003005)
		 */
		ideaCommentNotExists: errorCode(3003005, "点子评论不存在")
	},
	/**
	 * 资料调研模块
	 */
	researchError: {
		/**
		 * 资料不存在(3004001)
		 */
		researchNotExists: errorCode(3004001, "资料不存在")
	}
};