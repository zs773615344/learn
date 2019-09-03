package cn.zs.learn.web.frame.spring.transaction.programme;

import java.util.List;

import javax.sql.DataSource;

public interface StudentDao {
    public void setDataSource(DataSource dataSource);
    public void create(String name,Integer age,Integer marks,Integer year);
    public List<StudentMarks> listStudents();
}
