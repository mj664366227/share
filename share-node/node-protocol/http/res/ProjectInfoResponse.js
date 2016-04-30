module.exports = ProjectInfoResponse;
function ProjectInfoResponse(project) {
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
	this.projectiId = project.DProject.id;
	/**
	 * 项目标题
	 */
	this.title = project.DProject.title;
	/**
	 * 项目封面
	 */
	this.cover = project.DProject.cover;
	/**
	 * 项目描述
	 */
	this.description = project.DProject.description;
	/**
	 * 项目描述附件
	 */
	this.descriptionAttach = project.DProject.descriptionAttach;
	/**
	 * 导师配置
	 */
	this.tutorList = [];
	/**
	 * 支持机构
	 */
	this.agencyList = [];
	/**
	 * 发布时间
	 */
	this.createTime = project.DProject.createTime;
	/**
	 * 结束时间
	 */
	this.endTime = project.DProject.endTime;
	/**
	 * 品牌介绍
	 */
	this.brandIntroduction = project.DProject.brandIntroduction;
	/**
	 * 品牌介绍附件
	 */
	this.brandIntroductionAttach = project.DProject.brandIntroductionAttach;
	/**
	 * 显示状态(1-显示 0-隐藏)
	 */
	this.status = project.DProject.status;
}