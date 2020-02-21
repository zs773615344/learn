package cn.zs.learn.java.logger.interfaceLogger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerProxy {
    private Logger logger = Logger.getLogger(this.getClass());
    private LoggerInterface loggerInterface;
    
    public LoggerProxy(LoggerInterface loggerInterface) {
        this.setLoggerInterface(loggerInterface);
    }
    
    public void doAuditing(String name) {
        logger.log(Level.DEBUG, name+"开始审核数据。。。。。。。");
        System.out.println("hahahhahah");
        logger.log(Level.INFO, name+"审核数据结束。。。。。。。");
    }

	public LoggerInterface getLoggerInterface() {
		return loggerInterface;
	}

	public void setLoggerInterface(LoggerInterface loggerInterface) {
		this.loggerInterface = loggerInterface;
	}
}
