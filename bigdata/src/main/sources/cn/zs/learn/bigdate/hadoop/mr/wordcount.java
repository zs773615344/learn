package cn.zs.learn.bigdate.hadoop.mr;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class wordcount {
	
	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
//		conf.set("mapreduce.framework.name", "yarn");
//		conf.set("yarn.resourcemanager.address", "10.11.100.137:8032");
//		conf.set("mapreduce.job.jar", "/home/shuai/eclipse-workspace/xml.jar");
//		GenericOptionsParser optionParser = new GenericOptionsParser(conf, args);
//	   String[] remainingArgs = optionParser.getRemainingArgs();
//		if (!(remainingArgs.length != 2 | | remainingArgs.length != 4)) {
//			System.err.println("Usage: wordcount <in> <out> [-skip skipPatternFile]");
//		   System.exit(2);
//		}
		Job job = Job.getInstance(conf, "wordcount");
		job.setJarByClass(wordcount.class);
//		job.setJar("/home/shuai/eclipse-workspace/wordcount.jar");

		Path src = new Path("hdfs://10.11.100.137:9000/haha");
		FileInputFormat.addInputPath(job, src);

		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		Path dst = new Path("hdfs://10.11.100.137:9000/output");
		FileOutputFormat.setOutputPath(job, dst);

		System.out.println(job.waitForCompletion(true) ? 0 : 1);
	}

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

}