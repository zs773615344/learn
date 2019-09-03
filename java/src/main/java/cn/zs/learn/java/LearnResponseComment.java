package cn.zs.learn.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class LearnResponseComment {
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://192.168.1.228/users/sigin_in");
		try {
			HttpResponse response = httpClient.execute(get);
			
			HttpParams params = response.getParams();
			System.out.println(params.toString());
			String parameter = (String) params.getParameter("authenticity_token");
			System.out.println(parameter);
			
			Header[] allHeaders = response.getAllHeaders();
//			for(Header header:allHeaders) {
//				System.out.println(header.toString());
//			}
						
			
			StatusLine statusLine = response.getStatusLine();
			ProtocolVersion protocolVersion2 = statusLine.getProtocolVersion();
			String protocol2 = protocolVersion2.getProtocol();
			String reasonPhrase = statusLine.getReasonPhrase();
			int statusCode = statusLine.getStatusCode();
//			System.out.println(protocol2);
//			System.out.println(reasonPhrase);
//			System.out.println(statusCode);
			
			ProtocolVersion protocolVersion = response.getProtocolVersion();
			String protocol = protocolVersion.getProtocol();
			int major = protocolVersion.getMajor();
			int minor = protocolVersion.getMinor();
//			System.out.println(protocol);
//			System.out.println(major);
//			System.out.println(minor);
			
			
			
			HttpEntity entity = response.getEntity();
			
			Header contentType = entity.getContentType();
//			System.out.println(contentType.toString());
//			HeaderElement[] elements = contentType.getElements();
//			System.out.println(elements.length);
//			if(elements.length > 0) {
//				for(HeaderElement element:elements) {
//					String name = element.getName();
//					System.out.println(element.getParameterCount());
//					System.out.println(name);
//					System.out.println(element.getParameterByName(name));
//					NameValuePair[] nameValuePairs = element.getParameters();
//					System.out.println(nameValuePairs.length);
//					if(nameValuePairs.length > 0) {
//						for(NameValuePair nameValue:nameValuePairs) {
//							System.out.println(nameValue.getName());
//							System.out.println(nameValue.getValue());
//						}
//					}
//				}
//			}
			
			Header contentEncoding = entity.getContentEncoding();
			
//			if(contentEncoding == null) {
//				System.out.println("contentEncoding为null");
//			}else {
//				System.out.println(contentEncoding.getName());
//				System.out.println(contentEncoding.getValue());
//				HeaderElement[] elements = contentEncoding.getElements();
//				System.out.println(elements.toString());
//			}


			long contentLength = entity.getContentLength();
//			System.out.println(contentLength);
			
			InputStream in = entity.getContent();
//			BufferedReader bw = new BufferedReader(new InputStreamReader(in));
//			String readLine = null;
//			while((readLine = bw.readLine()) != null) {
//				System.out.println(readLine);
//			}
			
			
			// 语言环境
			Locale locale = response.getLocale();
//			System.out.println("toLanguageTag:\t"+locale.toLanguageTag());
//			System.out.println("toString:\t"+locale.toString());
//			System.out.println("getCountry:\t"+locale.getCountry());
//			System.out.println("getLanguage:\t"+locale.getLanguage());
//			System.out.println("getVariant:\t"+locale.getVariant());			
//			System.out.println("getScript:\t"+locale.getScript());
//			System.out.println("getDisplayCountry:\t"+locale.getDisplayCountry());
//			System.out.println("getDisplayLanguage:\t"+locale.getDisplayLanguage());
//			System.out.println("getDisplayName:\t"+locale.getDisplayName());
//			System.out.println("getDisplayVariant:\t"+locale.getDisplayVariant());
//			
//			
//			System.out.println("getISO3Country:\t"+locale.getISO3Country());
//			System.out.println("getISO3Language:\t"+locale.getISO3Language());
			
			
			Locale default1 = locale.getDefault();
//			System.out.println("getDefault:\t"+default1.toString());
			
			
			Locale[] availableLocales = locale.getAvailableLocales();
//			for(Locale availableLocale:availableLocales) {
//				System.out.println(availableLocale.toString());
//			}
			
			String[] isoCountries = locale.getISOCountries();
//			for(String isoCountry:isoCountries) {
//				System.out.println(isoCountry);
//			}
			
			String[] isoLanguages = locale.getISOLanguages();
//			for(String isoLanguage:isoLanguages) {
//				System.out.println(isoLanguage);
//			}
			
			Set<String> unicodeLocaleAttributes = locale.getUnicodeLocaleAttributes();
//			System.out.println(unicodeLocaleAttributes.size());
//			for(String unicodeLocaleAttributy:unicodeLocaleAttributes) {
//				System.out.println(unicodeLocaleAttributy);
//			}
			
			Set<String> unicodeLocaleKeys = locale.getUnicodeLocaleKeys();
//			System.out.println(unicodeLocaleKeys.size());
			
			Set<Character> extensionKeys = locale.getExtensionKeys();
//			System.out.println(extensionKeys.size());
//			for(Character character:extensionKeys) {
//				System.out.println(character.toString());
//			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

