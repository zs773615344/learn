package cn.zs.learn.java.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class LearnThread {
    	public static void main(String[] args) throws InterruptedException, ExecutionException {
    	    /*	
    	     * 线程的周期：新建状态----->执行start()----->就绪状态----->获取cpu资源，执行run()----->运行状态------>run()执行完------>死亡状态(可用stop与destory()强制中止)
    	     * 			   当线程阻塞，让出cpu资源，会进入阻塞状态，当再次获取cpu资源，又会进入就绪状态
    	     * 		阻塞状态：等待阻塞：运行状态中执行wait()方法
    	     * 				同步阻塞：线程在获取synchronized同步锁失败
    	     * 				其他阻塞：通过调用线程的sleep()或join()发出了i/o请求时
    	     * 线程的优先级：取值范围：1(min)--->10(max),默认每个线程都会分配优先级5
    	     * 		优先级不能保证线程执行的顺序，而且非常依赖于平台
    	     * 
    	     * thread重要方法：
    	     * 		start(),run(),setPriority(int priority),getPriority(),isAlive()，isDasemon(),
    	     *
    	     *      setDaemon(boolean on) 将该线程标记为守护线程程。
    	     *          守护线程：在后台提供通用服务的线程，不属于程序不可或缺的部分。
             *          当所有非后台线程结束时，程序就会终止，不会管后台线程是否还在运行。
             *          main()就是非后台进程
             *
             *      interrupt():中断线程直到目标线程t结束才恢复,只有阻塞状态下才能被中断.
             *
             *      isInterrupt():判断线程是否中断，但在catch子句中，返回值总是为false
             *
    	     * 		join(long millis) 线程在调用另一个线程t的join方法即t.jion()，则线程将被挂起，直到目标线程t结束才恢复，
    	     * 	    	若加上参数，则线程t在这段时间没结束的话，此线程会继续运行。t线程可能被调用interrupt中断。
    	     *
    	     * Thread的静态方法（不管任何对象调用，都作用在当前线程）
    	     * 		yield() 暂停正在执行的线程对象，让优先级相同或更高的线程有机会执行，但
    	     * 	    	不会将线程转入阻塞状态，只是强制当前线程进入就绪状态，因此
    	     * 	    	可能线程调用yield()方法暂停后又立即获得资源继续运行。
    	     * 	    yield()方法不会释放锁（不建议使用）
    	     *
    	     * 		sleep() 让当前线程休眠,会将线程转入阻塞状态，让其他线程有机会执行。
    	     * 	    	但不会释放对象锁，即有同步代码块时，其他线程无法访问共享资源
    	     *
    	     * 		currentThread() 当前线程对象的引用
    	     * 		holdsLock() 当且仅当当前线程在指定的对象上保持监视器锁时，才返回true
    	     * 		dumpStack() 将当前线程的堆栈跟踪打印至标准错误流
    	     * 
    	    */
    	    
    	    
    	    System.out.println(Thread.currentThread().getName());
    	    
    	    // 通过继承Runable接口
    	    Thread threadImplementRunable = new Thread(new ThreadImplementRunable(),"threadImplementRunable");    	    
    	    threadImplementRunable.start();
    	    
    	    
    	    // 通过继承Thread类重写run()方法。
    	    ThreadExtendsThread threadExtendsThread = new ThreadExtendsThread();
    	    threadExtendsThread.setName("threadExtendsThread");
    	    threadExtendsThread.start();
    	    	
    	    /**
    	     *  通过实现callable接口的call方法作为线程体，创建callable对象，使用FutureTask包装callable对象，
             *  将FutureTask作为Thread的target创建线程。（FutureTask继承了runnable接口）
             *  可获得返回值
    	    */
    	    ThreadByCallableAndFuture callable = new ThreadByCallableAndFuture();
    	    FutureTask<String> ft = new FutureTask<String>(callable);
    	    Thread threadByCallableAndFuture = new Thread(ft, "threadByCallableAndFuture");
    	    System.out.println(ft.isDone());
    	    threadByCallableAndFuture.start();
    	    System.out.println(ft.get());
    	    System.out.println(ft.isDone());
    	    	
	}
}
