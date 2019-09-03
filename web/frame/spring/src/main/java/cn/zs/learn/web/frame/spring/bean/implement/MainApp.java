package cn.zs.learn.web.frame.spring.bean.implement;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Parent parent = (Parent) context.getBean("parent");
        parent.getMessage1();
        parent.getMessage2();
        Son son1 = (Son) context.getBean("son");
        son1.getMessage1();
        son1.getMessage2();
        son1.getMessage3();
        System.out.println("##############################");
        Son son2 = (Son) context.getBean("templetImpl");
        son2.getMessage1();
        son2.getMessage2();
        son2.getMessage3();
        context.close();
    }
    
}
