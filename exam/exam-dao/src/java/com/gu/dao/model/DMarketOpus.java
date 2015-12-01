package com.gu.dao.model;

import java.util.ArrayList;
import java.util.List;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 创意圈_作品
 * @author luo
 */
@Pojo
@Message
public class DMarketOpus extends DSuper {
	/**
	 * id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
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
	 * 评论总数
	 */
	private int commentNum;
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
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
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
