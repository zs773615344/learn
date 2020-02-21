package cn.zs.learn.java.zeppelinclient;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class request {
	
	private static HttpClient httpclient=new DefaultHttpClient();
	
	public static HttpResponse post(String uri) throws Exception {
		HttpPost post=new HttpPost(uri);
		return httpclient.execute(post);
	}
	
	public static HttpResponse post(String uri,HttpEntity httpEntity) throws Exception {
		HttpPost post=new HttpPost(uri);
		post.setEntity(httpEntity);
		return httpclient.execute(post);
	}
	
	public static HttpResponse get(String uri) throws Exception {
		HttpGet get=new HttpGet(uri);
		return httpclient.execute(get);
	}
	
	public static HttpResponse delete(String uri) throws Exception {
		HttpDelete delete=new HttpDelete(uri);
		return httpclient.execute(delete);
	}
	
	public static HttpResponse put(String uri) throws Exception {
		HttpPut put=new HttpPut(uri);
		return httpclient.execute(put);
	}
	
	public static HttpResponse put(String uri,HttpEntity httpentity) throws Exception {
		HttpPut put=new HttpPut(uri);
		put.setEntity(httpentity);
		return httpclient.execute(put);
	}
}
