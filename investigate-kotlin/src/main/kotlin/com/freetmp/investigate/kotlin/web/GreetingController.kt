package com.freetmp.investigate.kotlin.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by LiuPin on 2015/6/3.
 */
data class Greeting(val id: Long, val content: String)

@RestController
class GreetingController {
  val counter = AtomicLong()

  @RequestMapping("/greeting")
  fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting = Greeting(counter.incrementAndGet(), "Hello, $name")
}

@SpringBootApplication
open class Application {

  companion object {
      @JvmStatic fun main(args: Array<String>){
      SpringApplication.run(Application::class.java,*args)
    }
  }
}