package cn.yuehua.netty.nio.timeserver;

public class TimeServer {
    public static void main(String[] args) {
        int port = 1111;
        if(args != null && args.length > 0 ){
            try{
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();

    }
}
