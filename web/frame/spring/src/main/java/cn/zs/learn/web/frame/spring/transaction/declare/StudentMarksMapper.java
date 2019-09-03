package cn.zs.learn.web.frame.spring.transaction.declare;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMarksMapper implements RowMapper<StudentMarks> {

    @Override
    public StudentMarks mapRow(ResultSet arg0, int arg1) throws SQLException {
        StudentMarks studentMarks = new StudentMarks();
        studentMarks.setId(arg0.getInt("id"));
        studentMarks.setName(arg0.getString("name"));
        studentMarks.setAge(arg0.getInt("age"));
        studentMarks.setSid(arg0.getInt("sid"));
        studentMarks.setMarks(arg0.getInt("marks"));
        studentMarks.setYear(arg0.getInt("year"));
        return studentMarks;
    }
    
}
