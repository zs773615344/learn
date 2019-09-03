package cn.zs.learn.java.jvm.memorymanager.oom;

/**
 * VM ARGS: -Xss2M
*/
public class StackOOM {
	private void dontStop() {
		while(true) {}
	}
	
	public void stackLeakByThread() {
		while(true) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					dontStop();
				}
				
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		StackOOM oom = new StackOOM();
		oom.stackLeakByThread();
				
	}
}
