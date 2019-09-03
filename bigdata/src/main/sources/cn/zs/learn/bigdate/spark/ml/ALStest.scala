package cn.zs.learn.bigdate.spark.ml

import com.esotericsoftware.kryo.KryoSerializable
import org.apache.spark.sql.SparkSession
import cn.zs.learn.bigdate.spark.graphX.GraphTest
import edu.umd.cloud9.collection.wikipedia.language.EnglishWikipediaPage
import edu.umd.cloud9.collection.wikipedia.WikipediaPage
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import java.util.Properties
import edu.stanford.nlp.pipeline.Annotation
import scala.collection.mutable.ArrayBuffer
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation
import org.apache.spark.sql.Dataset
import scala.io.Source
import org.apache.spark.ml.feature.CountVectorizer
import org.apache.spark.ml.feature.IDF

object ALStest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("基于维基百科的数据分析(ALS)").config("spark.serializer", classOf[KryoSerializable].getName).master("local[*]").getOrCreate()
    import spark.implicits._
    val ALSAssist = new ALSAssist(spark)
    import ALSAssist._
    val numTerms = 10
    // 读取数据
    val docTexts: Dataset[(String, String)] = parseWikipediaDump("hdfs://10.11.100.102:9000/wikipedia_test/wikipedia.xml")
    // 词形归并，去掉停止词
    val terms = contentsToTerms(docTexts)
    
    val termsDF = terms.toDF("title", "terms")
    import org.apache.spark.sql.functions._
    val filtered = termsDF.where(size($"terms") > 1)
    
    // 求DF，setVocabSize设置词汇表的总数，默认2^18。setMinDF设置词在每个文档出现的最小次数，默认为1.
    val countVectorizer = new CountVectorizer()
      .setInputCol("terms").setOutputCol("termFreqs").setVocabSize(numTerms)
    val vocabModel = countVectorizer.fit(filtered)
    val docTermFreqs = vocabModel.transform(filtered)

    val termIds = vocabModel.vocabulary

    docTermFreqs.cache()

    val docIds = docTermFreqs.rdd.map(_.getString(0)).zipWithUniqueId().map(_.swap).collect().toMap

    val idf = new IDF().setInputCol("termFreqs").setOutputCol("tfidfVec")
    val idfModel = idf.fit(docTermFreqs)
    val docTermMatrix = idfModel.transform(docTermFreqs).select("title", "tfidfVec")
    
  }
}