package cn.zs.learn.web.frame.mybatis3.dataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {
       public C3P0DataSourceFactory() {
           this.dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
       }
}
