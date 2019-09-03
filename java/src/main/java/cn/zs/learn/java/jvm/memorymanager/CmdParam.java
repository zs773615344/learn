package cn.zs.learn.java.jvm.memorymanager;

public class CmdParam {
    /**
     * -Xmx = -XX:MaxHeapSize    // java最大堆
     * -Xms = -XX:InitialHeapSize     // java初始堆
     * -Xss = -XX:ThreadStackSize   // java栈内存
     * -Xoss    // java本地栈，实际上无效
     * @since jdk1.7以前
     * -XX:PermSize // java方法区
     * -XX:MaxPermSize // java方法区最大值
     * @since jdk1.8
     * -XX:MetaspaceSize
     * -XX:MaxMetaspaceSize
     * -XX:MaxDirectMemorySize  // 直接内存，如不指定，默认与-Xmx一样
     * -Xmn = -XX:MaxNewSize    // 年轻代最大值
     * -XX:NewRatio // 年轻代与老年代的比值.默认2：1
     * -XX:NewSize  // 年轻代
     * -XX:OldSize  // 老年代
     * -XX:survivorRatio=8  //eden与survivor的比例，默认8：1
     * -XX:PretenureSizeThreshold   //大于该值直接进入老年代
     * -XX:MaxTenuringThreshold     //每经过一次minorgc年龄就加+,大于该值直接进入老年代
     * -XX:HandlePromotionFailure   //是否开启空间分配担保失败（minorgc时检测每次进入老年代的平均大小是否大于老年代的剩余空间，如果大于，则fullgc，如果小于，则看是否允许失败，如果允许，则进行minorgc，不允许，进行fullgc。如果担保失败后，再进行一次fullgc）
     * -XX:AdaptiveSizePausePolicy  //是否开启动态调整java堆各区域内存以及进入老年代的年龄
     * -XX:+HeapDumpOnOutOfMemoryError  // 输出堆转储
     * -verbose:gc  // 查看gc信息
     * -XX:PrintGCDetails
     * -verbose:class   // 查看类信息
     * -XX:+TraceClassLoading   // 跟踪类加载信息
     * -XX:+TraceClassUnLoading // 跟踪类卸载信息
     *
     *  java -XX:+PrintFlagsFinal // 查看所有参数
     *
     * jps [-l] // 查看java进程
     * jstat // jvm统计监视
     * jinfo [-flag paramName] pid // 查看java进程的配置
     * jinfo -flags pid
     * jmap // java内存映像工具
     * jmap -heap pid // 查看堆内存摘要
     * jhat //配合jmap，查看jmap生成的dump文件
     * jstack   // java栈跟踪工具
     * jconsole // 界面化控制台
     * jvisualvm    // 界面化控制台
     *
     *
     *
    */
}
