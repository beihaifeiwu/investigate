package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object MixinClassComposition extends App {

  abstract class AbsIterator {
    type T
    def hasNext: Boolean
    def next: T
  }

  trait RichIterator extends AbsIterator {
    def foreach(f: T => Unit) { while (hasNext) f(next)}
  }

  class StringIterator(s: String) extends AbsIterator {
    override type T = Char

    private var i = 0

    override def next: T = { val ch = s charAt i; i += 1; ch}

    override def hasNext: Boolean = i < s.length
  }


  class Iter extends StringIterator("It's just a test") with RichIterator
  val iter = new Iter
  iter foreach print
}
