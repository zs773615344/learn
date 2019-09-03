package cn.zs.learn.bigdate.hadoop.xml;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.streaming.StreamInputFormat;


/*
 * 用hadoop-streaming中的StreamInputFormat处理xml文件只能用旧api，因为StreamInputFormat继承的是旧版的InputFormat
*/
public class StreamXmlProcess {

	public static void main(String[] args) throws Exception {
		
		JobConf jobConf = new JobConf();
		jobConf.setJobName("streamXml");
		jobConf.setJarByClass(StreamXmlProcess.class);
//		jobConf.set("yarn.resourcemanager.address", "10.11.100.137:8032");
//		jobConf.setJar("/home/shuai/eclipse-workspace/xml.jar");
		jobConf.set("stream.recordreader.class", "org.apache.hadoop.streaming.StreamXmlRecordReader");
		jobConf.set("stream.recordreader.begin", "<page>" );  
		jobConf.set("stream.recordreader.end", "</page>" );
		jobConf.setMapperClass(StreamXmlMapper.class);
		jobConf.setReducerClass(StreamXmlReduce.class);
		jobConf.setInputFormat(StreamInputFormat.class);
		jobConf.setOutputFormat(TextOutputFormat.class );
		jobConf.setOutputKeyClass(Text.class);
		jobConf.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(jobConf, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));	
		JobClient.runJob(jobConf);
	}
	public static class StreamXmlMapper extends MapReduceBase  implements Mapper<Text, Text, Text, Text>{

		@Override
		public void map(Text key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			// TODO Auto-generated method stub
			System.out.println("map+++++++"+key.toString());
			System.out.println("map+++++++"+value.toString());
			output.collect(key, value);
		}
	}
	public static class StreamXmlReduce extends MapReduceBase implements Reducer<Text, Text, Text, Text>{

		@Override
		public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output,
				Reporter reporter) throws IOException {
			// TODO Auto-generated method stub
			while(values.hasNext()) {
				output.collect(key, values.next());
			}
		}
		
	}
}
