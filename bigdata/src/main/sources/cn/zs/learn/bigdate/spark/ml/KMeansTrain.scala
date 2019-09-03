package cn.zs.learn.bigdate.spark.ml

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StandardScaler
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.clustering.KMeansModel
import org.apache.spark.annotation.InterfaceStability

object KMeansTrain {
  def main(args: Array[String]): Unit = {
    // test
    val args = Array("hdfs://10.11.100.138:9000/csv2", "2", "2", "1", "20", "1e-4", "")

    val spark = SparkSession.builder().appName("KMeansTrain").master("local[*]").getOrCreate()
    import spark.implicits._

    val in = args(0)

    // 默认k=2
    val (kMin, kMax) = if (args(1) == null || args(2) == null || args(1).toInt >= args(2).toInt) (2, 2) else (args(1).toInt, args(2).toInt)
    val stepSize = if (args(3) == null || args(3).toInt < 2) 1 else args(3).toInt
    // 默认maxIterations=20
    val maxIterations = if (args(4) == null) 20 else args(4).toInt
    // 默认为tol=1e-4
    val tol = if (args(5) == null) 1e-4 else args(5).toDouble
    val out = args(6)

    val data = spark.read.option("inferSchema", true).option("header", false).csv(in)
    val ds = data.drop("_c1", "_c2", "_c3").cache()
    val assembler = new VectorAssembler()
      .setInputCols(ds.columns.slice(0, ds.columns.length - 1))
      .setOutputCol("featureVector")
    val scaler = new StandardScaler()
      .setInputCol("featureVector")
      .setOutputCol("scaledFeatureVector")
      .setWithStd(true)
      .setWithMean(false)

    //    train
    val (k,pipelinemode,kmeansmode,cost) = (2 to 5 by 1).map(k => (k, new KMeans().setK(k).setMaxIter(maxIterations).setTol(tol).setFeaturesCol("scaledFeatureVector").setPredictionCol("predict")))
      .map { k =>
        val pipeline = new Pipeline().setStages(Array(assembler, scaler, k._2))
        val pipelineMode = pipeline.fit(ds)
        val kmeansModel = pipelineMode.stages.last.asInstanceOf[KMeansModel]
//        pipelineMode.transform(ds).select("_c41","predict").groupBy("predict", "_c41").count().orderBy($"predict", $"count".desc).show(25)
        (k._1,pipelineMode,kmeansModel, kmeansModel.computeCost(pipelineMode.transform(ds)) / ds.count())

      }/*.foreach(println)*/.sortBy(_._4).head
      
      // test
      val testcost = kmeansmode.computeCost(pipelinemode.transform(ds)) / ds.count()
      // predict
      val predict = kmeansmode.transform(pipelinemode.transform(ds))

  }
}