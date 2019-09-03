package cn.zs.learn.java.datastructure.permutation_combination;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	static List<String> li=new ArrayList<String>();
    public static void Comb(String str,String result,int len){
		 if(result.length()==len){
			 if(li.contains(result)||li.contains(reserve(result))){
			 }else{
				 li.add(result);
				 System.out.println(result);				 
			 }
		 }else{
			 for(int i=0;i<str.length();i++){ 
				 Comb(str.substring(0, i)+str.substring(i+1),result+str.toCharArray()[i],len);
			 }
		 }		 
    }  
  
    public static String reserve(String str){
	  char[] ch=new char[str.length()];
	  String result="";
//	  if(str.length()%2!=0){
//		  ch[str.length()/2]=str.charAt(str.length()/2);
//	  }
	  for(int i=0;i<=str.length()/2-1;i++){
		  ch[str.length()/2]=str.charAt(str.length()/2);
		  ch[i]=str.charAt(str.length()-1-i);
		  ch[str.length()-1-i]=str.charAt(i);
	  }
	  for(int j=0;j<str.length();j++){
		  result=result+ch[j];
	  }
	  return result;
  }
}
