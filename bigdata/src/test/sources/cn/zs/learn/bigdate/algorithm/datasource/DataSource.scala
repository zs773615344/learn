package cn.zs.learn.bigdate.algorithm.datasource

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.DataFrame
import java.util.Properties
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.Row

object DataSource {

  def hdfsSource(sc: SparkContext, sqlContext: SQLContext, ip: String, port: String, path: String, head: Boolean, regex: String): DataFrame = {
    val url = "hdfs://" + ip + ":" + port + path
    val inputFile = sc.textFile(url).map(_.split(regex))
    val firstLine = inputFile.first()
    val schema = StructType(firstLine.map(f => StructField(f, StringType)))
    //		val schema = StructType(firstLine.map(f=>StructField(f,StringType)))
    //		val schema = StructType((1 to firstLine.length).map(f=>StructField("field"+f,StringType)))

    //		StructType((1 to firstLine.length).map(f=>),schema)

    val df = if (head)
      sqlContext.createDataFrame(inputFile.mapPartitionsWithIndex((index, f) => if (index == 0) f.drop(1) else f).map(f => Row(f: _*)), schema)
    else
      sqlContext.createDataFrame(inputFile.map(f => Row(f: _*)), schema)
    val map = Map(("dataNumber", df.count()))
    println(map)

    return df
  }

  def mysqlSource(sqlContext: SQLContext, ip: String, port: String, user: String, password: String, databaseName: String, tableName: String): DataFrame = {
    val url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName
    val pro = new Properties()
    pro.put("user", user)
    pro.put("password", password)
    val df = sqlContext.read.jdbc(url, tableName, pro)
    val map = Map(("dataNumber", df.count()))
    println(map)
    return df
  }

  def hiveSource(sqlContext: SQLContext, sql: String): DataFrame = {
    sqlContext.sql(sql)
  }

  def inputSource(data: Array[Double]): Array[Double] = {
    return data
  }
  //查询字段
  def Fields(df: DataFrame): (Array[String]) = {
    df.columns
  }

//  根据所给字段查询
  def Select(df: DataFrame, fields: Array[String]): (DataFrame) = {

    //判断字段是否存在
    for (field <- fields) {
      if (!df.columns.contains(field)) throw new Exception("瀛楁閿欒")
    }

    //字段查询
    if (fields.length <= 0) {
      df
    } else {
      if (fields.length == 1 && fields(0).toString().equals("*")) {
        df
      } else {
        fields.mkString("\"", "\",\"", "\"")
        df.select(fields(0), fields.drop(1): _*)
      }
    }
  }

}
