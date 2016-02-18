package com.freetmp.investigate.kotlin.delegate

import kotlin.properties.Delegates
import kotlin.properties.getValue
import kotlin.reflect.KProperty

/**
 * Created by LiuPin on 2015/6/4.
 */

fun main(args: Array<String>) {
  println("*****************Custom delegate*****************")
  val e = Example()
  println(e.p)
  e.p = "NEW"

  println("****************Lazy property*****************")
  var sample = LazyExample()
  println("lazy = ${sample.lazy}")
  println("lazy = ${sample.lazy}")

  println("****************Observable property****************")
  var user = User()
  user.name = "Carl"

  println("****************NotNull property******************")
  var human = Human()
  human.init("Carl")
  println(user.name)

  println("*****************Properties in map*****************")
  val female = Female(mapOf("name" to "John Doe", "age" to 25))
  println("name = ${female.name}, age = ${female.age}")

}

class Example {
  var p: String by Delegate()
  override fun toString() = "Example Class"
}

class Delegate() {
  operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
    return "$thisRef, thank you for delegating '${prop.name}' to me!"
  }

  operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
    println("$value has been assigned to ${prop.name} in $thisRef")
  }
}

class LazyExample {
  val lazy: String by lazy { println("computed");"my lazy" }
}

/**
 * The observable() function takes two arguments: initial value and a handler for modifications.
 * The handler gets called every time we assign to `name`, it has three parameters:
 * a property being assigned to, the old value and the new one. If you want to be able to veto
 * the assignment, use vetoable() instead of observable().
 */
class User {
  var name: String by Delegates.observable("no name") {
    d, old, new ->
    println("$old - $new")
  }
}

/**
 * Users frequently ask what to do when you have a non-null var, but you don't have an
 * appropriate value to assign to it in constructor (i.e. it must be assigned later)?
 * You can't have an uninitialized non-abstract property in Kotlin. You could initialize it
 * with null, bit then you'd have to check every time you access it. Now you have a delegate
 * to handle this. If you read from this property before writing to it, it throws an exception,
 * after the first assignment it works as expected.
 */
class Human {
  var name: String by Delegates.notNull()
  fun init(name: String) {
    this.name = name
  }
}

/**
 * Properties stored in a map. This comes up a lot in applications like parsing JSON
 * or doing other "dynamic" stuff. Delegates take values from this map (by the string keys -
 * names of properties). Of course, you can have var's as well (with mapVar() function),
 * that will modify the map upon assignment (note that you'd need MutableMap instead of read-only Map).
 */
class Female(val map: Map<String, Any?>) {
  val name: String by map
  val age: Int by map
}