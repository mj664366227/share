package com.gu.mobile;

import com.gu.core.server.HttpServer;
import com.gu.core.util.FileSystem;




public class StartMobile {
	public static void main(String[] a) {
		new HttpServer(FileSystem.getPropertyInt("http.mobile.port")).start();
	}
}