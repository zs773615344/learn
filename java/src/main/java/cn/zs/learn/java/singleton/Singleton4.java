package cn.zs.learn.java.singleton;

public class Singleton4 {
	private Singleton4() {}
	
	/*
	 * 使用靜態內部類實現延遲性單例模式
	 * 
	*/
	private static class LazyHolder{
		private static final Singleton4 INSTANCE = new Singleton4();
	}
	
	public static final Singleton4 getInstance() {
		return LazyHolder.INSTANCE;
	}
}
