package cn.zs.learn.java.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultSocketServer {

    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(8089);

        while (true) {
//            Socket socket = serverSocket.accept();
            Socket server = serverSocket.accept();
            System.out.println(server);
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    Socket server = socket;
                    System.out.println("###################"+Thread.currentThread().getName()+"\t"+server+"\tbegin");
                    System.out.println(server.getPort());
                    System.out.println(server.getLocalPort());
                    System.out.println("远程主机地址："+server.getRemoteSocketAddress());
                    InputStream inputStream = null;
                    try {
                        inputStream = server.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int read = 0;
                    try {
                        read = inputStream.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while(read != -1) {
//				System.out.print((char)read);
                        try {
                            read = inputStream.read();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        server.shutdownInput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    OutputStream outputStream = null;
                    try {
                        outputStream = server.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] bytes = "hello ,I am server\n".getBytes();
                    try {
                        for(int i=1; i<100;i++) {
                            outputStream.write(bytes);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("###################"+Thread.currentThread().getName()+"\t"+server+"\tbegin");
                    try {
                        outputStream.flush();
                        inputStream.close();
                        outputStream.close();
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
class ProcessThread extends Thread {
    private Socket server;
    public ProcessThread(Socket socket) {
        this.server = socket;
    }

    @Override
    public void run() {
        System.out.println(server);
        System.out.println(server.getPort());
        System.out.println(server.getLocalPort());
        System.out.println("远程主机地址："+server.getRemoteSocketAddress());
        InputStream inputStream = null;
        try {
            inputStream = server.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int read = 0;
        try {
            read = inputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(read != -1) {
//				System.out.print((char)read);
            try {
                read = inputStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            server.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutputStream outputStream = null;
        try {
            outputStream = server.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = "hello ,I am server\n".getBytes();
        try {
            for(int i=1; i<100;i++) {
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}