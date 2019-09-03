package cn.zs.learn.web.frame.strust2;

import org.apache.struts2.convention.annotation.Action;

//@Result(name="success",location="/annotation.jsp")
public class AnnotationAction{
  @Action("annotation")
  public String execute(){
    return "success";
  }
}
