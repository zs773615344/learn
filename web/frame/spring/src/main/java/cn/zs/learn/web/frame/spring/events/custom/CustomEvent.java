package cn.zs.learn.web.frame.spring.events.custom;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = -5985592798917001082L;

    public CustomEvent(Object source) {
        super(source);
        // TODO Auto-generated constructor stub
    }
    
    public String toString() {
        return "My Custom Event";
    }
    
}
