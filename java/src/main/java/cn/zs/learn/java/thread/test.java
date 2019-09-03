package cn.zs.learn.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    	public static void main(String[] args) throws InterruptedException {
//	    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//	    for(int i=0;i<5;i++) 
//		cachedThreadPool.execute(new LiftOff());
//		cachedThreadPool.shutdown();
//	    Thread thread = new Thread(new LiftOff());
//	    thread.setDaemon(true);
//	    thread.start();
//			try {
//				ExecptionThread execptionThread = new ExecptionThread();
////			execptionThread.setUncaughtExceptionHandler(new ExecptionThread.ExceptionHandle());
//				execptionThread.start();
//			} catch (RuntimeException e) {
//				System.out.println(e);
//			}
			Thread thread  = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(System.currentTimeMillis());
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						System.out.println(System.currentTimeMillis());
						System.out.println(e);
					}

				}
			});
			thread.start();
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				System.out.println(e);
//			}
			thread.interrupt();
			System.out.println(Integer.SIZE-3);

			System.out.println((int)(-1 << 29) | 0);
	}
}

class ExecptionThread extends Thread{

	@Override
	public void run() {
		throw new RuntimeException();
	}

	static class ExceptionHandle implements Thread.UncaughtExceptionHandler{

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println(e);
		}
	}
}



class LiftOff implements Runnable{
    	protected int countDown = 10;
    	private static int taskCount = 0;
    	private final int id = taskCount++;
    	public LiftOff() {}
    	public LiftOff(int countDown) {
    	    this.countDown = countDown;
    	}
    	
    	public String status() {
    	    return "#"+id+"("+(countDown>0?countDown:"LiftOff!")+")";
    	}
    @Override
    public void run() {
		while(countDown-->0) {
		    System.out.print(status());
		    Thread.yield();
//		    try {
//			Thread.sleep(1000);
//		    } catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		    }
		}
    }
    
}