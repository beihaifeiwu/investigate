package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object Variances extends App{

  class Stack[+A] {
    def push[B >: A](elem: B): Stack[B] = new Stack[B] {
      override def top: B = elem

      override def pop: Stack[B] = Stack.this

      override def toString(): String = elem.toString() + " " + Stack.this.toString()
    }

    def top: A = sys.error("no element on stack")
    def pop: Stack[A] = sys.error("no element on stack")
    override def toString() = ""
  }

  var s: Stack[Any] = new Stack().push("hello")
  s = s.push(new Object)
  s = s.push(7)
  println(s)
}
