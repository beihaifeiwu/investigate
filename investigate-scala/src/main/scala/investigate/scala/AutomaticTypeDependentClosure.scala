package investigate.scala

/**
 * Created by LiuPin on 2015/8/18.
 */
object AutomaticTypeDependentClosure extends App{

  def whileLoop(cond: => Boolean)(body: => Unit): Unit =
    if(cond) {
      body
      whileLoop(cond) (body)
    }

  var i = 10
  whileLoop( i > 0 ){
    println(i)
    i -= 1
  }

  def loop(body: => Unit): LoopUnlessCond = new LoopUnlessCond(body)

  protected class LoopUnlessCond(body: => Unit){
    def unless(cond: => Boolean): Unit ={
      body
      if(!cond) unless(cond)
    }
  }

  i = 10
  loop {
    println("i = " + i)
    i -= 1
  } unless( i == 0)
}
