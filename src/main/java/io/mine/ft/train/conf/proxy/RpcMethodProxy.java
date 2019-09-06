package io.mine.ft.train.conf.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author: machao
 * @Date: 2017年9月21日
 */
public class RpcMethodProxy implements InvocationHandler {

    /**
     * @Title:
     * @author:machao
     * @Date:2017年9月21日
     * @param:
     * @return:
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        
        //如果传进来是一个已实现的具体类（本次演示略过此逻辑）
        if (Object.class.equals(method.getDeclaringClass())) {  
            try {  
                return method.invoke(this, args);  
            } catch (Throwable t) {  
                t.printStackTrace();  
            }  
        //如果传进来的是一个接口（核心）
        } else {  
            return run(method, args);  
        }  
        return null;
    }
    
    public Object run(Method method, Object[] args) {
        
        Object result = null;
        try {
            //String interfaceName = SayHelloService.class.getName();
            //Method method = SayHelloService.class.getMethod("sayHello", java.lang.String.class);
            //Object[] arguments = { "hello" };
            
            String interfaceName = method.getDeclaringClass().getName();
            @SuppressWarnings("resource")
			Socket socket = new Socket("127.0.0.1", 1234);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF(interfaceName);
            output.writeUTF(method.getName());
            output.writeObject(method.getParameterTypes());
            output.writeObject(args);

            // 从远端读取执行结果
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            result = input.readObject();
            System.out.println("rpc result :" + result);
        } catch (SecurityException | IOException | ClassNotFoundException e) {

        }
        return result;
    }

}
