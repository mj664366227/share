package com.share.soa.thrift.protocol;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.soa.thrift.protocol.ShareObjectService.Iface;

public class ShareObjectServiceImpl implements Iface {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public int test(int a) throws TException {
		logger.info(a + "\ttest");
		return 0;
	}

	@Override
	public String testString(int n, boolean aaa) throws TException {
		logger.info("testString");
		return null;
	}

}
