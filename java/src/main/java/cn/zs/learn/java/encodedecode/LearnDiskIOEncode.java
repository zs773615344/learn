package cn.zs.learn.java.encodedecode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LearnDiskIOEncode {
    	public static void main(String[] args) throws Exception {
	    
    	    	String file = "/home/zhang/hehe";
    	    	String charset = "UTF-8";
    	    	
    	    	FileOutputStream outputStream = new FileOutputStream(file);
    	    	OutputStreamWriter writer = new OutputStreamWriter(outputStream,charset);
    	    	writer.write("这是要保存的中文字符");
    	    	writer.flush();
    	    	writer.close();
    	    	outputStream.close();
    	    	
    	    	FileInputStream inputStream = new FileInputStream(file);
    	    	InputStreamReader reader = new InputStreamReader(inputStream,charset);
    	    	StringBuffer sb = new StringBuffer();
    	    	char[] buffer = new char[64];
    	    	int count = 0;
//    	    	System.out.println(reader.read(buffer));
    	    	while ((count=reader.read(buffer)) != -1) {
    	    	    sb.append(buffer);
    	    	}
    	    	reader.close();
    	    	inputStream.close();
    	    	
    	    	System.out.println(sb);
	}
}
