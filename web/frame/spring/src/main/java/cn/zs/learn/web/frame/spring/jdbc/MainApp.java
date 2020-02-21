package cn.zs.learn.web.frame.spring.jdbc;

import java.util.List;

import cn.zs.learn.web.frame.spring.jdbc.DAO.Student;
import cn.zs.learn.web.frame.spring.jdbc.DAO.StudentJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ApplicationContext context = 
                new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        StudentJDBCTemplate studentTemplate = (StudentJDBCTemplate) context.getBean("studentJdbcTemplate");
//        studentTemplate.create("zhangsan", 11);
//        studentTemplate.create("lisi", 12);
//        studentTemplate.create("wangwu", 13);
        System.out.println("***********************");
        Student student1 = studentTemplate.getStudent(1);
        System.out.println(student1.toString());
        System.out.println("***********************");
        List<Student> student = studentTemplate.listStudent();
        System.out.println(student);
        System.out.println("***********************");
        studentTemplate.update(2, 1111);
        System.out.println("***********************");
        studentTemplate.delete(1);
        System.out.println("***********************");
        ((ClassPathXmlApplicationContext)context).close();
    }
    
}
