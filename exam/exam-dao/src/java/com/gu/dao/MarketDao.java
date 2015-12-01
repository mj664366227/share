package com.gu.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.util.Time;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DMarketOpus;
import com.gu.dao.model.DMarketOpusComment;
import com.gu.dao.model.DMarketOpusPraise;

/**
 * 创意圈dao
 * @author luo
 */
@Component
public class MarketDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;

	/**
	 * 发布一条创意圈_作品
	 * @param userId 用户ID
	 * @param title 标题
	 * @param content 内容
	 * @param image1 图片1
	 * @param image2 图片2
	 * @param image3 图片3
	 * @return
	 */
	public DMarketOpus addMarketOpus(long userId, String title, String content, String image1, String image2, String image3) {
		DMarketOpus marketOpus = new DMarketOpus();
		marketOpus.setUserId(userId);
		marketOpus.setCreateTime(Time.now());
		marketOpus.setTitle(title);
		marketOpus.setContent(content);
		marketOpus.setImage1(image1);
		marketOpus.setImage2(image2);
		marketOpus.setImage3(image3);
		return guMasterDbService.save(marketOpus);
	}

	/**
	 * 添加创意圈_作品评论
	 * @param userId 用户ID
	 * @param marketOpusId 作品ID
	 * @param content 评论内容
	 * @return
	 */
	public DMarketOpusComment addMarketOpusComment(long userId, long marketOpusId, String content, long replyUserId) {
		DMarketOpusComment marketOpusComment = new DMarketOpusComment();
		marketOpusComment.setUserId(userId);
		marketOpusComment.setMarketOpusId(marketOpusId);
		marketOpusComment.setContent(content);
		marketOpusComment.setReplyUserId(replyUserId);
		marketOpusComment.setCreateTime(Time.now());
		return guMasterDbService.save(marketOpusComment);
	}

	/**
	 * 添加创意圈_作品点赞
	 * @param userId 用户ID
	 * @param marketOpusId 作品ID
	 * @return
	 */
	public DMarketOpusPraise addMarketOpusPraise(long userId, long marketOpusId) {
		DMarketOpusPraise marketOpusPraise = new DMarketOpusPraise();
		marketOpusPraise.setUserId(userId);
		marketOpusPraise.setMarketOpusId(marketOpusId);
		marketOpusPraise.setCreateTime(Time.now());
		return guMasterDbService.save(marketOpusPraise);
	}

	/**
	 * 更新marketOpus
	 * @param marketOpus
	 * @return
	 */
	public boolean updateMarketOpus(DMarketOpus marketOpus) {
		return guMasterDbService.update(marketOpus);
	}

	/**
	 * 查询创意圈_作品 By opusId
	 * @param opusId
	 * @return
	 */
	public DMarketOpus getMarketOpusById(long opusId) {
		return guSlaveDbService.queryT("SELECT * FROM `market_opus` WHERE `id`=?", DMarketOpus.class, opusId);
	}

	/**
	 * 查询创意圈_作品评论 By marketOpusCommentId
	 * @param marketOpusCommentId
	 * @return
	 */
	public DMarketOpusComment getMarketOpusCommentById(long marketOpusCommentId) {
		return guSlaveDbService.queryT("SELECT * FROM `market_opus_comment` WHERE `id`=?", DMarketOpusComment.class, marketOpusCommentId);
	}

	public List<DMarketOpus> getMarketOpusList(long lastMarketOpusId, int pageSize) {
		String sql = "SELECT * FROM `market_opus` WHERE `id`<=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DMarketOpus.class, lastMarketOpusId, pageSize);

	}
	
	public List<DMarketOpus> getMarketOpusList(long userId, long lastMarketOpusId, int pageSize) {
		String sql = "SELECT * FROM `market_opus` WHERE `user_id`=? and `id`<=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DMarketOpus.class, userId, lastMarketOpusId, pageSize);
	}

	public List<DMarketOpusComment> getMarketOpusCommentList(long marketOpusId, long lastOpusCommentId, int pageSize) {
		String sql = "SELECT * FROM `market_opus_comment` WHERE `market_opus_id`=? and `id`<=? order by `id` desc limit ?";
		return guSlaveDbService.queryList(sql, DMarketOpusComment.class, marketOpusId, lastOpusCommentId, pageSize);
	}

	public List<DMarketOpus> getMarketOpus(Set<Long> marketOpusIdSet) {
		return guSlaveDbService.multiGetT(marketOpusIdSet, DMarketOpus.class);
	}

	public List<DMarketOpusComment> getMarketOpusComment(Set<Long> marketOpusCommentIdSet) {
		return guSlaveDbService.multiGetT(marketOpusCommentIdSet, DMarketOpusComment.class);
	}

	public DMarketOpusPraise getMarketOpusPraise(long userId, long marketOpusId) {
		String sql = "SELECT * FROM `market_opus_praise` WHERE `user_id`=? and `market_opus_id`=? ";
		return guSlaveDbService.queryT(sql, DMarketOpusPraise.class, userId, marketOpusId);
	}

	public boolean delMarketOpusPraise(long userId, long marketOpusId) {
		String sql = "DELETE  FROM `market_opus_praise` WHERE `user_id`=? and `market_opus_id`=? ";
		return guMasterDbService.update(sql, userId, marketOpusId);
	}
}
