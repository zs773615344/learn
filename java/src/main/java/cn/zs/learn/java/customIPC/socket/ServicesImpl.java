package cn.zs.learn.java.customIPC.socket;

import java.util.Arrays;

public class ServicesImpl implements Services {
    @Override
    public String getStr() {
        return System.getenv("HOME");
    }

    @Override
    public String getStrArr(String str) {
        return str + " is running on server.";
    }

    @Override
    public String getObjectArr(String[] str) {
        return Arrays.toString(str);
    }


}
