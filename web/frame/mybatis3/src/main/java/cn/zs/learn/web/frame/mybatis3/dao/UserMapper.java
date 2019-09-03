package cn.zs.learn.web.frame.mybatis3.dao;

import java.util.List;

import cn.zs.learn.web.frame.mybatis3.pojo.User;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {
    
    @Select("select * from user")
    List<User> selectAll();
    
    User selectById(int id);
    void insert(User user);
    void update(User user);
    void delete(int id);
}
