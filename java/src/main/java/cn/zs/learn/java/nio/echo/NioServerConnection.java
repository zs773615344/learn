package cn.zs.learn.java.nio.echo;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NioServerConnection {
    private SelectionKey key;
    private SocketChannel channel;
    private ByteBuffer buffer = ByteBuffer.allocate(1);
    public NioServerConnection(SelectionKey key) {
        this.key = key;
        this.channel = (SocketChannel) key.channel();
    }

    public void handleRead() throws IOException {
        long byteRead = channel.read(buffer);
        System.out.println(buffer.get(0));
        if (byteRead == -1) {
            channel.close();
        } else {
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

    }

    public void handleWrite() throws IOException, InterruptedException {
        buffer.flip();
//        Thread.sleep(10000);
        channel.write(buffer);
        if (buffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }

        buffer.compact();
    }
}
