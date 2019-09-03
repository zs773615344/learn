package cn.zs.learn.web.frame.mybatis3.pojo;


import org.apache.ibatis.type.Alias;
@Alias("pro")
public class Project {
    
    private int id;
    private String name;

    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return id+"\t"+name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
