package cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi.impl;

import java.net.URI;
import java.net.URISyntaxException;

import cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi.AbstractGitlab;
import cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi.Gitlab;
import cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi.GitlabConfiguration;


public class DefaultGitlabImpl extends AbstractGitlab implements Gitlab {
	
	
	private GitlabConfiguration conf;
	private String url;
	
	private DefaultGitlabImpl() {
		this.conf = new GitlabConfiguration();
		this.url = conf.getUrl();
	}
	
	private final static DefaultGitlabImpl defaultGitlabImpl = new DefaultGitlabImpl();
	
	public static DefaultGitlabImpl getInstance() {
		return defaultGitlabImpl;
	}
	
	
	public URI toUri(String str) {
		URI uri = null;
		try {
			 uri = new URI(url+str);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
	}
	
	@Override
	public URI getAdminSessionUri() {
		return toUri("/session?login="+conf.getAdminUsername()+"&password="+conf.getAdminPassword());
	}

		

}
