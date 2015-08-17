package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object RegularExpressionPattern extends App{

  def containsScala(x: String): Boolean = {
    val z: Seq[Char] = x
    z match {
      case Seq('s', 'c', 'a', 'l', 'a', rest @ _*) =>
        println("rest is " + rest)
        true
      case Seq(_*) => false
    }
  }

  println(containsScala("scala is such a beauty"))
  println(containsScala("nothing"))
}
