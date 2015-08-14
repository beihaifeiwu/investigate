package investigate.scala

/**
 * Created by LiuPin on 2015/8/14.
 */
object PatternMatching extends App{

  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case 2 => "two"
    case "two" => 2
    case y: Int => "scala.Int"
    case _ => "many"
  }

  println(matchTest(3))
  println(matchTest(2))

}
