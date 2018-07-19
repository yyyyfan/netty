package cn.yuehua.netty.nio.timeserver;

import cn.yuehua.jdk.nio.timeserver.TimeClientHandle;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;

import java.util.Map;
import java.util.logging.Logger;

public class TimeClientHandler extends ChannelHandlerAdapter{
    private static final Logger logger = Logger.getLogger(TimeClientHandle.class.getName());
   // private final ByteBuf firstMessage;
    private int counter;
    private byte[] req;

//    public TimeClientHandler(){
//        byte[] req = "QUERY TIME ORDER".getBytes();
//        firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(firstMessage);
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
//        System.out.println("Now is : "+body);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        logger.warning("Unexpected exception from downstream : "+cause.getMessage());
//        ctx.close();
//    }

    public TimeClientHandler(){
        req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for(int i = 0; i < 100; i++){
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
        String body = (String)msg;
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : "+cause.getMessage());
        ctx.close();
    }
}
