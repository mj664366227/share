package com.share.admin.start;

import com.share.core.server.HttpServer;
import com.share.core.util.FileSystem;

public class AdminMain {
	public static void main(String[] a) {
		new HttpServer(FileSystem.getPropertyInt("http.port")).start();
	}
}