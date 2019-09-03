package cn.zs.learn.java.customIPC.socket;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class MethodAndParam implements Serializable {

    private String method;
    private Object[] params;

    public MethodAndParam() {
    }

    public MethodAndParam(String method, Object[] params) {
        this.method = method;
        this.params = params;
    }

    public MethodAndParam(String method) {
        this.method = method;
        this.params = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodAndParam that = (MethodAndParam) o;
        return Objects.equals(method, that.method) &&
                Arrays.equals(params, that.params);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(method);
        result = 31 * result + Arrays.hashCode(params);
        return result;
    }

    @Override
    public String toString() {
        return "MethodAndParam{" +
                "method='" + method + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }


}
