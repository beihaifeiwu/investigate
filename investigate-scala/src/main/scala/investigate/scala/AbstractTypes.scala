package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object AbstractTypes extends App{

  trait Buffer { type T; val element: T }

  abstract class SeqBuffer extends Buffer {
    type U
    type T <: Seq[U]
    def length = element.length
  }

  abstract class IntSeqBuffer extends SeqBuffer {
    type U = Int
  }

  def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer =
    new IntSeqBuffer {
      override type T = List[U]
      override val element: T = List(elem1, elem2)
    }

  val buf = newIntSeqBuf(7, 8)

  println("length = " + buf.length)
  println("content = " + buf.element)
}

