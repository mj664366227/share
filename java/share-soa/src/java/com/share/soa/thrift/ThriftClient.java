package com.share.soa.thrift;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.collect.Maps;

public class ThriftClient implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, ThreadLocal> clientMap = Maps.newHashMap();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Properties properties = new Properties();

    private ThriftHttpService httpService;

    public void setHttpService(ThriftHttpService httpService) {
        this.httpService = httpService;
    }

    public <T> T get(String className) {
        lock.readLock().lock();
        try {
            ThreadLocal local = clientMap.get(className);
            if (local != null) {
                Object obj = local.get();
                if (obj != null) {
                    return (T) obj;
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        String url = properties.getProperty(className);
        if (url == null) {
            throw new IllegalStateException("cannot find url for:"+className);
        }
        lock.writeLock().lock();
        try {
            ThreadLocal local = clientMap.get(className);
            if (local == null) {
                local = new ThreadLocal();
                clientMap.put(className, local);
            }
            Object obj = local.get();
            if (obj == null) {
                try {
                    THttpClient transport = new THttpClient(url, httpService.getClient());
                    TProtocol protocol = new TBinaryProtocol(transport);
                    obj = Class.forName(className+"$Client").getDeclaredConstructor(TProtocol.class).newInstance(protocol);
                    local.set(obj);
                } catch (TTransportException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            return (T) obj;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("thrift.conf"));
            logger.info("load thrift.conf: {}", properties);
        } catch (Exception e) {
            throw new IllegalStateException("load thrift.conf error", e);
        }
    }
}
