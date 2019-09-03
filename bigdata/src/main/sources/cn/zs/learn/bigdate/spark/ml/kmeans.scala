package cn.zs.learn.bigdate.spark.ml

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.hadoop.io.Text
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.mapred.JobConf
import edu.umd.cloud9.collection.XMLInputFormat
import org.apache.hadoop.mapred.InputFormat
import org.apache.hadoop.mapred.FileInputFormat
import org.apache.hadoop.fs.Path
import org.apache.spark.ml.feature.VectorAssembler
import java.util.Properties
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.OneHotEncoder
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.StandardScaler
import org.apache.spark.ml.clustering.KMeans

object Kmeans {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Test")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._

    // json数据处理

    //    val Array(data1,data2) = spark.read.option("inferSchema", true).
    //      option("header", false).
    //      csv("hdfs://10.11.100.102:9000/user/ds/kddcup.data").
    //      toDF(
    //        "duration", "protocol_type", "service", "flag",
    //        "src_bytes", "dst_bytes", "land", "wrong_fragment", "urgent",
    //        "hot", "num_failed_logins", "logged_in", "num_compromised",
    //        "root_shell", "su_attempted", "num_root", "num_file_creations",
    //        "num_shells", "num_access_files", "num_outbound_cmds",
    //        "is_host_login", "is_guest_login", "count", "srv_count",
    //        "serror_rate", "srv_serror_rate", "rerror_rate", "srv_rerror_rate",
    //        "same_srv_rate", "diff_srv_rate", "srv_diff_host_rate",
    //        "dst_host_count", "dst_host_srv_count",
    //        "dst_host_same_srv_rate", "dst_host_diff_srv_rate",
    //        "dst_host_same_src_port_rate", "dst_host_srv_diff_host_rate",
    //        "dst_host_serror_rate", "dst_host_srv_serror_rate",
    //        "dst_host_rerror_rate", "dst_host_srv_rerror_rate",
    //        "label")
    //        .randomSplit(Array(0.8,0.2))
    //    data1.show()
    //    data2.show()
    //    data2.rdd.saveAsTextFile("file:/home/shuai/Desktop/haha")
    //    data2.write.csv("file:/home/shuai/Desktop/hehe.csv")
    //    val dataRead = spark.read.csv("file:/home/shuai/Desktop/hehe.csv")
    //    dataRead.printSchema()

    // xml数据处理 hadoop舊api
    //    val jobConf = new JobConf()
    //    jobConf.set("stream.recordreader.class", "org.apache.hadoop.streaming.StreamXmlRecordReader")
    //    jobConf.set("stream.recordreader.begin", "<page>")
    //    jobConf.set("stream.recordreader.end", "</page>")
    //    FileInputFormat.addInputPath(jobConf, new Path("hdfs://10.11.100.102:9000/user/ds/wikipedia.xml"))
    //    sc.hadoopRDD(jobConf, classOf[StreamInputFormat], classOf[Text], classOf[Text], 503).map(f => (f._1.toString,f._2.toString)).toDS().show
    //    sc.hadoopFile("hdfs://10.11.100.102:9000/user/ds/wikipedia.xml", classOf[StreamInputFormat], classOf[Text], classOf[Text], 503)

    //    val hadoopConf = new Configuration
    //    hadoopConf.set(XMLInputFormat.START_TAG_KEY, "<page>");
    //    hadoopConf.set(XMLInputFormat.END_TAG_KEY, "</page>");
    //    val xmlData = spark.sparkContext
    //      .newAPIHadoopFile("hdfs://10.11.100.138:9000/wiki.xml", classOf[XMLInputFormat], classOf[LongWritable], classOf[Text], hadoopConf)
    //      .map(f => (f._1.toString, f._2.toString))
    //      .toDS()
    //    xmlData.write.parquet("")
    //    val Array(xmlData1,xmlData2) = xmlData.randomSplit(Array(0.999,0.001))
    //    xmlData2.saveAsTextFile("hdfs://10.11.100.138:9000/out1")
    //    xmlData2.toDS().write.text("hdfs://10.11.100.138:9000/temp")
    //    spark.read.textFile("hdfs://10.11.100.138:9000/temp").show()

    //    println(spark.read.csv("hdfs://10.11.100.138:9000/csv1").count())
    //    println(spark.read.csv("hdfs://10.11.100.138:9000/csv2").count())
    //    println(spark.read.csv("hdfs://10.11.100.138:9000/kddcup/kddcup.data").count())

