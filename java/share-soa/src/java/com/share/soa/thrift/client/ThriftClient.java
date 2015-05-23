package com.share.soa.thrift.client;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.share.soa.thrift.protocol.ShareObjectService;

public class ThriftClient {
	public static void main(String[] aa) throws Exception {
		TTransport transport = new TSocket("127.0.0.1", 9394);
		TProtocol protocol = new TCompactProtocol(transport);

		ShareObjectService.Client client = new ShareObjectService.Client(protocol);

		transport.open();

		System.err.println(client.test(1));
	}
}