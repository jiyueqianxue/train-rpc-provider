package io.mine.ft.train.conf.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: machao
 * @Date: 2017年7月6日
 */
public class DynProxyFactory {

    // 客户类调用此工厂方法获得代理对象。
    // 对客户类来说，其并不知道返回的是代理类对象还是委托类对象。
    public static Subject getInstance() {
        Subject delegate = new RealSubject();
        InvocationHandler handler = new SubjectInvocationHandler(delegate);
        Subject proxy = null;
        proxy = (Subject) Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(), handler);
        return proxy;
    }
}
