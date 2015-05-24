package com.share.soa.thrift.http;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServlet;

import com.share.soa.thrift.protocol.ShareObjectService;
import com.share.soa.thrift.server.ShareObjectServiceImpl;

@SuppressWarnings("serial")
public class TServletExample extends TServlet {
	public TServletExample() {
		super(new ShareObjectService.Processor<ShareObjectServiceImpl>(
				new ShareObjectServiceImpl()), 
				new TCompactProtocol.Factory());
		System.err.println("095845034ujrpifdjkj;kldjvkdfxj");
	}
}
