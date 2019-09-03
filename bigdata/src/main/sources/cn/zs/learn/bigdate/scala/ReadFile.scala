package cn.zs.learn.bigdate.scala

import scala.io.Source
import java.io.File

object ReadFile {
  def main(args: Array[String]): Unit = {

    /**
     * 可以将source和source.getLines看作迭代器，一个针对字符，一个针对行
     * 
     * 
     * 获得项目根目录
     * 如果加.stopwords./txt,在jar包中为空
     * 不加的话，可以读出。
    */
    val url1 = ReadFile.getClass.getClassLoader.getResources("./stopwords.txt")
    val url3 = ReadFile.getClass.getClassLoader.getResources("stopwords.txt")
    val url2 = ReadFile.getClass.getResource("/stopwords.txt")

//    while (url1.hasMoreElements()) {
//      val url = url1.nextElement()
//      println(url)
//      println(Source.fromURL(url).mkString.substring(0, 10))
//      println("***********************************************************1")
//    }
//
//    while (url3.hasMoreElements()) {
//      val url = url3.nextElement()
//      println(url)
//      println(Source.fromURL(url).mkString.substring(0, 10))
//      println("***********************************************************2")
//    }

    println(url2)
    println(url2.toURI())
    val source = Source.fromURL(url2)
//    println(source.mkString.substring(0, 10))

    println("***********************************************************3")
    
    println(source.toSet)
    
//    println(source.getLines.toSet)

    /*
     * 在jar包中都不能使用fromFile读取jar包中的数据
    */
//    try {
//      println(Source.fromFile(url2.toURI()).mkString.substring(0, 10))
//    } finally {
//      println("***********************************************************4")
//      try {
//        println(Source.fromFile(url2.getFile).mkString.substring(0, 10))
//      } finally {
//        println("***********************************************************5")
//        try {
//          println(Source.fromFile(new File(url2.getFile)).mkString.substring(0, 10))
//        } finally {
//          println("***********************************************************6")
//          try {
//            println(Source.fromFile(new File(url2.getPath)).mkString.substring(0, 10))
//          } finally {
//            println("***********************************************************7")
//            try{
//            	println(Source.fromFile(new File(url2.toURI())).mkString.substring(0, 10))              
//            }finally{
//            	println("***********************************************************8")
//              println(Source.fromURI(url2.toURI()).mkString.substring(0, 10))
//            }
//          }
//
//        }
//      }
//
//    }

  }
}