package com.freetmp.investigate.kotlin.syntax

/**
 * Created by LiuPin on 2015/6/4.
 */
fun main(args: Array<String>) {
  println("******************Conditional expression******************")
  println(max(10, 20))
  println(max(-1, -4))
  println(max(3, 16))

  println("******************Null checks******************")
  println(parseInt("20"))
  println(parseInt("20a"))

  println("********************Is-checks and smart casts********************")
  println(getStringLength("aaa"))
  println(getStringLength(1))

  println("*******************Use a while loop********************")
  usingWhileLoop()

  println("*******************Use a for loop********************")
  usingForLoop()

  println("********************Use ranges and in**********************")
  usingRanges(4)

  println("**********************Use when***********************")
  useWhen()
}

fun max(a: Int, b: Int) = if (a > b) a else b

// Return null if str does not hold a number
fun parseInt(str: String): Int? {
  try {
    return Integer.parseInt(str)
  } catch(e: Exception) {
    println("One of the arguments isn't Int")
  }
  return null
}

fun getStringLength(obj: Any): Int? {
  if(obj is String) return obj.length // no cast to String is needed
  return null
}

fun usingWhileLoop(){
  var i = 0
  while(i++ < 10){
    print(i.toString() + " ")
  }
  println()
}

fun usingForLoop(){
  for(i in 1..10){
    print(i.toString() + " ")
  }
  println()
}

fun usingRanges(x: Int){
  //Check if a number lies within a range:
  var y = 10
  if(x in 1..y-1) println("OK")

  //Iterate over a range
  for(a in 1..5) print("${a} ")
  println()

  //Check if a number is out of range:
  var array = arrayListOf<String>()
  array.add("aaa")
  array.add("bbb")
  array.add("ccc")

  if(x !in 0..array.size - 1)
    println("Out: array has only ${array.size} elements. x = $x")

  //Check if a collection contains an object:
  if("aaa" in array) println("Yes: array contains aaa")

  if("ddd" in array) print("Yes: array contains ddd")
  else println("No: array doesn't contains ddd")

}

class MyClass(){}

fun useWhen(){
  fun cases(obj: Any){
    when (obj) {
      1 -> println("One")
      in 2..4 -> println("Between two and four")
      "Hello" -> println("Greeting")
      is Long -> println("Long")
      !is String -> println("Not a string")
      else -> println("Unknown")
    }
  }

  cases("Hello")
  cases(1)
  cases(3)
  cases(System.currentTimeMillis())
  cases(MyClass())
  cases("hello")
}