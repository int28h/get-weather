package com.github.int28h.getweather.handler

import com.github.int28h.getweather.exception.InvalidException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Component
@RestControllerAdvice
class InvalidRequestHandler {
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun onValidationError(ex: InvalidException): Any {
        return mapOf("errors" to ex.errors?.fieldErrors?.associate {
            it.field to it.defaultMessage.orEmpty()
        })
    }
}