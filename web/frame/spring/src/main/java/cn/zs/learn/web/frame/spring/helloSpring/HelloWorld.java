package cn.zs.learn.web.frame.spring.helloSpring;

public class HelloWorld {
    private String message;

    public void getMessage() {
        System.out.println("your messgae:"+message);
//        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
