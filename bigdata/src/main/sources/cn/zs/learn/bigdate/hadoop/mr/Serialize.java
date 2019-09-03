package cn.zs.learn.bigdate.hadoop.mr;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

//  �����������л�
//  WritableComparable��Writable��Comparable���ӽӿ�
//  value���л���ֻ��̳�Writable��key���л�����̳�Writable��Comparable���߼̳�WritableComparable

public class Serialize implements WritableComparable {
	private String tel;
	private Long up;
	private Long down;
	private Long total;

	public Serialize() {
	}

	public Serialize(String tel, long up, long down) {
		this.tel = tel;
		this.up = up;
		this.down = down;
		this.total = up + down;
	}

	public void set(String tel, long up, long down) {
		this.tel = tel;
		this.up = up;
		this.down = down;
		this.total = up + down;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tel + "\t" + this.up.toString() + "\t" + this.down.toString() + "\t" + this.total.toString();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(tel);
		out.writeLong(up);
		out.writeLong(down);
		out.writeLong(total);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		tel = in.readUTF();
		up = in.readLong();
		down = in.readLong();
		total = in.readLong();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getUp() {
		return up;
	}

	public void setUp(Long up) {
		this.up = up;
	}

	public Long getDown() {
		return down;
	}

	public void setDown(Long down) {
		this.down = down;
	}

	public Long getTotal() {
		return total;
	}

	public static void main(String[] args) {
		// Serialize ss=new Serialize();
		// ss.set("123", 123, 123);
		// System.out.println(ss.getTel());
		// ss.set("234", 234, 234);
		// System.out.println(ss.getTel());
		// Serialize se=new Serialize("345",345,345);
		// System.out.println(se.getTel());
		// se.set("456", 456, 456);
		// System.out.println(se.getTotal());

	}
}
