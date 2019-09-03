package cn.zs.learn.bigdate.algorithm.preprocess

import org.apache.spark.sql.DataFrame
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.feature.StandardScaler
import scala.collection.mutable.ArrayBuffer
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.Encoders

object DFToSvm {
  def dfToSvm(df: DataFrame, labelField: String, labelBoolean: Boolean, nonNumFeatureFields: Array[String]): RDD[LabeledPoint] = {
    val numFeatureFields = df.columns.filterNot((Array(labelField) ++ nonNumFeatureFields).contains(_))
    val nonNumLabelMap = df.select(labelField).map(_.get(0).toString())(Encoders.STRING).distinct().collect().zipWithIndex.toMap
    val nonNumFeatureFieldAndMapArrays = nonNumFeatureFields.map(nonNumFeature =>
      (nonNumFeature, df.select(nonNumFeature).map(_.get(0).toString())(Encoders.STRING).distinct().collect().zipWithIndex.toMap))

    val labeledPointRDD = df.map { f =>
      val labelNum = if (labelBoolean) nonNumLabelMap(f.getAs(labelField)) else f.getAs[String](labelField).toDouble
      val numFeaturesArray = numFeatureFields.map(f.getAs[String](_).toDouble)
      val nonNumFeaturesArray = new ArrayBuffer[Double]();
      nonNumFeatureFieldAndMapArrays.foreach { nonNumFeatureFieldAndMapArray =>
        val nonNumFeatureField = nonNumFeatureFieldAndMapArray._1
        val nonNumFeatureMap = nonNumFeatureFieldAndMapArray._2
        val nonNumFeatureArray = Array.ofDim[Double](nonNumFeatureMap.size)
        val nonNumFeatureIdx = nonNumFeatureMap(f.getAs(nonNumFeatureField))
        nonNumFeatureArray(nonNumFeatureIdx) = 1.0
        nonNumFeaturesArray.append(nonNumFeatureArray: _*)
      }
      LabeledPoint(labelNum, Vectors.dense(numFeaturesArray ++ nonNumFeaturesArray))
    }(Encoders.kryo[LabeledPoint]).rdd
    labeledPointRDD
  }

  def standard(labeledPointRDD: RDD[LabeledPoint]): RDD[LabeledPoint] = {
    val featuresData = labeledPointRDD.map(_.features)
    val stdScaled = new StandardScaler(true, true).fit(featuresData)
    val scaledRDD = labeledPointRDD.map(f => LabeledPoint(f.label, stdScaled.transform(f.features)))
    scaledRDD
  }
}