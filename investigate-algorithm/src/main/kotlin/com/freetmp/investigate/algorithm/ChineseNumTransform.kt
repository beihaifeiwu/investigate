package com.freetmp.investigate.algorithm

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.platform.platformStatic

/**
 * Created by pin on 2015/6/25.
 */
State(Scope.Thread) OutputTimeUnit(TimeUnit.MICROSECONDS) Fork(1)
public class ChineseNumTransform {

  val zhNumChar = charArrayOf('零', '一', '二', '三', '四', '五', '六', '七', '八', '九')
  val zhUnitChar = arrayOf("", "十", "百", "千")
  val zhUnitSection = arrayOf("", "万", "亿", "万亿")

  fun numToZh(number: Int): String {
    return StringBuilder {
      var unitPos = 0
      var needZero = false
      var num = number
      while (num > 0) {
        val section = num % 10000
        if (needZero) {
          insert(0, zhNumChar[0])
        }
        /*是否需要节权位*/
        insert(0, if (section != 0) zhUnitSection[unitPos] else zhUnitSection[0])
        insert(0, secToZh(section))
        /*千位是？，需要在下一个section补零*/
        needZero = section < 1000 && section > 0
        num /= 10000
        unitPos++
      }
    }.toString()
  }

  fun secToZh(sec: Int): String {
    return StringBuilder {
      var section = sec
      var zero = true
      var unitPos = 0
      while (section > 0) {
        val v = section % 10
        if (v == 0) {
          if (section == 0 || !zero) {
            /*需要补，zero的作用是确保对连续的多个，只补一个零*/
            zero = true
            insert(0, zhNumChar[v])
          }
        } else {
          zero = false
          insert(0, "${zhNumChar[v]}${zhUnitChar[unitPos]}")
        }
        section /= 10
        unitPos++
      }
    }.toString()
  }

  fun testDatas(): Map<Int, String> {
    val hashMap = HashMap<Int, String>()

    """
  {0, "零"},
  {1, "一"},
  {2, "二"},
  {3, "三"},
  {4, "四"},
  {5, "五"},
  {6, "六"},
  {7, "七"},
  {8, "八"},
  {9, "九"},
  {10, "一十"},
  {11, "一十一"},
  {110, "一百一十"},
  {111, "一百一十一"},
  {100, "一百"},
  {102, "一百零二"},
  {1020, "一千零二十"},
  {1001, "一千零一"},
  {1015, "一千零一十五"},
  {1000, "一千"},
  {10000, "一万"},
  {20010, "二万零一十"},
  {20001, "二万零一"},
  {100000, "一十万"},
  {1000000, "一百万"},
  {10000000, "一千万"},
  {100000000, "一亿"},
  {1000000000, "一十亿"},
  {1000001000, "一十亿一千"},
  {1000000100, "一十亿零一百"},
  {200010, "二十万零一十"},
  {2000105, "二百万零一百零五"},
  {20001007, "二千万一千零七"},
  {2000100190, "二十亿零一十万零一百九十"},
  {1040010000, "一十亿四千零一万"},
  {200012301, "二亿零一万二千三百零一"},
  {2005010010, "二十亿零五百零一万零一十"},
  {4009060200, "四十亿零九百零六万零二百"},
  {4294967295, "四十二亿九千四百九十六万七千二百九十五"}
    """
        .trim().split(",".toRegex()) forEach { entry ->

      val pair = entry.trimWrapper().split(",".toRegex())

      hashMap.put(pair[0].toInt(), pair[1].trimWrapper())
    }

    return hashMap
  }

  fun String.trimWrapper(): String {
    return trim().substring(1, length() - 1)
  }

  Benchmark fun testNumToZh() {
    testDatas().forEach({ entry ->
      println("Transform ${entry.key} to Chinese: expected ${entry.value} actually ${numToZh(entry.key)}")
    })
  }

  companion object {
    platformStatic fun main(args: Array<String>) {
      val opt = OptionsBuilder().include(javaClass.getSimpleName()).build()
      Runner(opt).run()
    }
  }
}