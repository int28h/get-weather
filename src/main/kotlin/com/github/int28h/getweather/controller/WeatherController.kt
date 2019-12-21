package com.github.int28h.getweather.controller

import com.github.int28h.getweather.config.AppConfig
import com.github.int28h.getweather.model.ForecastResponse
import com.github.int28h.getweather.model.GetWeatherResponse
import com.google.gson.Gson
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URL

const val DAYS_COUNT = 3

@RestController
class WeatherController(private val config: AppConfig) {

    @GetMapping("/api/getWeather")
    fun getWeather(@RequestParam(value="city") city: String, @RequestParam(value="country") country: String): String {
        var response: String
        val gson = Gson()

        try{
            response = URL("https://api.openweathermap.org/data/2.5/forecast?q=$city,$country&cnt=$DAYS_COUNT&appid=${config.apiKey}&units=metric").readText(
                Charsets.UTF_8
            )
            val forecast = gson.fromJson(response, ForecastResponse::class.java)

            val todayTemp: Float? = forecast.list[0].main["temp"]
            val minTemp: Float? = forecast.list.mapNotNull { it.main["temp"] }.min()

            return gson.toJson(GetWeatherResponse(todayTemp, minTemp))
        } catch (e: Exception){
            return e.message.toString()
        }
    }
}
