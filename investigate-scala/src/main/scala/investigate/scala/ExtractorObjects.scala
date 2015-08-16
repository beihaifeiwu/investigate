package investigate.scala

/**
 * Created by LiuPin on 2015/8/14.
 */
object ExtractorObjects extends App{

  object Twice {
    def apply(x: Int): Int = x * 2
    def unapply(z: Int): Option[Int] = if (z%2 == 0) Some(z/2) else None
  }

  val x = Twice(21)
  println(x)
  x match { case Twice(n) => Console.println(n) }

  object Email {
    def apply(user: String, domain: String) = user + "@" + domain
    def unapply(str: String): Option[(String, String)] = {
      val parts = str split "@"
      if(parts.length == 2){
        Some(parts(0), parts(1))
      }else{
        None
      }
    }
  }

  println ("Apply method : " + Email.apply("Zara", "gmail.com"))
  println ("Unapply method : " + Email.unapply("Zara@gmail.com"))
  println ("Unapply method : " + Email.unapply("Zara Ali"))

}
