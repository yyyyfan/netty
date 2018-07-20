package cn.yuehua.io;

import java.io.*;

public class FileOutputTest {
    public static void main(String[] args) {
        File file = new File("E:/io/FileOutputTest.txt");
        try (OutputStream out = new FileOutputStream(file);){
            String s = "hello file";
            byte[] bytes = s.getBytes("UTF-8");
            out.write(bytes);
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("写入文件失败");
        }
    }
}
