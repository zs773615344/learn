package cn.zs.learn.java.extendsimplement;

public class Construction {
    public static void main(String[] args) {
        new Son();

    }
}
class Parent{
    String parentField;
    static String parentStaticField;

//    public Parent(){
//        System.out.println("parent no param");
//    }

    public Parent(String param) {
        System.out.println("parent param : " + param);
    }
    public void  get() {
        System.out.println("");
    }
}
class Son extends Parent {
    private String sonField;
    private static String sonStaticField = "haha";
    public Son() {
        super("haha");
        System.out.println("son no param");
    }
    public Son(String param) {
        this();
        System.out.println("son have param:" + param);

    }
}