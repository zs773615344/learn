package cn.zs.learn.java.nio.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select() == 0) {
                System.out.println(".");
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                    socketChannel.configureBlocking(false);
                    SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                    NioServerConnection nioServerConnection = new NioServerConnection(selectionKey);
                    selectionKey.attach(nioServerConnection);
                }

                if (key.isReadable()) {
                    NioServerConnection attachment = (NioServerConnection) key.attachment();
                    attachment.handleRead();
                }

                if (key.isValid() && key.isWritable()) {
                    NioServerConnection attachment = (NioServerConnection) key.attachment();
                    attachment.handleWrite();
                }
            }
        }
    }
}
