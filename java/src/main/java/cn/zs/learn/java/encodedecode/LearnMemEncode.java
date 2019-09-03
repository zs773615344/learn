package cn.zs.learn.java.encodedecode;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class LearnMemEncode {
    	public static void main(String[] args) throws Exception {
    	    	
    	    String charsetName = "utf-8";
    	    	
    	    	// 字符串与字节的转换
    	    String str = "这是一段中文字符串";
//    	    char[] charArray = str.toCharArray();
    	    byte[] b = str.getBytes(charsetName);
//    	    str.getBytes(Charset.forName(charsetName));
    	    String str1 = new String(b, charsetName);
    	    	
    	    	// char[]与byte[]的转换
    	    Charset charset = Charset.forName(charsetName);
    	    ByteBuffer byteBuffer = charset.encode(str1);
    	    CharBuffer charBuffer = charset.decode(byteBuffer);
    	    System.out.println(byteBuffer);
    	    System.out.println(charBuffer);
    	    	
    	    	// ByteBuffer提供一种char和byte之间的软转换
    	    	ByteBuffer heapByteBuffer = ByteBuffer.allocate(1024);
    	    	ByteBuffer byteBuffer2 = heapByteBuffer.putChar('c');
    	    	System.out.println(byteBuffer2);
    	    	
	}
    	
    	
}
