package cn.zs.learn.bigdate.eml.filesplit

import org.apache.spark.sql.SparkSession
import org.apache.hadoop.conf.Configuration
import edu.umd.cloud9.collection.XMLInputFormat
import org.apache.hadoop.io.Text
import org.apache.hadoop.io.LongWritable

object FileSplit {
  def main(args: Array[String]): Unit = {
    //test
//    var args = Array("hdfs://10.11.100.138:9000/kddcup/kddcup.data","csv","0.8","hdfs://10.11.100.138:9000/filesplit1","hdfs://10.11.100.138:9000/filesplit2")
    
    val in = args(0)
    val textType = args(1)
    val ratio = args(2).toDouble
    val ratioArray = Array(ratio,1-ratio)
    val out1 = args(3)
    val out2 = args(4)
    val spark = SparkSession.builder().appName("FileSplit")/*.master("local[*]")*/.getOrCreate()
    import spark.implicits._
   
    val Array(data1, data2) = textType match {
      case "parqut" => spark.read.parquet(in).randomSplit(ratioArray)
      case "csv" => spark.read/*.option("timestampFormat", "yyyy/MM/dd HH:mm:ss ZZ")*/.option("inferSchema", true).option("header", false).csv(in).randomSplit(ratioArray)
      case "json" => spark.read.json(in).randomSplit(ratioArray)
      case "xml" => { val conf = new Configuration
        conf.set(XMLInputFormat.START_TAG_KEY, "<page>")
        conf.set(XMLInputFormat.END_TAG_KEY, "</page>")
        spark.sparkContext
          .newAPIHadoopFile(in, classOf[XMLInputFormat], classOf[LongWritable], classOf[Text], conf)
          .map(_._2.toString()).toDS().randomSplit(ratioArray)
          }
      case _ => spark.read.textFile(in).randomSplit(ratioArray)
    }
    data1.write.csv(out1)
    data2.write.csv(out2)
  }
}