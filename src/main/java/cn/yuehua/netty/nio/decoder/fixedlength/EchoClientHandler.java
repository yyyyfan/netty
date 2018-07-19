package cn.yuehua.netty.nio.decoder.fixedlength;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter{
    private int counter;
    static final String ECHO_REQ = "Hi, Yangfan. Welcome to Netty.$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 20; j++){
                builder.append(i);
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer(builder.toString().getBytes()));
            builder.setLength(0);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is "+ ++counter +" times receive server : ["+msg+"]");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
