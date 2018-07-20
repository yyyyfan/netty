package cn.yuehua.io;

import java.io.*;

public class ByteArrayOutputStreamTest {
    public static void main(String[] args) {
        File file = new File("E:\\linux\\linux部署.txt");
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))){
            byte[] bytes = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            while((len = in.read(bytes)) != -1){
                bout.write(bytes, 0, len);
//                bout.write(bytes);
//                String s = new String(bytes, 0, len);
//                System.out.println(s);
            }
            byte[] outBytes = bout.toByteArray();
            String s = new String(outBytes, "UTF-8");
            System.out.println(s);


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
