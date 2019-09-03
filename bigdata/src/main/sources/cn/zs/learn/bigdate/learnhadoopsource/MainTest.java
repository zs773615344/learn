package cn.zs.learn.bigdate.learnhadoopsource;

import org.apache.hadoop.conf.Configuration;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class MainTest {
    public static void main(String[] args) {
//        String[] s = {"1","2"};
//        Class<?> componentType = s.getClass().getComponentType();
//        System.out.println(s.getClass().getName());
//        System.out.println(componentType.getName());
//
//        Integer i = 2;
//        Class<? extends Integer> iClass = i.getClass();
//        Class<?> iClassComponentType = iClass.getComponentType();
//
//        System.out.println(iClass.getName());
////        System.out.println(iClassComponentType.getComponentType().getName());
//        System.out.println(new Configuration().getClass("fs.file.impl",null));


        int kvbuffer = 100*1024*1024;
        System.out.println(kvbuffer);
        int meta = 16;
        System.out.println(0 - (0 % 16));
        int kvindex = (kvbuffer - meta) % kvbuffer / 4;
        System.out.println(kvindex);
        System.out.println(kvindex * 4);

        System.out.println((kvindex - 4 + kvbuffer/4) % (kvbuffer / 4));

        byte[] buffer = new byte[kvbuffer];
        System.out.println(buffer.length);
        IntBuffer metabuffer =  ByteBuffer.wrap(buffer).asIntBuffer();
        System.out.println(metabuffer.capacity());

    }
}
