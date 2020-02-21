package cn.zs.learn.java.thread;

import java.util.concurrent.*;

public class LearnThreadPool {
    	public static void main(String[] args) throws ExecutionException, InterruptedException {
	    	/*
	    	 * 通过线程池实现多线程：创建线程池执行器，执行继承Runnable或Callable接口的方法体
	    	 * 
	    	 * 通常使用Executors.new……() 创建线程池，实现Runnable接口的对象使用execute执行任务，
	    	 * 	实现Callable接口的对象使用submit执行任务，
	    	 * 	
	    	 * 使用Callable有返回值，使用Runnale无返回值，
	    	 * 
	    	 * ThreadPoolExecutor的主要构造方法：
	    	 * 	ThreadPoolExecutor(int corePoolSize,
	    	 * 					 int maximumPoolSize,
	    	 * 					 long keepAliveTime,
	    	 * 					 TimeUnit unit,
	    	 * 					 BlockingQueue<Runnable> workQueue,
	    	 * 					 ThreadFactory threadFactory,
	    	 * 					 RejectedExecutionHandler handler)
	    	 * 
	    	 * ScheduledThreadPoolExecutor的主要构造方法（继承ThreadPoolExecutor）：
	    	 * 	ScheduledThreadPoolExecutor(int corePoolSize,
	    	 * 						RejectedExecutionHandler handler)  
	    	 *  	{
	    	 *  	super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
	    	 *  		  new DelayedWorkQueue(), handler);
	    	 *  	}
	    	 * 
	    	 * corePoolSize:核心池的大小，线程池初始化时创建线程的个数。
	    	 * 		当线程池数目达到corePoolSize，会把到达的任务放到缓存队列
	    	 * maximumPoolSize：线程池中能创建的线程的最大个数，线程池创建的数目不能大于该数字。
	    	 * keepAliveTime:线程没有任务执行时存活的时间
	    	 * unit:时间单位
	    	 * workQueue:阻塞队列
	    	 * threadFactory：线程工厂，主要用来创建线程
	    	 * handler:表示当拒绝处理任务时的策略
	    	 * 
	    	 * 
	    	 * CacheThreadPool，FixedThreadPool，SingleThreadExecutor实现的是ThreadPoolExecutor
	    	 * ScheduleThreadPool，SingleThreadSchedulerExecutor实现的是ScheduledThreadPoolExecutor
	    	 * 
	    	 * ScheduledThreadPoolExecutor比ThreadPoolExecutor多了几个scheduled方法，用于定时任务调度
	    	 * 
	    	 * 
	    	 * CacheThreadPool:corePoolSize=0
	    	 * FixedThreadPool:corePoolSize=n，maximumPoolSize=n
	    	 * SingleThreadExecutor:线程数量为1的FixedThreadPool
	    	 * ScheduleThreadPool:corePoolSize=n，maximumPoolSize=Integer.MAX_VALUE
	    	 * SingleThreadSchedulerExecutor:线程数量为1的ScheduleThreadPool
	    	 * 
	    	 * 
	    	 * 线程池的主要方法：
	    	 * 
	    	 * 	execute():向线程池提交任务，无返回值，提交实现Runnale接口的对象
	    	 * 	submit():向线程池提交任务，又返回值，提交实现Callable接口的对象
	    	 * 	shutdown():关闭线程池
	    	 * 
	    	*/
	    	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//	    	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
//	    	ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//	    	ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//	    	ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	    	
	    	cachedThreadPool.execute(new ThreadImplementRunable());
	    	Future<String> submit = cachedThreadPool.submit(new ThreadByCallableAndFuture());
            Future<?> submit1 = cachedThreadPool.submit(new ThreadImplementRunable());
            System.out.println(submit.get());
            System.out.println("submit1"+submit1.get());
            cachedThreadPool.shutdown();

	    
	    	// 每个线程池都可接受一个TreadFactory对象，用来创建进程及设置所创建进程的属性。
	    
			ExecutorService cachedThreadPool2 = Executors.newCachedThreadPool(new LearnThreadFactory());
	    	cachedThreadPool2.execute(new ThreadImplementRunable());
	    	cachedThreadPool2.shutdown();


	    	
	  }
}
