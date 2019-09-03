package cn.zs.learn.web.frame.spring.annotation.autowired.request;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {
    private Integer age;
    private String name;
    public Integer getAge() {
        return age;
    }
    @Autowired(required=false)
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    @Autowired
    public void setName(String name) {
        this.name = name;
    }
    
}
