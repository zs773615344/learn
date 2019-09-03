package cn.zs.learn.java.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.MessageFormat;
import java.util.Arrays;

public class DPMain {

    public static PDQueryStatus Create(DPQueryStatusImpl dpqs) {
        Class<?>[] interfaces = new Class[]{PDQueryStatus.class};

        DPInvocationHandler handler = new DPInvocationHandler(dpqs);

        Object result = Proxy.newProxyInstance(dpqs.getClass().getClassLoader(), interfaces, handler);

        return (PDQueryStatus) result;
    }

    public static void main(String[] args) {
//        PDQueryStatus query = Create(new DPQueryStatusImpl());
//        DPFileStatus status = query.getFileStatus("haha");
//        System.out.println(status);

        PDQueryStatus proxyInstance = (PDQueryStatus) Proxy.newProxyInstance(
                PDQueryStatus.class.getClassLoader(),
                new Class[]{PDQueryStatus.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object ret = null;

                        String msg1 = MessageFormat.format("calling method {0} ({1})",
                                method.getName(), Arrays.toString(args));
                        System.out.println(msg1);

                        ret = method.invoke(new DPQueryStatusImpl(), args);

                        return ret;
                    }
                });
        System.out.println(proxyInstance.getFileStatus("haha"));
    }
}
