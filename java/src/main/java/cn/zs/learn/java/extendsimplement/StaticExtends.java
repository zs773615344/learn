package cn.zs.learn.java.extendsimplement;

public class StaticExtends {
    public static void main(String[] args) {
        new B();
    }
}

class A {
    static String a = "A static";
    static {
        System.out.println("a static");
    }
    {
        System.out.println("a normal codeblock");
    }
}
class B extends A {
    static {
        System.out.println("b static ");
    }
    {
        System.out.println("b normal codeblock");
    }

}
