package cn.zs.learn.java.jvm.memorymanager.oom;


/**
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDeatils
 * 
*/
public class GCPrintTest {
	private static final int _1MB = 1024 * 1024;
	public static void testAllocation() {
		byte[] allocation1,allocation2,allocation3,allocation4;
		allocation1 = new byte[4 * _1MB];
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
		testAllocation();
	}
}
