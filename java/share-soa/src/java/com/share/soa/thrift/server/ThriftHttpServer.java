package com.share.soa.thrift.server;

import java.util.Enumeration;

import javax.servlet.ServletConfig;

import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.util.StringUtil;
import com.share.soa.thrift.impl.ShareObjectServiceHandler;
import com.share.soa.thrift.protocol.ShareObjectService;

/**
 * thrift http 服务器
 */
public final class ThriftHttpServer extends TServlet {
	private static final long serialVersionUID = 2832683504442203445L;
	private static final Logger logger = LoggerFactory.getLogger(ThriftHttpServer.class);
	private static TProtocolFactory tProtocolFactory;

	/**
	 * 配置初始化
	 */
	public void init(ServletConfig config) {
		System.err.println("init");
		try {
			String tProtocol = StringUtil.getString(config.getInitParameter("tProtocol")) + "$Factory";
			tProtocolFactory = (TProtocolFactory) Class.forName(tProtocol).newInstance();
			
			//new TServlet(new ShareObjectService.Processor<ShareObjectServiceHandler>(new ShareObjectServiceHandler()), tProtocolFactoryClass);

			Enumeration<String> ss = config.getInitParameterNames();
			while (ss.hasMoreElements()) {
				String str = ss.nextElement();
				System.err.println(str + "\t" + config.getInitParameter(str));
			}
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 构造函数
	 */
	public ThriftHttpServer() {
		super(new ShareObjectService.Processor<ShareObjectServiceHandler>(new ShareObjectServiceHandler()), tProtocolFactory);
		System.err.println("构造函数");
	}

}