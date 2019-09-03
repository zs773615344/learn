package cn.zs.learn.web.frame.spring.bean.lifecycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HelloLifeCycyle beanA=(HelloLifeCycyle)context.getBean("hellolifecycle");
        beanA.setMessage("I am  object A");
        beanA.getMessage();
        context.registerShutdownHook();
        context.close();
    }
    
}
