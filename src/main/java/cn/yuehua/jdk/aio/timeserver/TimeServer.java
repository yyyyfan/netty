package cn.yuehua.jdk.aio.timeserver;

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

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //System.out.println("运行中...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
    }
}
