package com.freetmp.investigate.kotlin.funcs

/**
 * Created by LiuPin on 2015/6/5.
 */

fun main(args: Array<String>) {

  println("****************Higher-Order Functions***************")
  higherOrderFunctions()

  println("******************Function Types********************")
  functionTypes()

  println("*******************Function Literal and Expression********************")
  functionLiteralAndExpression()

  println("*************************Closures*************************")
  closures()

  println("*****************Extension Function Expressions****************")
  extensionFunctionExpression()
}

fun higherOrderFunctions() {
  fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this)
      result.add(transform(item))
    return result
  }

  var ints = arrayListOf(1, 2, 3, 4, 5, 6)
  println(ints.map { it * 2 })
  var strings = arrayListOf("function", "closure", "expression", "literals")
  println(strings.filter { it.length > 5 } )
}

fun functionTypes() {
  fun <T> max(collection: Collection<T>, less: (T, T) -> Boolean): T? {
    var max: T? = null
    for ( it in collection)
      if (max == null || less(max, it)) max = it
    return max
  }
  println(max(arrayListOf(4, 6, 1, 9), { x, y -> x < y }))
  println(max(arrayListOf("function", "closure", "expression", "literals"), { a, b -> a.length < b.length }))
}

fun functionLiteralAndExpression() {
  var sum: (Int, Int) -> Int = { x, y -> x + y }
  println(sum(1, 2))

  sum = { x: Int, y: Int -> x + y }
  println(sum(1, 2))
  arrayListOf(-1, 3, 5, -3).filter(fun(item) = item > 0).forEach { println(it) }
}

fun closures() {
  var sum = 0
  arrayListOf(1, 2, 3, 4, 5).filter { it > 0 }.forEach { sum += it }
  println(sum)
}

fun extensionFunctionExpression() {
  val sum = fun Int.(other: Int): Int = this + other
  println(1.sum(2))
}