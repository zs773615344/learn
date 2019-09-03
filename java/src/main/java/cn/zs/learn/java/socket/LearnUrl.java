package cn.zs.learn.java.socket;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class LearnUrl {
    
    	/*
    	 *  URI:統一資源標識符（Uniform Resource Identifier）
    	 *  URL:統一資源定位符（Uniform Resource Locator）
    	 *  URN:統一資源命名（Uniform Resource Name）
    	 *  
    	 *  URL和URN都是URI的子集
    	 *  URI唯一標識資源；
    	 *  		包括：1.訪問資源的命名機制 2.存放資源的主機名與端口 3.資源自身的名稱，由路徑標示
    	 *
    	 *      uri由uri模式（getScheme()）、模式特有部分(getSchemeSpecificPart())和片段（getFragment()）组成
    	 *          即 schema:SchemeSpecificPart#Fargment
    	 *
    	 *      SchemeSpecificPart：
    	 *          //authority/path?query
    	 *          //授权机构（getAuthority()）/路径(getPath())？查询(getQuery())
    	 *
    	 *      authority:
    	 *          userinfo@host:port
    	 *          用户信息（getUserInfo()）@主机名(getHost())：端口(getPort())
    	 *
    	 *  URL標識internet資源的位置，資源如何訪問；
    	 *  		包括：1.協議 2.存放資源的主機名與端口類 3.資源的具體地址 
    	 *  	 	類似人的住址
    	 *
    	 *  	url: protocol://userinfo@host:port/path?query#fragment
    	 *          此处的protocol对应uri的schema。
    	 *          常用的protocol或schema：file、http、https、ftp、mailto、telnet……
    	 *          URI、URL都可以使用上述常用的schema，但是URI的schema可以自定义，
    	 *          而URL的protocol如果要使用自定义protocol的话，由于java无法识别
    	 *          自定义protocol，需要做一些额外工作。
    	 *
    	 *          如让java使用hdfs协议，需要在代码中添加
    	 *          URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    	 *          注：该方法是静态方法，只能在jvm调用一次
    	 *
    	 *
    	 *  URN只標識資源的名字，不提供訪問方式；類似人的身份證號 
    	 *  
    	 * 
    	 * 	如：URI：http://bitpoetry.io/posts/hello.html#intro
    	 *		URL：http://bitpoetry.io/posts/hello.html
    	 *		URN：bitpoetry.io/posts/hello.html#intro
    	 */
    	
    
    	public static void main(String[] args) throws Exception {
	    URL url = new URL("http://10.11.200.66/dashboard/projects?utf8=%E2%9C%93&filter_projects=s");
	    	System.out.println(url.getAuthority());
	    	System.out.println(url.getDefaultPort());
	    	System.out.println(url.getFile());
	    	System.out.println(url.getHost());
	    	System.out.println(url.getPath());
	    	System.out.println(url.getPort());
	    	System.out.println(url.getProtocol());
	    	System.out.println(url.getQuery());
	    	System.out.println(url.getRef());
	    	System.out.println(url.getUserInfo());
	    	System.out.println(url.getContent().toString());
	    	System.out.println(url.toString());
	    	System.out.println(url.toExternalForm());
	    	URLConnection connection = url.openConnection();
	    	System.out.println(connection.getContentType());
	    	System.out.println(connection.getConnectTimeout());
	    	System.out.println(connection.getContentEncoding());
	    	System.out.println(connection.getContentLength());
	    	System.out.println(connection.getContentLengthLong());
	    	System.out.println(connection.getAllowUserInteraction());
	    	System.out.println(connection.getDate());
	    	InputStream inputStream = connection.getInputStream();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//	    	String read;
//	    	while((read = reader.readLine()) != null) {
//	    	    System.out.println(read);
//	    	}
	    	BufferedReader reader2 = new BufferedReader(new InputStreamReader(url.openStream()));
	    	String read2;
	    	while((read2 = reader.readLine()) != null) {
	    	    System.out.println(read2);
	    	}
	    	URI uri = url.toURI();
//	    	System.out.println(uri.getScheme());
//	    	System.out.println(uri);
	    	
	}
}
