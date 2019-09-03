package cn.zs.learn.java.thread;

import java.util.concurrent.Callable;

public class ThreadByCallableAndFuture implements Callable<String> {

    @Override
    public String call() throws Exception {
		
		System.out.println(Thread.currentThread().getName());
		return "haha";
    } 

}
