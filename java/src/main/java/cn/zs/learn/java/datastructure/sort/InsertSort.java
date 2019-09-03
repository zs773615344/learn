package cn.zs.learn.java.datastructure.sort;

public class InsertSort {
	public static void main(String[] args) {
		int[] arr = {1,9,5,3,6,7,8,2,10};
		printArr(insertSort(arr));
	}
	
	public static int[] insertSort(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int temp = arr[i],j;
			for(j = i-1; j >= 0 && temp < arr[j]; j--)
				arr[j+1] = arr[j];
			arr[j+1] = temp;
		}
		return arr;
	}
	
	public static void printArr(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
