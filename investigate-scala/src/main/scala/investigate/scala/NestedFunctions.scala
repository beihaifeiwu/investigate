package investigate.scala

/**
 * Created by LiuPin on 2015/8/14.
 */
object NestedFunctions extends App {

  def filter(xs: List[Int], threshold: Int) = {
    def process(ys: List[Int]) : List[Int] =
      if (ys.isEmpty) ys
      else if (ys.head < threshold) ys.head :: process(ys.tail)
      else process(ys.tail)
    process(xs)
  }

  println(filter(List(1, 9, 2, 8, 3, 7, 4), 5))

}
