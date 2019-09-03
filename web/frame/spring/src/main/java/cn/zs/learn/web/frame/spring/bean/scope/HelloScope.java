package cn.zs.learn.web.frame.spring.bean.scope;

public class HelloScope {
    private String message;

    public void getMessage() {
//        return message;
        System.out.println("your message : "+message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
