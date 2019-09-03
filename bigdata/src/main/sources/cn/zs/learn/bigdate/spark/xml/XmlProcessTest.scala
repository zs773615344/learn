package cn.zs.learn.bigdate.spark.xml

import edu.umd.cloud9.collection.XMLInputFormat
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object XmlProcessTest{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Test")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
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
    //      .randomSplit(Array(0.8,0.2))
    //    data1.show()
    //    data2.show()
    //    data2.rdd.saveAsTextFile("file:/home/shuai/Desktop/haha")
    //      data2.write.csv("file:/home/shuai/Desktop/hehe.csv")
    //      data2.
//    val dataRead = spark.read.csv("file:/home/shuai/Desktop/hehe.csv")
//    dataRead.printSchema()

      // 使用hadoop旧api中StreamInputFormat    
//    val jobConf = new JobConf()
//    jobConf.set("stream.recordreader.class", "org.apache.hadoop.streaming.StreamXmlRecordReader")
//    jobConf.set("stream.recordreader.begin", "<page>")
//    jobConf.set("stream.recordreader.end", "</page>")
//    FileInputFormat.addInputPath(jobConf, new Path("hdfs://10.11.100.102:9000/user/ds/wikipedia.xml"))
//    spark.sparkContext.hadoopRDD(jobConf, classOf[StreamInputFormat], classOf[Text], classOf[Text], 503).map(f => (f._1.toString,f._2.toString)).toDS().show

    // 使用hadoop新api，需要导入cloud9-1.5.0.jar,使用的是XMLInputFormat
    val hadoopConf = new Configuration
    hadoopConf.set(XMLInputFormat.START_TAG_KEY, "<page>");
    hadoopConf.set(XMLInputFormat.END_TAG_KEY, "</page>");
    spark.sparkContext.newAPIHadoopFile("hdfs://10.11.100.102:9000/user/ds/wikipedia.xml", classOf[XMLInputFormat], classOf[LongWritable], classOf[Text], hadoopConf).map(f => (f._1.toString,f._2.toString)).toDS().show

  }
}