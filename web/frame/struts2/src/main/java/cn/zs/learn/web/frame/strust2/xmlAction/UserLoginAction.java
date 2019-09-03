package cn.zs.learn.web.frame.strust2.xmlAction;

import cn.zs.learn.web.frame.strust2.vo.UserPassword;
import com.opensymphony.xwork2.ActionSupport;



public class UserLoginAction extends ActionSupport{
    
	/**
     * 
     */
    private static final long serialVersionUID = -2388461714209561514L;
    private UserPassword userPassword;
    
    public String execute() {
        if("zhang".equals(userPassword.getUser())) {
            if("123123".equals(userPassword.getPassword())) {
                return "success";
            }else
                return "error";
        }else
            return "none";
    }
    @Override
    public void validate() {
        if(userPassword.getUser().length() == 0) {
            addFieldError("userPassword.user", "name is required.");
        }
        if(userPassword.getPassword().length() == 0) {
            addFieldError("userPassword.password", "password is required.");
        }
    }
    
    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }
    
}
