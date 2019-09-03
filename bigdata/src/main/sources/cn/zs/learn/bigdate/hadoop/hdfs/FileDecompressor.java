package cn.zs.learn.bigdate.hadoop.hdfs;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

public class FileDecompressor {
	public static void main(String[] args) throws Exception {
		String uri = "hdfs://192.168.1.224:9000/haha/haha";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		Path path = new Path(uri);
		CompressionCodecFactory codecFactory = new CompressionCodecFactory(conf);
		CompressionCodec codec = codecFactory.getCodec(path);
		if (codec == null) {
			System.err.println("No codec found for");
			System.exit(1);
		}
		String outputUri = CompressionCodecFactory.removeSuffix(uri, codec.getDefaultExtension());
		InputStream in = null;
		OutputStream out = null;
		try {
			in = codec.createInputStream(fs.open(path));
			out = fs.create(new Path(outputUri));
			IOUtils.copyBytes(in, out, conf);
		} finally {
			IOUtils.closeStream(in);
			IOUtils.closeStream(out);
		}
	}
}
