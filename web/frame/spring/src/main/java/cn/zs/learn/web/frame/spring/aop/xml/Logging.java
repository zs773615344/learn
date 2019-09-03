package cn.zs.learn.web.frame.spring.aop.xml;

public class Logging {
    
    public void beforeAdvice() {
        System.out.println("Going to setup student profile");
    }
    
    public void afterAdvice() {
        System.out.println("Student profile has been setup");
    }
    
    public void afterReturnAdvice(Object retval) {
        System.out.println("return: "+retval.toString());
    }
    
    public void afterThrowWingAdvie(IllegalArgumentException ex) {
        System.out.println("There has bean an exception: "+ex.toString());
    }
}
