package com.share.test;

import sun.misc.BASE64Decoder;

public class Test {
	public static void main(String[] args) throws Exception {
		System.err.println(getFromBASE64("c3RhnQua32P3R5cGU9MCZvcHVz=WQ9MSZzb=25naWQ9MSZAa3VHb3UlNy4wfCF+Kg"));
	}

	// 将 s 进行 BASE64 编码
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}