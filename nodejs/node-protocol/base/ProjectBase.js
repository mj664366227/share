module.exports = ProjectBase;
function ProjectBase(project) {
	var clazz = _.keys(project);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo class");
	}
	if (clazz[0] != "DProject") {
		throw new Error("t is not a DProject");
	}
	/**
	 * 项目id
	 */
	this.projectId = project.DProject.id;
	/**
	 * 支持机构
	 */
	this.agencyList = [];
	/**
	 * 项目标题
	 */
	this.title = project.DProject.title;
	/**
	 * 项目封面
	 */
	this.cover = project.DProject.cover;
	/**
	 * 结束剩余时间
	 */
	this.leftTime = project.DProject.endTime - timeUtil.now();
}