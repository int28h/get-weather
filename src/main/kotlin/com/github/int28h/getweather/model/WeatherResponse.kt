package com.github.int28h.getweather.model

data class ForecastResponse(
    val list: List<DailyForecastResponse>
)

data class DailyForecastResponse(
    val temp: HashMap<String, Double>
)