package cn.zs.learn.java;

public class LearnRecursion {
	public static void main(String[] args) {
		System.out.println(fac(6));
		System.out.println(fibonaccl(24));
		
	}
	
	public static Long fac(int n) {
		if(n<0)
			throw new IllegalArgumentException();
		if(n==0 || n==1)
			return 1L;
		return n*fac(n-1);
	}
	
	public static int fibonaccl(int n) {
		if(n<0)
			throw new IllegalArgumentException();
		if(n==0 || n==1) 
			return n;
		return fibonaccl(n-2)+fibonaccl(n-1);
	}
}
