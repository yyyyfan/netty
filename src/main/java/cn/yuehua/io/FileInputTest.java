package cn.yuehua.io;

import java.io.*;

public class FileInputTest {
    public static void main(String[] args) {
        // 1.建立联系 File对象
        File src = new File("E:/io/FileInputTest.java");
        //2.选择作用域
        InputStream is = null;
        try {
            //3.读入流
            is = new FileInputStream(src);

            BufferedInputStream bin = new BufferedInputStream(is);

            //4.选择容器
            byte[] bytes = new byte[1024];
            int len = 0;
            //5.读取内容
            StringBuilder builder = new StringBuilder();
            while ((len = is.read(bytes)) != -1){
                String s = new String(bytes, 0, len, "UTF-8");
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
