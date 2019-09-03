package cn.zs.learn.java.innerclass;

public class MainTest {
    public static void main(String[] args) {
        External external = new External("A");

        External.Inner inner = external.new Inner();
    }
}

class External {
    private String a;

    public External(String a) {
        this.a = a;
    }

    {
//        External.
        new External.Inner();
    }

    public class Inner {
        public Inner() {
            System.out.println(External.this.a);
        }
    }

    public Inner get() {
        return new Inner();
    }
}
