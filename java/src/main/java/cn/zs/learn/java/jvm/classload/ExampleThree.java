package cn.zs.learn.java.jvm.classload;

public class ExampleThree {
    /**
     * 常量在编译阶段会存入调用类的常量池中，本质上没有直接使用引用到定义
     * 常量的类，因此不会触发定义常量的类的初始化。final修饰的方法则会触发。
     *
    */
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
        ConstClass.constMethod();
    }
}
class ConstClass {
    static {
        System.out.println("constclass init");
    }

    public final static String HELLOWORLD = "helloworld";

    public final static void constMethod() {
        System.out.println("constMethod");
    }
}