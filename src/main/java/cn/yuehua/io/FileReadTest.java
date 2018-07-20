package cn.yuehua.io;

import java.io.*;

public class FileReadTest {
    public static void main(String[] args) {
        // 1.建立联系 File对象
        File src = new File("E:\\linux\\linux部署.txt");
        //2.选择作用域
        Reader is = null;
        try {
            //3.读入流
//            is = new FileReader(src);
            is = new InputStreamReader(new FileInputStream(src),"utf-8");
            //4.选择容器
            char[] charArr = new char[1024];
            int len = 0;
            //5.读取内容
            StringBuilder builder = new StringBuilder();
            while ((len = is.read(charArr)) != -1){
                String s = new String(charArr, 0, len);
                builder.append(s);
            }
            System.out.println(builder);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("文件不存在");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("读取文件失败");
        } finally {
            //6.关闭流 java7以上可以用 try() 关闭
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e){
                System.out.println("关闭输入流失败");
            }
        }
    }
}
