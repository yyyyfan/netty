package cn.yuehua.netty.nio.decoder.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;
    public EchoClientHandler(int sendNumber){
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = getUserInfo();
        for (UserInfo info : infos){
            ctx.write(info);
        }
        ctx.flush();
    }

    private UserInfo[] getUserInfo(){
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for(int i = 0; i < sendNumber; i++){
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("ABCDEFG --->"+i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message : "+msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
