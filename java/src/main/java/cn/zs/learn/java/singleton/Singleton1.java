package cn.zs.learn.java.singleton;

public class Singleton1 {
	
	/*
	 * 懶漢式，即延遲實例化，等需要使用的時候在實例化，線程不安全
	 * 
	 * 當處於多線程環境中，多個線程同時第一次執行getInstance()獲取實例時，此時instance爲null：
	 * 	  當線程1執行到if(instance == null)且還未執行到instance = new Singleton1()，如果
	 * 線程2也執行到if(instance == null)且還未執行到instance = new Singleton1()，此時
	 * instance都爲null；
	 * 	  當線程1執行到instance = new Singleton1()，會產生一個實例對象。此時線程2還認爲instance爲
	 * null，此時又會產生一個實例對象。
	*/
	private Singleton1() {}
	
	private static Singleton1 instance = null;

	public static Singleton1 getInstance() {
		if(instance ==  null) {
			instance = new Singleton1();
		}
		
		return instance;
	}
	

	
	
	/*
	 * 將getInstance方法改爲同步
	 * 修改後,線程安全，但會降低性能
	 * 
	 * 因爲只需要在第一次獲取實例產生實例對象的時候才需要同步，當instance不爲null後，就不需要同步了
	*/
/*	
	public static synchronized Singleton1 getInstance() {
		if(instance ==  null) {
			instance = new Singleton1();
		}
		
		return instance;
	}
*/
	
	
	
	
}
