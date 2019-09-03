package cn.zs.learn.java.thread;


public class CountDown {
	public static void main(String[] args) throws Exception {
		Num num = new Num();
		
		new Thread() {
			@Override
			public void run() {
                synchronized (num) {
				    while (num.i > 0) {
						if (num.flag) {
							System.out.println(num.i-- + Thread.currentThread().getName());
							num.flag = false;
							num.notify();
						} else {
							try {
								num.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				synchronized (num) {
					while (num.i > 0) {
						if (!num.flag) {
							System.out.println(num.i-- + Thread.currentThread().getName());
							num.flag = true;
							num.notify();
						}else {
							try {
								num.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.start();
	}
}

class Num {
	int i = 10000;
	boolean flag = true;
}