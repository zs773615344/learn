package cn.zs.learn.java.thread;

public class LearnJoinMethod {
    	public static void main(String[] args) throws InterruptedException {
//    	    	Sleeper sleepy = new Sleeper("sleepy", 15000),grumpy = new Sleeper("grumpy", 15000);
//    	    	Joiner depoy = new Joiner("depoy", sleepy),doc = new Joiner("doc", grumpy);
//    	    	grumpy.interrupt();
//    	    	System.out.println(grumpy.isInterrupted());

//    	    Thread thread = new Thread(new Runnable() {
//	        	public void run() {
//	        		for(int i=0;i<1000;i++) {
//							System.out.println(Thread.currentThread().getName()+"-------->"+i);
//						}
//	        	}
//    	    });
//    	    thread.start();
//    	    thread.join(100);
//    	    for(int i=0;i<100;i++) {
//		    System.out.println(Thread.currentThread().getName()+"############"+i);

			Thread mainthread = Thread.currentThread();
			Thread thread1 = new Thread(new Runnable() {
	        	public void run() {
	        		synchronized (mainthread) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							System.out.println(e);
						}
						for(int i=0;i<10;i++) {
							System.out.println(Thread.currentThread().getName()+"-------->"+i);
						}
					}
	        	}
    	    });

			Thread thread2 = new Thread(new Runnable() {
				public void run() {
					synchronized (thread1) {
					try {
						Thread.sleep(2000);
						System.out.println("shuimian");
						mainthread.join();
					} catch (InterruptedException e) {
						System.out.println(e);
					}

						for(int i=0;i<10;i++) {
							System.out.println(Thread.currentThread().getName()+"+++++++++>"+i);
						}


					}

				}
			});

			thread1.start();
			thread2.start();
			synchronized (mainthread) {
				mainthread.wait(10000);
			}
			for(int i=0;i<10;i++) {
				System.out.println(Thread.currentThread().getName()+"-------->"+i);
			}
	}
}
class Sleeper extends Thread {
    private int time;
    public Sleeper(String name,int time) {
		super(name);
		this.time = time;
		start();
    }
    
    @Override
    public void run() {
	try {
	    sleep(time);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    System.out.println(getName()+" was interrupted."+" isInterrupted():"+isInterrupted());
	    return;
	}
	System.out.println(getName()+" has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name,Sleeper sleeper) {
	super(name);
	this.sleeper = sleeper;
	start();
    }
    
    @Override
    public void run() {
	try {
	    sleeper.join();
//	    sleeper.join(1000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    System.out.println("interrupted");
	}
	System.out.println(getName()+" join completed");
    }
}