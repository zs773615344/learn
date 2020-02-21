package cn.zs.learn.java.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 摘自《深入分析JAVA WEB技术内幕》
 */

public class LearnNIO {
    public static void main(String[] args) throws Exception {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        Selector selector = Selector.open();


        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // 设置为非阻塞
        ssc.socket().bind(new InetSocketAddress(8088));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                it.remove();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();

                    while (true) {
                        buffer.clear();
                        int n = sc.read(buffer);
                        if (n <= 0) {
                            break;
                        } else {
                            buffer.flip();
                            System.out.println(StandardCharsets.UTF_8.decode(buffer));
                            buffer.rewind();
                            // 第二种读buffer的方式。
//							byte[] bytes = new byte[buffer.remaining()];
//							buffer.get(bytes,0, buffer.remaining());
//							System.out.println(new String(bytes,StandardCharsets.UTF_8));
//							buffer.rewind();
                        }

                    }
                }
            }

        }
    }
}