package cn.zs.learn.bigdate.eml.transform

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StandardScaler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.feature.StandardScalerModel
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.PipelineModel
import org.apache.commons.lang3.StringUtils

object TransStandardScaler {
  def main(args: Array[String]): Unit = {
    // test
//    val args=Array("hdfs://10.11.100.138:9000/kddcup/kddcup.data","hdfs://10.11.100.138:9000/vectorAssemblerPipelineModel","featureVectors","scalerFeatureVectors","hdfs://10.11.100.138:9000/standardScalerPipelineData","hdfs://10.11.100.138:9000/standardScalerPipelineModel")
    
    val inputDataPath = args(0)
    val inputModelPath = args(1)
    val inputCol = args(2)
    val outputCol = args(3)
    val outputDataPath = args(4)
    val outputModelPath = args(5)
    
    val conf = new SparkConf()/*.setMaster("local[*]")*/.setAppName("TransStandardScaler")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    val inputData = spark.read.option("inferSchema", true).option("header", false).csv(inputDataPath)
    
    if (StringUtils.isBlank(inputCol) || StringUtils.isBlank(outputCol))
      throw new Exception("无效参数")
    
    val standardScaler = new StandardScaler()
      .setWithStd(true)
      .setWithMean(false)
      .setInputCol(inputCol)
      .setOutputCol(outputCol)
      
    val standardScalerPipeline = if(StringUtils.isBlank(inputModelPath)) new Pipeline().setStages(Array(standardScaler)) 
      else new Pipeline().setStages(Array(PipelineModel.load(inputModelPath),standardScaler))
    
    val standardScalerPipelineModel = standardScalerPipeline.fit(inputData)
    val standardScalerPipelineData = standardScalerPipelineModel.transform(inputData)
    standardScalerPipelineData.show()
    
//    standardScalerPipelineData.write.csv(outputDataPath)
    standardScalerPipelineData.rdd.saveAsTextFile(outputDataPath)
    standardScalerPipelineModel.write.overwrite().save(outputModelPath)
  }
}