package cn.yuehua.netty.bio.timeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步IO TimeServer
 */
public class TimeServerPool {
    public static void main(String[] args) throws Exception{
        int port = 1111;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : "+port);
            Socket socket = null;
            TimeServerHandleExecutePool singleExecutor = new TimeServerHandleExecutePool(50, 10000);//创建io线程池
            while(true){
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if(server != null){
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
