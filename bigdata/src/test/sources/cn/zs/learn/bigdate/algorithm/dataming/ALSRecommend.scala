package cn.zs.learn.bigdate.algorithm.dataming

import org.apache.spark.SparkContext
import org.apache.spark.sql.DataFrame
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.ALS
import org.joda.time.DateTime
import org.joda.time.Duration
import scala.collection.mutable.HashMap
import org.apache.spark.sql.Encoder
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder
import org.apache.spark.sql.SparkSession

object ALSRecommend {
  def trainModel(sc: SparkContext, df: DataFrame, splitArray: Array[Double] = Array(0.8, 0.1, 0.1), rankArray: Array[Int] = Array(10), numIterationsArray: Array[Int] = Array(10), lambdaArray: Array[Double] = Array(0.1)): (MatrixFactorizationModel, RDD[Rating]) = {
//    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Rating]
//    implicit val RatingEncoder: Encoder[Rating] = ExpressionEncoder()
//    val spark:SparkSession = null
//    import spark.implicits._
//    val data = df.map(f => Rating(f(0).toString().toInt, f(1).toString().toInt, f(2).toString().toDouble)).rdd
    val data = df.map(f => Rating(f(0).toString().toInt, f(1).toString().toInt, f(2).toString().toDouble))(org.apache.spark.sql.Encoders.kryo[Rating]).rdd
    val Array(trainData, validationData, testData) = data.randomSplit(splitArray);
    val evaluations = for (rank <- rankArray; numIterations <- numIterationsArray; lambda <- lambdaArray) yield {
      val (rmse, time) = trainModel(trainData, validationData, rank, numIterations, lambda);
      (rank, numIterations, lambda, rmse, time)
    }
    val eval = (evaluations.sortBy(_._4))
    val bestEval = eval(0)
    val bestModel = ALS.train(trainData, bestEval._1, bestEval._2, bestEval._3)
    val path = "/home/ALS/ALS_" + System.currentTimeMillis()
    bestModel.save(sc, path)
    val map = Map(("path", path))
    println(map)
    (bestModel, testData)
  }

  def testModel(bestModel: MatrixFactorizationModel, testData: RDD[Rating]) {
    val rmse = computeRmse(bestModel, testData)
    print("测试：结果 rmse=" + rmse + "\t")
    val map = Map(("rmse", rmse))
    print(map)
  }

  def pridect(bestModel: MatrixFactorizationModel, predictData: Array[Array[Double]]) {
    val map = new HashMap[String, Double]
    predictData.map(f => bestModel.predict(f(0).toInt, f(1).toInt)).foreach(map("predict")= _)
  }
  def trainModel(trainData: RDD[Rating], validationData: RDD[Rating], rank: Int, iterations: Int, lambda: Double): (Double, Double) = {
    val startTime = new DateTime()
    val model = ALS.train(trainData, rank, iterations, lambda)
    val endTime = new DateTime()
    val Rmse = computeRmse(model, validationData)
    val duration = new Duration(startTime, endTime)
    //		println(f"训练参数：rank:$rank%3d,iterations:$iterations%.2f,lambda=$lambda%.2f 结果 Rmse=$Rmse%.2f" + "训练需要时间" + duration.getMillis + "毫秒")
    return (Rmse, duration.getStandardSeconds)
  }

  def computeRmse(model: MatrixFactorizationModel, RatingRDD: RDD[Rating]): Double = {
    val num = RatingRDD.count()
    val predictedRDD = model.predict(RatingRDD.map(r => (r.user, r.product)))
    val predictedAndRatings = predictedRDD.map(p => ((p.user, p.product), p.rating)).join(RatingRDD.map(r => ((r.user, r.product), r.rating))).values
    math.sqrt(predictedAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / num)
  }

}