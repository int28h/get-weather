package com.github.int28h.getweather.controller

import com.github.int28h.getweather.model.ForecastResponse
import com.google.gson.Gson
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URL

const val DAYS_COUNT = 3
const val API_KEY = ""

@RestController
class WeatherController{

    @GetMapping("/api/getWeather")
    fun getWeather(@RequestParam(value="city") city: String, @RequestParam(value="country") country: String): String {
        var response: String
        val gson = Gson()

        try{
            response = URL("https://api.openweathermap.org/data/2.5/forecast?q=$city,$country&cnt=$DAYS_COUNT&appid=$API_KEY").readText(
                Charsets.UTF_8
            )
            //val forecast = gson.fromJson(response, ForecastResponse::class.java)
            //val dailyForecast = forecast.list[0].temp["day"]

            //return dailyForecast.toString()
            return response
        } catch (e: Exception){
            return e.message.toString()
        }
    }
}
