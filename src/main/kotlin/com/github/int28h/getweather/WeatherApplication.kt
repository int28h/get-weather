package com.github.int28h.getweather

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherApplication

fun main(args: Array<String>) {
   runApplication<WeatherApplication>(*args)
}