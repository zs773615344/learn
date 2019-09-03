package cn.zs.learn.bigdate.algorithm

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.regression.LabeledPoint
import org.joda.time.DateTime
import org.apache.spark.mllib.classification.SVMWithSGD
import org.joda.time.Duration
import org.apache.spark.mllib.classification.SVMModel
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics


object svm {
  def prepareData(sc:SparkContext,Path:String,splitArray:Array[Double]=Array(0.8,0.1,0.1)):(RDD[LabeledPoint],RDD[LabeledPoint],RDD[LabeledPoint])={
    println("开始读取数据……")
    val date=MLUtils.loadLibSVMFile(sc, Path)
    
    println("共计："+date.count().toString()+"条数据")
    val splits = date.randomSplit(splitArray)
    val trainData = splits(0).cache()
    val validationData = splits(1).cache()
    val testData=splits(2).cache()

    val numTrainData = trainData.count()
    val numValidationData=validationData.count()
    val numTestData = testData.count()
    println("trainData："+numTrainData+" validationData："+numValidationData+" testData："+numTestData)
    
    return (trainData,validationData,testData)
  }
  
  def trainModel(trainData:RDD[LabeledPoint],numIterations:Int,stepSize:Double,regParam:Double):(SVMModel,Double)={
    val startTime=new DateTime()
    val model=SVMWithSGD.train(trainData, numIterations,stepSize,regParam)
    val endTime=new DateTime()
    val duration=new Duration(startTime,endTime)
    
    return (model,duration.getMillis)
  }
  
  def evaluateModel(model:SVMModel,validationData:RDD[LabeledPoint]):(Double)={
    val scoreAndLabels=validationData.map{labelpoint=>
        val predict=model.predict(labelpoint.features)
        (predict,labelpoint.label)
      }
    val Metrics=new BinaryClassificationMetrics(scoreAndLabels)
    val AUC=Metrics.areaUnderROC()
    AUC
  }
  
  def evaluateAllParameter(trainData:RDD[LabeledPoint],validationData:RDD[LabeledPoint],numIterationsArray:Array[Int]=Array(100),stepSizeArray:Array[Double]=Array(1),regParamArray:Array[Double]=Array(1)):SVMModel={
    val evaluationsArray=for(numIterations<-numIterationsArray;stepSize<-stepSizeArray;regParam<-regParamArray) yield{
      val (model,time)=trainModel(trainData, numIterations, stepSize, regParam)
      val AUC=evaluateModel(model, validationData)
      println("训练参数：numIterations:"+numIterations+",stepSize:"+stepSize+",regParam:"+regParam+" 结果 AUC="+AUC+",训练需要时间"+time+"毫秒")
      (numIterations,stepSize,regParam,AUC,time)
    }
    val BestEval=(evaluationsArray.sortBy(_._4).sortBy(_._5).reverse)(0)
    println("调校后最佳参数：numIterations："+BestEval._1+" stepSize:"+BestEval._2+" regParam"+BestEval._3+" ,结果AUC="+BestEval._4)
    val (bestModel,time)=trainModel(trainData.union(validationData), BestEval._1,  BestEval._2, BestEval._3)
    
    bestModel
  }
  
  def testModel(bestmodel:SVMModel,testData:RDD[LabeledPoint])={
     val AUC=evaluateModel(bestmodel, testData)
     println("测试最佳模型AUC："+AUC)
  }
  
  def predict(sc:SparkContext,Path:String,bestmodel:SVMModel)={
    val predictData=MLUtils.loadLibSVMFile(sc, Path)
    
    val predict=predictData.map{labeledPoint=>
      val features=labeledPoint.features
      val label=labeledPoint.label
      val predict=bestmodel.predict(features)
      
//      println("预测后：features："+features+",label："+label.toInt+"predict："+predict.toInt)
      
        (labeledPoint.features,predict,label)
      }.foreach(f=>println("预测后：特征值："+f._1+",标签："+f._2)) 
   
  }
}