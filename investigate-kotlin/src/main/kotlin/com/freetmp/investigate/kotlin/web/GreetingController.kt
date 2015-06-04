package com.freetmp.investigate.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import kotlin.platform.platformStatic

/**
 * Created by LiuPin on 2015/6/3.
 */
data public class Greeting(val id: Long, val content: String)

RestController
public class GreetingController {
  val counter = AtomicLong()

  RequestMapping("/greeting")
  public fun greeting(RequestParam(value = "name", defaultValue = "World") name: String): Greeting = Greeting(counter.incrementAndGet(), "Hello, $name")
}

SpringBootApplication
open public class Application {

  companion object {
    platformStatic public fun main(args: Array<String>){
      SpringApplication.run(javaClass<Application>(),*args)
    }
  }
}