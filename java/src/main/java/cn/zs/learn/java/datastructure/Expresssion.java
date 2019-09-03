package cn.zs.learn.java.datastructure;

import java.util.Stack;

public class Expresssion {
    // 将中缀表达式转换为后缀表达式
    public static String toPostfix(String expstr) {
        Stack<String> stack = new Stack<String>();
        String postfix = "";
        int i = 0;
        while (i < expstr.length()) {
            char ch = expstr.charAt(i);
            switch (ch) {
                case '+' :
                case '_' : while (!stack.isEmpty() && !stack.peek().equals("("))
                                postfix += stack.pop();
                            stack.push(ch + "");
                            i ++;
                            break;
                case '*' :
                case '/' : while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
                                postfix += stack.pop();
                            stack.push(ch + "");
                            i ++;
                            break;
                case '(' : stack.push(ch + "");
                            i ++;
                            break;
                case ')' : String out = stack.pop();
                            while (out != null && !out.equals("(")) {
                                postfix += out;
                                out = stack.pop();
                            }
                            i ++;
                            break;
                default : while (i < expstr.length() && ch >= '0' && ch <= '9') {
                                postfix += ch;
                                i ++;
                                if (i < expstr.length())
                                    ch = expstr.charAt(i);
                            }
                            postfix += " ";

            }
        }

        while (!stack.isEmpty())
            postfix += stack.pop();

        return postfix;
    }
    // 计算后缀表达式
    public static int compute(String postfix) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0,result = 0;
        while (i < postfix.length()) {
            char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9') {
                result = 0;
                while (ch != ' ') {
                    result = result * 10 + Integer.parseInt(ch + "");
                    i ++;
                    ch = postfix.charAt(i);
                }
                i ++;
                stack.push(new Integer(result));
            } else {
                int y = stack.pop().intValue();
                int x = stack.pop().intValue();
                switch (ch) {
                    case '+' : result = x + y;
                                break;
                    case '-' : result = x - y;
                                break;
                    case '*' : result = x * y;
                                break;
                    case '/' : result = x / y;
                                break;
                }
                stack.push(new Integer(result));
                i ++;
            }

        }
        return stack.pop().intValue();
    }

    public static void main(String[] args) {
        String expstr = "(12+5*6*2)/((2+1)*(1+1+1))";
        String postfix = toPostfix(expstr);
        System.out.println(postfix);
        int result = compute(postfix);
        System.out.println(result);
    }
}
