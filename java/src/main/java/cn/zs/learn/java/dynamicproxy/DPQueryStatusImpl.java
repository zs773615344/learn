package cn.zs.learn.java.dynamicproxy;

public class DPQueryStatusImpl implements PDQueryStatus {

    @Override
    public DPFileStatus getFileStatus(String filename) {
        return new DPFileStatus(filename);
    }
}
