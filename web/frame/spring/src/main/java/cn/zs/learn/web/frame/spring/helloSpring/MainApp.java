package cn.zs.learn.web.frame.spring.helloSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
//        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
//        HelloWorld bean = (HelloWorld) xmlBeanFactory.getBean("helloworld");
//        bean.getMessage();
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HelloWorld bean = (HelloWorld) context.getBean("helloworld");
        bean.getMessage();
        ((AbstractApplicationContext) context).close();
    }
    
}
