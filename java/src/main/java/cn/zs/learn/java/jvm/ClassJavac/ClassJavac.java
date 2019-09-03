package cn.zs.learn.java.jvm.ClassJavac;


import com.sun.tools.javac.main.Main;

public class ClassJavac {
	public static void main(String[] args) {
        com.sun.tools.javac.main.Main compiler = new com.sun.tools.javac.main.Main("javac");
        String[] arr = {"/home/shuai/hello.java"};
        Main.Result result = compiler.compile(arr);
        System.out.println(result.isOK());
    }
}
