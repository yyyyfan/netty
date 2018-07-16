

import org.junit.Test;
import java.io.*;

public class TestNetty {

    @Test
    public void test1(){
        //获取cpu 核数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void test2() throws IOException{
        File file = new File("C:\\Users\\Administrator\\Documents\\Tencent Files\\931333877\\FileRecv\\header.jsp");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[1024];
        in.read(bytes);

        String s = new String(new String(bytes,"UTF-8").getBytes("GB2312"),"UTF-8");
        String s2 = new String(s.getBytes("GB2312"),"UTF-8");


        System.out.println(s2);


//        OutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\header.jsp");
//        out.write(bytes);
//        out.flush();
//
//        in.close();
//        out.close();
    }
}
