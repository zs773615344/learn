package cn.zs.learn.java.datastructure.sort;

public class ShellSort {
    public static void shellSort(int[] table) {
        for (int delta = table.length/2; delta > 0; delta /= 2) {
            for (int i = delta; i < table.length; i ++) {
                int temp = table[i], j;
                for (j = i - delta; j >= 0 && temp < table[j]; j -= delta)
                    table[j + delta] = table[j];
                table[j + delta] = temp;
            }
        }
    }
}
