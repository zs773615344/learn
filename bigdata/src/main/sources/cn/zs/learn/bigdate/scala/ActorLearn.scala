package cn.zs.learn.bigdate.scala

import scala.actors.Actor
import scala.actors.Actor._

object ActorLearn {
  def main(args: Array[String]): Unit = {
    val actor1 = new HiActor
    actor1.start()
    val actor3 = new SenderActor
    actor3.start()
    val actor2 = actor {
      var i = 0
      while (i < 10) {
        actor3 ! "Hi"
        receive {
          case "hello" => println("receive")
        }
        i += 1
      }
    }

  }
}
class HiActor extends Actor {
  def act() {
    while (true) {
      receive {
        case "Hi" => println("hello")
      }
    }
  }
}

class infoActor1 extends Actor {
  def act() {
    while (true) {
      receive {
        case information(name, actor) => actor ! "name"
      }
    }
  }
}

case class information(name: String, actor: Actor)

class SenderActor extends Actor {
  def act() {
    while (true) {
      receive {
        case "Hi" => {
            println("sender")
//            sender ! "hello"
            reply("hello")
          }
      }
    }
  }
}