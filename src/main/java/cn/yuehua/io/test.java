package cn.yuehua.io;

import org.junit.Test;

public class test {
    public static void main(String[] args) {
        BigFileReader.Builder builder = new BigFileReader.Builder("e:/linux/linux部署.txt",new IHandle() {

            @Override
            public void handle(String line) {
                System.out.println(line);
//                increat();
            }
        });
        builder.withTreahdSize(10)
                .withCharset("utf-8")
                .withBufferSize(1024*1024);
        BigFileReader bigFileReader = builder.build();
        bigFileReader.start();
    }

    @Test
    public void test1(){
        int i = 0x02;
        byte b = (byte)(i & 0x0010);

    }
}
