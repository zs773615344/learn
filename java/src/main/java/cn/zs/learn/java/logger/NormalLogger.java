package cn.zs.learn.java.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class NormalLogger {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    public void doAuditing(String name) {
        logger.log(Level.DEBUG, name+"开始审核数据。。。。。。。");
        System.out.println("hahahhahah");
        logger.log(Level.INFO, name+"审核数据结束。。。。。。。");
    }
}
