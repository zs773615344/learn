package cn.zs.learn.web.frame.strust2;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

public class RestHelloAction extends ActionSupport{
    /**
     * 
     */
    private static final long serialVersionUID = -2337448112407836084L;

    @Action(value="resthello")
    public String index() {
        return "success";
    }
}
