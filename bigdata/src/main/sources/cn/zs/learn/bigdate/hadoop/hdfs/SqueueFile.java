package cn.zs.learn.bigdate.hadoop.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.Options;

public class SqueueFile {

	public static void main(String[] args) throws IOException, Exception {
		Configuration conf = new Configuration();

		FileSystem fs = FileSystem.get(new URI("hdfs://172.26.40.74:9000"), conf);

		// ??????????????
		FileStatus[] files = fs.listStatus(new Path("hdfs://172.26.40.74:9000/zhang/"));

		Text key = new Text();
		Text value = new Text();

		// ???????????
		SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
				new Path("hdfs://172.26.40.74:9000/zhang1/zhang.txt"), key.getClass(), value.getClass());
		InputStream in = null;
		byte[] buffer = null;

		for (int i = 0; i < files.length; i++) {
			key.set(files[i].getPath().getName());
			in = fs.open(files[i].getPath());
			buffer = new byte[(int) files[i].getLen()];
			IOUtils.readFully(in, buffer, 0, buffer.length);
			value.set(buffer);
			IOUtils.closeStream(in);
			System.out.println(key.toString() + "\n" + value.toString());
			writer.append(value, new Text());
		}

		IOUtils.closeStream(writer);
	}
}