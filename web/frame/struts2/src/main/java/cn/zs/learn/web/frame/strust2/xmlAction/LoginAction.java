package cn.zs.learn.web.frame.strust2.xmlAction;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
    /**
     * 
     */
    private static final long serialVersionUID = -906765608748167302L;
    private String name;
    
    public String execute() {
        if("zhang".equals(name)) {
            return "success";
        }else {
            return "error";
        }
//        return "success";
    }

    @Override
    public void validate() {
        // TODO Auto-generated method stub
        if(name.length() == 0) {
            addFieldError("name", "name is required");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
