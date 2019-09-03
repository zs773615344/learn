package cn.zs.learn.bigdate.eml.transform

import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.Pipeline
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.PipelineModel
import org.apache.commons.lang3.StringUtils

object TransStringIndex {
  def main(args: Array[String]): Unit = {
    // test
//    val args=Array("hdfs://10.11.100.138:9000/kddcup/kddcup.data","","_c1,_c2,_c3","_c1_1,_c2_2,_c3_3","hdfs://10.11.100.138:9000/stringIndexerPipelineData","hdfs://10.11.100.138:9000/stringIndexerPipelineModel")
    
    val inputDataPath = args(0)
    val inputModelPath = args(1)
    val inputCols = args(2)
    val outputCols = args(3)
    val outputDataPath = args(4)
    val outputModelPath = args(5)
   
    val conf = new SparkConf()/*.setMaster("local[*]")*/.setAppName("StringIndex")
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
    val stringIndexerArray = for( k <- colsMap.keySet) yield new StringIndexer().setInputCol(k).setOutputCol(colsMap(k))
    
    val stringIndexerPipeline = if(StringUtils.isBlank(inputModelPath)) new Pipeline().setStages(stringIndexerArray.toArray)
      else new Pipeline().setStages(Array(PipelineModel.load(inputModelPath)) ++ stringIndexerArray)
    
    val stringIndexerPipelineModel = stringIndexerPipeline.fit(inputData)
    val stringIndexerPipelineData = stringIndexerPipelineModel.transform(inputData)
    stringIndexerPipelineData.show()
    
//    stringIndexerPipelineData.write.csv(outputDataPath)
    stringIndexerPipelineData.rdd.saveAsTextFile(outputDataPath)
    stringIndexerPipelineModel.write.overwrite().save(outputModelPath)
  }
}