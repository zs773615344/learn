package cn.zs.learn.web.frame.mybatis3.pojo;

public class User {
    private int id;
    private String name;
    private int age;
    private String sex;
    private String email;
    private String phoneNumber;
    private String qqNumber;
    private String weixinNumber;
    private String password;
    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return id+"\t"+name+"\t"+age+"sex"+email+"\t"+password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getQqNumber() {
        return qqNumber;
    }
    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }
    public String getWeixinNumber() {
        return weixinNumber;
    }
    public void setWeixinNumber(String weixinNumber) {
        this.weixinNumber = weixinNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
