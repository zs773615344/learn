package cn.zs.learn.java.gitlablogin;

import java.io.IOException;

import cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.utils.ResponseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;


/*
 * 直接利用用户名或密码登录gitlab获得private-token
*/
public class LearnGitlabSessionLogin {
	public static void main(String[] args) throws Exception {
		String url = "http://192.168.1.228/api/v3/";
		
		HttpClient httpClient = new DefaultHttpClient();
		
//		HttpPost post = new HttpPost(url+"session");
		
		HttpPost post = new HttpPost(url+"session?login=zhangshuai&password=12345678");
		post.setHeader("login", "zhangshuai");
		post.setHeader("password","12345678");
		
//		HttpParams params = new BasicHttpParams();
//		params.setParameter("login", "zhangshuai");
//		params.setParameter("password", "12345678");
//		post.setParams(params);
		
		HttpResponse response = httpClient.execute(post);
		
		
		ResponseUtils.print(response.getEntity().getContent());
	}
}
