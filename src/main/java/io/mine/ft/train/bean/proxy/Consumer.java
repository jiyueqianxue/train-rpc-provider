package io.mine.ft.train.bean.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import io.mine.ft.train.service.SayHelloService;

/**
 * @author: machao
 * @Date: 2017年9月20日
 */
public class Consumer {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        String interfaceName = SayHelloService.class.getName();
        try {
            Method method = SayHelloService.class.getMethod("sayHello", java.lang.String.class);
            //入参
            Object[] arguments = { "hello" };

            Socket socket = new Socket("127.0.0.1", 1234);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF(interfaceName);
            output.writeUTF(method.getName());
            output.writeObject(method.getParameterTypes());
            output.writeObject(arguments);

            // 从远端读取执行结果
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Object result = input.readObject();
            System.out.println("rpc result :" + result);
        } catch (NoSuchMethodException | SecurityException | IOException | ClassNotFoundException e) {

        }
    }
}
