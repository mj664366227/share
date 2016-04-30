module.exports = ProjectIdeaAddedResponse;
function ProjectIdeaAddedResponse(projectIdeaAdded) {
	var clazz = _.keys(projectIdeaAdded);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo class");
	}
	if (clazz[0] != "DProjectIdeaAdded") {
		throw new Error("t is not a DProjectIdeaAdded");
	}
	/**
	 * 点子补充id
	 */
	this.ideaAddedId = projectIdeaAdded.DProjectIdeaAdded.id;
	/**
	 * 用户id
	 */
	this.userId = projectIdeaAdded.DProjectIdeaAdded.userId;
	/**
	 * 点子id
	 */
	this.ideaId = projectIdeaAdded.DProjectIdeaAdded.ideaId;
	/**
	 * 补充内容
	 */
	this.content = projectIdeaAdded.DProjectIdeaAdded.content;
	/**
	 * 发表时间
	 */
	this.createTime = projectIdeaAdded.DProjectIdeaAdded.createTime;
}