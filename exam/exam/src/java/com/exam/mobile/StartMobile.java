package com.exam.mobile;

import com.exam.core.server.HttpServer;
import com.exam.core.util.FileSystem;




public class StartMobile {
	public static void main(String[] a) {
		new HttpServer(FileSystem.getPropertyInt("http.mobile.port")).start();
	}
}