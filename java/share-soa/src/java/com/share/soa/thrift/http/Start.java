package com.share.soa.thrift.http;

import com.share.core.server.HttpServer;
import com.share.core.util.FileSystem;

public class Start {

	public static void main(String[] args) {
		HttpServer s = new HttpServer(FileSystem.getPropertyInt("thrift.http.port"));
		s.start();
	}

}
