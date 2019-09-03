package cn.zs.learn.bigdate.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FSApiCat {

	private static Configuration conf = new Configuration();
	private static FileSystem fs = null;
	static {
		String url = "hdfs://192.168.1.231:9000";
		conf.set("fs.defaultFS", url);
		// conf.set("dfs.client.use.datanode.hostname", "true");
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		FSDataInputStream open = fs.open(new Path("/haha/start-hadoop.sh"));
		IOUtils.copyBytes(open, System.out, 4096);
		// FSDataOutputStream create = fs.create(new Path("/haha/java4"));
		// IOUtils.copyBytes(open, create, 4096,false);
		IOUtils.closeStream(open);
	}
}
