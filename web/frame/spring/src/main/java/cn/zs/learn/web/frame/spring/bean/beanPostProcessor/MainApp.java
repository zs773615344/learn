package cn.zs.learn.web.frame.spring.bean.beanPostProcessor;

import cn.zs.learn.web.frame.spring.bean.lifecycle.HelloLifeCycyle;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HelloLifeCycyle bean = (HelloLifeCycyle) context.getBean("hellolifecycle");
        bean.getMessage();
        context.registerShutdownHook();
        context.close();
    }
}
