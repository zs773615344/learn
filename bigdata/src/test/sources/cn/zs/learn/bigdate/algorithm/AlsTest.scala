package cn.zs.learn.bigdate.algorithm

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.ALS
import org.joda.time.{ Duration, DateTime }
import java.util.Properties
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object AlsTest {
  def main(Args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.OFF)
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("hdfs"))
    val sqlContext = new SQLContext(sc)
    //    val inputFile=sc.textFile("hdfs://192.168.1.224:8020/home/app/ml-latest-small/ratings.csv")
    //    val splitFile=inputFile.map(_.split(","))
    //    val firstLine=splitFile.first()
    //    val rest=splitFile.mapPartitionsWithIndex((index,f)=>if(index==0) f.drop(1) else f)
    //    val schema=StructType(firstLine.map(f=>StructField(f,StringType)))
    //    val df=sqlContext.createDataFrame(rest.map(f=>Row(f:_*)), schema)

    val url = "jdbc:mysql://192.168.1.224:3306/test"
    val pro = new Properties()
    pro.put("user", "root")
    pro.put("password", "password")
    val df = sqlContext.read.jdbc(url, "rating", pro)
    val fields = Array("userId", "movieId", "rating")
    val cols = for (f <- fields) yield df.col(f)
    val data = df.select(cols: _*).rdd.map(f => Rating(f.get(0).toString().toInt, f.get(1).toString().toInt, f.get(2).toString().toDouble))
    val Array(trainData, validationData) = data.randomSplit(Array(0.8, 0.2))
    val bestModel = evaluateAllParameter(trainData, validationData, Array(5, 10, 15, 20, 50, 100), Array(5, 10, 15, 20, 25), Array(0.05, 0.1, 1, 5, 10.0))
    println("haha")
  }
  def computeRmse(model: MatrixFactorizationModel, RatingRDD: RDD[Rating]): Double = {
    val num = RatingRDD.count()
    val predictedRDD = model.predict(RatingRDD.map(r => (r.user, r.product)))
    val predictedAndRatings = predictedRDD.map(p => ((p.user, p.product), p.rating)).join(RatingRDD.map(r => ((r.user, r.product), r.rating))).values
    math.sqrt(predictedAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / num)
  }
  def trainModel(trainData: RDD[Rating], validationData: RDD[Rating], rank: Int, iterations: Int, lambda: Double): (Double, Double) = {
    val startTime = new DateTime()
    val model = ALS.train(trainData, rank, iterations, lambda)
    val endTime = new DateTime()
    val Rmse = computeRmse(model, validationData)
    val duration = new Duration(startTime, endTime)
    println(f"训练参数：rank:$rank%3d,iterations:$iterations%.2f,lambda=$lambda%.2f 结果 Rmse=$Rmse%.2f" + "训练需要时间" + duration.getMillis + "毫秒")
    return (Rmse, duration.getStandardSeconds)
  }
  def evaluateAllParameter(trainData: RDD[Rating], validationData: RDD[Rating], rankArray: Array[Int] = Array(10), numIterationsArray: Array[Int] = Array(20), lambdaArray: Array[Double] = Array(0.1)): MatrixFactorizationModel = {
    val evaluations = for (rank <- rankArray; numIterations <- numIterationsArray; lambda <- lambdaArray) yield { val (rmse, time) = trainModel(trainData, validationData, rank, numIterations, lambda); (rank, numIterations, lambda, rmse) }
    val Eval = (evaluations.sortBy(_._4))
    val BestEval = Eval(0)
    println("最佳model参数：rank：" + BestEval._1 + ",iterations:" + BestEval._2 + ",lambda:" + BestEval._3)
    val bestModel = ALS.train(trainData, BestEval._1, BestEval._2, BestEval._3)
    return bestModel
  }

}