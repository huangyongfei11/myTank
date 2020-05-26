package com.hyf.tank.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NPoolServer {

    private ExecutorService executorService = new ThreadPoolExecutor(50, 50,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    private Selector selector;

    public static void main(String[] args) throws IOException {
        NPoolServer nPoolServer = new NPoolServer();
        nPoolServer.init(9999);
        nPoolServer.listen();

    }


    private void init(int port) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        this.selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动");
    }


    private void listen() throws IOException {
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator =selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.socket();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(key.selector(),SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    key.interestOps(key.interestOps()&(~SelectionKey.OP_READ));
                    executorService.execute(new ThreadHandlerChannel(key));
                }
            }
        }
    }
}

class ThreadHandlerChannel extends Thread{
    private SelectionKey key;
    ThreadHandlerChannel(SelectionKey key){
        this.key=key;
    }
    @Override
    public void run() {
        //
        SocketChannel channel = (SocketChannel) key.channel();
        //
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int size = 0;
            while ((size = channel.read(buffer)) > 0) {
                buffer.flip();
                baos.write(buffer.array(),0,size);
                buffer.clear();
            }
            baos.close();
            //
            byte[] content=baos.toByteArray();
            ByteBuffer writeBuf = ByteBuffer.allocate(content.length);
            writeBuf.put(content);
            writeBuf.flip();
            channel.write(writeBuf);//
            if(size==-1){

                channel.close();
            }else{
                //
                key.interestOps(key.interestOps()|SelectionKey.OP_READ);
                key.selector().wakeup();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
