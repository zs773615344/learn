package cn.zs.learn.bigdate.hadoop.hdfs;

import java.io.File;

public class MainApp {

	static {
		System.out.println("静态代码块");
	}

	public MainApp() {
		// TODO Auto-generated constructor stub
		System.out.println("构造函数");
	}

	public static MainApp getInstance() {
		return new MainApp();
	}

	public static void main(String[] args) throws Exception {
		// MainApp.getInstance();
		// Configuration conf = new Configuration();
		// String string = conf.get("mapreduce.application.framework.path","");
		// Job job = new Job(conf);
		// Configuration configuration = job.getConfiguration();
		// String string2 = configuration.get("mapreduce.application.framework.path");
		// System.out.println(string2);
		//
		// int numReduceTasks = job.getNumReduceTasks();
		// System.out.println(numReduceTasks);
		File file = new File("D:\\workspace\\wordcont.jar");
		System.out.println(file.exists());
	}
}

class A {
	String str;

	String getStr() {
		return str;
	}
}

class B extends A {

}
