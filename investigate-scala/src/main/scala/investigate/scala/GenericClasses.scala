package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object GenericClasses extends App{

  class Stack[T] {
    var elems: List[T] = Nil
    def push(x: T) { elems = x :: elems }
    def top: T = elems.head
    def pop() { elems = elems.tail }
  }

  val stack = new Stack[Int]
  stack.push(1)
  stack.push('a')
  println(stack.top)
  stack.pop()
  println(stack.top)
}
