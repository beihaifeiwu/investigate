package com.freetmp.investigate.kotlin.actor

import co.paralleluniverse.actors.ActorRef
import co.paralleluniverse.actors.ActorRegistry
import co.paralleluniverse.fibers.Suspendable
import co.paralleluniverse.kotlin.Actor
import co.paralleluniverse.kotlin.register
import co.paralleluniverse.kotlin.spawn
import co.paralleluniverse.kotlin.Actor.Companion.Timeout
import java.util.concurrent.TimeUnit

/**
 * Created by LiuPin on 2015/7/27.
 */

data class Msg(val txt: String, val from: ActorRef<Any?>)

public class Ping(val n: Int) : Actor() {
  Suspendable override fun doRun() {
    val pong = ActorRegistry.getActor<Any?>("pong")
    for(i in 1..n){
      pong.send(Msg("ping", self()))
      receive {
        when (it) {
          "pong" -> println("Ping received pong")
          else -> null
        }
      }
    }
    pong.send("finished")
    println("Ping existing")
  }

}

public class Pong() : Actor() {
  Suspendable override fun doRun() {
    while(true){
      receive(1000, TimeUnit.MILLISECONDS) {
        when (it) {
          is Msg -> { if (it.txt == "ping") it.from.send("pong") }
          "finished" -> { println("Pong received 'finished', exiting"); return }
          is Timeout -> { println("Pong timeout in 'receive' exiting"); return }
          else -> defer()
        }
      }
    }
  }

}

fun main(args: Array<String>){
  spawn(register("pong", Pong()))
  spawn(Ping(3))
}