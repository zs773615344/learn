package cn.zs.learn.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.231:3306/zhang?characterEncoding=utf-8", "root", "password");
	PreparedStatement statement = connection.prepareStatement("select * from project");
	ResultSet resultSet = statement.executeQuery();
	while(resultSet.next()) {
	  System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2));
	    
	}
    }
}
