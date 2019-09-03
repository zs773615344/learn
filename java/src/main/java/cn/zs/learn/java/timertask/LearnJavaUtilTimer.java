package cn.zs.learn.java.timertask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
 * 使用java自带的类库java.util.Timer实现定时任务
 * 由Timer类构造一个单线程来控制任务
 * 可使用Timer.cancel()取消任务
*/

public class LearnJavaUtilTimer {
    
    public static void main(String[] args) throws InterruptedException {
		System.out.println(new Date(System.currentTimeMillis()));
//		delaySchedule();
//		
//		dateSchedule();
		
//		delayPeriodSchedule();
		
		datePeriodSchedule();
		
    	}
    
    	// 延迟毫秒执行
    public static void delaySchedule() throws InterruptedException {
		Timer timer = new Timer("delay:");
		timer.schedule(new Task(),5000);
//		Thread.sleep(1000);
	    	timer.cancel();
    }
    	
    	// 特定时间执行
    	public static void dateSchedule() throws InterruptedException {
    	    Timer timer = new Timer("timer:");
	    timer.schedule(new Task(), new Date(System.currentTimeMillis()+2000));
//	    Thread.sleep(1000);
	    	timer.cancel();
    	}
    	
    	// 第一次延迟delay执行后以固定的频率period执行
    	public static void delayPeriodSchedule() throws InterruptedException {
    	    Timer timer = new Timer("delayPeriod");
	    timer.schedule(new Task(), 2000,3000);
//	    Thread.sleep(11000);
//	    	timer.cancel();
    	}
    	
    	// 第一次在固定时间date执行后开始以固定的频率period执行
    	public static void datePeriodSchedule() throws InterruptedException {
    	    Timer timer = new Timer("datePeriod:");
	    timer.schedule(new Task(), new Date(System.currentTimeMillis()+5000),3000);
	    Thread.sleep(11000);
	    	timer.cancel();
    	}
    	
    	static class Task extends TimerTask {

	    @Override
	    public void run() {
			// TODO Auto-generated method stub
			System.out.println(Thread.currentThread().getName()+new Date(System.currentTimeMillis()));
	    }
    	    
    	}
}
