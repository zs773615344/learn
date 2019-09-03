public class MainTest {
    public static void main(String[] args) {
        A b = new B();
        b.eat();
        A c = new C();
        c.eat();
//        (c).drink();
        
    }
}
abstract class A{
    public abstract  void eat();
}
class B extends A{

    @Override
    public void eat() {
        // TODO Auto-generated method stub
        System.out.println("B eat");
    }
    
}
class C extends A{

    @Override
    public void eat() {
        // TODO Auto-generated method stub
        System.out.println("C eat");
    }
    
    public void drink() {
        System.out.println("c drink");
    }
    
}