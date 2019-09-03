package cn.zs.learn.java.timertask;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 使用java1.5+后新增的java.util.concurrent.ScheduledExecutorService来实现定时任务
 * 通过线程池的方式来执行任务
 * 延迟时间和周期时间单位由参数TimeUnit决定，两个时间的单位相同
 * 可使用executor.shutdown()提前取消定时任务
 * 
*/

public class LearnScheduledExecutorService {
    	public static void main(String[] args) throws InterruptedException {
    	    System.out.println("开始："+new Date(System.currentTimeMillis()));
    	    	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    	    	
    	    	Runnable runnable = new Runnable() {
		    
		    @Override
		    public void run() {
			// TODO Auto-generated method stub
			System.out.println(Thread.currentThread().getName()+new Date(System.currentTimeMillis()));
		    }
		};
		
		// 延迟执行
//		executor.schedule(runnable, 5, TimeUnit.SECONDS);
		
		// 第一次延迟执行后以固定频率执行
		executor.scheduleAtFixedRate(runnable, 1, 2000, TimeUnit.MILLISECONDS);
		
		// 第一次延迟执行后以固定频率延迟delay执行，与上面无区别
//		executor.scheduleWithFixedDelay(runnable, 2, 3, TimeUnit.SECONDS);
		
//		Thread.sleep(5000); // 单位:毫秒
//		System.out.println("结束："+new Date(System.currentTimeMillis()));
//		
//		executor.shutdown();
	}
}
