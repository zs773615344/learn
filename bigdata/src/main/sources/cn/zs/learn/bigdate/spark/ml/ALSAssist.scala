package cn.zs.learn.bigdate.spark.ml

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Dataset
import scala.io.Source
import java.util.Properties
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.pipeline.Annotation
import scala.collection.mutable.ArrayBuffer
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation
import cn.zs.learn.bigdate.spark.graphX.GraphTest
import edu.umd.cloud9.collection.wikipedia.language.EnglishWikipediaPage
import edu.umd.cloud9.collection.wikipedia.WikipediaPage

class ALSAssist(private val spark: SparkSession) {
  
  import spark.implicits._
  
  def contentsToTerms(docTexts: Dataset[(String, String)]): Dataset[(String, Seq[String])] = {
    val stopWords = spark.sparkContext.broadcast(Source.fromURL(ALStest.getClass.getResource("/stopwords")).getLines().toSet)
    docTexts.mapPartitions(it => {
      val pipeline = createNLPPipeline()
      it.map {
        case(title,contents) => (title,plainTextToLemas(contents, stopWords.value, pipeline))
      }
    })
  }
  
  
    /**
    * Create a StanfordCoreNLP pipeline object to lemmatize documents.
    * 下面的代码接收纯文本形式的文档 RDD，对文档进行词形归并，过滤掉其
    * 中的停用词
    */

  def createNLPPipeline(): StanfordCoreNLP = {
    val props = new Properties()
    props.put("annotators", "tokenize,ssplit,pos,lemma")
    new StanfordCoreNLP(props)
  }

  def isOnlyLetters(str: String): Boolean = {
    str.forall(c => Character.isLetter(c))
  }

  def plainTextToLemas(text: String, stopWords: Set[String], pipeline: StanfordCoreNLP): Seq[String] = {
    val doc = new Annotation(text)
    pipeline.annotate(doc)

    val lemmas = new ArrayBuffer[String]()
    val sentences = doc.get(classOf[SentencesAnnotation])
    import scala.collection.JavaConverters.asScalaBufferConverter
    for (sentence <- sentences.asScala; token <- sentence.get(classOf[TokensAnnotation]).asScala) {
      val lemma = token.get(classOf[LemmaAnnotation])
      if (lemma.length() > 2 && !stopWords.contains(lemma) && isOnlyLetters(lemma)) {
        lemmas += lemma.toLowerCase()
      }
    }
    lemmas
  }
  
  def parseWikipediaDump(path: String): Dataset[(String, String)] = {
    val kvs = GraphTest.loadXML(spark, "hdfs://10.11.100.102:9000/user/ds/wikipedia.xml", "<page>", "</page>")
    kvs.filter(_ != null).flatMap(wikiXmlToPlainText)
  }

  def wikiXmlToPlainText(xml: String): Option[(String, String)] = {
    val page = new EnglishWikipediaPage
    val hackedPageXml = xml.replaceFirst(
      "<text xml:space=\"preserve\" bytes=\"\\d+\">", "<text xml:space=\"preserve\">")
    WikipediaPage.readPage(page, xml)
    if (page.isEmpty() || !page.isArticle || page.isRedirect || page.isDisambiguation ||
      page.getTitle.contains("(disambiguation)"))
      None
    else
      Some((page.getTitle, page.getContent))
  }
}