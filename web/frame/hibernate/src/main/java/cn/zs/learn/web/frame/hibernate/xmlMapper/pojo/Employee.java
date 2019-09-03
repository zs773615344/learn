package cn.zs.learn.web.frame.hibernate.xmlMapper.pojo;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    
    public Employee() {
    }
    
    public Employee(String fname, String lname, int salary) {
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
        return this.id + "\t" + this.firstName + "\t" + this.lastName + "\t" + this.salary;
    }
}
