package cn.yuehua.io;

import java.io.*;

public class FileWtriteTest {
    public static void main(String[] args) {
        File file = new File("E:/io/FileWriteTest.txt");

        try (Writer out = new OutputStreamWriter(new FileOutputStream(file),"GBK");){
            String s = "测试一下";
//            char[] charArr = new char[s.length()];
//            s.getChars(0,s.length(), charArr,0);
//            out.write(charArr);
            out.write(s);
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("写入文件失败");
        }
    }
}
