package com.freetmp.investigate.algorithm

/**
 * 编辑距离概念描述：
 *    编辑距离，又称Levenshtein距离，是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。
 *    许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
 *    例如将kitten一字转成sitting：
 *      sitten （k→s）
 *      sittin （e→i）
 *      sitting （→g）
 *    俄罗斯科学家Vladimir Levenshtein在1965年提出这个概念。
 *
 *
 * Created by liupin on 2016/4/3.
 */
object StringEditDistance {

  /**
   * 朴素的递归算法
   */
  fun editDistanceByRecursion(src: String, dest: String, srcPos: Int = 0, destPos: Int = 0): Int {
    if ((src.length - srcPos) == 0 || (dest.length - destPos) == 0) {
      return Math.abs(src.length - srcPos - dest.length + destPos)
    }

    if (src[srcPos] == dest[destPos]) {
      return editDistanceByRecursion(src, dest, srcPos + 1, destPos + 1)
    }

    val edIns = editDistanceByRecursion(src, dest, srcPos, destPos + 1) + 1
    val edDel = editDistanceByRecursion(src, dest, srcPos + 1, destPos) + 1
    val edRep = editDistanceByRecursion(src, dest, srcPos + 1, destPos + 1) + 1

    return Math.min(edIns, Math.min(edDel, edRep))
  }

  data class TagMemoRecord(var distance: Int = 0, var refCount: Int = 0)

  /**
   * 引入备忘录的朴素递归算法
   */
  fun editDistanceByRecursionWithMemo(
      src: String,
      dest: String,
      srcPos: Int = 0,
      destPos: Int = 0,
      memo: Array<Array<TagMemoRecord>> = Array(src.length + 1, { Array(dest.length + 1, { TagMemoRecord() }) })): Int {

    if (memo[srcPos][destPos].refCount != 0) {
      memo[srcPos][destPos].refCount++
      return memo[srcPos][destPos].distance
    }

    var distance = 0
    if (src.length - srcPos == 0) {
      distance = dest.length - destPos
    } else if (dest.length - destPos == 0) {
      distance = src.length - srcPos
    } else {
      if (src[srcPos] == dest[destPos]) {
        distance = editDistanceByRecursionWithMemo(src, dest, srcPos + 1, destPos + 1, memo)
      } else {
        val edIns = editDistanceByRecursionWithMemo(src, dest, srcPos, destPos + 1, memo) + 1
        val edDel = editDistanceByRecursionWithMemo(src, dest, srcPos + 1, destPos, memo) + 1
        val edRep = editDistanceByRecursionWithMemo(src, dest, srcPos + 1, destPos + 1, memo) + 1
        distance = Math.min(edIns, Math.min(edDel, edRep))
      }
    }

    memo[srcPos][destPos].distance = distance
    memo[srcPos][destPos].refCount = 1

    return distance
  }

  /**
   * 动态规划算法
   */
  fun editDistanceByDynamicProgramming(src: String, dest: String): Int {
    val d = Array(src.length + 1, { Array(dest.length + 1, { Int.MAX_VALUE }) })
    for (i in 0..src.length) d[i][0] = i
    for (j in 0..dest.length) d[0][j] = j

    for (i in 1..src.length) {
      for (j in 1..dest.length) {
        if(src[i - 1] == dest[j - 1]){
          d[i][j] = d[i - 1][j - 1]
        }else {
          val edIns = d[i][j - 1] + 1
          val edDel = d[i - 1][j] + 1
          val edRep = d[i - 1][j - 1] + 1
          d[i][j] = Math.min(edIns, Math.min(edDel, edRep))
        }
      }
    }

    return d[src.length][dest.length]
  }

  @JvmStatic fun main(args: Array<String>) {
    println("SNOWY ==> SUNNY: ${editDistanceByRecursion("SNOWY", "SUNNY")}")
    println("kitten ==> sitting: ${editDistanceByRecursion("kitten", "sitting")}")

    println("SNOWY ==> SUNNY: ${editDistanceByRecursionWithMemo("SNOWY", "SUNNY")}")
    println("kitten ==> sitting: ${editDistanceByRecursionWithMemo("kitten", "sitting")}")

    println("SNOWY ==> SUNNY: ${editDistanceByDynamicProgramming("SNOWY", "SUNNY")}")
    println("kitten ==> sitting: ${editDistanceByDynamicProgramming("kitten", "sitting")}")
  }
}