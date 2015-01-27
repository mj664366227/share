package com.share.admin.start;

import com.share.core.server.HttpServer;

public class AdminMain {
	public static void main(String[] a) {
		new HttpServer(8080).start();
	}
}