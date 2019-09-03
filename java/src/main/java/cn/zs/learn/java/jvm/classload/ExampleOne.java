package cn.zs.learn.java.jvm.classload;

/**
 * 引用父类的静态字段，不会导致子类的初始化
 * 运行时加上参数-XX:+TraceClassLoading
 *
*/

public class ExampleOne {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
        SuperClass.superMethod();
    }
}
class SuperClass{
    static int value = 123;
    static {
        System.out.println("superclass init");
    }
    static void superMethod() {
        System.out.println("super method");
    }
}
class SubClass extends SuperClass {
    static {
        System.out.println("subclass init");
    }
}