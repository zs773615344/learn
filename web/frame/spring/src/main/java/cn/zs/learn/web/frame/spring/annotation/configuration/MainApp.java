package cn.zs.learn.web.frame.spring.annotation.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        @SuppressWarnings("resource")
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.setMessage("Hello World");
        helloWorld.getMessage();
        
    }
    
}
