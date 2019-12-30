package com.github.int28h.getweather.controller

import com.github.int28h.getweather.config.AppConfig
import com.github.int28h.getweather.exception.InvalidRequest
import com.github.int28h.getweather.model.ForecastResponse
import com.github.int28h.getweather.model.GetWeatherResponse
import com.google.gson.Gson
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URL

const val DAYS_COUNT = 3

@RestController
class WeatherController(private val config: AppConfig) {
    private val gson = Gson()

    /**
     * Проверка входных аргументов
     */
    private fun checkArguments(city: String, country: String) {
        val errors = org.springframework.validation.BindException(this, "")
        if (city == "") {
            errors.addError(FieldError("", "city", "Данное поле не может быть пустым"))
        }
        if (country == "") {
            errors.addError(FieldError("", "country", "Данное поле не может быть пустым"))
        }

        InvalidRequest.check(errors)
    }

    /**
     * Вызов API OpenWeatherMap
     */
    private fun apiCall(city: String, country: String): String? {
        try {
            return URL("https://api.openweathermap.org/data/2.5/forecast?q=$city,$country&cnt=$DAYS_COUNT&appid=${config.apiKey}&units=metric")
                .readText(Charsets.UTF_8)
        } catch (e: Exception) {
            return null
        }
    }

    @GetMapping("/api/getWeather")
    fun getWeather(@RequestParam(value = "city") city: String, @RequestParam(value = "country") country: String): Any {
        checkArguments(city, country)

        val response = apiCall(city, country)

        val forecast = gson.fromJson(response, ForecastResponse::class.java)

        val todayTemp: Float? = forecast.list[0].main["temp"]
        val minTemp: Float? = forecast.list.mapNotNull { it.main["temp"] }.min()

        return gson.toJson(GetWeatherResponse(todayTemp, minTemp))
    }
}
