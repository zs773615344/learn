package cn.zs.learn.java.datastructure.Map;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class LearnWeakHashMap {
    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<people,Integer> weakHashMap = new WeakHashMap<people,Integer>();
        people people1 = new people("zhangsan");
        people people2 = new people("lisi");
        people people3 = new people("wangwu");
        people people4 = new people("liuliu");
        weakHashMap.put(people1,1);
        weakHashMap.put(people2,2);
        weakHashMap.put(people3,3);
        weakHashMap.put(people4,4);
        System.out.println(weakHashMap);
        people4 = null;
        System.gc();
//        Thread.sleep(10000);
        System.out.println(weakHashMap);
        people3 = null;
//        System.gc();
//        Thread.sleep(10000);
        System.out.println(weakHashMap);
        people2 = null;
        System.out.println(weakHashMap.size());
        System.gc();
        Thread.sleep(1000);
        System.out.println(weakHashMap.size());
        System.out.println(weakHashMap);

    }
    static class people{
        private String name;
        private people alive;
        public people(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "people{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        protected void finalize() throws Throwable {
            alive = this;
            System.out.println("fuhuo");
        }
    }
}

