package cn.zs.learn.bigdate.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DateCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(DateCount.class);

		Path src = new Path("hdfs://172.26.40.74:9000/zhang/HTTP_20130313143750.dat");
		FileInputFormat.setInputPaths(job, src);

		job.setMapperClass(DCMapper.class);

		job.setReducerClass(DCReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Serialize.class);

		Path dst = new Path("hdfs://172.26.40.74:9000/zhang/output1");
		FileOutputFormat.setOutputPath(job, dst);
		System.out.println(job.waitForCompletion(true) ? 0 : 1);

	}

	public static class DCMapper extends Mapper<Object, Text, Text, Serialize> {

		@Override
		protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			String[] date = value.toString().split("\t");
			String tel = date[1];
			long up = Long.parseLong(date[8]);
			long down = Long.parseLong(date[9]);
			Serialize ser = new Serialize(tel, up, down);
			context.write(new Text(tel), ser);
		}

	}

	public static class DCReduce extends Reducer<Text, Serialize, Text, Serialize> {

		@Override
		protected void reduce(Text key, Iterable<Serialize> value,
				Reducer<Text, Serialize, Text, Serialize>.Context context) throws IOException, InterruptedException {
			long up_sum = 0;
			long down_sum = 0;
			for (Serialize val : value) {
				up_sum = up_sum + val.getUp();
				down_sum = down_sum + val.getDown();
			}
			context.write(key, new Serialize("", up_sum, down_sum));
		}

	}
}
