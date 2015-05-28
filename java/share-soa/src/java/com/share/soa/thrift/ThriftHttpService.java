package com.share.soa.thrift;

import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.InitializingBean;

public class ThriftHttpService implements InitializingBean {
	private CloseableHttpClient client;

	public CloseableHttpClient getClient() {
		return client;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(1, TimeUnit.SECONDS);
		connectionManager.setMaxTotal(128);
		connectionManager.setDefaultMaxPerRoute(32);

		HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connectionManager);
		client = builder.build();
	}
}