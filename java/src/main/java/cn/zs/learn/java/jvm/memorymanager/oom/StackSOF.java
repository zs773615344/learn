package cn.zs.learn.java.jvm.memorymanager.oom;
/**
 * VM ARGS： -Xss256k
*/
public class StackSOF {
	private int stackLength = 1;
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}
	public static void main(String[] args) {
		StackSOF oom = new StackSOF();
		try {
			oom.stackLeak();			
		} catch (Throwable e) {
			System.out.println("stack length:" + oom.stackLength);
			throw e;
		}
	}
}
