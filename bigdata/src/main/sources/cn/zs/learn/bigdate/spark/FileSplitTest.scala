package cn.zs.learn.bigdate.spark

import org.apache.spark.sql.SparkSession
import edu.umd.cloud9.collection.XMLInputFormat
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.spark.annotation.InterfaceStability

object FileSplitTest {
  def main(args: Array[String]): Unit = {
    val in = args(0)
    val textType = args(1)
    val ratio = args(2).toDouble
    val ratioArray = Array(ratio,1-ratio)
    val out1 = args(3)
    val out2 = args(4)
    val spark = SparkSession.builder().appName("FileSplit").master("local[*]").getOrCreate()
    import spark.implicits._
   
    textType match {
      case "parqut" => {val Array(data1, data2) = spark.read.parquet(in).randomSplit(ratioArray)
        data1.write.parquet(out1)
        data2.write.parquet(out2)
        }
      case "csv" => {val Array(data1, data2) = spark.read.csv(in).randomSplit(ratioArray)
        data1.write.csv(out1)
        data2.write.csv(out2)
        }
      case "json" => {val Array(data1, data2) = spark.read.json(in).randomSplit(ratioArray)
        data1.write.json(out1)
        data2.write.json(out2)
        }
      case "xml" => { val conf = new Configuration
        conf.set(XMLInputFormat.START_TAG_KEY, "<page>")
        conf.set(XMLInputFormat.END_TAG_KEY, "</page>")
        val Array(data1, data2) = spark.sparkContext
          .newAPIHadoopFile(in, classOf[XMLInputFormat], classOf[LongWritable], classOf[Text], conf)
          .map(_._2.toString()).toDS().randomSplit(ratioArray)
        data1.write.text(out1)
        data2.write.text(out2)
          }
      case _ => {val Array(data1, data2) = spark.read.textFile(in).randomSplit(ratioArray)
        data1.write.text(out1)
        data2.write.text(out2)
        }

    }
    
  }
}