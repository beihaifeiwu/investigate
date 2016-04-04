package com.freetmp.investigate.algorithm

/**
 * Google方程式
 *
 * 问题描述：
 *    有一个字符组成的等式：WWWDOT - GOOGLE = DOTCOM，每个字符代表一个0－9之间的数字，
 *    WWWDOT、GOOGLE和DOTCOM都是合法的数字，不能以0开头。请找出一组字符和数字的对应关系，
 *    使它们互相替换，并且替换后的数字能够满足等式
 *
 * 算法分析：
 *    从穷举法的角度看，这是一个典型的排列组合问题，题目中一共出现了9个字母，每个都可能是0~9
 *    之间的数字，穷举的方法就是对每个字母用0~9的数字尝试10次，如果某一次得到的字母和数字的
 *    对应关系能够满足减法等式，则输出这一组对应关系。根据题目意思，每个字母代表一个数字，也
 *    就是说，如果W代表1，则其他8个字母就不可能是1.很显然，这是一个组合问题，如果不考虑0开头
 *    数字的情况，这样的组合应该有10*9*8*7*6*5*4*3*2=3628800中组合，在这样的数量级上使
 *    用穷举法，计算机处理起来应该没有压力
 *
 * Created by liupin on 2016/4/3.
 */
object GoogleFormula {

  /**
   * 定义一个可变化的字符元素列表，每个字符元素包含3个属性，分别是字母本身、字母代表的数字以及
   * 是否是数字的最高位（根据题意，最高位不能是0，所以要特别对待）
   */
  data class TagCharItem(val c: Char, var value: Int, val leading: Boolean)

  /**
   * 因为这是一个组合问题，两个字母不能被指定为相同的数字，这就需要对每个数字做一个标识，当这个
   * 数字已经被某个字符“占用”时，其他字符不能再使用这个数字。我们对可参与穷举的数字也定义了一个
   * 列表，对于这个问题来说，0~9都可以参与穷举，但是有的问题可能有特殊的约束，比如字符只能代表
   * 偶数，或只能代表奇数等。
   */
  data class TagCharValue(var used: Boolean, val value: Int)

  /**
   * 采用递归的方式进行组合枚举
   */
  fun searchingResult(
      items: Array<TagCharItem>,
      values: Array<TagCharValue>,
      index: Int = 0,
      callback: (Array<TagCharItem>) -> Unit,
      isValid: (TagCharItem, TagCharValue) -> Boolean,
      maxCharCount: Int = 9) {

    if (index == maxCharCount) {
      callback(items)
      return
    }

    for (i in 0..maxCharCount) {
      if (isValid(items[index], values[i])) {
        values[i].used = true
        items[index].value = values[i].value
        searchingResult(items, values, index + 1, callback, isValid, maxCharCount)
        values[i].used = false
      }
    }
  }

  fun Array<TagCharItem>.makeInteger(value: String): Int =
      buildString {
        value.forEach { c ->
          append(this@makeInteger.first { c == it.c }.value)
        }
      }.toInt()


  @JvmStatic fun main(vararg args: String) {

    val charItems = arrayOf(
        TagCharItem('W', -1, true), TagCharItem('D', -1, true), TagCharItem('O', -1, false),
        TagCharItem('T', -1, false), TagCharItem('G', -1, true), TagCharItem('L', -1, false),
        TagCharItem('E', -1, false), TagCharItem('C', -1, false), TagCharItem('M', -1, false)
    )

    val charValues = (0..9).map { TagCharValue(false, it) }.toTypedArray()

    val callback: (Array<TagCharItem>) -> Unit = {
      val m = it.makeInteger("WWWDOT")
      val s = it.makeInteger("GOOGLE")
      val d = it.makeInteger("DOTCOM")
      if (m - s == d) {
        println("$m - $s = $d")
      }
    }

    val isValid: (TagCharItem, TagCharValue) -> Boolean = { item, value ->
      if(value.used == true ||
         (value.value == 0 && (item.c == 'W' || item.c == 'G' || item.c == 'D')))
        false
      else
        true
    }

    searchingResult(charItems, charValues, 0, callback, isValid)
  }
}