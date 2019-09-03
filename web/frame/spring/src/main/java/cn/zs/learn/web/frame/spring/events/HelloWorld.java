package cn.zs.learn.web.frame.spring.events;

public class HelloWorld {
    private String message;

    public void getMessage() {
        System.out.println("your message:"+message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
