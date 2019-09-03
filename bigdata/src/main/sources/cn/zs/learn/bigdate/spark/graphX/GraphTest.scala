package cn.zs.learn.bigdate.spark.graphX

import org.apache.spark.sql.SparkSession
import org.apache.hadoop.conf.Configuration
import edu.umd.cloud9.collection.XMLInputFormat
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import scala.xml.XML
import java.security.MessageDigest
import java.nio.charset.StandardCharsets
import org.apache.spark.sql.Row
import org.apache.spark.sql.Dataset
import org.apache.spark.graphx.Edge
import org.apache.spark.graphx.Graph
import org.apache.spark.sql.functions._
import org.apache.spark.graphx.VertexRDD
import org.apache.spark.graphx.VertexId
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.spark.graphx.Graph.graphToGraphOps
import org.apache.spark.rdd.RDD.doubleRDDToDoubleRDDFunctions
import org.apache.spark.rdd.RDD.numericRDDToDoubleRDDFunctions
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx.EdgeTriplet

object GraphTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("graphTest").master("local[*]").getOrCreate()
    import spark.implicits._

    val medlineRaw = loadXML(spark, "hdfs://10.11.100.102:9000/user/ds/medline")

    val medline = medlineRaw.map(getAvildInfo).cache()
    //    println(medline.count())

    val topics = medline.flatMap(f => f).toDF("topic")
    //    println(topics.count())
    topics.createOrReplaceTempView("topics")

    val topicDist = spark.sql("SELECT topic, COUNT(*) cnt FROM topics GROUP BY topic ORDER BY cnt DESC")
    topicDist.show()
    topicDist.createOrReplaceTempView("topic_dist")
    //    spark.sql("SELECT cnt, COUNT(*) dist FROM topic_dist GROUP BY cnt ORDER BY dist DESC LIMIT 10").show()

    val topicPairs = medline.flatMap(_.sorted.combinations(2)).toDF("pairs")
    topicPairs.createOrReplaceTempView("topic_pairs")

    val cooccurs = spark.sql("SELECT pairs, COUNT(*) cnt FROM topic_pairs GROUP BY pairs")
    cooccurs.cache()
    cooccurs.createOrReplaceTempView("cooccurs")
    //    println("Number of unique co-occurrence pairs: " + cooccurs.count())
    //    spark.sql("SELECT pairs, cnt FROM cooccurs ORDER BY cnt DESC LIMIT 10").show()

    /*
     * 构造图的顶点的集合
     * 构造图的顶点所需的VertexRDD[VD]实际是RDD[(VertexId,VD)]特殊实现。
    * VertexId是Long类型，是顶点的标识
    * VD可以是任意类型，是顶点属性
   */
    val verticesRDD = topics.map { case Row(topic: String) => (getMD5(topic), topic) }

    // map可以接受偏函数
    //    topics.map(getVertices)

    val vertices = verticesRDD.toDF("md5", "topic")
    vertices.createOrReplaceTempView("vertices")
    //    spark.sql("select md5 from vertices group by md5").count == spark.sql("select topic from vertices group by topic").count()
    //    spark.sql("select count(*) from (select md5 from vertices group by md5) md5 ")

    /*
     * 构造图的边的集合
     * 构造图的边所需的EdgeRDD[ED]是RDD[Edge[ED]]的特殊实现
     * Edge是包含两个VertexId和一个ED类型的边属性,ED为范型
    */
    val edge = cooccurs.map {
      case Row(topics: Seq[_], cnt: Long) =>
        val ids = topics.map(_.toString()).map(getMD5).sorted
        Edge(ids(0), ids(1), cnt)
    }

    // 构造图 图的类型graph[VD,ED],VD为顶点的属性，ED为边的属性
    val topicGraph = Graph(verticesRDD.rdd, edge.rdd)
    topicGraph.cache()

    /*
     * 获取图的连通子图
     * 连通子图的顶点集合为VertexRDD[VertexId]中VertexId实际上是连通图的标示符
     * 集合实际是RDD[vertexId,componentId]
    */
    val connectedComponentGraph = topicGraph.connectedComponents()

    /*
     * VertexRDD提供了innerJoin转换,innerJoin需要提供一个函数,该函数的输入为VertexID和两个VertexRDD的内部数据,即VD
     * 函数的返回值是一个新的VertexRDD.
    */
    val topicComponentDF = topicGraph.vertices.innerJoin(
      connectedComponentGraph.vertices) {
        (topicId, name, componentId) => (name, componentId.toLong)
      }.values.toDF("topic", "cid")
    topicComponentDF.createOrReplaceTempView("topicComponent")
    spark.sql("select count(topic) cnt,cid from topicComponent group by cid order by cnt desc ").show
    topicComponentDF.where("cid = -8193948242717911820").show()

    val campy = spark.sql("SELECT * FROM topic_dist WHERE topic LIKE '%ampylobacter%'")
    campy.show()

    // 获取顶点的度的分布 VertexRDD[Int]实际上是RDD[(VertexId,Int)],int是顶点VertexId的度
    val degrees: VertexRDD[Int] = topicGraph.degrees.cache()
    degrees.map(_._2).stats()
    degrees.innerJoin(topicGraph.vertices) {
      (topicId, degree, name) => (name, degree.toInt)
    }.values.toDF("topic", "degree").orderBy(desc("degree")).show()

    // 过滤噪声边
    val T = medline.count()
    val topicDistRdd = topicDist.map { case Row(topic: String, cnt: Long) => (getMD5(topic), cnt) }.rdd
    val topicDistGraph = Graph(topicDistRdd, topicGraph.edges)

    // mapTriplets将原图包装成数据结构EdgeTriplet[VD,ED],该数据结构将顶点和边的属性连通两个顶点的ID一起包装进一个对象
    // 计算卡方统计量
    val chiSquaredGraph = topicDistGraph.mapTriplets(triplet =>
      chiSq(triplet.attr, triplet.srcAttr, triplet.dstAttr, T))
    chiSquaredGraph.edges.map(x => x.attr).stats()

    // 期望卡方指标的值服从自由度为1的卡方分布,卡方分布的99.999百分位数大约为19.5
    // 过滤低于19.5的,留下置信度非常高的有意义的伴生关系
    val interesting = chiSquaredGraph.subgraph(triplet => triplet.attr > 19.5)
    interesting.edges.count()

    // 分析过滤后的图
    val interestingComponentGraph = interesting.connectedComponents()
    val icDF = interestingComponentGraph.vertices.toDF("vid", "cid")
    val icCountDF = icDF.groupBy("cid").count()
    icCountDF.count()
    icCountDF.orderBy(desc("count")).show()

    val interestingDegrees = interesting.degrees.cache()
    interestingDegrees.map(_._2).stats()
    interestingDegrees.innerJoin(topicGraph.vertices) {
      (topicId, degree, name) => (name, degree)
    }.toDF("topic", "degree").orderBy(desc("degree")).show()

    // 系与聚类系数
    val avgCC = avgClusteringCoef(interesting)
    
    // 用pergel计算平均路径长度
    val paths = samplePathLengths(interesting)
    paths.map(_._3).filter(_ > 0).stats()

    val hist = paths.map(_._3).countByValue()
    hist.toSeq.sorted.foreach(println)

  }

  def samplePathLengths[V, E](graph: Graph[V, E], fraction: Double = 0.02): RDD[(VertexId, VertexId, Int)] = {
    val replacement = false
    val sample = graph.vertices.map(v => v._1).sample(
      replacement, fraction, 1729L)
    val ids = sample.collect().toSet

    val mapGraph = graph.mapVertices((id, v) => {
      if (ids.contains(id)) {
        Map(id -> 0)
      } else {
        Map[VertexId, Int]()
      }
    })

    val start = Map[VertexId, Int]()
    val res = mapGraph.ops.pregel(start)(update, iterate, mergeMaps)
    res.vertices.flatMap {
      case (id, m) =>
        m.map {
          case (k, v) =>
            if (id < k) {
              (id, k, v)
            } else {
              (k, id, v)
            }
        }
    }.distinct().cache()
  }

  def mergeMaps(m1: Map[VertexId, Int], m2: Map[VertexId, Int]): Map[VertexId, Int] = {
    def minThatExists(k: VertexId): Int = {
      math.min(
        m1.getOrElse(k, Int.MaxValue),
        m2.getOrElse(k, Int.MaxValue))
    }

    (m1.keySet ++ m2.keySet).map(k => (k, minThatExists(k))).toMap
  }

  def update(id: VertexId, state: Map[VertexId, Int], msg: Map[VertexId, Int]): Map[VertexId, Int] = {
    mergeMaps(state, msg)
  }

  def checkIncrement(a: Map[VertexId, Int], b: Map[VertexId, Int], bid: VertexId): Iterator[(VertexId, Map[VertexId, Int])] = {
    val aplus = a.map { case (v, d) => v -> (d + 1) }
    if (b != mergeMaps(aplus, b)) {
      Iterator((bid, aplus))
    } else {
      Iterator.empty
    }
  }

  def iterate(e: EdgeTriplet[Map[VertexId, Int], _]): Iterator[(VertexId, Map[VertexId, Int])] = {
    checkIncrement(e.srcAttr, e.dstAttr, e.dstId) ++
      checkIncrement(e.dstAttr, e.srcAttr, e.srcId)
  }

  /*
   * 小世界网络的两个属性:
   * 1.	网络中的大部分节点的度都不高,它们与其他节点形成相对稠密的簇.也就是说一个节点的邻接节点大部分都是相连的.
   * 2.虽然图中大部分节点的度不高而且属于相对稠密的簇,但只需经过少数几条边可能从一个网络节点快速到达另外一个节点.
   *
   * 如果对每个顶点都存在一条边使其与其他任何节点都相连,则这个图就是完备的.
   * 给定一个图,如果有多个顶点子集是完备的,我们把这些完备的子图称为系.
   *
   * 判断一个图是否有给定大小的系是一个NP-完备问题,其计算复杂度非常高.
   *
   * 局部聚类系数:一个顶点的实际三角计数与其邻接点可能的三角计数的比率.用来衡量一个图的局部稠密性.
   * 对无向图来说,有k个邻接点和t个三角计数的顶点,其局部聚类系数C为:
   * 			C = 2t/(k(k-1))
   *
   * triangleCount()方法返回一个Graph对象,其中VertexRDD包含了每个顶点的三角计数
  */
  def avgClusteringCoef(graph: Graph[_, _]): Double = {
    val triCountGraph = graph.triangleCount()
    val maxTrisGraph = graph.degrees.mapValues(d => d * (d - 1) / 2.0)
    val clusterCoefGraph = triCountGraph.vertices.innerJoin(maxTrisGraph) {
      (vertexId, triCount, maxTris) => if (maxTris == 0) 0 else triCount / maxTris
    }
    clusterCoefGraph.map(_._2).sum() / graph.vertices.count()
  }

  // 偏函数
  def getVertices: PartialFunction[Row, (Long, String)] = {
    case Row(topic: String) => (getMD5(topic), topic)
    case Row(topic: Long)   => (topic, topic.toString())
  }

  def getAvildInfo(str: String): Seq[String] = {
    val elem = XML.loadString(str) \\ "DescriptorName"
    elem.filter(f => (f \ "@MajorTopicYN").text == "Y").map(_.text)
  }

  def loadXML(
    spark:         SparkSession,
    path:          String,
    START_TAG_KEY: String       = "<MedlineCitation ",
    END_TAG_KEY:   String       = "</MedlineCitation>"): Dataset[String] = {

    import spark.implicits._
    val hadoopConf = new Configuration
    hadoopConf.set(XMLInputFormat.START_TAG_KEY, START_TAG_KEY)
    hadoopConf.set(XMLInputFormat.END_TAG_KEY, END_TAG_KEY)
    spark.sparkContext
      .newAPIHadoopFile(path, classOf[XMLInputFormat], classOf[LongWritable], classOf[Text], hadoopConf)
      .map(f => f._2.toString).toDS
  }

  def getMD5(str: String): Long = {
    val bytes = MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8))
    (bytes(0) & 0xFFL) |
      ((bytes(1) & 0xFFL) << 8) |
      ((bytes(2) & 0xFFL) << 16) |
      ((bytes(3) & 0xFFL) << 24) |
      ((bytes(4) & 0xFFL) << 32) |
      ((bytes(5) & 0xFFL) << 40) |
      ((bytes(6) & 0xFFL) << 48) |
      ((bytes(7) & 0xFFL) << 56)
  }

  /*
   * 				YESB		NOB		BTOTAL
   * YESA		YY 	YN 		YA
   * NOA			NY 	NN 		NA
   * ATOTAL	YB 	NB 		T
   *
   * yy 是两个概念同时出现的次数
   * 该方法计算两个概念的卡方统计量,也可以说相关性
  */

  def chiSq(YY: Long, YB: Long, YA: Long, T: Long): Double = {
    val NB = T - YB
    val NA = T - YA
    val YN = YA - YY
    val NY = YB - YY
    val NN = T - YY - YN - NY
    val inner = math.abs(YY * NN - YN * NY) - T / 2.0
    T * math.pow(inner, 2) / (YA * NA * YB * NB)
  }

}