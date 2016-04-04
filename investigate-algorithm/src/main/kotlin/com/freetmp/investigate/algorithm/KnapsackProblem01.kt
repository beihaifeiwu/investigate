package com.freetmp.investigate.algorithm

/**
 * 0-1背包的问题
 *
 * 问题描述：
 *    给定N中物品和一个背包。物品i的重量是Wi,其价值位Vi ，背包的容量为C。
 *    问应该如何选择装入背包的物品，使得转入背包的物品的总价值为最大？？
 *    在选择物品的时候，对每种物品i只有两种选择，即装入背包或不装入背包。
 *    不能讲物品i装入多次，也不能只装入物品的一部分。因此，该问题被称为0-1背包问题。
 *
 *
 * Created by liupin on 2016/4/4.
 */
object KnapsackProblem01 {

  /**
   * 物品的数据结构， 属性包括重量、价值以及选择状态（0：未选中，1：已选中，2：已经不可选）
   */
  data class TagObject(val weight: Int, val price: Int, var status: Int) {
    override fun toString(): String = "($weight, $price)"
  }

  /**
   * 背包问题的数据结构
   */
  data class TagKnapstackProblem(val objects: Array<TagObject>, val totalC: Int)

  /**
   * 贪婪法
   */
  fun greedy(problem: TagKnapstackProblem, selectPolicy: (Array<TagObject>, Int) -> TagObject?) {
    var ntc = 0
    while (true) {
      // selectPolicy 每次选择最合适策略的那个物品，选后再检查
      val tagObject = selectPolicy(problem.objects, problem.totalC - ntc) ?: break

      // 所选物品是否满足背包承重要求？
      if (ntc + tagObject.weight <= problem.totalC) {
        tagObject.status = 1
        ntc += tagObject.weight
      } else {
        // 不能选择这个物品了，做个标记后重新选
        tagObject.status = 2
      }
    }

    print("选中的物品有：")
    problem.objects.filter { it.status == 1 }.forEach { print("$it ") }
    println()
    println("总重量：${problem.objects.filter { it.status == 1 }.sumBy { it.weight }}")
    println("总价值：${problem.objects.filter { it.status == 1 }.sumBy { it.price }}")
  }

  @JvmStatic fun main(args: Array<String>) {

    /**
     * 根据价值密度选择，物品的价值密度定义为Si = Pi / Wi
     */
    val selectPolicy: (Array<TagObject>, Int) -> TagObject? = { array, c ->
      array.filter { it.status == 0 }.maxBy { it.price / it.weight }
    }

    val objects = arrayOf(TagObject(35, 10, 0), TagObject(30, 40, 0),TagObject(60, 30, 0),
        TagObject(50, 50, 0), TagObject(40, 35, 0),TagObject(10, 40, 0),TagObject(25, 30, 0))

    greedy(TagKnapstackProblem(objects, 150), selectPolicy)
  }
}