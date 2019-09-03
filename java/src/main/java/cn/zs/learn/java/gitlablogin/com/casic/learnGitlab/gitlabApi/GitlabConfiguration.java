package cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi;

import java.io.IOException;
import java.util.Properties;

public class GitlabConfiguration {
	private static final String DEFAULT_PROIPERTIES = "gitlab.properties"; 
//	private static final String DEFAULT_PATH = ""
	private final Boolean ssl ;
	private final String gitlabHost;
	private final String apiVersion;
	private final String adminUsername;
	private final String adminPassword;
	private final String url;
		
	public GitlabConfiguration() {
		Properties p = loadDefaultProperties();
		
		ssl = Boolean.valueOf(p.getProperty("ssl"));
		gitlabHost = p.getProperty("gitlab_host");
		apiVersion = p.getProperty("api_version");
		adminUsername = p.getProperty("admin_username");
		adminPassword = p.getProperty("admin_password");
		if(ssl) {
			url = "https://"+gitlabHost+"/api/"+apiVersion;
		}else {
			url = "http://"+gitlabHost+"/api/"+apiVersion;
		}
	}
	
	
	
	
//	public GitlabConfiguration(Properties pro) {
//		
//		ssl = Boolean.valueOf((String) pro.getOrDefault("ssl", p.getProperty("ssl")));
//		gitlabHost = (String) pro.getOrDefault("gitlab_host", p.getProperty("gitlab_host"));
//		apiVersion = (String) pro.getOrDefault("api_version", p.getProperty("api_version"));
//		adminUsername = (String) pro.getOrDefault("admin_username", p.getProperty("admin_username"));
//		adminPassword = (String) pro.getOrDefault("admin_password", p.getProperty("admin_password"));
//		
//		if(ssl) {
//			url="https://"+gitlabHost+"/api/"+apiVersion;
//		}else {
//			url="http://"+gitlabHost+"/api/"+apiVersion;
//		}
//	}
	
	private static Properties loadDefaultProperties() {
//		Map<String, String> getenv = System.getenv();
		
		
//		GitlabConfiguration.class.getResource("/").getPath()
		
		Properties p = new Properties();
		try {
			p.load(GitlabConfiguration.class.getResourceAsStream("/"+DEFAULT_PROIPERTIES));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	} 
	
	public Boolean getSsl() {
		return ssl;
	}

	public String getGitlabHost() {
		return gitlabHost;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public String getUrl() {
		return url;
	}
	
}
