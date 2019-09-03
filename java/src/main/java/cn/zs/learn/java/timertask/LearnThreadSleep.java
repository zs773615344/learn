package cn.zs.learn.java.timertask;

import java.util.Date;

/*
 * 使用线程等待实现定时任务
*/

public class LearnThreadSleep {
    	public static void main(String[] args) throws InterruptedException {
	    final long interval = 1000;
	    	Runnable runnable = new Runnable() {
		    
		    @Override
		    public void run() {
				// TODO Auto-generated method stub
				while(true) {
				    System.out.println(new Date(System.currentTimeMillis()));
				    	try {
					    Thread.sleep(interval);
					} catch (InterruptedException e) {
					    // TODO Auto-generated catch block
					    e.printStackTrace();
					}
				}
		    }
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
