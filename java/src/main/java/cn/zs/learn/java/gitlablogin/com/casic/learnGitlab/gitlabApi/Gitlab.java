package cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi;

import cn.zs.learn.java.gitlablogin.com.casic.learnGitlab.gitlabApi.bean.GitlabCreateUserBean;

public interface Gitlab {
	
	// 获得所有用户信息
	String listUsers(String privateToken);
	
	// 按用户名或邮箱查找
	String searchUserByUsernameOrEmail(String privateToken,String usernameOrEmail);
	
	// 按用户名查找用户
	String searchUserByUsername(String privateToken,String username);
	
	// 按id查找
	String searchUserById(String privateToken,int id);
	
	// 創建用戶
	String createUser(GitlabCreateUserBean bean);
	
	// 修改用戶
	String updateUser(int id,GitlabCreateUserBean bean);
	
	// 刪除用戶
	String deleteUser(int id);
	
	// 列出项目
	String listProjects();
	
	String listOwnerProjects();
	
	String listStarttedProjects();
	
	String listAllProjects();
	
	String searchProjectByid(int id);
	
	String getProjectevents(int id);
	
//	@Deprecated
//	String CreateProjects(String PrivateToken);
	
	String CreateProjectsForUser(String userId);
}
