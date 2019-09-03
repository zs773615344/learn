package cn.zs.learn.java.observer;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer {
    	private String name = "";
    	public Reader(String name) {
    	    	this.name = name;
    	}
    @Override
    public void update(Observable o, Object arg) {
		News news = (News)o;
		//拉模型
		System.out.println(name+",接受的content=="+news.getContent());
		
		//推模型
		System.out.println(name+",接受的content=="+arg);
		
    	}

}
