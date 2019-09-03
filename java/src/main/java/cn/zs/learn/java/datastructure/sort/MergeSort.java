package cn.zs.learn.java.datastructure.sort;

public class MergeSort {
	
	
	public static void main(String[] args) {
		
		int[] arr1 = {10,2,6,0,8,4}, arr2 = {9,5,7,1,3};
		printArr(mergeSort(arr1, arr2));
	}
	
	public static int[] mergeSort(int[] arr1, int[] arr2) {
		if(arr1.length == 0)
			return arr1;
		if(arr2.length == 0)
			return arr2;
		int i = 0, j = 0,k = 0;
		int[] result = new int[arr1.length + arr2.length];
		while(i < arr1.length && j < arr2.length) {
//			if(arr1[i] < arr2[j])
//				result[k++] = arr1[i++];
//			else
//				result[k++] = arr2[j++];
			result[ i + j ] = arr1[i] < arr2[j] ? arr1[i ++] : arr2[j ++];
		}
		while(i < arr1.length)
			result[k++] = arr1[i++];
		while(j < arr2.length)
			result[k++] = arr1[j++];
		return result;
	}
	
	public static void printArr(int[] arr) {
		for(int i = 0; i < arr.length; i++)
			System.out.println(i);
	}
}
