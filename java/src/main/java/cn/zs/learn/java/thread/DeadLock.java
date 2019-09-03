package cn.zs.learn.java.thread;

public class DeadLock {
    public static void main(String[] args) {
        String string1 = "string1";
        String string2 = "string2";
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (string1) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (string2) {
                        System.out.println(string2);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (string2) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (string1) {
                        System.out.println(string2);
                    }
                }
            }
        }).start();
    }
}
