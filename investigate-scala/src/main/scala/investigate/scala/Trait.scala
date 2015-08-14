package investigate.scala

/**
 * Created by LiuPin on 2015/8/14.
 */
object Trait extends App{

  trait Similarity {
    def isSimilar(x: Any): Boolean
    def isNotSimilar(x: Any): Boolean = !isSimilar(x)
  }

  class Point(xc: Int, yc: Int) extends Similarity {
    var x: Int = xc
    var y: Int = yc

    def isSimilar(obj: Any) = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == x

  }

  val p1 = new Point(2, 3)
  val p2 = new Point(2, 4)
  val p3 = new Point(3, 3)

  println(p1.isNotSimilar(p2))
  println(p1.isNotSimilar(p3))
  println(p1.isNotSimilar(2))
}
