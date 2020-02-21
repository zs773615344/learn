package cn.zs.learn.java.socket.pingpackage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class PingSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket = new Socket("192.168.1.245",8088);
        OutputStream outputStream = socket.getOutputStream();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        outputStream.write("xintiao\n".getBytes(StandardCharsets.UTF_8));
                        Thread.sleep(2000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        int i = 1000000;
        while (i-- > 0) {
            outputStream.write("############\n".getBytes(StandardCharsets.UTF_8));
            Thread.sleep(500);
        }
        socket.close();

    }
}
