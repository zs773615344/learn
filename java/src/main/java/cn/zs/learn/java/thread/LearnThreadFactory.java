package cn.zs.learn.java.thread;

import java.util.concurrent.ThreadFactory;

public class LearnThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		if(thread.isDaemon())
		    thread.setDaemon(false);
		if(thread.getPriority()!=7)
		    thread.setPriority(7);
		return thread;
    }

}
