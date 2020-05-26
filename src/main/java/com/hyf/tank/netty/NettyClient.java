package com.hyf.tank.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NettyClient {


    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bs = new Bootstrap();
        bs.channel(NioSocketChannel.class).group(work).handler(new MyInitHandler() );

        ChannelFuture f = bs.connect("127.0.0.1", 9999).sync();

        f.channel().closeFuture().sync();

    }
}

class MyInitHandler extends ChannelInitializer{
    @Override
    protected void initChannel(Channel channel) throws Exception {
        System.out.println("连接服务端成功");
        channel.pipeline().addLast(new MyReadHandler());
    }
}
class MyReadHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString());
        ctx.writeAndFlush(msg);

        ctx.close();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
