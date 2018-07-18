package cn.yuehua.netty.aio.timeserver;

public class TimeClient {
    public static void main(String[] args) {
        int port = 1111;
        if(args != null && args.length > 0 ){
            try{
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-001").start();
    }
}
