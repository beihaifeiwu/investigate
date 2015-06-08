package com.freetmp.investigate.kotlin.reflect

import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

/**
 * Created by LiuPin on 2015/6/8.
 */

fun main(args: Array<String>) {
  println("******************Class References****************")
  var c = String::class
  println(c)

  println("****************Function References***************")
  fun isOdd(x: Int) = x % 2 != 0
  var numbers = listOf(1, 2, 3)
  println(numbers filter ::isOdd)

  fun compose<A, B, C>(f: (B) -> C, g: (A) -> B): (A) -> C = { x -> f(g(x)) }
  val oddLength = compose(::isOdd, String::length)
  val strings = listOf("a", "ab", "abc")
  println(strings filter oddLength)

  println("*****************Property References*******************")
  println(::x.get())
  ::x.set(2)
  println(x)

  val prop = A::p
  println(prop.get(A(1)))

  println(String::lastChar.get("abc"))

  println(A::p.javaGetter)
  println(A::p.javaField)

  println("*****************Constructor References*****************")
  fun function(factory: (Int) -> A, arg: Int) = factory(arg)
  println(function(::A, 1))
}

var x = 1

class A(val p: Int)

val String.lastChar: Char
  get() = this[length() - 1]