package cn.zs.learn.bigdate.algorithm

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.DataFrame

object HdfsTest {
  def main(Args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.OFF)
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("hdfs"))
    val sqlContext = new SQLContext(sc)

    //**********test1******************

    //     //读取文件，--->RDD[String]
    //    val inputFile=sc.textFile("hdfs://192.168.1.224:8020/home/app/ml-latest-small/ratings.csv")
    //
    //    //map切分文件，--->RDD[Array[String]]
    //    val splitFile=inputFile.map(_.split(","))
    //
    //    //获取第一行  作为表头
    //    val firstLine=splitFile.first()
    //
    //    //过滤第一行
    ////    val rest=splitFile.filter(f=>f(0)!=(firstLine(0)))
    //    val rest=splitFile.mapPartitionsWithIndex((index,f)=>if(index==0) f.drop(1) else f)
    //
    //    // 构造类  使用反射来推断包含具体类型的RDD的scehma
    //  //case class定义了表的schema，case class的参数名可以使用反射读取出来，成为列名
    //    case class table(userId:Int,movieId:Int,rating:Float,timestamp:String)
    //
    //    //map转化rdd形式   ---->RDD(case class() )
    //    val midFile=rest.map(f=>table(f(0).toInt,f(1).toInt,f(2).toFloat,f(3).toString))
    //
    //转化为dataframe
    //    val df=midFile.toDF()

    //    val schema=StructType(firstLine.map(f=>StructField(f,StringType)))

    //********************test2**************************

    //    val sqlc=sqlContext.read.text("hdfs://192.168.1.224:8020/home/app/ml-latest-small/ratings.csv")
    //    val first=sqlc.first().toString()
    //    val firstArray=first.substring(1,first.length()-2).split(",")
    //    val schema=StructType(firstArray.map(f=>StructField(f.toString(),StringType)))
    ////    val df=sqlContext.createDataFrame(sqlc.rdd, schema)
    //    val df=sqlContext.createDataFrame(sqlc.rdd.mapPartitionsWithIndex((index,f)=>if(index==0) f.drop(1) else f), schema)

    //***********test 3************************
    val inputFile = sc.textFile("hdfs://192.168.1.224:8020/home/app/ml-latest-small/ratings.csv")
    val splitFile = inputFile.map(_.split(","))
    val firstLine = splitFile.first()
    val rest = splitFile.mapPartitionsWithIndex((index, f) => if (index == 0) f.drop(1) else f)
    val schema = StructType(firstLine.map(f => StructField(f, StringType)))
    val df = sqlContext.createDataFrame(rest.map(f => Row(f: _*)), schema)

  }

  def hdfs(sc: SparkContext, sqlContext: SQLContext, ip: String, port: String, path: String, head: Boolean, regex: String): (DataFrame) = {
    val url = "hdfs://" + ip + ":" + port + path
    val inputFile = sc.textFile(url).map(_.split(regex))
    val firstLine = inputFile.first()
    val schema = StructType(firstLine.map(f => StructField(f, StringType)))
    if (head) {
      sqlContext.createDataFrame(inputFile.mapPartitionsWithIndex((index, f) => if (index == 0) f.drop(1) else f).map(f => Row(f: _*)), schema)
    } else {
      sqlContext.createDataFrame(inputFile.map(f => Row(f: _*)), schema)
    }
  }
}