package cn.zs.learn.java.observer;

import java.util.Observable;

public class News extends Observable {
    
    private String content = "";

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
        this.setChanged();
        this.notifyObservers("要传递的数据");
        
    }
}
