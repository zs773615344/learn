package cn.zs.learn.java.datastructure.permutation_combination;

public class Permutation {
	
	public static void Perm(String str ,String result ,int len){
		if(result.length()==len){            //表示遍历完了一个全排列结果
	           System.out.println(result);
	      }else{
	           for(int i=0;i<str.length();i++){
	               if(result.indexOf(str.charAt(i))<0){    //返回指定字符在此字符串中第一次出现处的索引。
	                   //System.out.println("字母："+str.charAt(i));
	                   Perm(str, result+str.charAt(i), len);
	                }
	            } 
	        }
	}
	
	 public static void perm1(String str,String result,int len){
		 if(result.length()==len){
			 System.out.println(result);
		 }else{
			 for(int i=0;i<str.length();i++){ 
				 perm1(str.substring(0, i)+str.substring(i+1),result+str.charAt(i),len);
			 }
		 }
		 
  }
}
