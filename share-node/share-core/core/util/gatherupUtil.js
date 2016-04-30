// gu项目工具库

module.exports = {
	/**
	 * 公共获取页码方法
	 * @param page
	 */
	getPage: function (page) {
		page = parseInt(page);
		return !page ? 0 : page;
	},
	/**
	 * 公共获取页面大小方法
	 * @param pageSize
	 */
	getPageSize: function (pageSize) {
		pageSize = parseInt(pageSize);
		pageSize = !pageSize ? 1 : pageSize;
		return pageSize > 20 ? 20 : pageSize;
	}
};