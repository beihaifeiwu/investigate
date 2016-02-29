package com.freetmp.investigate.kotlin.callable

/**
 * Created by LiuPin on 2015/6/4.
 */

fun main(args: Array<String>) {
  println("***************Reference to a function****************")
  val numbers = listOf(1, 2, 3)
  println(numbers.filter(::isOdd))

  println("****************Composition of functions*****************")
  val oddLength = compose(::isOdd, ::length)
  val strings = listOf("a", "ab", "abc")
  println(strings.filter(oddLength))
}

fun isOdd(x: Int) = x % 2 != 0
fun length(s: String) = s.length

/**
 * The composition function return a composition of two functions passed to it:
 * compose(f, g) = f(g(*)).
 * Now, you can apply it to callable references.
 */
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
  return { x -> f(g(x)) }
}