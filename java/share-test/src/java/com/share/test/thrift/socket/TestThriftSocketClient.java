package com.share.test.thrift.socket;

import org.apache.thrift.protocol.TCompactProtocol;

import com.share.soa.thrift.client.ThriftSocketClient;
import com.share.soa.thrift.protocol.ShareObjectService;

public class TestThriftSocketClient {

	public static void main(String[] args) {
		new ThriftSocketClient("127.0.0.1", 9394, TCompactProtocol.class, ShareObjectService.Client.class, "test", 1);
	}

}
