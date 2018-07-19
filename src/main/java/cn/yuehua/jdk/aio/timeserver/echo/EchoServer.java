package cn.yuehua.jdk.aio.timeserver.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class EchoServer {
    static int PORT = 1111;
    static int BUFFER_SIZE = 1024;
    static String CHARACTER = "UTF-8"; //默认编码
    static CharsetDecoder decoder = Charset.forName(CHARACTER).newDecoder(); //解码
    int port;
    AsynchronousServerSocketChannel serverChannel;

    public EchoServer(int port) throws IOException{
        this.port = port;
    }

    private void listen() throws Exception{
        //打开一个服务通道
        //绑定服务端口
        this.serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port), 100);
        this.serverChannel.accept(this, new AcceptHandler());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //System.out.println("运行中...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
    }

    /**
     *  accept到一个请求的回调
     */
    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, EchoServer>{

        @Override
        public void completed(AsynchronousSocketChannel result, EchoServer attachment) {
            try {
                System.out.println("远程地址："+result.getRemoteAddress());
                //tcp各项参数
                result.setOption(StandardSocketOptions.TCP_NODELAY, true);
                result.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
                result.setOption(StandardSocketOptions.SO_RCVBUF, 1024);

                if(result.isOpen()){
                    System.out.println("client.isOpen:"+result.getRemoteAddress());
                    final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    buffer.clear();
                    result.read(buffer, result, new ReadHandler(buffer));
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                attachment.serverChannel.accept(attachment, this);// 监听新的请求，递归调用。
            }
        }

        @Override
        public void failed(Throwable exc, EchoServer attachment) {
            try {
                exc.printStackTrace();
            } finally {
                attachment.serverChannel.accept(attachment, this);// 监听新的请求，递归调用。
            }
        }
    }

    /**
     *  Read到请求地址的回调
     */
    private class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel>{
        private ByteBuffer buffer;
        public ReadHandler(ByteBuffer buffer){
            this.buffer = buffer;
        }
        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            try {
                if(result < 0){ // 客户端关闭了连接
                    EchoServer.close(attachment);
                } else if(result == 0){ // 处理空数据
                    System.out.println("空数据");
                } else {
                    //读取请求，处理客户端发送的请求
                    buffer.flip();
                    CharBuffer charBuffer = EchoServer.decoder.decode(buffer);
                    System.out.println(charBuffer.toString());

                    buffer.clear();
                    String res = "HTTP/1.1 200 OK" + "\r\n\r\n" + "hellworld\r\n";
                    buffer = ByteBuffer.wrap(res.getBytes());
                    attachment.write(buffer, attachment, new WriteHandler(buffer));//response 响应
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
            EchoServer.close(attachment);
        }
    }

    /**
     *  Write响应完的回调
     */
    private class WriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel>{
        private ByteBuffer buffer;
        public WriteHandler(ByteBuffer buffer){
            this.buffer = buffer;
        }
        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            buffer.clear();
            EchoServer.close(attachment);;
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
            EchoServer.close(attachment);
        }
    }

    /**
     * 安全关闭通道
     * @param client
     */
    private static void close(AsynchronousSocketChannel client){
        try {
            client.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("正在启动服务...");
            EchoServer server = new EchoServer(PORT);
            server.listen();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
