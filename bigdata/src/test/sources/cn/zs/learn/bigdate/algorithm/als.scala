package cn.zs.learn.bigdate.algorithm

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.joda.time.DateTime
import org.joda.time.Duration
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions


object als {
  def prepareData(sc:SparkContext,ratingsData:String,moviesData:String,splitArray:Array[Double]=Array(0.8,0.1,0.1)):(RDD[Rating],RDD[Rating],RDD[Rating],scala.collection.Map[Int,String])={
    println("开始读取用户评分数据……")
//    val sc=new SparkContext(new SparkConf())
    
    // ratingsData "file:/home/app/ASL/ml-latest-small/ratings.csv"
    val rawUserData=sc.textFile(ratingsData)
    val rawRatings=rawUserData.map(_.split(",").take(3)).filter{case Array(userId,movieId,rating)=>(!userId.equals("userId"))}
    val ratingsRDD=rawRatings.map{case Array(userId,movieId,rating)=>Rating(userId.toInt,movieId.toInt,rating.toDouble)}
    
    println("共计："+ratingsRDD.count().toString()+"条ratings")
    
    println("开始读取电影数据中……")
    
    //moviesData: "file:/home/app/ASL/ml-latest-small/movies.csv"
    val itemRDD=sc.textFile(moviesData)
    val moviesTitle=itemRDD.map(line=>line.split(",").take(2)).filter{case Array(movieId,title)=>(!movieId.equals("movieId"))}.map(array=>(array(0).toInt,array(1))).collectAsMap()
    val numRatings=ratingsRDD.count()
    val numUsers=ratingsRDD.map(_.user).distinct().count()
    val numMovies=ratingsRDD.map(_.product).distinct().count()
    
    println("共计：ratings："+numRatings+" user "+numUsers+" Movies "+numMovies)
    
    val Array(trainData,validationData,testData)=ratingsRDD.randomSplit(splitArray)
    
    println("trainData:"+trainData.count()+" validationData:"+validationData.count()+" testData:"+testData.count())
    
    return (trainData,validationData,testData,moviesTitle)
  }
  
  //计算RMSE(root mean squared error)
  def computeRmse(model:MatrixFactorizationModel,RatingRDD:RDD[Rating]):Double={
    val num=RatingRDD.count()
    val predictedRDD=model.predict(RatingRDD.map(r=>(r.user,r.product)))
    val predictedAndRatings=predictedRDD.map(p=>((p.user,p.product),p.rating)).join(RatingRDD.map(r=>((r.user,r.product),r.rating))).values
    math.sqrt(predictedAndRatings.map(x=>(x._1-x._2)*(x._1-x._2)).reduce(_+_)/num)    
  }
  
  //训练模型
  def trainModel(trainData:RDD[Rating],validationData:RDD[Rating],rank:Int,iterations:Int,lambda:Double):(Double,Double)={
    val startTime=new DateTime()
    val model=ALS.train(trainData, rank, iterations, lambda)
    val endTime=new DateTime()
    val Rmse=computeRmse(model,validationData)
    val duration=new Duration(startTime,endTime)
    println(f"训练参数：rank:$rank%3d,iterations:$iterations%.2f,lambda=$lambda%.2f 结果 Rmse=$Rmse%.2f"+"训练需要时间"+duration.getMillis+"毫秒")
    return (Rmse,duration.getStandardSeconds)
  }
  
  //评价rank参数  
  //评价numIterations参数
  //评估lambda参数  
  //所有参数交叉评估
  //eavluateAllParameter找出最佳的参数组合
  def evaluateAllParameter(trainData:RDD[Rating],validationData:RDD[Rating],rankArray:Array[Int]=Array(10),numIterationsArray:Array[Int]=Array(20),lambdaArray:Array[Double]=Array(0.1)):MatrixFactorizationModel={
		val evaluations=for(rank<-rankArray;numIterations<-numIterationsArray;lambda<-lambdaArray) yield{val (rmse,time)=trainModel(trainData,validationData,rank,numIterations,lambda);(rank,numIterations,lambda,rmse,time)}
		val Eval=(evaluations.sortBy(_._4).sortBy(_._5))
		val BestEval=Eval(0)
		println("最佳model参数：rank："+BestEval._1+",iterations:"+BestEval._2+",lambda:"+BestEval._3)
		val bestModel=ALS.train(trainData, BestEval._1, BestEval._2,BestEval._3)
    return bestModel
  }
  
  //测试阶段   为防止overfitting的问题：模型过度贴近trainData，而对testData评估时误差变大
  def testModel(bestmodel:MatrixFactorizationModel,testData:RDD[Rating])={
     val rmse=computeRmse(bestmodel, testData)
     println("测试最佳模型："+rmse) 
  }
  
//  //推荐阶段
//  def pridect(sc:SparkContext,bestmodel:MatrixFactorizationModel,predictData:String)={
//    
//  }
  
}