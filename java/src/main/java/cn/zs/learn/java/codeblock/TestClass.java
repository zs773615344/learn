package cn.zs.learn.java.codeblock;

public class TestClass {
	
//	static {
//		System.out.println("first");
//	}
	
	private int m;
	public int inc() {
		return m+1;
	}
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
//		byte[] str = {0x6c,0x65,0x61,0x72,0x6e,0x2f,0x54,0x65,0x73,0x74,0x43,0x6c,0x61,0x73,0x73};
//		for(byte b:str)
//			System.out.print((char)b);
//		new TestInit();
//		System.out.println("*************");
//		TestInit.getStatic();
//		System.out.println(TestInit.value);
//		new TestInitSon();
//		System.out.println(TestInitSon.value);
		
//		Class<?> ti = Class.forName("learn.TestInit");
		
//		TestInitSon.getStatic();
		System.out.println(TestInit.value1);
		
	}
	
}

class TestInit{
	public static int value = 3;
	public final static int value1 = 1;
	public final static String str = "strs";
	public final static int[] arr = {1,2,3};
	public int value2 = 2;
	static {
		System.out.println("静态代码块");
		System.out.println("value="+value);
		System.out.println("value1="+value1);
	}
	public TestInit() {
		System.out.println("构造函数");
		System.out.println("value="+value);
		System.out.println("value1="+value1);
		System.out.println("value2="+value2);
	}
	public static void getStatic(){
		System.out.println("静态方法");
	}
}

class TestInitSon extends TestInit {
	
	static {
		System.out.println("son 的静态代码快");
	}
	public TestInitSon() {
		System.out.println("son的构造函数");
	}
	
}

