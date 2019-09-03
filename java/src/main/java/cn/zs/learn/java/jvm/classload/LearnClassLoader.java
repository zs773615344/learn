package cn.zs.learn.java.jvm.classload;

public class LearnClassLoader {

    static int i = 2;
    final static int j = 3;
    static {
        i=1;
        System.out.println(i);
    }

    public LearnClassLoader() {
        System.out.println(i);
        i = 10;
        System.out.println(i);
    }

    public static void main(String[] args) {

        /**
         *
         *  类加载：
         *      1、加载 ---> 2、Linking（验证 ---> 准备 ---> 解析） ---> 3、类初始化 ---> 使用 ---> 卸载
         *      1、找到.class文件并把文件字节流按照虚拟机所需的格式存储到方法区中中，并
         *          在java堆实例化一个java.lang.class类的对象，这个对象作为程序访问方
         *          法区中的数据的外部接口。
         *      2、字节码验证、class类数据结构分析及相应的内存分配和最后的符号表的链接
         *      3、类中静态属性初始化赋值，以及静态块的执行。
         *
         * 加载器（对应1）：
         * Bootstrap ClassLoader:主要加在JVM自身工作需要的类，完全有JVM自身控制，需要加载哪个类、
         *  怎么加载都是由JVM自己控制，别人无法访问到这个类，这个类也不遵守双亲委派模型。它没有父
         *  加载器，也没有子加载器。
         * ExtClassLoader:他是JVM自身的一部分，但并不是JVM亲自实现的。它负责加载${java.ext.dirs}
         *  系统变量所指定的路径的类库。开发者可使用这个加载器。
         * AppClassLoader：它的父类是ExtClassLoader，它负责加载${java.class.path}变量或-cp或
         *  --classpath所指定的路径的类库。如果应用程序中没有定义过自己的类加载器，一般情况下就是
         *  这个程序的默认加载器。如下代码
         *
         * 类初始化（对应3，有且只有以下情况才会触发类的初始化）：
         *      1）遇见new、getstatic、putstatic或invokestatic这4条字节码指令时，如果类没有初始化
         *          过，则需要先触发其初始化。（调用的是自身的静态变量且不是final修饰常量，或自身的静态方法）
         *      2）使用java.lang.reflect包进行反射调用的时候。
         *      3）当初始化一个类的时候，如果发现父类还没进行初始化，则触发弗雷的初始化
         *      4）当虚拟机启动的时候，虚拟机会先初始化主类（运行main()的类）
         *      @see ExampleOne
         *      @see ExampleTwo
         *      @see ExampleThree
        */

        ClassLoader classLoader = LearnClassLoader.class.getClassLoader();
        System.out.println(classLoader);
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        System.out.println(parent.getParent());


    }
}
