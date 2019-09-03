package cn.zs.learn.java.jvm.memorymanager.oom;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 *  VM ARGS: -XX:PermSize=10m -XX:MaxPermSize=10m
 *  上述参数在jdk1.8上无效。
 *  -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
*/

public class MethodAeraOOM {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        URL url;
        List<ClassLoader> list = new ArrayList<ClassLoader>();

        url = new File("/home/shuai/").toURI().toURL();
        URL[] urls = {url};
        while (true) {
            URLClassLoader classLoader = new URLClassLoader(urls);
            list.add(classLoader);
            classLoader.loadClass("hello");
        }
    }
}
