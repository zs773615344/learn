package cn.zs.learn.bigdate.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.DStream.toPairDStreamFunctions

object SparkStreamingTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test")
    //      .setMaster("local[2]")
    //    val sc = new SparkContext(conf)
    //    val file = sc.textFile("hdfs://10.11.100.137:9000/haha")
    //    file.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect().foreach(println)

    val ssc = new StreamingContext(conf, Seconds(1))

    // socker流，基于tcp连接，nc -lk 9999
        val lines = ssc.socketTextStream("localhost", 9999)
    //    val result = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    //    result.print()

    // flume源 流水线式推送方式，sparkstreaming设置接收器作为flume的avro代理，flume将数据推送到接收器
    //    val flumeStream = FlumeUtils.createStream(ssc,"192.168.1.245",4545)
    //    val result = flumeStream.map(f=>Charset.forName("utf-8").newDecoder().decode(f.event.getBody).toString()).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    //    result.print()

    // flume源 自定义接收器拉取，flume不直接将数据推送到sparkstreaming，而是推送到在自定义的flume_spark接受器,并且数据保持缓冲，sparkstreaming从接收器提取数据
    //    val  flumeStream = FlumeUtils.createPollingStream(ssc,"192.168.1.245",4545)
    //    val result = flumeStream.map(f=>Charset.forName("utf-8").newDecoder().decode(f.event.getBody).toString()).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    //    result.print()

    // kafka-0.8
    //    val kafkaStream = KafkaUtils.createStream(ssc, "10.11.100.130:2181,10.11.100.131:2181,10.11.100.132:2181", "java", Map("test1"->0))
    //    val result = kafkaStream.map(_.toString()).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    //    result.print()

    //     kafka-0.10
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "10.11.100.146:9092,10.11.100.147:9092,10.11.100.148:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean))
    val topics = Array("test")
    val kafkaStream = KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))
    //    val result = kafkaStream.map(f=>f.key()+"\t"+f.value())
    val result = kafkaStream.flatMap(_.value().split(" ")).map((_, 1)).reduceByKey(_ + _)
    result.print()

    ssc.start()
    ssc.awaitTermination()
  }
}