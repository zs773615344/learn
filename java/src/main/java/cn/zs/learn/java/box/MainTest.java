package cn.zs.learn.java.box;

public class MainTest {
    public static void main(String[] args) {

        /**
         *自动装箱拆箱：
         *
         * 1、当基本类型的包装类（Byte、Short、Integer、Long、Double、Float、Boolean）直接以基本类型的值来声明，
         * 如：Integer i = 3，则会自动触发装箱。其实际上是调用该包装类的valueOf方法，如上述示例实际上调用Integer.valueOf(int i)。
         * 此时，需注意
         *
         *  1）Byte、Short、Integer、Long的valueOf方法类似，当值大于等于-128且小于等于127的时候，会从数组缓存中取值，因此
         * 两个相同包装类型的指向内存地址相同，即==为true；大于127的时候就等于直接new了一个新对象。
         *
         *  2）Double、Foalt的valueOf就相当于直接new对象。
         *
         *  3）Boolean的valueOf是一个三元组，返回的是已经声明且定义为static final的Boolean对象，所以不管声明多少个Boolean包装类型
         *  的对象，如果其值都为true/false，其都指向同一个对象。故Boolean包装类的==与boolean基本类型的没有区别。
         *
         * 2、当基本类型（byte、short、int、long、double、float、boolean）使用包装类赋值时，如：Integer inte = 3；int i = int，
         * 则会触发自动拆箱，其实际调用该包装类的intValue/longValue方法。
         *
         * 3、自动装箱封箱对+、-、==、equals的影响：
         *  1）+、-等运算符
         *      当包装类遇到+、-运算符的时候，会将运算符两边的包装类都拆箱进行计算，即当作基本类型的基本运算进行。
         *      因为java禁止重写运算符方法，且包装类型是没有运算符方法。所以碰到包装类与包装类的运算或包装类与基本类型的运算，都将包
         *      装类进行拆箱，将运算式转化为基本类型的运算式（此时遵循基本类型运算原理）。
         *
         *  2）==
         *      ==判断的是对象指向内存的地址，当==两边没有运算符的话，比较的是两个包装类对象的内存地址，此时判断的依据是上述1；
         *      但当==两边有运算符的话，则需要将其两边都拆箱，然后比较数值，即变成基本类型的值的比较。
         *
         *  3）euqals(object obj)
         *      基本类型的包装类都重写了euqals(object obj)方法，都是先判断obj是否是相同类型的,然后再判断值是否相等。
         *      equals其实判断的是两个对象的内存地址，所以括号里面碰到基本类型的话都会进行装箱。如果括号里面碰到的是运算式，则根据上
         *      述3.1）把它们认为是基本类型进行运算，然后再进行基本类型到包装类的装箱，最后比较两个对象。即当括号里面是
         *      两个包装类的运算，则需要先进行拆箱运算，然后再装箱，
         *      最后进行比较。
         *
         * 基本类型的运算实际上就是真实数值的计算，如果两个不同基本类型进行运算，会将其中低类型向上转型。
         *
        */
        Byte by = 1;
        Short sh = 1;
        Integer inte = 3;
        Long lo = 1L;
        Double don = 3.4D;
        Float fl = 3.4f;
        Boolean bool = true;



        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2); //y

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4); //x

        Integer i5 = Integer.valueOf(127);
        Integer i6 = Integer.valueOf(127);
        System.out.println(i5 == i6); //y
        System.out.println(i1 == i5); //y

        Integer i7 = Integer.valueOf(128);
        Integer i8 = Integer.valueOf(128);
        System.out.println(i7 == i8); //x
        System.out.println(i3 == i8); //x

        Integer i9 = new Integer(1);
        Integer i10 = new Integer(1);
        System.out.println(i9 == i10); //x

        System.out.println("#######################################");

        int i11 = i1;
        int i12 = i2;
        System.out.println(i11 == i12); //y

        int i13 = i3;
        int i14 = i4;
        System.out.println(i13 == i14); //y

        int i15 = i1.intValue(); //y
        int i16 = i2.intValue(); //y
        System.out.println(i15 == i16); //y
        System.out.println(i11 == i15); //y

        int i17 = i3.intValue();
        int i18 = i4.intValue();
        System.out.println(i17 == i18); //y
        System.out.println(i17 == i14); //y

        System.out.println("#######################################");


        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer f = 321;
        Integer f1 = 300;
        Integer f2 = 21;
        Long g = 3L;
        Long g1 = 2L;

        //拆箱
        System.out.println(a + b);
        System.out.println(f1 + f2);
        System.out.println(f1 + g);

        System.out.println(c == (a+b)); //y
        System.out.println(g == (a+b));  //y
        System.out.println(c.equals(a+b)); //y
        System.out.println(g.equals(a+b)); //x
        System.out.println(c.equals(g));//x

        System.out.println(c.equals(a +g1)); //x
        System.out.println(g.equals(a + g1)); //y

        Integer f3 = 127;
        Integer f4 = 127;
        Integer f5 = 128;
        Integer f6 = 128;
        Integer f7 = 129;

        System.out.println(f3 == (f5 - a));//y
        System.out.println(f5 == (f3 + a));//y
        System.out.println(f7 == (f5 + a));//y
        System.out.println(f7 == (f4 + b));//y

        long lon = 3L;
        int in = 3;

        System.out.println(lon == in);
        System.out.println(lon + in);
        System.out.println(g/c);
        long x = 321l;
        int y = 321;

        System.out.println(c.equals(in));
        System.out.println(f.equals(y));
        System.out.println(f.equals(x));

        System.out.println(x==y);//y

        System.out.println(f == (f1+f2));//y
        System.out.println(f.equals(f1+f2));//y

    }
}
