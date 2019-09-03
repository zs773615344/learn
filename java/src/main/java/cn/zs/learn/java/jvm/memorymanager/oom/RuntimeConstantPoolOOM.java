package cn.zs.learn.java.jvm.memorymanager.oom;

import java.util.ArrayList;
import java.util.List;

/**
 *  VM ARGS: -XX:PermSize=10m -XX:MaxPermSize=10m
 *  上述参数在jdk1.8上无效。
 *  -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -Xmx10m -Xms10m
 *
*/
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		// 使用list保持常量池的引用，避免full Gc回收常量池
		List<String> list = new ArrayList<String>();
		int i = 0;
		while(true) {
		    // String.intern()：如果池中已经包含一个等与此String的字符串，则返回代表池中这个字符串的
            // 添加到常量池中，并且返回此String对象的引用。
            list.add(String.valueOf(i++).intern());
		}
	}
}
