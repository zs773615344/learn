package cn.zs.learn.bigdate.algorithm.dataming

import org.apache.spark.mllib.classification.SVMModel
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import org.joda.time.DateTime
import org.apache.spark.SparkContext
import org.apache.spark.sql.DataFrame
import org.apache.spark.mllib.classification.SVMWithSGD
import org.joda.time.Duration
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import scala.collection.mutable.HashMap
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.feature.StandardScaler
import org.apache.spark.mllib.feature.StandardScaler

object SVM {

  def trainModel(sc: SparkContext, df: DataFrame, randomSplit: Array[Double], numIterationsArray: Array[Int], stepSizeArray: Array[Double], regParamArray: Array[Double]): (SVMModel, RDD[LabeledPoint]) = {
    val length = df.columns.length
    val labeledPointRDD = df.map { f =>
      val featureArray = (1 to length).map(f.get(_).toString().toDouble)
      LabeledPoint(f.get(0).toString().toDouble, Vectors.dense(featureArray.toArray[Double]))
    }(org.apache.spark.sql.Encoders.kryo[LabeledPoint]).rdd
    val featuresData = labeledPointRDD.map(_.features)
    val stdScaled = new StandardScaler(true, true).fit(featuresData);
    val scaledRDD = labeledPointRDD.map(f => LabeledPoint(f.label, stdScaled.transform(f.features)))
    val Array(trainData, validationData, testData) = scaledRDD.randomSplit(Array(0.8, 0.1, 0.1))
    val evaluationArray = for (numIterations <- numIterationsArray; stepSize <- stepSizeArray; regParam <- regParamArray) yield {
      val (model, time) = trainModel(trainData, numIterations, stepSize, regParam)
      val AUC = evaluateModel(model, validationData)
      (numIterations, stepSize, regParam, AUC, time)
    }
    val bestEval = evaluationArray.sortBy(_._4).reverse(0)
    val (bestModel, time) = trainModel(trainData.union(validationData), bestEval._1, bestEval._2, bestEval._3)
    val path = "/home/SVMModel/SVMModel" + System.currentTimeMillis()
    bestModel.save(sc, path)
    val map = Map(("path", path))
    println(map)
    (bestModel, testData)
  }

  def testModel(bestModel: SVMModel, testData: RDD[LabeledPoint]) {
    val AUC = evaluateModel(bestModel, testData)
    val map = Map(("AUC", AUC))
    print(map)
  }

  def trainModel(trainData: RDD[LabeledPoint], numIterations: Int, stepSize: Double, regParam: Double): (SVMModel, Double) = {
    val startTime = new DateTime();
    val model = SVMWithSGD.train(trainData, numIterations, stepSize, regParam)
    val endTime = new DateTime();
    val duration = new Duration(startTime, endTime)
    (model, duration.getMillis)
  }

  def predict(bestModel: SVMModel, predictData: Array[Array[Double]]) {
    val map = new HashMap[String, Double]
    predictData.map(f => bestModel.predict(Vectors.dense(f))).foreach(map("predict")= _)
    println(map)
  }

  def evaluateModel(model: SVMModel, validationData: RDD[LabeledPoint]): Double = {
    val scoreAndLabels = validationData.map { labelpoint =>
      val predict = model.predict(labelpoint.features)
      (predict, labelpoint.label)
    }
    val metrics = new BinaryClassificationMetrics(scoreAndLabels)
    val AUC = metrics.areaUnderROC()
    AUC
  }
}