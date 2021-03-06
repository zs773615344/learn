package cn.zs.learn.java.datastructure.sort;

public class LearnSort {
    /**
     * 插入排序：直接插入排序，折半插入排序、希尔插入排序
     * @see InsertSort#insertSort(int[])
     *  直接插入排序：将一个元素插入到一个已排序的数组中，得到的还是一个已排序的数组。o(n)~o(n^2),稳定。
     *
     *  折半插入排序：使用直接插入排序，只是在插入的时候使用折半查找来查找插入的位置。
     *
     * @see ShellSort#shellSort(int[])
     *  希尔排序：将序列分为若干组，每组由若干相隔一段距离的元素组成。初始距离为序列长度的一半，每个组中使用直接插入排序。
     *      然后将每组元素组合成新的序列，zai将该序列继续若干组，长度为上次分组数的一半。继续插入排序，以此类推，直到
     *      分组距离为1，然后进行一次直接插入排序。
     *      该算法的目的是减少直接插入排序时会碰到的最恶劣的情况。
     *
     * 交换排序：冒泡排序、快速排序
     * @see BubbleSort#bubbleSort(int[])
     *  冒泡排序：比较相邻的两个元素的值，如果反序，则交换。如按升序，每一趟将最大的值交换到最后的位置。o(n)~o(n^2),稳定。
     * @see QuickSort#quickSort(int[], int, int)
     *  快速排序：分区交换排序。选择一个元素作为基准值，将数组剩下的元素与基准值做比较，小的放左边，大的放右边。则将数组分为
     *      两个小数组，然后对两个小数组分别进行上述操作，以此类推。 o(n*log2n)最坏的情况是o(n^2),不稳定。
     *
     * 选择排序：直接选择排序、堆排序（利用二叉树的特性）
     * @see SelectSort#selectSort(int[])
     *  直接选择排序：每一次将最小或最大的元素放在数组的最右边。o(n^2),不稳定。
     * @see HeapSort#heapSort(int[])
     *  堆排序：利用完全二叉树的性质，将序列初始为最大（小）堆，然后将根节点（即最大或最小小值）与最后一个的节点替换，然后再将
     *      前n-1个序列再调整为最大（小）堆。
     *      初始堆时，从第n/2-1个节点（即完全二叉树的最后一个分支节点）开始，依次向左即递减进行调整，一直循环到根节点。
     *      初始堆之后的每次调整，都只需要从根节点开始调整，因为只是根节点被替换了，其他节点还是保持着原来的位置，不需要调整。
     *
     *      每次调整会比较左子节点和右子节点的大小，然后用大的或小的与父节点比较，如果需要交换，则进行交换，并将下标换成被交换的
     *      子节点进入循环。不需要交换，则退出。
     *
     * 归并排序：将两个排序的子序列合并。
     *
    */
}
