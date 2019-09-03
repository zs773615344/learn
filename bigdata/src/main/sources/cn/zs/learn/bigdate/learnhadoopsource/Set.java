package cn.zs.learn.bigdate.learnhadoopsource;

import java.util.HashSet;

public class Set {
    public static void main(String[] args) {
        java.util.Set<Integer> set1 = new HashSet<Integer>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        java.util.Set<Integer> set2 = new HashSet<Integer>();
        set2.add(1);
        set2.add(2);
        set2.add(4);
//        set2.add(3);

//        java.util.Set<Integer> set3 = set1.clone();
        System.out.println(set1.containsAll(set2));
        /*System.out.println(set1.removeAll(set2));
        System.out.println(set1);*/
        System.out.println(set2.removeAll(set1));
        System.out.println(set2);
//        System.out.println(set1);
    }
}
