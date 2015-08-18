package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object TypeBounds extends App{

  trait Similar {
    def isSimilar(x: Any): Boolean
  }

  case class MyInt(x: Int) extends Similar {
    def isSimilar(m: Any): Boolean = m.isInstanceOf[MyInt] && m.asInstanceOf[MyInt].x == x
  }

  def findSimilar[T <: Similar](e: T, xs: List[T]): Boolean =
    if (xs.isEmpty) false
    else if (e.isSimilar(xs.head)) true
    else findSimilar[T](e, xs.tail)

  val list: List[MyInt] = List(MyInt(1), MyInt(2), MyInt(3))
  println(findSimilar[MyInt](MyInt(4), list))
  println(findSimilar[MyInt](MyInt(2), list))

  case class ListNode[+T](h: T, t: ListNode[T]){
    def head: T = h
    def tail: ListNode[T] = t
    def prepend[U >: T](elem: U): ListNode[U] = ListNode(elem, this)
  }

  val empty: ListNode[Null] = ListNode(null,null)
  val strList: ListNode[String] = empty.prepend("hello").prepend("world")
  val anyList: ListNode[Any] = strList.prepend(12345)

  println(empty)
  println(strList)
  println(anyList)
}
