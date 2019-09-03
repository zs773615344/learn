package cn.zs.learn.web.frame.spring.aop.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("aspect.xml");
        Student student = (Student) context.getBean("student");
        student.setAge(18);
        student.getName();
        student.getAge();
        student.printThrowException();
    }
}
