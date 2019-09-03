package cn.zs.learn.java.codeblock;

import java.util.HashMap;
import java.util.Map;

public class CodeBlock {

    public static Map<String, String> map = new HashMap<String, String>();

    static {
        System.out.println("static code block");
        for (String s : map.keySet())
            System.out.println(s);
    }

    public CodeBlock() {
        map.put("key", "value");
        System.out.println("construct method");
    }

    {
        System.out.println("normal code block");
    }

    public static void print() {
        System.out.println("static method");
    }

    public static void main(String[] args) {
        CodeBlock.print();

        new CodeBlock();
        new CodeBlock();
    }
}
