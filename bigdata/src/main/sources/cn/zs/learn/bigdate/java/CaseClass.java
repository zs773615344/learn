package cn.zs.learn.bigdate.java;

public class CaseClass {
    public static int get(int i) {
        int result = 0;
        switch (i) {
            case 1: result = 1;
            case 2: result = result + i * 2;
            case 3: result = result + i * 2;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(get(2));
    }
    interface c{
       static double get(){
           return 1.2;
       }
       default double getD(){
           return 1.2;
       }
    }
}
