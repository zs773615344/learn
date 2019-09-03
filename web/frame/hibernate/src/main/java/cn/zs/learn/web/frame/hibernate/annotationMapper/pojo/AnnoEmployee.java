package cn.zs.learn.web.frame.hibernate.annotationMapper.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
// @Entity 注释标志着这个类为一个实体 bean，所以它必须含有一个没有参数的构造函数并且在可保护范围是可见的。
@Entity
@Table(name="annoemployee")
public class AnnoEmployee {
    @Id @GeneratedValue
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="salary")
    private int salary;
    
    public AnnoEmployee() {}
    public AnnoEmployee(String fname,String lname,int salary) {
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+"\t"+this.firstName+"\t"+this.lastName+"\t"+this.salary;
	}
    
}
