package cn.zs.learn.web.frame.mybatis3.dao;

import cn.zs.learn.web.frame.mybatis3.pojo.Project;

import java.util.List;


public interface ProjectMapper {
    Project selectById(int id);
    	
    List<Project> selectAll();
    
    void insert(Project project);
    
    void deleteById(int id);
    
    void update(Project project);
    
    void insertMore(List<Project> list);
    List<Project> selectBycondition(Project pro);
    List<Project> selectWhere(Project pro);
    void updateAll(Project pro);
}
