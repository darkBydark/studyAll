package com.example.Proxy;

import java.lang.reflect.Proxy;

public class proxyService {
    public static void main(String[] args) {
        A a = new A();

        Class<?>[] interfaces = a.getClass().getInterfaces();
        B o = (B)Proxy.newProxyInstance(proxyService.class.getClassLoader(), interfaces, new invocation(a));
        o.test();
    }
}
