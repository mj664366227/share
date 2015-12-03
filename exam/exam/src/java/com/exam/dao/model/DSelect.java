package com.exam.dao.model;

import com.exam.core.annotation.Pojo;
import com.exam.core.interfaces.DSuper;

/**
 * 选择题
 * @author ruan
 */
@Pojo
public class DSelect extends DSuper {
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
	 * 视频
	 */
	private String flashurl = "";
	/**
	 * a选项
	 */
	private String a = "";
	/**
	 * b选项
	 */
	private String b = "";
	/**
	 * c选项
	 */
	private String c = "";
	/**
	 * d选项
	 */
	private String d = "";
	/**
	 * 答案
	 */
	private String answer = "";

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

	public String getFlashurl() {
		return flashurl;
	}

	public void setFlashurl(String flashurl) {
		this.flashurl = flashurl;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}