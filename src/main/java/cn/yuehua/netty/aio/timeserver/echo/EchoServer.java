package cn.yuehua.netty.aio.timeserver.echo;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class EchoServer {
    static int PORT = 1111;
    static int BUFFER_SIZE = 1024;
    static String CHARACTER = "UTF-8";
    static CharsetDecoder decoder = Charset.forName(CHARACTER).newDecoder();
}
