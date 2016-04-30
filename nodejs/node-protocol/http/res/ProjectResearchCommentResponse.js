module.exports = ProjectResearchCommentResponse;
function ProjectResearchCommentResponse() {
	/**
	 * 用户个人资料
	 */
	this.userBase = null;
	/**
	 * 发表时间
	 */
	this.createTime = 0;
	/**
	 * 评论内容
	 */
	this.content = '';
	/**
	 * 回复的用户信息
	 */
	this.replyUserBase = null;
}