package cn.yuehua.netty.bio.timeserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    public static void main(String[] args) {
        int port = 1111;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("localhost", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println("QUERY TIME ORDER");
            out.flush(); //记得刷新，否则设置自动刷新
            System.out.println("Send order 2 server succeed.");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        }catch (Exception e){

        }finally {
            if(out != null){
                out.close();
                out = null;
            }

            if(in != null){
                try {
                    in.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                in = null;
            }

            if(socket != null ){
                try {
                    socket.close();
                }catch (IOException e2){
                    e2.printStackTrace();
                }
                    socket = null;
            }
        }
    }
}
