package cn.zs.learn.web.frame.spring.annotation.qualifier;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Profile profile = (Profile) context.getBean("profile");
        profile.printName();
        profile.printAge();
        context.close();
    }
    
}
