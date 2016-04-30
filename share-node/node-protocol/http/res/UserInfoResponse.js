module.exports = UserInfoResponse;
function UserInfoResponse(user) {
	var clazz = _.keys(user);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo class");
	}
	if (clazz[0] != "DUser") {
		throw new Error("t is not a DUser");
	}
	/**
	 * 用户id
	 */
	this.userId = user.DUser.id;
	/**
	 * 用户昵称
	 */
	this.nickname = user.DUser.nickname;
	/**
	 * 头像
	 */
	this.avatarImage = user.DUser.avatarImage;
	/**
	 * 职业
	 */
	this.job = user.DUser.job;
	/**
	 * 机构
	 */
	this.agency = user.DUser.agency;
	/**
	 * 特长/专长
	 */
	this.specialty = user.DUser.specialty;
	/**
	 * 创意值
	 */
	this.creative = user.DUser.creative;
	/**
	 * 是否实名认证
	 */
	this.isProve = user.DUser.isProve;
}