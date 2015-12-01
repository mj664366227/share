package com.gu.dao.model;

import java.util.ArrayList;
import java.util.List;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 创意(点子)
 * @author ruan
 */
@Pojo
@Message
public class DIdea extends DSuper {
	/**
	 * id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 发表时间
	 */
	private int createTime;
	/**
	 * 创意标题
	 */
	private String title;
	/**
	 * 创意内容
	 */
	private String content;
	/**
	 * 点赞数
	 */
	private int praise;
	/**
	 * 创意图片1
	 */
	private String image1;
	/**
	 * 创意图片2
	 */
	private String image2;

	/**
	 * 创意图片3
	 */
	private String image3;
	/**
	 * 最后点赞时间
	 */
	private long lastPraiseTime;
	/**
	 * 评论总数
	 */
	private int commentNum;
	/**
	 * 观看权限(整数=用户id,0=不公开,-1=all,-2=friend)
	 */
	private String visit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
		this.praise = this.praise < 0 ? 0 : this.praise;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public long getLastPraiseTime() {
		return lastPraiseTime;
	}

	public void setLastPraiseTime(long lastPraiseTime) {
		this.lastPraiseTime = lastPraiseTime;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	public List<String> getImageList() {
		List<String> imageList = new ArrayList<String>();
		if (!image1.isEmpty()) {
			imageList.add(image1);
		}
		if (!image2.isEmpty()) {
			imageList.add(image2);
		}
		if (!image3.isEmpty()) {
			imageList.add(image3);
		}
		return imageList;
	}
}