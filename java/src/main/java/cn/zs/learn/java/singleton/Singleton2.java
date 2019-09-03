package cn.zs.learn.java.singleton;

public class Singleton2 {
	
	/*
	 * 餓漢式，不管使不使用，都先初始化  線程安全
	*/
	private static Singleton2 instance = new Singleton2();
	
	private Singleton2() {}
	
	public static Singleton2 getInstance() {
		return instance;
	}
}
