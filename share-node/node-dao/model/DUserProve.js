module.exports = function () {
	this.DUserProve = {
		/**
		 * 用户id
		 */
		userId: 0,
		/**
		 * 认证信息
		 */
		proveInfo: "",
		/**
		 * 身份证图片
		 */
		identityCard: "",
		/**
		 * 其他证件图片
		 */
		otherCard: "",
		/**
		 * 是否实名认证
		 */
		isProve: 0,
		/**
		 * 添加时间
		 */
		createTime: 0,
		/**
		 * 审核时间
		 */
		checkTime: 0
	}
};