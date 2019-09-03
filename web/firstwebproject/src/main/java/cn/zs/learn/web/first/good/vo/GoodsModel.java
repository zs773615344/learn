package cn.zs.learn.web.first.good.vo;

import java.io.Serializable;

public class GoodsModel implements Serializable{

	private int uuid;
	private String name;
	private String desc;
	private float price;
	
//	public GoodsModel() {}
	
//	public GoodsModel(int uuid,String name ,String desc,float price) {
//		this.uuid=uuid;
//		this.name=name;
//		this.price=price;
//		this.desc=desc;
//	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.uuid+"\t"+this.name+"\t"+this.desc+"\t"+this.price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uuid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(getClass()!=obj.getClass()) {
			return false;
		}
		GoodsModel gm=(GoodsModel)obj;
		if(uuid!=gm.uuid) {
			return false;
		}
		return true;
	}

	
	
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
