package cn.zs.learn.java.socket;

import java.net.InetAddress;


public class LearnInetAddress {

    public static void main(String[] args) throws Exception {
		/*
		 *InetAddress类是java包装用来表示IP地址的高级表示。
		 * 构造函数修饰为缺省，不可直接实例化，可通过调用其静态方法返回其实例
		 */
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost);
		System.out.println(InetAddress.getByName("localhost"));
		System.out.println(InetAddress.getLoopbackAddress());

		for(InetAddress ip:InetAddress.getAllByName("localhost")) {
	   	 	System.out.println(ip);
		}
		System.out.println(localHost.toString());

		System.out.println(InetAddress.getByName(null));
    }

}
