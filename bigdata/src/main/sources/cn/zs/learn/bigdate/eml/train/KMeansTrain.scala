package cn.zs.learn.bigdate.eml.train

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.clustering.KMeans
import org.apache.commons.lang3.StringUtils
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.clustering.KMeansModel

object KMeansTrain {
  def main(args: Array[String]): Unit = {

    // test
//    val args = Array("hdfs://10.11.100.138:9000/filesplit1", "hdfs://10.11.100.138:9000/filesplit2",
//      "hdfs://10.11.100.138:9000/standardScalerPipelineModel", "scalerFeatureVectors", "predict", "2", "5", "1", "10", "", "hdfs://10.11.100.138:9000/pipelineModel")

    val trainDataPath = args(0)
    val testDataPath = args(1)
    val inputModelPath = args(2)
    val inputCol = args(3)
    val outputCol = args(4)
    
    if (StringUtils.isBlank(inputCol) || StringUtils.isBlank(outputCol))
      throw new Exception("无效参数")

    // 默认k=2
    val (kMin, kMax) = if (StringUtils.isBlank(args(5)) || StringUtils.isBlank(args(6)) || args(5).toInt >= args(6).toInt) (2, 2) 
      else (args(5).toInt, args(6).toInt)
    val stepSize = if (StringUtils.isBlank(args(7)) || args(7).toInt < 2) 1 
      else args(7).toInt
    // 默认maxIterations=20
    val maxIterations = if (StringUtils.isBlank(args(8))) 20 
      else args(8).toInt
    // 默认为tol=1e-4
    val tol = if (StringUtils.isBlank(args(9))) 1e-4 
      else args(9).toDouble

    val outputModelPath = args(10)

    val conf = new SparkConf()/*.setMaster("local[*]")*/.setAppName("KMeansTrain")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    
    val trainData = spark.read.option("inferSchema", true).option("header", false).csv(trainDataPath)
    val testData = spark.read.option("inferSchema", true).option("header", false).csv(testDataPath)
    
    val (inputPipeline, testDataProcess ) = if (StringUtils.isEmpty(inputModelPath)) (Array(),testData) 
    else {
    	val inputPipelineModel = PipelineModel.load(inputModelPath)
    			(Array(inputPipelineModel),inputPipelineModel.transform(testData))
    }
    
    val (k, pipelineModel,model, cost) = (kMin to kMax by stepSize)
      .map(k => (k, new KMeans().setK(k).setMaxIter(maxIterations)
          .setTol(tol).setFeaturesCol(inputCol).setPredictionCol(outputCol)))
      .map {
        case (k, algorithm) =>
          val pipeline = new Pipeline().setStages(inputPipeline ++ Array(algorithm))
          val pipelineModel = pipeline.fit(trainData)
          val model = pipelineModel.stages.last.asInstanceOf[KMeansModel]
          val cost = model.computeCost(testDataProcess) / testDataProcess.count()
          (k, pipelineModel,model, cost)
      }.sortBy(_._4).head
    model.clusterCenters.foreach(println)
    println((k,cost))
    pipelineModel.write.overwrite().save(outputModelPath)
  }
}