package cn.zs.learn.bigdate.eml.test

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.clustering.KMeansModel
import org.apache.spark.ml.PipelineModel

object KMeansTest {
  def main(args: Array[String]): Unit = {
    
    // test
//    val args = Array("hdfs://10.11.100.138:9000/pipelineModel","hdfs://10.11.100.138:9000/filesplit1")
    
    val modePath = args(0)
    val dataPath = args(1)
    
    val conf = new SparkConf()/*.setMaster("local[*]")*/.setAppName("KMeansTest")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    
    val data = spark.read.option("inferSchema", true).option("header", false).csv(dataPath)
    val model =  PipelineModel.load(modePath)
    val kmeanModel = model.stages.last.asInstanceOf[KMeansModel]
    println(kmeanModel.computeCost(model.stages(model.stages.length-2).transform(data))/data.count())
  }
}