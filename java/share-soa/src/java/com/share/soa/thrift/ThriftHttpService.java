package com.share.soa.thrift;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class ThriftHttpService implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
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

    public String getString(String url) {
        try {
            return EntityUtils.toString(execute(new HttpGet(url)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpEntity execute(HttpUriRequest request) {
        CloseableHttpResponse response = null;
        try {
        	HttpGet httpGet=new HttpGet(request.getURI());//HTTP Get请求(POST雷同)
        	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300).build();//设置请求超时时间
        	httpGet.setConfig(requestConfig);
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return new ByteArrayEntity(EntityUtils.toByteArray(entity), ContentType.get(entity));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }

}
