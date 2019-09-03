package cn.zs.learn.java.logger.javaProxy;

public class LogImpl implements LoggerInterface{

    @Override
    public void doAuditing(String name) {
        // TODO Auto-generated method stub
        System.out.println(name);
    }
    
}
