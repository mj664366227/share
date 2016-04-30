module.exports = ProjectIdeaLinkResponse;
function ProjectIdeaLinkResponse(projectIdeaLink) {
	var clazz = _.keys(projectIdeaLink);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo class");
	}
	if (clazz[0] != "DProjectIdeaLink") {
		throw new Error("t is not a DProjectIdeaLink");
	}
	/**
	 * 点子附件id
	 */
	this.ideaLinkId = projectIdeaLink.DProjectIdeaLink.id;
	/**
	 * 名字
	 */
	this.name = projectIdeaLink.DProjectIdeaLink.name;
	/**
	 * 链接
	 */
	this.link = projectIdeaLink.DProjectIdeaLink.link;
}