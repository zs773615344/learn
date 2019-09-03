package cn.zs.learn.web.frame.spring.annotation.autowired.property;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // TODO Auto-generated constructor stub
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();
        context.close();
    }
}
