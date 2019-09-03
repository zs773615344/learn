package cn.zs.learn.bigdate.eml.predict

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.clustering.KMeansModel
import org.apache.commons.lang3.StringUtils
import org.apache.spark.ml.PipelineModel

object Predict {
  def main(args: Array[String]): Unit = {
    
    // test
//    val args = Array("hdfs://10.11.100.138:9000/pipelineModel","hdfs://10.11.100.138:9000/filesplit2","")
    
    val modePath = args(0)
    val inputDataPath = args(1)
    val outputDataPath = args(2)
    
    val conf = new SparkConf()/*.setMaster("local[*]")*/.setAppName("OneHot")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    
    val data = spark.read.option("inferSchema", true).option("header", false).csv(inputDataPath)
    val model =  PipelineModel.load(modePath)
    val predictData = model.transform(data)
    predictData.show(100)
    
    if(StringUtils.isNotBlank(outputDataPath)) 
//      predictData.write.csv(outputDataPath)
      predictData.rdd.saveAsTextFile(outputDataPath) 
  }
}