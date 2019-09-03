package cn.zs.learn.java.observer;

public class Client {

    public static void main(String[] args) {
		
		News news = new News();
		
		Reader r1 = new Reader("张三");
		Reader r2 = new Reader("李四");
		Reader r3 = new Reader("王五");
		
		news.addObserver(r1);
		news.addObserver(r2);
		news.addObserver(r3);
		
		news.setContent("观察者模式");
    }

}
