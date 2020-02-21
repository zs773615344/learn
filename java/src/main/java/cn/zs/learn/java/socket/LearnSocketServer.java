package cn.zs.learn.java.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class LearnSocketServer {
    public static void main(String[] args) throws IOException, InterruptedException {
		
		@SuppressWarnings("resource")
		ServerSocket serverSocket1 = new ServerSocket(8088);
//		ServerSocket serverSocket2 = new ServerSocket(8082, 0);
//		ServerSocket serverSocket1 = new ServerSocket(8083, 0,InetAddress.getLocalHost());
		
//		serverSocket1.setSoTimeout(10000);
//		System.out.println(serverSocket1.getLocalPort());
//		System.out.println(serverSocket1.getReceiveBufferSize());
//		System.out.println(serverSocket1.getReuseAddress());
//		System.out.println(serverSocket1.getInetAddress());
//		System.out.println(serverSocket1.getLocalSocketAddress());
//		serverSocket1.bind(new SocketAddress() {
//			@Override
//			public int hashCode() {
//				return super.hashCode();
//			}
//		});
		while(true) {		    
		    Socket server = serverSocket1.accept();
			System.out.println(server.getPort());
			System.out.println(server.getLocalPort());
		    System.out.println("远程主机地址："+server.getRemoteSocketAddress());
		    InputStream inputStream = server.getInputStream();
		    int read;
		    while((read = inputStream.read()) != -1) {
				System.out.print((char)read);
		    	}
		    	
		    server.shutdownInput();
		    
		    OutputStream outputStream = server.getOutputStream();
			byte[] bytes = "hello ,I am server".getBytes();
			outputStream.write(bytes);
			Thread.sleep(6000);
			outputStream.flush();
			inputStream.close();
			outputStream.close();
			
			server.close();
		}
		
		
	}
}
