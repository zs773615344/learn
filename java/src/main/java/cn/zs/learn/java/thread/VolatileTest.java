package cn.zs.learn.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile可以保证变量的可见性和禁止指令重排序，但不能保证对变量操作的原子性
 *
*/

public class VolatileTest {
    public static volatile int race = 0;
    public static AtomicInteger race2 = new AtomicInteger();
    public static void increase() {
        race ++;
        VolatileTest.race ++;
        race2.getAndIncrement();
    }

//    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
//        Thread[] threads = new Thread[THREAD_COUNT];
//        for (int i = 0; i < THREAD_COUNT; i ++) {
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int j = 0; j < 10000; j ++) {
//                        increase();
//                    }
//                }
//            });
//            threads[i].start();
//        }
//
//        // Thread.activeCount()在idea上运行返回值是2，在eclipse上为1，在命令行是1
//        System.out.println(Thread.activeCount());
//
//        while (Thread.activeCount() > 2) {
//            Thread.yield();
//        }
//        System.out.println(race);
//        System.out.println(race2.get());




        VolatileTest o = new VolatileTest();
        new Thread() {
            @Override
            public void run() {
                System.out.println(o.inner == null);
                for (int i = 0 ; i < 10; i ++) {
                    o.inner = new Inner();
                    System.out.println(Thread.currentThread().getName() + " \t " + o.inner);
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.inner = null;
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Inner inner = null;
                do {
                    try {
                        System.out.println(Thread.currentThread().getName() + " \t " + inner);
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    inner = o.inner;
                } while (inner != null);
            }
        }.start();

    }
    private  volatile Inner inner;
    public VolatileTest() {
        inner = new Inner();
    }
}
class Inner {

}