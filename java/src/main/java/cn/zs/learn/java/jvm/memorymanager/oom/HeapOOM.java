package cn.zs.learn.java.jvm.memorymanager.oom;

import java.util.ArrayList;
import java.util.List;
/**
 * VM ARGS：-Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
*/
public class HeapOOM {
	static class Oom{}
	public static void main(String[] args) {
		List<Oom> list = new ArrayList<Oom>();
		while (true) {
			list.add(new Oom());
		}
	}
}