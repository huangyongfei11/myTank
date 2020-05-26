package com.hyf.tank.io;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BClientSocket {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("127.0.0.1",9999);

        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("服务器你好!!!!");
        writer.flush();
        writer.close();
        socket.close();



    }
}
