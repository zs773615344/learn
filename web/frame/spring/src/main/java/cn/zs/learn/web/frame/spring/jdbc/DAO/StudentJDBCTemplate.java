package cn.zs.learn.web.frame.spring.jdbc.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate implements StudentDAO {
    private DataSource dataSource;
    private JdbcTemplate JDBCTemplateObject;
    @Override
    public void setDataSource(DataSource ds) {
        // TODO Auto-generated method stub
        this.dataSource = ds;
        this.JDBCTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void create(String name, Integer age) {
        // TODO Auto-generated method stub
        String sql="insert into Student(name,age) values(?,?)";
        JDBCTemplateObject.update(sql,name,age);
        System.out.println("insert record Name= "+name+" ,age= "+age);
        
    }
    
    @Override
    public Student getStudent(Integer id) {
        // TODO Auto-generated method stub
        String sql = "select * from Student where id =?";
        Student student = JDBCTemplateObject.queryForObject(sql, new Object[]{id},new StudentMapper());
        return student;
    }
    
    
    @Override
    public List<Student> listStudent() {
        String sql = "select * from Student ";
        List<Student> student = JDBCTemplateObject.query(sql,new StudentMapper());
        return student;
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        String sql = "delete from Student where id=?";
        JDBCTemplateObject.update(sql, id);
        System.out.println("delete record with id= "+id);
    }
    
    @Override
    public void update(Integer id, Integer age) {
        // TODO Auto-generated method stub
        String sql = "update Student set age=? where id=?";
        JDBCTemplateObject.update(sql,age,id);
        System.out.println("update record with id= "+id);
    }
    
}
