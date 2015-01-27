package com.share.test;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String link = "baidu.com";
		if (!link.startsWith("http://") && !link.startsWith("https://")) {
			System.err.println("xxx");
		}
	}

}
