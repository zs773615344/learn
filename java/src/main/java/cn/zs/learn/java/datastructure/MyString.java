package cn.zs.learn.java.datastructure;

public class MyString {

    char[] value;

    public MyString() {
        value = new char[]{};
    }

    public MyString(String str) {
        value = str.toCharArray();
    }

    public int length() {return value.length;}

    //brute-force 目标串回溯
    int indexOf(String str, int begin) {
        if (str != null && str.length() > 0 && this.length() >= str.length()) {
            int i = begin , j = 0;
            while (i <= (this.length() - str.length())) {
                if (value[i] == str.charAt(j)) {
                    i ++;
                    j ++;
                } else {
                    i ++;
                    j = 0;
                }
                if (j == str.length())
                    return i - j;
            }
        }
        return -1;
    }

    // String用的 暴力匹配，但效率很高，why？
    int indexOf2(String str, int begin) {
        if (str != null && str.length() > 0 && this.length() >= str.length()) {
            int max = this.length() - str.length();
            int first = str.charAt(0);
            for (int i = begin; i <= max; i++) {
                if (value[i] != first)
                    while (++i <= max && value[i] != first);
                if (i <= max) {
                    int j = i + 1;
                    int end = j + str.length() - 1;
                    for (int k = 1; j < end && value[j] == str.charAt(k); j++, k++);
                    if (j == end)
                        return i;
                }
            }
        }
        return -1;
    }

    // kmp
    static int indexOf3(String target, String pattern, int begin) {
        if (target != null && pattern != null && pattern.length() > 0 && target.length() >= pattern.length()) {
            int i = begin, j = 0, max = target.length() - pattern.length();
            int[] next = getNext(pattern);
            while (i <= max) {
                if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
                    i ++;
                    j ++;
                } else
                    j = next[j];
                if (j == pattern.length())
                    return i - j;
            }
        }
        return -1;
    }

    /**
     * next[0] = -1;
     * next[j] = k; 0<=k<j;
     *
     * next[j]=k则，p0p1……p(k-1)==p(j-k)……p(j-1)
     * 若p[k] == p[j],则next[j+1]=k+1;
     * 若p[k] ！= p[j]，则相当于进行模式串匹配，令k=next[k],然后重新匹配。
     *
    */
    static int[] getNext(String pattern) {
        int j = 0, k = -1;
        int[] next = new int[pattern.length()];
        next[0] = -1;
        while (j < pattern.length() - 1) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                j ++;
                k ++;
                next[j] = k;
            } else
                k = next[k];
        }
        return next;
    }

    // 对上述改进
    static int[] betterGetNext(String pattern) {
        int j = 0, k = -1;
        int[] next = new int[pattern.length()];
        next[0] = -1;
        while (j < pattern.length() - 1) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                j ++;
                k ++;
                if (pattern.charAt(j) != pattern.charAt(k))
                    next[j] = k;
                else
                    next[j] = next[k];
            } else
                k = next[k];
        }
        return next;
    }
}
