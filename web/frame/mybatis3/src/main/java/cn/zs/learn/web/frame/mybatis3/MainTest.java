package cn.zs.learn.web.frame.mybatis3;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.zs.learn.web.frame.mybatis3.dao.ProjectMapper;
import cn.zs.learn.web.frame.mybatis3.pojo.Project;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MainTest {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis3-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        
//         int read = 0;
//         while ((read=inputStream.read()) != -1) {
//         System.out.println(read);
//         }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            // 获取映射表的两种方式
            
            // 第一种
//            Project one = (Project) session.selectOne("dao.ProjectMapper.selectById", 1);
//            System.out.println(one.getName());
            
//            List<User> list1 = session.selectList("dao.UserMapper.selectAll");
            
            // 第二种
//            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
//            
//            Project two = projectMapper.selectById(2);
//            System.out.println(two.getName());
            
//            UserMapper userMapper = session.getMapper(dao.UserMapper.class);
            
//            @SuppressWarnings("unused")
//            List<User> list2 = userMapper.selectAll();
//            for (User user : list1) {
//                System.out.println(user.toString());
//            }
            
            // 测试typeAlias的package+@select映射
//            UserMapperCopy userMapperCopy = session.getMapper(UserMapperCopy.class);
//            User selectById = userMapperCopy.selectById();
//            System.out.println(selectById.toString());
            
            // 测试增删改查
            ProjectMapper mapper = session.getMapper(ProjectMapper.class);
//            // select
//            List<Project> all = mapper.selectAll();
//            for (Project per : all) {
//                System.out.println(per.toString());
//            }
            
            Project project1 = new Project();
            project1.setId(1);
            project1.setName("liu");
//            Project project2 = new Project();
//            project2.setId(18);
//            project2.setName("qi");
//            Project project3 = new Project();
//            project3.setId(19);
//            project3.setName("qi");            
//                        
//            List<Project> insertList = new ArrayList<Project>();
//            list.add(project1);
//            list.add(project2);
//            list.add(project3);
            
            // insert
//            mapper.insert(project1);
            
            // delcom.mysql.jdbc.Driverete
//            mapper.deleteById(4);
//            mapper.update(project1);
//            mapper.insertMore(insertList);
            
//            List<Project> selectBycondition = mapper.selectBycondition(project1);
//            List<Project> selectBycondition = session.selectList("dao.ProjectMapper.selectByCondition", project1);
            List<Project> selectBycondition = mapper.selectWhere(project1);
            for(Project per:selectBycondition) {
                System.out.println(per.toString());
            }
//            mapper.update(project1);
            
            /*
             *  事物管理器使用JDBC时默认需要使用commit才能提交数据， 
             *  创建会话时可以使用sqlSessionFactory.openSession(true)设置自动提交
             *  使用MANAGED不需要
       */    
//             session.commit();
        } finally {
            session.close();
        }
    }
}
