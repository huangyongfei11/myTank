package com.hyf.tank.nettychat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Client {

    private Channel channel;

    public void connect() {
        EventLoopGroup work = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();
        try {
            Future future = b.group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel socketChannel) throws Exception {
                            channel = socketChannel;
                            socketChannel.pipeline().addLast(new MyHandler02());
                        }
                    })
                    .connect("localhost", 8888)
                    .sync();
            ((ChannelFuture) future).channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            work.shutdownGracefully();
        }


    }

    public void send(String msg) {
        this.channel.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
    }

    public void close(String msg){
        send(msg);
        channel.close();
    }
}

class MyHandler02 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buff = null;
        try {
            buff = (ByteBuf) msg;
            byte[] bytes = new byte[buff.readableBytes()];
            buff.getBytes(buff.readerIndex(), bytes);
            String msg2 = new String(bytes);
            System.out.println(msg2);
            ClientFrame.INSTANCE.update(msg2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != buff) {
                ReferenceCountUtil.release(buff);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.channel().close();
    }
}
