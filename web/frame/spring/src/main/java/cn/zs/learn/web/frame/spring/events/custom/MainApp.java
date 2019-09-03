package cn.zs.learn.web.frame.spring.events.custom;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
   public static void main(String[] args) {
       ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
       CustomEventPublisher cep = (CustomEventPublisher) context.getBean("customEventPublisher");
       cep.publish();
       cep.publish();
       context.close();
}
    
}
