package cn.zs.learn.web.frame.strust2;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
@Namespace("/hello")
@Results({@Result(name="success",location="/hello/success.jsp")})
public class HelloAction extends ActionSupport {


	/**
     * 
     */
    private static final long serialVersionUID = 5330675936078260128L;
        
    public String execute() throws Exception {
		return "success";
	}
}
