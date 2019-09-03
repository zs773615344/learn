package cn.zs.learn.java.param;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    /**
     * java中形参与实参的区别：
     *  实参、形参是程序语言设计中的通用概念。
     *  形参就是形式上的参数，实参就是实际的参数。形参只是对实参的一种抽象类型描述，只是声明一个函数（方法）能接受什么类型的实参，而不确
     * 定接受实参具体内容是多少；实参就是传递给函数（方法）对应形参的具体内容，形参的初始值(内容)由实参决定，形参在函数（方法）结束返回
     * 后就被释放了。
     *
     * 参数传递方式：传值和传址
     *  1、传值方式：只是将实参的值的拷贝传递给方法，在方法内对形参进行操作，其对象是实参的拷贝，对实参不能造成影响，在方法结束返回后，
     *  形参被释放丢弃，实参的内容并不会被改变。
     *  2、传址方式：将实参的地址传递给方法，在方法内对形参进行操作等于对实参进行相同的操作，在方法结束返回后，形参同样被释放，实参的内
     *  容将会是对形参进行操作的结果。
     *
     *  java中传参其实是传递对象的引用地址，形参拷贝该传递对象的引用地址，当方法执行结束返回时就会释放掉该形参。对形参的任何操作并不会
     *  改变实参的引用地址。对于基本类型与String来说，它们的引用地址实际上就可以说是它们的值，因此对这些类型的形参的操作，不会影响实参的
     *  值，这种就是传值方式。但是类似集合数组如List这种类型的形参来说，如果对形参的内容进行操作，虽然没有改变实参的引用地址，但是改变了
     *  引用地址指向的内容的值，这种就是传址方式，此时对形参内容的操作实际上就是对实参的操作。
     *
    */
    public static void main(String[] args) {
        String str = "COUNTRY";
		str = "hehe";
		getNewStr(str, 2);
		System.out.println(str);
		List<String> list = new ArrayList<String>();
		list.add("1");
		getNewList(list);
		System.out.println(list.size());
    }
    	public static void getNewStr(String str,int num) {
		if(num == 1)
			str = "ENGLISH";
		else if (num == 2)
			str = "england";
		else
			str = "china";
	}
	public static void getNewList(List<String> list) {
		list.add("2");
	}
}
