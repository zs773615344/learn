package cn.zs.learn.web.frame.spring.annotation.jsr250;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class HelloWorld {
    private String message;

    public String getMessage() {
        System.out.println("your message: "+message);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @PostConstruct
    public void init() {
        System.out.println("bean is going through init");
    }
    @PreDestroy
    public void destory() {
        System.out.println("bean will destory");
    }
}
