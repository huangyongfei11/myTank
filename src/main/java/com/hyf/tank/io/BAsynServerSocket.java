package com.hyf.tank.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/25]
 * @see [相关类/方法] BIO 模拟伪异步
 * @since [产品/模块版本]
 */
public class BAsynServerSocket {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        boolean isStart = true;
        while (isStart){
            Socket socket = serverSocket.accept();
            System.out.println("测试accept()是否阻塞");
            new Thread(()->{
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(bufferedReader.readLine());
                    System.out.println("测试 read相关方法是阻塞的，只有客户端发送消息才会往下执行");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }
}