    // mysql數據讀取
    //    spark.read.format("jdbc").option("url", "jdbc:mysql://mysql:3306/test").option("user", "root").option("password", "111111").option("dbtable", "titanic_train").load().show()
    //    val pro = new Properties()
    //    pro.put("user", "root")
    //    pro.put("password", "111111")
    //    val url = "jdbc:mysql://mysql:3306/test"
    //    spark.read.jdbc(url, "haha", pro).show()

    val data = spark.read
      .option("inferSchema", true)
      .option("header", false)
      .csv("hdfs://10.11.100.138:9000/csv2")
//    val df = data.toDF(
//      "duration", "protocol_type", "service", "flag",
//      "src_bytes", "dst_bytes", "land", "wrong_fragment", "urgent",
//      "hot", "num_failed_logins", "logged_in", "num_compromised",
//      "root_shell", "su_attempted", "num_root", "num_file_creations",
//      "num_shells", "num_access_files", "num_outbound_cmds",
//      "is_host_login", "is_guest_login", "count", "srv_count",
//      "serror_rate", "srv_serror_rate", "rerror_rate", "srv_rerror_rate",
//      "same_srv_rate", "diff_srv_rate", "srv_diff_host_rate",
//      "dst_host_count", "dst_host_srv_count",
//      "dst_host_same_srv_rate", "dst_host_diff_srv_rate",
//      "dst_host_same_src_port_rate", "dst_host_srv_diff_host_rate",
//      "dst_host_serror_rate", "dst_host_srv_serror_rate",
//      "dst_host_rerror_rate", "dst_host_srv_rerror_rate",
//      "label")
      
    val input1 = "2,3,4"
    val colNames1 = for (col <- input1.split(",").map(_.toInt)) yield "_c"+(col-1)
    val stringIndexs = for( inputcol <- colNames1) yield new StringIndexer().setInputCol(inputcol).setOutputCol(inputcol+"_bak")
    val stringIndexPipeline = new Pipeline().setStages(stringIndexs)
    val selectCols = data.columns.map(f => if(colNames1.contains(f)) f+"_bak" else f)
    val stringIndexData = stringIndexPipeline.fit(data).transform(data).select(selectCols(0),selectCols.drop(1): _*)
    stringIndexData.show
    stringIndexPipeline.save("hdfs://10.11.100.138:9000/stringIndex")
    
    val pipeline =  Pipeline.load("hdfs://10.")
    
    val input2 = "2,3,4"
    val colNames2 = for (col <- input2.split(",").map(_.toInt)) yield "_c"+(col-1)+"_bak"
    val onehots = for( inputcol <- colNames2) yield new OneHotEncoder().setInputCol(inputcol).setOutputCol(inputcol+"_bak")
    val onehotPipeline = new Pipeline().setStages(onehots)
    val selectCols2 = stringIndexData.columns.map(f => if(colNames2.contains(f)) f+"_bak" else f)
    val onehotData = onehotPipeline.fit(stringIndexData).transform(stringIndexData).select(selectCols2(0),selectCols2.drop(1): _*)
    onehotData.show()

//    val input = if("42" == null) onehotData.columns else onehotData.columns.slice(from, until)
    val selectcol3 = onehotData.columns
    val vectorAssembler = new VectorAssembler()
      .setInputCols(onehotData.columns.slice(0, onehotData.columns.length - 1))
      .setOutputCol("featureVector")
    val vectorAssemblerData = vectorAssembler.transform(onehotData).select("featureVector")
    vectorAssemblerData.show()
//    vectorAssemblerData.write.
    
    val standardScaler = new StandardScaler()
      .setInputCol("featureVector")
      .setOutputCol("scaledFeatureVector")
      .setWithStd(true)
      .setWithMean(false)
   val standardScalerData = standardScaler.fit(vectorAssemblerData).transform(vectorAssemblerData).select("scaledFeatureVector")
   standardScalerData.show()
   
   (2 to 2 by 1).map(k => (k, new KMeans().setK(k).setMaxIter(20).setTol(1e-4).setFeaturesCol("scaledFeatureVector").setPredictionCol("predict")))
     .map{ case (k,altorithm) =>
       val model = altorithm.fit(standardScalerData)
       val cost = model.computeCost(standardScalerData)/standardScalerData.count()
       (k,model,cost)
       }.foreach(println)/*.sortBy(_._3).head*/
  }
}