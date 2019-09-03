package cn.zs.learn.java.datastructure.sort;

public class QuickSort {
	
	
	public static void quickSort(int[] arr, int begin, int end) {
		if(begin < end) {
			int i = begin, j = end;
			int vot = arr[i];
			while(i != j) {
				while(i < j && vot <= arr[j])
					j --;
				if(i < j)
					arr[i++] = arr[j];
				while(i < j && vot >= arr[i])
					i ++;
				if(i < j)
					arr[j--] = arr[i];
			}
			arr[i] = vot;
			quickSort(arr, begin, j-1);
			quickSort(arr, i+1, end);
		}
	}

    //快速排序  不稳定
    public static int[] QuickSort(int[] arr,int low,int high){
        if(low>=high){
            return arr;
        }
        int partition=Partition(arr,low,high);
        Partition(arr,low,partition);
        Partition(arr,partition,high);
        return arr;
    }
    public static int Partition(int[] arr,int low ,int high){
        int tmp=arr[low];
        while(low<high){
            while(low<high && arr[high]>=tmp){
                high--;
            }
            arr[low]=arr[high];
            while(low<high && arr[low]<=tmp){
                low++;
            }
            arr[high]=arr[low];
        }
        arr[low]=tmp;
        return low;
    }
}
