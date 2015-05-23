package com.share.soa.thrift.client;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.share.soa.thrift.protocol.TestShareObject;

public class ThriftClient {

	public static void main(String[] aaa) throws Exception {
		TTransport transport = new TSocket("127.0.0.1", 9813);
		TProtocol protocol = new TBinaryProtocol(transport);

		TestShareObject.Client client = new TestShareObject.Client(protocol);

		transport.open();

		client.test(1);

		transport.close();
	}
}