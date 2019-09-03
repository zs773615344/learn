package cn.zs.learn.bigdate.scala

object CharLetter {
  def main(args: Array[String]): Unit = {
    val str = "!@#$%^&HFVGFkjl"
    println(str.forall(c => Character.isLetter(c)))
    println(Character.isLetter('c'))
    println(Character.isLetter('$'))
  }
}