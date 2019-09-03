package cn.zs.learn.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress("192.168.1.245", 8088);
        SocketChannel sc = SocketChannel.open();
        sc.connect(socketAddress);
        if (sc.isConnected()) {
            System.out.println("连接");
            System.out.println(sc.getRemoteAddress());
            sc.write(StandardCharsets.UTF_8.encode("nio client 发送消息"));
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true) {
                byteBuffer.clear();
                int read = sc.read(byteBuffer);
                System.out.println(read);
                if (read == -1) {
                    sc.close();
                    break;
                } else {
                    byteBuffer.flip();
                    System.out.println(StandardCharsets.UTF_8.decode(byteBuffer));
                    byteBuffer.rewind();
                }
            }
        }
    }

}
