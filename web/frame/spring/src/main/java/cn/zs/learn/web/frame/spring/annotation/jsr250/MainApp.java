package cn.zs.learn.web.frame.spring.annotation.jsr250;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HelloWorld helloWorld = (HelloWorld) context.getBean("jsr250");
        helloWorld.getMessage();
        context.registerShutdownHook();
        context.close();
    }
    
}
