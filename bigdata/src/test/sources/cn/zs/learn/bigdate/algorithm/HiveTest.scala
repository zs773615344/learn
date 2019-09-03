package cn.zs.learn.bigdate.algorithm

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import java.util.Properties

object HiveTest {
  def main(Args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.OFF)
    val sc = new SparkContext(new SparkConf().setAppName("hive").setMaster("local[*]"))
    val sqlContext = new SQLContext(sc)

    // test1
    //		  sqlContext.sql("select * from rating")

    //test2
    //		  val hc=new HiveContext(sc)
    //		  hc.sql("select * from haha").show()

    //test3
    //		  Class.forName("org.apache.hive.jdbc.HiveDriver")
    //		  val con=DriverManager.getConnection("jdbc:hive2://172.18.0.3:10000","","")
    //		  val st=con.createStatement()
    //		  val rs=st.executeQuery("select * from haha")
    //		  print(rs)

    //test4   报错
    val url = "jdbc:hive2://10.11.100.142:10000"
    val pro = new Properties()
    pro.put("user", "root")
    pro.put("password", "111111")
    val sj = sqlContext.read.jdbc(url, "haha", pro)
    sj.show
    print("haha")
  }
}