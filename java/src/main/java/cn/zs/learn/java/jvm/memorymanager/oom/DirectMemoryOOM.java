package cn.zs.learn.java.jvm.memorymanager.oom;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * VM args: -Xms20m -XX:MaxDirectMemorySize=10m
 *
*/

@SuppressWarnings("restriction")
public class DirectMemoryOOM {

    private static final int _MB = 1024 * 1024;
    public static void main(String[] args) throws IllegalAccessException {
		Field fields = Unsafe.class.getDeclaredFields()[0];
        fields.setAccessible(true);
        Unsafe unsafe = (Unsafe) fields.get(null);
        while (true) {
            unsafe.allocateMemory(_MB);
        }
    }
}
