package com.share.soa.thrift.client;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.share.soa.thrift.model.TestShareObject;

public class ThriftClient {
	TTransport transport = new TSocket("10.0.0.41", 9813);  
    long start=System.currentTimeMillis();  
    TProtocol protocol = new TBinaryProtocol(transport);  
    
    TestShareObject.Client client = new TestShareObject.Client(protocol);
    
   // transport.

    
}