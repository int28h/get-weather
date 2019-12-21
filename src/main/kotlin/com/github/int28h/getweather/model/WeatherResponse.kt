package com.github.int28h.getweather.model

data class ForecastResponse(
    val list: List<DailyForecastResponse>
)

data class DailyForecastResponse(
    val main: HashMap<String, Float>
)

data class GetWeatherResponse(
    val todayTemp: Float?,
    val minTemp: Float?
)