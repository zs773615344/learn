package cn.zs.learn.web.frame.spring.jdbc.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {
    
    @Override
    public Student mapRow(ResultSet arg0, int arg1) throws SQLException {
        // TODO Auto-generated method stub
        Student student = new Student();
        student.setId(arg0.getInt("id"));
        student.setName(arg0.getString("name"));
        student.setAge(arg0.getInt("age"));
        return student;
    }
    
}
