package cn.zs.learn.web.frame.spring.events;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        context.start();
        HelloWorld object = (HelloWorld) context.getBean("event");
        object.getMessage();
        context.stop();
        context.close();
    }
}
