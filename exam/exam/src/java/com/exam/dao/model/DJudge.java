package com.exam.dao.model;

import com.exam.core.annotation.Pojo;
import com.exam.core.interfaces.DSuper;

/**
 * 判断题
 * @author ruan
 */
@Pojo
public class DJudge extends DSuper {
	/**
	 * 题目id
	 */
	private long id;
	/**
	 * 题目内容
	 */
	private String question = "";
	/**
	 * 图片
	 */
	private String image = "";
	/**
	 * 答案(0-错，1-对)
	 */
	private int answer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}
}
