package cn.zs.learn.java.customIPC.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class server {
    private final static int TIME_OUT = 10;
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.register(selector,SelectionKey.OP_READ);

        while (true) {
            if (selector.select(TIME_OUT) == 0) {
                System.out.println(".");
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }

                if (selectionKey.isReadable()) {
                    doRead();
                }

                if (selectionKey.isValid() && selectionKey.isWritable()) {
                    doWrite();
                }
            }

        }
    }

    public static void doRead() {

    }

    public static void doWrite() {

    }
}
