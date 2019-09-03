package cn.zs.learn.web.frame.spring.annotation.request;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println("name: "+student.getName());
        System.out.println("age: "+student.getAge());
        
        
        context.close();
    }
    
}
