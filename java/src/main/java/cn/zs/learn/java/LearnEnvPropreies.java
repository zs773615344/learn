package cn.zs.learn.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class LearnEnvPropreies {
	public static void main(String[] args) throws Exception {
		// 系统环境变量
//		Map<String, String> getenv = System.getenv();
//		for(String env:getenv.keySet()) {
//			System.out.println(env+":\t"+getenv.get(env));
//		}
		
		// 系统的相关属性，java运行时的属性 ，可在运行时加上-Dkey=value添加属性
//		Properties properties = System.getProperties();
//		for(Object key:properties.keySet()) {
//			System.out.println((String)key+":\t"+properties.getProperty((String) key));
//		}
		
		final String GITLAB_PROPERTIES = "gitlab.properties";
		InputStream defaultProperties = LearnEnvPropreies.class
				.getResourceAsStream("/"+GITLAB_PROPERTIES);
		Properties pro = new Properties();
		pro.load(defaultProperties);
		if(! pro.isEmpty()) {
			for(Object key:pro.keySet()) {
				System.out.println(key+":\t"+pro.getProperty((String) key));
			}
		}
		
	}
}
