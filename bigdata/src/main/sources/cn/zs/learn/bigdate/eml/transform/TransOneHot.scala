package cn.zs.learn.bigdate.eml.transform

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.OneHotEncoder
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.PipelineModel
import org.apache.commons.lang3.StringUtils

object TransOneHot {
  def main(args: Array[String]): Unit = {
    // test
//    val args=Array("hdfs://10.11.100.138:9000/kddcup/kddcup.data","hdfs://10.11.100.138:9000/stringIndexerPipelineModel","_c1_1,_c2_2,_c3_3","_c1_11,_c2_22,_c3_33","hdfs://10.11.100.138:9000/onehotPipelineData","hdfs://10.11.100.138:9000/onehotPipelineModel")
    
    val inputDataPath = args(0)
    val inputModelPath = args(1)
    val inputCols = args(2)
    val outputCols = args(3)
    val outputDataPath = args(4)
    val outputModelPath = args(5)
    
    val conf = new SparkConf()/*.setMaster("local[*]")*/.setAppName("OneHot")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    
    val inputData = spark.read.option("inferSchema", true).option("header", false).csv(inputDataPath)
    
    if(StringUtils.isBlank(inputCols) || StringUtils.isBlank(outputCols))
        throw new Exception("无效参数")
    
    val inputColsArr = inputCols.split(",")
    val outputColsArr = outputCols.split(",")
    if(inputColsArr.length != outputColsArr.length)
        throw new Exception("无效参数")
    
    val colsMap = inputColsArr.zip(outputColsArr).toMap
    val onehotsArray = for( k <- colsMap.keySet) yield new OneHotEncoder().setInputCol(k).setOutputCol(colsMap(k))
    
    val onehotPipeline = if(StringUtils.isBlank(inputModelPath)) new Pipeline().setStages(onehotsArray.toArray) else new Pipeline().setStages(Array(PipelineModel.load(inputModelPath)) ++ onehotsArray)
    
    val onehotPipelineModel = onehotPipeline.fit(inputData)
    val onehotPipelineData = onehotPipelineModel.transform(inputData)
    onehotPipelineData.show()
    
//    onehotPipelineData.write.csv(outputDataPath)
    onehotPipelineData.rdd.saveAsTextFile(outputDataPath)
    onehotPipelineModel.write.overwrite().save(outputModelPath)
    
  }
}