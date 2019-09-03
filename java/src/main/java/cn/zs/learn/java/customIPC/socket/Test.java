package cn.zs.learn.java.customIPC.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        Method getStr = Services.class.getMethod("getStr");
//        System.out.println(getStr.invoke(new Services(){
//
//            @Override
//            public String getStr() {
//                return "haha";
//            }
//
//            @Override
//            public String getStrArr(String str) {
//                return "aha";
//            }
//        }));


        Services services = new ServicesImpl();
        System.out.println(getStr.invoke(services));
        System.out.println(getStr.invoke((ServicesImpl) services, new Object[0]));
        System.out.println(System.getenv("Home"));


        int[] arr = new int[]{1, 2, 3};
        MethodAndParam[] arr1 = new MethodAndParam[]{new MethodAndParam(), new MethodAndParam()};
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(arr1);
        objectOutputStream.close();
        System.out.println(byteArrayOutputStream.toByteArray());
    }
}
