package cn.zs.learn.bigdate.java;

import java.io.File;

public class Forfenhao {
    public static void main(String[] args) {
//        for (;;) {
//            System.out.println("haha");
//        }

        File file = new File("file:/Users/zhangshuai/idea-workspace/idea/bigdata/src/main/sources/cn/zhang/bigdata/hadoop/Forfenhao.java");
        System.out.println(file.getPath());
        int index = file.getPath().toString().indexOf("file");

        System.out.println(index);
        System.out.println(file.getPath().toString().substring(index));
    }
}
