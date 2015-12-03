package com.exam.dao.model;

import com.exam.core.annotation.Pojo;
import com.exam.core.interfaces.DSuper;

/**
 * 答案
 * @author ruan
 */
@Pojo
public class DAnswer extends DSuper {
	/**
	 * 题目id
	 */
	private long baid;
	/**
	 * 答案
	 */
	private String answer = "";
	/**
	 * 解释
	 */
	private String explain = "";

	public long getBaid() {
		return baid;
	}

	public void setBaid(long baid) {
		this.baid = baid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}