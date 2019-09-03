package cn.zs.learn.java.jvm.classload;

public class ExampleTwo {
    /**
     * 通过数组定义来引用类，不会触发类的初始化
    */
    public static void main(String[] args) {
        SuperClass[] superClasses = new SuperClass[10];
    }
}