package cn.zs.learn.web.frame.spring.bean.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class InitHelloLifeCycle implements BeanPostProcessor{

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("AfterInitialization : "+arg1);
        
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("BeforeInitialization : "+arg1);
        
        return arg0;
    }
    
}
