package cn.zs.learn.java.customIPC.socket;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        Services services = (Services) Proxy.newProxyInstance(Services.class.getClassLoader(),
                new Class[]{Services.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket("192.168.1.245", 8088);
                        OutputStream outputStream = socket.getOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        MethodAndParam methodAndParam = null;
//                        if ( args == null || args.length == 0) {
//                            methodAndParam = new MethodAndParam(method.getName());
//                        } else {
                        methodAndParam = new MethodAndParam(method.getName(), args);
//                        }
                        objectOutputStream.writeObject(methodAndParam);

                        socket.shutdownOutput();

                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        Object s = objectInputStream.readObject();

                        objectOutputStream.close();
                        outputStream.close();
                        inputStream.close();
                        objectInputStream.close();
                        return s;
                    }
                });
        String str = services.getStr();
        System.out.println(str);
        System.out.println(services.getStrArr("haha"));
        System.out.println(services.getObjectArr(new String[]{"haha", "hehe", "xixi"}));
    }
}
