package cn.zs.learn.bigdate.hadoop.hdfs;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class URLCat {

	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String[] args) {
		URLConnection conn = null;
		InputStream in = null;
		try {
			// conn = new
			// URL("hdfs://192.168.1.224:9000/haha/output/part-r-00000").openConnection();
			in = new URL("hdfs://zhangshuai:9001/haha").openStream();
			IOUtils.copyBytes(in, System.out, 4096, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeStream(in);
		}
	}
}
