package cn.zs.learn.java.dynamicproxy;

public class DPFileStatus {
    private String filename;

    public DPFileStatus(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "DPFileStatus{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
