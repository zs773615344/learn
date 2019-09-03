package cn.zs.learn.web.frame.spring.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {
    
    @Override
    public void onApplicationEvent(ContextStoppedEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println("ContextStoppedEvent Received");
    }
    
}
