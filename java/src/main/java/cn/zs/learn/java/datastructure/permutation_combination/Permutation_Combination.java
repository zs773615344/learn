package cn.zs.learn.java.datastructure.permutation_combination;

public class Permutation_Combination {

	public static void main(String[] args) {
		String s = "abcda";
        String result = "";
        Permutation.perm1(s, result, s.length());
        System.out.println("############################");
        Combination.Comb(s, result,s.length());
	}

}
