package cn.zs.learn.web.frame.mybatis3.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;


@Intercepts({@Signature(
	type=Executor.class,
	method="update",
	args= {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation arg0) throws Throwable {
	// TODO Auto-generated method stub
	return arg0.proceed();
    }

    @Override
    public Object plugin(Object arg0) {
	// TODO Auto-generated method stub
	return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties arg0) {
	// TODO Auto-generated method stub

    }

}
