package com.freetmp.investigate.algorithm

import java.util.*
import kotlin.platform.platformStatic

/**
 * Created by pin on 2015/6/27.
 */
public class EightLitreWaters {
  data class BucketState(val buckets: Array<Int> = arrayOf(8, 0, 0), val lastAction: Action = Action(-1, 1, 8))
  data class Action(val from: Int, val to: Int, val water: Int)

  val capacities = arrayOf(8, 5, 3)
  val stack = Stack<BucketState>()

  val solutions = HashSet<String>()
  var count = 0

  fun searchOnState(curState: BucketState = BucketState()) {
    if (curState.buckets[0] == 4 && curState.buckets[1] == 4) {
      val solution = stack.toStringPresentation()
      if (!solutions.contains(solution)) {
        println("${++count}. ${solution}")
        solutions.add(solution)
      }
      return
    }

    perform(3, 2, curState)
    perform(3, 1, curState)
    perform(2, 1, curState)
    perform(2, 3, curState)
    perform(1, 3, curState)
    perform(1, 2, curState)
  }

  fun perform(from: Int, to: Int, curState: BucketState) {
    if (curState.buckets[from - 1] == 0 || curState.buckets[to - 1] >= capacities[to - 1]) {
      return
    }

    val buckets = curState.buckets.clone()
    val water = Math.min(buckets[from - 1], capacities[to - 1] - buckets[to - 1])
    buckets[to - 1] += water
    buckets[from - 1] -= water

    // prevent iterate loop
    stack.forEach { if ( it.buckets equalsEachElement buckets ) return@perform }

    val nextState = BucketState(buckets, Action(from, to, water))
    stack.push(nextState)
    searchOnState(nextState)
    stack.pop()
  }

  fun Array<Int>.equalsEachElement(other: Array<Int>): Boolean {
    forEachIndexed { index, value ->
      if (value != other[index]) return@equalsEachElement false
    }
    return true
  }

  fun Stack<BucketState>.toStringPresentation(): String = StringBuilder {
    forEach { append("(${capacities[it.lastAction.from - 1]},${capacities[it.lastAction.to - 1]},${it.lastAction.water})") }
  }.toString()

  companion object {
    platformStatic fun main(args: Array<String>) {
      EightLitreWaters().searchOnState()
    }
  }
}