package cn.zs.learn.bigdate.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.DataFrame
object SparkSessionTest {
  def main(args: Array[String]): Unit = {

    //    val conf = new SparkConf().setMaster("local[2]").setAppName("sqlcontext")
    //    val sc = new SparkContext(conf)
    //    val sqlc = new SQLContext(sc)
    //    sqlc.read.csv(this.getClass.getResource("/spark/data/mllib/sample_tree_data.csv").getFile).show()

    //    println(this.getClass.getResource("/spark/data/mllib/sample_tree_data.csv").getFile)
    //    println(this.getClass.getResource("/spark/data/mllib/sample_tree_data.csv").getPath)
    //    println(this.getClass.getResource("/spark/data/mllib/sample_tree_data.csv").toString())
    //    println(this.getClass.getResource("/spark/data/mllib/sample_tree_data.csv").toURI())

    val ss = SparkSession.builder().appName("sparksession").config("spark.master", "local[2]").getOrCreate()
    //    println(ss.conf.getAll.toMap)
    //    ss.read.csv(this.getClass.getResource("/spark/data/mllib/sample_tree_data.csv").getPath).show

    val lines = ss.readStream.format("socket").option("host", "192.168.1.245").option("port", 9999).load()
    //    lines.as[String](Encoders.STRING).flatMap(_.split(" "))(Encoders.STRING).map((_,1))(Encoders.kryo[(String,Int)]).show()
    import ss.implicits._
    val wordcount =  lines.as[String].flatMap(_.split(" ")).groupBy("value").count()
    
    //启动计算逻辑，并通过调用writeStream将结果以complete模式输出，输出目标为控制台 
    val query = wordcount.writeStream.outputMode("complete").format("console").start()
    query.awaitTermination()
    
  }
}