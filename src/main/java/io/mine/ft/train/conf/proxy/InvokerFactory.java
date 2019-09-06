package io.mine.ft.train.conf.proxy;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.mine.ft.train.service.SayHelloService;

/**
 * 创建代理
 * 
 * @author: machao
 * @Date: 2017年9月21日
 */
@Configuration
public class InvokerFactory {

    @Bean
    public SayHelloService getReference() {
        return getInstance(SayHelloService.class);
    }

    @SuppressWarnings("unchecked")
    <T> T getInstance(Class<T> cls) {

        RpcMethodProxy rpcMethodProxy = new RpcMethodProxy();

        Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[] { cls }, rpcMethodProxy);
        return (T) newProxyInstance;
    }
}
