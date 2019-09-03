package cn.zs.learn.java.thread;

public class ThreadExtendsThread extends Thread{

    @Override
    public void run() {
		System.out.println(Thread.currentThread().getName());
    }
    	
}
