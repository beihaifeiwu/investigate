package com.freetmp.investigate.selfmade.rest;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class MyApplication extends javax.ws.rs.core.Application{
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(MyResource.class);
        return s;
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 25);
        HttpContext context = server.createContext("/resources");
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint
                (new MyApplication(), HttpHandler.class);
        context.setHandler(handler);
        server.start();
    }
}