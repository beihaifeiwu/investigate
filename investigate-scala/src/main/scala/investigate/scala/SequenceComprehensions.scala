package investigate.scala

import scala.collection.immutable.IndexedSeq

/**
 * Created by LiuPin on 2015/8/17.
 */
object SequenceComprehensions extends App{

  def even(from: Int, to: Int): IndexedSeq[Int] =
    for (i <- Range(from, to) if i % 2 == 0) yield i

  Console.println(even(0, 20))

  def foo(n: Int, v: Int) =
    for(i <- 0 until n; j <- i until n if i + j == v) yield (i,j)

  foo(20, 32) foreach { case (i, j) => println("(" + i + ", " + j + ")")}

  for(i <- Range(0, 20); j <- Range(i, 20) if i + j == 32) println("(" + i + ", " + j + ")")
}
