package cn.zs.learn.java.datastructure;

import java.util.*;

public class DataStructure {
	static  {
		/**
         *
         * @see java.util.Collection
         * @see java.util.List
         * @see java.util.Set
		 * 集合分为有序集合list和无序集合set
		 * 有序集合list可重复,可存储空值.
		 * 无序集合Set不可重复，可存储空值。
		 *
         * @see java.util.ArrayList
		 * ArrayList是由数组实现的
         * @see java.util.LinkedList
		 * LinkedList是由双向链表实现的 ？
         * @see java.util.Vector
		 * Vector是继承的是AbsractList，类似是ArrayList，也是由数组实现的，但是线程安全的
         * @see java.util.Stack
		 * Stack直接继承的是Vector，在Vector的基础上实现先进先出的逻辑。
		 *
         * @see java.util.HashSet
         * @see java.util.TreeSet
		 * HashSet和TreeSet的实现和HashMap和TreeMap紧密联系在一起，java中的HashSet和TreeSet的实现
		 * 其实就是HashMap和HashTable的实现，HashSet和TreeSet实例时，就是创建了一个HashMap和TreeMap，
		 * 然后调用HashMap和TreeMap的方法实现自己的逻辑，HashMap和TreeMap是关于keyValue的映射，HashSet
		 * 和TreeSet则是keyPRESENT(PRESENT=new Obejct(),PRESENT不变)的映射。
		 *
         * @see java.util.Map
		 * 映射：HashMap和Hashtable
		 *
         * @see java.util.HashMap
         * HashMap的实现是由数组和单链表实现的，根据key的hash值将entry存储为数组相对应下标元素，entry其实就
         * 是个单链表。如果key的hash值相同且key值也相同的话，则修改key对应value为新插入的值。如果key的hash值
         * 相同而key值不同的话，则将entry存储在对应hash的单链表中。
         * 上述是比较简单的实现，java会设定链表长度的阀值，如果超过阀值，则将单链表转化为二叉树。
         *
         * @see java.util.Hashtable
         * Hashtable与HashMap的实现类似，但Hashtable不会将链表转化为二叉树。
         * 1、Hashtable的每个操作都是synchronized修饰的，是线程安全的。相对应的HashMap不是线程安全的
         * 2、Hashtable的key和value都不能为null，是因为Hashtable直接调用key.hashCode()方法求hash值，key为空会
         * 报空值异常，且put()首先会检查value是否为空。相对应的HashMap的key和value都可以为空。
         *
         * @see java.util.LinkedHashMap
         * LinkedHashMap:继承HashMap，HashMap+双向链表，双链表继承HashMap的单链表 ????
         *
         * @see java.util.TreeMap
         * TreeMap是使用红黑树实现的.???? 暂时没有涉猎
         *
         * @see java.lang.String
         * @see java.lang.StringBuffer
         * @see java.lang.StringBuilder
         * 字符串:String、StringBuffer、StringBuild
         * String:final类,不可继承.一旦实例,其指向的字符串不可修改.是由容量不可变的char[]实现的.
         * StringBuffer：final类,不可继承.默认容量+16的数组，自动扩容。线程安全
         * StringBuilder：final类,不可继承。默认容量+16的数组，自动扩容。线程不安全
         *
         * StringBuild和StringBuffer指向的字符都是可追加的。但由于StringBuffer是线程安全的，速度较慢。
         *
         * 字符串匹配见：
         *  @see MyString#indexOf(java.lang.String, int)
         *  @see MyString#indexOf2(java.lang.String, int)
         *  @see MyString#indexOf3(java.lang.String, java.lang.String, int)
		 *
         *
		*/


	}

	public static void main(String[] args) {
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		map.put(1,1);
		map.put(1,2);
		System.out.println(map);

		int[] arr1 = {1,2,3};
		int[] arr2 = arr1;
		arr1[1] = 4;
		for (int i = 0; i < arr1.length; i++)
			System.out.println(arr1[i] + " " + arr2[i]);


		int[] arr = {2,4,6,7,9,-2,8,3,1,5,12,11};
		ArrayList<Integer> list = new ArrayList<Integer>();
		Set set = new HashSet<Integer>();
		list.add(7);
		list.add(2);
		list.add(1);
		list.add(4);
		list.add(6);
		list.add(3);

		System.out.println((int)Math.sqrt(5));
		int[][] douArr = new int[10][];


	}

}
