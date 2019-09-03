package cn.zs.learn.bigdate.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HiveJdbc {
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection connection = DriverManager.getConnection("jdbc:hive2://zhangshuai:10000/default", "root", "");
		PreparedStatement prepareStatement = connection.prepareStatement("show tables");
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1));
		}
		connection.close();
	}
}
