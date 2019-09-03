package cn.zs.learn.bigdate.hadoop.mr;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * mapreduce�м�������ʹ��
 *
 */
public class WordCountApp {

	private static final String INPUT_PATH = "hdfs://172.26.40.74:9000/zhang/h.txt";
	private static final String OUTPUT_PATH = "hdfs://172.26.40.74:9000/zhang/output";

	public static void main(String[] args)
			throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();

		final FileSystem fileSystem = FileSystem.get(new URI(OUTPUT_PATH), conf);
		fileSystem.delete(new Path(OUTPUT_PATH), true);

		final Job job = new Job(conf, WordCountApp.class.getSimpleName());
		job.setJarByClass(WordCountApp.class);

		FileInputFormat.setInputPaths(job, INPUT_PATH);
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);

		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));

		job.waitForCompletion(true);
	}

	public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			final String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			final Counter counter = context.getCounter("hh", "hello");
			if (value.toString().contains("hello")) {
				counter.increment(1L); // ����ѯ������hello�Ĵ���ʱ����������1
			}
			while (tokenizer.hasMoreTokens()) {
				String target = tokenizer.nextToken();
				context.write(new Text(target), new LongWritable(1));
			}
		}
	}

	public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

		@Override
		protected void reduce(Text key, Iterable<LongWritable> value,
				Reducer<Text, LongWritable, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			long times = 0l;
			while (value.iterator().hasNext()) {
				times += value.iterator().next().get();
			}
			context.write(key, new LongWritable(times));
		}

	}

}