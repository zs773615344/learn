package cn.zs.learn.bigdate.spark
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCount {
  def main(args: Array[String]) {
    //    println("haha")
    val conf = new SparkConf().setAppName("print")
      //      .setMaster("spark://192.168.1.224:7077")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("hdfs://192.168.1.224:9000/haha/haha")
    //    println(textFile.count())
    //    println(textFile.first())
    //    textFile.take(2).foreach(println)
    //    textFile.takeOrdered(3).foreach(println)
    //    println(textFile.takeOrdered(3)(Ordering[String].reverse).foreach(println))
    textFile.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).foreach(println)
    //     .flatMap(f=>f.split(" "))
    //     .map(x=>(x,1))
    //     .reduceByKey((x,y)=>x+y)
    textFile.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)/*.map{
      case  (k:String,v:Int) => (v,k)
    }*/.map(f => (f._2, f._1)).sortByKey(false).top(10);

  }
}