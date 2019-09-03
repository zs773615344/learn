package cn.zs.learn.web.frame.mybatis3.dataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory{
    public DruidDataSourceFactory() {
        // TODO Auto-generated constructor stub
        this.dataSource = new com.alibaba.druid.pool.DruidDataSource();
    }
}
