package cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi.bean.GitlabCreateUserBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;


import com.google.gson.Gson;


public abstract class AbstractGitlab implements Gitlab {
	private final HttpClient httpClient = new DefaultHttpClient();
	private HttpGet httpGet = new HttpGet();
	private HttpPost httpPost = new HttpPost();
	private HttpPut httpPut =  new HttpPut();
	private HttpDelete httpDelete = new HttpDelete();

	public String listUsers(String privateToken) {
		httpGet.setURI(toUri("/users"));
		httpGet.setHeader("PRIVATE-TOKEN",privateToken );
		return execHttpMethod(httpGet);
	}

	public String searchUserByUsernameOrEmail(String privateToken, String usernameOrEmail) {
		httpGet.setHeader("PRIVATE-TOKEN",privateToken );
		httpGet.setURI(toUri("/users?search="+usernameOrEmail));
		return execHttpMethod(httpGet);
	}


	public String searchUserByUsername(String privateToken, String username) {
		httpGet.setHeader("PRIVATE-TOKEN",privateToken );
		httpGet.setURI(toUri("/users?username="+username));
		return execHttpMethod(httpGet);
	}

	public String searchUserById(String privateToken, int id) {
		httpGet.setHeader("PRIVATE-TOKEN",privateToken );
		httpGet.setURI(toUri("/users/"+id));
		return execHttpMethod(httpGet);
	}

	public String createUser(GitlabCreateUserBean bean) {
		httpPost.setHeader("PRIVATE-TOKEN",getRootToken() );
		httpPost.setURI(toUri("/users?email="+bean.getEmail()+"&username="+bean.getUsername()+"&name="+bean.getName()+"&password="+bean.getPassword()));
		return execHttpMethod(httpPost);
	}


	public String updateUser(int id,GitlabCreateUserBean bean) {
		httpPut.setHeader("PRIVATE-TOKEN",getRootToken() );
		
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotBlank(bean.getEmail()))
			list.add("email="+bean.getEmail());
		if(StringUtils.isNotBlank(bean.getName()))
			list.add("name="+bean.getName());
		if(StringUtils.isNotBlank(bean.getUsername()))
			list.add("username="+bean.getUsername());
		if(StringUtils.isNotBlank(bean.getPassword()))
			list.add("password="+bean.getPassword());
		if(list.size() == 0) {
			return "不能为空";
		}
		
		httpPut.setURI(toUri("/users/"+id+"?"+StringUtils.join(list, "&")));
		return execHttpMethod(httpPut);
	}

	public String deleteUser(int id) {
		httpDelete.setHeader("PRIVATE-TOKEN",getRootToken());
		httpDelete.setURI(toUri("/users/"+id));
		return execHttpMethod(httpDelete);
	}
	

	public String listProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listOwnerProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listStarttedProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}


	public String searchProjectByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProjectevents(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public String CreateProjectsForUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract URI toUri(String str);
	public abstract URI getAdminSessionUri();
	
	private String getRootToken() {
		httpPost.setURI(getAdminSessionUri());
		return (String)new Gson().fromJson(execHttpMethod(httpPost), Map.class).get("private_token");
	}
	
	public String execHttpMethod(HttpRequestBase method) {
		StringBuffer sb = new StringBuffer(); 
		try {
			BufferedReader content = new BufferedReader( new InputStreamReader(
					httpClient.execute(method).getEntity().getContent()));
			String readLine = null;
			while((readLine=content.readLine()) != null) {
				sb.append(readLine);
			}
			
			content.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
}
