package cn.zs.learn.java.datastructure.sort;

public class SelectSort {

	public void selectSort(int[] arr) {
		for(int i = 0 ; i < arr.length - 1; i++) {
			int min = i;
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[min]) 
					min = j;
				if(min != i) {
					int temp = arr[i];
					arr[i] = arr[min];
					arr[min] = temp;					
				}	
			}
		}
	}

	public static int[] SelectSort(int[] arr){
//		第i个数依次与其后面的每个数比较大小，然后互换位置，以此来得到最小值
		for(int m=0;m<arr.length-1;m++){
			for(int n=m+1,k;n<arr.length;n++){
				if(arr[m]>arr[n]){  //可改进，一次循环只需要交换一次。
					k=arr[m];
					arr[m]=arr[n];
					arr[n]=k;
				}
			}
		}
		return arr;
	}
}
