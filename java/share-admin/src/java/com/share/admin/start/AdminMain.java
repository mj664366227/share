package com.share.admin.start;

import com.share.core.server.HttpServer;
import com.share.core.util.FileSystem;

public class AdminMain extends HttpServer {
	public AdminMain() {
		super(FileSystem.getPropertyInt("http.port"));
	}

	public static void main(String[] a) {
		AdminMain adminMain = new AdminMain();
		adminMain.start();
	}
}