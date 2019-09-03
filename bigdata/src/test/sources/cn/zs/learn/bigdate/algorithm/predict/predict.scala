package cn.zs.learn.bigdate.algorithm.predict

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.SVMModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel

object predict {
  def loadModel(sc: SparkContext, predictData: Array[Array[Double]], modelName: String, path: String) {
    modelName match {
      case "SVM" => predictData.map(f => SVMModel.load(sc, path).predict(Vectors.dense(f))).foreach(f => println("预测结果" + f))
      case "svm" => predictData.map(f => SVMModel.load(sc, path).predict(Vectors.dense(f))).foreach(f => println("预测结果" + f))
      case "ALS" => predictData.map(f => MatrixFactorizationModel.load(sc, path).predict(f(0).toInt, f(1).toInt)).foreach(f => println("预测结果" + f))
      case "als" => predictData.map(f => MatrixFactorizationModel.load(sc, path).predict(f(0).toInt, f(1).toInt)).foreach(f => println("预测结果" + f))
      case _     => println("暂无此算法")
    }
  }
}