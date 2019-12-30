package com.github.int28h.getweather.handler

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.ConnectException

@Component
@RestControllerAdvice
class TimeoutExceptionHandler{
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    fun processValidationError(ex: ConnectException): Any {
        return "Превышено время ожидания ответа от сервера или сервер недоступен."
    }
}