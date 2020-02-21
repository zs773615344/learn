package cn.zs.learn.java.extendsimplement;

public abstract class AnothorParent {
    String field1 ;
	private String field2 = "xixi";
	
	public void print() {
		println();
		println(field1);
        println(this.field1);
		println(field2);
		System.out.println(field1);
		System.out.println(this.getClass().getName());
	}
	
	abstract void println();
	 void println(String a) {
		 System.out.println(a);
	 }
}
