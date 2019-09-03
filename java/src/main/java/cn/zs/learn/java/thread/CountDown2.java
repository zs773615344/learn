package cn.zs.learn.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CountDown2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new Thread() {
            @Override
            public void run() {
                synchronized (atomicInteger) {
                    while (atomicInteger.get() < 10) {
                        System.out.println(getName() + " " + atomicInteger.get());
                        try {
                            atomicInteger.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        atomicInteger.getAndIncrement();
                        atomicInteger.notify();
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                synchronized (atomicInteger) {
                    while (atomicInteger.get() < 10) {
                        atomicInteger.getAndIncrement();
                        System.out.println(getName() + " " + atomicInteger.get());
                        atomicInteger.notify();
                        try {
                            atomicInteger.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
