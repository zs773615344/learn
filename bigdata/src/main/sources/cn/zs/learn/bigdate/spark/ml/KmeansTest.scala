package cn.zs.learn.bigdate.spark.ml

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.OneHotEncoder
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StandardScaler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.util.MLReader


object KmeansTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Test")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._

    val data = spark.read
      .option("inferSchema", true)
      .option("header", false)
      .csv("hdfs://10.11.100.138:9000/csv2")

    val input1 = "2,3,4"
    val outputcol1 = "2_bak,3_bak,4_bak".split(",")
    val colNames1 = for (col <- input1.split(",").map(_.toInt)) yield "_c" + (col - 1)
    val colMap = colNames1.zip(outputcol1).toMap
    val stringIndexs = for (k <- colMap.keySet) yield new StringIndexer().setInputCol(k).setOutputCol(colMap(k))
    val stringIndexPipeline = new Pipeline().setStages(stringIndexs.toArray)
    val stringIndexPipelineModel = stringIndexPipeline.fit(data)
    val stringIndexData = stringIndexPipelineModel.transform(data)
    stringIndexData.show
    stringIndexPipeline.write.overwrite().save("hdfs://10.11.100.138:9000/stringIndexPipeline")
    stringIndexData.write.csv("hdfs://10.11.100.138:9000/stringIndexData")
    stringIndexPipelineModel.write.overwrite().save("hdfs://10.11.100.138:9000/stringIndexPipelineModel")

    val inputPipeline1 = Pipeline.load("hdfs://10.11.100.138:9000/stringIndexPipeline")
    val inputPipelineModel1 = PipelineModel.load("hdfs://10.11.100.138:9000/stringIndexPipelineModel")
    val input2 = "2_bak,3_bak,4_bak".split(",")
    val outputcols2 = "2_bak2,3_bak2,4_bak2".split(",")
    val colMap2 = input2.zip(outputcols2).toMap
    val onehots = for (k <- colMap2.keySet) yield new OneHotEncoder().setInputCol(k).setOutputCol(colMap2(k))
//    val onehotPipeline = new Pipeline().setStages(Array(inputPipelineModel1)).setStages(onehots.toArray)
//    inputPipelineModel1.transform(data)
//    val onehotPipeline = new Pipeline().setStages(onehots.toArray)
//    val onehotData = onehotPipeline.fit(inputPipelineModel1.transform(data)).transform(inputPipelineModel1.transform(data))
    val onehotPipeline = new Pipeline().setStages(Array(inputPipelineModel1) ++ onehots)
    val onehotPipelineModel = onehotPipeline.fit(data)
    val onehotData = onehotPipelineModel.transform(data)
    onehotData.show()
    onehotPipeline.write.overwrite().save("hdfs://10.11.100.138:9000/onehotPipeline")
    onehotPipelineModel.write.overwrite().save("hdfs://10.11.100.138:9000/onehotPipelineModel")
    onehotData.write.csv("hdfs://10.11.100.138:9000/onehotData")

    val inputPipelineModel2 = PipelineModel.load("hdfs://10.11.100.138:9000/onehotPipelineModel")
    val inputcols3 = "_c41,_c1,_c2,_c3,2_bak,3_bak,4_bak"
    val outputcols3 = "featureVector"
    val vectorAssembler = new VectorAssembler()
      .setInputCols((Set(data.columns: _*) -- Set(inputcols3.split(","): _*)).toArray)
      .setOutputCol("featureVector")
    val vectorAssemblerPipeline = new Pipeline().setStages(Array(inputPipelineModel2,vectorAssembler))
    val vectorAssemblerPipelineModel = vectorAssemblerPipeline.fit(data)
    val vectorAssemblerData = vectorAssemblerPipelineModel.transform(data)
    vectorAssemblerData.show()
    vectorAssemblerData.write.csv("hdfs://10.11.100.138:9000/vectorAssemblerData")
    vectorAssemblerPipeline.write.overwrite().save("hdfs://10.11.100.138:9000/vectorAssemblerPipeline")
    vectorAssemblerPipelineModel.write.overwrite().save("hdfs://10.11.100.138:9000/vectorAssemblerPipelineModel")
    
    val inputPipelineModel3 = PipelineModel.load("hdfs://10.11.100.138:9000/vectorAssemblerPipelineModel")
    val inputcols = "featureVector"
    val outputcols = "scaledFeatureVector"
    val standardScaler = new StandardScaler()
      .setInputCol(inputcols)
      .setOutputCol(outputcols)
      .setWithStd(true)
      .setWithMean(false)
    val standardScalerPipeline = new Pipeline().setStages(Array(inputPipelineModel3,standardScaler))
    val standardScalerPipelineModel = standardScalerPipeline.fit(data)
    val standardScalerData = standardScalerPipelineModel.transform(data)
    standardScalerData.write.csv("hdfs://10.11.100.138:9000/standardScalerData")
    standardScalerPipeline.write.overwrite().save("hdfs://10.11.100.138:9000/standardScalerPipeline")
    standardScalerPipelineModel.write.overwrite().save("hdfs://10.11.100.138:9000/standardScalerPipelineModel")

    (2 to 2 by 1).map(k => (k, new KMeans().setK(k).setMaxIter(20).setTol(1e-4).setFeaturesCol("scaledFeatureVector").setPredictionCol("predict")))
      .map {
        case (k, altorithm) =>
          val model = altorithm.fit(standardScalerData)
          val cost = model.computeCost(standardScalerData) / standardScalerData.count()
          (k, model, cost)
      }.foreach(println) /*.sortBy(_._3).head*/
      
      
  }
}