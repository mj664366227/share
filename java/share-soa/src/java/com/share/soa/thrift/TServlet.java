package com.share.soa.thrift;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private TProcessor processor;
    private TProtocolFactory compactProtocolFactory = new TCompactProtocol.Factory();
    private TProtocolFactory jsonProtocolFactory = new TJSONProtocol.Factory();
    private TProtocolFactory binaryProtocolFactory = new TBinaryProtocol.Factory();

    public TServlet(TProcessor processor) {
        this.processor = processor;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String protocol = request.getHeader("protocol");
        TProtocolFactory protocolFactory;
        if ("json".equals(protocol)) {
            protocolFactory = jsonProtocolFactory;
        } else if ("compact".equals(protocol)) {
            protocolFactory = compactProtocolFactory;
        } else if ("binary".equals(protocol)) {
            protocolFactory = binaryProtocolFactory;
        } else {
            //默认协议改成binary
            protocolFactory = binaryProtocolFactory;
        }

        response.setContentType("application/x-thrift");
        OutputStream output = response.getOutputStream();
        TTransport transport = new TIOStreamTransport(request.getInputStream(), output);
        try {
            processor.process(protocolFactory.getProtocol(transport), protocolFactory.getProtocol(transport));
            output.flush();
        } catch (TException e) {
            throw new RuntimeException(e);
        }

    }
}