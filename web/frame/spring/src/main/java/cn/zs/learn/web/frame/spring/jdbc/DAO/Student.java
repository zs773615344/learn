package cn.zs.learn.web.frame.spring.jdbc.DAO;

public class Student {
    private Integer id;
    private Integer age;
    private String name;
    public Integer getAge() {
        System.out.println("age : "+age);
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        System.out.println("name : "+name);
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public void printThrowException() {
        System.out.println("Exception raised");
        throw new IllegalArgumentException();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return id+"\t"+name+"\t"+age;
    }
    
}
