package cn.zs.learn.java.thread;



public class LearnSynchronized {
    	/*
    	 * 原子性：操作不可分割，且不会被线程的中断机制打断。
    	 * 	除long，double外的基本类型的读取和写入都是原子操作，i++不是
    	 * 可视性：读取和写入操作不会得到优化（即不会写入缓存），
    	 *	 直接写入主存，保证其他线程可该操作可见。
    	 *
    	 *	原子性和可视性不是一回事，也没有什么联系。
    	 *
    	 *1）volatile 使变量具有可视性（使long，double具有原子性）
    	 *2)synchronized同步方法
    	 *3)synchronized同步代码块
    	 *4)synchronized锁其他对象
    	 *5)Lock lock = new ReentrantLock()
    	 *6)AtomicInteger、AtomicLong等原子类 //cas
    	 *7)ThreadLocal<>线程本地存储变量
    	 *  
    	 * 锁针对的是对象，当线程获得对象锁时，该对象的其他同步方法也被锁定。
    	 *
         * 同步代码块与同步方法的区别：
         *  当使用synchronized同步时，该对象的其他同步方法也被锁定，但非同步方法依旧可以调用。
         *      1）同步方法锁的是this对象，同步代码块可以指定锁的对象，但他们都针对的是实例对象，如果锁的不是同一个对象的话，也无法
         *      形成同步竞争的场景.
         *      2)同步方法能用static修饰,此时类似锁类对象,该类的其他的同步静态方法也被锁定,但不会对实例同步方法造成影响.
         *      3)同步范围不同，同步范围越大，性能越差.该代码没有体现。
         *
    	 */
        public static void main(String[] args) {
            SycClass sycClass = new SycClass();
//            SycClass sycClass1 = new SycClass();
//            SycClass sycClass2 = new SycClass();
//            SycClass sycClass3 = new SycClass();
//            SycClass sycClass4 = new SycClass();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (sycClass) {
//                    try {
//                        System.out.println("*******" + System.currentTimeMillis());
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SycClass.methodE();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SycClass.methodF();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sycClass.methodA();
//                    sycClass1.methodA();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sycClass.methodB();
//                sycClass2.methodB();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sycClass.methodC();
//                sycClass3.methodC();

                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sycClass.methodD();
//                sycClass4.methodD();
                }
            }).start();
        }

    static class SycClass {
        public synchronized void methodA() throws InterruptedException {
            System.out.println("methodA" + System.currentTimeMillis());
            Thread.sleep(3000);
        }

        public synchronized void methodB() {
            System.out.println("methodB" + System.currentTimeMillis());
        }

        public void methodC() {
            synchronized (this) {
                System.out.println("methodC" + System.currentTimeMillis());
            }
        }

        public void methodD() {
            String ss = "haha";
            synchronized (ss) {
                System.out.println("methodD" + System.currentTimeMillis());
            }
        }

        public static synchronized void methodE() throws InterruptedException {
            System.out.println("methodE" + System.currentTimeMillis());
            Thread.sleep(3000);
        }

        public static synchronized void methodF() throws InterruptedException {
            System.out.println("methodF" + System.currentTimeMillis());
            Thread.sleep(3000);
        }
    }
}
