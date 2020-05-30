package com.hyf.tank.nettychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Server {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) {

        EventLoopGroup boss = new NioEventLoopGroup(2);
        EventLoopGroup work = new NioEventLoopGroup(10);

        try {
            ServerBootstrap sb = new ServerBootstrap();
            Future future = sb.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) {
                            System.out.println("客户端连接成功");
                            channel.pipeline().addLast(new MyHandler());
                        }
                    }).bind(8888).sync();
            ((ChannelFuture) future).channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}

class MyHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        Server.channels.add(channelHandlerContext.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf buff = null;
        buff = (ByteBuf) o;
        byte[] bytes = new byte[buff.readableBytes()];
        buff.getBytes(buff.readerIndex(), bytes);
        String msg = new String(bytes);
        Server.channels.writeAndFlush(buff);
        if ("byte".equals(msg)) {
            Server.channels.remove(channelHandlerContext.channel());
            channelHandlerContext.channel().close();
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        channelHandlerContext.channel().close();
    }
}
