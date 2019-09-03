package cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;



public class ResponseUtils {
	
	public static void print(InputStream in) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String readLine = null;
		StringBuffer sb = new StringBuffer();
		while((readLine = br.readLine()) != null) {
//			System.out.println(readLine);
			sb.append(readLine);
		}
		System.out.println(sb);


		br.close();
		in.close();
	}
	
}
