// userDao

var DUser = requireT('DUser');

module.exports = {
	/**
	 * 查询用户 By 用户id
	 * @param userId 用户id
	 */
	getUserById: async function (userId) {
		return await guSlaveDbService.queryT("SELECT * FROM `user` WHERE `id`=?", DUser, userId);
	}
};