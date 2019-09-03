package cn.zs.learn.web.frame.mybatis3.dao.mapper;


import cn.zs.learn.web.frame.mybatis3.pojo.User;
import org.apache.ibatis.annotations.Select;


public interface UserMapperCopy {
    
    @Select("select * from user where id = 1")
    User selectById();
}
