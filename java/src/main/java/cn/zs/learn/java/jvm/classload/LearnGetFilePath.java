package cn.zs.learn.java.jvm.classload;

import java.net.URL;


public class LearnGetFilePath {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * 类名.class.getResource(String str)获得当前类的编译码的路径
		 * 		此时路径包含包名。
		 * 		str为""或".",则为当前类路径,即:	项目/target/classes/包路径/类名/
		 * 		str为"..",则为上一级路径,即:	项目/target/classes/包路径/
		 *		str为"/",则为项目编译码的根路径,即:	项目/target/classes/
		 *
		 * 类名.class.getClassLoader().getResource(String str)获得
		 * 		当前项目java代码的编译码存放的根路径，此时路径不包含包名
		 * 		str为""或".",则为根路径,即:	项目/target/classes/
		 * 		str为".."或"/",返回值都为null
		 * 
		 * 两者都不能跳出当前项目的编译码根路径(target/classes) 
		 * 
		 * 
		 * System.getProperty("user.dir") 获得当前项目的根路径 即: /.../项目名
		 * 
		 * System.getProperty("java.class.path") 获得当前项目所使用的jar的存放地址
		*/
//		System.out.println(LearnGetFilePath.class.getName());
//		System.out.println(LearnGetFilePath.class.getCanonicalName());
//		System.out.println(LearnGetFilePath.class.getPackage());
		
		URL resource = LearnGetFilePath.class.getClassLoader().getResource("/");
		System.out.println(resource);
		URL resource2 = LearnGetFilePath.class.getResource(".");
		System.out.println(resource2);
		
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(System.getProperty("java.class.path"));
//		
//		URL resource = LearnGetFilePath.class.getResource("/");
//		System.out.println(resource);
//
//		System.out.println(resource.getFile());
//		System.out.println(resource.getPath());
//		
//		File file = new File(resource.getFile()+"../../conf/");
//		File file = new File(resource.getPath()+"../../conf/");

//		System.out.println(file.getAbsolutePath());
//		System.out.println(file.getPath());
//		System.out.println(file.exists());
		
		
		
		
		
		
		
		
		
	}

}
