package cn.yuehua.jdk.nio.timeserver;

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

        TimeClientHandle handle = new TimeClientHandle("127.0.0.1", port);
        new Thread(handle, "TimeClient-001").start();

    }
}
