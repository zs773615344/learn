package cn.zs.learn.java.logger.javaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class LogProxy implements InvocationHandler {
    private Logger logger = Logger.getLogger(this.getClass());
    private Object delegate;
            
    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO Auto-generated method stub
        Object result = null;
        try {
            logger.log(Level.INFO, args[0]+"kaishi");
            result = method.invoke(delegate, args);
            logger.log(Level.INFO, args[0]+"结束");
                    
        }catch (Exception e) {
            // TODO: handle exception
            logger.log(Level.INFO, e.toString());
        }
        return result;
    }
    
}
