package cn.zs.learn.java.socket;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

public class LearnSocketClient {

    public static void main(String[] args) throws IOException {
		
		client = new Socket("192.168.1.245", 8088);
//		System.out.println(client.getInetAddress());
//		System.out.println(client.getReuseAddress());
//		System.out.println(client.getLocalAddress());
//		System.out.println(client.getLocalSocketAddress());
//		System.out.println(client.getRemoteSocketAddress());
//		System.out.println(client.getTrafficClass());
//		System.out.println(client.getLocalPort());
//		System.out.println(client.getPort());
//		System.out.println(client.getReceiveBufferSize());
//		System.out.println(client.getSendBufferSize());
//		System.out.println(client.getSoLinger());
		System.out.println(client.getSoTimeout());
//		System.out.println(client.getOOBInline());
//		System.out.println(client.getKeepAlive());
//		System.out.println(client.getTcpNoDelay());
		client.setSoTimeout(5000);
		System.out.println(client.getSoTimeout());
		System.out.println(client.getLocalPort());
		System.out.println(client.getPort());
		OutputStream outputStream = client.getOutputStream();
		byte[] bytes = "hello ,I am client\n".getBytes();
		for(int i=1; i<1000000;i++) {
		    
		    outputStream.write(bytes);
		}
		
//		client.shutdownOutput();

//		InputStream inputStream = client.getInputStream();
		PingInputStream inputStream = new PingInputStream(client.getInputStream());
		int read;
		while((read = inputStream.read()) != -1) {
		    System.out.print((char)read);
		}
		
		outputStream.flush();
	    inputStream.close();
		outputStream.close();
		client.close();
    }

    private static Socket client;
    private static InputStream inputStream;

    static class PingInputStream extends FilterInputStream {
		private  long lastTime;
		protected PingInputStream(InputStream in) {
			super(in);
			lastTime = System.currentTimeMillis();
		}


		@Override
		public int read() throws IOException {
			while (true) {
				try {
					int i = super.read();
					lastTime = System.currentTimeMillis();
					return i;
				} catch (SocketTimeoutException e) {
					handleTimeout(e);
				}
			}
		}

		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			while (true) {
				try {
					int i = super.read(b, off, len);
					lastTime = System.currentTimeMillis();
					return i;
				} catch (SocketTimeoutException e) {
					handleTimeout(e);
				}
			}
		}

		public void handleTimeout(SocketTimeoutException e) throws IOException {
			sendPing(lastTime);
		}


	}

	public static void sendPing(long lastTime) throws IOException {
    	if (System.currentTimeMillis() - lastTime > 5000)
    	client.getOutputStream().write("心跳".getBytes(StandardCharsets.UTF_8));
	}

	public static InputStream getInputStream() {
		return inputStream;
	}

	public static void setInputStream(InputStream inputStream) {
		LearnSocketClient.inputStream = inputStream;
	}

}
