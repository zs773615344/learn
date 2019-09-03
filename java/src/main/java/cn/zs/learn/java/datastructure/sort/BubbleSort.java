package cn.zs.learn.java.datastructure.sort;

public class BubbleSort {
	
	/**
	 * exchange标记每一次循环是否有数据交换，如没有，则排序已经完成，提前结束循环。
     *
	*/

	public static void bubbleSort(int[] arr) {
		boolean exchange = true;
		for(int i = 1; i < arr.length && exchange; i++) {
			exchange = false;
			for(int j = 0 ; j < arr.length - i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					exchange = true;
				}
			}
		}
	}

    //冒泡排序  相邻两个数比较大小然后互换位置
    public static int[] BubbleSort(int[] arr){
        int len=arr.length;
        for(int i=0;i<len;i++){
            for(int j=len-1,temp;j>i;j--){  //可改进，如没有数据交换，则可以结束循环。
                if(arr[j]<arr[j-1]){
                    temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }
        return arr;
    }
}
