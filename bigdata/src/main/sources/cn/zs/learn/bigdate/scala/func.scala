package cn.zs.learn.bigdate.scala

object test {
  def main(args: Array[String]): Unit = {
//    val haha = if (0 == 0 ) None else Some()
    println(hello.ff(4))

    def fun1(x: Double) = {
      val z = 4
      def fun2 = (y: Double) => x * y * z
      fun2(4)
      fun2(5)
    }
    val fun2 = fun1(3)
    println(fun2)
    //    println(fun2(3))
    def xx(x:(Double=>Double))=x
    def yy(x:Double)=x
    xx(yy)
  }
}

object hello {
  val num = 4
  val ff = (x: Double) => num * 3
}
