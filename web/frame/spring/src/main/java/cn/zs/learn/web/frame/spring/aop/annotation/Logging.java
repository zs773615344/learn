package cn.zs.learn.web.frame.spring.aop.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {
    
    @Pointcut("execution(* cn.zhang.spring.aop.annotation.*.*(..))")
    private void select() {}

    @Before("select()")
    public void beforeAdvice() {
        System.out.println("Going to setup student profile");
    }
  
    @After("select()")
    public void afterAdvice() {
        System.out.println("Student profile has been setup");
    }
    
    @AfterReturning(pointcut="select()",returning="retval")
    public void afterReturnAdvice(Object retval) {
        if(retval!=null)
            System.out.println("return: "+retval.toString());
    }
    
    @AfterThrowing(pointcut="select()",throwing="ex")
    public void afterThrowWingAdvie(IllegalArgumentException ex) {
        System.out.println("There has bean an exception: "+ex.toString());
    }
}
