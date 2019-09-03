package cn.zs.learn.bigdate.eml.transform

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.PipelineModel
import org.apache.commons.lang3.StringUtils

object TransVectorAssembler {
  def main(args: Array[String]): Unit = {
    //test
//        val args=Array("hdfs://10.11.100.138:9000/kddcup/kddcup.data","hdfs://10.11.100.138:9000/onehotPipelineModel","_c1_11,_c2_22,_c3_33","_c1,_c2,_c3,_c41,_c1_1,_c2_2,_c3_3","featureVectors","hdfs://10.11.100.138:9000/vectorAssemblerPipelineData","hdfs://10.11.100.138:9000/vectorAssemblerPipelineModel")

    val inputDataPath = args(0)
    val inputModelPath = args(1)
    val inputAddCols = args(2)
    val inputDelCols = args(3)
    val outputCols = args(4)
    val outputDataPath = args(5)
    val outputModelPath = args(6)

    val conf = new SparkConf()/*.setMaster("local[*]")*/.setAppName("VectorAssembler")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._

    val inputData = spark.read.option("inferSchema", true).option("header", false).csv(inputDataPath)

    val selectColsArr = if (StringUtils.isBlank(inputAddCols)) {
      if (StringUtils.isBlank(inputDelCols))
        Set(inputData.columns: _*)
      else
        Set(inputData.columns: _*) -- Set(inputDelCols.split(","): _*)
    } else {
      if (StringUtils.isBlank(inputDelCols))
        Set(inputData.columns: _*) ++ Set(inputAddCols.split(","): _*)
      else
        Set(inputData.columns: _*) ++ Set(inputAddCols.split(","): _*) -- Set(inputDelCols.split(","): _*)
    }

    if (StringUtils.isBlank(outputCols))
      throw new Exception("无效参数")

    val vectorAssembler = new VectorAssembler()
      .setInputCols(selectColsArr.toArray)
      .setOutputCol(outputCols)

    val vectorAssemblerPipeline = if (StringUtils.isBlank(inputModelPath)) new Pipeline().setStages(Array(vectorAssembler))
      else new Pipeline().setStages(Array(PipelineModel.load(inputModelPath), vectorAssembler))

    val vectorAssemblerPipelineModel = vectorAssemblerPipeline.fit(inputData)
    val vectorAssemblerPipelineData = vectorAssemblerPipelineModel.transform(inputData)
    vectorAssemblerPipelineData.show()

    //    vectorAssemblerPipelineData.write.csv(outputDataPath)
    vectorAssemblerPipelineData.rdd.saveAsTextFile(outputDataPath)
    vectorAssemblerPipelineModel.write.overwrite().save(outputModelPath)
  }
}