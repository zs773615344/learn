package cn.zs.learn.java.thread;

public class LearnThreadLocalVariable {

    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>();

    static InheritableThreadLocal<Integer> inheritableThreadLocal;
    /**
     * ThreadLocal<>线程本地存储变量
     * 每个线程本地都会存储一个变量副本.
     *
     *
    */

    public static void main(String[] args) {
        new InnerClass(1).start();
        new InnerClass(3).start();
        new InnerClass(5).start();
        System.out.println("main " + integerThreadLocal.get());
    }
    static class InnerClass extends Thread {
        private int num;
        public InnerClass(int num) {
            this.num = num;
            integerThreadLocal.set(num);
        }
        @Override
        public void run() {
            System.out.println(integerThreadLocal.get());
            integerThreadLocal.set(num );
            System.out.println(integerThreadLocal.get());
        }
    }
}
