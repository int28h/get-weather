package com.github.int28h.getweather

import com.github.int28h.getweather.config.AppConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableConfigurationProperties(AppConfig::class)
class WeatherApplication

fun main(args: Array<String>) {
   runApplication<WeatherApplication>(*args)
}