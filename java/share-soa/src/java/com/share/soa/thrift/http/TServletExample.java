package com.share.soa.thrift.http;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServlet;

import com.share.soa.thrift.protocol.ShareObjectService;
import com.share.soa.thrift.server.ShareObjectServiceImpl;

public class TServletExample extends TServlet {
	private static final long serialVersionUID = -6393044103559956922L;

	 @SuppressWarnings({ "unchecked", "rawtypes" })  
	    public TServletExample() {  
	        super(new ShareObjectService.Processor(new ShareObjectServiceImpl()),  
	                new TCompactProtocol.Factory());  
	    }  
}