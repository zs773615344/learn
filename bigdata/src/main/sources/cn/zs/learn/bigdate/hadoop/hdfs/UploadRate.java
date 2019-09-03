package cn.zs.learn.bigdate.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class UploadRate {
	public static void main(String[] args) throws IOException, URISyntaxException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.1.224:9000"), conf);
		FSDataInputStream open = fs.open(new Path("/haha/haha"));
		FileStatus status = fs.getFileStatus(new Path("/haha/haha"));
		final long l = status.getLen() / 65536;
		long b = status.getBlockSize();
		System.out.println(b / 1024 / 1024);
		System.out.println(l * 100 / 1024 / 1024 / 1024);
		FSDataOutputStream create = fs.create(new Path("/haha/copy"), new Progressable() {
			int count = 0;

			// this method was invoked when client sent a packet(k4k) to datanode
			@Override
			public void progress() {
				// TODO Auto-generated method stub
				count++;
				System.out.println(count * 100 / l + "%");
			}
		});
		try {
			IOUtils.copyBytes(open, create, 4096, false);
		} finally {
			IOUtils.closeStream(open);
			IOUtils.closeStream(create);
		}
	}
}
