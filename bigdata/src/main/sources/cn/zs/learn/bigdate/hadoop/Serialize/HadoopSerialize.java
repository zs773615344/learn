package cn.zs.learn.bigdate.hadoop.Serialize;

import org.apache.calcite.rel.core.SetOp;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class HadoopSerialize implements Writable,Comparable {

    private String name;

    private String address;

    private int number;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeChars(name);
        dataOutput.writeChars(address);
        dataOutput.writeInt(number);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.name = dataInput.readUTF();
        this.address = dataInput.readUTF();
        this.number = dataInput.readInt();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }


    public static void main(String[] args) {
        short s = 1;
    }
}
