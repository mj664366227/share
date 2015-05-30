package com.share.soa.thrift;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class ThriftHttpClientFactory implements FactoryBean<Object>, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(ThriftHttpClientFactory.class);
	private Class<?> ifaceClass;
	private Class<?> clientClass;
	private String className;
	private ThriftHttpClient thriftClient;

	public void setClassName(String className) {
		this.className = className;
	}

	public void setThriftClient(ThriftHttpClient thriftClient) {
		this.thriftClient = thriftClient;
	}

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(clientClass.getClassLoader(), clientClass.getInterfaces(), new ThriftProxy(thriftClient, className));
	}

	@Override
	public Class<?> getObjectType() {
		return ifaceClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ifaceClass = Class.forName(className + "$Iface");
		clientClass = Class.forName(className + "$Client");
	}

	class ThriftProxy implements InvocationHandler {
		private ThriftHttpClient thriftClient;
		private String className;

		public ThriftProxy(ThriftHttpClient thriftClient, String className) {
			this.thriftClient = thriftClient;
			this.className = className;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
				return method.invoke(thriftClient.get(className), args);
			} catch (Exception e) {
				logger.error("", e);
			}
			return null;
		}
	}
}