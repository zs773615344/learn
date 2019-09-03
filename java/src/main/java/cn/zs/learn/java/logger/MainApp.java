package cn.zs.learn.java.logger;


import cn.zs.learn.java.logger.javaProxy.LogImpl;
import cn.zs.learn.java.logger.javaProxy.LogProxy;
import cn.zs.learn.java.logger.javaProxy.LoggerInterface;

public class MainApp {
    public static void main(String[] args) {
//        NormalLogger normalLog = new NormalLogger();
//        normalLog.doAuditing("zhangsan");
        
//        LoggerProxy loggerProxy = new LoggerProxy(new LoggerImpl());
//        loggerProxy.doAuditing("zhangsan");
        
        LogProxy logProxy = new LogProxy();
        LoggerInterface bind = (LoggerInterface) logProxy.bind(new LogImpl());
        bind.doAuditing("zhangsan");
    }
}
