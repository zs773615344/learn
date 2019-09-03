package cn.zs.learn.java.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;

public class DPInvocationHandler implements InvocationHandler {

    private DPQueryStatusImpl dpqs;

    public DPInvocationHandler(DPQueryStatusImpl dpqs) {
        this.dpqs = dpqs;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;

        String msg1 = MessageFormat.format("calling method {0} ({1})",
                method.getName(), Arrays.toString(args));
        System.out.println(msg1);

        ret = method.invoke(dpqs, args);

        return ret;
    }


}
