package cn.zs.learn.bigdate.algorithm.dataming

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.SVMModel
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel

object LoadModel {
  def loadSVM(sc: SparkContext, path: String): SVMModel = {
    val model = SVMModel.load(sc, path)
    val map = Map(("loadModel", "ok"))
    println(map)
    model
  }

  def loadALS(sc: SparkContext, path: String): MatrixFactorizationModel = {
    val model = MatrixFactorizationModel.load(sc, path)
    val map = Map(("loadModel", "ok"))
    print(map)
    model
  }
}