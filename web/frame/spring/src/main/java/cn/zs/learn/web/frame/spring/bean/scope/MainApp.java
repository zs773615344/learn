package cn.zs.learn.web.frame.spring.bean.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HelloScope beanA=(HelloScope)context.getBean("helloscope");
        beanA.setMessage("I am  object A");
        beanA.getMessage();
        HelloScope beanB=(HelloScope)context.getBean("helloscope");
        beanB.setMessage("I am object B");
        beanB.getMessage();
        beanA.getMessage();
        ((AbstractApplicationContext) context).close();
    }
    
}
