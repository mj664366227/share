package com.share.soa.thrift.http;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;

import com.share.soa.thrift.protocol.ShareObjectService;

public class HttpClient {

	public static void main(String[] args) throws Exception {
		String servletUrl = "http://localhost:8080/soa/test";

		THttpClient thc = new THttpClient(servletUrl);
		TProtocol loPFactory = new TCompactProtocol(thc);
		ShareObjectService.Client client = new ShareObjectService.Client(loPFactory);

		System.err.println(client.test(1));
		
		servletUrl = "http://localhost:8080/soa/test2";

		thc = new THttpClient(servletUrl);
		loPFactory = new TCompactProtocol(thc);
		client = new ShareObjectService.Client(loPFactory);

		System.err.println(client.test(2));
	}

}
