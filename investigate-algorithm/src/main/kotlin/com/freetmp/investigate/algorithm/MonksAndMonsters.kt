package com.freetmp.investigate.algorithm

import java.util.*
import kotlin.platform.platformStatic

/**
 * Created by pin on 2015/6/28.
 */
public class MonksAndMonsters {
  enum class BoatPos { LOCAL, REMOTE }
  data class ItemState(val localMonk: Int = 3, val localMonster: Int = 3,
                       val remoteMonk: Int = 0, val remoteMonster: Int = 0,
                       val boat: BoatPos = BoatPos.LOCAL,
                       val curAct: Action? = null)

  enum class ActionName {
    ONE_MONSTER_GO, TWO_MONSTER_GO, ONE_MONK_GO, TWO_MONK_GO,
    ONE_MONSTER_ONE_MONK_GO, ONE_MONSTER_BACK, TWO_MONSTER_BACK, ONE_MONK_BACK,
    TWO_MONK_BACK, ONE_MONSTER_ONE_MONK_BACK
  }

  data class Action(val name: ActionName, val boatTo: BoatPos, val moveMonks: Int, val moveMonsters: Int)

  val actions = arrayListOf<Action>()

  val stack = Stack<ItemState>()

  var count = 0

  init {
    actions add Action(ActionName.ONE_MONSTER_GO, BoatPos.REMOTE, 0, 1)
    actions add Action(ActionName.TWO_MONSTER_GO, BoatPos.REMOTE, 0, 2)
    actions add Action(ActionName.ONE_MONK_GO, BoatPos.REMOTE, 1, 0)
    actions add Action(ActionName.TWO_MONK_GO, BoatPos.REMOTE, 2, 0)
    actions add Action(ActionName.ONE_MONSTER_ONE_MONK_GO, BoatPos.REMOTE, 1, 1)
    actions add Action(ActionName.ONE_MONSTER_BACK, BoatPos.LOCAL, 0, -1)
    actions add Action(ActionName.TWO_MONSTER_BACK, BoatPos.LOCAL, 0, -2)
    actions add Action(ActionName.ONE_MONK_BACK, BoatPos.LOCAL, -1, 0)
    actions add Action(ActionName.TWO_MONK_BACK, BoatPos.LOCAL, -2, 0)
    actions add Action(ActionName.ONE_MONSTER_ONE_MONK_BACK, BoatPos.LOCAL, -1, -1)

    stack push ItemState()
  }

  fun ItemState.canTake(action: Action): Boolean {
    if (boat == action.boatTo)
      return false
    if (localMonk - action.moveMonks < 0 || localMonk - action.moveMonks > 3)
      return false
    if (localMonster - action.moveMonsters < 0 || localMonster - action.moveMonsters > 3)
      return false
    return true
  }

  fun ItemState.take(action: Action): ItemState = ItemState(this.localMonk - action.moveMonks, this.localMonster - action.moveMonsters,
      this.remoteMonk + action.moveMonks, this.remoteMonster + action.moveMonsters, action.boatTo, action)

  fun ItemState.isFinalState(): Boolean = localMonk == 0 && localMonster == 0 && remoteMonk == 3 && remoteMonster == 3 && boat == BoatPos.REMOTE

  fun ItemState.isValid(): Boolean = localMonk + remoteMonk == 3 && localMonster + remoteMonster == 3
      && (localMonk == 0 || localMonk >= localMonster) && (remoteMonk == 0 || remoteMonk >= remoteMonster)

  fun ItemState.existedInTheProcessedStates(): Boolean = stack.filter({ it.localMonk == localMonk && it.localMonster == localMonster && it.boat == boat }).isNotEmpty()

  fun searchState(state: ItemState = stack.peek()) {
    if (state.isFinalState()) {
      println("*************Found ${++count}th Solution*************")
      stack.forEachIndexed { index, itemState ->
        if (itemState.curAct != null)
          println("${index}. ${itemState.curAct.name.toString().toLowerCase().replace('_', ' ')}.")
      }
      println("*************End of ${count}th Solution*************")
    }
    actions.forEach { action ->
      if (state.canTake(action)) {
        val next = state.take(action)
        if (next.isValid() && !next.existedInTheProcessedStates()) {
          stack.push(next)
          searchState()
          stack.pop()
        }
      }
    }
  }

  companion object {
    platformStatic fun main(args: Array<String>) {
      MonksAndMonsters().searchState()
    }
  }
}