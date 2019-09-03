package cn.zs.learn.java.extendsimplement;

import java.lang.reflect.Field;

public class son extends parent {
	String field1 = "haha";
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
//		new son().print();
//		System.out.println(new son().field1);
//		System.out.println(new son().field2);

//		new son().println();
//		parent s = new son();
//		s.print();
//        System.out.println(s.field1);
        Class<?> aClass = Class.forName("learn.extendsimplement.son");
        Field[] fields = aClass.getFields();
        System.out.println(fields.length);
        for (Field field:fields) {
            System.out.println(field.getName());
        }
    }
	@Override
	void println() {
		// TODO Auto-generated method stub
		System.out.println(field1);
	}
    void println(String p) {
        // TODO Auto-generated method stub
        System.out.println(p);
    }
}
