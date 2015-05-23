package com.share.soa.thrift.protocol;

import org.apache.thrift.TException;

import com.share.soa.thrift.protocol.ShareObjectService.Iface;

public class ShareObjectServiceImpl implements Iface {

	@Override
	public int test(int a) throws TException {
		System.err.println(a + "\ttest");
		return 0;
	}

	@Override
	public String testString(int n, boolean aaa) throws TException {
		System.err.println("testString");
		return null;
	}

}
