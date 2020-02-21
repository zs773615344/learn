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

public class NIOServer {

    public static void main(String[] args) throws Exception {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        Selector selector = Selector.open();


        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // 设置为非阻塞
        ssc.socket().bind(new InetSocketAddress(8088));

        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {

            /**
             * select() 阻塞等待，知道一个注册通道有感兴趣的操作就绪
             * select（）等待一段时间，或一个注册通道有感兴趣的操作就绪
             * selectNow() 非阻塞
             */
//            int select = selector.select();
//            int select = selector.select(TIME_OUT);
            int select = selector.selectNow();
            if (select <= 0)
//				System.out.println(select);
                continue;
            if (select > 0) {
//				System.out.println(select);
//                System.out.println(selector.keys().size());
//                System.out.println(selector.selectedKeys().size());
            }

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
                    /**
                     * 当通道中读操作就绪，开始读数据，每次只读一个buffer的数据。一个buffer读满后，将状态码转成
                     * 写操作就绪，这个就可以将读的一个buffer的数据写入。如果没有转入写操作，可以用while（true）
                     * 将数据全部读出。
                     */
                    buffer.clear();
                    int n = sc.read(buffer);
                    if (n == -1) {
                        sc.close();
                    } else {
                        buffer.flip();
                        System.out.println(StandardCharsets.UTF_8.decode(buffer));
                        buffer.rewind();
                        // 第二种读buffer的方式。
//						byte[] bytes = new byte[buffer.remaining()];
//						buffer.get(bytes,0, buffer.remaining());
//						System.out.println(new String(bytes,StandardCharsets.UTF_8));
//						buffer.rewind();
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                } else if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.write(buffer);

                    if (!buffer.hasRemaining()) {
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        }
    }

    public static void printFlag(ByteBuffer buffer, String flag) {
        System.out.println(flag + ":postion " + buffer.position());
        System.out.println(flag + ":limit " + buffer.limit());
        System.out.println(flag + ":offset " + buffer.arrayOffset());
        System.out.println(flag + ":capacity " + buffer.capacity());
        System.out.println(flag + ":mask " + buffer.mark());
    }
}
