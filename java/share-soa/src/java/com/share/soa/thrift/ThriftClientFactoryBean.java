package com.share.soa.thrift;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class ThriftClientFactoryBean implements FactoryBean, InitializingBean {
    private Class ifaceClass;
    private Class clientClass;

    private String className;
    private ThriftClient thriftClient;

    public void setClassName(String className) {
        this.className = className;
    }

    public void setThriftClient(ThriftClient thriftClient) {
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
        private ThriftClient thriftClient;
        private String className;

        public ThriftProxy(ThriftClient thriftClient, String className) {
            this.thriftClient = thriftClient;
            this.className = className;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //modified by jesseling at 2015-3-12 to add detail of exception
            try {
                return method.invoke(thriftClient.get(className), args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            } catch (Exception e){
                throw e.getCause();
            } 
        }
    }
}