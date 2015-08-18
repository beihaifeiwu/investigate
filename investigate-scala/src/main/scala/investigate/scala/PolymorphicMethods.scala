package investigate.scala

/**
 * Created by LiuPin on 2015/8/18.
 */
object PolymorphicMethods extends App {

  def dup[T](x: T, n: Int): List[T] = if (n == 0) Nil else x :: dup(x, n -  1)

  println(dup[Int](3,4))
  println(dup("three", 3))
}
