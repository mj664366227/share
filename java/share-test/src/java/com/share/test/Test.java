package com.share.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.share.core.util.SystemUtil;

public class Test {
	public static void main(String[] args) throws Exception {
		System.err.println(SystemUtil.findAnnotationField("com.share.test", Autowired.class));
	}
}