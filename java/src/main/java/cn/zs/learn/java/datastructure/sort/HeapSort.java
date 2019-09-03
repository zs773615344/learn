package cn.zs.learn.java.datastructure.sort;

public class HeapSort {
    public static void heapSort(int[] table) {
        int n = table.length;
        for (int j = n/2-1; j >= 0; j--)
            sift(table, j, n-1);
        for (int j = n-1;j > 0;j --) {
            int temp = table[0];
            table[0] = table[j];
            table[j] = temp;
            sift(table,0, j-1);
        }

    }

    // 将以begin为根的子树调整成最小堆，begin、end是序列的上界和下界
    private static void sift(int[] table, int begin, int end) {
        int i = begin, j = 2 * i + 1;
        int temp = table[i];
        while (i <= end) {
            if (j < end && table[j] > table[j+1])
                j ++;
            if (temp > table[j]) {
                table[i] = table[j];
                i = j;
                j = 2 * i + 1;
            } else break;


        }
    }
}
