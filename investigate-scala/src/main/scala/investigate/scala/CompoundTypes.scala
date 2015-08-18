package investigate.scala

/**
 * Created by LiuPin on 2015/8/17.
 */
object CompoundTypes extends App{

  trait Cloneable extends java.lang.Cloneable {
    override def clone(): Cloneable = super.clone().asInstanceOf[Cloneable]
  }

  trait Resetable { def reset: Unit }

  def cloneAndReset(obj: Cloneable with Resetable): Cloneable = {
    val cloned = obj.clone()
    obj.reset
    cloned
  }
}
