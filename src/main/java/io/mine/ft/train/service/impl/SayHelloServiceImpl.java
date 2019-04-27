package io.mine.ft.train.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.mine.ft.train.service.SayHelloService;

/**
 * @author: machao
 * @Date: 2017年9月20日
 */
@Service
public class SayHelloServiceImpl implements SayHelloService {

    /**
     * @Title:
     * @author:machao
     * @Date:2017年9月20日
     * @param:
     * @return:
     */
    @Override
    public String sayHello(String helloArg) {
        
        System.out.println("invoke SayHelloService!!");
        if (helloArg.equals("hello")) {
            
            return "hello";
        } else {
            return "bye bye";
        }
    }
    
    public void selector() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//设置为非阻塞方式
        ssc.socket().bind(new InetSocketAddress(8080));
        //注册监听的事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //取得所有key集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey)it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
                    //接受服务端的请求
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel)key.channel();
                    while (true) {
                        buffer.clear();
                        //读取数据
                        int n = sc.read(buffer);
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();
                    }
                    it.remove();
                }
            }
        }
    }
}
