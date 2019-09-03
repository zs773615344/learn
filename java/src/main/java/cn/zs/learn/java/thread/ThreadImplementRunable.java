package cn.zs.learn.java.thread;

public class ThreadImplementRunable implements Runnable {

    @Override
    public void run() {
	// TODO Auto-generated method stub
	System.out.println(Thread.currentThread().getName());
    }

}
