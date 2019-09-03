package cn.zs.learn.bigdate.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Create {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection("jdbc:hive2://zhangshuai:10000/default", "root", "");
		Statement stat = conn.createStatement();
		String sql = "create table test2(id int)";
		stat.execute(sql);
	}

}