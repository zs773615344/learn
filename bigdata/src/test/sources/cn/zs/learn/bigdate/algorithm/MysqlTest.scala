package cn.zs.learn.bigdate.algorithm

import java.util.Properties
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.SparkSession

object mysqlTest {
  def main(Args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.OFF)
    val sc = new SparkContext(new SparkConf().setAppName("mysql").setMaster("local[*]"))
    val sqlContext = new SQLContext(sc)

    //    //展示表，全表查询
    // spark1api
    val url = "jdbc:mysql://192.168.1.224:3306/test"
    val pro = new Properties()
    pro.put("user", "root")
    pro.put("password", "password")
    val df = sqlContext.read.jdbc(url, "rating", pro)
    
    // spark2api
//    val spark:SparkSession = null
//    spark.read.format("jdbc").option("url", "jdbc:mysql://mysql:3306/test").option("user", "root").option("password", "111111").option("dbtable", "titanic_train").load().show()
    
    //    //展示字段
    val col = df.columns
    val fields = Array[String]("userId", "movieId")

    //判断字段是否存在
    for (field <- fields) {
      if (!col.contains(field)) throw new Exception("字段错误")
    }
    //	  var sr:org.apache.spark.rdd.RDD[org.apache.spark.sql.Row]=null
    //字段查询
    if (fields.length <= 0) {
      df.show()
    } else {
      if (fields.length == 1 && fields(0).toString().equals("*")) {
        df.show
      } else {
        //        fields.mkString("\"", "\",\"", "\"")
        df.select(fields(0), fields.drop(1): _*).show
      }
    }

  }
  def mysql(sqlContext: SQLContext, ip: String, port: String, url: String, user: String, password: String, databaseName: String, tableName: String): (DataFrame) = {
    val url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName
    val pro = new Properties()
    pro.put("user", user)
    pro.put("password", password)
    sqlContext.read.jdbc(url, tableName, pro)

  }
}