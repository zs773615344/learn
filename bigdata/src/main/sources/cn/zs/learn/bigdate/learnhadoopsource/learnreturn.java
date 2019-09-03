package cn.zs.learn.bigdate.learnhadoopsource;

public class learnreturn {
    public static void main(String[] args) {
        System.out.println(deadLoop());
    }

    public static int deadLoop() {
        int i = 1;
        do {
            return i++;
        } while (true);
    }
}
