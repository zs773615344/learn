package cn.zs.learn.java.gitlablogin;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.utils.ResponseUtils;
/*
 * 使用private_token调用gitlab api
 * 
*/
public class LearnGitlabTokenLogin {
    
  public static void main(String[] args) throws Exception {
	  
	  String url = "http://192.168.1.228/api/v3/";
	  HttpClient httpclient = new DefaultHttpClient();
//	  HttpGet get = new HttpGet("http://192.168.1.228/api/v3/user?private_token=y7ezzsg9HitTPDDhdNds");
	  HttpGet get = new HttpGet(url+"users");
	  

//	  HttpParams params = new BasicHttpParams();
//	  params.setParameter("PRIVATE-TOKEN", "y7ezzsg9HitTPDDhdNds");
//	  params.setParameter("private_token", "y7ezzsg9HitTPDDhdNds");
//	  System.out.println(params.getParameter("private_token"));
//	  get.setParams(params);
	  
	  
	  // root的token
//	  get.setHeader("PRIVATE-TOKEN", "y7ezzsg9HitTPDDhdNds");
	  // zhangshuai的token
//	  get.setHeader("private-token","yvTQW-Z5LsWxMxA74j6P");
	  
//	  Header header = new BasicHeader("PRIVATE-TOKEN", "y7ezzsg9HitTPDDhdNds");
//	  get.setHeader(header);
	  
	  
	  HttpResponse response = httpclient.execute(get);
	  
	  InputStream content = response.getEntity().getContent();
	  ResponseUtils.print(content);
  }
}
