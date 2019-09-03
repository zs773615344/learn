package cn.zs.learn.java.thread;


public class LearnWaitNotifyMethod {
    /**
     *  线程协作：通过wait()和notify()、notifyAll()
     * wait()调用，会使线程挂起，并释放对象锁，并且只有在notify()或noyifyAll()发生时，才会被唤醒。
     * wait()、notify()、notifyAll()只能同步代码块中调用
     * wait()、notify()、notifyAll()的调用只能是当前锁的对象去调用。
     *
     * wait()、notify()、notifyAll()都是继承自Object的方法。
     *
   	*/

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        System.out.println();
    	    Thread threadA = new Thread() {
                public void run() {

                    // 此处的this指的是当前类的内部类，即实现了Thread接口的匿名类的实例
                    System.out.println(this.getClass().getName());
                    synchronized (this) {
                        System.out.println("before waiting");
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//    					notify();
                        System.out.println("after waiting");
                    }

                }

    	    };

//    	    threadA.start();
//            System.out.println(threadA.getName());
//            System.out.println(threadA.getClass().getName());
    	    
    	    new Thread() {
                public void run() {
                    System.out.println(this.getClass().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    synchronized (threadA) {
//    		    	synchronized (o) {
                        System.out.println("before notify");
                        /**
                         * 此处不能直接用notify()或者this.notify()，因为都是当前线程的匿名类在调用notify()方法
                         * 应该直接使用锁对象去调用。即threadA或o；
                         */
                        threadA.notify();
//		    			this.notify();


                        /**
                         *使用sleep测试notify后会不会释放锁，得出结果为：
                         *  notify只是唤醒别的争夺该锁对象的线程，让其进入就绪状态，并不会释放锁，当前线程依旧会继续执行。
                         */
//                        try {
//                            sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        /**
                         * 使用join测试notify后会不会释放锁，得出结果为：
                         *  notify不会释放锁，join也不会释放锁，但是join会把当前线程拥有的锁传递给被调用的线程，被调用的线程拥有锁
                         *  去执行，然后等被调用的线程执行完后，再将锁返回给当前线程去执行
                         */
//						try {
//							threadA.join();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
                        System.out.println("after notify");
                    }
                }
            }/*.start()*/;

        //配和上面的join，此时需要让上个线程先获得锁执行
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (threadA) {
                    System.out.println("after join");
                }
            }
        }/*.start()*/;
        /**
         * 以下测试synchronized代码块和synchronized方法对wait、notify的影响。
         *结果：并没有什么区别，都是锁的同一个对象才能构成同步的意义。
         *
         */
        System.out.println("#######################################");
        new SynClass().start();
        Thread.sleep(3000);
        new SynClass().start();

//            SynClass2 synClass2 = new SynClass2();
//            new Thread(synClass2).start();
//            Thread.sleep(3000);
//            new Thread(synClass2).start();

//            System.out.println("#######################################");
//            new Thread(new SynClass2()).start();
//            Thread.sleep(3000);
//            new Thread(new SynClass2()).start();


    }

    private static volatile boolean flag = true;

    static class SynClass extends Thread {

        @Override
        public void run() {
            try {
//                getSyn1();
                getSyn2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void getSyn1() throws InterruptedException {
            if (flag) {
                System.out.println("before waiting");
                flag = false;
                System.out.println("sleep********" + System.currentTimeMillis());
                Thread.sleep(3000);
                wait();
                System.out.println("after waiting");
            } else {
                System.out.println("sleep********" + System.currentTimeMillis());
                System.out.println(" before notify");
//				notify();
                notifyAll();
                System.out.println("after notify");
            }
        }

        public void getSyn2() throws InterruptedException {
            synchronized (this) {
                if (flag) {
                    System.out.println("before waiting");
                    flag = false;
                    System.out.println("sleep********" + System.currentTimeMillis());
                    Thread.sleep(3000);
                    wait();
                    System.out.println("after waiting");
                } else {
                    System.out.println("sleep********" + System.currentTimeMillis());
                    System.out.println(" before notify");
//					notify();
                    notifyAll();
                    System.out.println("after notify");
                }
            }
        }
    }

    static class SynClass2 implements Runnable {

        @Override
        public void run() {
            try {
                getSyn1();
//                getSyn2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void getSyn1() throws InterruptedException {
            if (flag) {
                System.out.println("before waiting");
                flag = false;
                wait();
                System.out.println("after waiting");
            } else {
                System.out.println(" before notify");
//				notify();
                notifyAll();
                System.out.println("after notify");
            }
        }

        public void getSyn2() throws InterruptedException {
            synchronized (this) {
                if (flag) {
                    System.out.println("before waiting");
                    flag = false;
                    wait();
                    System.out.println("after waiting");
                } else {
                    System.out.println(" before notify");
//					notify();
                    notifyAll();
                    System.out.println("after notify");
                }
            }
        }
    }
	
    
    		// 网上的例子
//    private String[] shareObj = { "true" };
//
//    public static void main(String[] args) {
//		LearnWaitNotifyMethod test = new LearnWaitNotifyMethod();
//        ThreadWait threadWait1 = test.new ThreadWait("wait thread1");
//        threadWait1.setPriority(2);
//        ThreadWait threadWait2 = test.new ThreadWait("wait thread2");
//        threadWait2.setPriority(3);
//        ThreadWait threadWait3 = test.new ThreadWait("wait thread3");
//        threadWait3.setPriority(4);
//
//        ThreadNotify threadNotify = test.new ThreadNotify("notify thread");
//
//        threadNotify.start();
//        threadWait1.start();
//        threadWait2.start();
//        threadWait3.start();
//    }
//
//    class ThreadWait extends Thread {
//
//        public ThreadWait(String name){
//            super(name);
//        }
//
//        public void run() {
//            synchronized (shareObj) {
//                while ("true".equals(shareObj[0])) {
//                    System.out.println("线程"+ this.getName() + "开始等待");
//                    long startTime = System.currentTimeMillis();
//                    try {
//                        shareObj.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    long endTime = System.currentTimeMillis();
//                    System.out.println("线程" + this.getName() 
//                            + "等待时间为：" + (endTime - startTime));
//                }
//            }
//            System.out.println("线程" + getName() + "等待结束");
//        }
//    }
//
//    class ThreadNotify extends Thread {
//
//        public ThreadNotify(String name){
//            super(name);
//        }
//
//
//        public void run() {
//            try {
//                // 给等待线程等待时间
//                sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            synchronized (shareObj) {
//                System.out.println("线程" + this.getName() + "开始准备通知");
//                shareObj[0] = "false";
//                shareObj.notifyAll();
//                System.out.println("线程" + this.getName() + "通知结束");
//            }
//            System.out.println("线程" + this.getName() + "运行结束");
//        }
//    }    	
    	
}
