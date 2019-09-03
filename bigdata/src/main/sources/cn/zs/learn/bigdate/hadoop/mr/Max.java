package cn.zs.learn.bigdate.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Max {

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(DateCount.class);

		Path src = new Path("hdfs://172.26.40.74:9000/zhang/HTTP_20130313143750.dat");
		FileInputFormat.setInputPaths(job, src);

		job.setMapperClass(MaxMapper.class);

		job.setCombinerClass(MaxReduce.class);

		job.setReducerClass(MaxReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		Path dst = new Path("hdfs://172.26.40.74:9000/zhang/output2");
		FileOutputFormat.setOutputPath(job, dst);
		System.out.println(job.waitForCompletion(true) ? 0 : 1);

	}

	public static class MaxMapper extends Mapper<Object, Text, Text, LongWritable> {

		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			String[] fields = value.toString().split("\t");
			String tel = fields[1];
			Long sum = Long.parseLong(fields[8]) + Long.parseLong(fields[9]);
			context.write(new Text(tel), new LongWritable(sum));
		}

	}

	public static class MaxReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

		@Override
		protected void reduce(Text key, Iterable<LongWritable> value,
				Reducer<Text, LongWritable, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			Long max = 0L;
			String telno;
			for (LongWritable val : value) {
				if (val.get() > max) {
					max = val.get();
				}
			}

		}

	}

}
