package cn.zs.learn.bigdate.learnhadoopsource.ipc;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class IPCFileStatus implements Writable {
    private String filename;

    public IPCFileStatus() {}
    public IPCFileStatus(String filename) {
        this.filename = filename;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(filename);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        filename = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return "IPCFileStatus{" +
                "filename='" + filename + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPCFileStatus status = (IPCFileStatus) o;
        return Objects.equals(filename, status.filename);
    }

    @Override
    public int hashCode() {

        return Objects.hash(filename);
    }
}
