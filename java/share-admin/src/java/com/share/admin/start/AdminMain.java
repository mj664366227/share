package com.share.admin.start;

import com.share.core.util.FileSystem;

public class AdminMain {
	public static void main(String[] a) {
		JettyServer jettyServer = new JettyServer();
		jettyServer.setPort(FileSystem.getPropertyInt("http.port"));
		jettyServer.setContextPath("/");
		jettyServer.start();
		// new HttpServer(FileSystem.getPropertyInt("http.port")).start();
	}
}