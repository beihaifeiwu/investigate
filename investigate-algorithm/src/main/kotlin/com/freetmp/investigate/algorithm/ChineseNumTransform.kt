package com.freetmp.investigate.algorithm

import org.openjdk.jmh.annotations.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by pin on 2015/6/25.
 */
@State(Scope.Thread) @OutputTimeUnit(TimeUnit.MICROSECONDS) @Fork(1)
class ChineseNumTransform {

  val zhNumChar = charArrayOf('零', '一', '二', '三', '四', '五', '六', '七', '八', '九')
  val zhUnitChar = arrayOf("", "十", "百", "千")
  val zhUnitSection = arrayOf("", "万", "亿", "万亿")

  fun numToZh(number: Long): String {
    if (number == 0L) return zhNumChar[0].toString()
    return buildString {
      var unitPos = 0
      var needZero = false
      var num = number
      while (num > 0) {
        val section = num % 10000
        if (needZero) {
          insert(0, zhNumChar[0])
        }
        /*是否需要节权位*/
        insert(0, if (section != 0L) zhUnitSection[unitPos] else zhUnitSection[0])
        insert(0, secToZh(section))
        /*千位是？，需要在下一个section补零*/
        needZero = section < 1000 && section > 0
        num /= 10000
        unitPos++
      }
    }
  }

  fun secToZh(sec: Long): String {
    return buildString {
      var section = sec
      var zero = true
      var unitPos = 0
      while (section > 0) {
        val v = section % 10
        if (v == 0L) {
          if (section == 0L || !zero) {
            /*需要补，zero的作用是确保对连续的多个，只补一个零*/
            zero = true
            insert(0, zhNumChar[v.toInt()])
          }
        } else {
          zero = false
          insert(0, "${zhNumChar[v.toInt()]}${zhUnitChar[unitPos]}")
        }
        section /= 10
        unitPos++
      }
    }
  }

  fun zhToNum(zh: String): Long {
    var num = 0L
    var section = 0
    var temp = 0
    zh.toCharArray().forEach {
      val index = zhNumChar.indexOf(it)
      if (index != -1) {
        temp = index
      } else {
        when (it) {
          '十' -> section += temp * 10
          '百' -> section += temp * 100
          '千' -> section += temp * 1000
          '万' -> {
            num += (section + temp) * 10000L
            section = 0
          }
          '亿' -> {
            num += (section + temp) * 100000000L
            section = 0
          }
        }
        temp = 0
      }
    }
    num += section + temp
    return num
  }

  fun testDatas(): Map<Long, String> {
    val map = TreeMap<Long, String>()
    map.put(0, "零")
    map.put(1, "一")
    map.put(2, "二")
    map.put(3, "三")
    map.put(4, "四")
    map.put(5, "五")
    map.put(6, "六")
    map.put(7, "七")
    map.put(8, "八")
    map.put(9, "九")
    map.put(10, "一十")
    map.put(11, "一十一")
    map.put(110, "一百一十")
    map.put(111, "一百一十一")
    map.put(100, "一百")
    map.put(102, "一百零二")
    map.put(1020, "一千零二十")
    map.put(1001, "一千零一")
    map.put(1015, "一千零一十五")
    map.put(1000, "一千")
    map.put(10000, "一万")
    map.put(20010, "二万零一十")
    map.put(20001, "二万零一")
    map.put(100000, "一十万")
    map.put(1000000, "一百万")
    map.put(10000000, "一千万")
    map.put(100000000, "一亿")
    map.put(1000000000, "一十亿")
    map.put(1000001000, "一十亿一千")
    map.put(1000000100, "一十亿零一百")
    map.put(200010, "二十万零一十")
    map.put(2000105, "二百万零一百零五")
    map.put(20001007, "二千万一千零七")
    map.put(2000100190, "二十亿零一十万零一百九十")
    map.put(1040010000, "一十亿四千零一万")
    map.put(200012301, "二亿零一万二千三百零一")
    map.put(2005010010, "二十亿零五百零一万零一十")
    map.put(4009060200L, "四十亿零九百零六万零二百")
    map.put(4294967295L, "四十二亿九千四百九十六万七千二百九十五")
    return map
  }

  @Benchmark fun testNumToZh() {
    testDatas().forEach({ entry ->
      val str = numToZh(entry.key)
      assert(entry.value == str) { "Error: cannot transform ${entry.key} correctly" }
      println("Transform ${entry.key} to Chinese: expected ${entry.value} actually $str")
    })
  }

  @Benchmark fun testZhToNum() {
    testDatas().forEach { entry ->
      val num = zhToNum(entry.value)
      assert(entry.key == num) { "Error: cannot transform ${entry.value} correctly" }
      println("Transform ${entry.value} to number: expected ${entry.key} actually $num")
    }
  }

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      /*      val opt = OptionsBuilder().include(javaClass.getSimpleName()).build()
            Runner(opt).run()*/
      ChineseNumTransform().testNumToZh()
      ChineseNumTransform().testZhToNum()
    }
  }
}