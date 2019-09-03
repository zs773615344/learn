package cn.zs.learn.java.jvm.memorymanager;

public class gc {
    /**
     * 根搜索算法：
     * 四种引用：
     * 标记过程：
     * 垃圾收集算法
     *  标记-清除（marksweep）
     *  复制 (copy)
     *  标记-整理 (marksweepcompact)
     *  分代收集
     * 垃圾收集器：
     *  年轻代：
     *      Serial:单线程复制，默认serial old
     *      ParNew：多线程复制。默认serial old
     *      Parallel Scavenge：改进的多线程复制，可控制吞吐量，默认是parallel marksweep（由于使用的api问题，可划归到serial old）
     *  老年代：
     *      SerialOld：单线程marksweepcompact
     *      Parallel Old:多线程marksweepcompact，默认Parallel Scavenge
     *      cms:(UseConcMarkSweepGC)默认用parNew，可使用-XX:-UseParNewGC使其使用serial
     *      g1：
     *
    */
}
