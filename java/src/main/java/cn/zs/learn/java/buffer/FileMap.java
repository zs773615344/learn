package cn.zs.learn.java.buffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileMap {
    private final static String FILE_NAME = "";
    private final static int BUFFER_SIZE = 1024;
    public static void main(String[] args) {

        long fileLength = new File(FILE_NAME).length();
        int bufferCount = 1 + (int) (fileLength / BUFFER_SIZE);
        MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCount];
        long remaining = fileLength;
        for (int i = 0; i < bufferCount; i++) {
            RandomAccessFile file;
            try {
                file = new RandomAccessFile(FILE_NAME,"r");
                file.getChannel().map(FileChannel.MapMode.READ_ONLY,
                        i * BUFFER_SIZE, (int) Math.min(remaining, BUFFER_SIZE));
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            remaining -= BUFFER_SIZE;
        }
    }
}
