package cn.zs.learn.bigdate.hadoop.mr;

import java.io.IOException;
import java.util.*;
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

public class MTJoin {

	public static int time = 0;

	/*
	 * ��map������������������������ұ�Ȼ�������ֵ���зָ
	 * ������������keyֵ��ʣ���к����ұ��־��value�У�������
	 */
	public static class Map extends Mapper<Object, Text, Text, Text> {

		// ʵ��map����
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();// ÿ���ļ�
			String relationtype = new String();// ���ұ��ʶ

			// �����ļ����У�������
			if (line.contains("name") == true || line.contains("addressed") == true) {
				return;
			}

			// �����һ��Ԥ�����ı�
			StringTokenizer itr = new StringTokenizer(line);
			String mapkey = new String();
			String mapvalue = new String();
			int i = 0;
			while (itr.hasMoreTokens()) {
				// �ȶ�ȡһ������
				String token = itr.nextToken();
				// �жϸõ�ַID�ͰѴ浽"values[0]"
				// charAt() �����ɷ���ָ��λ�õ��ַ�
				if (token.charAt(0) >= '0' && token.charAt(0) <= '9') {
					mapkey = token;
					if (i > 0) {
						relationtype = "1";
					} else {
						relationtype = "2";
					}
					// break��������������ǰѭ����,continue���ڽ���ѭ�������������ִ��
					continue;
				}

				// �湤����
				mapvalue += token + " ";
				i++;
			}

			// ������ұ�
			context.write(new Text(mapkey), new Text(relationtype + "+" + mapvalue));
		}
	}

	/*
	 * reduce����map�������value�����ݰ������ұ�ֱ𱣴棬 ����* Ȼ������ѿ��������������
	 */
	public static class Reduce extends Reducer<Text, Text, Text, Text> {

		// ʵ��reduce����
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

			// �����ͷ
			if (0 == time) {
				context.write(new Text("name"), new Text("address"));
				time++;
			}

			int factorynum = 0;
			String[] factory = new String[10];
			int addressnum = 0;
			String[] address = new String[10];

			Iterator ite = values.iterator();
			while (ite.hasNext()) {
				String record = ite.next().toString();
				int len = record.length();
				int i = 2;
				if (0 == len) {

					continue;
				}

				// ȡ�����ұ��ʶ
				char relationtype = record.charAt(0);

				// ���
				if ('1' == relationtype) {
					factory[factorynum] = record.substring(i);
					factorynum++;
				}

				// �ұ�
				if ('2' == relationtype) {
					address[addressnum] = record.substring(i);
					addressnum++;
				}
			}

			// ��ѿ�����
			if (0 != factorynum && 0 != addressnum) {
				for (int m = 0; m < factorynum; m++) {
					for (int n = 0; n < addressnum; n++) {
						// ������
						context.write(new Text(factory[m]), new Text(address[n]));
					}
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		// ��仰�ܹؼ�
		conf.set("mapred.jar", "mt.jar");

		String[] ioArgs = new String[] { "m_in", "m_out" };
		String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: Multiple Table Join <in> <out>");
			System.exit(2);
		}

		Job job = new Job(conf, "Multiple Table Join");
		job.setJarByClass(MTJoin.class);

		// ����Map��Reduce������
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		// �����������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// ������������Ŀ¼
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
