package com.freetmp.investigate.kotlin.multi

/**
 * Created by LiuPin on 2015/6/4.
 */

fun main(args: Array<String>) {
  println("****************Multi-declarations****************")
  val pair = Pair(1, "one")
  val (num, name) = pair
  println("num = $num, name = $name")

  println("****************Data classes****************")

  val (name2, id) = User("Alex", 1)
  println("name = $name2, id = $id")

  println("****************Traversing a map*****************")
  val map = hashMapOf<String, Int>()
  map.put("one", 1)
  map.put("two", 2)
  for ((key, value) in map) println("key = $key, value = $value")

  println("****************Autogenerated functions****************")
  val user = User("Alex", 1)
  println(user) // toString()

  val secondUser = User("Alex", 1)
  val thirdUser = User("Max", 2)
  println("user == secondUser: ${user == secondUser}")
  println("user == thridUser: ${user == thirdUser}")

  // copy() function
  println(user.copy())
  println(user.copy("Max"))
  println(user.copy(id = 2))
  println(user.copy("Max", 2))

}

/**
 * This example introduces a concept that we call mutli-declarations.
 * It creates multiple variable at once. Anything can be on the right-hand
 * side of a mutli-declaration, as long as the required number of component
 * functions can be called on it.
 * See http://kotlinlang.org/docs/reference/multi-declarations.html#multi-declarations
 */
class Pair<K, V>(val first: K, val second: V) {
  operator fun component1(): K {
    return first
  }

  operator fun component2(): V {
    return second
  }
}

/**
 *  Data class gets component functions, one for each property declared
 *  in the primary constructor, generated automatically, same for all the
 *  other goodies common for data: toString(), equals(), hashCode() and copy().
 *  See http://kotlinlang.org/docs/reference/data-classes.html#data-classes
 */
data class User(val name: String, val id: Int)
