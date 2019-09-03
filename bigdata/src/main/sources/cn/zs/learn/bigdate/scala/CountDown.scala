package cn.zs.learn.bigdate.scala

import scala.actors.Actor
import scala.actors.Actor._

object CountDown {
  def main(args: Array[String]): Unit = {
    var i = 10
    val actor1 = actor {
      while(i > 0){
        receive{
          case j:Int if j>0 => print(j-1); println(Thread.currentThread().getName); i = i - 1;sender ! (j-1)
          case _ => exit()
        }
      }
    }
    actor1.start()
    val actor2 = actor {
      actor1 ! i
      while(i > 0) {
        receive{
          case j:Int if j>0 => print(j-1); println(Thread.currentThread().getName); i = i - 1;sender ! (j-1)
          case _ => exit()
        }
      }
    }
    actor2.start() 
  }
}
