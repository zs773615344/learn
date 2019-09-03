package cn.zs.learn.bigdate.learnhadoopsource;

public class StringTest {

    public static void main(String[] args) {
        String a = "ab";
        String b = new String("ab");
        String c = "c";
        String d = a + c;
        String e = "ab" + "c";
        String f = "abc";
        String g = new String("abcd");
        System.out.println(d == e);
        System.out.println(d == f);
        System.out.println(e == f);

    }
}
