package cn.zs.learn.bigdate.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;

public class CreateTable {

	public static Configuration configuration;
	static {
		configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
		configuration.set("hbase.zookeeper.quorum",
				"172.26.40.74:2181,172.26.40.75:2181,172.26.40.76:2181,172.26.40.77:2181,172.26.40.78:2181");
		configuration.set("hbase.master", "172.26.40.74:60010");
	}

	public static void main(String[] args) {
		createTable("ss");
	}

	public static void createTable(String tableName) {
		System.out.println("start create table ......");
		try {
			HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
			if (hBaseAdmin.tableExists(tableName)) {
				hBaseAdmin.disableTable(tableName);
				hBaseAdmin.deleteTable(tableName);
				System.out.println(tableName + " is exist,detele....");
			}
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			tableDescriptor.addFamily(new HColumnDescriptor("column1"));
			tableDescriptor.addFamily(new HColumnDescriptor("column2"));
			tableDescriptor.addFamily(new HColumnDescriptor("column3"));
			hBaseAdmin.createTable(tableDescriptor);
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end create table ......");
	}

	public static void crud() throws IOException {
//		Admin
		new HTable(configuration, "");
	}
}