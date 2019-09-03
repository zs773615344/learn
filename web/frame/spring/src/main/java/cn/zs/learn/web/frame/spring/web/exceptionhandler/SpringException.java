package cn.zs.learn.web.frame.spring.web.exceptionhandler;

public class SpringException extends RuntimeException{
    /**
     * 
     */
    private static final long serialVersionUID = 883066037970549496L;
    private String exceptionMsg;   
    public SpringException(String exceptionMsg) {
       this.exceptionMsg = exceptionMsg;
    }   
    public String getExceptionMsg(){
       return this.exceptionMsg;
    }   
    public void setExceptionMsg(String exceptionMsg) {
       this.exceptionMsg = exceptionMsg;
    }
}
