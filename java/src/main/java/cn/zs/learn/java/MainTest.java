package cn.zs.learn.java;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Stack;


public class MainTest extends Thread{

	private static final int key1 = 1;
	public static int key2 = 2;
	public final int key3 = 3;
	private static final String key11 = "1";
	public static String key22 = "2";
	public final String key33 = "3";
	private static final Integer key111 = 11;
	public static Integer key222 = 22;
	public final Integer key333 = 33;
	public static void main(String[] args) throws Exception {
//	    System.out.println(System.getProperties());
//	    System.out.println(System.getProperty("java.ext.dirs"));
//	    System.out.println(System.getProperty("java.class.path"));

//	    ClassLoader classLoader = MainTest.class.getClassLoader();
//	    while (classLoader != null) {
//			System.out.println(classLoader);
//			classLoader = classLoader.getParent();
//	    }
//
//	    Stack stack = new Stack();
//	    stack.add("1");
//	    stack.add("2");
//	    stack.add("3");
//	    System.out.println(stack.pop());



//	    Parent son = new Son();
//	    son.age();
//	    ((Son)son).name();

		//构造器调用顺序，默认调用父类的无参构造器，若父类没有无参构造器，则子类需显示调用父类的构造器
//	    new A();
//	    new C(10);


//	    List<Integer> asList = Arrays.asList(1,2,3,4,5);

//	    System.out.println("3,2,1!ni men hao ! wo shi zhang shuai".matches("^\\d,.*[iI]$"));


//		Stack stack = new Stack();
//		stack.push(1);
//		stack.push(2);
//		stack.push(3);
//		System.out.println(stack);
//		Object pop = stack.pop();
//		System.out.println(pop);
//		System.out.println(stack);
//		Object peek = stack.peek();
//		System.out.println(peek);
//		System.out.println(stack);
//		ArrayList<Integer> list1 = new  ArrayList<Integer>();
//		list1.add(1);
//		list1.add(2);
//		list1.add(5);
//		list1.add(4);
//		LinkedList<Integer> list2 = new  LinkedList<Integer>();
//		list2.add(1);
//		list2.add(2);
//		list2.add(5);
//		list2.add(4);
//		System.out.println(list1);
//		System.out.println(list2);
//		System.out.println(list1.get(3));
//		System.out.println(list2.get(0));
//		System.out.println(new Random());
//		int[][] in = new int[2][3];
//		int[] in2 = new int[10];
//	   int a =  returnTest();
//	   System.out.println("a="+ a);
//		int[] arr = {1,2,3,4,5};
//		int i = 1;
//		System.out.println(arr[i]);
//		System.out.println(arr[i++]);
//		System.out.println(arr[i]);

//		Map<String, String> map = System.getenv();
//		for(String key:map.keySet()) {
//			System.out.println(key+" = "+ map.get(key));
//		}
//		Properties properties = System.getProperties();
//		for(Object key:properties.keySet()) {
//			System.out.println((String)key+":\t"+properties.getProperty((String) key));
//		}
		for (;;) {
			////			System.out.println("haha");
			System.out.print("");
		}

//		System.out.println(returnTest());
//		System.out.println(Parent.key1);
//		Class a  = MainTest.class.getClassLoader().loadClass("learn.A");
//		a.newInstance();
//		a.newInstance();

	}



	public static int returnTest() {
		int i;
		try {
			System.out.println(Parent.key1);
			System.out.println(3);
			i = 0;
//			return 6/0;
			return 0;
		} catch(ArithmeticException e) {
			System.out.println(1);
			return 1;
		} finally {
			System.out.println(2);
//			return 2;
		}
	}

}

class A{
	static {
		System.out.println("hahaha");
	}
	A(){System.out.println("A+");}
	A(int a){System.out.println("a");}
}
class B extends A {
	public B() {System.out.println("B");}
	public B(int age) {System.out.println("B"+age);}

}
class C extends B {
	public C(int age) {System.out.println("C"+age);}

}


interface Parent{
	static final String key1 = "hehe";
	public void age();
}
class Son implements Parent{

	public void age() {
		// TODO Auto-generated method stub
		System.out.println("18");
	}

	public void name() {
		System.out.println("son");
	}

}