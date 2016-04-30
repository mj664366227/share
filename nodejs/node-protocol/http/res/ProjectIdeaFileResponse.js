module.exports = ProjectIdeaFileResponse;
function ProjectIdeaFileResponse(projectIdeaFile) {
	var clazz = _.keys(projectIdeaFile);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo class");
	}
	if (clazz[0] != "DProjectIdeaFile") {
		throw new Error("t is not a DProjectIdeaFile");
	}
	/**
	 * 点子附件id
	 */
	this.ideaFileId = projectIdeaFile.DProjectIdeaFile.id;
	/**
	 * 名字
	 */
	this.name = projectIdeaFile.DProjectIdeaFile.name;
	/**
	 * 链接
	 */
	this.link = projectIdeaFile.DProjectIdeaFile.link;
}