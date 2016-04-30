module.exports = ProjectIdeaBase;
function ProjectIdeaBase(projectIdea) {
	var clazz = _.keys(projectIdea);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo class");
	}
	if (clazz[0] != "DProjectIdea") {
		throw new Error("t is not a DProjectIdea");
	}
	/**
	 * 用户信息
	 */
	this.userBase = {};
	/**
	 * 是否点赞
	 */
	this.isPraise = 0;
	/**
	 * 点子id
	 */
	this.ideaId = projectIdea.DProjectIdea.id;
	/**
	 * 项目id
	 */
	this.projectId = projectIdea.DProjectIdea.projectId;
	/**
	 * 项目点子标题
	 */
	this.title = projectIdea.DProjectIdea.title;
	/**
	 * 点赞数
	 */
	this.praise = projectIdea.DProjectIdea.praise;
	/**
	 * 评论数
	 */
	this.commentNum = projectIdea.DProjectIdea.commentNum;
	/**
	 * 所属标签
	 */
	this.tag = projectIdea.DProjectIdea.tag;
	/**
	 * 详细介绍
	 */
	this.detail = projectIdea.DProjectIdea.detail;
	/**
	 * 封面
	 */
	this.cover = projectIdea.DProjectIdea.cover;
	/**
	 * 发布时间
	 */
	this.createTime = projectIdea.DProjectIdea.createTime;
	/**
	 * 最后编辑时间
	 */
	this.lastEditTime = projectIdea.DProjectIdea.lastEditTime;
}