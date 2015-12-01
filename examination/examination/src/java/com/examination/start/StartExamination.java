package com.examination.start;

import com.examination.core.server.HttpServer;
import com.examination.core.util.FileSystem;

public class StartExamination {
	public static void main(String[] a) {
		new HttpServer(FileSystem.getPropertyInt("http.port")).start();
	}
}