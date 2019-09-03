package cn.zs.learn.bigdate.hadoop.hdfs;

import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ConfigurationPrinter extends Configured implements Tool {

	static {
		Configuration.addDefaultResource("hadoop/hdfs-site.xml");
		Configuration.addDefaultResource("hadoop/core-site.xml");
		Configuration.addDefaultResource("hadoop/yarn-site.xml");
		Configuration.addDefaultResource("hadoop/mapred-site.xml");
	}

	@Override
	public int run(String[] arg0) throws Exception {
		Configuration conf = getConf();
		String key = null;
		String value = null;
//		int m = 0, n = 0;
//		for (Entry<String, String> entry : conf) {
//			key = entry.getKey();
//			value = entry.getValue();
//			if (key.contains("yarn") || key.contains("mapreduce") || value.contains("yarn"))
//				System.out.println(key + "=" + value);
//			m++;
//		}
//
		conf = Job.getInstance(conf).getConfiguration();
//		for (Entry<String, String> entry : conf) {
//			key = entry.getKey();
//			value = entry.getValue();
//			if (key.contains("yarn") || key.contains("mapreduce") || value.contains("yarn"))
//				System.out.println(key + "=" + value);
//			n++;
//		}
//		System.out.println(m + "\t" + n);
		System.out.println(conf.get("fs.defaultFS"));
		for (Entry<String, String> entry : conf) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new ConfigurationPrinter(), args);
		System.out.println(exitCode);

	}

}
