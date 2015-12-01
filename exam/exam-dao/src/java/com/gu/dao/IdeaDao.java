package com.gu.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.gu.core.util.Time;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DIdea;
import com.gu.dao.model.DIdeaComment;
import com.gu.dao.model.DIdeaCompanyComment;
import com.gu.dao.model.DIdeaPraise;

/**
 * 创意(点子)dao
 * @author luo
 */
@Component
public class IdeaDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;

	/**
	 * 发布一条创意
	 * @param userId 用户ID
	 * @param caseId 专案ID
	 * @param title 标题
	 * @param content 内容
	 * @param image1 图片1
	 * @param image2 图片2
	 * @param image3 图片3
	 * @return
	 */
	public DIdea addIdea(long userId, long caseId, String title, String content, String visit, String image1, String image2, String image3) {
		DIdea idea = new DIdea();
		idea.setUserId(userId);
		idea.setCaseId(caseId);
		idea.setCreateTime(Time.now());
		idea.setTitle(title);
		idea.setContent(content);
		idea.setPraise(0);
		idea.setVisit(visit);
		idea.setImage1(image1);
		idea.setImage2(image2);
		idea.setImage3(image3);
		idea.setLastPraiseTime(Time.now(true));
		return guMasterDbService.save(idea);
	}

	/**
	 * 删除点子
	 * @author ruan 
	 * @param ideaId 点子id
	 */
	public void deleteIdea(long ideaId) {
		String sql = "delete from `idea` where `id`=?";
		guMasterDbService.update(sql, ideaId);
	}

	/**
	 * 添加创意评论
	 * @param userId 用户ID
	 * @param ideaId 创意ID
	 * @param content 评论内容
	 * @param replyUserId 回复的用户ID
	 * @return
	 */
	public DIdeaComment addIdeaComment(long userId, long ideaId, String content, long replyUserId) {
		DIdeaComment ideaComment = new DIdeaComment();
		ideaComment.setUserId(userId);
		ideaComment.setIdeaId(ideaId);
		ideaComment.setContent(content);
		ideaComment.setReplyUserId(replyUserId);
		ideaComment.setCreateTime(Time.now());
		return guMasterDbService.save(ideaComment);
	}

	/**
	 * 删除创意评论
	 * @author luo 
	 * @param ideaCommentId 创意评论id
	 */
	public void deleteIdeaComment(long ideaCommentId) {
		String sql = "delete from `idea_comment` where `id`=?";
		guMasterDbService.update(sql, ideaCommentId);
	}

	/**
	 * 添加企业回复/评论创意
	 * @param companyId 企业ID
	 * @param ideaId 创意ID
	 * @param userId 用户id
	 * @param content 评论内容
	 * @param action 动作(1-我回复了xx 2-xx回复了我)
	 */
	public DIdeaCompanyComment addIdeaCompanyComment(long companyId, long ideaId, long userId, String content, int action) {
		DIdeaCompanyComment ideaCompanyComment = new DIdeaCompanyComment();
		ideaCompanyComment.setCompanyId(companyId);
		ideaCompanyComment.setUserId(userId);
		ideaCompanyComment.setIdeaId(ideaId);
		ideaCompanyComment.setContent(content);
		ideaCompanyComment.setCreateTime(Time.now());
		ideaCompanyComment.setAction(action);
		return guMasterDbService.save(ideaCompanyComment);
	}

	/**
	 * 添加点赞数据
	 * @param userId 用户ID
	 * @param ideaId 创意ID
	 * @return
	 */
	public DIdeaPraise addIdeaPraise(long userId, long ideaId, long caseId) {
		DIdeaPraise ideaPraise = new DIdeaPraise();
		ideaPraise.setUserId(userId);
		ideaPraise.setIdeaId(ideaId);
		ideaPraise.setCaseId(caseId);

		ideaPraise.setCreateTime(Time.now(true));
		return guMasterDbService.save(ideaPraise);
	}

	/**
	 * 查询创意 By ideaId
	 * @param ideaId 创意ID
	 * @return
	 */
	public DIdea getIdeaById(long ideaId) {
		return guSlaveDbService.queryT("SELECT * FROM `idea` WHERE `id`=?", DIdea.class, ideaId);
	}

	/**
	 * 查询创意评论 By ideaCommentId
	 * @param ideaCommentId 创意评论ID
	 * @return
	 */
	public DIdeaComment getIdeaCommentById(long ideaCommentId) {
		return guSlaveDbService.queryT("SELECT * FROM `idea_comment` WHERE `id`=?", DIdeaComment.class, ideaCommentId);
	}

	/**
	 * 查询企业回复/评论创意 By ideaCompanyCommentId
	 * @param ideaCompanyCommentId 企业回复/评论创意ID
	 * @return
	 */
	public DIdeaCompanyComment getIdeaCompanyCommentById(long ideaCompanyCommentId) {
		return guSlaveDbService.queryT("SELECT * FROM `idea_company_comment` WHERE `id`=?", DIdeaCompanyComment.class, ideaCompanyCommentId);
	}

	/**
	 * 批量获取企业回复创意评论
	 * @author ruan 
	 * @param companyCommentIdSet id集合
	 */
	public List<DIdeaCompanyComment> getIdeaCompanyComment(Set<Long> companyCommentIdSet) {
		return guSlaveDbService.multiGetT(companyCommentIdSet, DIdeaCompanyComment.class);
	}

	/**
	 * 企业回复创意评论列表
	 * @author ruan 
	 * @param ideaId 创意id
	 */
	public List<DIdeaCompanyComment> getIdeaCompanyComment(long ideaId) {
		String sql = "select * from `idea_company_comment` where `idea_id`=? order by `create_time` desc";
		return guSlaveDbService.queryList(sql, DIdeaCompanyComment.class, ideaId);
	}

	/**
	 * 企业回复创意评论列表
	 * @author luo 
	 * @param ideaId 创意id
	 * @param lastIdeaCompanyCommentId 上一页最后一个创意评论id
	 * @param pageSize 页面大小
	 */
	public List<DIdeaCompanyComment> getIdeaCompanyComment(long ideaId, long lastIdeaCompanyCommentId, int pageSize) {
		String sql = "select * from `idea_company_comment` where `idea_id`=? and `id`<=? order by `create_time` desc limit ?";
		return guSlaveDbService.queryList(sql, DIdeaCompanyComment.class, ideaId, lastIdeaCompanyCommentId, pageSize);
	}

	/**
	 * 查询创意 By userId caseId
	 * @param userId 用户id
	 * @param caseId 专案id
	 * @return
	 */
	public DIdea getIdeaByUserIdCaseId(long userId, long caseId) {
		return guSlaveDbService.queryT("SELECT * FROM `idea` WHERE `user_id`=? and `case_id`=?", DIdea.class, userId, caseId);
	}

	/**
	 * 批量获取创意列表
	 * @param caseId 专案id
	 * @param pageSize 页面大小
	 * @return
	 */
	public List<DIdea> getIdeaList(long caseId, long lastIdeaId, int pageSize) {
		String sql = "SELECT * FROM `idea` WHERE `case_id`=? and `id`<=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DIdea.class, caseId, lastIdeaId, pageSize);
	}

	/**
	 * 批量获取创意列表的用户id
	 * @param caseId 专案id
	 * @param pageSize 页面大小
	 * @return
	 */
	public List<Long> getIdeaListUserId(long caseId, long lastIdeaId, int pageSize) {
		String sql = "SELECT `user_id` FROM `idea` WHERE `case_id`=? and `id`<=? order by `id` desc limit ?";
		return guSlaveDbService.queryLongList(sql, caseId, lastIdeaId, pageSize);
	}

	/**
	 * 批量获取创意Top列表
	 * @param caseId 专案id
	 * @param pageSize 页面大小
	 * @return
	 */
	public List<DIdea> getIdeaTop(long caseId, int pageSize) {
		String sql = "SELECT * FROM `idea` WHERE `case_id`=? order by `praise` desc ,`last_praise_time` asc limit ?";
		return guSlaveDbService.queryList(sql, DIdea.class, caseId, pageSize);
	}

	/**
	 * 批量获取创意评论列表
	 * @param ideaId 创意id
	 * @param lastIdeaCommentId 上一页最后一个创意评论id
	 * @param pageSize 页面大小
	 * @return
	 */
	public List<DIdeaComment> getIdeaCommentList(long ideaId, long lastIdeaCommentId, int pageSize) {
		String sql = "SELECT * FROM `idea_comment` WHERE `idea_id`=? and `id`<=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DIdeaComment.class, ideaId, lastIdeaCommentId, pageSize);
	}

	/**
	 * 批量获取创意
	 * @param ideaIdSet 创意id集合
	 */
	public List<DIdea> getIdea(Set<Long> ideaIdSet) {
		return guSlaveDbService.multiGetT(ideaIdSet, DIdea.class);
	}

	/**
	 * 批量获取创意评论
	 * @param ideaCommentIdSet 创意评论id集合
	 */
	public List<DIdeaComment> getIdeaComment(Set<Long> ideaCommentIdSet) {
		return guSlaveDbService.multiGetT(ideaCommentIdSet, DIdeaComment.class);
	}

	/**
	 * 查询用户点赞创意详细
	 * @param userId 用户id
	 * @param ideaId 创意id
	 * @return
	 */
	public DIdeaPraise getIdeaPraise(long userId, long ideaId) {
		String sql = "SELECT * FROM `idea_praise` WHERE `user_id`=? and `idea_id`=? ";
		return guSlaveDbService.queryT(sql, DIdeaPraise.class, userId, ideaId);
	}

	/**
	 * 创意点赞数+1
	 * @param ideaId 创意id
	 * @return
	 */
	public boolean updateIdeaUpOne(DIdea idea) {
		long lastPraiseTime = Time.now(true);
		String sql = "UPDATE `idea` SET `praise`=`praise`+1,`last_praise_time`=? WHERE `id`=?";
		idea.setLastPraiseTime(lastPraiseTime);
		return guMasterDbService.update(sql, lastPraiseTime, idea.getId());
	}

	/**
	 * 创意点赞数-1
	 * @param ideaId 创意id
	 * @return
	 */
	public boolean updateIdeaDownOne(DIdea idea) {
		long lastPraiseTime = Time.now(true);
		String sql = "UPDATE `idea` SET `praise`=`praise`-1,`last_praise_time`=? WHERE `id`=?";
		idea.setLastPraiseTime(lastPraiseTime);
		return guMasterDbService.update(sql, lastPraiseTime, idea.getId());
	}

	/**
	 * 更新idea
	 * @param idea
	 */
	public boolean updateIdea(DIdea idea) {
		return guMasterDbService.update(idea);
	}

	/**
	 * 删除用户点赞创意详细
	 * @param userId 用户id
	 * @param ideaId 创意id
	 * @return
	 */
	public boolean deleteIdeaPraise(long userId, long ideaId) {
		String sql = "DELETE FROM `idea_praise` WHERE `user_id`=? and `idea_id`=? ";
		return guMasterDbService.update(sql, userId, ideaId);
	}

	/**
	 * 点赞过专案创意的人
	 * @param caseId 专案
	 * @return
	 */
	public List<Long> praiseAllUserOfCase(long caseId) {
		String sql = "select `user_id` from `idea_praise` where `case_id`=? group by `user_id`";
		return guSlaveDbService.queryLongList(sql, caseId);
	}

	public List<Long> ideaUserOfCase(long caseId) {
		String sql = "SELECT  `user_id`  FROM `idea` WHERE `case_id`=? group by `user_id`";
		return guSlaveDbService.queryLongList(sql, caseId);
	}

	public List<Map<String, Object>> getIdeaTopAll(long caseId) {
		String sql = "SELECT `id`, `user_id`,  `praise` FROM `idea` WHERE `case_id`=? order by `praise` desc ,`last_praise_time` asc";
		return guSlaveDbService.queryList(sql, caseId);
	}

	public List<Long> praiseAllUserOfIdeas(List<Long> ideaIdList) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT `user_id` FROM `idea_praise` WHERE `idea_id` in (");
		sql.append(Joiner.on(",").join(ideaIdList));
		sql.append(") group by `user_id`");
		return guSlaveDbService.queryLongList(sql.toString());
	}

}
