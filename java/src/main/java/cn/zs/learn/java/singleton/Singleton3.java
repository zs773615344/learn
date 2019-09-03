package cn.zs.learn.java.singleton;

public class Singleton3 {
	
	/*
	 * 使用雙重檢查加鎖方法修改懶漢式，在getInstance()減少使用同步
	*/
	
	/* 
	 * volatile 保證不同線程對這個變量進行操作的可見性，即一個線程修改了某個變量的值後，
	 * 修改的值會立即更新到主內存，當其他線程需要讀取時，會去內存讀取新值。
	 * 
	 * synchronized和lock保證同一時刻只有一個線程獲取鎖然後執行同步代碼，並且在釋放鎖
	 * 之前會將對變量的修改刷新到主內存中。保證了可見性
	 * 
	*/ 
	private volatile static Singleton3 instance;
	
	private Singleton3() {}
	
	/* 
	 *只有第一次獲取實例才執行同步代碼塊裏的代碼
	*/
	public static Singleton3 getInstance() {
		if(instance == null) {
			synchronized (Singleton3.class) {
				if(instance == null) {
					instance = new Singleton3();
				}
			}
		}
		
		return instance;
	}
}
