package io.mine.ft.train.bean.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

//import javax.annotation.PostConstruct;

//import org.springframework.stereotype.Component;

import io.mine.ft.train.service.impl.SayHelloServiceImpl;

/**
 * @author: machao
 * @Date: 2017年9月20日
 */
//@Component
public class Provider_2 {
    
    //初始化方法的注解方式  等同与init-method=init 
    //@PostConstruct
    public void init() {
        try {
            @SuppressWarnings("resource")
            ServerSocket server = new ServerSocket(1234);
            System.out.println("Provider going on!");
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                String interfaceName = input.readUTF();
                String methodName = input.readUTF();

                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();

                Object[] arguments = (Object[]) input.readObject();
                Class<?> serviceClass = Class.forName(interfaceName);
                Object service = new SayHelloServiceImpl();
                Method method = serviceClass.getMethod(methodName, parameterTypes);

                Object result = method.invoke(service, arguments);

                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);
            }
        } catch (Exception e) {

        }
    }
}
