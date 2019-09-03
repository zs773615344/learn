package cn.zs.learn.java.serialize;

import java.io.*;

public class JavaSerialize implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String address;
	private int number;

    public JavaSerialize() {
    }

    public JavaSerialize(String name, String address, int number) {
		this.name=name;
		this.address=address;
		this.number=number;
	}
	
	public void set(String name,String address,int number){
		this.name=name;
		this.address=address;
		this.number=number;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+"\t"+address+"\t"+number;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        JavaSerialize ser = new JavaSerialize();
		ser.name="zhang";
		ser.address="henan";
		ser.number=1;
        FileOutputStream fos = new FileOutputStream("/home/shuai/Desktop/ser.txt");
		ObjectOutputStream out = new ObjectOutputStream(fos);
         out.writeObject(ser);
         out.close();
         fos.close();
         System.out.println("haha");
	}

}
