package cn.yuehua.netty.bio.timeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio TimeServer
 */
public class TimeServer {
    public static void main(String[] args) throws IOException{
        int port = 1111;
        if(args != null && args.length > 0 ){
            try{
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port:"+port);
            Socket socket = null;
            while (true){
                 socket = server.accept();
//                new Thread(new TimeServerHandler(socket)).start();
                new TimeServerHandler(socket).run();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(server != null){
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
