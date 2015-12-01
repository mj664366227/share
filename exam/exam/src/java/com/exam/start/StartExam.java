package com.exam.start;

import com.exam.core.server.HttpServer;
import com.exam.core.util.FileSystem;




public class StartExam {
	public static void main(String[] a) {
		new HttpServer(FileSystem.getPropertyInt("http.port")).start();
	}
}