package cn.zs.learn.bigdate.hadoop.xml;

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
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import edu.umd.cloud9.collection.XMLInputFormat;

public class XmlInputFormatProcess {
	
	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
//		conf.set("yarn.resourcemanager.address", "10.11.100.137:8032");
//		conf.set("mapreduce.job.jar", "/home/shuai/eclipse-workspace/xml.jar");
		conf.set(XMLInputFormat.START_TAG_KEY, "<page>");
		conf.set(XMLInputFormat.END_TAG_KEY, "</page>");
		Job job = Job.getInstance(conf, "xmlinputformat");
		job.setJarByClass(XmlInputFormatProcess.class);
		job.setMapperClass(XmlInputMapper.class);
		job.setReducerClass(XmlOutputReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setInputFormatClass(XMLInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));		
		System.out.println(job.waitForCompletion(true)?0:1);
	}
	
	public static class XmlInputMapper extends Mapper<LongWritable, Text, LongWritable, Text>{

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			System.out.println("map+++++++"+key.toString());
			System.out.println("map+++++++"+value.toString());
			context.write(key, value);
		}
		
	}
	
	public static class XmlOutputReduce extends Reducer<LongWritable, Text, LongWritable, Text>{

		@Override
		protected void reduce(LongWritable arg0, Iterable<Text> arg1, Reducer<LongWritable, Text, LongWritable, Text>.Context arg2)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			for(Text value:arg1) {
				arg2.write(arg0, value);
			}
		}
		
	}
}
