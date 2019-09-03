package cn.zs.learn.bigdate.learnhadoopsource.ipc;

import org.apache.hadoop.ipc.VersionedProtocol;
import scala.tools.nsc.doc.model.Public;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException {
        Method m = IPCServicesImpl.class.getMethod("getFileStatus",String.class);
        System.out.println(m.getDeclaringClass().getName());
        if (m.getDeclaringClass().equals(VersionedProtocol.class)) {
            System.out.println(true);
        }

        getStr();

    }


    public static void getStr() {
        if (true)
            return;
        System.out.println("haha");
        return;
    }
}
